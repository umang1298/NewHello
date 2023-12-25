package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.HAPPortalLandingPage;
import com.qa.pages.LoginPage;
import com.qa.pages.MemberRegistrationPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MemberRegistrationSteps extends TestBase {
	MemberRegistrationPage memberRegistrationPage = new MemberRegistrationPage(driver);
	Properties prop = initialize_properties();
	


	@Given("user provides {string} {string} {string} {string} {string} {string} {string} details")
	public void user_provides_details(String IDNumber,String GroupID,String LastName,
			String FirstName,String Zipcode,String DOB,String password) throws InterruptedException {
	    memberRegistrationPage.Userinformation(IDNumber,GroupID,LastName,FirstName,Zipcode,DOB,password); 
	}
	
	@Then("user verifies Member Registraion completed successfully")
	public void user_verifies_Member_Registraion_completed_successfully() {
	    memberRegistrationPage.verifymembercreatedsuccessful();
	}
	
	/*@Given("get number of members present in spreadsheet {int}")
	public void get_number_of_members_present_in_spreadsheet(Integer RowCount) {
	    // Write code here that turns the phrase above into concrete actions
		memberRegistrationPage.getnumberofcountfromexcel(RowCount);
	}

	
	@Given("user registers the member for the number of rows {int} in data sheet")
	public void user_registers_the_member_for_the_number_of_rows_in_data_sheet(Integer RowCount) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		memberRegistrationPage.fetchfeilddetailsfromexcel(RowCount);
	}*/
	@Then("user creates member from {string} for {string} and {string}")
	public void user_creates_member_from_and(String responseXMLPath, String memrecordnumber,String password) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		memberRegistrationPage.membercreation(responseXMLPath,memrecordnumber,password);
	}
}
