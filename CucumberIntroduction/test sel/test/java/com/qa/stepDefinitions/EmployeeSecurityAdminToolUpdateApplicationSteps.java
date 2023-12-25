package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployeeSecurityAdminToolUpdateApplicationPage;

import io.cucumber.java.en.Then;

public class EmployeeSecurityAdminToolUpdateApplicationSteps extends TestBase{

	EmployeeSecurityAdminToolUpdateApplicationPage employeeSecurityAdminToolUpdateApplicationPage = new EmployeeSecurityAdminToolUpdateApplicationPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user selects the update application and add or remove the application & validates its successfully updated")
	public void user_selects_the_update_application_and_add_or_remove_the_application_validates_its_successfully_updated() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeSecurityAdminToolUpdateApplicationPage.userupdatestheapplicationandvalidates();
	}
}
