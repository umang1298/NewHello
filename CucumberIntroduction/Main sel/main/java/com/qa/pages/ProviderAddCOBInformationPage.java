package com.qa.pages;

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
import com.qa.util.ScenarioContext;

public class ProviderAddCOBInformationPage extends TestBase {

	public ProviderAddCOBInformationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	By COBInformationPage = By.xpath("(//div[contains(text(),'COB Information')])[2]");
	By InquirytypeDropDown = By.xpath("//select[@id='selectInquiryType']");
	By CarrierNameTextBox = By.xpath("//input[@name='otherCarrierName']");
	By CarrierPhoneNumberTextBox = By.xpath("//input[@name='otherCarrierPhoneNumber']");
	By NameOfPolicyHolderTextBox = By.xpath("//input[@name='otherCarrierPolicyHolderName']");
	By PolicyClaimNumberTextBox = By.xpath("//input[@name='otherCarrierPolicyNumber']");
	By COBCategoryDropDown = By.xpath("//select[@id='selectCOBCategory']");
	By CoverageIncludesDropDown = By.xpath("//select[@id='coverage-picker']");
	By EmployerNameTextBox = By.xpath("//input[@name='otherPolicyEmployerName']");
	By CoverageEffectiveDate = By.xpath("//input[@name='otherPolicyEffectiveDate']");
	By CoverageTerminateDate = By.xpath("//input[@name='otherCoverageTermDate']");
	By HearusRadioButton = By.xpath("//input[@id='yesforcall']");
	By ContactNameTextBox = By.xpath("//input[@name='contactName']");
	By ContactNumberTextBox = By.xpath("//input[@name='contactPhone']");
	By ContactFaxTextBox = By.xpath("//input[@name='contactFax']");
	By AddressTextBox = By.xpath("//input[@name='contactAddress1']");
	By NextButton = By.xpath("//button[contains(text(),'Next')]");
	By NoEligInforMessage = By.xpath("//p[contains(text(),'No eligibility information was found for the entered member')]");
	By NoEligInfoClose = By.xpath("//button[@id='closeButton']");
	
	
	
	public void UserEnterstheCOBInformationinAddInformationPageandValidates(String cOBAvailable) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(6000);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		Thread.sleep(4000);
		Boolean NoEligFound = driver.findElements(NoEligInforMessage).size()>0;
		if(NoEligFound==true) {
			wait.until(ExpectedConditions.elementToBeClickable(NoEligInfoClose));
			driver.findElement(NoEligInfoClose).click();
			ScenarioContext.setContext(cOBAvailable, "COB Not Available");
		}
		
		else {
			ScenarioContext.setContext(cOBAvailable, "COB Available");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(COBInformationPage));
		WebElement Dropdown = driver.findElement(InquirytypeDropDown);
		Select selectInquirytypedropdown = new Select(Dropdown);
		selectInquirytypedropdown.selectByIndex(1);
		driver.findElement(CarrierNameTextBox).sendKeys("Test");
		driver.findElement(CarrierPhoneNumberTextBox).sendKeys("9123456780");
		driver.findElement(NameOfPolicyHolderTextBox).sendKeys("TestAutomation");
		driver.findElement(PolicyClaimNumberTextBox).sendKeys("10411023021");
		WebElement COBDropdown = driver.findElement(COBCategoryDropDown);
		Select COBCategorydropdown = new Select(COBDropdown);
		COBCategorydropdown.selectByIndex(1);
		WebElement CoverageDropdown = driver.findElement(CoverageIncludesDropDown);
		Select CoverageIncludesdropdown = new Select(CoverageDropdown);
		CoverageIncludesdropdown.selectByIndex(1);
		driver.findElement(EmployerNameTextBox).sendKeys("hap");
		driver.findElement(CoverageEffectiveDate).clear();
		driver.findElement(CoverageEffectiveDate).sendKeys(CommonMethods.CurrentDate());
		driver.findElement(CoverageTerminateDate).clear();
		driver.findElement(CoverageTerminateDate).sendKeys("09/14/2026");
		driver.findElement(HearusRadioButton).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ContactFaxTextBox));
		driver.findElement(ContactNameTextBox).sendKeys("TestAutomation");
		driver.findElement(ContactNumberTextBox).sendKeys("9876543210");
		driver.findElement(ContactFaxTextBox).sendKeys("0987654321");
		driver.findElement(AddressTextBox).sendKeys("TamilNadu");
		driver.findElement(NextButton).click();
		
		}
	}
	
	
	

}
