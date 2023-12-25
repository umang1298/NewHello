package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.AgentPortalUpdatePasswordPage;

import io.cucumber.java.en.Then;


public class AgentPortalUpdatePasswordSteps extends TestBase {
	
	AgentPortalUpdatePasswordPage agentPortalUpdatePasswordPage = new AgentPortalUpdatePasswordPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user enters the {string} and updates the {string} and {string} and validates update password correctly")
	public void user_enters_the_and_updates_the_and_and_validates_update_password_correctly(String password, String newpassword, String confirmpassword) {
	    // Write code here that turns the phrase above into concrete actions
		agentPortalUpdatePasswordPage.userentersthepasswordandvalidatesupdatepasswordcorrectly(password,newpassword,confirmpassword);
	}

}
