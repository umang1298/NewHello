package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.VendorRemittanceAdvicePage;

import io.cucumber.java.en.Then;

public class VendorRemittanceAdvicePageSteps extends TestBase{

	VendorRemittanceAdvicePage vendorRemittanceAdvicePage = new VendorRemittanceAdvicePage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("user searches RA for one year")
	public void user_searches_RA_for_one_year() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		vendorRemittanceAdvicePage.usersearchesRAforonceyear();
	}
	
	@Then("user validates results for RA search")
	public void user_validates_results_for_RA_search() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		vendorRemittanceAdvicePage.uservalidatesresultforRAsearch();
	}
	
	@Then("user validates results for RA search in employee portal")
	public void user_validates_results_for_RA_search_in_employee_portal() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		vendorRemittanceAdvicePage.uservalidatessearchresultforRAinemployeeportal();
	}
	
	@Then("user takes {string} and {string} from search results")
	public void user_takes_and_from_search_results(String VendorId, String PaymentNumber) {
	    // Write code here that turns the phrase above into concrete actions
	    vendorRemittanceAdvicePage.usergetsVendorIdandPaymentNumberfromSearchResults(VendorId,PaymentNumber);
	}
	
	@Then("user searches RA with {string}")
	public void user_searches_RA_with(String Searchwith) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    vendorRemittanceAdvicePage.usersearcheswithoption(Searchwith);
	}
}
