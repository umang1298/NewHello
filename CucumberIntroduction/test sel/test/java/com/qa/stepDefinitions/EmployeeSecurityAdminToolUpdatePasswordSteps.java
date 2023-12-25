package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployeeSecurityAdminToolUpdatePasswordPage;

import io.cucumber.java.en.Then;


public class EmployeeSecurityAdminToolUpdatePasswordSteps extends TestBase {

	EmployeeSecurityAdminToolUpdatePasswordPage employeeSecurityAdminToolUpdatePasswordPage = new EmployeeSecurityAdminToolUpdatePasswordPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user selects the {string} enters the {string} and validates the Search results")
	public void user_selects_the_enters_the_and_validates_the_Search_results(String Usertype, String Userid) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeSecurityAdminToolUpdatePasswordPage.userselectsusertypeenterstheuseridandvalidatessearchresults(Usertype,Userid);
	}
	
	@Then("user updates {string} and {string} and validates the password updatedsuccessfully")
	public void user_updates_and_and_validates_the_password_updatedsuccessfully(String newpassword, String confirmpassword) {
	    // Write code here that turns the phrase above into concrete actions
		employeeSecurityAdminToolUpdatePasswordPage.userupdatespasswordandvalidatespasswordupdated(newpassword,confirmpassword);
	}
	
	@Then("user enters the {string} and updates {string} and {string}")
	public void user_enters_the_and_updates_and(String newpassword, String actualpassword, String actualPassword) {
	    // Write code here that turns the phrase above into concrete actions
		employeeSecurityAdminToolUpdatePasswordPage.userupdatesthepasswordandvalidatesthepassword(newpassword,actualpassword,actualPassword);
	}
}
