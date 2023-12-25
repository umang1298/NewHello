package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class EmployerPortalLandingPage extends TestBase{

	
	
	// Page Factory
	
	By LogOutBtn = By.xpath("//span[contains(text(),'Log Out')]");
	
	
	
	public EmployerPortalLandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	public void userclicksononeofthemenusinEmployerPortalLandingpage(String menuOptionToPass) {
		// TODO Auto-generated method stub
		String MenuOptionXpath = "(//span[text()='" + menuOptionToPass + "'])";
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(MenuOptionXpath))));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(By.xpath(MenuOptionXpath)).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
	}

	public void userlogoutsofemployerportal() {
		// TODO Auto-generated method stub
		driver.findElement(LogOutBtn).click();
	}
}
