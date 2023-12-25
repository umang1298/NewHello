package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployerPortalLandingPage;

import io.cucumber.java.en.Then;


public class EmployerPortalLandingSteps extends TestBase {

	EmployerPortalLandingPage employerPortalLandingPage = new EmployerPortalLandingPage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("User Clicks on {string} in Employer Portal Landing Page")
	public void user_Clicks_on_in_Employer_Portal_Landing_Page(String MenuOptionToPass) {
	    // Write code here that turns the phrase above into concrete actions
		employerPortalLandingPage.userclicksononeofthemenusinEmployerPortalLandingpage(MenuOptionToPass);
	}

	

@Then("user logouts the employer portal")
public void user_logouts_the_employer_portal() {
    // Write code here that turns the phrase above into concrete actions
    employerPortalLandingPage.userlogoutsofemployerportal();
}

	
	
	
	
}
