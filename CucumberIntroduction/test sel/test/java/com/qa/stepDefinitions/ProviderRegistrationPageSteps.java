package com.qa.stepDefinitions;

import java.util.List;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderReferralSearchPage;
import com.qa.pages.ProviderRegistrationPage;

import io.cucumber.java.en.Then;

public class ProviderRegistrationPageSteps extends TestBase{

	ProviderRegistrationPage providerRegistrationPage = new ProviderRegistrationPage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("user selects option for Tell us about organisation:")
	public void user_selects_option_for_Tell_us_about_organisation(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		List<List<String>> listofOptionsforTellUsAboutOrganisation = dataTable.asLists(String.class);
		String OptionForContracted = listofOptionsforTellUsAboutOrganisation.get(1).get(0);
		String OptionForOfficeType = listofOptionsforTellUsAboutOrganisation.get(1).get(1);
		String OptionForRA = listofOptionsforTellUsAboutOrganisation.get(1).get(2);
		providerRegistrationPage.userentersoptionsforTellUsAboutOrganisation(OptionForContracted,OptionForOfficeType,OptionForRA);
	}
	
	@Then("user fill details for your information section with {string} and {string} and {string} based on {string}")
	public void user_fill_details_for_your_information_section_with_and_and_based_on(String NPI, String TaxId, String VendorId, String Type) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		providerRegistrationPage.userfillsyourinformationforProviderRegistration(NPI,TaxId,VendorId,Type);
	}
	
	@Then("user enters details for ID Administration Information and Consent")
	public void user_enters_details_for_ID_Administration_Information_and_Consent() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		providerRegistrationPage.userfillsdetailsforIDadministrationandConsent();
	}
	
	@Then("user deletes the record from Manage Users Table for {string} and {string}")
	public void user_deletes_the_record_from_Manage_Users_Table_for_and(String NPI, String VendorId) {
	    // Write code here that turns the phrase above into concrete actions
		providerRegistrationPage.userdeletesrecordfromManageUsersTable(NPI,VendorId);
	}
}
