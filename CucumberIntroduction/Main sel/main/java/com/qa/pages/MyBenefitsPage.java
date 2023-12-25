package com.qa.pages;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
public class MyBenefitsPage extends TestBase{
	public MyBenefitsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
}

//PageFactory
	By signout=By.xpath("//button[contains(text(),'Sign Out')]");
public void Userclicksoneofthemenusfrommybenefitspage(String MenuoptiontoPass)
{
	String MenuoptionXpath =  "(//span[text()='" + MenuoptiontoPass + "'])[2]";
	WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(MenuoptionXpath))));
	driver.findElement(By.xpath(MenuoptionXpath)).click();
	System.out.println("Clicked My Coverage");
	
}

public void userVerifiesEyemedPage() throws InterruptedException {
	// TODO Auto-generated method stub
	Thread.sleep(15000);
	ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(newtabs.get(1));
	driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
	Thread.sleep(15000);
	WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(signout)));
	
//	Assert.assertTrue(driver.findElement(signout).isDisplayed());
}
}


