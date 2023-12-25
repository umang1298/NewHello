package com.qa.stepDefinitions;

import com.qa.base.TestBase;
import com.qa.pages.ReimbursementAccountPage;

import io.cucumber.java.en.Then;

public class ReimbursementAccountSteps extends TestBase{
	ReimbursementAccountPage reimbursementAccountPage = new ReimbursementAccountPage(driver);
	@Then("user validates Reimbursement Account Link")
	public void user_validates_Reimbursement_Account_Link() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		reimbursementAccountPage.ValidateReimbursementLink();
	}
}
