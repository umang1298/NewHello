package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;
import org.testng.*;
import com.qa.base.TestBase;
import com.qa.pages.MyDeductiblesPage;

import io.cucumber.java.en.Then;

public class MyDeductiblesSteps extends TestBase{
	MyDeductiblesPage myDeductiblesPage=new MyDeductiblesPage(driver);
	Properties prop = initialize_properties();

	
	@Then("user validates Innetwork DeductibleandOOP page based on {string} and {string} using deductible service")
	public void user_validates_Innetwork_DeductibleandOOP_page_based_on_and_using_deductible_service(String responseXML, String Dateval) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		myDeductiblesPage.uservalidatesInnetworkDeductibleandOOPpage(responseXML,Dateval);
	}
	@Then("user validates Outnetwork DeductibleandOOP page based on {string} and {string} using deductible service")
	public void user_validates_Outnetwork_DeductibleandOOP_page_based_on_and_using_deductible_service(String responseXML, String Dateval) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		myDeductiblesPage.uservalidatesOutnetworkDeductibleandOOPpage(responseXML,Dateval);
	}
}