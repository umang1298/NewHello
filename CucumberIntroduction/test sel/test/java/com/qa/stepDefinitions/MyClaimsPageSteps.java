package com.qa.stepDefinitions;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.MemberRosterPage;
import com.qa.pages.MyClaimsPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MyClaimsPageSteps  extends TestBase{

	MyClaimsPage myClaimspage = new MyClaimsPage(driver);
	Properties prop = initialize_properties();

	
	@Then("verifies list of claims displayed for the {string} from the {string}")
	public void verifies_list_of_claims_displayed_for_the_from_the(String memberid, String Date) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		myClaimspage.clickonsearchforgivenperiod(memberid,Date);
	}

	@Then("user gets the ClaimNumber from search results and set the value to {string} if {string}")
	public void user_gets_the_ClaimNumber_from_search_results_and_set_the_value_to(String ClaimNumber,String ClaimsPresent) {
	    // Write code here that turns the phrase above into concrete actions
		myClaimspage.getClaimNumber(ClaimNumber,ClaimsPresent);
	}

		
	@Then("user clicks on {string} link from search results if {string}")
	public void user_clicks_on_link_from_search_results(String Claimnumber,String Claimspresent) {
	    // Write code here that turns the phrase above into concrete actions
		myClaimspage.clickonFirstClaimNumber(Claimnumber,Claimspresent);
	}

	
	@Then("user does the validation based on the MemberServiceresponse XML {string} for {string} and checks {string}")
	public void user_does_the_validation_based_on_the_MemberServiceresponse_XML(String ResponseXMLPath,String MemberName,String ClaimsPresent) throws IOException, ParseException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		myClaimspage.validateDependantageRestriction(ResponseXMLPath,MemberName,ClaimsPresent);
	}


	@Then("user gets the MemRecordNumber from {string} set the value to {string}")
	public void user_gets_the_MemRecordNumber_from_set_the_value_to(String ResponseXMLPath, String memrecordnumber) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		myClaimspage.getmemRecordNumber(ResponseXMLPath,memrecordnumber);
	}
	
	@Then("user gets the MemRecordNumber for the {string} from {string} set the value to {string}")
	public void user_gets_the_MemRecordNumber_for_the_from_set_the_value_to(String MemberID, String ResponseXMLPath, String memrecordnumber) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		myClaimspage.getmemRecordNumberforMemberID(MemberID,ResponseXMLPath,memrecordnumber);
	}
	
	@Then("user does the Multiple contracts validation based on the Claimsummary XML {string}")
	public void user_does_the_Multiple_contracts_validation_based_on_the_Claimsummary_XML(String ResponseXMLPath) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	myClaimspage.validationonClaimSearchResultscount(ResponseXMLPath);
	}

	@Then("user validates the EOB Document for the value yes\\/no based on MemberServiceresponse XML {string} for {string} if {string}")
	public void user_validates_the_EOB_Document_for_the_value_yes_no_based_on_MemberServiceresponse_XML(String ResponseXMLPath,String MemberName,String ClaimsPresent) throws InterruptedException, IOException {
	    // Write code here that turns the phrase above into concrete actions
		myClaimspage.clickonEOBLink(ResponseXMLPath,MemberName,ClaimsPresent);
	}
}