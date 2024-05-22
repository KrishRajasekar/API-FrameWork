package com.runner;

import org.junit.AfterClass;

import org.junit.runner.RunWith;
import com.Api.Reports.Reports;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "json:target\\report.json", tags = "@Login or @StateList or @CityList or @Address or @search or @Changeprofilepic", dryRun = false, glue = "com.StepDefinition", features = "C:\\Users\\HARII\\eclipse-workspace1\\APIAutomation\\src\\test\\resources\\Sample")
public class TestRunner_Login {

	@AfterClass
	public static void afterexecution() {

		Reports report = new Reports();
		report.GenerateJvmReport(System.getProperty("user.dir") + "\\target\\report.json");

	}
}
