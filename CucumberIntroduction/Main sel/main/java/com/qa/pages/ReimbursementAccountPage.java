package com.qa.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class ReimbursementAccountPage extends TestBase{

	public ReimbursementAccountPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	TestBase.driver=driver;
	}
	By HealthEquity=By.xpath("//a[@id='ctl00__HeaderSection1_hlLeftLogo']//img");

	public void ValidateReimbursementLink() throws InterruptedException {
		// TODO Auto-generated method stub
		
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(HealthEquity));
		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(HealthEquity).isDisplayed(),"Covered Drug Lists page is not displayed");
		
	}

	
}
