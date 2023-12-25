package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class ProviderViewAllUsersPage extends TestBase{

	public ProviderViewAllUsersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver=driver;
	}

	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	
	By SelectFirstUserRadioButton = By.xpath("//table[@id='users']//tbody//tr[1]//td//input");
	By UpdateUser = By.xpath("//a[@id='updateuserbutton']");
	By FirstUserPhoneNumber = By.xpath("//table[@id='users']//tbody//tr[1]//td[7]");
	
	public void UserselectsfirstuserIDandClickUpdateUser() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SelectFirstUserRadioButton));
		driver.findElement(SelectFirstUserRadioButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(UpdateUser));
		WebElement UpdateUserButton = driver.findElement(UpdateUser);
		js.executeScript("arguments[0].click();", UpdateUserButton);
	}

	public void Uservalidatesinfoisupdatedsuccessfully(String info) {
		// TODO Auto-generated method stub
		String Infor = (String) ScenarioContext.getContext(info);
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SelectFirstUserRadioButton));
		String PhoneNuminUI = driver.findElement(FirstUserPhoneNumber).getText();
		String Info1 = Infor.substring(0, 3);
		String Info2 = Infor.substring(3, 6);
		String Info3 = Infor.substring(6, 10);
		System.out.println("Number sub"+Info1 + Info2 + Info3 );
		Assert.assertTrue(PhoneNuminUI.contains(Info1), "Validating Information1 Updated Successfully");
		Assert.assertTrue(PhoneNuminUI.contains(Info2), "Validating Information2 Updated Successfully");
		Assert.assertTrue(PhoneNuminUI.contains(Info3), "Validating Information3 Updated Successfully");
	}
	
}

