package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class ChangePasswordPage extends TestBase {

	public ChangePasswordPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	//Page Factory
	By currentPassword=By.xpath("//input[@id='currentPassword']");
	By currentPasswordAgent=By.xpath("//input[@id='password']");
	By newPassword=By.xpath("//input[@id='newPassword']");
	By newPasswordAgent=By.xpath("//input[@id='newPassword']");
	By confirmnew=By.xpath("//input[@id='confirmPassword']");
	By confirmnewPasswordAgent=By.xpath("//input[@id='confirm-Password']");
	By submit=By.xpath("//button[@id='submitbtn']");
	By Successmessage=By.xpath("//p[contains(text(),'Password has been updated successfully.')]");
	By OKbutton=By.xpath("//button[@class='btn btn-primary hap-main-buttons marginright20 yes-button xs-width100']");
	
	
	public void validatesChangePassword() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(submit));
		driver.findElement(currentPassword).sendKeys("Today1234");
		driver.findElement(newPassword).sendKeys("Today123");
		driver.findElement(confirmnew).sendKeys("Today123");
		driver.findElement(submit).click();
		wait.until(ExpectedConditions.elementToBeClickable(OKbutton));
		String SuccessText=driver.findElement(Successmessage).getText();
		System.out.println("SuccessText:"+SuccessText);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		Assert.assertTrue("Password is not updated",SuccessText.contains("Password is updated successfully."));
		driver.findElement(OKbutton).click();
		wait.until(ExpectedConditions.elementToBeClickable(submit));
		driver.findElement(currentPassword).sendKeys("Today123");
		driver.findElement(newPassword).sendKeys("Today1234");
		driver.findElement(confirmnew).sendKeys("Today1234");
		driver.findElement(submit).click();
		wait.until(ExpectedConditions.elementToBeClickable(OKbutton));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(OKbutton).click();
		
	}


	public void validatesChangePasswordforAgent() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(submit));
		driver.findElement(currentPasswordAgent).sendKeys("Today1234");
		driver.findElement(newPasswordAgent).sendKeys("Today123");
		driver.findElement(confirmnewPasswordAgent).sendKeys("Today123");
		driver.findElement(submit).click();
		wait.until(ExpectedConditions.elementToBeClickable(OKbutton));
		String SuccessText=driver.findElement(Successmessage).getText();
		System.out.println("SuccessText:"+SuccessText);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		Assert.assertTrue("Password is not updated",SuccessText.contains("Password has been updated successfully."));
		driver.findElement(OKbutton).click();
		wait.until(ExpectedConditions.elementToBeClickable(submit));
		driver.findElement(currentPasswordAgent).sendKeys("Today123");
		driver.findElement(newPasswordAgent).sendKeys("Today1234");
		driver.findElement(confirmnewPasswordAgent).sendKeys("Today1234");
		driver.findElement(submit).click();
		wait.until(ExpectedConditions.elementToBeClickable(OKbutton));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(OKbutton).click();
	}

}
