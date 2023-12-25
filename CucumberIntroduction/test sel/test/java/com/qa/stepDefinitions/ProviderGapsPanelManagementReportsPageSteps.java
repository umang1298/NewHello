package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderGapsPanelManagementReportsPage;
import com.qa.pages.ProviderHCCandHEDISPage;

import io.cucumber.java.en.Then;

public class ProviderGapsPanelManagementReportsPageSteps extends TestBase{
	
	ProviderGapsPanelManagementReportsPage providerGapsPanelManagementReportsPage = new ProviderGapsPanelManagementReportsPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user Selects Provider and {string} and Clicks on Submit and Validates Expected results")
	public void user_Selects_Provider_and_and_Clicks_on_Submit_and_Validates_Expected_results(String TypeofGap) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		providerGapsPanelManagementReportsPage.userselectsproviderandTypeofgapandclickssubmit(TypeofGap);
	}

	@Then("user Selects Provider and {string} and Clicks on Download and Validates Expected results")
	public void user_Selects_Provider_and_and_Clicks_on_Download_and_Validates_Expected_results(String TypeofGap) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		providerGapsPanelManagementReportsPage.userselectsproviderandTypeofgapandclicksDownload(TypeofGap);
	}

	


}
