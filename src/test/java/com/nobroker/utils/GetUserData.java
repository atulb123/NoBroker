package com.nobroker.utils;

import java.io.FileReader;
import java.util.Arrays;

import com.google.gson.Gson;
import com.nobroker.model.UserDataModel;

public class GetUserData {
	public static UserDataModel getTestData(String userType) {
		try {
			return Arrays
					.asList(new Gson().fromJson(
							new FileReader(
									System.getProperty("user.dir") + "/src/test/resources/testdata/UserData.json"),
							UserDataModel[].class))
					.stream().filter(userData -> userData.getUserType().equals(userType)).findFirst().get();
		} catch (Exception e) {
			System.out.println("Test Data File not found");
			e.printStackTrace();
			return null;
		}

	}
}
