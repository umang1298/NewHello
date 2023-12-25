package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;

import com.qa.pages.EmployerPortalMyToolsPage;

import io.cucumber.java.en.Then;

public class EmployerPortalMyToolsSteps extends TestBase {

	EmployerPortalMyToolsPage employerPortalMyToolsPage = new EmployerPortalMyToolsPage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("User Clicks on {string} in Employer Portal My Tools Page")
	public void user_Clicks_on_in_Employer_Portal_My_Tools_Page(String MenuOptionToPass) throws InterruptedException  {
	    // Write code here that turns the phrase above into concrete actions
		employerPortalMyToolsPage.UserClicksononeofthemenusinMyToolsPage(MenuOptionToPass);
	}
	
	@Then("User Clicks on {string} in PA Employer Portal My Tools Page")
	public void user_Clicks_on_in_PA_Employer_Portal_My_Tools_Page(String MenuOptionToPass) throws InterruptedException  {
	    // Write code here that turns the phrase above into concrete actions
		employerPortalMyToolsPage.UserClicksononeofthemenusinPAMyToolsPage(MenuOptionToPass);
	}
}
