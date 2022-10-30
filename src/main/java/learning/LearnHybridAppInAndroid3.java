package learning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnHybridAppInAndroid3 extends GenericWrappers{

	@Test
	public void runCode() {
		launchAndroidApp("my device", "", "", "", "/apks/TheApp-v1.10.0.apk");
		printContext();
		click(getWebElement(Locators.XPATH.asString(), "//android.widget.TextView[@text='Dual Webview Demo']"));
		printContext();
		switchContext("WEBVIEW_io.cloudgrey.the_app");
		System.out.println(driver.getWindowHandles().size());
		switchToFirstWindow();
		System.out.println(getText(getWebElement(Locators.TAG_NAME.asString(), "h2")));
		switchToLastWindow();
		System.out.println(getText(getWebElement(Locators.TAG_NAME.asString(), "h2")));
		sleep(5000);
		closeApp();

		
	}
}
