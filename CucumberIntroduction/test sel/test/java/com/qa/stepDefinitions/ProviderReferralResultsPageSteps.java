package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderReferralResultsPage;
import com.qa.pages.ProviderReferralSearchPage;

import io.cucumber.java.en.Then;

public class ProviderReferralResultsPageSteps extends TestBase {
	
	ProviderReferralResultsPage providerReferralResultsPage = new ProviderReferralResultsPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user validates Referral search results are displayed")
	public void user_validates_search_results_are_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		providerReferralResultsPage.userverifiesthereferralresults();
	}
	
	@Then("user clicks on {string} and stores {string}")
	public void user_clicks_on_and_stores(String ReferralNumber, String PatientName) {
	    // Write code here that turns the phrase above into concrete actions
		providerReferralResultsPage.userclickonReferralNumberandstoresPatientName(ReferralNumber,PatientName);
	}
}

