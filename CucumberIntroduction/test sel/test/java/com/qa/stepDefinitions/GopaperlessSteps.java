package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.GoPaperlessPage;

import io.cucumber.java.en.Given;
public class GopaperlessSteps extends TestBase{

	GoPaperlessPage gopaperlessPagePage = new GoPaperlessPage(driver);
	Properties prop = initialize_properties();

	@Given("user validates ContactInformation in Gopaperless application")
	public void user_validates_ContactInformation_in_Gopaperless_application() {
	    // Write code here that turns the phrase above into concrete actions
		gopaperlessPagePage.uservalidatescontactInformation();
	}
	@Given("user validates Paperless in Gopaperless application")
	public void user_validates_Paperless_in_Gopaperless_application() {
	    // Write code here that turns the phrase above into concrete actions
		gopaperlessPagePage.uservalidatespaperless();
	}
	@Given("user gets {string} and {string} and {string} values")
	public void user_gets_and_and_values(String email, String EOBcheck, String paperlesscheck) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		gopaperlessPagePage.usersetsthevalues(email,EOBcheck,paperlesscheck);
	}
	@Given("user validates the updated {string} and {string} and {string} saved properly")
	public void user_validates_the_updated_and_and_saved_properly(String email, String EOBcheck, String paperlesscheck) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		gopaperlessPagePage.uservalidatestheupdatedinformation(email,EOBcheck,paperlesscheck);
	}
}
