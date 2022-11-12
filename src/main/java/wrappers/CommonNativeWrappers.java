package wrappers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.PullsFiles;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.appium.java_client.remote.SupportsRotation;

public class CommonNativeWrappers {
	public AppiumDriver driver;
	public static final int MAX_SCROLL = 10;

	// To launch the application (Native/Hybrid)
	public boolean launchApp(String platformName, String deviceName, String udid, String appPackage, String appActivity,
			String automationName, String chromeDriverPort, String systemPort, String xcodeOrgId, String xcodeSigningId,
			String bundleId, String app, String mjpegServerPort, String wdaLocalPort) {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			if (!automationName.equals(""))
				dc.setCapability("automationName", automationName);
			if (!udid.equals(""))
				dc.setCapability("udid", udid);
			if (!app.equals(""))
				dc.setCapability("app", System.getProperty("user.dir") + app);
			// Android
			if (!appPackage.equals(""))
				dc.setCapability("appPackage", appPackage);
			if (!appActivity.equals(""))
				dc.setCapability("appActivity", appActivity);
			if (!chromeDriverPort.equals(""))
				dc.setCapability("chromedriverPort", chromeDriverPort);
			if (!mjpegServerPort.equals(""))
				dc.setCapability("mjpegServerPort", mjpegServerPort);
			if (!systemPort.equals(""))
				dc.setCapability("systemPort", systemPort);
			// iOS
			if (!wdaLocalPort.equals(""))
				dc.setCapability("wdaLocalPort", wdaLocalPort);
			if (!xcodeOrgId.equals(""))
				dc.setCapability("xcodeOrgId", xcodeOrgId);
			if (!xcodeSigningId.equals(""))
				dc.setCapability("xcodeSigningId", xcodeSigningId);
			if (!bundleId.equals(""))
				dc.setCapability("bundleId", bundleId);
			// Mandatory desired capabilities
			dc.setCapability("deviceName", deviceName);
			// Comment the below line based on need
			dc.setCapability("noReset", true);
			if (platformName.equalsIgnoreCase("Android")) {
				// Comment the below line based on need
				dc.setCapability("autoGrantPermissions", true);
				driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			} else if (platformName.equalsIgnoreCase("iOS")) {
				// Comment the below line based on need
				dc.setCapability("autoAcceptAlerts", true);
				driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// Install the application if the application is not installed
	public boolean verifyAndInstallApp(String bundleIdOrAppPackage, String appPath) {
		boolean bInstallSuccess = false;
		try {
			if (((InteractsWithApps) driver).isAppInstalled(bundleIdOrAppPackage)) {
				((InteractsWithApps) driver).removeApp(bundleIdOrAppPackage);
			}
			((InteractsWithApps) driver).installApp(appPath);
			bInstallSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bInstallSuccess;
	}

	// Not recommended for use (Sleep). Should not be used for app testing
	public void sleep(int mSec) {
		try {
			Thread.sleep(mSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// To print the context available in application
	public void printContext() {
		try {
			Set<String> contexts = ((SupportsContextSwitching) driver).getContextHandles();
			for (String context : contexts) {
				System.out.println(context);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To switch the context available in application
	public boolean switchContext(String context) {
		try {
			((SupportsContextSwitching) driver).context(context);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// To switch the context as Native
	public boolean switchNativeview() {
		try {
			Set<String> contextNames = ((SupportsContextSwitching) driver).getContextHandles();
			for (String contextName : contextNames) {
				if (contextName.contains("NATIVE_APP"))
					((SupportsContextSwitching) driver).context(contextName);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// Locators ENUM
	public enum Locators {
		ID("id"), NAME("name"), CLASS_NAME("className"), LINK_TEXT("link"), PARTIAL_LINK_TEXT("partialLink"),
		TAG_NAME("tag"), CSS("css"), XPATH("xpath"), ACCESSIBILITY_ID("accessibilityId");

		private String value;

		private Locators(String value) {
			this.value = value;
		}

		public String asString() {
			return this.value;
		}
	}

	// To get the WebElements
	public WebElement getWebElement(String locator, String locValue) {
		try {
			switch (locator) {
			case "id":
				return driver.findElement(By.id(locValue));
//				return driver.findElement(AppiumBy.xpath("//*[@id='" + locValue + "']"));
			case "name":
				return driver.findElement(By.name(locValue));
//				return driver.findElement(AppiumBy.xpath("//*[@name='" + locValue + "']"));
			case "className":
				return driver.findElement(AppiumBy.className(locValue));
			case "link":
				return driver.findElement(AppiumBy.linkText(locValue));
			case "partialLink":
				return driver.findElement(AppiumBy.partialLinkText(locValue));
			case "tag":
				return driver.findElement(AppiumBy.tagName(locValue));
			case "css":
				return driver.findElement(AppiumBy.cssSelector(locValue));
			case "xpath":
				return driver.findElement(AppiumBy.xpath(locValue));
			case "accessibilityId":
				return driver.findElement(AppiumBy.accessibilityId(locValue));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// To take screenshots in application
	public long takeScreenShot() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			File srcFiler = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFiler, new File("./reports/images/" + number + ".png"));
		} catch (WebDriverException e) {
			e.printStackTrace();
			System.err.println("The browser has been closed.");
		} catch (IOException e) {
			System.err.println("The snapshot could not be taken");
		}
		return number;
	}

	// To verify element is displayed
	public boolean eleIsDisplayed(WebElement ele) {
		try {
			return ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	// To verify text in element
	public boolean verifyText(WebElement ele, String Expected) {
		boolean val = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(ele));
			String name = ele.getText();
			if (name.equals(Expected)) {
				val = true;
			} else
				val = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	// To scroll in application
	private boolean scroll(int startX, int startY, int endX, int endY) {
		try {
			PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
			Sequence sequence = new Sequence(finger, 1);
			sequence.addAction(finger.createPointerMove(Duration.ZERO, Origin.viewport(), startX, startY));
			sequence.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()));
			sequence.addAction(finger.createPointerMove(Duration.ofSeconds(2), Origin.viewport(), endX, endY));
			sequence.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));
			driver.perform(Arrays.asList(sequence));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// To double tap in application
	public void doubleTap(int x, int y) {
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Sequence doubleTap = new Sequence(finger, 1);
		doubleTap.addAction(finger.createPointerMove(Duration.ofMillis(0), Origin.viewport(), x, y));
		doubleTap.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()));
		doubleTap.addAction(new Pause(finger, Duration.ofMillis(200)));
		doubleTap.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));
		doubleTap.addAction(new Pause(finger, Duration.ofMillis(200)));
		doubleTap.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()));
		doubleTap.addAction(new Pause(finger, Duration.ofMillis(200)));
		doubleTap.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(doubleTap));
	}

	// To pinch in application
	public void pinchInApp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		int maxY = driver.manage().window().getSize().getHeight();
		int maxX = driver.manage().window().getSize().getWidth();
		PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
		Sequence a = new Sequence(finger1, 1);
		a.addAction(finger1.createPointerMove(Duration.ofSeconds(0), Origin.viewport(), (int) (maxX * 0.75),
				(int) (maxY * 0.25)));
		a.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg()));
		a.addAction(finger1.createPointerMove(Duration.ofSeconds(1), Origin.viewport(), (int) (maxX * 0.5),
				(int) (maxY * 0.5)));
		a.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
		PointerInput finger2 = new PointerInput(Kind.TOUCH, "finger2");
		Sequence b = new Sequence(finger2, 1);
		b.addAction(finger2.createPointerMove(Duration.ofSeconds(0), Origin.viewport(), (int) (maxX * 0.25),
				(int) (maxY * 0.75)));
		b.addAction(finger2.createPointerDown(MouseButton.LEFT.asArg()));
		b.addAction(finger2.createPointerMove(Duration.ofSeconds(1), Origin.viewport(), (int) (maxX * 0.5),
				(int) (maxY * 0.5)));
		b.addAction(finger2.createPointerUp(MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(a, b));
	}

	// To zoom in application
	public void zoomInApp() {
		int maxY = driver.manage().window().getSize().getHeight();
		int maxX = driver.manage().window().getSize().getWidth();
		PointerInput finger1 = new PointerInput(Kind.TOUCH, "lokesh-finger1");
		Sequence a = new Sequence(finger1, 1);
		a.addAction(finger1.createPointerMove(Duration.ofSeconds(0), Origin.viewport(), (int) (maxX * 0.5),
				(int) (maxY * 0.5)));
		a.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg()));
		a.addAction(finger1.createPointerMove(Duration.ofSeconds(1), Origin.viewport(), (int) (maxX * 0.75),
				(int) (maxY * 0.25)));
		a.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
		PointerInput finger2 = new PointerInput(Kind.TOUCH, "lokesh-finger2");
		Sequence b = new Sequence(finger2, 1);
		b.addAction(finger2.createPointerMove(Duration.ofSeconds(0), Origin.viewport(), (int) (maxX * 0.5),
				(int) (maxY * 0.5)));
		b.addAction(finger2.createPointerDown(MouseButton.LEFT.asArg()));
		b.addAction(finger2.createPointerMove(Duration.ofSeconds(1), Origin.viewport(), (int) (maxX * 0.25),
				(int) (maxY * 0.75)));
		b.addAction(finger2.createPointerUp(MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(a, b));
	}

	// To scroll up in application
	public boolean swipeUpInApp() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.5);
		int startY = (int) (size.getHeight() * 0.8);
		int endX = (int) (size.getWidth() * 0.5);
		int endY = (int) (size.getHeight() * 0.2);
		return scroll(startX, startY, endX, endY);
	}

	// To scroll down in application
	public boolean swipeDownInApp() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.5);
		int startY = (int) (size.getHeight() * 0.2);
		int endX = (int) (size.getWidth() * 0.5);
		int endY = (int) (size.getHeight() * 0.8);
		return scroll(startX, startY, endX, endY);
	}

