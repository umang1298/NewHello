package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class ProviderManageUsersPage extends TestBase {

	public ProviderManageUsersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
		
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	By ViewAllUsersLink = By.xpath("//a[contains(text(),'All Users')]");
	By ViewAllUsersSearchButton = By.id("search3");
	By NPIDropdown = By.id("selectedNpi3");
	
	public void UserClicksontheViewAllUsersLink() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ViewAllUsersLink));
		driver.findElement(ViewAllUsersLink).click();
	}

	public void usersearchesbyNPI() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ViewAllUsersSearchButton));
		Select dropdown = new Select(driver.findElement(NPIDropdown));
		dropdown.selectByIndex(3);
		driver.findElement(ViewAllUsersSearchButton).click();
		
	}
	
	

}
