package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.MyCareMenuPage;
import com.qa.pages.MyHealthWelbeingMenusPage;

import io.cucumber.java.en.Then;

public class MyHealthWelbeingMenusSteps extends TestBase{

	MyHealthWelbeingMenusPage myHealthWelbeingMenusPage = new MyHealthWelbeingMenusPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user validates all the links under My Health Wellbeing Menu for {string}")
	public void user_validates_all_the_links_under_My_Health_Wellbeing_Menu_for(String UserType) {
	    // Write code here that turns the phrase above into concrete actions
		myHealthWelbeingMenusPage.validateallthemenus(UserType);
}
}