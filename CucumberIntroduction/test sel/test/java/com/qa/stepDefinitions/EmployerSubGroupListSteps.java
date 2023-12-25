package com.qa.stepDefinitions;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;

import com.qa.base.TestBase;
import com.qa.pages.EmployerPortalMyToolsPage;
import com.qa.pages.EmployerSubGroupListPage;


import io.cucumber.java.en.Then;

public class EmployerSubGroupListSteps extends TestBase {

	EmployerSubGroupListPage employerSubGroupListPage = new EmployerSubGroupListPage(driver);
	Properties prop = initialize_properties();
	
	
	
	
	 @Then("user gets the count of Subgroup ids and stores it to a key {string}")
	 public void
	 user_gets_the_count_of_Subgroup_id_s_and_stores_it_to_a_key(String subGroupIdCount) { 
		 // Write code here that turns the phrase above int concrete actions
	 employerSubGroupListPage.getCountOfSubGroupIds(subGroupIdCount);
	 
	 }

	 @Then("user verifies the documents based on the {string}, {string} , {string}, {string} and {string}")
public void user_verifies_the_documents_based_on_the_and(String GroupID, String CountofSubgroupIds, String RenwalMonthKey, String MemberType, String Dateval) throws InterruptedException, IOException {
    // Write code here that turns the phrase above into concrete actions
	employerSubGroupListPage.verifyDocumentsForBenifitCodes( GroupID,  CountofSubgroupIds,  RenwalMonthKey,  MemberType,  Dateval);
		
}
	 @Then("user verifies the links based on the {string}, {string} , {string}, {string} and {string}")
public void user_verifies_the_links_based_on_the_and(String GroupID, String CountofSubgroupIds, String RenwalMonthKey, String MemberType, String Dateval) throws InterruptedException, IOException {
    // Write code here that turns the phrase above into concrete actions
	employerSubGroupListPage.verifylinksForBenifitCodes( GroupID,  CountofSubgroupIds,  RenwalMonthKey,  MemberType,  Dateval);
		
}
	 @Then("user verifies the links for PAT Employer based on the {string}, {string} , {string}, {string} and {string}")
	 public void user_verifies_the_links_for_PAT_Employer_based_on_the_and(String GroupID, String CountofSubgroupIds, String RenwalMonthKey, String MemberType, String Dateval) throws InterruptedException, IOException {
	     // Write code here that turns the phrase above into concrete actions
	 	employerSubGroupListPage.verifylinksForBenifitCodesPA( GroupID,  CountofSubgroupIds,  RenwalMonthKey,  MemberType,  Dateval);
	 		
	 } 
	 @Then("user verifies the documents based on the {string}, {string} , {string}, {string}, {string} and Checks {string} is displayed  in Health Insurance Policy")
	 public void user_verifies_the_documents_based_on_the_and_Checks_is_displayed_in_Health_Insurance_Policy(String GroupID, String CountofSubgroupIds, String RenwalMonthKey, String MemberType, String Dateval, String texttovalidate) throws ClientProtocolException, IOException, InterruptedException {
	     // Write code here that turns the phrase above into concrete actions
	     employerSubGroupListPage.verifyDocumentsForBenefitCodesPATransitional(GroupID,  CountofSubgroupIds,  RenwalMonthKey,  MemberType,  Dateval, texttovalidate);
	 }
	 @Then("user verifies the documents based on the {string}, {string} , {string},{string}, {string} and {string}")
	 public void user_verifies_the_documents_based_on_the_and(String GroupID, String CountofSubgroupIds, String MedicalKey, String PharmacyKey, String MemberType, String Dateval) throws IOException, InterruptedException, AWTException {
	     // Write code here that turns the phrase above into concrete actions
		 employerSubGroupListPage.verifyDocumentsForSBCBenifitCodes1( GroupID,  CountofSubgroupIds,  MedicalKey,PharmacyKey,  MemberType,  Dateval);
		 
			
	 }
	 @Then("verify Group Contract documents in the View forms and Documents page based on the Response XML {string}, {string} , {string},{string}, {string} and {string}")
	 public void verify_Group_Contract_documents_in_the_View_forms_and_Documents_page_based_on_the_Response_XML_and(String GroupID, String CountofSubgroupIds, String MedicalKey, String PharmacyKey, String MemberType, String Dateval) throws IOException, InterruptedException {
		 employerSubGroupListPage.verifyGroupContractDocumentsQHPEmployerPegaFlow(GroupID,  CountofSubgroupIds,  MedicalKey, PharmacyKey,  MemberType,  Dateval);
	 }
}
