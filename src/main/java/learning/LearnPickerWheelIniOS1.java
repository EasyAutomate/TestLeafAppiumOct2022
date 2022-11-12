package learning;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LearnPickerWheelIniOS1 {
public static void main(String[] args) throws MalformedURLException, InterruptedException {
	DesiredCapabilities dc = new DesiredCapabilities();
	dc.setCapability("appium:app", System.getProperty("user.dir") + "/apks/uicatalog.zip");
	dc.setCapability("appium:deviceName", "iPhone SE (3rd generation)");
	//dc.setCapability("bundleId","com.example.apple-samplecode.UICatalog");
	dc.setCapability("automationName", "XCUITest");
	//dc.setCapability("udid","D5A3818B-6D4B-466C-8F09-0AC8C380E937");
	//dc.setCapability("platformName", "iOS");
	//AppiumDriver driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
	IOSDriver driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
	driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("100");
	driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("100");
	driver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("100");
}
}
