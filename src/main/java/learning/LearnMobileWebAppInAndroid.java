package learning;

import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class LearnMobileWebAppInAndroid extends GenericWrappers{

	@Test
	public void runCode() {
		launchChromeBrowser("sdgdg", "http://www.google.com");
		enterValue(getWebElement(Locators.NAME.asString(), "q"), "Lokesh");
		pressEnter();
		sleep(3000);
		closeApp();

		
	}
}
