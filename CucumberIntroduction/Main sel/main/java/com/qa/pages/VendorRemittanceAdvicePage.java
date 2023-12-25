package com.qa.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import com.qa.util.ScenarioContext;

public class VendorRemittanceAdvicePage extends TestBase{

	public VendorRemittanceAdvicePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	By FromDate = By.xpath("//input[@id='fromDate']");
	By ToDate = By.xpath("//input[@id='toDate']");
	By SearchButton = By.id("btnSubmit");
	By PopUpOkButton = By.xpath("//button[contains(text(),'OK')]");
	By SearchResultsBreadCrumb = By.xpath("//span[contains(text(),'Search Results')]");
	By SearchResultsHeading = By.xpath("//div[contains(text(),'Search Results')]");
	By ResultsTable = By.xpath("//table[@id='raaResults']");
	By ResultsFound = By.xpath("//span[contains(text(),'Results Found')]");
	By FirstVendorId = By.xpath("//table[@id='raaResults']//tbody//tr[1]//td[3]//span");
	By FirstPaymentNumber = By.xpath("//table[@id='raaResults']//tbody//tr[1]//td[4]//span");
	By DropdownButton = By.xpath("//button[@data-id='searchfilter']");
	By Searchfilterdropdown = By.xpath("//select[@id='searchfilter']");
	By VendorIdTextBox = By.xpath("//input[@id='vendIdEntered']");
	By PaymentNumberTextBox = By.xpath("//input[@id='paymentNumber']");
	
	public void usersearchesRAforonceyear() throws InterruptedException {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date Todate = new Date();
		System.out.println("To date: " +dateFormat.format(Todate));
		String Todates = dateFormat.format(Todate);
		Calendar instance = Calendar.getInstance();
	    instance.setTime(Todate);
	    instance.add(Calendar.YEAR, -1);
	    SimpleDateFormat isoFormat = new SimpleDateFormat("MM/dd/yyyy");
	    String previousYearDate = isoFormat.format(instance.getTime());
		System.out.println("From Date: " + previousYearDate);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		driver.findElement(FromDate).clear();
		driver.findElement(FromDate).sendKeys(previousYearDate);
		driver.findElement(ToDate).clear();
		driver.findElement(ToDate).sendKeys(Todates);
		driver.findElement(SearchButton).click();
		Thread.sleep(3000);
	}

	public void uservalidatesresultforRAsearch() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(SearchResultsBreadCrumb));
		Thread.sleep(4000);
		boolean popupdisplayed = driver.findElement(PopUpOkButton).isDisplayed();
		if(popupdisplayed==true) {
			wait.until(ExpectedConditions.elementToBeClickable(PopUpOkButton));
			driver.findElement(PopUpOkButton).click();
		}
		else {
			wait.until(ExpectedConditions.elementToBeClickable(SearchResultsHeading));
			softassert.assertTrue(driver.findElement(SearchResultsHeading).isDisplayed(), "Search Results are not displayed");
			softassert.assertTrue(driver.findElement(ResultsTable).isDisplayed(), "Search Results Table is not displayed");
		}
		
		softassert.assertAll();
	}
	
	public void uservalidatessearchresultforRAinemployeeportal() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(SearchResultsBreadCrumb));
		Thread.sleep(4000);
		boolean popupdisplayed = driver.findElements(PopUpOkButton).size()>0;
		if(popupdisplayed==true) {
			wait.until(ExpectedConditions.elementToBeClickable(PopUpOkButton));
			driver.findElement(PopUpOkButton).click();
		}
		else {
			wait.until(ExpectedConditions.elementToBeClickable(SearchResultsHeading));
			softassert.assertTrue(driver.findElement(SearchResultsHeading).isDisplayed(), "Search Results are not displayed");
			softassert.assertTrue(driver.findElement(ResultsTable).isDisplayed(), "Search Results Table is not displayed");
		}
		
		softassert.assertAll();
	}

	public void usergetsVendorIdandPaymentNumberfromSearchResults(String vendorId, String paymentNumber) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(ResultsFound));
		wait.until(ExpectedConditions.elementToBeClickable(SearchResultsHeading));
		String VendorIdinUI = driver.findElement(FirstVendorId).getText();
		String PaymentNumberinUI = driver.findElement(FirstPaymentNumber).getText();
		ScenarioContext.setContext(vendorId, VendorIdinUI);
		ScenarioContext.setContext(paymentNumber, PaymentNumberinUI);
	}

	public void usersearcheswithoption(String searchwith) throws InterruptedException {
		// TODO Auto-generated method stub
		String datavalue = (String) ScenarioContext.getContext(searchwith);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Searchfilterdropdown));
		Thread.sleep(3000);
		WebElement Dropdown = driver.findElement(Searchfilterdropdown);
		Select searchdropdown = new Select(Dropdown);
		
		if(searchwith.equals("VendorId")) {
			driver.findElement(DropdownButton).click();
	    	searchdropdown.selectByIndex(3);
	    	WebElement searchtextbox = driver.findElement(VendorIdTextBox);
	    	searchtextbox.click();
	    	searchtextbox.sendKeys(datavalue);
		}
		else if(searchwith.equals("Payment Number")){
			driver.findElement(DropdownButton).click();
	    	searchdropdown.selectByIndex(2);
	    	WebElement searchtextbox = driver.findElement(PaymentNumberTextBox);
	    	searchtextbox.click();
	    	searchtextbox.sendKeys(datavalue);
	    	driver.findElement(SearchButton).click();
		}
	    
	}

	

}
