package com.qa.stepDefinitions;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ViewFormsAndDocumentsPage;
import com.qa.util.ScenarioContext;

import io.cucumber.java.en.Then;

public class ViewFormsAndDocumentsPageSteps extends TestBase {

	ViewFormsAndDocumentsPage viewFormsAndDocumentsPage = new ViewFormsAndDocumentsPage(driver);
	Properties prop = initialize_properties();

	@Then("verify the Benefit Year based on the key {string} and {string}")
	public void verify_the_Benefit_Year_based_on_the_key_and(String BenefitStartDate, String BenefitEndDate) throws ParseException {
	    // Write code here that turns the phrase above into concrete actions
		viewFormsAndDocumentsPage.VerifytheBenifitYear(BenefitStartDate , BenefitEndDate);
	}
	
	/*
	 * @Then("verify the Benifit Year based on the key {string}") public void
	 * verify_the_Benifit_Year_based_on_the_key(String KeyToStore) { // Write code
	 * here that turns the phrase above into concrete actions
	 * 
	 * viewFormsAndDocumentsPage.VerifytheBenifitYear(KeyToStore);
	 * 
	 * }
	 */
	
	@Then("verify all the Riders documents for {string} based on the {string}")
	public void verify_all_the_Riders_documents_for_based_on_the(String MemberType, String renewalMonth) throws InterruptedException, IOException {
	    // Write code here that turns the phrase above into concrete actions
		viewFormsAndDocumentsPage.VerifytheRidersDocuments(MemberType, renewalMonth);
	}

	
	
	@Then("verify {string} subscriber contract based on the {string}")
	public void verify_subscriber_contract_based_on_the(String MemberTypeForSubscriberContract, String renewalMonthForSubscriberContract) throws InterruptedException, IOException {
	    // Write code here that turns the phrase above into concrete actions
		viewFormsAndDocumentsPage.verifysubscribercontract(MemberTypeForSubscriberContract,renewalMonthForSubscriberContract);
	}

	@Then("verify {string} Summary of Contract changes based on the {string}")
	public void verify_Summary_of_Contract_changes_based_on_the(String MemberTypeForSOB, String renewalMonthForSOB) throws InterruptedException, IOException {
		// Write code here that turns the phrase above into concrete actions
		viewFormsAndDocumentsPage.verifysummaryofcontractchanges(MemberTypeForSOB,renewalMonthForSOB);
	}
	
	@Then("user Clicks on {string} and verifies the title {string} is displayed.")
	public void user_Clicks_on_and_verifies_the_title_is_displayed(String DocumentToClick, String TextToVerify) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		viewFormsAndDocumentsPage.verifyhealthinsurancepolicyforPATransitionalMember(DocumentToClick, TextToVerify);
	}


	@Then("verify Contract documents in the View forms and Documents page based on the Response XML {string}")
	public void verify_Contract_documents_in_the_View_forms_and_Documents_page_based_on_the_Response_XML(String pathtoresponseXML) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    viewFormsAndDocumentsPage.verifyContractDocumentsQHPMemberPegaFlow(pathtoresponseXML);
	}

	@Then("user verifies the document links based on {string} and Response XML {string} and {string}")
	public void user_verifies_the_document_links_based_on_and_Response_XML(String MemberType, String ResponseXMLPath, String RenewalMonthKey) throws IOException {
		viewFormsAndDocumentsPage.verifyDocumentLinksforSGTransitionalMember(MemberType, ResponseXMLPath, RenewalMonthKey);
	    
	}

	@Then("user verifies the document links for PAT Member based on {string} and Response XML {string} and {string}")
	public void user_verifies_the_document_links_for_PAT_Member_based_on_and_Response_XML(String MemberType, String ResponseXMLPath, String RenewalMonthKey) throws IOException {
		viewFormsAndDocumentsPage.verifyDocumentLinksforPATransitionalMember(MemberType, ResponseXMLPath, RenewalMonthKey);
	    
	}
}
