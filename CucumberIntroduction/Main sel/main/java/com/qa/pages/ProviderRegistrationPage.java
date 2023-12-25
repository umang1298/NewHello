package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;

public class ProviderRegistrationPage extends TestBase{

	
	public ProviderRegistrationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver =driver;
	}
	
	//Page Factory 
	
	By ContinueButton = By.xpath("//button[@id='continuebutton']");
	By LastNameTextBox = By.xpath("//input[@name='regLastName']");
	By FirstNameTextBox = By.xpath("//input[@name='regFirstName']");
	By PhoneNumberTextBox = By.xpath("//input[@id='phoneNumberDisplay']");
	By EmailAddressTextBox = By.xpath("//input[@name='regEmailAddr']");
	By ConfirmEmailTextBox = By.xpath("//input[@name='regConfirmEmailAddr']");
	By AlternateEmailTextBox = By.xpath("//input[@name='regAlternateEmailAddr']");
	By PasswordTextBox = By.xpath("//input[@id='regPassword']");
	By ConfirmPasswordTextBox = By.xpath("//input[@id='regConfirmPassword']");
	By ChallengeQuestionTextBox = By.xpath("//input[@name='regChallengeQn']");
	By ChallengeAnswerTextBox = By.xpath("//input[@id='regChallengeAns']");
	By DeclarationCheckBox = By.xpath("//input[@id='regTermsAndConditions']");
	By Type1NpiTextBox = By.xpath("//input[@id='type1npi']");
	By TaxIDTextBox = By.xpath("//input[@name='regTaxId']");
	By BillingOfficeNPITextBox = By.xpath("//input[@name='regNpiBillOffice']");
	By Type2NPITextBox = By.xpath("//input[@id='type2npi']");
	By VendorIdTextBox = By.xpath("//input[@name='regVendorId']");
	By LogInLink = By.xpath("//a[contains(text(),'log in')]");
	By ThankyouHeading = By.xpath("//div[contains(text(),'Thank you for agreeing to be the ID Administrator.')]");
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	

	public void userentersoptionsforTellUsAboutOrganisation(String optionForContracted, String optionForOfficeType,
			String optionForRA) throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ContinueButton));
		
		if(optionForRA.equals("NA")) {
		driver.findElement(By.xpath("//input[@id='"+optionForContracted+"']//..//label")).click();
		driver.findElement(By.xpath("//input[@id='"+optionForOfficeType+"']//..//label")).click();
		}
		else if(optionForOfficeType.equals("NA")) {
			driver.findElement(By.xpath("//input[@id='"+optionForContracted+"']//..//label")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@id='"+optionForRA+"']//..//label")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='"+optionForContracted+"']//..//label")).click();
			driver.findElement(By.xpath("//input[@id='"+optionForOfficeType+"']//..//label")).click();
			driver.findElement(By.xpath("//input[@id='"+optionForRA+"']//..//label")).click();
		}
		
	}


	public void userfillsyourinformationforProviderRegistration(String nPI, String taxId, String vendorId,
			String type) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(2000);
		switch (type) {
		
		case "Individual with RA":
			driver.findElement(Type1NpiTextBox).sendKeys(nPI);
			driver.findElement(TaxIDTextBox).sendKeys(taxId);
			driver.findElement(VendorIdTextBox).sendKeys(vendorId);
			break;
			
		case "Group with RA":
			driver.findElement(Type2NPITextBox).sendKeys(nPI);
			driver.findElement(TaxIDTextBox).sendKeys(taxId);
			driver.findElement(VendorIdTextBox).sendKeys(vendorId);
			break;
			
		case "Ancillary with RA":	
			driver.findElement(Type2NPITextBox).sendKeys(nPI);
			driver.findElement(TaxIDTextBox).sendKeys(taxId);
			driver.findElement(VendorIdTextBox).sendKeys(vendorId);
			break;
			
		case "Hospital with RA":	
			driver.findElement(Type2NPITextBox).sendKeys(nPI);
			driver.findElement(TaxIDTextBox).sendKeys(taxId);
			driver.findElement(VendorIdTextBox).sendKeys(vendorId);
			break;
			
		case "Billing Office":	
			driver.findElement(BillingOfficeNPITextBox).sendKeys(nPI);
			driver.findElement(TaxIDTextBox).sendKeys(taxId);
			driver.findElement(VendorIdTextBox).sendKeys(vendorId);
			break;
			
		case "Individual without RA":
			driver.findElement(Type1NpiTextBox).sendKeys(nPI);
			driver.findElement(TaxIDTextBox).sendKeys(taxId);
			break;
			
		case "Group without RA":
			driver.findElement(Type2NPITextBox).sendKeys(nPI);
			driver.findElement(TaxIDTextBox).sendKeys(taxId);
			break;
			
		case "Ancillary without RA":	
			driver.findElement(Type2NPITextBox).sendKeys(nPI);
			driver.findElement(TaxIDTextBox).sendKeys(taxId);
			break;
			
		case "Hospital without RA":	
			driver.findElement(Type2NPITextBox).sendKeys(nPI);
			driver.findElement(TaxIDTextBox).sendKeys(taxId);
			break;
			
		case "Non Contracted with RA":
			driver.findElement(TaxIDTextBox).sendKeys(taxId);
			driver.findElement(VendorIdTextBox).sendKeys(vendorId);
			break;
			
		}
		
	}


	public void userfillsdetailsforIDadministrationandConsent() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		
		driver.findElement(LastNameTextBox).sendKeys("Test");
		driver.findElement(FirstNameTextBox).sendKeys("Automation");
		driver.findElement(PhoneNumberTextBox).sendKeys("9894557760");
		driver.findElement(EmailAddressTextBox).sendKeys("test@hap.org");
		driver.findElement(ConfirmEmailTextBox).sendKeys("test@hap.org");
		driver.findElement(PasswordTextBox).sendKeys("Today1234");
		driver.findElement(ConfirmPasswordTextBox).sendKeys("Today1234");
		driver.findElement(ChallengeQuestionTextBox).sendKeys("Q");
		driver.findElement(ChallengeAnswerTextBox).sendKeys("A");
		Thread.sleep(2000);
		driver.findElement(DeclarationCheckBox).click();
		Thread.sleep(2000);
		driver.findElement(ContinueButton).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(LogInLink));
		Assert.assertTrue(driver.findElement(ThankyouHeading).isDisplayed(), "Validating Thank you message is displayed after Registration");
		
	}


	public void userdeletesrecordfromManageUsersTable(String nPI, String vendorId) {
		// TODO Auto-generated method stub
		
		String Query = "delete from MANAGE_USERS where user_id in ('"+nPI+"','"+vendorId+"')";
		String Query1 = "commit";
		CommonMethods.executeSql(Query);
		CommonMethods.executeSql(Query1);
		
	}

	

	
	
}

