package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.MyCareMenuPage;
import com.qa.pages.MyHealthRemainderPage;

import io.cucumber.java.en.Then;

public class MyHealthRemainderSteps extends TestBase{

	MyHealthRemainderPage myHealthRemainderPage = new MyHealthRemainderPage(driver);
	Properties prop = initialize_properties();

@Then("user validates MyHealthRemainders from {string}")
public void user_validates_MyHealthRemainders_from(String ResponseXML) throws IOException {
    // Write code here that turns the phrase above into concrete actions
myHealthRemainderPage.validatesMyHealthRemainderPage(ResponseXML);  

}
}
