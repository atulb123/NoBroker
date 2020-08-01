package com.nobroker.missions;

import com.nobroker.base.BaseClass;
import com.nobroker.model.UserDataModel;
import com.nobroker.screen.WelcomeScreen;

public class LoginMission extends BaseClass {
	private WelcomeScreen welcomeScreen = new WelcomeScreen();

	public void performLogin(UserDataModel userData) {
		welcomeScreen.tapOnWebElement(welcomeScreen.continueButton).tapOnWebElement(welcomeScreen.allowButton)
				.tapOnWebElement(welcomeScreen.allowButton).tapOnWebElement(welcomeScreen.allowAllTheTimeButton)
				.tapOnWebElement(welcomeScreen.naviageIcon).clickElement(welcomeScreen.loginorSignUpLink)
				.enterText(welcomeScreen.mobileNoTextBox, userData.getUserName());
		driver.navigate().back();
		welcomeScreen.clickElement(welcomeScreen.loginButton).clickElement(welcomeScreen.iHavePwdRadioButton)
				.enterText(welcomeScreen.passwordTextBox, userData.getPassword())
				.clickElement(welcomeScreen.loginButton);
	}
}
