package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ClaimDetailsPage;
import com.qa.pages.MyClaimsPage;

import io.cucumber.java.en.Then;

public class ClaimDetailsPageSteps  extends TestBase{

	ClaimDetailsPage claimDetailsPage = new ClaimDetailsPage(driver);
	Properties prop = initialize_properties();

	
	
	@Then("user does the service detail validation based on the ClaimServiceresponse XML {string} if {string}")
	public void user_does_the_service_detail_validation_based_on_the_ClaimServiceresponse_XML(String ResponseXMLPath,String ClaimsPresent) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		claimDetailsPage.ClaimDetailspageValidations(ResponseXMLPath,ClaimsPresent);
	}

	@Then("user does the provider information validation based on the ClaimDetaileresponse XML {string} if {string}")
	public void user_does_the_provider_information_validation_based_on_the_ClaimDetaileresponse_XML(String ResponseXMLPath,String ClaimsPresent) throws IOException, InterruptedException {
    // Write code here that turns the phrase above into concrete actions
		claimDetailsPage.ProviderDetailValidations(ResponseXMLPath,ClaimsPresent);
}
	@Then("user does the Member information validation based on the MemberServiceresponse XML {string} for {string} if {string}")
	public void user_does_the_Member_information_validation_based_on_the_MemberServiceresponse_XML_and(String ResponseXMLPath, String MemberName,String ClaimsPresent) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		claimDetailsPage.MemberDetailValidations(ResponseXMLPath,MemberName,ClaimsPresent);
	}

}
