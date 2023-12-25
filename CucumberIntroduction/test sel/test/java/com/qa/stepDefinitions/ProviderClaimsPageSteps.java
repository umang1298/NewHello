package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;

import com.qa.pages.ProviderClaimsPage;

import io.cucumber.java.en.Then;

public class ProviderClaimsPageSteps extends TestBase{
	
	ProviderClaimsPage providerClaimsPage = new ProviderClaimsPage(driver);
	Properties prop = initialize_properties();

	
	@Then("user search the Claims for last one year")
	public void user_search_the_Claims_for_last_one_year() {
	    // Write code here that turns the phrase above into concrete actions
	    providerClaimsPage.usersearchesclaimsforlastoneyear();
	}


@Then("user verifies provider information is displayed")
public void user_verifies_provider_information_is_displayed() {
    // Write code here that turns the phrase above into concrete actions
	providerClaimsPage.userverfiesproviderinformation();
}

@Then("user searches {string} with {string} and validates results are displayed Successfully")
public void user_searches_with_and_validates_results_are_displayed_Successfully(String searchwith, String data) throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	providerClaimsPage.usersearcheswithoptionandentersdata(searchwith,data);
}

@Then("user searches claims with {string} and validates results are displayed Successfully")
public void user_searches_claims_with_and_validates_results_are_displayed_Successfully(String MemberID) {
    // Write code here that turns the phrase above into concrete actions
	providerClaimsPage.userentersthememberidandclickonsearch(MemberID);
    
}

@Then("user searches with {string} and validates results are displayed Successfully")
public void user_searches_with_and_validates_results_are_displayed_Successfully(String PatientNumber){
    // Write code here that turns the phrase above into concrete actions
    providerClaimsPage.userentersthepatientnumberandclicksonsearch(PatientNumber);
}

}
