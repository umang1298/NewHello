package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class IPAUpdateProfilePage extends TestBase {

	public IPAUpdateProfilePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	//Page Factory
	By currentPassword=By.xpath("//input[@name='currentpassword']");
	By newPassword=By.xpath("//input[@name='newpassword']");
	By confirmnew=By.xpath("//input[@name='confirmnewpassword']");
	By submit=By.xpath("//button[contains(text(),'Submit')]");
	By Successmessage=By.xpath("//div[contains(text(),'Your password')]");
	By OKbutton=By.xpath("//button[@id='success-modal-ok']");
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	public void UserValidatesChangePasswordFunctionalityInTheIPAportal() {
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
		Assert.assertTrue("Password is not updated",SuccessText.contains("successfully changed."));
		driver.findElement(OKbutton).click();
	}
	public void UserChangesthePasswordtotheOldPassword() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(submit));
		driver.findElement(currentPassword).sendKeys("Today123");
		driver.findElement(newPassword).sendKeys("Today1234");
		driver.findElement(confirmnew).sendKeys("Today1234");
		driver.findElement(submit).click();
		wait.until(ExpectedConditions.elementToBeClickable(OKbutton));
		String SuccessText=driver.findElement(Successmessage).getText();
		System.out.println("SuccessText:"+SuccessText);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		Assert.assertTrue("Password is not updated",SuccessText.contains("successfully changed."));
		driver.findElement(OKbutton).click();
	}
	
	
}
