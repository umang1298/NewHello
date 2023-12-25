package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class ProducerRegistrationPage extends TestBase {

	public ProducerRegistrationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver =driver;
	}

	//Page Factory
	
	By UserIdTextBox = By.xpath("//input[@id='userId']");
	By SubmitButton = By.xpath("//input[@type='submit']");
	By FirstNameTextBox = By.xpath("//input[@id='firstName']");
	By LastNameTextBox = By.xpath("//input[@id='lastName']");
	By SSNTextBox = By.xpath("//input[@id='ssn']");
	By NPNTextBox = By.xpath("//input[@id='npn']");
	By EmailAddressTextBox = By.xpath("//input[@id='emailAddress']");
	By ConfirmAddressTextBox = By.xpath("//input[@id='confirmEmailAddress']");
	By PasswordTextBox = By.xpath("//input[@id='password']");
	By ConfirmPasswordTextBox = By.xpath("//input[@id='verifyPassword']");
	By ChallengeQuestionTextBox = By.xpath("//input[@id='challengeQuestion']");
	By SecurityAnswerTextBox = By.xpath("//input[@id='challengeAnswer']");
	By ConfirmAnswerTextBox = By.xpath("//input[@id='verifyChallengeAnswer']");
	By DeclarationCheckBox =  By.xpath("//span[@class='checkmark']");
	By Registrationsuccessmessage = By.xpath("//div[contains(text(),'You have successfully registered for the HAP Website')]");
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	
	public void userenterstheregistrationdetails(String username, String firstName, String lastName, String sSN,
			String nPN) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(UserIdTextBox).sendKeys(username);
		driver.findElement(FirstNameTextBox).sendKeys(firstName);
		driver.findElement(LastNameTextBox).sendKeys(lastName);
		driver.findElement(SSNTextBox).sendKeys(sSN);
		driver.findElement(NPNTextBox).sendKeys(nPN);
	}
	public void userentersemailaddressandpassword(String emailAddress, String password) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(EmailAddressTextBox).sendKeys(emailAddress);
		driver.findElement(ConfirmAddressTextBox).sendKeys(emailAddress);
		driver.findElement(PasswordTextBox).sendKeys(password);
		driver.findElement(ConfirmPasswordTextBox).sendKeys(password);
		
	}
	public void usercreateschallengequestionsandverifiesuserregistration() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(ChallengeQuestionTextBox).sendKeys("Q");
		driver.findElement(SecurityAnswerTextBox).sendKeys("A");
		driver.findElement(ConfirmAnswerTextBox).sendKeys("A");
		driver.findElement(DeclarationCheckBox).click();
		driver.findElement(SubmitButton).click();
		Thread.sleep(2000);
		softassert.assertTrue(driver.findElement(Registrationsuccessmessage).isDisplayed(), "Validating whether success message for producer registration is displayed");
		softassert.assertAll();
	}
	
}
