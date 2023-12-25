package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderReferralDetailsPage;
import com.qa.pages.ProviderReferralResultsPage;

import io.cucumber.java.en.Then;

public class ProviderReferralDetailsSteps extends TestBase {
	
	ProviderReferralDetailsPage providerReferralDetailsPage = new ProviderReferralDetailsPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user validates the Referral details is displayed correctly for {string}")
	public void user_validates_the_Referral_details_is_displayed_correctly_for(String string) {
	    // Write code here that turns the phrase above into concrete actions
		providerReferralDetailsPage.uservalidatesthereferraldetailsdisplayed(string);
	}
}
