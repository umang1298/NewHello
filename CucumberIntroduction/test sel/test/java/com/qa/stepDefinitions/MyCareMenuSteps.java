package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.MyCareMenuPage;
import com.qa.pages.MyClaimsPage;

import io.cucumber.java.en.Then;

public class MyCareMenuSteps extends TestBase{

	MyCareMenuPage myCareMenuPage = new MyCareMenuPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user validates all the static content links under My Care Menu for {string}")
	public void user_validates_all_the_static_content_links_under_My_Care_Menu_for(String UserType) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	myCareMenuPage.validateStaticLinks(UserType);
	}
	
	@Then("user validates the Link {string} under My Care Menu")
	public void user_validates_the_Link_under_My_Care_Menu(String menuoptiontopass) {
	    // Write code here that turns the phrase above into concrete actions
	  myCareMenuPage.ValidatemenuLinks(menuoptiontopass);
	}
}
