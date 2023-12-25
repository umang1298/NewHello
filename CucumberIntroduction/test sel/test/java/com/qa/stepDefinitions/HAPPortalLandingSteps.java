package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.HAPPortalLandingPage;
import com.qa.util.CommonMethods;

import io.cucumber.java.en.Then;

public class HAPPortalLandingSteps extends TestBase{

	HAPPortalLandingPage hAPPortalLandingPage = new HAPPortalLandingPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user verifies the HAP logo and the LogOut Button")
	public void user_verifies_the_HAP_logo_and_the_LogOut_Button() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    
		hAPPortalLandingPage.verifyHAPLogoandLogOut();
		
	}
	
	@Then("user verifies the HAP logo and LogOut for Agent portal")
	public void user_verifies_the_HAP_logo_and_LogOut_for_Agent_portal() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    
		hAPPortalLandingPage.verifyHAPLogoandLogOutForAgent();
		
	}
	
	@Then("user waits for {int} ms")
	public void user_waits_for_ms(Integer int1) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	   CommonMethods.ThreadSleep(int1);
	}
	
	@Then("user verifies the HAP Doctor")
	public void user_verifies_the_HAP_Doctor() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    
		hAPPortalLandingPage.verifyHAPDoctor();
		
	}
	@Then("user verifies label the HAP Welcome")
	public void user_verifies_the_HAP_Welcome() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    
		hAPPortalLandingPage.verifyHAPWelcome();
		
	}
	
	

@Then("user navigates to the {string}")
public void user_navigates_to_the(String MenuOptionToPass) throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	hAPPortalLandingPage.userClickOnOneOfTheMenusFromTheLandingPage(MenuOptionToPass);


}


@Then("user Logouts the session")
public void user_Logouts_the_session() throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
    hAPPortalLandingPage.userlogout();
}


@Then("user navigates to the {string} and validates the CareAffiliate page is displayed")
public void user_navigates_to_the_and_validates_the_CareAffiliate_page_is_displayed(String string) throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
    hAPPortalLandingPage.UserClickontheAuthorizationsFromTheLandingpage();
}

@Then("user clicks on Quick Links option in the Portal")
public void user_clicks_on_Quick_Links_option_in_the_Portal() throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	hAPPortalLandingPage.UserClicksonQuickLinks();
}

@Then("user Clicks on Related Links option in the Portal")
public void user_Clicks_on_Related_Links_option_in_the_Portal() throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	hAPPortalLandingPage.UserClicksOnReleatedLinks();
}

@Then("user Clicks on Resources link in the Home Page")
public void user_Clicks_on_Resources_link_in_the_Home_Page() {
    // Write code here that turns the phrase above into concrete actions
	hAPPortalLandingPage.UserClicksonResourcesLink();
}

@Then("user navigates to {string}")
public void user_navigates_to(String Menuoption) throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	hAPPortalLandingPage.UserClicksontheManagerUsers(Menuoption);
}

@Then("user navigates to the {string} page")
public void user_navigates_to_the_page(String paperlessoption) {
    // Write code here that turns the phrase above into concrete actions
	hAPPortalLandingPage.usernavigatestothepaperlessoption(paperlessoption);
}

@Then("user close browser")
public void user_close_browser() {
    // Write code here that turns the phrase above into concrete actions
	CommonMethods.CloseBrowser();
}

@Then("user navigates to the {string} Page")
public void user_navigates_to_the_Page(String UpdateProfile) {
    // Write code here that turns the phrase above into concrete actions
	hAPPortalLandingPage.UserNavigatesToTheUpdateProfile(UpdateProfile);
}

@Then("user logs out")
public void user_logs_out() {
    // Write code here that turns the phrase above into concrete actions
	hAPPortalLandingPage.UserLogsOut();
}

@Then("user logs out the Prospective member page")
public void user_logs_out_the_Prospective_member_page() throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	hAPPortalLandingPage.userlogsoutofprospectivemember();
}

}
