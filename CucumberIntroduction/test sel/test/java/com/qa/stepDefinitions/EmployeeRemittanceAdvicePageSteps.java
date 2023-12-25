package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployeeRemittanceAdvicePage;

import io.cucumber.java.en.Then;

public class EmployeeRemittanceAdvicePageSteps extends TestBase{
	
	EmployeeRemittanceAdvicePage employeeRemittanceAdvicePage = new EmployeeRemittanceAdvicePage(driver);
	Properties prop = initialize_properties();

	@Then("user searches {string} with {string} and selects one")
	public void user_searches_with_and_selects_one(String toselectindropdown, String VendorNameorPaymentNumber) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeRemittanceAdvicePage.userselectsindropdown(toselectindropdown);
		employeeRemittanceAdvicePage.userrsearchesandselectsoneorenterspaymentnumbervalue(toselectindropdown,VendorNameorPaymentNumber);
	}
	
}
