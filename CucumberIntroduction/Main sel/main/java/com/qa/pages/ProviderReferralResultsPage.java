package com.qa.pages;

import java.util.concurrent.TimeUnit;

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

import junit.framework.Assert;

public class ProviderReferralResultsPage extends TestBase {
	
	public ProviderReferralResultsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	By ReferralResultsPageHeading = By.xpath("//div[contains(text(),'Referral Results')]");
	By ReferralSearchResultsTable = By.xpath("//table[@id='referralSearchResult']");
	By FirstReferralNumber = By.xpath("//table[@id='referralSearchResult']//tbody//tr//td[2]//a");
	By FirstPatientName = By.xpath("//table[@id='referralSearchResult']//tbody//tr//td[6]");
	
	public void userverifiesthereferralresults() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(ReferralSearchResultsTable));
		WebElement SearchResultTable = driver.findElement(ReferralSearchResultsTable);
		wait.until(ExpectedConditions.elementToBeClickable(FirstReferralNumber));
		softassert.assertTrue(SearchResultTable.isDisplayed(),"Validating Search Result Is Displayed");
		softassert.assertAll();		
	}

	public void userclickonReferralNumberandstoresPatientName(String referralNumber, String patientName) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FirstReferralNumber));
		String PatientName = driver.findElement(FirstPatientName).getText();
		System.out.println("patientname:"+PatientName);
		String PatientFirstName = PatientName.substring(PatientName.indexOf(",")+1);
		System.out.println("patientfirstname:"+PatientFirstName);
		ScenarioContext.setContext(patientName, PatientFirstName);
		WebElement FirstReferralLink = driver.findElement(FirstReferralNumber);
		js.executeScript("arguments[0].click();", FirstReferralLink);
		
	}
}
