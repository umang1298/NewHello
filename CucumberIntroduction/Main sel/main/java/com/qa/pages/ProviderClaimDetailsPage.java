package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import io.cucumber.java.Scenario;

public class ProviderClaimDetailsPage extends TestBase{

	public ProviderClaimDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	Scenario scenario;
	//Page Factory
	
	By Heading = By.xpath("//div[contains(text(),'Details')]");
	By HAPClaimNumber = By.xpath("/html/body/div[2]/section/div/div/form/div[7]/div/div[2]/div[1]/div[1]/span[2]");
	By ViewRemittanceAdviceLink = By.xpath("//span[contains(text(),'View')]");
	By RequestAppealStatus = By.xpath("//*[@id='page-content-wrapper']/div/div[2]/div/div[3]/div/table/tbody/tr[1]/td[3]/span");
	By LineItemRows = By.xpath("/html/body/div[2]/section/div/div/form/div[8]/div/div[3]/div/table/tbody/tr");
	By AppealButton = By.xpath("//button[@id='appealbtn']");
	By OverallClaimStatus = By.xpath("/html/body/div[2]/section/div/div/form/div[7]/div/div[2]/div[1]/div[2]/span[2]");
	
	public void uservalidateswhetherclaimidtakesusertoClaimDetailsPage(String claimNumber) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(Heading));
		String ClaimID = (String) ScenarioContext.getContext(claimNumber);
		String ClaimIDinUI = driver.findElement(HAPClaimNumber).getText();
		softassert.assertTrue(ClaimID.equals(ClaimIDinUI), "Validating Whether Claim details are loaded for the correct claim ID -->" + ClaimID);
		softassert.assertAll();
	}

	public void userclicksonViewRemittanceAdviceLink(String claimStatus) {
		// TODO Auto-generated method stub
		String ClaimStatus = driver.findElement(OverallClaimStatus).getText();
		ScenarioContext.setContext(claimStatus, ClaimStatus);
		if(ClaimStatus.equals("Processed and Forwarded to TPA")) {
		// View Remittance Advice Link Will not be present . So Do Nothing.
		}
		else {
		WebElement RemittanceAdviceLink = driver.findElement(ViewRemittanceAdviceLink);
		softassert.assertTrue(RemittanceAdviceLink.isDisplayed(), "Verifying View Remittance Advice Link is Displayed or Not");
		//driver.findElement(ViewRemittanceAdviceLink).click();
		js.executeScript("arguments[0].click();", RemittanceAdviceLink);
		}
		softassert.assertAll();
	}

	public void userchecksrequestappealstatusandclicksAppeal(String requestAppealStatus, String forappeal) {
		// TODO Auto-generated method stub
		List <WebElement> LineItems = driver.findElements(LineItemRows);
		int NoofLineItems = LineItems.size();
		String claimlineavailableforappeal;
		for(int i=1; i<=NoofLineItems; i++) {
			
			String ReqAppealStsinUI = driver.findElement(By.xpath("/html/body/div[2]/section/div/div/form/div[8]/div/div[3]/div/table/tbody/tr[" + i + "]/td[3]/span")).getText();
			if(ReqAppealStsinUI.equals(requestAppealStatus)) {
				claimlineavailableforappeal = "true";
				ScenarioContext.setContext(forappeal, claimlineavailableforappeal);
				driver.findElement(By.xpath("/html/body/div[2]/section/div/div/form/div[8]/div/div[3]/div/table/tbody/tr[" + i + "]/td[1]/span/label[1]/span")).click();
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait.until(ExpectedConditions.elementToBeClickable(AppealButton));
				driver.findElement(AppealButton).click();
				
				break;
			}
			else {
				System.out.println("No Line Item with Request Appeal Status 'Appeal'");
				claimlineavailableforappeal="false";
				ScenarioContext.setContext(forappeal, claimlineavailableforappeal);
			}
			
		}
		
	}

}
