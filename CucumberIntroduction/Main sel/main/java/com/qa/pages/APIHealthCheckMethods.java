package com.qa.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.ScenarioContext;

public class APIHealthCheckMethods extends TestBase{

	Properties prop = new Properties();
	
	public void SendRequestandvalidateresponse(String endpoint, String payLoadPath) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		File requestFile = new File(System.getProperty("user.dir") + payLoadPath);
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endpoint);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		// execute the request
		HttpResponse response = cilent.execute(post);
		System.out.println("This is the response code: -->"+response.getStatusLine().getStatusCode());
		// Get response using the HttpEntity class
		int StatusCode =  response.getStatusLine().getStatusCode();
		Assert.assertTrue(StatusCode==200, "Validating Success of the Service");
		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println("This is the response" + responseBody);
			String Fail = "<IsSuccess>No</IsSuccess>";
			if(responseBody.contains(Fail)) {
				Assert.fail("Soap Response is Not Success");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public void setupSOAPRequestforRemitAdvicePaymentSearch(String paymentNumber, String dateval, String payLoadPath) throws IOException {
		// TODO Auto-generated method stub
		String toDate = (String) ScenarioContext.getContext(dateval);
		String FromDate = CommonMethods.generateADateValueinTheFuture(-365);
		System.out.println("From Date :"+FromDate);
		String PayementSearchRequestXML="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cla=\"http://hap.org/ws/facets/claim\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <cla:PaymentSearch>\r\n" + 
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
				"        \r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <cla:PaymentNo>"+paymentNumber+"</cla:PaymentNo>\r\n" + 
				"         <cla:CheckFromDate>"+FromDate+"</cla:CheckFromDate>\r\n" + 
				"         <cla:CheckToDate>"+toDate+"</cla:CheckToDate>\r\n" + 
				"      </cla:PaymentSearch>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>"
				;
		
		FileUtils.writeStringToFile(new File(payLoadPath), PayementSearchRequestXML);
	}

	
}
