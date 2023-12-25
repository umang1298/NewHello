package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.AWDFormPage;
import com.qa.pages.HapConnectivityPage;

import io.cucumber.java.en.Then;

public class AWDFormSteps extends TestBase{

	AWDFormPage aWDFormPage=new AWDFormPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user clicks on online forms {string} link based on {string}")
	public void user_clicks_on_online_forms_link_based_on(String MenuOptionToPass, String Response ) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		aWDFormPage.clickontheonlineformlink(MenuOptionToPass,Response);
	}

	@Then("user validates AWD form Request")
	public void user_validates_AWD_form_Request() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		aWDFormPage.AWDRequestForm();
	}
}
