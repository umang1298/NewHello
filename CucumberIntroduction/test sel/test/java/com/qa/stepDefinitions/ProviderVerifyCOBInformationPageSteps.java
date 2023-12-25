package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderAddCOBInformationPage;
import com.qa.pages.ProviderVerifyCOBInformationPage;

import io.cucumber.java.en.Then;

public class ProviderVerifyCOBInformationPageSteps extends TestBase {
	
	ProviderVerifyCOBInformationPage providerVerifyCOBInformationPage = new ProviderVerifyCOBInformationPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user verifies the COB informations are displayed correctly if {string}")
	public void user_verifies_the_COB_informations_are_displayed_correctly_if(String COBAvailable) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		 providerVerifyCOBInformationPage.UserVerifyCOBInformationandValidatestheInformation(COBAvailable);
	}
}
