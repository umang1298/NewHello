package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.MemberEligibilityPage;

import io.cucumber.java.en.Then;


public class MemberEligibilityPageSteps extends TestBase{

	MemberEligibilityPage memberEligibilityPage = new MemberEligibilityPage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("user clicks on {string} link")
	public void user_clicks_on_link(String MemberRoster) {
	    // Write code here that turns the phrase above into concrete actions
	    memberEligibilityPage.userClicksMemerRosterLink(MemberRoster);
	}
	
	@Then("user enters {string} and Clicks on submit")
	public void user_enters_and_Clicks_on_submit(String MemberID) {
	    // Write code here that turns the phrase above into concrete actions
	    memberEligibilityPage.entermemberid(MemberID);
	    memberEligibilityPage.ClickSubmit();
	}
	
	@Then("user enters {string} and checks Display Contract history and Clicks on submit")
	public void user_enters_and_checks_Display_Contract_history_and_Clicks_on_submit(String MemberID) {
	    // Write code here that turns the phrase above into concrete actions
		 memberEligibilityPage.entermemberid(MemberID);
		 memberEligibilityPage.checkdisplayContractHistory();
		    memberEligibilityPage.ClickSubmit();
	}

	
	@Then("user enters {string} and {string} and {string} and clicks on submit")
	public void user_enters_and_and_and_clicks_on_submit(String MemberID1, String MemberID2, String MemberID3) {
	    // Write code here that turns the phrase above into concrete actions
	    
		memberEligibilityPage.entermultiplememberIDs(MemberID1, MemberID2, MemberID3);
		memberEligibilityPage.ClickSubmit();
	}
}
