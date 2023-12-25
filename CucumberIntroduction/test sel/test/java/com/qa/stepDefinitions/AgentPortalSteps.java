package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.AgentPortalPage;

import io.cucumber.java.en.Then;

public class AgentPortalSteps extends TestBase {

	AgentPortalPage agentPortalPage = new AgentPortalPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user navigates to Agen Portal {string}")
	public void user_navigates_to_Agen_Portal(String menuoptiontopass) {
	    // Write code here that turns the phrase above into concrete actions
	   agentPortalPage.clickontheAgentPortalmenu(menuoptiontopass);
	}
	@Then("user Verifies Agent Portal menu Link")
	public void user_Verifies_Agent_Portal_menu_Link() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	   agentPortalPage.validateAgentPortalmenupage();
	}
	@Then("user validates Announcement page")
	public void user_validates_Announcement_page() {
	    // Write code here that turns the phrase above into concrete actions
		agentPortalPage.validateAgentAnnouncementpage();
		
	}

	@Then("user validates Search Functionalty")
	public void user_validates_Search_Functionalty() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    agentPortalPage.validateAgentPortalSearch();
	}
	@Then("user navigates to {string} and validates {string} Page")
	public void user_navigates_to_and_validates_Page(String planName, String linkname) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		agentPortalPage.ValidatelinkleadstoRespectivePage(planName,linkname);
	}

	@Then("user navigates to {string} and validates {string} document downloaded")
	public void user_navigates_to_and_validates_document_downloaded(String planName, String linkname) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		agentPortalPage.validatedocumentgetdownloaded(planName,linkname);
	}

	@Then("user navigates to {string} and validates {string}")
	public void user_navigates_to_and_validates(String planName, String linkname) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	 

		agentPortalPage.clickandvalidatetheLink(planName,linkname);
	}
	@Then("user navigates to {string} and validates {string} pdf document")
	public void user_navigates_to_and_validates_pdf_document(String planName, String linkname) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		agentPortalPage.validateslinkcontainspdf(planName,linkname);
	}

	@Then("user navigates to {string} and validates {string} Quoting Page")
	public void user_navigates_to_and_validates_Quoting_Page(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	}

}
