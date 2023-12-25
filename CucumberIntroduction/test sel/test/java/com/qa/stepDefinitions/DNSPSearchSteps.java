package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.DNSPSearchPage;
import com.qa.pages.PayerPortalClaimStatusPage;

import io.cucumber.java.en.Then;

public class DNSPSearchSteps extends TestBase {
	
	DNSPSearchPage dNSPSearchPage =new DNSPSearchPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user selects {string} and search with {string}")
	public void user_selects_and_search_with(String Providernamemenu, String Providername) {
	    // Write code here that turns the phrase above into concrete actions
		dNSPSearchPage.passvalueintextboxandclickonsearchbutton(Providernamemenu,Providername);
	}
	@Then("user validates Provider name search result page")
	public void user_validates_Provider_name_search_result_page() {
	    // Write code here that turns the phrase above into concrete actions
		dNSPSearchPage.validateprovidnamesearchresult();
	}
	
	@Then("user Run the DNSP Traning Report")
	public void user_Run_the_DNSP_Traning_Report() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		dNSPSearchPage.runtheTrainingReport();
	}

	@Then("user Save the DNSP Traning Report")
	public void user_Save_the_DNSP_Traning_Report() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		dNSPSearchPage.savetheTrainingReport();
	}

	@Then("user validates saved Target list name and delete from the list")
	public void user_validates_saved_Target_list_name_and_delete_from_the_list() {
	    // Write code here that turns the phrase above into concrete actions
		
		dNSPSearchPage.deletetheTrainingReport();
	}
	@Then("user down the DNSP Training Report")
	public void user_down_the_DNSP_Training_Report() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		
		dNSPSearchPage.downloadtheTrainingReport();
	}
	@Then("user validates the DNSP Dashboard")
	public void user_validates_the_DNSP_Dashboard() {
	    // Write code here that turns the phrase above into concrete actions
		dNSPSearchPage.validatesDashboard();
	}


}
