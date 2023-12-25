package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class ProviderUpdatePasswordPage extends TestBase{
	
	public ProviderUpdatePasswordPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory

	By UpdatePasswordLink = By.xpath("//a[contains(text(),'Update Password')]");
	By CurrentPasswordTextBox = By.xpath("//input[@id='currentpassword']");
	By NewPasswordTextBox = By.xpath("//input[@id='newPassword']");
	By ConfirmPasswordTextBox = By.xpath("//input[@id='confirmPassword']");
	By SubmitButton = By.xpath("//button[contains(text(),'Submit')]");
	By ConfirmationPasswordPopUpButton = By.xpath("//button[@id='success-modal-ok']");
	
	
	
	public void userenterspasswordandupdatespassword(String password, String newpassword, String confirmpassword) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(UpdatePasswordLink));
		driver.findElement(UpdatePasswordLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(CurrentPasswordTextBox).click();
		driver.findElement(CurrentPasswordTextBox).clear();
		driver.findElement(CurrentPasswordTextBox).sendKeys(password);
		System.out.println("currentpassword: "+password);
		driver.findElement(NewPasswordTextBox).click();
		driver.findElement(NewPasswordTextBox).clear();
		driver.findElement(NewPasswordTextBox).sendKeys(newpassword);
		System.out.println("newpassword: "+newpassword);
		driver.findElement(ConfirmPasswordTextBox).click();
		driver.findElement(ConfirmPasswordTextBox).clear();
		driver.findElement(ConfirmPasswordTextBox).sendKeys(confirmpassword);
		System.out.println("confirmpassword: "+confirmpassword);
		driver.findElement(SubmitButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(ConfirmationPasswordPopUpButton));
		driver.findElement(ConfirmationPasswordPopUpButton).click();
	}
	 
}
