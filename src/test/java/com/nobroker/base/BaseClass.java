package com.nobroker.base;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nobroker.model.DeviceCapabilityModel;
import com.nobroker.utils.GetMobileCapability;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {
	public static AndroidDriver driver;
	public static WebDriverWait wait;
	public static TouchAction action;
	public static DesiredCapabilities dc;
	public static AppiumDriverLocalService appiumservice;
	public static DeviceCapabilityModel mobileType;

	public void launchApp(String deviceType) throws Exception {
		appiumservice = AppiumDriverLocalService.buildDefaultService();
		appiumservice.start();
		dc = new DesiredCapabilities();
		mobileType = GetMobileCapability.getCapability(deviceType);
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, mobileType.getPlatformName());
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, mobileType.getDeviceName());
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, mobileType.getPlatformVersion());
		dc.setCapability(MobileCapabilityType.ORIENTATION, mobileType.getOrientation());
		dc.setCapability("appPackage", mobileType.getAppPackage());
		dc.setCapability("appActivity", mobileType.getAppActivity());
		dc.setCapability("noReset", mobileType.getNoReset());
		driver = new AndroidDriver(new URL(mobileType.getAppiumServerUrl()), dc);
		action = new TouchAction(driver);
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
