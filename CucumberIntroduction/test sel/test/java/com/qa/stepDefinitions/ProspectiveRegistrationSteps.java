package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProspectiveRegistrationPage;

import io.cucumber.java.en.Then;



public class ProspectiveRegistrationSteps extends TestBase{
	
	ProspectiveRegistrationPage prospectiveRegistrationPage = new ProspectiveRegistrationPage(driver);
	Properties prop = initialize_properties();
	

@Then("user clicks on Register Button")
	public void user_clicks_on_Register_Button() {
	    // Write code here that turns the phrase above into concrete actions
	prospectiveRegistrationPage.userclicksonRegisterbutton();
	
	}

	@Then("user enters required values to create member based on {string} and {string} and {string} and {string} and {string} and {string}")
	public void user_enters_required_values_to_create_member_based_on_and_and_and_and(String username, String lastname, String Firstname, String ssn, String DOB,String password) {
	    // Write code here that turns the phrase above into concrete actions
		prospectiveRegistrationPage.userentersRequiredvalues(username,lastname,Firstname,ssn,DOB,password);
	}
	
	}
