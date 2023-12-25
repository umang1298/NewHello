package com.qa.stepDefinitions;

import com.qa.base.TestBase;
import com.qa.pages.ApplicationMenusPage;

import io.cucumber.java.en.Then;

public class ApplicationMenusSteps extends TestBase {

	ApplicationMenusPage applicationMenusPage =new ApplicationMenusPage(driver);
	
	
	@Then("user validates Hap doctor and search and message")
	public void user_validates_Hap_doctor_and_search_and_message() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		applicationMenusPage.validatingApplicationMenus();
	}

}
