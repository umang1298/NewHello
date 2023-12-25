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

public class AgentPortalUpdatePasswordPage extends TestBase {

	public AgentPortalUpdatePasswordPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	
	By CurrentPasswordTextBox = By.xpath("//input[@id='password']");
	By NewPasswordTextBox = By.xpath("//input[@id='newPassword']");
	By ConfirmPasswordTextBox = By.xpath("//input[@id='confirm-Password']");
	By SubmitButton = By.xpath("//button[@id='submitbtn']");
	By OkConfirmationPopupButton = By.xpath("(//button[@type='button'])[3]");
	
	
	public void userentersthepasswordandvalidatesupdatepasswordcorrectly(String password, String newpassword,
			String confirmpassword) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
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
		wait.until(ExpectedConditions.elementToBeClickable(OkConfirmationPopupButton));
		driver.findElement(OkConfirmationPopupButton).click();
		
	}

}
