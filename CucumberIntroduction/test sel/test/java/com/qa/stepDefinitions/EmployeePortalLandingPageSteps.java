package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployeePortalLandingPage;
import com.qa.pages.EmployerPortalLandingPage;

import io.cucumber.java.en.Then;

public class EmployeePortalLandingPageSteps extends TestBase{
	
	EmployeePortalLandingPage employeePortalLandingPage = new EmployeePortalLandingPage(driver);
	Properties prop = initialize_properties();

	@Then("user navigates to {string} in Employee Portal")
	public void user_navigates_to_in_Employee_Portal(String MenuOption) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    employeePortalLandingPage.usernavigatestooneoftheMenusinEmployeePortal(MenuOption);
	}

	@Then("user navigtes to Employee portal home page")
	public void user_navigtes_to_Employee_portal_home_page() {
	    // Write code here that turns the phrase above into concrete actions
	    employeePortalLandingPage.usernaviagatestoemployeeportalhomepageusingbreadcrumHomeButton();
	}
	
	@Then("user navigates to the {string} in Employee portal")
	public void user_navigates_to_the_in_Employee_portal(String menuoption) {
	    // Write code here that turns the phrase above into concrete actions
		employeePortalLandingPage.usernavigatestooneofthemenusatthetop(menuoption);
	}
	
	@Then("user navigates to the HEDIS Member Outreach in Employee portal")
	public void user_navigates_to_the_HEDIS_Member_Outreach_in_Employee_portal() {
	    // Write code here that turns the phrase above into concrete actions
		employeePortalLandingPage.userclicksonHedisMemberOutreach();
	}
	
	@Then("user verifies successfull landing in the provider online lookup page")
	public void user_verifies_successfull_landing_in_the_provider_online_lookup_page() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeePortalLandingPage.userverifiesproviderlookuppageisopenedinnewtab();
	}
	
}
