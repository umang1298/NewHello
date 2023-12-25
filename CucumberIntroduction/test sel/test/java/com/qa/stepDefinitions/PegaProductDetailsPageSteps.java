package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.PegaProductDetailsPage;

import io.cucumber.java.en.Then;

public class PegaProductDetailsPageSteps extends TestBase{

	
	PegaProductDetailsPage pegaProductDetailsPage = new PegaProductDetailsPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user clicks on first result")
	public void user_clicks_on_first_result() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		pegaProductDetailsPage.userclicksonfirstdocumentlink();
	}
	
	@Then("user Downloads PDF")
	public void user_Downloads_PDF() {
	    // Write code here that turns the phrase above into concrete actions
		pegaProductDetailsPage.userdownloadsPDF();
	}
	

}
