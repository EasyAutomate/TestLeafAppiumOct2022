package learning;

import org.testng.annotations.Test;
import wrappers.GenericWrappers;

public class LearnHybridAppIniOS extends GenericWrappers{

	@Test
	public void runCode() {
		launchIosApp("iPhone 14","","","","/apks/uicatalog.zip");
		stopRunningApp("com.example.apple-samplecode.UICatalog");
		switchToAnotherApp("com.example.apple-samplecode.UICatalog");
		swipeUpinAppUntilElementIsVisible(Locators.ACCESSIBILITY_ID.asString(), "Web View");
		click(getWebElement(Locators.ACCESSIBILITY_ID.asString(), "Web View"));
		printContext();
		switchWebview();
		click(getWebElement(Locators.XPATH.asString(), "//li/a[@role='button' and @id='ac-gn-menuanchor-open']"));
		click(getWebElement(Locators.XPATH.asString(), "(//a[@data-analytics-title='tv and home'])[1]"));
		sleep(3000);
		switchNativeview();
		closeApp();

		
	}
}
