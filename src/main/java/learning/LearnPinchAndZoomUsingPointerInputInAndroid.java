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

public class LearnPinchAndZoomUsingPointerInputInAndroid {
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
		//Pinch
		PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger 1");
		Sequence seq = new Sequence(finger1, 1);
		seq.addAction(
				finger1.createPointerMove(Duration.ZERO, Origin.viewport(), (int) (maxX * 0.25), (int) (maxY * 0.25)));
		seq.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg()));
		seq.addAction(finger1.createPointerMove(Duration.ofSeconds(2), Origin.viewport(), (int) (maxX * 0.5),
				(int) (maxY * 0.5)));
		seq.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
		PointerInput finger2 = new PointerInput(Kind.TOUCH, "finger 2");
		Sequence seq1 = new Sequence(finger2, 1);
		seq1.addAction(
				finger2.createPointerMove(Duration.ZERO, Origin.viewport(), (int) (maxX * 0.75), (int) (maxY * 0.75)));
		seq1.addAction(finger2.createPointerDown(MouseButton.LEFT.asArg()));
		seq1.addAction(finger2.createPointerMove(Duration.ofSeconds(2), Origin.viewport(), (int) (maxX * 0.5),
				(int) (maxY * 0.5)));
		seq1.addAction(finger2.createPointerUp(MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(seq,seq1));
		//zoom
		PointerInput finger4 = new PointerInput(Kind.TOUCH, "finger 1");
		Sequence seq2 = new Sequence(finger4, 1);
		seq2.addAction(
				finger4.createPointerMove(Duration.ZERO, Origin.viewport(), (int) (maxX * 0.5), (int) (maxY * 0.5)));
		seq2.addAction(finger4.createPointerDown(MouseButton.LEFT.asArg()));
		seq2.addAction(finger4.createPointerMove(Duration.ofSeconds(2), Origin.viewport(), (int) (maxX * 0.25),
				(int) (maxY * 0.25)));
		seq2.addAction(finger4.createPointerUp(MouseButton.LEFT.asArg()));
		PointerInput finger3 = new PointerInput(Kind.TOUCH, "finger 2");
		Sequence seq3 = new Sequence(finger3, 1);
		seq3.addAction(
				finger3.createPointerMove(Duration.ZERO, Origin.viewport(), (int) (maxX * 0.5), (int) (maxY * 0.5)));
		seq3.addAction(finger3.createPointerDown(MouseButton.LEFT.asArg()));
		seq3.addAction(finger3.createPointerMove(Duration.ofSeconds(2), Origin.viewport(), (int) (maxX * 0.75),
				(int) (maxY * 0.75)));
		seq3.addAction(finger3.createPointerUp(MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(seq2,seq3));
		Thread.sleep(2000);
		driver.quit();
	}
}