	// To scroll left in application
	public boolean swipeLeftInApp() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.8);
		int startY = (int) (size.getHeight() * 0.5);
		int endX = (int) (size.getWidth() * 0.2);
		int endY = (int) (size.getHeight() * 0.5);
		return scroll(startX, startY, endX, endY);
	}

	// To scroll right in application
	public boolean swipeRightInApp() {
		Dimension size = driver.manage().window().getSize();
		int startX = (int) (size.getWidth() * 0.2);
		int startY = (int) (size.getHeight() * 0.5);
		int endX = (int) (size.getWidth() * 0.8);
		int endY = (int) (size.getHeight() * 0.5);
		return scroll(startX, startY, endX, endY);
	}

	// To scroll down within web element in application
	public boolean swipeDowninAppWithWebElement(WebElement ele) {
		String bounds = null;
		if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("Android")) {
			bounds = ele.getAttribute("bounds");
		} else if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("iOS")) {
			Rectangle rect = ele.getRect();
			bounds = "[" + rect.getX() + "," + rect.getY() + "][" + (rect.getX() + rect.getWidth()) + ","
					+ (rect.getY() + rect.getHeight()) + "]";
		}
		String[] split = bounds.replace("][", "!").replace("[", "").replace("]", "").split("!");
		String[] eleStart = split[0].split(",");
		String[] eleEnd = split[1].split(",");
		int startX = (int) ((Integer.parseInt(eleEnd[0]) - Integer.parseInt(eleStart[0])) * 0.5)
				+ Integer.parseInt(eleStart[0]);
		int startY = (int) ((Integer.parseInt(eleEnd[1]) - Integer.parseInt(eleStart[1])) * 0.2)
				+ Integer.parseInt(eleStart[1]);
		int endX = (int) ((Integer.parseInt(eleEnd[0]) - Integer.parseInt(eleStart[0])) * 0.5)
				+ Integer.parseInt(eleStart[0]);
		int endY = (int) ((Integer.parseInt(eleEnd[1]) - Integer.parseInt(eleStart[1])) * 0.8)
				+ Integer.parseInt(eleStart[1]);
		return scroll(startX, startY, endX, endY);
	}

	// To scroll up within web element in application
	public boolean swipeUpinAppWithWebElement(WebElement ele) {
		String bounds = null;
		if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("Android")) {
			bounds = ele.getAttribute("bounds");
		} else if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("iOS")) {
			bounds = "[" + ele.getAttribute("x") + "," + ele.getAttribute("y") + "][" + ele.getAttribute("width") + ","
					+ ele.getAttribute("height") + "]";
		}
		String[] split = bounds.replace("][", "!").replace("[", "").replace("]", "").split("!");
		String[] eleStart = split[0].split(",");
		String[] eleEnd = split[1].split(",");
		int startX = (int) ((Integer.parseInt(eleEnd[0]) - Integer.parseInt(eleStart[0])) * 0.5)
				+ Integer.parseInt(eleStart[0]);
		int startY = (int) ((Integer.parseInt(eleEnd[1]) - Integer.parseInt(eleStart[1])) * 0.8)
				+ Integer.parseInt(eleStart[1]);
		int endX = (int) ((Integer.parseInt(eleEnd[0]) - Integer.parseInt(eleStart[0])) * 0.5)
				+ Integer.parseInt(eleStart[0]);
		int endY = (int) ((Integer.parseInt(eleEnd[1]) - Integer.parseInt(eleStart[1])) * 0.2)
				+ Integer.parseInt(eleStart[1]);
		return scroll(startX, startY, endX, endY);
	}

	// To scroll right within web element in application
	public boolean swipeRightinAppWithWebElement(WebElement ele) {
		String bounds = null;
		if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("Android")) {
			bounds = ele.getAttribute("bounds");
		} else if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("iOS")) {
			bounds = "[" + ele.getAttribute("x") + "," + ele.getAttribute("y") + "][" + ele.getAttribute("width") + ","
					+ ele.getAttribute("height") + "]";
		}
		String[] split = bounds.replace("][", "!").replace("[", "").replace("]", "").split("!");
		String[] eleStart = split[0].split(",");
		String[] eleEnd = split[1].split(",");
		int startX = (int) ((Integer.parseInt(eleEnd[0]) - Integer.parseInt(eleStart[0])) * 0.2)
				+ Integer.parseInt(eleStart[0]);
		int startY = (int) ((Integer.parseInt(eleEnd[1]) - Integer.parseInt(eleStart[1])) * 0.5)
				+ Integer.parseInt(eleStart[1]);
		int endX = (int) ((Integer.parseInt(eleEnd[0]) - Integer.parseInt(eleStart[0])) * 0.8)
				+ Integer.parseInt(eleStart[0]);
		int endY = (int) ((Integer.parseInt(eleEnd[1]) - Integer.parseInt(eleStart[1])) * 0.5)
				+ Integer.parseInt(eleStart[1]);
		return scroll(startX, startY, endX, endY);
	}

	// To scroll left within web element in application
	public boolean swipeLeftinAppWithWebElement(WebElement ele) {
		String bounds = null;
		if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("Android")) {
			bounds = ele.getAttribute("bounds");
		} else if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("iOS")) {
			bounds = "[" + ele.getAttribute("x") + "," + ele.getAttribute("y") + "][" + ele.getAttribute("width") + ","
					+ ele.getAttribute("height") + "]";
		}
		String[] split = bounds.replace("][", "!").replace("[", "").replace("]", "").split("!");
		String[] eleStart = split[0].split(",");
		String[] eleEnd = split[1].split(",");
		int startX = (int) ((Integer.parseInt(eleEnd[0]) - Integer.parseInt(eleStart[0])) * 0.8)
				+ Integer.parseInt(eleStart[0]);
		int startY = (int) ((Integer.parseInt(eleEnd[1]) - Integer.parseInt(eleStart[1])) * 0.5)
				+ Integer.parseInt(eleStart[1]);
		int endX = (int) ((Integer.parseInt(eleEnd[0]) - Integer.parseInt(eleStart[0])) * 0.2)
				+ Integer.parseInt(eleStart[0]);
		int endY = (int) ((Integer.parseInt(eleEnd[1]) - Integer.parseInt(eleStart[1])) * 0.5)
				+ Integer.parseInt(eleStart[1]);
		return scroll(startX, startY, endX, endY);
	}

	// To scroll up until web element is displayed in application
	public boolean swipeUpinAppUntilElementIsVisible(String locator, String locValue) {
		int i = 1;
		while (!eleIsDisplayed(getWebElement(locator, locValue))) {
			swipeUpInApp();
			i++;
			if (i == MAX_SCROLL)
				break;
		}
		return true;
	}

	// To scroll down until web element is displayed in application
	public boolean swipeDowninAppUntilElementIsVisible(String locator, String locValue) {
		int i = 1;
		while (!eleIsDisplayed(getWebElement(locator, locValue))) {
			swipeDownInApp();
			i++;
			if (i == MAX_SCROLL)
				break;
		}
		return true;
	}

	// To scroll left until web element is displayed in application
	public boolean swipeLeftInAppUntilElementIsVisible(String locator, String locValue) {
		int i = 1;
		while (!eleIsDisplayed(getWebElement(locator, locValue))) {
			swipeLeftInApp();
			i++;
			if (i == MAX_SCROLL)
				break;
		}
		return true;
	}

	// To scroll right until web element is displayed in application
	public boolean swipeRightinAppUntilElementIsVisible(String locator, String locValue) {
		int i = 1;
		while (!eleIsDisplayed(getWebElement(locator, locValue))) {
			swipeRightInApp();
			i++;
			if (i == MAX_SCROLL)
				break;
		}
		return true;
	}

	// To pull a file from the device
	public boolean pullFile(String phonePath, String destinationPath) {
		byte[] data = ((PullsFiles) driver).pullFile(phonePath);
		Path path = Paths.get(destinationPath);
		try {
			Files.write(path, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	// To close all the application opened in this session
	public void closeApp() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
			}
		}
	}

	// To set portrait orientation
	public boolean setPortraitOrientation() {
		((SupportsRotation) driver).rotate(ScreenOrientation.PORTRAIT);
		return true;
	}

	// To set landscape orientation
	public boolean setLanscapeOrientation() {
		((SupportsRotation) driver).rotate(ScreenOrientation.LANDSCAPE);
		return true;
	}

	// To hide the keyboard if it is visible
	public void hideKeyboard() {
		if (isKeyboardShown()) {
			try {
				((HidesKeyboard) driver).hideKeyboard();
			} catch (Exception e) {
				if (driver.getCapabilities().getPlatformName().toString().equalsIgnoreCase("iOS")) {
					String context = ((SupportsContextSwitching) driver).getContext();
					boolean isNative = context.equalsIgnoreCase("NATIVE_APP");
					if (!isNative) {
						switchNativeview();
					}
					if (isKeyboardShown()) {
						click(getWebElement(Locators.ACCESSIBILITY_ID.toString(), "Done"));
					}
					if (!isNative) {
						switchContext(context);
					}
				}
			}
		}
	}

	public boolean isKeyboardShown() {
		return ((HasOnScreenKeyboard) driver).isKeyboardShown();
	}

	// To get orientation set in the application
	public String getOrientation() {
		return ((SupportsRotation) driver).getOrientation().toString();
	}

	// To enter data in web element
	public boolean enterValue(WebElement ele, String data) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.clear();
		ele.sendKeys(data);
		hideKeyboard();
		return true;
	}

	// To click in web element
	public boolean click(WebElement ele) {
		System.out.println(ele);
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// To get text in web element
	public String getText(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ele));
		return ele.getText();
	}

	// To switch to another application installed in device
	public void switchToAnotherApp(String bundleIdOrAppPackage) {
		((InteractsWithApps) driver).activateApp(bundleIdOrAppPackage);
	}

	// To close the application installed in device
	public void stopRunningApp(String bundleIdOrAppPackage) {
		((InteractsWithApps) driver).terminateApp(bundleIdOrAppPackage);
	}
}
