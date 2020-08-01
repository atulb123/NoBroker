package com.nobroker.hooks;

import org.openqa.selenium.OutputType;

import com.nobroker.base.BaseClass;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseClass {

	@Before("@Hooks")
	public void browserInitialization(Scenario scenario) throws Throwable {
		try {
			launchApp(System.getProperty("deviceType"));
		} catch (Exception e) {
			launchApp("android_device");
		}
	}

	@After("@Hooks")
	public void closeBrowser(Scenario scenario) throws Exception {
		if (scenario.isFailed())
			scenario.embed(driver.getScreenshotAs(OutputType.BYTES), "image/png");
		driver.quit();
		appiumservice.stop();
	}
}
