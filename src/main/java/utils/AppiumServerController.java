package utils;

import static io.appium.java_client.service.local.AppiumDriverLocalService.buildService;

import java.io.File;
import java.time.Duration;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public final class AppiumServerController {

	private final static AppiumDriverLocalService service;

	static {

		service = buildService(new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingPort(Integer.parseInt("4723"))
				.withArgument(GeneralServerFlag.LOG_LEVEL, "error").withArgument(GeneralServerFlag.RELAXED_SECURITY)
				.withArgument(GeneralServerFlag.LOG_TIMESTAMP).withTimeout(Duration.ofSeconds(300))
				.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/").withLogFile(new File("./appiumServerlog.txt")));
	}

	@BeforeSuite
	public static void startAppiumServer() {
		try {
			service.start();
			if (!service.isRunning()) {
				throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public static void stopAppiumServer() {
		try {
			if (service.isRunning()) {
				service.stop();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void runCode() {
		System.out.println("test is running");
	}
	
	public static void main(String[] args) {
		AppiumServerController.startAppiumServer();
		AppiumServerController.startAppiumServer();
	}

}
