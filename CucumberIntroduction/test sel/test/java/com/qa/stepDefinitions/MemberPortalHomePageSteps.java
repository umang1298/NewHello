package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployerPortalLandingPage;
import com.qa.pages.MemberPortalHomePage;

import io.cucumber.java.en.Then;

public class MemberPortalHomePageSteps extends TestBase {

	MemberPortalHomePage memberPortalHomePage = new MemberPortalHomePage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("user verifies {string} widget is displayed")
	public void user_verifies_widget_is_displayed(String Widgetname) {
	    // Write code here that turns the phrase above into concrete actions
		memberPortalHomePage.VerifyWidgetsDisplayed(Widgetname);
	}

	@Then("user verifies clicking {string} link takes user to {string} page")
	public void user_verifies_clicking_link_takes_user_to_page(String ChangePCPLink, String ProviderLookUp) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		memberPortalHomePage.VerifyChangePCPLink(ChangePCPLink, ProviderLookUp);
	}

	@Then("user verifies clicking {string} on {string} widget takes user to {string} page")
	public void user_verifies_clicking_on_widget_takes_user_to_page(String viewmore, String widgetname, String targetpage) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		memberPortalHomePage.VerifyViewmoreFunctionalityonHomePage(viewmore, widgetname,targetpage);
	}
	
}
