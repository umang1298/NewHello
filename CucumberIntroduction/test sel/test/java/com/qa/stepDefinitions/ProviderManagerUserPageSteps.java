package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderManageUsersPage;

import io.cucumber.java.en.Then;

public class ProviderManagerUserPageSteps extends TestBase {
	
	ProviderManageUsersPage providerManageUsersPage = new ProviderManageUsersPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user clicks on the View all Users link")
	public void user_clicks_on_the_View_all_Users_link() {
	    // Write code here that turns the phrase above into concrete actions
		providerManageUsersPage.UserClicksontheViewAllUsersLink();
	}
	
	@Then("user searches by NPI")
	public void user_searches_by_NPI() {
	    // Write code here that turns the phrase above into concrete actions
		providerManageUsersPage.usersearchesbyNPI();
	}
}
