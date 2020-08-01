package com.nobroker.screen;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WelcomeScreen extends BaseScreen {

	@FindBy(xpath = "//*[@text='Continue']")
	public WebElement continueButton;

	@FindBy(xpath = "//*[@text='ALLOW']")
	public WebElement allowButton;

	@FindBy(xpath = "//*[@text='ALLOW ALL THE TIME']")
	public WebElement allowAllTheTimeButton;

	@FindBy(id = "Navigate up")
	public WebElement naviageIcon;
	
	@FindBy(xpath = "//*[contains(@text,'LOGIN')]")
	public WebElement loginorSignUpLink;
	
	@FindBy(id = "com.nobroker.app:id/et_signup_phone")
	public WebElement mobileNoTextBox;
	
	@FindBy(id = "com.nobroker.app:id/btn_signup")
	public WebElement loginButton;

	@FindBy(id = "com.nobroker.app:id/rb_signup_pwd")
	public WebElement iHavePwdRadioButton;
	
	@FindBy(id = "com.nobroker.app:id/et_signup_pwd")
	public WebElement passwordTextBox;
	
	@FindBy(id = "com.nobroker.app:id/buyLayout")
	public WebElement buyLink;
	
	@FindBy(id = "com.nobroker.app:id/searchEditHome")
	public WebElement searchTextBox;

	public WelcomeScreen() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}


}
