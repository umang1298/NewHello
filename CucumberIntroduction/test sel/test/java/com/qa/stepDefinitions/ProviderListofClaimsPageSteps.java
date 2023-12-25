package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderListofClaimsPage;

import io.cucumber.java.en.Then;

public class ProviderListofClaimsPageSteps extends TestBase{
	
	ProviderListofClaimsPage providerListofClaimsPage = new ProviderListofClaimsPage(driver);
	Properties prop = initialize_properties();


	@Then("user validates the message for more than {int} results")
	public void user_validates_the_message_for_more_than_results(int int1) {
	    // Write code here that turns the phrase above into concrete actions
	    providerListofClaimsPage.uservalidateserrormessage(int1);
	}
	
	@Then("user clicks on {string} with {string} status")
	public void user_clicks_on_Claim_Number_with_status_and_validates_whether_it_takes_user_to_Claim_Details_Page(String ClaimNumber, String ClaimStatus) {
	    // Write code here that turns the phrase above into concrete actions
	    providerListofClaimsPage.userclicksonclaimwithcompletedstatus(ClaimNumber, ClaimStatus);
	}

	@Then("user takes {string} from search results and sets value for {string} and {string}")
	public void user_takes_from_search_results_and_sets_value_for_and(String ClaimNumber, String PartialClaimNumber, String FullClaimNumber) {
	    // Write code here that turns the phrase above into concrete actions
		providerListofClaimsPage.usertakesclaimnumberandsetsvalueforpartialandfullcalimnumber(ClaimNumber,PartialClaimNumber,FullClaimNumber);
	}
	
	@Then("user gets the Patient Account Number from search results and set the value to {string}")
	public void user_gets_the_Patient_Account_Number_from_search_results_and_set_the_value_to(String PatientNumber) {
	    // Write code here that turns the phrase above into concrete actions
		providerListofClaimsPage.usergetsthepatientaccountnumberandsetsthevalue(PatientNumber);
	}
	
	@Then("user takes {string} from list of Claims")
	public void user_takes_from_list_of_Claims(String MemberId) {
	    // Write code here that turns the phrase above into concrete actions
	    providerListofClaimsPage.usergetsmemberIDfromListofClaims(MemberId);
	}
}
