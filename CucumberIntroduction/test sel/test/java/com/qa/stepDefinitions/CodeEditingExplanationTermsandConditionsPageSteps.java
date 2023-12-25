package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.CodeEditingExplanationTermsandConditionsPage;
import com.qa.pages.ProviderClaimsPage;

import io.cucumber.java.en.Then;

public class CodeEditingExplanationTermsandConditionsPageSteps extends TestBase {

	
	CodeEditingExplanationTermsandConditionsPage codeEditingExplanationTermsandConditionsPage = new CodeEditingExplanationTermsandConditionsPage(driver);
	Properties prop = initialize_properties();

	
	@Then("user verifies terms and conditions are opened in new tab")
	public void user_verifies_terms_and_conditions_are_opened_in_new_tab() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		codeEditingExplanationTermsandConditionsPage.userverifiesTermsandConditionsareopenedinnewtab();
	}

	
}
