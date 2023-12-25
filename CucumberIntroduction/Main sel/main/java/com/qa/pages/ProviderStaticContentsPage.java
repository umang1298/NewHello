package com.qa.pages;

import java.util.Properties;
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

public class ProviderStaticContentsPage extends TestBase {

	public ProviderStaticContentsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page factory
	By QuickLinksClosePopUpButton = By.xpath("//button[@alt='Close Pop-up']");
	By RelatedLinksClosePopupButton = By.xpath("(//button[@alt='Close Pop-up'])[2]");
	By WorkingWithHapHeading = By.xpath("//h4[contains(text(),'Working with HAP')]");
	
	public void UserValidatesQuickLinksisDisplayed(String quickLink) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		String xpath = "//span[contains(text(),'"+quickLink+"')]";
		WebElement QuickLink = driver.findElement(By.xpath(xpath));
		Assert.assertTrue(QuickLink.isDisplayed(), "Validating Quicklink for -->"+quickLink+" is Displayed");
		
	}

	public void UserClosestheLinksPanel() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(QuickLinksClosePopUpButton));
		WebElement CloseButton = driver.findElement(QuickLinksClosePopUpButton);
		js.executeScript("arguments[0].click();", CloseButton);
		
		
	}

	public void UserClosesRelatedLinkPanel() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(RelatedLinksClosePopupButton));
		WebElement CloseButton = driver.findElement(RelatedLinksClosePopupButton);
		js.executeScript("arguments[0].click();", CloseButton);
	}

	public void UserValidatesLinksArepresentinresources(String link) throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		Thread.sleep(4000);	
		String xpath = "//a[contains(text(),'"+link+"')]";
		WebElement Link = driver.findElement(By.xpath(xpath));
//		int linkcount = driver.findElements(By.xpath(xpath)).size();
//		System.out.println("Count:"+linkcount);
		Assert.assertTrue(Link.isDisplayed(), "Validating Links -->"+link+" is displayed");
		
	}
	//*[@id='memberheading']/h4/a/i
	//*[@id='contractheading']/h4/a/i
	//*[@id='copayheading']/h4/a/i

	public void UserExpandsHeading(String headingName) throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		Thread.sleep(4000);	
		String xpath = "//*[@id='"+headingName+"']/h4/a/i";
		driver.findElement(By.xpath("(//a[@role='button'])[2]")).click();
		
	}

}
