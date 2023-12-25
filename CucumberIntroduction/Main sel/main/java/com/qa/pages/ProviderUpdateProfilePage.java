package com.qa.pages;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import junit.framework.Assert;

public class ProviderUpdateProfilePage extends TestBase {

	public ProviderUpdateProfilePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory 
	By PhoneNumberTextBox = By.xpath("//input[@id='phone']");
	By SubmitButton = By.xpath("//input[@type='submit']");
	By PopUpOkButton = By.xpath("//button[@id='backtodetails']");
	By UpdatePasswordCancelButton = By.xpath("//button[contains(text(),'Cancel')]");
	By UpdateProfilePageHeading = By.xpath("//div[contains(text(),'Update Profile')]");
	By TrainingandDisabilityHeading = By.xpath("//div[contains(text(),'Training & Disability Accommodations')]");
	By NoneofTheseInput = By.xpath("(//input[@value='None Of These'])[1]");
	By NoneOfTheseRadioButton = By.xpath("(//span[@class='checkmark'])[12]");
	By SubmitButtonAdminUser = By.xpath("//button[contains(text(),'Submit')]");
	By PopupOkButton = By.xpath("(//button[contains(text(),'OK')])[2]");
	By SuccessMessage = By.xpath("//*[@id='updateSuccess-modal']/div/div/div[2]/div/span");
	By ContactDetailsEdit = By.id("contact-editbtn");
	By EmailTextBox = By.xpath("//input[@id='emailAddress']");
	By ContactSave = By.xpath("//button[@id='contact-save']");
	
	public void UserUpdatesTheInformationAndClicksOnSubmitButton(String info) throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ContactDetailsEdit));
		driver.findElement(ContactDetailsEdit).click();
		wait.until(ExpectedConditions.elementToBeClickable(ContactSave));
		driver.findElement(EmailTextBox).clear();
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		int random = CommonMethods.getRndNumber();
		driver.findElement(EmailTextBox).sendKeys("none"+random+"@hap.org");
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.findElement(PhoneNumberTextBox).click();
		Thread.sleep(5000);
		int RandomNumber = CommonMethods.getRndNumber();
		String Phonenumber = String.valueOf(RandomNumber);
		System.out.println("Number-->: "+ RandomNumber);
		driver.findElement(PhoneNumberTextBox).clear();
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.findElement(PhoneNumberTextBox).sendKeys(Phonenumber);
		Thread.sleep(5000);
		driver.findElement(ContactSave).click();
		wait.until(ExpectedConditions.elementToBeClickable(PopUpOkButton));
		driver.findElement(PopUpOkButton).click();
		String PhoneNumberLastFourDigit = Phonenumber.substring(6, 10);
		System.out.println("SetPhone-->"+PhoneNumberLastFourDigit);
		ScenarioContext.setContext(info, PhoneNumberLastFourDigit);
//		wait.until(ExpectedConditions.elementToBeClickable(UpdatePasswordCancelButton));
//		driver.findElement(UpdatePasswordCancelButton).click();
	}

	public void UserValidatesInformationUpdatesSuccessfully(String info) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ContactDetailsEdit));
		String PhoneNumberInUI = driver.findElement(PhoneNumberTextBox).getAttribute("value");
		String UIPhoneLastFour = PhoneNumberInUI.substring(10, 14);
		System.out.println("UIPhoneLastFour-->"+UIPhoneLastFour);
		System.out.println("phonenumberinUI"+PhoneNumberInUI);
		String Info = (String) ScenarioContext.getContext(info);
		Assert.assertEquals("Validating Information Updated Succesfully",Info, UIPhoneLastFour);
		
	}

	public void UserValidatesUpdateProfilePageIsDisplayed() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(UpdateProfilePageHeading));
		wait.until(ExpectedConditions.elementToBeClickable(TrainingandDisabilityHeading));
		Assert.assertTrue("Validating the Training and disability heading is displayed in the update profile page for admin user",driver.findElement(TrainingandDisabilityHeading).isDisplayed());
	}

	public void UserSelectsNoneofTheseandClicksonSubmitButton() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButtonAdminUser));
		Thread.sleep(3000);
		WebElement NoneofThese = driver.findElement(NoneofTheseInput);
		if(NoneofThese.isSelected()) {
			driver.findElement(SubmitButtonAdminUser).click();
		}
		else {
		driver.findElement(NoneOfTheseRadioButton).click();
		driver.findElement(SubmitButtonAdminUser).click();
		}
	}

	public void UserValidatesTheUpdatedInformationMessageIsDisplayed() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(PopupOkButton));
		Assert.assertTrue("Validating Updated information Message is displayed",driver.findElement(SuccessMessage).isDisplayed());
		driver.findElement(PopupOkButton).click();
		
	}
	
	
}
