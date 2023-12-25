package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.qa.base.TestBase;
import com.qa.pages.APIHealthCheckMethods;
import com.qa.pages.APIMethods;
import com.qa.util.RestClient;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class APIHealthCheckSteps extends TestBase{

	
	APIHealthCheckMethods aPIHealthCheckMethods;
	RestClient restClient;
	
	@Then("user sends a SOAP request and validates response success or Not:")
	public void user_sends_a_SOAP_request_and_validates_response_success_or_Not(io.cucumber.datatable.DataTable dataTable) throws ClientProtocolException, IOException {
		aPIHealthCheckMethods = new APIHealthCheckMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String Endpoint = list.get(1).get(0);
		String PayLoadPath = list.get(1).get(1);
		aPIHealthCheckMethods.SendRequestandvalidateresponse(Endpoint,PayLoadPath);
	}
	
	@Given("user sets up the SOAP request xml for member deductible service:")
	public void user_sets_up_the_SOAP_request_xml_for_member_deductible_service(io.cucumber.datatable.DataTable dataTable) throws IOException {
		aPIHealthCheckMethods = new APIHealthCheckMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String ProductId = list.get(1).get(0);
		String ContrivedKey = list.get(1).get(1);
		String Dateval = list.get(1).get(2);
		String PayLoadPath = list.get(1).get(3);
		aPIHealthCheckMethods.setupSOAPRequestforMemberDeductibleService(ProductId, ContrivedKey, Dateval, PayLoadPath);
	}

	@Given("user sets up the SOAP request xml for Remit Advice service:")
	public void user_sets_up_the_SOAP_request_xml_for_Remit_Advice_service(io.cucumber.datatable.DataTable dataTable) throws IOException {
		aPIHealthCheckMethods = new APIHealthCheckMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String PaymentNumber = list.get(1).get(0);
		String Dateval = list.get(1).get(1);
		String PayLoadPath = list.get(1).get(2);
		aPIHealthCheckMethods.setupSOAPRequestforRemitAdvicePaymentSearch(PaymentNumber,Dateval, PayLoadPath);
	}
	
}
