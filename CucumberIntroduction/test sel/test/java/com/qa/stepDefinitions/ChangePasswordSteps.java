package com.qa.stepDefinitions;

import com.qa.base.TestBase;
import com.qa.pages.ChangePasswordPage;

import io.cucumber.java.en.Then;

public class ChangePasswordSteps extends TestBase{

	ChangePasswordPage changePasswordPage=new ChangePasswordPage(driver);

@Then("user validates Change Password Functionality")
public void user_validates_Change_Password_Functionality() throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	changePasswordPage.validatesChangePassword();
}


@Then("user validates Change Password Functionality for agent")
public void user_validates_Change_Password_Functionality_for_agent() {
    // Write code here that turns the phrase above into concrete actions
	changePasswordPage.validatesChangePasswordforAgent();
}

}