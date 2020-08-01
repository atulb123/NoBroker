@Hooks
Feature: Verify Abuse Report

  @TC001
  Scenario: Verify Abuse Report related functionality for the Buy Property
    Given I login to nobroker app as "Test User 1" and all contacts and location
    And I click on Buy tab
    And I select "Bangalore" city
    And Search the below localities
      | Marathahalli | HSR Layout |
    And I select "2 BHK" and "3 BHK" and search
    And I select "4" th property from the list
    And I select wrong info and report it
    When I change configuarion from "3 BHK" to "4+ BHK" and save it
    Then I should see Thank you message displayed
