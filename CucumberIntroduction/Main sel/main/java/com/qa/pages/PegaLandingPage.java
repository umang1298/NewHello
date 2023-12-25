package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class PegaLandingPage extends TestBase{

	public PegaLandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	//Page Factory
	By LogoffBtn = By.xpath("//span[contains(text(),'Log off')]");
	
	public void userclickson(String menuOption) {
		// TODO Auto-generated method stub
		By GenerateDoc = By.xpath("//span[contains(text(),'" + menuOption + "')]");
		
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(GenerateDoc));
		driver.findElement(GenerateDoc).click();
	}

	public void userlogsoffpega() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.switchTo().defaultContent();
		for(int i=0; i<=2;i++){

			  try{
				  Thread.sleep(3000);
				  driver.findElement(By.xpath("(//i)[5]")).click();
				  Thread.sleep(3000);
				  driver.findElement(LogoffBtn).click();
			     break;
			  }
			  catch(Exception e){
			     System.out.println(e.getMessage());
			  }

			}
	}
	
	
}
