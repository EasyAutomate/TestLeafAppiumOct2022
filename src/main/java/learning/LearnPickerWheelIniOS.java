package learning;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class LearnPickerWheelIniOS {
public static void main(String[] args) throws MalformedURLException, InterruptedException {
	DesiredCapabilities dc = new DesiredCapabilities();
	dc.setCapability("appium:app", System.getProperty("user.dir") + "/apks/uicatalog.zip");
	dc.setCapability("appium:deviceName", "iPhone 14");
	//dc.setCapability("bundleId","com.example.apple-samplecode.UICatalog");
	dc.setCapability("automationName", "XCUITest");
	//dc.setCapability("udid","D5A3818B-6D4B-466C-8F09-0AC8C380E937");
	//dc.setCapability("platformName", "iOS");
	//AppiumDriver driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
	IOSDriver driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.findElement(AppiumBy.accessibilityId("Picker View")).click();

	HashMap<String, Object> params = new HashMap<>();
	params.put("order", "previous");
	params.put("offset", 0.15);
	params.put("element", driver.findElement(AppiumBy.accessibilityId("Red color component value")));
	driver.executeScript("mobile: selectPickerWheelValue", params);

	HashMap<String, Object> params1 = new HashMap<>();
	params1.put("order", "next");
	params1.put("offset", 0.15);
	params1.put("element", driver.findElement(AppiumBy.accessibilityId("Green color component value")));
	driver.executeScript("mobile: selectPickerWheelValue", params1);

}
}
