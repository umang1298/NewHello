package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderManageUsersPage;
import com.qa.pages.ProviderViewAllUsersPage;

import io.cucumber.java.en.Then;

public class ProviderViewAllUsersPageSteps extends TestBase{

	ProviderViewAllUsersPage providerViewAllUsersPage = new ProviderViewAllUsersPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user selects the First user id and clicks on Update user link")
	public void user_selects_the_First_user_id_and_clicks_on_Update_user_link() {
	    // Write code here that turns the phrase above into concrete actions
		providerViewAllUsersPage.UserselectsfirstuserIDandClickUpdateUser();
	}
	
	@Then("user validates {string} is updated successfully")
	public void user_validates_is_updated_successfully(String Info) {
	    // Write code here that turns the phrase above into concrete actions
		providerViewAllUsersPage.Uservalidatesinfoisupdatedsuccessfully(Info);
	}
}
