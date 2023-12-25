package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.RemittanceAdvicePage;

import io.cucumber.java.en.Then;


public class RemittanceAdvicePageSteps extends TestBase{
	
	RemittanceAdvicePage remittanceAdvicePage = new RemittanceAdvicePage(driver);
	Properties prop = initialize_properties();
	
	@Then("user validates print option and Remittance Advice for {string} is loaded Correctly based on {string}")
	public void user_validates_Remittance_Advice_for_is_loaded_Correctly(String ClaimNumber, String ClaimStatus) {
	    // Write code here that turns the phrase above into concrete actions
	    remittanceAdvicePage.uservalidatesclaimNumberandPrintoptiononRAPage(ClaimNumber, ClaimStatus);
	}




}
