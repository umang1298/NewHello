package com.qa.pages;

import java.util.ArrayList;
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

public class MemberPortalHomePage extends TestBase {
	
	public MemberPortalHomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	} 
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	By FindDoctorXpath=By.xpath("//span[contains(text(),'Find a Doctor')]");
	By HomeBreadcrum = By.xpath("//a[contains(text(),'Home')]");
	
	public void VerifyWidgetsDisplayed(String WidgetName)
	{
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FindDoctorXpath));	
		WebElement Widget = driver.findElement(By.xpath("//h2[contains(text(),'" + WidgetName + "')]"));
		softassert.assertTrue(Widget.isDisplayed(), "Validating " + Widget + "widget is displayed");
		softassert.assertAll();
	}

	public void VerifyChangePCPLink(String changePCPLink, String providerLookUp) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FindDoctorXpath));
		Thread.sleep(3000);
		String pagesource=driver.getPageSource();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		if(pagesource.contains("No PCP on record"))
		{
			System.out.println("No PCP on record");
		}
		else
		{
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			WebElement ChangePCPLink = driver.findElement(By.xpath("//a[contains(text(),'" + changePCPLink +"')]"));
			js.executeScript("arguments[0].click();", ChangePCPLink);
			
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newtabs.get(1));
			Thread.sleep(3000);
			WebElement MyProviderList = driver.findElement(By.xpath("//a[contains(text(),' My Provider List ')]"));
			wait.until(ExpectedConditions.elementToBeClickable(MyProviderList));
			String PageTitle = driver.getTitle();
			softassert.assertTrue(PageTitle.contains(providerLookUp), "Validating " +changePCPLink +" Link takes user to " + providerLookUp + " Page" );
			driver.close();
			driver.switchTo().window(newtabs.get(0));
			softassert.assertAll();
		}
	}

	public void VerifyViewmoreFunctionalityonHomePage(String viewmore, String widgetname, String targetpage) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(FindDoctorXpath));
		Thread.sleep(2000);
		WebElement viewmorelink = driver.findElement(By.xpath("//h2[contains(text(),'" + widgetname + "')]//..//..//..//div/following-sibling::div//a//span"));
		wait.until(ExpectedConditions.elementToBeClickable(viewmorelink));
		Thread.sleep(4000);
		js.executeScript("arguments[0].click();", viewmorelink);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(HomeBreadcrum));
		WebElement Target = driver.findElement(By.xpath("//h1[contains(text(),'" + targetpage + "')]"));
		softassert.assertTrue(Target.isDisplayed(), "Validating viewmore on" + widgetname + "widget takes user to " + targetpage + " page");
		WebElement HomeBtn = driver.findElement(HomeBreadcrum);
		js.executeScript("arguments[0].click();", HomeBtn);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		softassert.assertAll();
	}
}
