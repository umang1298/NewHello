package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ForgotUserNamePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class ForgotUserNameSteps extends TestBase {
	
	ForgotUserNamePage forgotUserNamePage = new ForgotUserNamePage(driver);
	Properties prop = initialize_properties();
	
	@Given("user enters {string} and SSN {string} and NPN {string} in producer Forgot username page")
	public void user_enters_and_SSN_and_NPN_in_producer_Forgot_username_page(String EmailAddress, String SSN, String NPN) {
	    // Write code here that turns the phrase above into concrete actions
		forgotUserNamePage.userentersthecredentialsandclicksoncontinuebutton(EmailAddress,SSN,NPN);
	}

	@Given("user enters {string} and SSN {string} and DOB {string} in Prospective Member Forgot username page")
	public void user_enters_and_SSN_and_NPN_in_Prospective_Member_Forgot_username_page(String EmailAddress, String SSN, String DOB) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    forgotUserNamePage.userentersthedetailsforProspMemandClicksOnContinueButton(EmailAddress,SSN,DOB);
	}
	
	@Then("user enters the {string} for challenge question and validates user name is displayed")
	public void user_enters_the_for_challenge_question(String SecurityAnswer) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		forgotUserNamePage.userentersthesecurityanswerforchallengequestionandvalidatesthepresenceofusername(SecurityAnswer);
	}
	
	@Then("user enters the {string} for challenge question for Prospect Member and validates user name is displayed")
	public void user_enters_the_for_challenge_question_for_Prospect_Member_and_validates_user_name_is_displayed(String Answer) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		forgotUserNamePage.userenterssecurityanswerforprospectmemberandvalidatesusernamedisplayed(Answer);
	}

}
