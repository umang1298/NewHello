package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.HAPPortalLandingPage;
import com.qa.pages.MyFormsAndDocumentsPage;

import io.cucumber.java.en.Then;

public class MyFormsAndDocumentsPageSteps extends TestBase{
	
	MyFormsAndDocumentsPage myformsanddocumentspage = new MyFormsAndDocumentsPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user clicks {string}")
	public void user_clicks(String MenuoptiontoPass) {
	    // Write code here that turns the phrase above into concrete actions
	   myformsanddocumentspage.Userclicksoneofthemenusfrommyformsanddocumentspage(MenuoptiontoPass);
	}

}
