package com.qa.pages;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class ProviderGapsPanelManagementReportsPage extends TestBase {

	public ProviderGapsPanelManagementReportsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver =driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	
	By ProviderDropdown = By.xpath("//select[@id='selectedProvider']");
	By GapsDropdown = By.xpath("//select[@id='selectedTypeOfGap']");
	By HedisTypeDropDown = By.xpath("//select[@id='hedisTypesList']");
	By PanelManagementHeading = By.xpath("//div[contains(text(),'Management Selection Criteria')]");
	By SubmitButton = By.xpath("//button[contains(text(),'Submit')]");
	By DownloadButton = By.xpath("//button[contains(text(),'Download')]");
	By HCCANDHEDIS = By.xpath("(//span[contains(text(),'HCC & HEDIS Program')])[2]");
	By HCCgapresultsheading = By.xpath("//div[contains(text(),'HCC Gaps in Diagnosis')]");
	By HEDISgapresultsheading = By.xpath("//div[contains(text(),'HEDIS Gaps in Care')]");
	By BothHCCandHedisHeading = By.xpath("//div[contains(text(),'HCC and HEDIS Panel Management Report')]");
	By PrintButton = By.xpath("//*[@id='panelForm']/div[1]/a");
	
	
	
	public void userselectsproviderandTypeofgapandclickssubmit(String typeofGap) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(PanelManagementHeading));
		Select ProviderSelect = new Select(driver.findElement(ProviderDropdown));
		ProviderSelect.selectByIndex(3);
		Select GapTypeSelect = new Select (driver.findElement(GapsDropdown));
		switch(typeofGap) {
		case"hcc":
			
			GapTypeSelect.selectByValue(typeofGap);
			wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
			WebElement Submit = driver.findElement(SubmitButton);

			js.executeScript("arguments[0].click();", Submit);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Panel Management Report Results')]")));
			WebElement PanelManagementReportResultsHeading = driver.findElement(By.xpath("//div[contains(text(),'Panel Management Report as of ')]"));
			softassert.assertTrue(PanelManagementReportResultsHeading.isDisplayed(), "Verifying whether Submit button takes user to Panel Management report Results page" );
			WebElement HCCgapresultsheading = driver.findElement(By.xpath("//div[contains(text(),'HCC Gaps in Diagnosis')]"));
			softassert.assertTrue(HCCgapresultsheading.isDisplayed(), "Verifying whether Panel Management report Results for HCC is loaded" );
			WebElement ResultsTableforHCC = driver.findElement(By.xpath("(//table)[1]"));
			softassert.assertTrue(ResultsTableforHCC.isDisplayed(), "Verifying whether Panel Management report Results Table for HCC is loaded" );
			wait.until(ExpectedConditions.elementToBeClickable(PrintButton));
//			driver.findElement(PrintButton).click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.numberOfWindowsToBe(3));
//			ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
//			System.out.println("NewTabs Count:" +newtabs.size());
//			Thread.sleep(3000);
//			driver.switchTo().window(newtabs.get(1));
//			Thread.sleep(3000);
//			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
//			driver.close();
//			driver.switchTo().window(newtabs.get(0));
		break;
		case "hedis" :
			GapTypeSelect.selectByValue(typeofGap);
			//driver.manage().timeouts().pageLoadTimeout(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			//wait.until(ExpectedConditions.presenceOfElementLocated(HedisTypeDropDown));
			//Select HedisTypeSelect = new Select (driver.findElement(HedisTypeDropDown));
			//HedisTypeSelect.getFirstSelectedOption();
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
			WebElement Submit1 = driver.findElement(SubmitButton);

			js.executeScript("arguments[0].click();", Submit1);

			//wait.until(ExpectedConditions.)
			//Thread.sleep(5000);
			//driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			//Select HedisTypeSelect = new Select(driver.findElement(HedisTypeDropDown));
			//HedisTypeSelect.selectByIndex(0);
			//driver.findElement(SubmitButton).click();
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Panel Management Report Results')]")));
			//WebElement PanelManagementReportResultsHeading1 = driver.findElement(By.xpath("//div[contains(text(),'Panel Management Report as of ')]"));
			//softassert.assertTrue(PanelManagementReportResultsHeading1.isDisplayed(), "Verifying whether Submit button takes user to Panel Management report Results page" );
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Panel Management Report Results')]")));
			WebElement HEDISgapresultsheading = driver.findElement(By.xpath("//div[contains(text(),'HEDIS Gaps in Care')]"));
			wait.until(ExpectedConditions.elementToBeClickable(HEDISgapresultsheading));
			softassert.assertTrue(HEDISgapresultsheading.isDisplayed(), "Verifying whether Panel Management report Results for HEDIS is loaded" );
			WebElement ResultsTableforHEDIS = driver.findElement(By.xpath("(//table)[1]"));
			softassert.assertTrue(ResultsTableforHEDIS.isDisplayed(), "Verifying whether Panel Management report Results Table for HEDIS is loaded" );
			wait.until(ExpectedConditions.elementToBeClickable(PrintButton));
//			driver.findElement(By.xpath("//*[@id='panelForm']/div[1]/a")).click();
//			Thread.sleep(3000);
//			ArrayList<String> newtabs1 = new ArrayList<String>(driver.getWindowHandles());
//			driver.switchTo().window(newtabs1.get(1));
//			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
//			driver.close();
//			driver.switchTo().window(newtabs1.get(0));
		break;
		case "both" :
			GapTypeSelect.selectByValue(typeofGap);
			wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
			WebElement Submit2 = driver.findElement(SubmitButton);

			js.executeScript("arguments[0].click();", Submit2);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Panel Management Report Results')]")));
			WebElement BothHCCandHedisHeading = driver.findElement(By.xpath("//div[contains(text(),'HCC and HEDIS Panel Management Report')]"));
			softassert.assertTrue(BothHCCandHedisHeading.isDisplayed(), "Verifying whether Panel Management report Results for both HCC & HEDIS is loaded");
			WebElement ResultsTableforbothHCCandHEDIS = driver.findElement(By.xpath("(//table)[1]"));
			softassert.assertTrue(ResultsTableforbothHCCandHEDIS.isDisplayed(), "Verifying whether Panel Management report Results Table for HEDIS is loaded" );
			wait.until(ExpectedConditions.elementToBeClickable(PrintButton));
//			driver.findElement(By.xpath("//*[@id='panelForm']/div[1]/a")).click();
//			Thread.sleep(3000);
//			ArrayList<String> newtabs2 = new ArrayList<String>(driver.getWindowHandles());
//			driver.switchTo().window(newtabs2.get(1));
//			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
//			driver.close();
//			driver.switchTo().window(newtabs2.get(0));
			break;
		}
		
		softassert.assertAll();
	}

	public void userselectsproviderandTypeofgapandclicksDownload(String typeofGap) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(PanelManagementHeading));
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String dateinformat = dateFormat.format(date);

		Select ProviderSelect = new Select(driver.findElement(ProviderDropdown));
		ProviderSelect.selectByIndex(3);
		Select GapTypeSelect = new Select (driver.findElement(GapsDropdown));
		switch(typeofGap) {
		case"hcc":
			
			GapTypeSelect.selectByValue(typeofGap);
			driver.findElement(DownloadButton).click();
			Thread.sleep(50000);
			File hccdownloadrpt = new File (System.getProperty("user.home") + "/Downloads/" + "hcc.panel.report.export." + dateinformat +".xls");
			System.out.println("File " + hccdownloadrpt);
			softassert.assertTrue(hccdownloadrpt.exists(), "Verifying excel file is downloaded for HCC");
			hccdownloadrpt.delete();
		break;
		case "hedis" :
			GapTypeSelect.selectByValue(typeofGap);	
			//Select HedisTypeSelect = new Select (driver.findElement(HedisTypeDropDown));
			//HedisTypeSelect.getFirstSelectedOption();
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
			WebElement Download = driver.findElement(DownloadButton);

			js.executeScript("arguments[0].click();", Download);

			Thread.sleep(50000);
			File hedisdownloadrpt = new File (System.getProperty("user.home") + "/Downloads/" + "hedis.panel.rpt." + dateinformat +".xls");
			System.out.println("File " + hedisdownloadrpt);
			softassert.assertTrue(hedisdownloadrpt.exists(), "Verifying excel file is downloaded for HEDIS");
			WebElement HCCANDHEDISLINK = driver.findElement(HCCANDHEDIS);
			js.executeScript("arguments[0].click();", HCCANDHEDISLINK);
			hedisdownloadrpt.delete();
		break;
		case "both" :	
			GapTypeSelect.selectByValue(typeofGap);	
			driver.findElement(DownloadButton).click();
			Thread.sleep(50000);
			File hccandhedisdownloadrpt = new File (System.getProperty("user.home") + "/Downloads/" + "hcc.hedis.panel.rptt." + dateinformat +".xls");
			System.out.println("File " + hccandhedisdownloadrpt);
			softassert.assertTrue(hccandhedisdownloadrpt.exists(), "Verifying excel file is downloaded for HCC and hedis");
			hccandhedisdownloadrpt.delete();
		break;
	}
		softassert.assertAll();
	}

	

}
