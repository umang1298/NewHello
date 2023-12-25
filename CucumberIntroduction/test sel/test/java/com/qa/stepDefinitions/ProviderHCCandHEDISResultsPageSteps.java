package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderHCCandHEDISResultsPage;

import io.cucumber.java.en.Then;

public class ProviderHCCandHEDISResultsPageSteps extends TestBase{
	
	ProviderHCCandHEDISResultsPage providerHCCandHEDISResultsPage = new ProviderHCCandHEDISResultsPage(driver);
	Properties prop = initialize_properties();

	
	@Then("user verifies HHC & HEDIS Program result is displayed successfully for the {string}")
	public void user_verifies_HHC_HEDIS_Program_result_is_displayed_successfully_for_the(String MemberID) {
	    // Write code here that turns the phrase above into concrete actions
		providerHCCandHEDISResultsPage.userverifiesHCCandHEDISProgramresultisdispalyedfortheMemberID(MemberID);
	}
	
	@Then("user selects Medical record attachment for {string} and uploads the document")
	public void user_selects_Medical_record_attachment_for_and_uploads_the_document(String Documentfor) {
	    // Write code here that turns the phrase above into concrete actions
		providerHCCandHEDISResultsPage.userselectsmedicalrecordattachmentanduploadsthedocument(Documentfor);
	}

	@Then("user verifies print function on HCC & HEDIS Program results page for the {string}")
	public void user_verifies_print_function_on_HCC_HEDIS_Program_results_page_for_the(String MemberID) {
	    // Write code here that turns the phrase above into concrete actions
		providerHCCandHEDISResultsPage.userverifiesprintfunction(MemberID);
	}
	
	
	
}
