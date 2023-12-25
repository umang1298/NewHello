package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.MyClaimsPage;
import com.qa.pages.ReferralAndAuthorizations;

import io.cucumber.java.en.Then;

public class ReferralAndAuthorizationsSteps extends TestBase{

	ReferralAndAuthorizations referralAndAuthorizations = new ReferralAndAuthorizations(driver);
	Properties prop = initialize_properties();
	
	@Then("verifies list of referral number displayed for the {string} from the {string}")
	public void verifies_list_of_referral_number_displayed_for_the_from_the(String membername, String date) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		referralAndAuthorizations.clickonreferralsearchforgivenperiod(membername,date);
	}


}
