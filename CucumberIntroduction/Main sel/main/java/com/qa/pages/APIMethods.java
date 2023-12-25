package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.util.ScenarioContext;

import groovy.util.NodeList;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

public class APIMethods extends TestBase {

	Properties prop = new Properties();

	public void sendASOAPRequest(String endPointURL, String pathToXML, String PathtoProductID, String PathToRenwalMonth, String PathToBenefitStartDate, String PathToBenefitEndDate,
			String keyToStoreRenewalMonth, String KeyToStoreBenefitStartDate, String KeyToStoreBenefitEndDate, String KeyToStoreProductID) throws ClientProtocolException, IOException {
		//	String endPointURL = prop.getProperty("SOAPEndPoint")+ endPointURI;
		File requestFile = new File(System.getProperty("user.dir") + pathToXML);
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endPointURL);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		// execute the request
		HttpResponse response = cilent.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		// Get response using the HttpEntity class

		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println("This is the response" + responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Parse the response using XmlPath
		XmlPath js = new XmlPath(responseBody);
		String TagvalueRenewalMonth = js.getString(PathToRenwalMonth);
		System.out.println("This is the tag val for renewal Month->" + TagvalueRenewalMonth);
		ScenarioContext.setContext(keyToStoreRenewalMonth, TagvalueRenewalMonth);
		String TagvalueBenefitStartDate = js.getString(PathToBenefitStartDate);
		System.out.println("This is the tag val for Benefit Start Date->" + TagvalueBenefitStartDate);
		ScenarioContext.setContext(KeyToStoreBenefitStartDate, TagvalueBenefitStartDate);
		String TagvalueBenefitEndDate = js.getString(PathToBenefitEndDate);
		System.out.println("This is the tag val for Benefit End Date->" + TagvalueBenefitEndDate);
		ScenarioContext.setContext(KeyToStoreBenefitEndDate, TagvalueBenefitEndDate);
		String TagvalueProductID = js.getString(PathtoProductID);
		System.out.println("This is the tag value for Product ID -->" + TagvalueProductID);
		ScenarioContext.setContext(KeyToStoreProductID, TagvalueProductID);
	}

	
	public static void SendSOAPRequestforGetProductsBySubgroup(String endPointURL, String pathToXML,
			String xPathToRenewalMonth, String keyToStoreRenewalMonth) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		File requestFile = new File(System.getProperty("user.dir") + pathToXML);
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endPointURL);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		// execute the request
		HttpResponse response = cilent.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		// Get response using the HttpEntity class

		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println("This is the response" + responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Parse the response using XmlPath
		XmlPath js = new XmlPath(responseBody);
		String TagvalueRenewalMonth = js.getString(xPathToRenewalMonth);
		System.out.println("This is the SubGroup Renewal Month->" + TagvalueRenewalMonth);
		ScenarioContext.setContext(keyToStoreRenewalMonth, TagvalueRenewalMonth);
	}
	
	public void sendSOAPRequestforMemberserviceforQHP(String endPointURL, String pathToXML, String xPathToRenewalMonth,
			String xPathToBenefitStartDate, String xPathToBenefitEndDate, String xPathToMedicalProduct,
			String xPathToPharmacyProduct, String xpathToGroupID, String xpathToSubGroupID,
			String keyToStoreRenewalMonth, String keyToStoreBenefitStartDate, String keyToStoreBenefitEndDate,
			String keyToStoreMedicalProduct, String keyToStorePharmacyProduct, String keyToStoreGroupID,
			String keyToStoreSubgroupID) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		File requestFile = new File(System.getProperty("user.dir") + pathToXML);
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endPointURL);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		// execute the request
		HttpResponse response = cilent.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		// Get response using the HttpEntity class

		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println("This is the response" + responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Parse the response using XmlPath
		XmlPath js = new XmlPath(responseBody);
		String TagvalueRenewalMonth = js.getString(xPathToRenewalMonth);
		System.out.println("This is the Renewal Month --> " + TagvalueRenewalMonth);
		String TagvalueBenefitStartdate = js.getString(xPathToBenefitStartDate);
		System.out.println("This is the Benefit Start Date --> " + TagvalueBenefitStartdate);
		String TagvalueBenefitEndDate = js.getString(xPathToBenefitEndDate);
		System.out.println("This the the Bnefit End date -->" + TagvalueBenefitEndDate);
		String TagvalueMedicalProduct = js.getString(xPathToMedicalProduct);
		System.out.println("This is the Medical Product --> " + TagvalueMedicalProduct);
		String TagvaluePharmacyProduct = js.getString(xPathToPharmacyProduct);
		System.out.println("This is the Pharmacy Product --> " + TagvaluePharmacyProduct);
		String TagvalueGroupId = js.getString(xpathToGroupID);
		System.out.println("This is the Group id --> "+ TagvalueGroupId);
		String TagvalueSubGroupId = js.getString(xpathToSubGroupID);
		System.out.println("This is the SubgroupID --> "+ TagvalueSubGroupId);
		ScenarioContext.setContext(keyToStoreRenewalMonth, TagvalueRenewalMonth);
		ScenarioContext.setContext(keyToStoreBenefitStartDate, TagvalueBenefitStartdate);
		ScenarioContext.setContext(keyToStoreBenefitEndDate, TagvalueBenefitEndDate);
		ScenarioContext.setContext(keyToStoreMedicalProduct, TagvalueMedicalProduct);
		ScenarioContext.setContext(keyToStorePharmacyProduct, TagvaluePharmacyProduct);
		ScenarioContext.setContext(keyToStoreGroupID, TagvalueGroupId);
		ScenarioContext.setContext(keyToStoreSubgroupID, TagvalueSubGroupId);
		
		
	}
	
	public void SendSOAPRequesttogetProductIDs(String endPointURL, String pathToXML, String xPathToMedicalProduct,
			String xPathToPharmacyProduct, String keyToStoreMedicalProduct, String keyToStorePharmacyProduct) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		File requestFile = new File(System.getProperty("user.dir") + pathToXML);
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endPointURL);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		// execute the request
		HttpResponse response = cilent.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		// Get response using the HttpEntity class

		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println("This is the response" + responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Parse the response using XmlPath
		XmlPath js = new XmlPath(responseBody);
		String TagvalueMedicalProduct = js.getString(xPathToMedicalProduct);
		System.out.println("This the Medical Product-> " + TagvalueMedicalProduct);
		String TagvaluePharmacyProduct = js.getString(xPathToPharmacyProduct);
		System.out.println("This the Pharmacy Product-> " + TagvaluePharmacyProduct);
		ScenarioContext.setContext(keyToStoreMedicalProduct, TagvalueMedicalProduct);
		ScenarioContext.setContext(keyToStorePharmacyProduct, TagvaluePharmacyProduct);
	}
	
	public void sendSOAPRequestforGetBenefitDocumentsMetaDataPAQHPMember(String endPointURL, String pathToXML,
			String xpathtoContractfile, String keyToStoreContractFileName) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		File requestFile = new File(System.getProperty("user.dir") + pathToXML);
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endPointURL);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		// execute the request
		HttpResponse response = cilent.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		// Get response using the HttpEntity class

		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println("This is the response" + responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Parse the response using XmlPath
		XmlPath js = new XmlPath(responseBody);
		String TagvalueContractFileName = js.getString(xpathtoContractfile);
		System.out.println("This is the Contract file name -->" + TagvalueContractFileName);
		ScenarioContext.setContext(keyToStoreContractFileName, TagvalueContractFileName);
	}
	
	public static void SendSOAPRequestforGetRidersByProductID(String endPointURLforGetRiders,
			String pathtoXMLForGetRiders) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		File requestFile = new File(System.getProperty("user.dir") + pathtoXMLForGetRiders);
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endPointURLforGetRiders);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		// execute the request
		HttpResponse response = cilent.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		// Get response using the HttpEntity class
		
		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println("This is the response" + responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String XMLPathtoWriteResponse ="XMLs/GetRidersByProductIDResponse.xml";
		FileUtils.writeStringToFile(new File(XMLPathtoWriteResponse), responseBody);
	}
	
	public void SendSOAPRequestandWriteResponsetoFile(String endpoint, String payLoadPath, String writeResponsePath) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		File requestFile = new File(System.getProperty("user.dir") + payLoadPath);
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endpoint);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		// execute the request
		HttpResponse response = cilent.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		// Get response using the HttpEntity class
		
		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println("This is the response" + responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileUtils.writeStringToFile(new File(writeResponsePath), responseBody);
		
	}
	
	public static void SendSOAPRequestforMemberDeductibleServiceandWriteResponsetoFile(String endpoint, String payLoadPath, String writeResponsePath) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		File requestFile = new File(System.getProperty("user.dir") + payLoadPath);
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endpoint);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		// execute the request
		HttpResponse response = cilent.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		// Get response using the HttpEntity class
		
		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println("This is the response" + responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileUtils.writeStringToFile(new File(writeResponsePath), responseBody);
		
	}

	
	public void setUpSOAPRequestForMemberService(String memberId, String dateVal, String xMLPath) throws IOException {

		String memberIdVal = (String) ScenarioContext.getContext(memberId); 
		String storeDateVal = (String) ScenarioContext.getContext(dateVal);		
		String xmlrequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mem=\"http://hap.org/ws/facets/member\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <mem:GetAllContractsByMemberRecordNumber>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <mem:Config>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemName>?</mem:CallingSystemName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemTransactionId>?</mem:CallingSystemTransactionId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemUserId>?</mem:CallingSystemUserId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemUserName>?</mem:CallingSystemUserName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:FacetsIdentity>WEB_LOCAL</mem:FacetsIdentity>\r\n" + 
				"         </mem:Config>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <mem:memberRecordNumbers>\r\n" + 
				"            <!--Zero or more repetitions:-->\r\n" + 
				"            <mem:string>" + memberIdVal + "</mem:string>\r\n" + 
						"         </mem:memberRecordNumbers>\r\n" + 
					"         <mem:p_AS_OF_DT>" + storeDateVal + "</mem:p_AS_OF_DT>\r\n" + 
								"      </mem:GetAllContractsByMemberRecordNumber>\r\n" + 
								"   </soapenv:Body>\r\n" + 
								"</soapenv:Envelope>"; 
		FileUtils.writeStringToFile(new File(xMLPath), xmlrequest);
		System.out.println("Worked");
		
		
		
		
		
	}

	public static void setUpSOAPRequestForGetProductsBySubGroup(String groupID, String dateVal, String subgroupId,
			String xMLPathofGetProductsBySubGroup) throws IOException {
		// TODO Auto-generated method stub
		
		String getproductsbysubgroupxmlrequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ben=\"http://hap.org/ws/facets/benefits\">\r\n" + 
				"<soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <ben:GetProductsbySubgroup>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <ben:Config>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <ben:CallingSystemName>?</ben:CallingSystemName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <ben:CallingSystemTransactionId>?</ben:CallingSystemTransactionId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <ben:CallingSystemUserId>?</ben:CallingSystemUserId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <ben:CallingSystemUserName>?</ben:CallingSystemUserName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <ben:FacetsIdentity>WEB_LOCAL</ben:FacetsIdentity>\r\n" + 
				"         </ben:Config>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <ben:inputGroupID>" + groupID + "</ben:inputGroupID>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <ben:inputSubgroupID>" + subgroupId + "</ben:inputSubgroupID>\r\n" + 
				"         <ben:inputAsOfDate>" + dateVal + "</ben:inputAsOfDate>\r\n" + 
				"      </ben:GetProductsbySubgroup>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>\r\n" ;
		//FileUtils.writeStringToFile(new File(xMLPathofGetProductsBySubGroup), getproductsbysubgroupxmlrequest);
		FileUtils.writeStringToFile(new File(xMLPathofGetProductsBySubGroup), getproductsbysubgroupxmlrequest);
		System.out.println("Worked");
	}

	public void setUpSOAPRequestForPegaServiceforPAQHPMember(String dateVal, String xMLPath,
			String groupIDtoset, String subGroupIDtoset, String medicalProductIDtoset, String pharmacyProductIDtoset) throws IOException {
		// TODO Auto-generated method stub
		String storeDateVal = (String) ScenarioContext.getContext(dateVal);	
		String GroupID = (String) ScenarioContext.getContext(groupIDtoset);
		String SubgroupID = (String) ScenarioContext.getContext(subGroupIDtoset);
		String MedicalProductID = (String) ScenarioContext.getContext(medicalProductIDtoset);
		String PharmacyProductID = (String) ScenarioContext.getContext(pharmacyProductIDtoset);
		
		String getBenfitDocumentsMetaDataxmlrequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"   <ProductBenefitInputDTO>\r\n" + 
				"        <groupID>" + GroupID + "</groupID>\r\n" + 
				"        <subGroupID>" + SubgroupID + "</subGroupID>\r\n" + 
				"        <medProductID>" + MedicalProductID + "</medProductID>\r\n" + 
				"<rxProductID>" + PharmacyProductID + "</rxProductID>       \r\n" + 
				"  </ProductBenefitInputDTO>\r\n" ;
				

		FileUtils.writeStringToFile(new File(xMLPath), getBenfitDocumentsMetaDataxmlrequest);
		
	}

	public static void SendSOAPRequestforSBCGetProductsBySubgroup(String endPointURL, String pathToXML,
			String xPathToMedical, String keyToMedical,String xPathToPharmacy,String keyToPharmacy) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		File requestFile = new File(System.getProperty("user.dir") + pathToXML);
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endPointURL);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		// execute the request
		HttpResponse response = cilent.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		// Get response using the HttpEntity class

		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println("This is the response" + responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Parse the response using XmlPath
		XmlPath js = new XmlPath(responseBody);
		String TagvalueMedical = js.getString(xPathToMedical);
		System.out.println("This is the SubGroup Medical->" + TagvalueMedical);
		ScenarioContext.setContext(keyToMedical, TagvalueMedical);
		String TagvaluePharmacy = js.getString(xPathToPharmacy);
		System.out.println("This is the SubGroup Pharmacy->" + TagvaluePharmacy);
		ScenarioContext.setContext(keyToPharmacy, TagvaluePharmacy);
	}

	public static void setUpSOAPRequestForPegaServiceforPAQHPEmployer(String dateVal, String xMLPath,
			String GroupID, String SubgroupID, String MedicalProductID, String PharmacyProductID) throws IOException {
		// TODO Auto-generated method stub
		
		
		String getBenfitDocumentsMetaDataxmlrequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"   <ProductBenefitInputDTO>\r\n" + 
				"        <groupID>" + GroupID + "</groupID>\r\n" + 
				"        <subGroupID>" + SubgroupID + "</subGroupID>\r\n" + 
				"        <medProductID>" + MedicalProductID + "</medProductID>\r\n" + 
				"<rxProductID>" + PharmacyProductID + "</rxProductID>       \r\n" + 
				"  </ProductBenefitInputDTO>\r\n" ;
				

		FileUtils.writeStringToFile(new File(xMLPath), getBenfitDocumentsMetaDataxmlrequest);
		
	}

	public static void SetupSOAPRequestForGetRidersByProductID(String ProductID, String dateVal,
			String getRidersXMLpath) throws IOException {
		// TODO Auto-generated method stub
		
		String GetRidersByProductIDRequestXML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ben=\"http://hap.org/ws/facets/benefits\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <ben:GetRidersbyProductId>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <ben:Config>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <ben:CallingSystemName>?</ben:CallingSystemName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <ben:CallingSystemTransactionId>?</ben:CallingSystemTransactionId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <ben:CallingSystemUserId>?</ben:CallingSystemUserId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <ben:CallingSystemUserName>?</ben:CallingSystemUserName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <ben:FacetsIdentity>WEB_LOCAL</ben:FacetsIdentity>\r\n" + 
				"         </ben:Config>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <ben:inputProductIDs>\r\n" + 
				"            <!--Zero or more repetitions:-->\r\n" + 
				"            <ben:string>" + ProductID + "</ben:string>\r\n" + 
				"         </ben:inputProductIDs>\r\n" + 
				"         <ben:inputAsOfDate>" + dateVal + "</ben:inputAsOfDate>\r\n" + 
				"      </ben:GetRidersbyProductId>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>\r\n" ;
		
		FileUtils.writeStringToFile(new File(getRidersXMLpath), GetRidersByProductIDRequestXML);
	}
	public static void SetupSOAPRequestForGetClaimServiceLineItem(String ClaimNumber,String ClaimServiceXMLpath) throws IOException
	{
	String ClaimServiceXML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cla=\"http://hap.org/ws/facets/claim\">\r\n" + 
			"   <soapenv:Header/>\r\n" + 
			"   <soapenv:Body>\r\n" + 
			"      <cla:GetLineItem>\r\n" + 
			"         <!--Optional:-->\r\n" + 
			"         <cla:Config>\r\n" + 
			"            <!--Optional:-->\r\n" + 
			"            <cla:CallingSystemName>?</cla:CallingSystemName>\r\n" + 
			"            <!--Optional:-->\r\n" + 
			"            <cla:CallingSystemTransactionId>?</cla:CallingSystemTransactionId>\r\n" + 
			"            <!--Optional:-->\r\n" + 
			"            <cla:CallingSystemUserId>?</cla:CallingSystemUserId>\r\n" + 
			"            <!--Optional:-->\r\n" + 
			"            <cla:CallingSystemUserName>?</cla:CallingSystemUserName>\r\n" + 
			"            <!--Optional:-->\r\n" + 
			"            <cla:FacetsIdentity>WEB_LOCAL</cla:FacetsIdentity>\r\n" + 
			"         </cla:Config>\r\n" + 
			"         <!--Optional:-->\r\n" + 
			"         <cla:inputClaimNumber>" + ClaimNumber + "</cla:inputClaimNumber>\r\n" + 
			"      </cla:GetLineItem>\r\n" + 
			"   </soapenv:Body>\r\n" + 
			"</soapenv:Envelope>";
	

	FileUtils.writeStringToFile(new File(ClaimServiceXMLpath), ClaimServiceXML);
	}

	public void setupSOAPRequestforGetallcontractsbysubscriberid(String subscriberID, String dateVal, String xMLPath) throws IOException {
		// TODO Auto-generated method stub
		String SubscriberIDVal = (String) ScenarioContext.getContext(subscriberID);
		String date = (String) ScenarioContext.getContext(dateVal);
		
		String GetallContractsbySubscriberIDRequest ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mem=\"http://hap.org/ws/facets/member\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <mem:GetAllContractsBySubscriberId>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <mem:Config>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemName>?</mem:CallingSystemName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemTransactionId>?</mem:CallingSystemTransactionId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemUserId>?</mem:CallingSystemUserId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemUserName>?</mem:CallingSystemUserName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:FacetsIdentity>WEB_LOCAL</mem:FacetsIdentity>\r\n" + 
				"         </mem:Config>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <mem:subscriberIds>\r\n" + 
				"            <!--Zero or more repetitions:-->\r\n" + 
				"            <mem:string>" + SubscriberIDVal + "</mem:string>\r\n" + 
				"         </mem:subscriberIds>\r\n" + 
				"         <mem:p_AS_OF_DT>" + date + "</mem:p_AS_OF_DT>\r\n" + 
				"      </mem:GetAllContractsBySubscriberId>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>\r\n" ;
		
		FileUtils.writeStringToFile(new File(xMLPath), GetallContractsbySubscriberIDRequest);
		
	}
	
	public void SetUpSOAPRequestforGetMemberCOBandEligandProviderBySubscriberId(String subscriberID,
			String memberSuffix, String xMLPath) throws IOException {
		// TODO Auto-generated method stub
		String SubscriberIDVal = (String) ScenarioContext.getContext(subscriberID);
		String MemSuffix = (String) ScenarioContext.getContext(memberSuffix);
		
		String GetCOBMemEligandProviderbySubscriberIDRequest ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mem=\"http://hap.org/ws/facets/member\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <mem:GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffix>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <mem:Config>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemName>?</mem:CallingSystemName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemTransactionId>?</mem:CallingSystemTransactionId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemUserId>?</mem:CallingSystemUserId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:CallingSystemUserName>?</mem:CallingSystemUserName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <mem:FacetsIdentity>WEB_LOCAL</mem:FacetsIdentity>\r\n" + 
				"         </mem:Config>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <mem:subscriberId>"+SubscriberIDVal+"</mem:subscriberId>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <mem:memberSuffix>"+MemSuffix+"</mem:memberSuffix>\r\n" +  
				"      </mem:GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffix>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		
		FileUtils.writeStringToFile(new File(xMLPath), GetCOBMemEligandProviderbySubscriberIDRequest);
		
	}

	public static void setUpSOAPRequestForClaimService(String ClaimNumber,String ClaimServiceXMLPath) throws IOException
    {
        String Claimnum = (String) ScenarioContext.getContext(ClaimNumber);   
       
        //ScenarioContext.setContext(ClaimNumber, ClaimNumber);
        String GetClaimServiceRequestXML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cla=\"http://hap.org/ws/facets/claim\">\r\n" +
                "   <soapenv:Header/>\r\n" +
                "   <soapenv:Body>\r\n" +
                "      <cla:GetLineItem>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <cla:Config>\r\n" +
                "            <!--Optional:-->\r\n" +
                "            <cla:CallingSystemName>?</cla:CallingSystemName>\r\n" +
                "            <!--Optional:-->\r\n" +
                "            <cla:CallingSystemTransactionId>?</cla:CallingSystemTransactionId>\r\n" +
                "            <!--Optional:-->\r\n" +
                "            <cla:CallingSystemUserId>?</cla:CallingSystemUserId>\r\n" +
                "            <!--Optional:-->\r\n" +
                "            <cla:CallingSystemUserName>?</cla:CallingSystemUserName>\r\n" +
                "            <!--Optional:-->\r\n" +
                "            <cla:FacetsIdentity>WEB_LOCAL</cla:FacetsIdentity>\r\n" +
                "         </cla:Config>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <cla:inputClaimNumber>" + Claimnum +"</cla:inputClaimNumber>\r\n" +
                "      </cla:GetLineItem>\r\n" +
                "   </soapenv:Body>\r\n" +
                "</soapenv:Envelope>";
               
               
               
    FileUtils.writeStringToFile(new File(ClaimServiceXMLPath), GetClaimServiceRequestXML);
    }


	public void setUpSOAPRequestForClaimDetail(String ClaimNumber, String claimServiceXMLPath, String diagnosiscode) throws IOException {
		// TODO Auto-generated method stub
		 String Claimnum = (String) ScenarioContext.getContext(ClaimNumber);   
		 String GetClaimDetailRequestXML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cla=\"http://hap.org/ws/facets/claim\">\r\n" + 
		 		"   <soapenv:Header/>\r\n" + 
		 		"   <soapenv:Body>\r\n" + 
		 		"      <cla:GetClaimDetail>\r\n" + 
		 		"         <!--Optional:-->\r\n" + 
		 		"         <cla:Config>\r\n" + 
		 		"            <!--Optional:-->\r\n" + 
		 		"            <cla:CallingSystemName>?</cla:CallingSystemName>\r\n" + 
		 		"            <!--Optional:-->\r\n" + 
		 		"            <cla:CallingSystemTransactionId>?</cla:CallingSystemTransactionId>\r\n" + 
		 		"            <!--Optional:-->\r\n" + 
		 		"            <cla:CallingSystemUserId>?</cla:CallingSystemUserId>\r\n" + 
		 		"            <!--Optional:-->\r\n" + 
		 		"            <cla:CallingSystemUserName>?</cla:CallingSystemUserName>\r\n" + 
		 		"            <!--Optional:-->\r\n" + 
		 		"            <cla:FacetsIdentity>WEB_LOCAL</cla:FacetsIdentity>\r\n" + 
		 		"         </cla:Config>\r\n" + 
		 		"         <!--Optional:-->\r\n" + 
		 		"         <cla:inputClaimNumber>"+ Claimnum +"</cla:inputClaimNumber>\r\n" + 
		 		"         <cla:GetDiagnosisCodes>"+ diagnosiscode +"</cla:GetDiagnosisCodes>\r\n" + 
		 		"      </cla:GetClaimDetail>\r\n" + 
		 		"   </soapenv:Body>\r\n" + 
		 		"</soapenv:Envelope>";
		 
		 FileUtils.writeStringToFile(new File(claimServiceXMLPath), GetClaimDetailRequestXML);
	}


	

	public void setupSOAPRequestforClaimSummarybymemrecordnumber(String memrecordnumber, String dosfrom, String dateVal,
			String XMLPath) throws IOException {
		// TODO Auto-generated method stub
		String Recordnumber = (String) ScenarioContext.getContext(memrecordnumber);
		String date = (String) ScenarioContext.getContext(dateVal);
		System.out.println("recod:" +Recordnumber);
		String getClaimSummaryXML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cla=\"http://hap.org/ws/facets/claim\">\r\n" + 
		"   <soapenv:Header/>\r\n" + 
		"   <soapenv:Body>\r\n" + 
		"      <cla:GetClaimSummary>\r\n" + 
		"         <!--Optional:-->\r\n" + 
		"         <cla:Config>\r\n" + 
		"            <!--Optional:-->\r\n" + 
		"            <cla:CallingSystemName>?</cla:CallingSystemName>\r\n" + 
		"            <!--Optional:-->\r\n" + 
		"            <cla:CallingSystemTransactionId>?</cla:CallingSystemTransactionId>\r\n" + 
		"            <!--Optional:-->\r\n" + 
		"            <cla:CallingSystemUserId>?</cla:CallingSystemUserId>\r\n" + 
		"            <!--Optional:-->\r\n" + 
		"            <cla:CallingSystemUserName>?</cla:CallingSystemUserName>\r\n" + 
		"            <!--Optional:-->\r\n" + 
		"            <cla:FacetsIdentity>WEB_LOCAL</cla:FacetsIdentity>\r\n" + 
		"         </cla:Config>\r\n" + 
		"         <!--Optional:-->\r\n" + 
		"         <cla:input_MemberRecordNumber>"+Recordnumber+"</cla:input_MemberRecordNumber>\r\n" + 
		"         <!--Optional:-->\r\n" + 
		"         <cla:inputProviderNPI/>\r\n" + 
		"         <!--Optional:-->\r\n" + 
		"         <cla:inputSubscriberID></cla:inputSubscriberID>\r\n" + 
		"         <!--Optional:-->\r\n" + 
		"         <cla:inputMemberSuffix></cla:inputMemberSuffix>\r\n" + 
		"         <!--Optional:-->\r\n" + 
		"         <cla:inputParams>\r\n" + 
		"            <!--Zero or more repetitions:-->\r\n" + 
		"            <cla:InputParam>\r\n" + 
		"               <!--Optional:-->\r\n" + 
		"               <cla:inputClaimStatus>02</cla:inputClaimStatus>\r\n" + 
		"            </cla:InputParam>\r\n" + 
		"            <cla:InputParam>\r\n" + 
		"               <cla:inputClaimStatus>11</cla:inputClaimStatus>\r\n" + 
		"            </cla:InputParam>\r\n" + 
		"            <cla:InputParam>\r\n" + 
		"               <cla:inputClaimStatus>13</cla:inputClaimStatus>\r\n" + 
		"            </cla:InputParam>\r\n" + 
		"            <cla:InputParam>\r\n" + 
		"               <cla:inputClaimStatus>01</cla:inputClaimStatus>\r\n" + 
		"            </cla:InputParam>\r\n" + 
		"            <cla:InputParam>\r\n" + 
		"               <cla:inputClaimStatus>02</cla:inputClaimStatus>\r\n" + 
		"            </cla:InputParam>\r\n" + 
		"            <cla:InputParam>\r\n" + 
		"               <cla:inputClaimStatus>81</cla:inputClaimStatus>\r\n" + 
		"            </cla:InputParam>\r\n" + 
		"            <cla:InputParam>\r\n" + 
		"               <cla:inputClaimStatus>91</cla:inputClaimStatus>\r\n" + 
		"            </cla:InputParam>\r\n" + 
		"            <cla:InputParam>\r\n" + 
		"               <cla:inputClaimStatus>14</cla:inputClaimStatus>\r\n" + 
		"            </cla:InputParam>\r\n" + 
		"            <cla:InputParam>\r\n" + 
		"               <cla:inputClaimStatus>15</cla:inputClaimStatus>\r\n" + 
		"            </cla:InputParam>\r\n" + 
		"            <cla:InputParam>\r\n" + 
		"               <cla:inputClaimStatus>16</cla:inputClaimStatus>\r\n" + 
		"            </cla:InputParam>\r\n" + 
		"         </cla:inputParams>\r\n" + 
		"         <!--Optional:-->\r\n" + 
		"         <cla:inputClaimType>B</cla:inputClaimType>\r\n" + 
		"         <!--Optional:-->\r\n" + 
		"         <cla:inputPatientAccountNumber/>\r\n" + 
		"         <!--Optional:-->\r\n" + 
		"         <cla:inputAlternateID/>\r\n" + 
		"         <cla:inputDateOfServiceFrom>"+dosfrom+"</cla:inputDateOfServiceFrom>\r\n" + 
		"         <cla:inputDateOfServiceTo>"+date+"</cla:inputDateOfServiceTo>\r\n" + 
		"      </cla:GetClaimSummary>\r\n" + 
		"   </soapenv:Body>\r\n" + 
		"</soapenv:Envelope>";
		FileUtils.writeStringToFile(new File(XMLPath), getClaimSummaryXML);
	}


	public void setupSOAPRequestforGetRefferralListbyMemberID(String memberID, String dosfrom, String dateVal,
			String maxRefferral, String xMLPath) throws IOException {
		// TODO Auto-generated method stub
		String MemberID = (String) ScenarioContext.getContext(memberID);
		String date = (String) ScenarioContext.getContext(dateVal);
		
		String getRefferralList="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:auth=\"http://hap.org/ws/facets/auth\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <auth:GetReferralListByMemberId>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <auth:Config>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <auth:CallingSystemName>?</auth:CallingSystemName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <auth:CallingSystemTransactionId>?</auth:CallingSystemTransactionId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <auth:CallingSystemUserId>?</auth:CallingSystemUserId>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <auth:CallingSystemUserName>?</auth:CallingSystemUserName>\r\n" + 
				"            <!--Optional:-->\r\n" + 
				"            <auth:FacetsIdentity>WEB_LOCAL</auth:FacetsIdentity>\r\n" + 
				"         </auth:Config>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <auth:inputMemberId>"+MemberID+"</auth:inputMemberId>\r\n" + 
				"         <auth:inputRecdDateFrom>"+dosfrom+"</auth:inputRecdDateFrom>\r\n" + 
				"         <auth:inputRecdDateTo>"+date+"</auth:inputRecdDateTo>\r\n" + 
				"         <auth:inputMaxReferrals>"+maxRefferral+"</auth:inputMaxReferrals>\r\n" + 
				"      </auth:GetReferralListByMemberId>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		
				
				
				
				FileUtils.writeStringToFile(new File(xMLPath), getRefferralList);
		
		
	}


	public static void setupSOAPRequestforMemberDeductibleService(String productID, String contrivedKey, String dateVal,
			String DeductibleRequestXmlPath) throws IOException {
		// TODO Auto-generated method stub
		String date = (String) ScenarioContext.getContext(dateVal);
	String getMemberDeductibleRequestXML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mem=\"http://hap.org/ws/facets/member\">\r\n" + 
			"   <soapenv:Header/>\r\n" + 
			"   <soapenv:Body>\r\n" + 
			"      <mem:GetMemberDeductible>\r\n" + 
			"         <!--Optional:-->\r\n" + 
			"         <mem:Config>\r\n" + 
			"            <!--Optional:-->\r\n" + 
			"            <mem:CallingSystemName>?</mem:CallingSystemName>\r\n" + 
			"            <!--Optional:-->\r\n" + 
			"            <mem:CallingSystemTransactionId>?</mem:CallingSystemTransactionId>\r\n" + 
			"            <!--Optional:-->\r\n" + 
			"            <mem:CallingSystemUserId>?</mem:CallingSystemUserId>\r\n" + 
			"            <!--Optional:-->\r\n" + 
			"            <mem:CallingSystemUserName>?</mem:CallingSystemUserName>\r\n" + 
			"            <!--Optional:-->\r\n" + 
			"            <mem:FacetsIdentity>WEB_LOCAL</mem:FacetsIdentity>\r\n" + 
			"         </mem:Config>\r\n" + 
			"         <!--Optional:-->\r\n" + 
			"         <mem:productId>"+productID+"</mem:productId>\r\n" + 
			"         <!--Optional:-->\r\n" + 
			"         <mem:memberContrivedKey>"+contrivedKey+"</mem:memberContrivedKey>\r\n" + 
			"         <mem:asOfDate>"+date+"</mem:asOfDate>\r\n" + 
			"      </mem:GetMemberDeductible>\r\n" + 
			"   </soapenv:Body>\r\n" + 
			"</soapenv:Envelope>";
	

	FileUtils.writeStringToFile(new File(DeductibleRequestXmlPath), getMemberDeductibleRequestXML);

	}


	public void setupjsonrequest(String rewardNumber, String jsonpath) throws IOException {
		// TODO Auto-generated method stub
	
		
		String JSONPayload = "{\r\n" + 
				"  \"queries\": [\r\n" + 
				"    {\r\n" + 
				"      \"id\": \"exact-match\",\r\n" + 
				"      \"values\": [\r\n" + 
				"        \"false\"\r\n" + 
				"      ]\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"id\": \"rewards-number\",\r\n" + 
				"      \"values\": [\r\n" + 
				"        \"784755862\"\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
		
		FileUtils.writeStringToFile(new File(jsonpath), JSONPayload);
	}


	public void setupjsonrequest(String subscriberID, String groupID, String jsonpath) throws IOException {
		// TODO Auto-generated method stub
		String JSONPayload ="\r\n"
				+ "{\r\n"
				+ "\"subscriberId\":  \""+subscriberID+"\",\r\n"
				+ "\"groupId\":  \""+groupID+"\"\r\n"
				+ "}\r\n"
				+ "";
			
				
				FileUtils.writeStringToFile(new File(jsonpath), JSONPayload);
	}



	




	



}
