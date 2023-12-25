package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployeeHEDISAdvancesearchPage;

import io.cucumber.java.en.Then;


public class EmployeeHEDISAdvancesearchPageSteps extends TestBase{
	
	EmployeeHEDISAdvancesearchPage employeeHEDISAdvancesearchPage = new EmployeeHEDISAdvancesearchPage();
	Properties prop = initialize_properties();
	
	@Then("user creates Target list of the member with Hedis measures")
	public void user_creates_Target_list_of_the_member_with_Hedis_measures() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeHEDISAdvancesearchPage.usercreatestargetlistofmemberwithHedismeasures();
	}
	
	@Then("user executes the saved target list and verifies the results")
	public void user_executes_the_saved_target_list_and_verifies_the_results() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeHEDISAdvancesearchPage.userexecutesthesavedtargetlistandverifiestheresult();
	}
}
