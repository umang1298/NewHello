package com.qa.stepDefinitions;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.HAPPortalLandingPage;
import com.qa.pages.MyBenefitsPage;
import com.qa.pages.MyFormsAndDocumentsPage;
import io.cucumber.java.en.Then;
public class MyBenefitsPageSteps extends TestBase{
	
	MyBenefitsPage mybenefitspage = new MyBenefitsPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user clicks option {string}")
	public void user_clicks_option(String MenuoptiontoPass) {
	    // Write code here that turns the phrase above into concrete actions
	   mybenefitspage.Userclicksoneofthemenusfrommybenefitspage(MenuoptiontoPass);
	}

	@Then("user verifies EyeMed page")
	public void user_verifies_EyeMed_page() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		 mybenefitspage.userVerifiesEyemedPage();
	}
}
