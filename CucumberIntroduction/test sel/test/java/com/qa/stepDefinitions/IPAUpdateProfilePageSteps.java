package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.IPAUpdateProfilePage;

import io.cucumber.java.en.Then;

public class IPAUpdateProfilePageSteps extends TestBase {

	IPAUpdateProfilePage iPAUpdateProfilePage = new IPAUpdateProfilePage(driver);
	Properties prop = initialize_properties();
	
	@Then("user validates the Change Password Functionality in the IPA Portal")
	public void user_validates_the_Change_Password_Functionality_in_the_IPA_Portal() {
	    // Write code here that turns the phrase above into concrete actions
		iPAUpdateProfilePage.UserValidatesChangePasswordFunctionalityInTheIPAportal();
	}
	
	@Then("user changes password to the old password")
	public void user_changes_password_to_the_old_password() {
	    // Write code here that turns the phrase above into concrete actions
		iPAUpdateProfilePage.UserChangesthePasswordtotheOldPassword();
	}
}
