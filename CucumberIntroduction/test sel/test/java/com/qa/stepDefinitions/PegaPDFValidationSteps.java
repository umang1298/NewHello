package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.PegaPDFValidation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class PegaPDFValidationSteps extends TestBase{

	PegaPDFValidation pegaPDFValidation = new PegaPDFValidation(driver);
	Properties prop = initialize_properties();

	@Given("User Compares the Reference PDF {string} and PDF downloaded {string}")
	public void user_Compares_the_Reference_PDF_and_PDF_downloaded(String ReferencePDF, String DownloadedPDF) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		pegaPDFValidation.ValidatePEGAPDF(ReferencePDF,DownloadedPDF);
		//pegaPDFValidation.ComparePDFandgeneratereport(ReferencePDF, DownloadedPDF);
	}
	
	@Then("user checks the PDF is downloaded successfully {string}")
	public void user_checks_the_PDF_is_downloaded_successfully(String DownloadPDF) {
	    // Write code here that turns the phrase above into concrete actions
	    pegaPDFValidation.userchecksPDFdownloadedsuccessfully(DownloadPDF);
	}

}

