package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.HAPPortalLandingPage;
import com.qa.pages.ProviderUpdateProfilePage;

import io.cucumber.java.en.Then;

public class ProviderUpdateProfilePageSteps extends TestBase {
	
	ProviderUpdateProfilePage providerUpdateProfilePage = new ProviderUpdateProfilePage(driver);
	Properties prop = initialize_properties();
	
	@Then("user updates the Contact Details {string} and clicks on the save button")
	public void user_updates_the_and_clicks_on_the_Submit_button(String info) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		providerUpdateProfilePage.UserUpdatesTheInformationAndClicksOnSubmitButton(info);
	}
	
	@Then("user validates Contact Details {string} is updated successfully for child user")
	public void user_validates_is_updated_successfully_for_child_user(String info) {
	    // Write code here that turns the phrase above into concrete actions
		providerUpdateProfilePage.UserValidatesInformationUpdatesSuccessfully(info);
	}
	
	@Then("user validates update profile page is displayed for admin user is displayed")
	public void user_validates_update_profile_page_is_displayed_for_admin_user_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		providerUpdateProfilePage.UserValidatesUpdateProfilePageIsDisplayed();
	}
	
	@Then("user selects the None of these radio button and clicks on Submit button")
	public void user_selects_the_None_of_these_radio_button_and_clicks_on_Submit_button() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		providerUpdateProfilePage.UserSelectsNoneofTheseandClicksonSubmitButton();
	}
	
	@Then("user validates the updated informations message is displayed")
	public void user_validates_the_updated_informations_message_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		providerUpdateProfilePage.UserValidatesTheUpdatedInformationMessageIsDisplayed();
	}
}
	
