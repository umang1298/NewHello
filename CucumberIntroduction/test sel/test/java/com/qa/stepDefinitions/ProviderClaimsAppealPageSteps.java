package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderClaimsAppealPage;

import io.cucumber.java.en.Then;


public class ProviderClaimsAppealPageSteps extends TestBase{

	
	ProviderClaimsAppealPage providerClaimsAppealPage = new ProviderClaimsAppealPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user selects the option for Appeal and clicks Next Button if there is line available {string}")
	public void user_selects_the_option_for_Appeal_and_clicks_Next_Button_if_there_is_line_available(String forappeal){
	    // Write code here that turns the phrase above into concrete actions
	    providerClaimsAppealPage.userselectsoptionforAppealandClicksNext(forappeal);
	}
	
	@Then("user enters Appeal Notes and clicks Next Button if there is line available {string}")
	public void user_enters_Appeal_Notes_and_clicks_Next_Button_if_there_is_line_available(String forappeal) {
	    // Write code here that turns the phrase above into concrete actions
		providerClaimsAppealPage.userenterappealnotesandclicksNext(forappeal);
	}

	
	@Then("user Submits the Appeal if there is line available {string}")
	public void user_Submits_the_Appeal_if_there_is_line_available(String forappeal) {
	    // Write code here that turns the phrase above into concrete actions
		providerClaimsAppealPage.usersubmitstheappeal(forappeal);
	}
	
	@Then("user verifies whether the case ID is generated and clicks No Button if there is line available {string}")
	public void user_verifies_whether_the_case_ID_is_generated_and_clicks_No_Button_if_there_is_line_available(String forappeal) {
	    // Write code here that turns the phrase above into concrete actions
		providerClaimsAppealPage.userverifiescaseidandclicksNo(forappeal);
	}

}
