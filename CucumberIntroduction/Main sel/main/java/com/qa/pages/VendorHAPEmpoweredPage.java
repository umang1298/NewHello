package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class VendorHAPEmpoweredPage extends TestBase{

	public VendorHAPEmpoweredPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver =driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	
	//Page Factory
	By SearchButton = By.xpath("//input[@type='submit']");
	By BillingProviderNPITextBox = By.xpath("//input[@id='billingProviderNPI']");
	By SearchResultHeading = By.xpath("//div[contains(text(),'Search Results')]");
	By FirstClaimIDinResults = By.xpath("//table[@id='summaryTable']//tbody//tr[1]//td//span");
	By ResultsTable = By.xpath("//table[@id='summaryTable']");
	By Heading = By.xpath("//div[contains(text(),'Details')]");
	By ResultsFound = By.xpath("//b[contains(text(),'Results Found')]");
	By NoClaimDataMsg = By.xpath("//p[contains(text(),'No claims data found for the search criteria')]");
	By NoClaimDataMsgOK = By.xpath("//button[contains(text(),'OK')]");
	By ClaimNumberinMwClaimDetails = By.xpath("//span[contains(text(),'Claim Number :')]//following-sibling::span[1]");
	
	public void userenterBillingProviderNPI(String billingProviderNPI) {
		// TODO Auto-generated method stub
		
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		driver.findElement(BillingProviderNPITextBox).sendKeys(billingProviderNPI);
		
	}

	public void userclicksonsearch() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		driver.findElement(SearchButton).click();
	}

	public void uservalidatessearchresultsaredispalyedforMWClaims(String resultAvailable) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(10000);
		boolean ResultnotAvailable = driver.findElements(NoClaimDataMsg).size()>0;
		if(ResultnotAvailable==true) {
			ScenarioContext.setContext(resultAvailable, "false");
			driver.findElement(NoClaimDataMsgOK).click();
		}
		else {
			ScenarioContext.setContext(resultAvailable, "True");
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FirstClaimIDinResults));
		softassert.assertTrue(driver.findElement(ResultsTable).isDisplayed(), "Validating Results table is displayed for MW Claims");
		softassert.assertAll();
		}
	}

	public void userclicksonclaimIDinResults(String claimNumber, String resultAvailable) throws InterruptedException {
		// TODO Auto-generated method stub
		String ResultAvailable =(String) ScenarioContext.getContext(resultAvailable);
		if(ResultAvailable.equals("false")) {
			//Do Nothing
		}
		else {
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(ResultsFound));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(FirstClaimIDinResults));
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		String ClaimIDinResult = driver.findElement(FirstClaimIDinResults).getText();
		ScenarioContext.setContext(claimNumber, ClaimIDinResult);
		driver.findElement(FirstClaimIDinResults).click();
		}
	}

	public void uservalidateswhetherclaimnumberlinktakesusertoMWclaimdetailspage(String claimNumber, String resultAvailable) {
		// TODO Auto-generated method stub
		String ResultAvailable =(String) ScenarioContext.getContext(resultAvailable);
		if(ResultAvailable.equals("false")) {
			//Do Nothing
		}
		else {
		
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(Heading));
		wait.until(ExpectedConditions.elementToBeClickable(ClaimNumberinMwClaimDetails));
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		String ClaimNumberinMWClaimDetailsPage = driver.findElement(ClaimNumberinMwClaimDetails).getText();
		String ClaimNumberValue = (String ) ScenarioContext.getContext(claimNumber);
		softassert.assertTrue(ClaimNumberValue.equals(ClaimNumberinMWClaimDetailsPage), "Validating calim Number link taken user to the corresponding MW Claim Details Page");
		softassert.assertAll();
		}
	}

}
