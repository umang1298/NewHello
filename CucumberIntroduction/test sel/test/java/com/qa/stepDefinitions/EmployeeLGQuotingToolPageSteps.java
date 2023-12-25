package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployeeLGQuotingToolPage;

import io.cucumber.java.en.Then;


public class EmployeeLGQuotingToolPageSteps extends TestBase {
	
	EmployeeLGQuotingToolPage employeeLGQuotingToolPage = new EmployeeLGQuotingToolPage(driver);
	Properties prop = initialize_properties();

	@Then("user performs search for an existing quote with {string} and value as {string}")
	public void user_performs_search_for_an_existing_quote_with_and_value_as(String Searchwith, String Value) {
	    // Write code here that turns the phrase above into concrete actions
		employeeLGQuotingToolPage.userperformssearchforexistingquotingusingcompanyname(Searchwith,Value);
	}
	
	@Then("user takes {string} and {string} from search results for Company Name")
	public void user_takes_and_from_search_results_for_Company_Name(String QuoteID, String GroupID) {
	    // Write code here that turns the phrase above into concrete actions
		employeeLGQuotingToolPage.usertakesQuoteIDandGroupIDfromsearchresults(QuoteID,GroupID);
	}

	@Then("user validates search result table is displayed")
	public void user_validates_search_result_table_is_displayed() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeLGQuotingToolPage.uservalidatesSearchresultTableisdisplayed();
	}
	
	@Then("user Clicks {string} link in the search results")
	public void user_Clicks_link_in_the_search_results(String QuoteID) {
	    // Write code here that turns the phrase above into concrete actions
		employeeLGQuotingToolPage.userclicksonquoteidlink(QuoteID);
	}
	
	@Then("user clicks on Browse catalog option")
	public void user_clicks_on_Browse_catalog_option() {
	    // Write code here that turns the phrase above into concrete actions
		employeeLGQuotingToolPage.userclicksonBrowseCatalog();
	}

}
