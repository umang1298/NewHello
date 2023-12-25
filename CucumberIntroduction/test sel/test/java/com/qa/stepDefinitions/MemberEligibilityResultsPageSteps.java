package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.MemberEligibilityPage;
import com.qa.pages.MemberEligibilityResultsPage;

import io.cucumber.java.en.Then;

public class MemberEligibilityResultsPageSteps extends TestBase{

	MemberEligibilityResultsPage memberEligibilityResultsPage = new MemberEligibilityResultsPage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("user does the validation for {string} based on the response XML {string}")
	public void user_does_the_validation_for_based_on_the_response_XML(String MemberId, String ResponseXMLPath)  throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    
		memberEligibilityResultsPage.memberEligibilityvalidations(MemberId, ResponseXMLPath);
	}
	
	
	@Then("user does the Member Eligibilty Contract History validation for {string} based on the response XML {string}")
	public void user_does_the_Member_Eligibilty_Contract_History_validation_for_based_on_the_response_XML(String MemberId, String ResponseXMLPath) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    
		memberEligibilityResultsPage.ContractHistorymemberEligibilityvalidations(MemberId, ResponseXMLPath);
	}


@Then("user verifies the eligibility results are displayed for {string} and {string} and {string}")
public void user_verifies_the_eligibility_results_are_displayed_for_and_and(String MemberID1, String MemberID2, String MemberID3) throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	memberEligibilityResultsPage.verifyresultsloadedforallthemembers(MemberID1, MemberID2, MemberID3);
}

@Then("user validates results are successfully displayed for all the dependants based on the response XML {string}")
public void user_validates_results_are_successfully_displayed_for_all_the_dependants_based_on_the_response_XML(String ResponseXMLPath) throws IOException, InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	memberEligibilityResultsPage.memberEligValidationsforSubscriber(ResponseXMLPath);
}

@Then("user clicks on Member Name Link")
public void user_clicks_on_Member_Name_Link() {
    // Write code here that turns the phrase above into concrete actions
	memberEligibilityResultsPage.ClickonMemberNameLink();
}
}
