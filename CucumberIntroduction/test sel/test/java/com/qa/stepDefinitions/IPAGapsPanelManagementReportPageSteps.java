package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.IPAGapsPanelManagementReportPage;
import com.qa.pages.ProviderAddCOBInformationPage;

import io.cucumber.java.en.Then;

public class IPAGapsPanelManagementReportPageSteps extends TestBase {
	
	IPAGapsPanelManagementReportPage iPAGapsPanelManagementReportPage = new IPAGapsPanelManagementReportPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user Selects gap as {string} and Clicks on Submit and Validates Expected results")
	public void user_Selects_Provider_as_and_Clicks_on_Submit_and_Validates_Expected_results(String TypeofGap) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		iPAGapsPanelManagementReportPage.UserSelectsgapandClicksonSubmitButton(TypeofGap);
	}
	

	@Then("user Selects gap as {string} and Clicks on Download and Validates Expected results")
	public void user_Selects_gap_as_and_Clicks_on_Download_and_Validates_Expected_results(String TypeofGap) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		iPAGapsPanelManagementReportPage.UserSelectsgapandClicksonDownloadButton(TypeofGap);
	}

	@Then("user Selects gap as {string} and Clicks on Download and Validates Expected results for Physician Organisation")
	public void user_Selects_gap_as_and_Clicks_on_Download_and_Validates_Expected_results_for_Physician_Organisation(String TypeofGap) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		iPAGapsPanelManagementReportPage.UserSelectsgapandClicksonDownloadButtonforPO(TypeofGap);
	}
	
	@Then("user selects Reports for radio button {string}")
	public void user_selects_Reports_for_radio_button(String Option) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		iPAGapsPanelManagementReportPage.userselectsreportsforoption(Option);
	}
}
