package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.PegaGenerateDocumentsPage;

import io.cucumber.java.en.Then;


public class PegaGenerateDocumentsPageSteps extends TestBase{

	PegaGenerateDocumentsPage pegaGenerateDocumentsPage = new PegaGenerateDocumentsPage(driver);
	Properties prop = initialize_properties();
	
	
	@Then("user gives {string} input in the {string} text box")
	public void user_gives_input_in_the_text_box(String Product, String Field) {
	    // Write code here that turns the phrase above into concrete actions
		pegaGenerateDocumentsPage.userentersproductidinthetextbox(Product,Field);
	}
	

@Then("user gives {string} and {string} input in the {string} and {string} text box")
public void user_gives_and_input_in_the_and_text_box(String MedicalProduct, String PharmacyProduct, String MedicalTextBox, String RxTextBox) {
    // Write code here that turns the phrase above into concrete actions
	pegaGenerateDocumentsPage.userentersproductidinthetextbox(MedicalProduct, PharmacyProduct, MedicalTextBox, RxTextBox);
}
	
	@Then("user selects the checkbox for {string}")
	public void user_selects_the_checkbox_for(String Documenttype) {
	    // Write code here that turns the phrase above into concrete actions
		pegaGenerateDocumentsPage.userselectsthecheckbox(Documenttype);
	}
	
	@Then("user clicks Generate Documents")
	public void user_clicks_Generate_Documents() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		pegaGenerateDocumentsPage.clickGenrateDocuments();
	}
}
