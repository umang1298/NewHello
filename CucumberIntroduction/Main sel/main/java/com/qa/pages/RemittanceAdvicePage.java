package com.qa.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.ScenarioContext;

public class RemittanceAdvicePage extends TestBase{

	public RemittanceAdvicePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;

	//Page Factory
	
	By ClaimNumber = By.xpath("//*[@id='claim-Remittance-print']/div[3]/div/div[1]/div/div/div/div/span[2]");
	By Print =By.xpath("//span[contains(text(),'Print')]");
	
	public void uservalidatesclaimNumberandPrintoptiononRAPage(String claimNumber, String claimStatus) {
		// TODO Auto-generated method stub
		String ClaimStatus = (String) ScenarioContext.getContext(claimStatus);
		if(ClaimStatus.equals("Processed and Forwarded to TPA")) {
		//View Remittance Advice Will not be present . So Do Nothing
		}
		else {
		String ClaimID = (String) ScenarioContext.getContext(claimNumber);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		String ClaimIDinUI = driver.findElement(ClaimNumber).getText();
		softassert.assertTrue(ClaimID.equals(ClaimIDinUI), "Validating RA loaded is Correct for the claim Number ->" +ClaimID);
		WebElement PrintBtn = driver.findElement(Print);
		softassert.assertTrue(PrintBtn.isDisplayed(), "Validating Print option is displayed in RA Page");
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		}
		softassert.assertAll();
	}

	
	
	
}
