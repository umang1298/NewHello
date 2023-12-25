package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import com.qa.base.TestBase;
import com.qa.pages.EmployerSubGroupListPage;
import com.qa.pages.ForgotPasswordPage;

import io.cucumber.java.en.Then;

public class ForgotPasswordPageSteps extends TestBase{

	
	ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
	Properties prop = initialize_properties();
	
	@Then("user selects {string} for the question in the forgot password page")
	public void user_selects_for_the_question_in_the_forgot_password_page(String yesorno) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.userselectsyesornoforthequestion(yesorno);
	}
	
	@Then("user enters all required values in forgot password page based on {string} and response xml {string}")
	public void user_enters_all_required_values_in_forgot_password_page_based_on_and_response_xml(String MemberRecordNumber, String ResponseXMLPath) throws IOException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.userentersrequiredvaluesinforgotpwdpageformemberfromresponsexml(MemberRecordNumber,ResponseXMLPath);
	}
	
	@Then("user enters the security {string} and clicks submit")
	public void user_enters_the_security_and_clicks_submit(String SecurityAnswer) {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.userentersSecurityAnswerandClickssubmit(SecurityAnswer);
	}
	

	@Then("user sets the new password for the member")
	public void user_sets_the_new_password_for_the_member() {
    // Write code here that turns the phrase above into concrete actions
	forgotPasswordPage.usersetsnewpasswordformember();
	}

	@Then("user enters wrong security answer")
	public void user_enters_wrong_security_answer() {
    // Write code here that turns the phrase above into concrete actions
	forgotPasswordPage.userenterswrongsecurityanswer();
}
	@Then("user enters Registerd username {string}")
	public void user_enters_Registerd_username(String username) {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.Userentersusername(username);
	}

	
	@Then("user enters wrong security answer to unregister prospective member {string} and {string}")
	public void user_enters_wrong_security_answer_to_unregister_prospective_member_and(String DOB, String SSN) {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.Prospectiveuserenterswrongsecurityanswer(DOB,SSN);
	}
	
	@Then("user enters {string} and {string} and wrong security answer to unregister the producer")
	public void user_enters_and_and_wrong_security_answer_to_unregister_the_producer(String Email, String SSN) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.unregisterProducer(Email,SSN);
	}

	
	@Then("user enters Primary {string} in email ID TextBox")
	public void user_enters_Primary_in_email_ID_TextBox(String Email) {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.userentersPrimaryEmail(Email);
	}

	
	@Then("user enters the security {string} for Provider and clicks submit")
	public void user_enters_the_security_for_Provider_and_clicks_submit(String SecurityAnswer) {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.userentersSecurityAnswerforProviderandClickssubmit(SecurityAnswer);
	}
	
	@Then("user sets the new password for the provider")
	public void user_sets_the_new_password_for_the_provider() {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.usersetsnewpasswordforProvider();
	}
	
	@Then("user enters Primary {string} in the email ID TextBox and enters SSN {string} in SSN Text Box")
	public void user_enters_Primary_in_the_email_ID_TextBox_and_enters_SSN_in_SSN_Text_Box(String Email, String SSN) {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.userentersPrimaryEmailandSSN(Email,SSN);
	}
	
	@Then("user enters the security {string} for Producer and clicks submit")
	public void user_enters_the_security_for_Producer_and_clicks_submit(String securityAnswer) {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.userentersSecurityanswerandclicksonSubmitButton(securityAnswer);
	}
	
	@Then("user sets the new password for the producer")
	public void user_sets_the_new_password_for_the_producer() {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.usersetsnewpasswordforProducer();
	}
	@Then("user enters the {string} and {string} of prospective user {string}")
	public void user_enters_the_and_of_prospective_user(String dob, String ssn,String SecurityAnswer) {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.userentersDOBandSSN(dob,ssn,SecurityAnswer);
	}
	@Then("user sets the new password for the prospective member")
	public void user_sets_the_new_password_for_the_prospective_member() {
	    // Write code here that turns the phrase above into concrete actions
		forgotPasswordPage.usersetsnewpasswordforProspectiveMember();
	}
}
