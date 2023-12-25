package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.base.TestBase;
import com.qa.util.Constants;

public class EmployerPortalMyToolsPage extends TestBase{

	// Page Factory
	By subGroupIDLinks = By.xpath("//div[2]/table/tbody/tr/td[1]/b");
	
	public EmployerPortalMyToolsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	public void UserClicksononeofthemenusinMyToolsPage(String menuOptionToPass) throws InterruptedException {
		// TODO Auto-generated method stub
		String MenuOptionXpath = "(//a[text()='" + menuOptionToPass + "'])";
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(MenuOptionXpath))));
		driver.findElement(By.xpath(MenuOptionXpath)).click();
		Thread.sleep(4000);
	//	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(subGroupIDLinks)));
		//java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(1);
	}

	public void UserClicksononeofthemenusinPAMyToolsPage(String menuOptionToPass) throws InterruptedException {
		// TODO Auto-generated method stub
		String MenuOptionXpath = "(//a[text()='" + menuOptionToPass + "'])";
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(MenuOptionXpath))));
		driver.findElement(By.xpath(MenuOptionXpath)).click();
		Thread.sleep(90000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(subGroupIDLinks)));
		
	}

	
}
