package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;

import com.qa.pages.PegaLandingPage;

import io.cucumber.java.en.Then;

public class PegaLandingPageSteps extends TestBase{

	PegaLandingPage pegaLandingPage = new PegaLandingPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user clicks on {string}")
	public void user_clicks_on(String MenuOption) {
	    // Write code here that turns the phrase above into concrete actions
	    pegaLandingPage.userclickson(MenuOption);
	}
	
	@Then("user log off the pega session")
	public void user_log_off_the_pega_session() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		pegaLandingPage.userlogsoffpega();  
	}
}
