package com.nobroker.acceptancetest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/feature" }, glue = { "com.nobroker" }, tags = { "@TC001" }, plugin = {
		"json:target/cucumber-reports/CucumberTestReport.json" })

@Test
public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
