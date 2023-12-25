package com.qa.pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class EmployeePortalLandingPage extends TestBase {
	
	public EmployeePortalLandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softAssert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	//Page Factory
	By NotificationBellIcon = By.xpath("//img[@id='agentAnnNotification']");
	By HomeBreadCrum = By.xpath("//a[@title='Go to Home page']");
	
	public void usernavigatestooneoftheMenusinEmployeePortal(String menuOption) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(NotificationBellIcon));
		String XpathtoLink = null ;
		switch(menuOption) {
		case "Member Eligibility":
			XpathtoLink ="(//a/div)[5]";
			break;
		case "Benefit Admin Manual":
			XpathtoLink ="(//a/div)[2]";
			break;
		case "Code Editing Explanation":
			XpathtoLink ="(//a/div)[3]";
			break;
		case "Contracts,Benfits and Riders":
			XpathtoLink ="(//a/div)[4]";
			break;
		case "Summary of Benefits and Coverage":
			XpathtoLink ="(//a/div)[6]";
			break;
		case "Search for a Doctor or Facility":
			XpathtoLink ="(//a/div)[7]";
			break;
		}
		WebElement LinktoClick = driver.findElement(By.xpath(XpathtoLink));
		wait.until(ExpectedConditions.elementToBeClickable(LinktoClick));
		js.executeScript("arguments[0].click();", LinktoClick);
		Thread.sleep(5000);
	}

	public void usernaviagatestoemployeeportalhomepageusingbreadcrumHomeButton() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(HomeBreadCrum));
		driver.findElement(HomeBreadCrum).click();
		
	}

	public void usernavigatestooneofthemenusatthetop(String menuoption) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(NotificationBellIcon));
		String xpathtomenu = "//span[contains(text(),'"+menuoption+"')]";
		WebElement menu = driver.findElement(By.xpath(xpathtomenu));
		wait.until(ExpectedConditions.elementToBeClickable(menu));
		js.executeScript("arguments[0].click();", menu);
	}

	public void userverifiesproviderlookuppageisopenedinnewtab() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		Thread.sleep(3000);
		WebElement MyProviderList = driver.findElement(By.xpath("//a[contains(text(),' My Provider List ')]"));
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(MyProviderList));
		String PageTitle = driver.getTitle();
		softAssert.assertTrue(PageTitle.contains("ProviderLookup"), "Validating Link takes user to  providerlookup  Page" );
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		softAssert.assertAll();
	}

	public void userclicksonHedisMemberOutreach() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(NotificationBellIcon));
		String xpathtoHedisMemberOutreach = "(//span[contains(text(),'HEDIS Member Outreach')])[2]";
		WebElement HedisMemberOutreach = driver.findElement(By.xpath(xpathtoHedisMemberOutreach));
		wait.until(ExpectedConditions.elementToBeClickable(HedisMemberOutreach));
		js.executeScript("arguments[0].click();", HedisMemberOutreach);
	}
	
	
}
