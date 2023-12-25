package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderUpdatePasswordPage;

import io.cucumber.java.en.Then;

public class ProviderUpdatePasswordSteps extends TestBase {

	ProviderUpdatePasswordPage providerUpdatePasswordPage = new ProviderUpdatePasswordPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user enters the {string} and updates the {string} and {string}")
	public void user_enters_the_and_updates_the_and(String password, String newpassword, String confirmpassword) {
	    // Write code here that turns the phrase above into concrete actions
		providerUpdatePasswordPage.userenterspasswordandupdatespassword(password,newpassword,confirmpassword);
	}
	
}
