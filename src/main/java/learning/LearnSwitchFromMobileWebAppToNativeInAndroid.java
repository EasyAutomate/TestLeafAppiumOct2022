package learning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnSwitchFromMobileWebAppToNativeInAndroid extends GenericWrappers {

	@Test
	public void runCode() {
		launchChromeBrowser("sdgdg", "http://www.google.com");
		enterValue(getWebElement(Locators.NAME.asString(), "q"), "Lokesh");
		pressEnter();
		switchToAnotherApp("com.testleaf.leaforg");
		// startAnAppUsingActivity("com.testleaf.leaforg",
		// "com.testleaf.leaforg.MainActivity");
		sleep(5000);
		printContext();
		switchContext("NATIVE_APP");
		printContext();
//		enterValue(getWebElement(Locators.XPATH.asString(), "//input[@placeholder='Email']"), "rajkumar@testleaf.com");
//		enterValue(getWebElement(Locators.XPATH.asString(), "//input[@placeholder='Password']"), "Leaf@123");
//		click(getWebElement(Locators.XPATH.asString(), "//button[@type='submit']"));
		enterValue(getWebElement(Locators.XPATH.asString(), "//android.widget.EditText"), "rajkumar@testleaf.com");
		enterValue(getWebElement(Locators.XPATH.asString(), "(//android.widget.EditText)[2]"), "Leaf@123");
		click(getWebElement(Locators.CLASS_NAME.asString(), "android.widget.Button"));
		sleep(3000);
		closeApp();

	}
}
