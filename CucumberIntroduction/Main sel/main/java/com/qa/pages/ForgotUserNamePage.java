package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;

public class ForgotUserNamePage extends TestBase {

	public ForgotUserNamePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver =driver;
	}
	
	//Page Factory
	By SubmitButton = By.xpath("//input[@type='submit']");
	By SecurityAnsContinueProspect = By.xpath("//div[@id='submitbuttondiv']//button");
	By ProspectContinueButton = By.xpath("//button[contains(text(),'Continue')]");
	By EmailaddressTextbox = By.xpath("//input[@id='emailAddress']");
	By EmailaddressTextboxProspect = By.xpath("//input[@id='email']");
	By SSNTextBox = By.xpath("//input[@id='ssn']");
	By NPNTextBox = By.xpath("//input[@id='npn']");
	By DOBTextBoxProspect = By.xpath("//input[@id='dateOfBirth']");
	By AnswerPageHeading = By.xpath("//div[contains(text(),'Answer')]");
	By ChallengeanswerTextBox = By.xpath("//input[@id='challengeAnswer']");
	
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;

	public void userentersthecredentialsandclicksoncontinuebutton(String emailAddress, String sSN, String nPN) {
	   // TODO Auto-generated method stub
	   WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	   wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
	   driver.findElement(EmailaddressTextbox).sendKeys(emailAddress);
	   driver.findElement(SSNTextBox).sendKeys(sSN);
	   driver.findElement(NPNTextBox).sendKeys(nPN);
	   driver.findElement((SubmitButton)).click();	
	}

	public void userentersthesecurityanswerforchallengequestionandvalidatesthepresenceofusername(String securityAnswer) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(AnswerPageHeading));
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(ChallengeanswerTextBox).sendKeys(securityAnswer);
		driver.findElement(SubmitButton).click();
		Thread.sleep(6000);
		WebElement UserNamePresent = driver.findElement(By.xpath("//div[contains(text(),'Your username is : ')]//span"));
		System.out.println("Username: " +UserNamePresent.getText());
		softassert.assertTrue(UserNamePresent.isDisplayed(), "Validating whether user name is displayed");
		softassert.assertAll();
	}

	public void userentersthedetailsforProspMemandClicksOnContinueButton(String emailAddress, String sSN, String dOB) throws InterruptedException {
		// TODO Auto-generated method stub
		 	WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		   wait.until(ExpectedConditions.elementToBeClickable(ProspectContinueButton));
		   driver.findElement(EmailaddressTextboxProspect).sendKeys(emailAddress);
		   driver.findElement(SSNTextBox).sendKeys(sSN);
		   //driver.findElement(DOBTextBoxProspect).clear();
		   Thread.sleep(3000);
		   driver.findElement(DOBTextBoxProspect).sendKeys(Keys.chord(Keys.CONTROL, "a"), dOB);
		   Thread.sleep(5000);
		   driver.findElement((ProspectContinueButton)).click();
	}

	public void userenterssecurityanswerforprospectmemberandvalidatesusernamedisplayed(String answer) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(AnswerPageHeading));
		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnsContinueProspect));
		driver.findElement(ChallengeanswerTextBox).sendKeys(answer);
		driver.findElement(SecurityAnsContinueProspect).click();
		Thread.sleep(6000);
		WebElement UserNamePresent = driver.findElement(By.xpath("//div[@id='yourinformationdiv']//b"));
		System.out.println("Username: " +UserNamePresent.getText());
		softassert.assertTrue(UserNamePresent.isDisplayed(), "Validating whether user name is displayed");
		softassert.assertAll();
	}
	
}
