package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

import io.restassured.path.xml.XmlPath;

public class MemberEligibilityDetailsPage extends TestBase{

	public MemberEligibilityDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	By GroupName = By.xpath("//div[@id='providerinfo']//div/div/div[2]/div[2]/span/span");
	By ExpandAll = By.xpath("//div[contains(text(),'Expand All')]");
	
	public void validatememberEligdetails(String responseXMLPath) throws IOException {
		// TODO Auto-generated method stub
		String xpathtogroupname = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[0].EligibilityInfo.Eligibility[0].GroupName";
		ValidateGroupName(responseXMLPath,xpathtogroupname);
	}

	public void ContractHistoryvalidatememberEligdetails(String responseXMLPath) throws IOException {
		// TODO Auto-generated method stub
		String xpathtogroupname = "Envelope.Body.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResponse.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResult.ResponseData.Member[0].EligibilityInfo.Eligibility[0].GroupName";
		ValidateGroupName(responseXMLPath,xpathtogroupname);
	}
	
	public void ValidateGroupName(String responseXMLPath, String xpathtogroupname) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		String GroupNamefromService = xt.getString(xpathtogroupname);
		System.out.println("Group Name from Response -->" + GroupNamefromService);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(GroupName));
		String GroupNameinUI = driver.findElement(GroupName).getText();
		softassert.assertEquals(GroupNameinUI, GroupNamefromService, "Validating Group Name in MemberElig Details");
		softassert.assertAll();
	}

	
	
}
