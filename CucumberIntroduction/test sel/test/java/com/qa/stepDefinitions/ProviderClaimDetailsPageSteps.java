package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderClaimDetailsPage;
import com.qa.pages.ProviderListofClaimsPage;

import io.cucumber.java.en.Then;

public class ProviderClaimDetailsPageSteps extends TestBase{

	ProviderClaimDetailsPage providerClaimDetailsPage = new ProviderClaimDetailsPage(driver);
	Properties prop = initialize_properties();

	
	@Then("validates whether it takes user to Claim Details Page of {string}")
	public void validates_whether_it_takes_user_to_Claim_Details_Page_of(String ClaimNumber) {
	    // Write code here that turns the phrase above into concrete actions
	    providerClaimDetailsPage.uservalidateswhetherclaimidtakesusertoClaimDetailsPage(ClaimNumber);
	}

	@Then("user Clicks on {string} Link based on {string}")
	public void user_Clicks_on_Link(String string, String ClaimStatus) {
	    // Write code here that turns the phrase above into concrete actions
	    providerClaimDetailsPage.userclicksonViewRemittanceAdviceLink(ClaimStatus);
	}

	@Then("user checks the Line Item with Request Appeal status {string} and Clicks on Appeal Button if there is line available {string}")
	public void user_checks_the_Line_Item_with_Request_Appeal_status_and_Clicks_on_Appeal_Button_if_there_is_line_available(String RequestAppealStatus, String forappeal) {
	    // Write code here that turns the phrase above into concrete actions
	    providerClaimDetailsPage.userchecksrequestappealstatusandclicksAppeal(RequestAppealStatus, forappeal);
	}

	
}
