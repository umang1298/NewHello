package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ReferralAndAuthorizations;
import com.qa.pages.ReferralDetailPage;

import io.cucumber.java.en.Then;

public class ReferralDetailPageSteps extends TestBase{

	ReferralDetailPage referralDetailPage = new ReferralDetailPage(driver);
	Properties prop = initialize_properties();

	@Then("user validates Referrals & Prior Authorizations from {string} and {string}")
	public void user_validates_Referrals_Prior_Authorizations_from_and(String xmlPath, String membername) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		referralDetailPage.validateReferralDetailpage(xmlPath,membername);
	}
	
}
