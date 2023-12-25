package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.GoPaperlessPage;
import com.qa.pages.MemberProgramConsentsPage;

import io.cucumber.java.en.Then;

public class MemberProgramConsentsPageSteps extends TestBase {
	MemberProgramConsentsPage memberProgramConsentsPage = new MemberProgramConsentsPage(driver);
	Properties prop = initialize_properties();


	
	@Then("user validates program consents page and Access history")
	public void user_validates_program_consents_page_and_Access_history() {
	    // Write code here that turns the phrase above into concrete actions
		memberProgramConsentsPage.valiadateAccess_history();
	}

}
