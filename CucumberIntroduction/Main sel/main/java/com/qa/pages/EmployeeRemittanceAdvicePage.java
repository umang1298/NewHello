package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class EmployeeRemittanceAdvicePage extends TestBase{

	public EmployeeRemittanceAdvicePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		TestBase.driver = driver;
	}

	//Page Factory
	By SearchButton = By.xpath("//button[@id='btnSubmit']");
	By SearchFilterDropdownOne = By.xpath("//select[@id='searchfilterOne']");
	By DropdownButtonOne = By.xpath("//button[@data-id='searchfilterOne']");
	By SearchIconOne = By.id("search-box-icon2");
	By VendorSearchButton = By.xpath("//button[@id='vendorSearch']");
	By VendorNameTextBox = By.xpath("//input[@id='vendorValue']");
	By VendorSearchResultsTable = By.xpath("//table[@id='vendorSearchResults']");
	By FirstResultinVendorSearch = By.xpath("//table[@id='vendorSearchResults']//tbody//tr[2]//td//span");
	By PaymentNumberTextBox = By.xpath("//input[@id='paymentNumber']");
	
	public void userselectsindropdown(String toselectindropdown) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(SearchFilterDropdownOne));
		Thread.sleep(5000);
		WebElement DropdownOne = driver.findElement(SearchFilterDropdownOne);
		Select searchdropdownOne = new Select(DropdownOne);
		driver.findElement(DropdownButtonOne).click();
		searchdropdownOne.selectByValue(toselectindropdown);
		
		}


	public void userrsearchesandselectsoneorenterspaymentnumbervalue(String toselectindropdown,
			String vendorNameorPaymentNumber) throws InterruptedException {
		// TODO Auto-generated method stub
		if(toselectindropdown.equals("vendorId")) {
			WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
			wait.until(ExpectedConditions.elementToBeClickable(SearchIconOne));
			driver.findElement(SearchIconOne).click();
			wait.until(ExpectedConditions.elementToBeClickable(VendorSearchButton));
			wait.until(ExpectedConditions.presenceOfElementLocated(VendorNameTextBox));
			driver.findElement(VendorNameTextBox).sendKeys(vendorNameorPaymentNumber);
			Thread.sleep(2000);
			driver.findElement(VendorSearchButton).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(VendorSearchResultsTable));
			wait.until(ExpectedConditions.elementToBeClickable(FirstResultinVendorSearch));
			driver.findElement(FirstResultinVendorSearch).click();
		}
		else {
			WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
			wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
			driver.findElement(PaymentNumberTextBox).sendKeys(vendorNameorPaymentNumber);
		}
	}

}
