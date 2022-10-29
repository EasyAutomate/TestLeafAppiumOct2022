package learning;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class LaunchAmazonUsingAppPackageAndAppActivity {
public static void main(String[] args) throws MalformedURLException, InterruptedException {
	DesiredCapabilities dc = new DesiredCapabilities();
	dc.setCapability("appium:deviceName", "my emulator");
	//dc.setCapability("appium:app", System.getProperty("user.dir")+"/apks/amazon.apk");
	dc.setCapability("appPackage", "com.amazon.mShop.android.shopping");
	dc.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
	//dc.setCapability("platformName", "Android");
	//AppiumDriver driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
	AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	Thread.sleep(5000);
	driver.quit();
	
	
	
}
}
