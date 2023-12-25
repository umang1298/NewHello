package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderClaimsPage;
import com.qa.pages.VendorClaimsPage;

import io.cucumber.java.en.Then;

public class VendorClaimsPageSteps extends TestBase {

	VendorClaimsPage vendorClaimsPage = new VendorClaimsPage(driver);
	Properties prop = initialize_properties();


@Then("user clicks on HAP Empowered Historical Information button")
public void user_clicks_on_HAP_Midwest_button() {
    // Write code here that turns the phrase above into concrete actions
    vendorClaimsPage.userclicksonHAPMidwestbutton();
}
	
@Then("user clicks on For Trusted HP button and validates user gets navigates to the Trusted Health Plan Home page")
public void user_clicks_on_For_Trusted_HP_button_and_validates_user_gets_navigates_to_the_Trusted_Health_Plan_Home_page() throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	vendorClaimsPage.userclicksonTrustedHPButton();
}
}
