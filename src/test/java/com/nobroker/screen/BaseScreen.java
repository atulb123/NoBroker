package com.nobroker.screen;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.nobroker.base.BaseClass;

import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class BaseScreen extends BaseClass {
	public BaseScreen tapOnWebElement(WebElement element) {
		action.tap(TapOptions.tapOptions()
				.withElement(ElementOption.element(wait.until(ExpectedConditions.visibilityOf(element))))).perform();
		return this;
	}

	public BaseScreen clickElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element)).click();
		return this;
	}

	public BaseScreen clickElement(String text) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(@text,'" + text + "')]"))))
				.click();
		return this;
	}

	public BaseScreen enterText(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
		return this;
	}

	public BaseScreen scrollToViewAndClick(String text) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView(text(\"" + text + "\"));")
				.click();
		return this;
	}

	public BaseScreen scrollDown() {
		Dimension size = driver.manage().window().getSize();
		int width = size.width / 2;
		int startPoint = (int) (size.getHeight() * 0.80);
		int endPoint = (int) (size.getHeight() * 0.40);
		action.press(PointOption.point(width, startPoint)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point(width, endPoint)).release().perform();
		return this;
	}
}
