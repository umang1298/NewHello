package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployeeLGQuotingToolPage;
import com.qa.pages.EmployeeQuoteDetailsPage;

import io.cucumber.java.en.Then;

public class EmployeeQuoteDetailsPageSteps extends TestBase{
	
	EmployeeQuoteDetailsPage employeeQuoteDetailsPage = new EmployeeQuoteDetailsPage(driver);
	Properties prop = initialize_properties();

	@Then("user validates Quote details page is displayed for {string}")
	public void user_validates_Quote_details_page_is_displayed_for(String QuoteID) {
	    // Write code here that turns the phrase above into concrete actions
		employeeQuoteDetailsPage.uservalidatesquotedetailspageisdisplayed(QuoteID);
	}

	@Then("user validates {string}, {string} and {string} Functionalities")
	public void user_validates_and_Functionalities(String SBC, String BenefitSumamry, String ViewRatessheet) {
	    // Write code here that turns the phrase above into concrete actions
		employeeQuoteDetailsPage.uservalidatesSBCBenefitSummaryAndViewRateSheetfunctionalities(SBC, BenefitSumamry, ViewRatessheet );
	}
	
	@Then("user Clicks Back to search button")
	public void user_Clicks_Back_to_search_button() {
	    // Write code here that turns the phrase above into concrete actions
		employeeQuoteDetailsPage.userclicksbacktosearch();
	}

	
}
