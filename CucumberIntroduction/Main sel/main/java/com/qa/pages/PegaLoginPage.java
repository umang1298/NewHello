package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.base.TestBase;
import com.qa.util.ScenarioContext;

public class PegaLoginPage extends TestBase{

	public PegaLoginPage(WebDriver driver) {
		TestBase.driver=driver;	

		}

	//PageFactory
	By UserIDField = By.xpath("//input[@id='txtUserID']");
	By PasswordField = By.xpath("//input[@id='txtPassword']");
	By LoginButton = By.xpath("//button[@id='sub']");
	
	public PegaLandingPage LogintoApp(String userID, String password) {
		// TODO Auto-generated method stub
		String userIdVal = (String) ScenarioContext.getContext(userID);
		driver.findElement(UserIDField).sendKeys(userIdVal);
		driver.findElement(PasswordField).sendKeys(password);
		driver.findElement(LoginButton).click();
		return new PegaLandingPage(driver);
	}
	
}
