package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderHCCandHEDISPage;

import io.cucumber.java.en.Then;


public class ProviderHCCandHEDISPageSteps extends TestBase{

	ProviderHCCandHEDISPage providerHCCandHEDISPage = new ProviderHCCandHEDISPage(driver);
	Properties prop = initialize_properties();

	
	@Then("user enters the {string} in text box and clicks search button")
	public void user_enters_the_in_text_box_and_clicks_search_button(String MemberID) {
	    // Write code here that turns the phrase above into concrete actions
		providerHCCandHEDISPage.userentersmemberIDandClickssearch(MemberID);
	}

	@Then("user Clicks on Panel Managaement Report Link")
	public void user_Clicks_on_Panel_Managaement_Report_Link() {
	    // Write code here that turns the phrase above into concrete actions
		providerHCCandHEDISPage.userclicksonPanelManagementReportLink();
	}

}
