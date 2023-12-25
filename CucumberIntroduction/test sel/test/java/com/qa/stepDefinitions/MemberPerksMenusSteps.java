package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.GoPaperlessPage;
import com.qa.pages.MemberPerksMenusPage;

import io.cucumber.java.en.Then;

public class MemberPerksMenusSteps extends TestBase {
	MemberPerksMenusPage memberPerksMenusPage = new MemberPerksMenusPage(driver);
	Properties prop = initialize_properties();

	
	@Then("user validates all the static content links under Member Perks Menu for {string}")
	public void user_validates_all_the_static_content_links_under_Member_Perks_Menu_for(String usertype) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	memberPerksMenusPage.ValidatingMemberPerksStaticlinks(usertype);
	
	}

}
