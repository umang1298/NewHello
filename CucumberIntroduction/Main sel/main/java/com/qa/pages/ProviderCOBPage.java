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
import com.qa.util.ScenarioContext;

public class ProviderCOBPage extends TestBase{

	public ProviderCOBPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	By SearchButton = By.xpath("(//button[contains(text(),'Search')])[2]");
	By MemberIDTextBox = By.xpath("//input[@id='hapId1']");
	
	public void usersearchesCOBforthememberID(String memberID) {
		// TODO Auto-generated method stub
		String MemberID = (String) ScenarioContext.getContext(memberID);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		driver.findElement(MemberIDTextBox).sendKeys(MemberID);
		driver.findElement(SearchButton).click();
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);	
	}


}
