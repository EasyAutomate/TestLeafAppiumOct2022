package learning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnSwitchBetweenNativeAppInAndroid2 extends GenericWrappers{

	@Test
	public void runCode() {
		launchAndroidApp("my device", "", "", "", "/apks/leaforg.apk");
		enterValue(getWebElement(Locators.XPATH.asString(), "//android.widget.EditText"), "rajkumar@testleaf.com");
		enterValue(getWebElement(Locators.XPATH.asString(), "(//android.widget.EditText)[2]"), "Leaf@123");
		click(getWebElement(Locators.CLASS_NAME.asString(), "android.widget.Button"));
		switchToAnotherApp("com.android.contacts");
		click(getWebElement(Locators.ACCESSIBILITY_ID.asString(), "Create new contact"));
		startAnAppUsingActivity("com.testleaf.leaforg", "com.testleaf.leaforg.MainActivity");
		sleep(3000);
		closeApp();

		
	}
}
