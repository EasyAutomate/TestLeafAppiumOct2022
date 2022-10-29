package learning;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidFirstCodeUsingAppPackageAndAppActivity {
public static void main(String[] args) throws MalformedURLException, InterruptedException {
	DesiredCapabilities dc = new DesiredCapabilities();
	dc.setCapability("appium:deviceName", "my emulator");
	dc.setCapability("appPackage", "com.testleaf.leaforg");
	dc.setCapability("appActivity", "com.testleaf.leaforg.MainActivity");
	//dc.setCapability("platformName", "Android");
	//AppiumDriver driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
	AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.findElement(By.className("android.widget.EditText")).sendKeys("rajkumar@testleaf.com");
	driver.findElement(By.xpath("(//android.widget.EditText)[2]")).sendKeys("Leaf@123");
	driver.findElement(By.className("android.widget.Button")).click();
	Thread.sleep(5000);
	driver.quit();
	
	
	
}
}
