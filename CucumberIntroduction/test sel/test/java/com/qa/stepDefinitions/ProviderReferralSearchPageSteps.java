package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderReferralSearchPage;

import io.cucumber.java.en.Then;

		public class ProviderReferralSearchPageSteps extends TestBase{

			ProviderReferralSearchPage providerReferralSearchPage = new ProviderReferralSearchPage(driver);
			Properties prop = initialize_properties();
			
			@Then("user selects the Status from the dropdown and searches Referrals for one year")
		public void user_selects_the_Status_from_the_dropdown_and_searches_Refferals_for_one_year() {
		    // Write code here that turns the phrase above into concrete actions
				providerReferralSearchPage.userselectstatusandsearchreferrralforlastoneyear();
		}
	}

