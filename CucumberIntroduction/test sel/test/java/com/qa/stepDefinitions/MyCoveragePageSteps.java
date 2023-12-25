package com.qa.stepDefinitions;

import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Properties;
import com.qa.base.TestBase;
import com.qa.pages.MyCoveragePage;
import io.cucumber.java.en.Then;

public class MyCoveragePageSteps extends TestBase {

	MyCoveragePage mycoveragepage = new MyCoveragePage(driver);
	Properties prop = initialize_properties();

	@Then("user selects {string}")
	public void user_selects(String MenuoptiontoPass) throws InterruptedException, IOException, AWTException {
		// Write code here that turns the phrase above into concrete actions
		mycoveragepage.Userselectsoptionfrommycoverage(MenuoptiontoPass);
		//mycoveragepage.UserClicks1();

	}
	
	@Then("user validates the SBC Document for {string} and {string}")
	public void user_selects_and_validates_the_SBC_Document_for_and( String MedicalProductID, String PharmacyProductID) throws MalformedURLException, IOException, AWTException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		//mycoveragepage.UserClicksSummaryOfBenefitsCoverage(MedicalProductID, PharmacyProductID);
		mycoveragepage.UserClicksSummaryOfBenefitsCoverageJS(MedicalProductID, PharmacyProductID);
	}
	@Then("user validates the SBC Document Links for Members")
	public void user_validates_the_SBC_Document_Links_for_Members() {
	    // Write code here that turns the phrase above into concrete actions
	   mycoveragepage.userverifiesSBCDocumentLink();
	}

@Then("user validates member is having single or dual contracts based on the MemberServiceresponse XML {string}")
public void user_validates_member_is_having_single_or_dual_contracts_based_on_the_MemberServiceresponse_XML(String ResponseXMLPath) throws IOException, InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	mycoveragepage.uservalidatesdualcontractdropdown(ResponseXMLPath);
}

	@Then("user validates third party links based on the MemberServiceresponse XML {string}")
	public void user_validates_third_party_links_based_on_the_MemberServiceresponse_XML(String ResponseXMLPath) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		mycoveragepage.userverifiesthirdpartyLinks(ResponseXMLPath);
	}

	@Then("user validates Terminated and future members")
	public void user_validates_Terminated_and_future_members() {
	    // Write code here that turns the phrase above into concrete actions
	    mycoveragepage.userverifiesterminatedandfuturemember();
	}
	

	@Then("user validates contract information based on the MemberServiceresponse XML {string}")
	public void user_validates_contract_information_based_on_the_MemberServiceresponse_XML(String ResponseXMLPath) throws IOException, ParseException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		mycoveragepage.validatescontractinformation(ResponseXMLPath);
	}
	@Then("user validates Covered members based on the MemberServiceresponse XML {string} for {string}")
	public void user_validates_Covered_members_based_on_the_MemberServiceresponse_XML(String ResponseXMLPath,String usertype) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		mycoveragepage.validatescoveredmembersdetails(ResponseXMLPath,usertype);
	}

	@Then("user validates Yourcosts details based on the MemberServiceresponse XML {string}")
	public void user_validates_Yourcosts_details_based_on_the_MemberServiceresponse_XML(String ResponseXMLPath) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	   mycoveragepage.validatesyourcostdetails(ResponseXMLPath);
	}
	@Then("user validates COB Details from {string}")
	public void user_validates_COB_Details_from(String ResponseXMLPath) throws IOException, ParseException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	   mycoveragepage.validatesCOBMemberDetails(ResponseXMLPath);
	}
}
