package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderUpdateUserPage;
import com.qa.pages.ProviderViewAllUsersPage;

import io.cucumber.java.en.Then;

public class ProviderUpdateUserPageSteps extends TestBase{
	
	ProviderUpdateUserPage providerUpdateUserPage = new ProviderUpdateUserPage(driver);
	Properties prop = initialize_properties();

	@Then("user updates the {string} and clicks on the Update button")
	public void user_updates_the_and_clicks_on_the_Update_button(String Info) {
	    // Write code here that turns the phrase above into concrete actions
		providerUpdateUserPage.userupdatesinfo(Info);
	}
	
	@Then("user stores the list of {string} for the user")
	public void user_stores_the_list_of_for_the_user(String Availableapplications) {
	    // Write code here that turns the phrase above into concrete actions
		providerUpdateUserPage.userstoresthelistofavailableapplications(Availableapplications);
	}

	@Then("user validates the {string} remains unchanged for the user")
	public void user_validates_the_remains_unchanged_for_the_user(String Availableapplications) {
	    // Write code here that turns the phrase above into concrete actions
		providerUpdateUserPage.uservalidatestheavailableapplicationsremainsunchanged(Availableapplications) ;
	}
	
}
