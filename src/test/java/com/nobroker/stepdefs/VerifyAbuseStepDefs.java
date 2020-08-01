package com.nobroker.stepdefs;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.nobroker.base.BaseClass;
import com.nobroker.missions.LoginMission;
import com.nobroker.model.UserDataModel;
import com.nobroker.screen.SearchScreen;
import com.nobroker.screen.WelcomeScreen;
import com.nobroker.utils.GetUserData;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VerifyAbuseStepDefs extends BaseClass {
	private LoginMission loginMission = new LoginMission();
	private WelcomeScreen welcomeScreen = new WelcomeScreen();
	private SearchScreen searchScreen = new SearchScreen();

	@Given("I login to nobroker app as {string} and all contacts and location")
	public void i_login_to_nobroker_app_as_and_all_contacts_and_location(String userType) {
		UserDataModel userData = GetUserData.getTestData(userType);
		loginMission.performLogin(userData);
	}

	@Given("I click on Buy tab")
	public void i_click_on_Buy_tab() {
		welcomeScreen.clickElement(welcomeScreen.buyLink).tapOnWebElement(welcomeScreen.searchTextBox);
	}

	@Given("I select {string} city")
	public void i_select_city(String city) {
		searchScreen.selectCity(city);
	}

	@Given("Search the below localities")
	public void search_the_below_localities(DataTable dataTable) {
		dataTable.asList().stream().forEach(e -> searchScreen.selectArea(e));
	}

	@Given("I select {string} and {string} and search")
	public void i_select_and_and_search(String value1, String value2) {
		searchScreen.clickElement(searchScreen.includeNearByProperty).clickElement(value1).clickElement(value2)
				.clickElement(searchScreen.searchButton);
	}

	@Given("I select {string} th property from the list")
	public void i_select_th_property_from_the_list(String searchResultNo) {
		searchScreen.selectFromSearchResult(Integer.parseInt(searchResultNo));
	}

	@Given("I select wrong info and report it")
	public void i_select_wrong_info_and_report_it() throws Exception {
		searchScreen.clickWrongInfoLink().selectAllReportOption().clickElement(searchScreen.reportButton);
	}

	@When("I change configuarion from {string} to {string} and save it")
	public void i_change_configuarion_from_to_and_save_it(String value1, String value2) {
		searchScreen.clickElement(value1).scrollToViewAndClick(value2).scrollToViewAndClick("Add a note").enterText(searchScreen.addNoteTextArea, "Test Comment")
				.clickElement(searchScreen.saveButton);
	}

	@Then("I should see Thank you message displayed")
	public void i_should_see_Thank_you_message_displayed() {
		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOf(searchScreen.feedbackSuccessMessage)).isDisplayed(),
				"Feedback Message not displayed");
	}

}
