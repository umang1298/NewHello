package com.qa.util;

import java.nio.file.Files;
//import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.nio.file.*;
import java.util.List;
import java.util.Properties;
import org.w3c.dom.*;
import javax.xml.bind.Element;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.collections.Lists;

import com.qa.base.TestBase;
import com.qa.util.XMLUtil.XmlParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;

public class RestClient extends TestBase {
	static TestBase base;
	static Properties prop;
	

	public static Response sendRESTPOSTRequest(String baseUrl, String basePath, String requestPath) {
		// TODO Auto-generated method stub
		base = new TestBase();
		prop = base.initialize_properties();
		String baseURLVal = prop.getProperty("BaseURLDIGITALREST");
		RestAssured.baseURI = baseURLVal;
		String payloadPath = System.getProperty("user.dir") + File.separator + requestPath;
		Response response = null;
		try {
			response = RestAssured.given().log().all().contentType(ContentType.XML).auth().basic("hap20", "testing")
					.header("Accept", "application/xml").body(new File(payloadPath)).when().log().all().post(basePath)
					.then().extract().response();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("This is the PEGA service Response-> \n " + response.prettyPrint());
		Assert.assertEquals(response.getStatusCode(), 200);
		return response;
	}

	public static  void parseTheRESTPOSTResponse(String baseUrl, String basePath, String requestPath,
			String filepath) throws IOException {

		Response response = RestClient.sendRESTPOSTRequest(baseUrl, basePath, requestPath);
		String responseval = response.toString();
//Writing response to a File
		
		CommonMethods.writeResponsetoFile(response, filepath);

	}
	
	public static Response sendRESTPOSTJSONRequest(String baseUrl, String basePath, String requestPath) {
		// TODO Auto-generated method stub
		prop = base.initialize_properties();
		RestAssured.baseURI = baseUrl;
		String payloadPath = System.getProperty("user.dir") + File.separator + requestPath;
		Response response = null;
		try {
			System.out.println("Inside try");
			response = RestAssured.given().relaxedHTTPSValidation().log().all().contentType(ContentType.JSON).auth().oauth2("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJXYnhTQzVPc2Zrek0ydHFZUFdrUGZhUHJ3S19IOFM5dmNtS2lZR3dHRXNRIn0.eyJleHAiOjE2MzIzMDkxMTksImlhdCI6MTYzMjMwODgxOSwianRpIjoiZDVlM2Q2NDQtZTc1OS00MDgyLWE5MjktOTBhYjM4MzFiNWVhIiwiaXNzIjoiaHR0cHM6Ly9zcG9ydGFsLmhhcC5vcmcvYXV0aC9yZWFsbXMvYXBpLXN0YWdlIiwiYXVkIjpbImJpbGxpbmdhcGkiLCJhY2NvdW50Il0sInN1YiI6ImJkZTU2NDgxLTU2NjMtNGVkNS1hMDE2LWYwYWRjZGVlMDdmZSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImI1Yzk2ZmRlIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJiaWxsaW5nYXBpIGVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudEhvc3QiOiJbY2xpZW50SG9zdG9mZmxpbmVfYWNjZXNzLCBjbGllbnRIb3N0dW1hX2F1dGhvcml6YXRpb25dIiwiY2xpZW50SWQiOiJbY2xpZW50SWRvZmZsaW5lX2FjY2VzcywgY2xpZW50SWR1bWFfYXV0aG9yaXphdGlvbl0iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtYjVjOTZmZGUiLCJjbGllbnRBZGRyZXNzIjoiW2NsaWVudEFkZHJlc3N1bWFfYXV0aG9yaXphdGlvbiwgY2xpZW50QWRkcmVzc29mZmxpbmVfYWNjZXNzXSJ9.YMAs55ciIMCN6djj8f7Ay0m4fjYvnxlDtScskyyCa5nZHTSIi6EMX3qlYzLXijP9gBxcsLAEDG2HEPlHgWayYH7UtglLmDraz1p5Jc4c6mNwbm1kdgiDJt_uFQz2UI0AR0jNnEUwfL4suQ38q6Ba-U3qGfNYqtCPTML4mOKSYcHChpFQcQB3NH-QK1UZqLpXld47MVqmNslNUtGHbDbb-HclLOdQN9MTIp38c0yPQ1zSfQ29hC-pUCUpDY9hwXH5FuI_P6UyNIg-_cd8OWUuLXYj9najsbhnh2M1E_qjZSSoHt5SBRXjb_UvtxNFgIuyIL-nFXWwArKXo0m7_A5jzw") 
					.header("Content-Type", "application/json").body(new File(payloadPath)).when().log().all().post(basePath)
					.then().extract().response();
//			String responseval = response.toString();
			System.out.println("This is the PEGA service Response-> \n " + response.prettyPrint());
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
//		Assert.assertEquals(response.getStatusCode(), 207);
		return response;
	}


	public void parseTheRESTPOSTJSONResponse(String baseUrl, String basePath, String requestPath,
			String writeresponsexmlpath) throws IOException {
		// TODO Auto-generated method stub
		Response response = RestClient.sendRESTPOSTJSONRequest1(baseUrl, basePath, requestPath);
		//String responseval = response.toString();
		//Writing response to a File
				
				CommonMethods.writeResponsetoFile(response , writeresponsexmlpath);
	}
	public static Response sendRESTPOSTJSONRequest1(String baseUrl, String basePath, String requestPath) {
		// TODO Auto-generated method stub
		base = new TestBase();
		prop = base.initialize_properties();
		RestAssured.baseURI = baseUrl;
		String payloadPath = System.getProperty("user.dir") + File.separator + requestPath;
		Response response = null;
		String getAccessToken=null;
		String tokenURL= prop.getProperty("ResttokenURLbillingapi");
		getAccessToken=RestAssured.given().param("grant_type","client_credentials").param("audience","billingapi").param("scope", "billingapi").auth().preemptive().basic("b5c96fde", "dfb4873182b4c49773d655c7b6c6e441")
				 .when().post(tokenURL).then().extract().path("access_token");
		System.out.println("The token is: "+getAccessToken);
		
		try {
			System.out.println("Inside try");
			response = RestAssured.given().relaxedHTTPSValidation().log().all().contentType(ContentType.JSON).auth().oauth2(getAccessToken) 
					.header("Content-Type", "application/json").body(new File(payloadPath)).when().log().all().post(basePath)
					.then().extract().response();
			int statusCode = response.getStatusCode();
			System.out.println("statuscod"+statusCode);
			System.out.println("This is the PEGA service Response-> \n " + response.prettyPrint());
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
//		Assert.assertEquals(response.getStatusCode(), 207);
		return response;
	}
	
	
}