package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployeeHEDISMemberoutreachGapsSearchPage;

import io.cucumber.java.en.Then;

public class EmployeeHEDISmemberoutreachGapsSearchPageSteps extends TestBase {
		
	EmployeeHEDISMemberoutreachGapsSearchPage employeeHEDISMemberoutreachGapsSearchPage = new EmployeeHEDISMemberoutreachGapsSearchPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user searches for MemberId with {string} and {string} and selects one")
	public void user_searches_for_MemberId_with_and_and_selects_one(String FirstName, String LastName) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeHEDISMemberoutreachGapsSearchPage.usersearchesforMemberidwithmembername(FirstName,LastName);
	}
	
	@Then("user validates HEDIS gaps results are displayed")
	public void user_validates_HEDIS_gaps_results_are_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		employeeHEDISMemberoutreachGapsSearchPage.uservalidatesHEDISGapsresultsaredisplayed();
	}
	
}
