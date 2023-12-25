package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.MemberEligibilityDetailsPage;

import io.cucumber.java.en.Then;

public class MemberEligibilityDetailsPageSteps extends TestBase{

	MemberEligibilityDetailsPage memberEligibilityDetailsPage = new MemberEligibilityDetailsPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user does the validation on Member Eligibility Details page based on the response XML {string}")
	public void user_does_the_validation_on_Member_Eligibility_Details_page_based_on_the_response_XML(String ResponseXMLPath) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		memberEligibilityDetailsPage.validatememberEligdetails(ResponseXMLPath);
	}
	
	@Then("user does the Contract History validation on Member Eligibility Details page based on the response XML {string}")
	public void user_does_the_Contract_History_validation_on_Member_Eligibility_Details_page_based_on_the_response_XML(String ResponseXMLPath) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		memberEligibilityDetailsPage.ContractHistoryvalidatememberEligdetails(ResponseXMLPath);
	}

}
