package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderCOBPage;

import io.cucumber.java.en.Then;

public class ProviderCOBPageSteps extends TestBase{

	ProviderCOBPage providerCOBPage = new ProviderCOBPage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("user searches COB informations for {string}")
	public void user_searches_COB_informations_for(String MemberID) {
	    // Write code here that turns the phrase above into concrete actions
		providerCOBPage.usersearchesCOBforthememberID(MemberID);
	}

}
