package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.ProviderAddCOBInformationPage;
import com.qa.pages.ProviderBenefitAdminManualPage;

import io.cucumber.java.en.Then;

public class ProviderAddCOBInformationPageSteps extends TestBase {
	
	ProviderAddCOBInformationPage providerAddCOBInformationPage = new ProviderAddCOBInformationPage(driver);
	Properties prop = initialize_properties();
	
	


@Then("user enters the COB information in the Add COB Information page if {string}")
public void user_enters_the_COB_information_in_the_Add_COB_Information_page_if(String COBAvailable) throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
	providerAddCOBInformationPage.UserEnterstheCOBInformationinAddInformationPageandValidates(COBAvailable);
}
	
}
