package learning;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidFirstCodeUsingSauceLabsInAndroid {
public static void main(String[] args) throws MalformedURLException, InterruptedException {
	MutableCapabilities caps = new MutableCapabilities();
	caps.setCapability("platformName", "Android");
	caps.setCapability("appium:app", "storage:filename=leaforg.apk"); // The filename of the mobile app
	caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
	caps.setCapability("appium:platformVersion", "12.0");
	caps.setCapability("appium:automationName", "UiAutomator2");
	MutableCapabilities sauceOptions = new MutableCapabilities();
	sauceOptions.setCapability("appiumVersion", "1.22.1");
	sauceOptions.setCapability("build", "<your build id>");
	sauceOptions.setCapability("name", "<your test name>");
	caps.setCapability("sauce:options", sauceOptions);

	URL url = new URL("https://appiumoct2022:6183b6c3-6cb2-4825-9c72-bf8e9c6ad29c@ondemand.us-west-1.saucelabs.com:443/wd/hub");
	AndroidDriver driver = new AndroidDriver(url, caps);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.findElement(By.className("android.widget.EditText")).sendKeys("rajkumar@testleaf.com");
	driver.findElement(By.xpath("(//android.widget.EditText)[2]")).sendKeys("Leaf@123");
	driver.findElement(By.className("android.widget.Button")).click();
	Thread.sleep(5000);
	driver.quit();
	
	
	
}
}
