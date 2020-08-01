package com.nobroker.screen;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SearchScreen extends BaseScreen {

	@FindBy(id = "com.nobroker.app:id/spinnergo")
	public WebElement cityDropDown;

	@FindBy(xpath = "//android.widget.CheckedTextView")
	public List<WebElement> cityDropDownList;

	@FindBy(id = "com.nobroker.app:id/localityAutoCompleteTxt")
	public WebElement searchAreaTextBox;

	@FindBy(id = "com.nobroker.app:id/nearByRadio")
	public WebElement includeNearByProperty;

	@FindBy(xpath = "//*[@resource-id='com.nobroker.app:id/property_emi_per_month'][1]")
	public WebElement searchResult;

	@FindBy(id = "com.nobroker.app:id/searchProperty")
	public WebElement searchButton;

	@FindBy(xpath = "//*[@text='Wrong Info']")
	public WebElement wrongInfoLink;

	@FindBy(xpath = "//android.widget.ScrollView//android.widget.TextView")
	public List<WebElement> whatsWrongOptions;

	@FindBy(id = "com.nobroker.app:id/btn_report")
	public WebElement reportButton;
	
	@FindBy(id = "com.nobroker.app:id/btn_save")
	public WebElement saveButton;
	
	@FindBy(xpath = "//*[contains(@text,'Thank you for the feedback')]")
	public WebElement feedbackSuccessMessage;

	@FindBy(xpath = "//*[@text='Add a note']")
	public WebElement addNoteTextArea;

	public SearchScreen() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public SearchScreen selectCity(String city) {
		clickElement(cityDropDown);
		wait.until(ExpectedConditions.visibilityOfAllElements(cityDropDownList)).stream()
				.filter(e -> e.getText().toLowerCase().contains(city.toLowerCase())).findFirst().get().click();
		return this;
	}

	public SearchScreen selectArea(String area) {
		enterText(searchAreaTextBox, area);
		try {Thread.sleep(3000);} catch (InterruptedException e) {}
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
		try {Thread.sleep(3000);} catch (InterruptedException e) {}
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		return this;
	}

	public SearchScreen selectFromSearchResult(int resultNo) {
		Set<String> resultSize = new LinkedHashSet<String>();
		while (resultSize.size() < resultNo) {
			scrollDown();
			resultSize.add(searchResult.getText());
		}
		clickElement(new ArrayList<String>(resultSize).get(resultSize.size() - 1));
		return this;
	}

	public SearchScreen clickWrongInfoLink() {
		for (int i = 0; i < 20; i++) {
			Dimension size = driver.manage().window().getSize();
			int width = size.width / 2;
			int startPoint = (int) (size.getHeight() * 0.90);
			int endPoint = (int) (size.getHeight() * 0.10);
			action.press(PointOption.point(width, startPoint))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(width, endPoint)).release().perform();
			try {
				if (wrongInfoLink.isDisplayed()) {
					wrongInfoLink.click();
					break;
				}
			} catch (Exception e) {
			}
		}
		return this;
	}

	public SearchScreen selectAllReportOption() {
		wait.until(ExpectedConditions.visibilityOfAllElements(whatsWrongOptions)).stream()
				.forEach(e -> tapOnWebElement(e));
		return this;
	}
}
