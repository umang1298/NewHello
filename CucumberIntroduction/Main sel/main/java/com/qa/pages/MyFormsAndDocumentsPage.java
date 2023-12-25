package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class MyFormsAndDocumentsPage extends TestBase {

	
	public MyFormsAndDocumentsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	//PageFactory
	
	

	public void Userclicksoneofthemenusfrommyformsanddocumentspage(String MenuoptiontoPass) {
		// TODO Auto-generated method stub
		String MenuoptionXpath =  "(//span[text()='" + MenuoptiontoPass + "'])[2]";
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(MenuoptionXpath))));
		driver.findElement(By.xpath(MenuoptionXpath)).click();
		System.out.println("Clicked Forms & Documents");
		
	}

}
