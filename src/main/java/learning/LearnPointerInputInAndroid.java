package learning;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class LearnPointerInputInAndroid {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("appium:app", System.getProperty("user.dir") + "/apks/MultiTouch Tester_v1.2.apk");
		dc.setCapability("appium:deviceName", "my emulator");
		dc.setCapability("noReset", true);
		// dc.setCapability("platformName", "Android");
		// AppiumDriver driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"),
		// dc);
		AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
		Thread.sleep(3000);
		int maxX = driver.manage().window().getSize().getWidth();
		int maxY = driver.manage().window().getSize().getHeight();
		// Swipe Up
		swipe((int) (maxX * 0.5), (int) (maxY * 0.8), (int) (maxX * 0.5), (int) (maxY * 0.2), driver);
		Thread.sleep(2000);
		// Swipe Down
		swipe((int) (maxX * 0.5), (int) (maxY * 0.2), (int) (maxX * 0.5), (int) (maxY * 0.8), driver);
		Thread.sleep(2000);
		// Swipe Left
		swipe((int) (maxX * 0.8), (int) (maxY * 0.5), (int) (maxX * 0.2), (int) (maxY * 0.5), driver);
		Thread.sleep(2000);
		// Swipe Right
		swipe((int) (maxX * 0.2), (int) (maxY * 0.5), (int) (maxX * 0.8), (int) (maxY * 0.5), driver);
		Thread.sleep(2000);
		driver.quit();
	}

	public static void swipe(int startX, int startY, int endX, int endY, AndroidDriver driver) {
		PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger 1");
		Sequence seq = new Sequence(finger1, 1);
		seq.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), startX, startY));
		seq.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg()));
		seq.addAction(finger1.createPointerMove(Duration.ofSeconds(2), Origin.viewport(), endX, endY));
		seq.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(seq));
	}
}
