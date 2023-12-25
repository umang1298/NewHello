package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployeeLGQuotingToolBrowseCatalogPage;
import com.qa.pages.EmployeeLGQuotingToolPage;

import io.cucumber.java.en.Then;

public class EmployeeLGQuotingToolBrowseCatalogPageSteps extends TestBase {

	
	EmployeeLGQuotingToolBrowseCatalogPage employeeLGQuotingToolBrowseCatalogPage = new EmployeeLGQuotingToolBrowseCatalogPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user selects Plan attributes Line of Business as {string} and HSA as {string}")
	public void user_selects_Plan_attributes_Line_of_Business_as_and_HSA_as(String LOBValue, String HSAValue) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeLGQuotingToolBrowseCatalogPage.userselectsLOB(LOBValue);
		employeeLGQuotingToolBrowseCatalogPage.userselectsHSA(HSAValue);
	}


	@Then("user sets range value for Medical plan benefits and validates slider functionalities")
	public void user_sets_range_value_for_Medical_plan_benefits_and_validates_slider_functionalities() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeLGQuotingToolBrowseCatalogPage.usersetsrangeformedicalplanbenfitsandvalidatessliderfunctions();
	}
	
	@Then("user clicks search button in browse catalog page")
	public void user_clicks_search_button_in_browse_catalog_page() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeLGQuotingToolBrowseCatalogPage.userclicksSearch();
	}
	
	@Then("user validates search results page is loaded")
	public void user_validates_search_results_page_is_loaded() {
	    // Write code here that turns the phrase above into concrete actions
		employeeLGQuotingToolBrowseCatalogPage.uservalidatessearchresultisloaded();
	}
	
	@Then("user modifies search criteria in the left side filter by options")
	public void user_modifies_search_criteria_in_the_left_side_filter_by_options() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		employeeLGQuotingToolBrowseCatalogPage.usermodifiessearchinFilterByoptions();
	}
}
