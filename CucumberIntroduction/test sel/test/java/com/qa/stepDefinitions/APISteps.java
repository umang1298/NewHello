package com.qa.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.qa.base.TestBase;
import com.qa.pages.APIMethods;
import com.qa.util.RestClient;
import com.qa.util.ScenarioContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class APISteps extends TestBase {

	APIMethods aPIMethods;
	RestClient restClient;
	
	@Given("user sets the SOAP request xml for MemberService:")
	public void user_sets_the_SOAP_request_xml_for_following(io.cucumber.datatable.DataTable dataTable) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		aPIMethods = new APIMethods();
		List<List<String>> listToSetUpXMLRequestBody = dataTable.asLists(String.class);
		String memberId = listToSetUpXMLRequestBody.get(1).get(0);
		String dateVal = listToSetUpXMLRequestBody.get(1).get(1);
		String XMLPath = listToSetUpXMLRequestBody.get(1).get(2);
		aPIMethods.setUpSOAPRequestForMemberService(memberId, dateVal, XMLPath);

	}
	
	@Given("user sets the SOAP request xml for Group:")
	public void user_sets_the_SOAP_request_xml_for_Group(io.cucumber.datatable.DataTable dataTable) throws IOException {
		aPIMethods = new APIMethods();  
		List<List<String>> listToSetUpXMLRequestBody = dataTable.asLists(String.class);
		String GroupID = listToSetUpXMLRequestBody.get(1).get(0);
		String dateVal = listToSetUpXMLRequestBody.get(1).get(1);
		String SubgroupId = listToSetUpXMLRequestBody.get(1).get(2);
		String XMLPathofGetProductsBySubGroup = listToSetUpXMLRequestBody.get(1).get(3);
		aPIMethods.setUpSOAPRequestForGetProductsBySubGroup(GroupID, dateVal, SubgroupId, XMLPathofGetProductsBySubGroup);
	
	
	}
	
	@Given("user sets the SOAP Request xml for Pega Service for PA QHP member:")
	public void user_sets_the_SOAP_Request_xml_for_Pega_Service_for_PA_QHP_member(io.cucumber.datatable.DataTable dataTable) throws IOException {
		aPIMethods = new APIMethods();  
		List<List<String>> listToSetUpXMLRequestBody = dataTable.asLists(String.class);
		
		String dateVal = listToSetUpXMLRequestBody.get(1).get(0);
		String XMLPath = listToSetUpXMLRequestBody.get(1).get(1);
		String GroupIDtoset = listToSetUpXMLRequestBody.get(1).get(2);
		String SubGroupIDtoset = listToSetUpXMLRequestBody.get(1).get(3);
		String MedicalProductIDtoset = listToSetUpXMLRequestBody.get(1).get(4);
		String PharmacyProductIDtoset = listToSetUpXMLRequestBody.get(1).get(5);
		
		aPIMethods.setUpSOAPRequestForPegaServiceforPAQHPMember(dateVal, XMLPath, GroupIDtoset, SubGroupIDtoset, MedicalProductIDtoset, PharmacyProductIDtoset);
	}

	@Given("user sets the SOAP Request xml for Pega Service for Health Check:")
	public void user_sets_the_SOAP_Request_xml_for_Pega_Service_for_Health_Check(io.cucumber.datatable.DataTable dataTable) throws IOException {
		aPIMethods = new APIMethods();  
		List<List<String>> listToSetUpXMLRequestBody = dataTable.asLists(String.class);
		
		String dateVal = listToSetUpXMLRequestBody.get(1).get(0);
		String XMLPath = listToSetUpXMLRequestBody.get(1).get(1);
		String GroupIDtoset = listToSetUpXMLRequestBody.get(1).get(2);
		String SubGroupIDtoset = listToSetUpXMLRequestBody.get(1).get(3);
		String MedicalProductIDtoset = listToSetUpXMLRequestBody.get(1).get(4);
		String PharmacyProductIDtoset = listToSetUpXMLRequestBody.get(1).get(5);
		
		aPIMethods.setUpSOAPRequestForPegaServiceforPAQHPEmployer(dateVal, XMLPath, GroupIDtoset, SubGroupIDtoset, MedicalProductIDtoset, PharmacyProductIDtoset);
	}
	


	
	@Given("user sends a SOAP request with the MemberService:")
	public void user_sends_a_SOAP_request_with_the_following(io.cucumber.datatable.DataTable dataTable) throws ClientProtocolException, IOException {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		aPIMethods = new APIMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String EndPointURL = list.get(1).get(0);
		String PathToXML = list.get(1).get(1);
		String XpathToProductID = list.get(1).get(2);
		String XPathToRenewalMonth = list.get(1).get(3);
		String XPathToBenefitStartDate = list.get(1).get(4);
		String XPathToBenefitEndDate = list.get(1).get(5);
		String KeyToStoreRenewalMonth = list.get(1).get(6);
		String KeyToStoreBenefitStartDate = list.get(1).get(7);
		String KeyToStoreBenefitEndDate = list.get(1).get(8);
		String KeyToStoreProductID = list.get(1).get(9);
		aPIMethods.sendASOAPRequest(EndPointURL, PathToXML, XpathToProductID, XPathToRenewalMonth, XPathToBenefitStartDate, XPathToBenefitEndDate, KeyToStoreRenewalMonth, KeyToStoreBenefitStartDate, KeyToStoreBenefitEndDate, KeyToStoreProductID );
				
	}

	@Given("user sends a SOAP request for GetProductsBySubgroup:")
	public void user_sends_a_SOAP_request_for_GetProductsBySubgroup(io.cucumber.datatable.DataTable dataTable) throws ClientProtocolException, IOException {
	 
		
		aPIMethods = new APIMethods();	
		List<List<String>> list = dataTable.asLists(String.class);
		String EndPointURL = list.get(1).get(0);
		String PathToXML = list.get(1).get(1);
		String XPathToRenewalMonth = list.get(1).get(2);
		String KeyToStoreRenewalMonth = list.get(1).get(3);
		aPIMethods.SendSOAPRequestforGetProductsBySubgroup(EndPointURL, PathToXML, XPathToRenewalMonth, KeyToStoreRenewalMonth);
		
	}
	
	
	@Given("user sends a SOAP request with the MemberService To get Product IDs:")
	public void user_sends_a_SOAP_request_with_the_MemberService_To_get_Product_IDs(io.cucumber.datatable.DataTable dataTable) throws ClientProtocolException, IOException {
	    
		aPIMethods = new APIMethods();	
		List<List<String>> list = dataTable.asLists(String.class);
		String EndPointURL = list.get(1).get(0);
		String PathToXML = list.get(1).get(1);
		String XPathToMedicalProduct = list.get(1).get(2);
		String XPathToPharmacyProduct = list.get(1).get(3);
		String KeyToStoreMedicalProduct = list.get(1).get(4);
		String KeyToStorePharmacyProduct = list.get(1).get(5);
		aPIMethods.SendSOAPRequesttogetProductIDs(EndPointURL, PathToXML, XPathToMedicalProduct, XPathToPharmacyProduct, KeyToStoreMedicalProduct, KeyToStorePharmacyProduct);
	}
	
	@Given("user sends a SOAP request with the MemberService For QHP:")
	public void user_sends_a_SOAP_request_with_the_MemberService_For_PA_QHP(io.cucumber.datatable.DataTable dataTable) throws ClientProtocolException, IOException {
	    
		aPIMethods = new APIMethods();	
		List<List<String>> list = dataTable.asLists(String.class);
		String EndPointURL = list.get(1).get(0);
		String PathToXML = list.get(1).get(1);
		String XPathToRenewalMonth = list.get(1).get(2);
		String XPathToBenefitStartDate = list.get(1).get(3);
		String XPathToBenefitEndDate = list.get(1).get(4);
		String XPathToMedicalProduct = list.get(1).get(5);
		String XPathToPharmacyProduct = list.get(1).get(6);
		String XpathToGroupID = list.get(1).get(7);
		String XpathToSubGroupID = list.get(1).get(8);
		String KeyToStoreRenewalMonth = list.get(1).get(9);
		String KeyToStoreBenefitStartDate = list.get(1).get(10);
		String KeyToStoreBenefitEndDate = list.get(1).get(11);
		String KeyToStoreMedicalProduct = list.get(1).get(12);
		String KeyToStorePharmacyProduct = list.get(1).get(13);
		String KeyToStoreGroupID = list.get(1).get(14);
		String KeyToStoreSubgroupID = list.get(1).get(15);
		aPIMethods.sendSOAPRequestforMemberserviceforQHP(EndPointURL, PathToXML, XPathToRenewalMonth, XPathToBenefitStartDate, XPathToBenefitEndDate, XPathToMedicalProduct, XPathToPharmacyProduct, XpathToGroupID, XpathToSubGroupID, KeyToStoreRenewalMonth, KeyToStoreBenefitStartDate, KeyToStoreBenefitEndDate, KeyToStoreMedicalProduct, KeyToStorePharmacyProduct, KeyToStoreGroupID, KeyToStoreSubgroupID );
	}

	@Given("user sends the SOAP request with pega Service for PA QHP Member:")
	public void user_sends_the_SOAP_request_with_pega_Service_for_PA_QHP_Member(io.cucumber.datatable.DataTable dataTable) throws ClientProtocolException, IOException {
	   
		aPIMethods = new APIMethods();	
		List<List<String>> list = dataTable.asLists(String.class);
		String EndPointURL = list.get(1).get(0);
		String PathToXML = list.get(1).get(1);
		String xpathtoContractfile = list.get(1).get(2);
		String KeyToStoreContractFileName = list.get(1).get(3);
		aPIMethods.sendSOAPRequestforGetBenefitDocumentsMetaDataPAQHPMember(EndPointURL, PathToXML, xpathtoContractfile, KeyToStoreContractFileName);
	}
	
	

	@Given("user sends the REST POST request using the following:")
	public void user_sends_the_REST_POST_request_using_the_following(io.cucumber.datatable.DataTable dataTable) throws ClientProtocolException, IOException {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		
    List<List<String>> listForRESTPOST = dataTable.asLists(String.class);
    restClient = new RestClient();
    String BaseUrl = listForRESTPOST.get(1).get(0);
    String BasePath = listForRESTPOST.get(1).get(1);
    String RequestPath = listForRESTPOST.get(1).get(2);
    String writeresponsexmlpath = listForRESTPOST.get(1).get(3);
    restClient.parseTheRESTPOSTResponse(BaseUrl, BasePath, RequestPath, writeresponsexmlpath );
	}

	@Given("user sends the REST POST JSON using the following")
	public void user_sends_the_REST_POST_JSON_using_the_following(io.cucumber.datatable.DataTable dataTable) throws IOException {
		List<List<String>> listForRESTPOST = dataTable.asLists(String.class);
	    restClient = new RestClient();
	    String BaseUrl = listForRESTPOST.get(1).get(0);
	    String BasePath = listForRESTPOST.get(1).get(1);
	    String RequestPath = listForRESTPOST.get(1).get(2);
	    String writeresponsexmlpath = listForRESTPOST.get(1).get(3);
	    restClient.parseTheRESTPOSTJSONResponse(BaseUrl, BasePath, RequestPath, writeresponsexmlpath );
	}

	
	@Given("user sends the REST POST request using the following and validates response success or not:")
	public void user_sends_the_REST_POST_request_using_the_following_and_validates_response_success_or_not(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> listForRESTPOST = dataTable.asLists(String.class);
	    restClient = new RestClient();
	    String BaseUrl = listForRESTPOST.get(1).get(0);
	    String BasePath = listForRESTPOST.get(1).get(1);
	    String RequestPath = listForRESTPOST.get(1).get(2);
	    restClient.sendRESTPOSTRequest(BaseUrl,BasePath,RequestPath);
	}

	
	@Given("user sets the SOAP request xml for getRidersbyProductID:")
	public void user_sets_the_SOAP_request_xml_for_getRidersbyProductID(io.cucumber.datatable.DataTable dataTable) throws IOException {
		
		aPIMethods = new APIMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String ProductIDContext = list.get(1).get(0);
		String DateValContext = list.get(1).get(1);
		String getridersXMLpath = list.get(1).get(2);
		String ProductID = (String) ScenarioContext.getContext(ProductIDContext);
		String Dateval = (String) ScenarioContext.getContext(DateValContext);
		aPIMethods.SetupSOAPRequestForGetRidersByProductID(ProductID, Dateval, getridersXMLpath);
	}

	@Given("user sends a SOAP request with GetRidersbyProductID:")
	public void user_sends_a_SOAP_request_with_GetRidersbyProductID(io.cucumber.datatable.DataTable dataTable) throws ClientProtocolException, IOException {
	    
		List<List<String>> list = dataTable.asLists(String.class);
		String Endpoint = list.get(1).get(0);
		String XMLPath = list.get(1).get(1);
		aPIMethods.SendSOAPRequestforGetRidersByProductID(Endpoint, XMLPath);
		
	}
	@Given("user sets the SOAP request xml for ClaimService")
	public void user_sets_the_SOAP_request_xml_for_ClaimService(io.cucumber.datatable.DataTable dataTable) throws IOException {

		aPIMethods = new APIMethods();
		List<List<String>> listToSetUpXMLRequestBody = dataTable.asLists(String.class);
		String ClaimNumber = listToSetUpXMLRequestBody.get(1).get(0);
		
		String ClaimServiceXMLPath = listToSetUpXMLRequestBody.get(1).get(1);
		aPIMethods.setUpSOAPRequestForClaimService(ClaimNumber, ClaimServiceXMLPath);
	}
	
	@Given("user sets the SOAP request xml for MemberService Get All Contracts by Subscriber id:")
	public void user_sets_the_SOAP_request_xml_for_MemberService_Get_All_Contracts_by_Subscriber_id(io.cucumber.datatable.DataTable dataTable) throws IOException {
		aPIMethods = new APIMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String SubscriberID = list.get(1).get(0);
		String DateVal = list.get(1).get(1);
		String XMLPath = list.get(1).get(2);
		aPIMethods.setupSOAPRequestforGetallcontractsbysubscriberid(SubscriberID,DateVal, XMLPath);
	}

	@Given("user sets the SOAP request xml for MemberService Get Member COB and Eligibility and Provider by Subscriber id:")
	public void user_sets_the_SOAP_request_xml_for_MemberService_Get_Member_COB_and_Eligibility_and_Provider_by_Subscriber_id(io.cucumber.datatable.DataTable dataTable) throws IOException {
		aPIMethods = new APIMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String SubscriberID = list.get(1).get(0);
		String MemberSuffix = list.get(1).get(1);
		String XMLPath = list.get(1).get(2);
		aPIMethods.SetUpSOAPRequestforGetMemberCOBandEligandProviderBySubscriberId(SubscriberID,MemberSuffix,XMLPath);
	}


	
	@Given("user sends a SOAP request and Writes it to file:")
	public void user_sends_a_SOAP_request_and_Writes_it_to_file(io.cucumber.datatable.DataTable dataTable) throws ClientProtocolException, IOException {
		aPIMethods = new APIMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String Endpoint = list.get(1).get(0);
		String PayLoadPath = list.get(1).get(1);
		String WriteResponsePath = list.get(1).get(2);
		aPIMethods.SendSOAPRequestandWriteResponsetoFile(Endpoint, PayLoadPath, WriteResponsePath);
		
	}
	

	
	@Given("user sets the SOAP request xml for ClaimDetail")
	public void user_sets_the_SOAP_request_xml_for_ClaimDetail(io.cucumber.datatable.DataTable dataTable) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	
		aPIMethods = new APIMethods();
		List<List<String>> listToSetUpXMLRequestBody = dataTable.asLists(String.class);
		String ClaimNumber = listToSetUpXMLRequestBody.get(1).get(0);
		String ClaimServiceXMLPath = listToSetUpXMLRequestBody.get(1).get(1);
		String Diagnosiscode=listToSetUpXMLRequestBody.get(1).get(2);
		aPIMethods.setUpSOAPRequestForClaimDetail(ClaimNumber, ClaimServiceXMLPath,Diagnosiscode);
	}

	@Given("user sets the SOAP request xml for ClaimSummary by memrecord number:")
	public void user_sets_the_SOAP_request_xml_for_ClaimSummary_by_memrecord_number(io.cucumber.datatable.DataTable dataTable) throws IOException {
		aPIMethods = new APIMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String MemRecordnumber = list.get(1).get(0);
		String Dosfrom=list.get(1).get(1);
		String DateVal = list.get(1).get(2);
		String XMLPath = list.get(1).get(3);
		aPIMethods.setupSOAPRequestforClaimSummarybymemrecordnumber(MemRecordnumber,Dosfrom,DateVal, XMLPath);
	}
	@Given("user sets the SOAP request xml for RefferralService Get Refferral List by Member id:")
	public void user_sets_the_SOAP_request_xml_for_RefferralService_Get_Refferral_List_by_Member_id(io.cucumber.datatable.DataTable dataTable) throws IOException {
		aPIMethods = new APIMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String MemberID = list.get(1).get(0);
		String Dosfrom=list.get(1).get(1);
		String DateVal = list.get(1).get(2);
		String MaxRefferral=list.get(1).get(3);
		String XMLPath = list.get(1).get(4);
		aPIMethods.setupSOAPRequestforGetRefferralListbyMemberID(MemberID,Dosfrom,DateVal,MaxRefferral, XMLPath);
	
	}
	
	@Given("user sets up the request json file")
	public void user_sets_up_the_request_json_file(io.cucumber.datatable.DataTable dataTable) throws IOException {
		aPIMethods = new APIMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String RewardNumber = list.get(1).get(0);
		String jsonpath = list.get(1).get(1);
		aPIMethods.setupjsonrequest(RewardNumber,jsonpath);
	}
	@Given("user sets up the request json file for Paymybill")
	public void user_sets_up_the_request_json_file_for_Paymybill(io.cucumber.datatable.DataTable dataTable) throws IOException {
	    // Writew io.cucumber.java.PendingException();
		aPIMethods = new APIMethods();
		List<List<String>> list = dataTable.asLists(String.class);
		String SubsriberID = list.get(1).get(0);
		String GroupID=list.get(1).get(1);
		String jsonpath = list.get(1).get(2);
		aPIMethods.setupjsonrequest(SubsriberID,GroupID,jsonpath);
	}
	
}
