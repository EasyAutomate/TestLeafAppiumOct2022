package learning;

import org.testng.annotations.Test;
import wrappers.GenericWrappers;

public class LearnMobileWebAppIniOS extends GenericWrappers{

	@Test
	public void runCode() {
		launchSafariBrowser("iPhone 14", "http://www.google.com","00008030-000A708E01F0C02E");
		enterValue(getWebElement(Locators.NAME.asString(), "q"), "Lokesh");
		switchNativeview();
		click(getWebElement(Locators.NAME.asString(), "Search"));
		switchWebview();
		sleep(3000);
		closeApp();

		
	}
}
