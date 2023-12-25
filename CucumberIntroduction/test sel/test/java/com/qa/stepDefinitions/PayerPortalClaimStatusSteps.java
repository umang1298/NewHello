package com.qa.stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.qa.base.TestBase;
import com.qa.pages.PayerPortalClaimStatusPage;

import io.cucumber.java.en.Then;


public class PayerPortalClaimStatusSteps extends TestBase {
	PayerPortalClaimStatusPage payerPortalClaimStatusPage =new PayerPortalClaimStatusPage(driver);
	
	@Then("User Search claims by member and dateofservice")
	public void user_Search_claims_by_member_and_dateofservice() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		payerPortalClaimStatusPage.usersearchclaimsbySSN();
	}
	@Then("User Search using SSN")
	public void user_Search_using_SSN() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		payerPortalClaimStatusPage.usersearchclaimsbySSN();
		}

	@Then("User search the Claims for last one year in payer portal and checks if {string}")
	public void user_search_the_Claims_for_last_one_year_in_payer_portal_and_checks_if(String alertpresent) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		payerPortalClaimStatusPage.usersearchesclaimsforlastoneyear(alertpresent);
	}

	@Then("user selects the {string} from Claim Summary list if {string}")
	public void user_selects_the_from_Claim_Summary_list_if(String claimnumber,String alertpresent) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		payerPortalClaimStatusPage.userselectsclaimnumber(claimnumber,alertpresent);
	}

	@Then("user validates the {string} in Claim Information page if {string}")
	public void user_validates_the_in_Claim_Information_page_if(String claimnumber,String alertpresent) {
	    // Write code here that turns the phrase above into concrete actions
		payerPortalClaimStatusPage.userValidatesclaimnumberinclaiminformationpage(claimnumber,alertpresent);
	}

	@Then("user validates print option if {string}")
	public void user_validates_print_option_if(String alertpresent) {
	    // Write code here that turns the phrase above into concrete actions
		payerPortalClaimStatusPage.validateprintoption(alertpresent);
	}

	@Then("user search using {string} if {string}")
	public void user_search_using(String claimnumber,String alertpresent) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		payerPortalClaimStatusPage.usersearchusingClaimnumber(claimnumber,alertpresent);
	}

}