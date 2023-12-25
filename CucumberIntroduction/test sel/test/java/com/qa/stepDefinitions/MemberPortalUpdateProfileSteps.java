package com.qa.stepDefinitions;

import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.MemberPortalUpdateProfilePage;

import io.cucumber.java.en.Then;


public class MemberPortalUpdateProfileSteps extends TestBase {

	MemberPortalUpdateProfilePage memberportalupdateprofilepage = new MemberPortalUpdateProfilePage(driver);
	Properties prop = initialize_properties();
	

	@Then("user updates Email ID {string} and validates the provided information displayed correctly")
	public void user_updates_Email_ID_and_validates_the_provided_information_displayed_correctly(String email) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		memberportalupdateprofilepage.userupdatesandvalidatestheprofilesecurityinformation(email);
	}
	
	
	@Then("user updates Phone number and validates profile security information are displayed correctly")
	public void user_updates_Phone_number_and_validates_profile_security_information_are_displayed_correctly() {
	    // Write code here that turns the phrase above into concrete actions
		memberportalupdateprofilepage.userupdatesphonenmberandvalidatestheprofilesecurityinformation();
	}
	
	@Then("user updates Challenge question {string} and validate security quesstion are updated successfull")
	public void user_updates_Challenge_question_and_validate_security_quesstion_are_updated_successfull(String ChallengeQuestion) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		memberportalupdateprofilepage.userupdateschallengequestionandvalidatestheprofilesecurityinformation(ChallengeQuestion);
	}
	
	@Then("user updates Email ID {string} and validates information displayed correctly")
	public void user_updates_Email_ID_and_validates_information_displayed_correctly(String email) {
	    // Write code here that turns the phrase above into concrete actions
		memberportalupdateprofilepage.userupdatesemailidandvalidatestheinformation(email);
	}
	
	@Then("user sign ups for paperless delivery of documents and validates the delivery of document type")
	public void user_sign_ups_for_paperless_delivery_of_documents_and_validates_the_delivery_of_document_type() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		memberportalupdateprofilepage.usersignupforthedeliveryofdocumentsandvalidates();
	}

	
	@Then("user enters {string} and  updates a {string} and {string} and validates the password are updated")
	public void user_enters_and_updates_a_and_and_validates_the_password_are_updated(String password, String newpassword, String confirmpassword) {
	    // Write code here that turns the phrase above into concrete actions
		memberportalupdateprofilepage.userupdatesthepasswordandvalidatesthepasswordareupdated(password,newpassword,confirmpassword);
	}
	
	
	
}
