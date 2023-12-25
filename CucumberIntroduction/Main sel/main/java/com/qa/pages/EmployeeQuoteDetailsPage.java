package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class EmployeeQuoteDetailsPage extends TestBase{

	public EmployeeQuoteDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver=driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	//Page Factory
	By SBCLink = By.xpath("//a[contains(text(),'SBC')]");
	By BenefitSummaryLink = By.xpath("//a[contains(text(),'Benefit Summary')]");
	By ViewRateSheetButton = By.xpath("//button[contains(text(),'View Rate Sheet')]");
	By BackToSearch = By.xpath("//a[contains(text(),'Back to Search')]");
	
	public void uservalidatesquotedetailspageisdisplayed(String quoteID) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SBCLink));
		String QuoteIDValue = (String) ScenarioContext.getContext(quoteID);
		WebElement QuoteIDHeading = driver.findElement(By.xpath("//span[contains(text(),'"+QuoteIDValue+"')]"));
		Assert.assertTrue(QuoteIDHeading.isDisplayed(), "Validating Quote ID details page is displayed");
	}

	public void uservalidatesSBCBenefitSummaryAndViewRateSheetfunctionalities(String sBC, String benefitSumamry,
			String viewRatessheet) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SBCLink));
		softassert.assertTrue(driver.findElement(SBCLink).isDisplayed(), "Validating SBC Link");
		softassert.assertTrue(driver.findElement(BenefitSummaryLink).isDisplayed(), "Validating Benefit Summary Link");
		softassert.assertTrue(driver.findElement(ViewRateSheetButton).isDisplayed(), "Validating View Rate Sheet Button Button");
	}

	public void userclicksbacktosearch() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(BackToSearch));
		driver.findElement(BackToSearch).click();
		
	}
	
}
