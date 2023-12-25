package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderBenefitAdminManualPage;
import com.qa.pages.ProviderReferralResultsPage;

import io.cucumber.java.en.Then;

public class ProviderBenefitAdminManualPageSteps extends TestBase {

	ProviderBenefitAdminManualPage providerBenefitAdminManualPage = new ProviderBenefitAdminManualPage(driver);
	Properties prop = initialize_properties();
	
	    @Then("user searches Policy by text and validates search results are displayed")
	    public void user_searches_Policy_by_text_and_validates_search_results_are_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		providerBenefitAdminManualPage.UserSearchesPolicyByTextAndValidatesSearchResultsAreDisplayed();
		
	    }
	    
		@Then("user searches Policy by Policy Index and Validates the search results are displayed")
		public void user_searches_Policy_by_Policy_Index_and_Validates_the_search_results_are_displayed() {
		// Write code here that turns the phrase above into concrete actions
		providerBenefitAdminManualPage.UserSearchesPolicyByPolicyIndexAndValidatesTheSearchResultsAreDisplayed();	
	    }
		
		@Then("user searches Policy by Categorical Index and validates the Searched results are displayed")
		public void user_searches_Policy_by_Categorical_Index_and_validates_the_Searched_results_are_displayed() {
		// Write code here that turns the phrase above into concrete actions
	    providerBenefitAdminManualPage.UserSearchesPolicyByCategoricalIndexAndValidatesTheSearchedResultsAreDisplayed();
		}
		
		@Then("user searches Policy by Recent Changes and validate searched Results are displayed")
		public void user_searches_Policy_by_Recent_Changes_and_validate_searched_Results_are_displayed() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		providerBenefitAdminManualPage.UserSearchesPolicyByRecentChangesAndValidateSearchedResultsAreDisplayed();
		}
	}
