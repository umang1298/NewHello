package com.qa.stepDefinitions;

import java.awt.AWTException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.HapConnectivityPage;

import io.cucumber.java.en.Then;

public class HapConnectivitySteps extends TestBase {

	HapConnectivityPage hapConnectivityPage=new HapConnectivityPage(driver);
	Properties prop = initialize_properties();
@Then("user validates Shop for Coverage for {string}")
public void user_validates_Shop_for_Coverage_for(String type) throws InterruptedException, AWTException {
    // Write code here that turns the phrase above into concrete actions
	hapConnectivityPage.validateShopforCoverage(type);
}
}