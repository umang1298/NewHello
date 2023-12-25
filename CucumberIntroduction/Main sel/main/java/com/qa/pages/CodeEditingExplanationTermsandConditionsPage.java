package com.qa.pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class CodeEditingExplanationTermsandConditionsPage extends TestBase {

	public CodeEditingExplanationTermsandConditionsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;

	//Page Factory
	By TermsandConditionsHeading = By.xpath("//div[contains(text(),'Code Editing Explanation Terms and Conditions')]");
	By IAgreeButton = By.xpath("//button[contains(text(),'I Agree')]");
	
	By C3Pageheading = By.xpath("//div[contains(text(),'Connection™')]");
	
	public void userverifiesTermsandConditionsareopenedinnewtab() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		Thread.sleep(3000);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(TermsandConditionsHeading));
		wait.until(ExpectedConditions.elementToBeClickable(IAgreeButton));
		Assert.assertTrue(newtabs.size()>0, "Validating New tab is opened");
		Assert.assertTrue(driver.findElement(TermsandConditionsHeading).isDisplayed(),"Validating Terms Conditions are displayed");
		driver.findElement(IAgreeButton).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(C3Pageheading));
		String C3URL = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		
	}
}
