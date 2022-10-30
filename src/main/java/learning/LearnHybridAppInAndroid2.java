package learning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnHybridAppInAndroid2 extends GenericWrappers{

	@Test
	public void runCode() {
		launchAndroidApp("my device", "", "", "", "/apks/TheApp-v1.10.0.apk");
		printContext();
		click(getWebElement(Locators.XPATH.asString(), "//android.widget.TextView[@text='Webview Demo']"));
		sleep(3000);
		printContext();
		enterValue(getWebElement(Locators.ACCESSIBILITY_ID.asString(), "urlInput"), "https://appiumpro.com");
		click(getWebElement(Locators.XPATH.asString(), "//android.widget.TextView[@text='Go']"));
		printContext();
		switchContext("WEBVIEW_io.cloudgrey.the_app");
		click(getWebElement(Locators.XPATH.asString(), "(//*[@id='toggleMenu']/img)[2]"));
		sleep(5000);
		closeApp();

		
	}
}
