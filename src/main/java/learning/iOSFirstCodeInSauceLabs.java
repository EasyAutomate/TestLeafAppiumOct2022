package learning;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class iOSFirstCodeInSauceLabs {
public static void main(String[] args) throws MalformedURLException, InterruptedException {
	MutableCapabilities caps = new MutableCapabilities();
	caps.setCapability("platformName", "iOS");
	caps.setCapability("appium:app", "storage:filename=UICatalog.zip"); // The filename of the mobile app
	caps.setCapability("appium:deviceName", "iPhone Simulator");
	caps.setCapability("appium:platformVersion", "15.4");
	caps.setCapability("appium:automationName", "XCUITest");
	MutableCapabilities sauceOptions = new MutableCapabilities();
	sauceOptions.setCapability("appiumVersion", "1.22.3");
	sauceOptions.setCapability("build", "<your build id>");
	sauceOptions.setCapability("name", "<your test name>");
	caps.setCapability("sauce:options", sauceOptions);

	URL url = new URL("https://appium1321:4386a764-7c64-4349-a250-71b4a807b1ce@ondemand.us-west-1.saucelabs.com:443/wd/hub");
	IOSDriver driver = new IOSDriver(url, caps);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.findElement(AppiumBy.accessibilityId("Action Sheets")).click();
	driver.findElement(AppiumBy.accessibilityId("Okay / Cancel")).click();
	driver.findElement(AppiumBy.accessibilityId("OK")).click();
	Thread.sleep(5000);
	driver.quit();
	
	
	
}
}
