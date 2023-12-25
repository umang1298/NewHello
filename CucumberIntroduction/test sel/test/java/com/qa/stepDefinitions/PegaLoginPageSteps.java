package com.qa.stepDefinitions;

import java.sql.SQLException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.qa.base.TestBase;
import com.qa.pages.PegaLandingPage;
import com.qa.pages.PegaLoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

public class PegaLoginPageSteps extends TestBase{

	TestBase base;
	static Properties prop;
	PegaLoginPage pegaLoginPage;
	PegaLandingPage pegaLandingPage;
	
	
	@Given("user logs in to the Pega Application with {string} and {string}")
	public void user_logs_in_to_the_Pega_Application_with_and(String UserID, String Password) {
	    // Write code here that turns the phrase above into concrete actions
		pegaLoginPage = new PegaLoginPage(driver);
		pegaLandingPage = pegaLoginPage.LogintoApp(UserID, Password);
	}
	
	}
