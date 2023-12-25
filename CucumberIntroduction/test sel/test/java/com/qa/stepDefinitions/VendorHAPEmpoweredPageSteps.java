package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.VendorHAPEmpoweredPage;

import io.cucumber.java.en.Then;

public class VendorHAPEmpoweredPageSteps extends TestBase{

	VendorHAPEmpoweredPage vendorHAPEmpoweredPage = new VendorHAPEmpoweredPage(driver);
	Properties prop = initialize_properties();
	

@Then("user enters {string} on HAP empowered page")
public void user_enters_on_HAP_empowered_page(String BillingProviderNPI) {
    // Write code here that turns the phrase above into concrete actions
    vendorHAPEmpoweredPage.userenterBillingProviderNPI(BillingProviderNPI);
}

@Then("user clicks search on HAP empowered page")
public void user_clicks_search_on_HAP_empowered_page() {
    // Write code here that turns the phrase above into concrete actions
    vendorHAPEmpoweredPage.userclicksonsearch();
}

@Then("user validates search {string} are displayed for MW claims")
public void user_validates_search_are_displayed_for_MW_claims(String ResultAvailable) throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	vendorHAPEmpoweredPage.uservalidatessearchresultsaredispalyedforMWClaims(ResultAvailable);
}

@Then("user clicks on {string} in the MW claims search results if {string}")
public void user_clicks_on_in_the_MW_claims_search_results_if(String ClaimNumber, String ResultAvailable) throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	vendorHAPEmpoweredPage.userclicksonclaimIDinResults(ClaimNumber,ResultAvailable);
}

@Then("validates whether it takes user to Vendor MW Claim Details Page of {string} if {string}")
public void validates_whether_it_takes_user_to_Vendor_MW_Claim_Details_Page_of_if(String ClaimNumber, String ResultAvailable) {
    // Write code here that turns the phrase above into concrete actions
	vendorHAPEmpoweredPage.uservalidateswhetherclaimnumberlinktakesusertoMWclaimdetailspage(ClaimNumber,ResultAvailable);
}

}

