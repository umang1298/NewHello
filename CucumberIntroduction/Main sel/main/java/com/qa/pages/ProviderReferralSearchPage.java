package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;

public class ProviderReferralSearchPage extends TestBase {
	
	//Page Factory
	
	By ReferralSearchPage = By.xpath("//span[contains(text(),'Referral Search')]");
	By StatusDropDown = By.xpath("//select[@name='selectedStatus']");
	By SearchButton = By.xpath("//button[contains(text(),'Search')]");
	By FromDateBox = By.xpath("//input[@id='fromDate']");
	By ToDateBox = By.xpath("//input[@id='toDate']");
	
	public ProviderReferralSearchPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
		
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	public void userselectstatusandsearchreferrralforlastoneyear() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		String Fromdate = CommonMethods.LastYearDate();
		System.out.println("Currentdate:" +Fromdate);
		driver.findElement(FromDateBox).sendKeys(Fromdate);
		String Todate = CommonMethods.CurrentDate();
		System.out.println("Todaydate:" +Todate);
		driver.findElement(ToDateBox).sendKeys(Todate);
		Select statusdropdown = new Select(driver.findElement(StatusDropDown));
		statusdropdown.selectByIndex(5);
		driver.findElement(SearchButton).click();
	}
}
