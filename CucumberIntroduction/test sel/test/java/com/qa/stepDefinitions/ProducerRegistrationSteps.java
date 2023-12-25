package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProducerRegistrationPage;

import io.cucumber.java.en.Then;

public class ProducerRegistrationSteps extends TestBase {

	ProducerRegistrationPage producerRegistrationPage = new ProducerRegistrationPage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("user enters the {string} and {string} and  {string} and {string} and {string}")
	public void user_enters_the_and_and_and_and(String username, String FirstName, String LastName, String SSN, String NPN) {
	    // Write code here that turns the phrase above into concrete actions
		producerRegistrationPage.userenterstheregistrationdetails(username,FirstName,LastName,SSN,NPN);
	}
	
	@Then("user enters {string} and {string}")
	public void user_enters_and(String EmailAddress, String password) {
	    // Write code here that turns the phrase above into concrete actions
		producerRegistrationPage.userentersemailaddressandpassword(EmailAddress,password);
	}
	
	@Then("user creates challenge question & answer and verifies the producer user registration")
	public void user_creates_challenge_question_answer_and_verifies_the_producer_user_registration() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		producerRegistrationPage.usercreateschallengequestionsandverifiesuserregistration();
	}
	
}
