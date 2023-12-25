package com.qa.pages;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import io.restassured.path.xml.XmlPath;

public class FormularlyExceptionFormPage extends TestBase{

	public FormularlyExceptionFormPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	By FormularlyHeadingXpath=By.xpath("//h2[@class='inherittext']");
	By LastnameXpath=By.xpath("//span[@id='lastName1']");
	By FirstnameXpath=By.xpath("//span[@id='firstName1']");
	By contactnumberXpath=By.xpath("//input[@id='currentContactNumber']");
	By DrugRequestedXpath=By.xpath("//input[@id='drugRequested']");
	By DrugDoseXpath=By.xpath("//input[@id='drugDoseRequested']");
	By docLastNameXpath=By.xpath("//input[@id='docLastName']");
	By docFristNameXpath=By.xpath("//input[@id='docFirstName']");
	public void validateformularlyExceptionpage(String responseXMLPath) throws IOException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(FormularlyHeadingXpath));
		String MembersfirstnameXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member[0].FirstName";
		String MemberslastnameXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member[0].LastName";
			
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		String Firstname=xt.getString(MembersfirstnameXpath);
		System.out.println("First name:" +Firstname);
		String Lastname=xt.getString(MemberslastnameXpath);
		System.out.println("Last name:" +Lastname);
		String LastNameUI=driver.findElement(LastnameXpath).getText();
		String FirstNameUI=driver.findElement(FirstnameXpath).getText();
		System.out.println("UI name" +LastNameUI);
		System.out.println("UI"+FirstNameUI);
		Assert.assertTrue(Lastname.equalsIgnoreCase(LastNameUI),"Last name is not matching with UI");
		Assert.assertTrue(Firstname.equalsIgnoreCase(FirstNameUI),"First name is not matching with UI");
		
	}

}
