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

public class EmployeeSecurityAdminToolUpdateApplicationPage extends TestBase{

	public EmployeeSecurityAdminToolUpdateApplicationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softAssert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	//PageFactory
	By UpdateApplicationLink = By.xpath("//a[@id='updateApplication']");
	By UpdateButton = By.xpath("//button[@id='updateApp']");
	By Searchfordoctorradiobutton = By.xpath("(//label[@class='toggle'])[13]");
	By UpdatedOkPopUpButton = By.xpath("//button[@id='backtodetails']");
	By SearchForDoctorLink = By.xpath("//span[contains(text(),'Doctor')]");
	
	
	public void userupdatestheapplicationandvalidates() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(UpdateApplicationLink));
		driver.findElement(UpdateApplicationLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(UpdateButton));
		driver.findElement(Searchfordoctorradiobutton).click();
		Thread.sleep(15000);
		driver.findElement(UpdateButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(UpdatedOkPopUpButton));
		driver.findElement(UpdatedOkPopUpButton).click();
		
	}


	public void useraddstheapplicationandvalidates() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(UpdateApplicationLink));
		driver.findElement(UpdateApplicationLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(UpdateButton));
		driver.findElement(Searchfordoctorradiobutton).click();
		Thread.sleep(15000);
		driver.findElement(UpdateButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(UpdatedOkPopUpButton));
		driver.findElement(UpdatedOkPopUpButton).click();
	}

}





