package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.MemberPortalPayMyBillPage;

import io.cucumber.java.en.Then;

public class MemberPortalPayMyBillSteps extends TestBase{
	
	MemberPortalPayMyBillPage memberPortalPayMyBillPage=new MemberPortalPayMyBillPage(driver);
	Properties prop = initialize_properties();


	@Then("user clicks on Pay my Bill {string}")
	public void user_clicks_on_Pay_my_Bill(String Links) {
	    // Write code here that turns the phrase above into concrete actions
		memberPortalPayMyBillPage.clickonpaymybillLinks(Links);
	}
	@Then("user validates Manage Auto pay option")
	public void user_validates_Manage_Auto_pay_option() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		memberPortalPayMyBillPage.validatesAutoPayOption();
	}
	@Then("user validates Pay Invoice based on {string} response")
	public void user_validates_Pay_Invoice_based_on_response(String RestResponse) throws InterruptedException, IOException {
	    // Write code here that turns the phrase above into concrete actions
	    memberPortalPayMyBillPage.validatePayInvoice(RestResponse);
	}

	@Then("user validates Manage Method of Payment")
	public void user_validates_Manage_Method_of_Payment() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		memberPortalPayMyBillPage.validatePaymentMethod();
	}

	
		
}