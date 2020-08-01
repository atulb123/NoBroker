package com.nobroker.utils;

import java.io.FileReader;
import java.util.Arrays;

import com.google.gson.Gson;
import com.nobroker.model.DeviceCapabilityModel;

public class GetMobileCapability {
	public static DeviceCapabilityModel getCapability(String userType) {
		try {
			return Arrays
					.asList(new Gson().fromJson(
							new FileReader(System.getProperty("user.dir")
									+ "/src/test/resources/config/DeviceCapabilities.json"),
							DeviceCapabilityModel[].class))
					.stream().filter(mobileCapability -> mobileCapability.getDeviceType().equals(userType)).findFirst()
					.get();
		} catch (Exception e) {
			return null;
		}
	}
}
