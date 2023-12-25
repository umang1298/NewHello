package com.qa.pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;

public class EmployeeHEDISAdvancesearchPage extends TestBase {
	
	public EmployeeHEDISAdvancesearchPage() {
		// TODO Auto-generated constructor stub
	    
		TestBase.driver = driver;
}
	
	SoftAssert softAssert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	//Page Factory
	By HEDISMemberadvancesearchpageheading = By.xpath("//div[contains(text(),'HEDIS Member Outreach - Advanced Search')]");
	By SaveButton = By.xpath("//button[@id='save-user-button']");
	By SelectFilterDropdown = By.xpath("//select[@id='select-picker']");
	By DropdownButton = By.xpath("(//button[@type='button'])[3]");
	By InteractionSelectDropdown = By.xpath("(//button[@type='button'])[2]");
	By SecondselectdropdownButton = By.xpath("(//span[contains(text(),'Select')])[2]");
	By AdultBMIDropdownButton = By.xpath("//span[@class='checkmark']");
	By HedisMeasuresDropdownButton = By.xpath("(//button/span[contains(text(),'Select')])[1]");
	By AdultBMICheckBox = By.xpath("//label[contains(text(),'Adult BMI Assessment')]/span");
	By BreastCancerCheckBox = By.xpath("//label[contains(text(),'Breast Cancer Screening')]/span");
	By CervicalCancerCheckBox = By.xpath("//label[contains(text(),'Cervical Cancer Screening')]/span"); 
	By SelectDropdownButton = By.xpath("//select[@name='interaction']");
	By InteractionDropdownButton = By.xpath("//span[contains(text(),'interaction')]");
	By InteractionButton = By.xpath("(//span[contains(text(),'interaction')])[2]");
	By Fromdate = By.xpath("//input[@id='fromDate']");
	By Todate = By.xpath("//input[@id='toDate']");
	By TargetListTextBox = By.xpath("//input[@id='targetListName']");
	By TargetSaveButton = By.xpath("//button[contains(text(),'Save')]");
	By OkButton = By.xpath("(//button[@type='button'])[12]");
	By TargetlistConfirmMessage = By.xpath("//span[@id='successMessage']");
	By RunButton = By.xpath("//button[contains(text(),'Run')]");
	By SavedTargetListTable = By.xpath("(//div[@class='bootstrap-table'])[1]");
	By TargetListName = By.xpath("//td[contains(text(),'Test Automation')]");
	By Executebutton = By.xpath("//span[@id='Test Automation']");
	By Deletebutton = By.xpath("(//span[contains(text(),'Delete')])[1]");
	By DownloadButton = By.xpath("//button[contains(text(),'Download')]");
	By HedisResultsHeading = By.xpath("//div[contains(text(),'HEDIS Measure')]");
	By HedisResultsTable = By.xpath("(//div[@class='bootstrap-table'])[2]");
	By DeleteSuccessMessage = By.xpath("//span[@id='successMessage']");
	By DeleteOkButton = By.xpath("(//button[contains(text(),'OK')])[1]");
	
	
	
	public void usercreatestargetlistofmemberwithHedismeasures() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SaveButton));
		wait.until(ExpectedConditions.presenceOfElementLocated(HEDISMemberadvancesearchpageheading));
		driver.findElement(HedisMeasuresDropdownButton).click();
		Thread.sleep(3000);
//		driver.findElement(AdultBMICheckBox).click();
		driver.findElement(BreastCancerCheckBox).click();
		driver.findElement(CervicalCancerCheckBox).click();
		WebElement Dropdown = driver.findElement(SelectDropdownButton);
		Select selectInteractiondropdown = new Select(Dropdown);
		selectInteractiondropdown.selectByValue("Y");
		Thread.sleep(3000);
		driver.findElement(Fromdate).clear();
		driver.findElement(Fromdate).sendKeys(CommonMethods.LastYearDate());
		driver.findElement(Todate).clear();
		driver.findElement(Todate).sendKeys(CommonMethods.CurrentDate());
		Thread.sleep(3000);
		driver.findElement(TargetListTextBox).clear();
		driver.findElement(TargetListTextBox).sendKeys("Test Automation");
		driver.findElement(TargetSaveButton).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(OkButton));
		WebElement confirmationMessage = driver.findElement(TargetlistConfirmMessage);
//		softAssert.assertTrue(confirmationMessage.equals("Target list has been created"));
		softAssert.assertTrue(confirmationMessage.isDisplayed(), "Validating Target list creation Confirmation Message is displayed");
		driver.findElement(OkButton).click();
		softAssert.assertAll();
	}

	public void userexecutesthesavedtargetlistandverifiestheresult() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(Executebutton));
		wait.until(ExpectedConditions.presenceOfElementLocated(SavedTargetListTable));
		String TargetListname = driver.findElement(TargetListName).getText();
		System.out.println("TargetResult:" + TargetListname );
		softAssert.assertTrue(TargetListname.equals("Test Automation"));
		driver.findElement(Executebutton).click();	
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(DownloadButton));
		wait.until(ExpectedConditions.presenceOfElementLocated(HedisResultsHeading));
		WebElement HedisMeasureresults = driver.findElement(HedisResultsTable);
		softAssert.assertTrue(HedisMeasureresults.isDisplayed(), "Validating HEDIS Measure results is displayed");
		driver.findElement(Deletebutton).click();
		wait.until(ExpectedConditions.elementToBeClickable(DeleteOkButton));
		WebElement SuccessMessage = driver.findElement(DeleteSuccessMessage);
		softAssert.assertTrue(SuccessMessage.isDisplayed(), "Validating Target list deleted Confirmation Message is displayed");
		driver.findElement(DeleteOkButton).click();
		softAssert.assertAll();
//		File hedismeasuredownloadrpt = new File (System.getProperty("user.home") + "/Downloads/" + "hedis.panel.rpt." + dateinformat +".xls");
//		softAssert.assertTrue(hedismeasuredownloadrpt.exists(), "Verifying excel file is downloaded for HEDIS");
//		hedismeasuredownloadrpt.delete();
	}
	
  
}
