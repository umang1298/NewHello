package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.qa.base.TestBase;
import com.qa.pages.FormularlyExceptionFormPage;

import io.cucumber.java.en.Then;

public class FormularlyExceptionFormSteps extends TestBase {


	FormularlyExceptionFormPage formularlyExceptionFormPage=new FormularlyExceptionFormPage(driver);

	Properties prop = initialize_properties();

@Then("user validates Formulary Exception Form {string}")
public void user_validates_Formulary_Exception_Form(String ResponseXMLpath) throws IOException {
    // Write code here that turns the phrase above into concrete actions
	formularlyExceptionFormPage.validateformularlyExceptionpage(ResponseXMLpath);
}
}