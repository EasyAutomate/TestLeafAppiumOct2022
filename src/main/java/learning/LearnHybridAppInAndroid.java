package learning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnHybridAppInAndroid extends GenericWrappers{

	@Test
	public void runCode() {
		launchAndroidApp("my device", "", "", "", "/apks/leaforg.apk");
		printContext();
		//switchWebview();
		switchContext("WEBVIEW_com.testleaf.leaforg");
		enterValue(getWebElement(Locators.XPATH.asString(), "//input[@placeholder='Email']"), "rajkumar@testleaf.com");
		enterValue(getWebElement(Locators.XPATH.asString(), "//input[@placeholder='Password']"), "Leaf@123");
		click(getWebElement(Locators.XPATH.asString(), "//button[@type='submit']"));
		sleep(3000);
		closeApp();

		
	}
}
