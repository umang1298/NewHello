package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class EmployeeSecurityAdminToolUpdatePasswordPage extends TestBase {

	public EmployeeSecurityAdminToolUpdatePasswordPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softAssert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	//Page Factory
	
//	By UserTypeRadioButton = By.xpath("//input[@value='Providers/Vendors']/parent::div/../..");//..//parent::div//parent::div
	By ShowResultsButton = By.xpath("//button[@id='show-results-btn']");
	By UserIDInputTextBox = By.xpath("//input[@id='userInput']");
	By UpdatePasswordLink = By.xpath("//a[@id='updatePassword']");
	By NoMatchFoundPopUpOkButton = By.xpath("//button[contains(text(),'Okay')]");
	By NoResultsErrorMessage = By.xpath("//p[contains(text(),'No matches found for the entered value.')]");
	By PasswordUpdatePopUpOkayButton  = By.xpath("//button[@id='passwordUpdate']");
	By PasswordUpdatePopUpCancelButton = By.xpath("//button[contains(text(),'Cancel')]");
	By UpdatePasswordButton = By.xpath("//button[@id='updatePass']");
	By NewPasswordTextBox = By.xpath("//input[@id='newPassword']");
	By ConfirmPasswordTextBox = By.xpath("//input[@id='confirmPassword']");
	By PasswordUpdateConfirmationOKButton = By.xpath("//button[@id='backtodetails']");
	By UpdatePassword = By.xpath("//a[contains(text(),'Update Profile')]");
	By LogOutButton = By.xpath("//span[contains(text(),'Log Out')]");
	By SubmitButton = By.xpath("//span[contains(text(),'Submit')]");
	By CurrentPasswordTextBox = By.xpath("//input[@id='password']");
	By NewpasswordTextBox = By.xpath("//input[@id='newPassword']");
	By ConfirmpasswordTextBox = By.xpath("//input[@id='confirmNewPassword']");
	
	
	public void userselectsusertypeenterstheuseridandvalidatessearchresults(String usertype, String userid) throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ShowResultsButton));
		driver.findElement(UserIDInputTextBox).click();
		driver.findElement(UserIDInputTextBox).clear();
		driver.findElement(UserIDInputTextBox).sendKeys(userid);
		driver.findElement(By.xpath("//input[@value='"+ usertype +"']")).click();
		System.out.println("Selected User Type:" +usertype);
		Thread.sleep(3000);
		driver.findElement(ShowResultsButton).click();
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		boolean SearchResultsFound = driver.findElement(UpdatePasswordLink).isDisplayed();
		boolean NoMatchResults = driver.findElement(NoMatchFoundPopUpOkButton).isSelected();
		if(SearchResultsFound=true)
		{
			String MessageInUI = driver.findElement(UpdatePasswordLink).getText();
			System.out.println("Message:" + MessageInUI);
			Assert.assertEquals(UpdatePasswordLink,UpdatePasswordLink,"Search Results are found");
		}
		else
		{
			String ErrorMessageInUI = driver.findElement(NoResultsErrorMessage).getText();
			System.out.println("Message:" + ErrorMessageInUI);
			Assert.assertEquals(NoResultsErrorMessage,"No matches found for the entered value.");
		}
		
	}


	public void userupdatespasswordandvalidatespasswordupdated(String newpassword, String confirmpassword) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(UpdatePasswordLink));
		driver.findElement(UpdatePasswordLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(PasswordUpdatePopUpOkayButton));
		driver.findElement(PasswordUpdatePopUpOkayButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(UpdatePasswordButton));
		driver.findElement(NewPasswordTextBox).click();
		driver.findElement(NewPasswordTextBox).clear();
		driver.findElement(NewPasswordTextBox).sendKeys(newpassword);
		driver.findElement(ConfirmPasswordTextBox).click();
		driver.findElement(ConfirmPasswordTextBox).clear();
		driver.findElement(ConfirmPasswordTextBox).sendKeys(confirmpassword);
		driver.findElement(UpdatePasswordButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(PasswordUpdateConfirmationOKButton));
		driver.findElement(PasswordUpdateConfirmationOKButton).click();
	}


	public void userupdatesthepasswordandvalidatesthepassword(String newpassword, String actualpassword,
			String actualPassword2) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(LogOutButton));
		driver.findElement(UpdatePassword).click();
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(CurrentPasswordTextBox).click();
		driver.findElement(CurrentPasswordTextBox).clear();
		driver.findElement(CurrentPasswordTextBox).sendKeys(newpassword);
		driver.findElement(NewpasswordTextBox).click();
		driver.findElement(NewpasswordTextBox).clear();
		driver.findElement(NewpasswordTextBox).sendKeys(actualpassword);
		driver.findElement(ConfirmpasswordTextBox).click();
		driver.findElement(ConfirmpasswordTextBox).clear();
		driver.findElement(ConfirmpasswordTextBox).sendKeys(actualpassword);
		driver.findElement(SubmitButton).click();
		try 
	    {
	        Alert alert = driver.switchTo().alert();
	        System.out.println("Alert is present");
	        String message = alert.getText();
	        System.out.println("Message: "+ message);
	        alert.sendKeys("");
	        alert.accept();
	    } 
	    catch (NoAlertPresentException e) 
	    {
	        //if alert is not present print message
	        System.out.println("alert is not present");
	    }
	}
	

	
}
