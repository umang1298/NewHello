package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;

public class MemberPortalUpdateProfilePage extends TestBase {

	public MemberPortalUpdateProfilePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//PageFactorys
	By UpdateProfileHeading = By.xpath("//div[contains(text(),'Update')]");
	By Emailaddressupdatelink = By.xpath("//a[@id='email-editbtn']");
	By UpdateButton = By.xpath("//button[contains(text(),'Update')]");
	By EmailAddressTextBox = By.xpath("//input[@id='emailAddress']");
	By ConfirmationEmailMessage = By.xpath("//p[contains(text(),'Your email has been updated')]");
	By OkButton = By.xpath("(//button[contains(text(),'OK')])[1]");
	By UIEmailText = By.xpath("//a[@id='email-editbtn']//..//span");
	By UpdatePhoneNumberLink = By.xpath("//a[@id='cellphone-editbtn']");
	By PhoneNumberTextBox = By.xpath("//input[@id='cellPhone']");
	By UpdatePhoneNumberButton = By.xpath("//button[@id='save-cellphone']");
	By ConfirmationPhoneMessage = By.xpath("(//button[contains(text(),'OK')])[2]");
	By OKSubmitPhonenumberButton = By.xpath("//button[contains(text(),'OK')]");
	By PhoneNumberinUI = By.xpath("//a[@id='cellphone-editbtn']//..//span");
	By UpdateChallengeQuestionLink = By.xpath("//a[@id='challenge-editbtn']");
	By ChallengequestionTextBox = By.xpath("//input[@id='challengeQuestion']");
	By ChallengeAnswerTextBox = By.xpath("//input[@id='challengeAnswer']");
	By ChallengeConfirmAnswerTextBox = By.xpath("//input[@id='challengeConfirmAnswer']");
	By ChallengeQuestionSubmitbutton = By.xpath("(//button[contains(text(),'Update')])[4]");
	By ConfirmationChallengeQuestionButton = By.xpath("//button[@type='submit']");
	By ConfirmationChallengeMessageButton = By.xpath("//p[contains(text(),'Your Challenge Question and Answer have been updated')]");
	By ChallengeQuestioninUI = By.xpath("(//span[contains(text(),'Q')])[2]");
	By PaperlessSaveButton = By.xpath("//button[@id='save-paperless']");
	By PaperlessEmailUpdateLink = By.xpath("//a[@id='paperlessemail-editbtn']");
	By PaperlessEmailTextBox = By.xpath("//input[@id='email']");
	By PaperlessEmailUpdateButton = By.xpath("//button[@id='save-email-paperless']");
	By PaperlessEmailButtonEOB = By.xpath("//input[@id='eobemail']//..//label");
	By PaperlessPaperButtonEOB = By.xpath("//input[@id='eobpaper']//..//label");
	By ConfirmationOkbuttonforpaperless = By.xpath("//button[contains(text(),'OK')]");
	By EMailIDTextboxInUI = By.xpath("//a[@id='paperlessemail-editbtn']//..//span");
    By SignupDocumentConfirmationbutton = By.xpath("//button[contains(text(),'OK')]");
	By UpdatePasswordLink = By.xpath("//a[@id='password-editbtn']");
	By CurrentPasswordTextBox = By.xpath("//input[@id='currentPassword']");
	By NewPasswordTextBox = By.xpath("//input[@id='newPassword']");
	By ConfirmPasswordTextBox = By.xpath("//input[@id='confirmPassword']");
	By SavePasswordUpdateButton = By.xpath("//button[@id='save-change-password']");
	By ConfirmationPasswordUpdateButton = By.xpath("(//button[@type='submit'])[1]");
	
	
	public void userupdatesandvalidatestheprofilesecurityinformation(String email) throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(UpdateProfileHeading));
		driver.findElement(Emailaddressupdatelink).click();
		driver.findElement(EmailAddressTextBox).clear();
		driver.findElement(EmailAddressTextBox).sendKeys(email);
		driver.findElement(UpdateButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(OkButton));
		driver.findElement(OkButton).click();
		String UIText = driver.findElement(UIEmailText).getText();
		System.out.println("UpdatedEmail:"+ UIText );
		Assert.assertEquals(UIText, email,"Validating Information are successful");
		wait.until(ExpectedConditions.elementToBeClickable(Emailaddressupdatelink));
		driver.findElement(Emailaddressupdatelink).click();
		driver.findElement(EmailAddressTextBox).clear();
		driver.findElement(EmailAddressTextBox).sendKeys("NONE@HAP.ORG");
		driver.findElement(UpdateButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(OkButton));
		driver.findElement(OkButton).click();
		Thread.sleep(5000);
		String EmailIDINUI = driver.findElement(UIEmailText).getText();
		System.out.println("EmailIDinUI"+ EmailIDINUI );
		Assert.assertEquals(EmailIDINUI, "NONE@HAP.ORG","Validating Information Updated Succesfully");
	}


	public void userupdatesphonenmberandvalidatestheprofilesecurityinformation() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(UpdateProfileHeading));
		int Number = CommonMethods.getRndNumber();
		String num = String.valueOf(Number);		
		driver.findElement(UpdatePhoneNumberLink).click();
		driver.findElement(PhoneNumberTextBox).clear();
		driver.findElement(PhoneNumberTextBox).sendKeys(num);
		driver.findElement(UpdatePhoneNumberButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(OKSubmitPhonenumberButton));
		driver.findElement(OKSubmitPhonenumberButton).click();
		String PhonenumberinUI = driver.findElement(PhoneNumberinUI).getText();
		String LastFourDigit = PhonenumberinUI.substring(11,14);
		System.out.println("LastDigitInUI:"+ LastFourDigit);
		String Lastfour = num.substring(7, 10);
		System.out.println("LastfourEntered:" +Lastfour);
	   Assert.assertEquals(LastFourDigit, Lastfour, "Validating information updated successfully");
	}

	

	public void userupdateschallengequestionandvalidatestheprofilesecurityinformation(String challengeQuestion) throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(UpdateProfileHeading));
		driver.findElement(UpdateChallengeQuestionLink).click();
		driver.findElement(ChallengequestionTextBox).clear();
		driver.findElement(ChallengequestionTextBox).sendKeys(challengeQuestion);
		driver.findElement(ChallengeAnswerTextBox).clear();
		driver.findElement(ChallengeAnswerTextBox).sendKeys("A");
		driver.findElement(ChallengeConfirmAnswerTextBox).clear();
		driver.findElement(ChallengeConfirmAnswerTextBox).sendKeys("A");
		WebElement click = driver.findElement(ChallengeQuestionSubmitbutton);
		js.executeScript("arguments[0].click();", click);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(ConfirmationChallengeMessageButton));
		driver.findElement(ConfirmationChallengeQuestionButton).click();
		String ChallengeQuestionInUI = driver.findElement(ChallengeQuestioninUI).getText();
		System.out.println("UpdatedQuestion:" + ChallengeQuestionInUI);
		Assert.assertEquals(ChallengeQuestionInUI, challengeQuestion, "Validating information updated successfully");
		
	}


	public void userupdatesemailidandvalidatestheinformation(String email) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(PaperlessSaveButton));
		driver.findElement(PaperlessEmailUpdateLink).click();
		driver.findElement(PaperlessEmailTextBox).clear();
		driver.findElement(PaperlessEmailTextBox).sendKeys(email);
		driver.findElement(PaperlessEmailUpdateButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(ConfirmationOkbuttonforpaperless));
		driver.findElement(ConfirmationOkbuttonforpaperless).click();
		String UIText = driver.findElement(EMailIDTextboxInUI).getText();
		System.out.println("UpdatedEmail:"+ UIText );
		Assert.assertEquals(UIText, email,"Validating Information are successful");
		driver.findElement(PaperlessEmailUpdateLink).click();
		driver.findElement(PaperlessEmailTextBox).clear();
		driver.findElement(PaperlessEmailTextBox).sendKeys("TEST4@HAP.ORG");
		driver.findElement(PaperlessEmailUpdateButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(ConfirmationOkbuttonforpaperless));
		driver.findElement(ConfirmationOkbuttonforpaperless).click();
		String EmailIDINUI = driver.findElement(EMailIDTextboxInUI).getText();
		System.out.println("EmailIDinUI"+ EmailIDINUI );
		Assert.assertEquals(EmailIDINUI, "TEST4@HAP.ORG","Validating Information Updated Succesfully");
	}


	public void usersignupforthedeliveryofdocumentsandvalidates() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(PaperlessSaveButton));
		boolean EOBEmailisselected = driver.findElement(PaperlessEmailButtonEOB).isSelected();
		boolean EOBPaperSelected = driver.findElement(PaperlessPaperButtonEOB).isSelected();
		if(EOBEmailisselected=true)
		{
			driver.findElement(PaperlessPaperButtonEOB).click();
		}
		else
		{
			driver.findElement(PaperlessEmailButtonEOB).click();
		}
		
		driver.findElement(PaperlessSaveButton).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(SignupDocumentConfirmationbutton));
		driver.findElement(SignupDocumentConfirmationbutton).click();
		
	}


	public void userupdatesthepasswordandvalidatesthepasswordareupdated(String password, String newpassword, String confirmpassword) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(UpdatePasswordLink));
		driver.findElement(UpdatePasswordLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(SavePasswordUpdateButton));
		driver.findElement(CurrentPasswordTextBox).click();
		driver.findElement(CurrentPasswordTextBox).clear();
		driver.findElement(CurrentPasswordTextBox).sendKeys(password);
		System.out.println("Currentpassword: " +password );
		driver.findElement(NewPasswordTextBox).click();
		driver.findElement(NewPasswordTextBox).clear();
		driver.findElement(NewPasswordTextBox).sendKeys(newpassword);
		System.out.println("Newpassword: " +newpassword );
		driver.findElement(ConfirmPasswordTextBox).click();
		driver.findElement(ConfirmPasswordTextBox).clear();
		driver.findElement(ConfirmPasswordTextBox).sendKeys(confirmpassword);
		System.out.println("Confirmpassword: " +confirmpassword );
		driver.findElement(SavePasswordUpdateButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(ConfirmationPasswordUpdateButton));
		driver.findElement(ConfirmationPasswordUpdateButton).click();
	}



}
