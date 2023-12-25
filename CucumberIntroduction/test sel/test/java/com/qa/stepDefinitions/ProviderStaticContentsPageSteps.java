package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderReferralSearchPage;
import com.qa.pages.ProviderStaticContentsPage;

import io.cucumber.java.en.Then;

public class ProviderStaticContentsPageSteps extends TestBase{
	
	ProviderStaticContentsPage providerStaticContentsPage = new ProviderStaticContentsPage(driver);
	Properties prop = initialize_properties();

	@Then("user verifies {string} link is present in the Links Panel")
	public void user_verifies_link_is_present_in_the_Links_Panel(String QuickLink) {
	    // Write code here that turns the phrase above into concrete actions
		providerStaticContentsPage.UserValidatesQuickLinksisDisplayed(QuickLink);
	}
	
	@Then("user Close the Links Panel")
	public void user_Close_the_Links_Panel() {
	    // Write code here that turns the phrase above into concrete actions
		providerStaticContentsPage.UserClosestheLinksPanel();
	}
	
	@Then("user Close the Related Links Panel")
	public void user_Close_the_Related_Links_Panel() {
	    // Write code here that turns the phrase above into concrete actions
		providerStaticContentsPage.UserClosesRelatedLinkPanel();
	}
	
	@Then("user validates {string} is present in the Resources page")
	public void user_validates_is_present_in_the_Resources_page(String Link) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		providerStaticContentsPage.UserValidatesLinksArepresentinresources(Link);
	}
	
	@Then("user expands {string}")
	public void user_expands(String HeadingName) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		providerStaticContentsPage.UserExpandsHeading(HeadingName);
	}
}

