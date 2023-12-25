package com.qa.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import io.cucumber.java.en.Then;


	public class PayerPortalClaimStatusPage extends TestBase{

		public PayerPortalClaimStatusPage(WebDriver driver) {
			// TODO Auto-generated constructor stub
			TestBase.driver = driver;
		}
		//Page Factory
		By SearchIcon=By.xpath("//img[@id='insuredSSN_img']");
		By FromDate=By.xpath("//input[@id='dateFrom']");
		By ToDate=By.xpath("//input[@id='dateTo']");
		By TypeBoth=By.xpath("//input[@id='radioTypeBothId']");
		By ClaimnumberYes=By.xpath("//input[@id='searchByClaimNumberRadio']");
		By ClaimIDText=By.xpath("//input[@id='claimNumber']");
		By ClaimsHeading=By.xpath("//h2[contains(text(),'Enter Search Criteria')]");
		By SSNnumberText=By.xpath("//input[@id='insuredSSN']");
		By SearchButton=By.xpath("//span[contains(text(),'Search')]");
		By LastName=By.xpath("//input[@id='hapAjaxSubSearchLastName']");
		By SubscriberSearchButton=By.xpath("//div[@id='hapAjaxPeraSubSearchButton']/a/span[contains(text(),'Search')]");
		By SSNumber=By.xpath("//div/div/div/div[1]/table/tbody/tr/td[1]/a");
		By ClaimNumber=By.xpath("//table[@id='claimsTableId']/tbody/tr/td[3]/a");
		By NewSearch=By.xpath("//span[contains(text(),'New Search')]");
		By HAPClaimNumber=By.xpath("//div[@class='boxBody']/table/tbody/tr[3]/td[2]");
		By PrintOption=By.xpath("//a[contains(text(),'Print')]");
		By Close=By.xpath("//a[contains(text(),'Close')]");
		
		public void usersearchclaimsbySSN() throws InterruptedException {
			// TODO Auto-generated method stub
			WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
			wait.until(ExpectedConditions.elementToBeClickable(ClaimsHeading));
		//	CommonMethods.Takescreenshot(scenario);
			driver.findElement(SearchIcon).click();
			wait.until(ExpectedConditions.elementToBeClickable(SubscriberSearchButton));
			driver.findElement(LastName).sendKeys("andrea");
			driver.findElement(SubscriberSearchButton).click();
			Thread.sleep(2000);
			String SSN=driver.findElement(SSNumber).getText();
			driver.findElement(SSNumber).click();
			System.out.println("ClaimNumber:"+SSN);
			Thread.sleep(2000);
			
			
			
		}

		public void validateprintoption(String alertpresent) {
			// TODO Auto-generated method stub
			String Alertpresented = (String) ScenarioContext.getContext(alertpresent);	
			if(Alertpresented!= null)
			{
			
			}
			else
			{
			Assert.assertTrue(driver.findElement(PrintOption).isDisplayed(), "Print link is not available");
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
//			Assert.assertTrue(driver.findElement(Close).isDisplayed(), "Close link is not available");
		}
		}

		public void usersearchesclaimsforlastoneyear(String alertpresent) throws InterruptedException {
			// TODO Auto-generated method stub
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date Todate = new Date();
			System.out.println("To date: " +dateFormat.format(Todate));
			String Todates = dateFormat.format(Todate);
			Calendar instance = Calendar.getInstance();

		    instance.setTime(Todate);
		    instance.add(Calendar.YEAR, -1);
		    SimpleDateFormat isoFormat = new SimpleDateFormat("MM/dd/yyyy");
		    String previousYearDate = isoFormat.format(instance.getTime());
			System.out.println("From Date: " + previousYearDate);
			
			driver.findElement(FromDate).sendKeys(previousYearDate);
			Thread.sleep(2000);
			driver.findElement(ToDate).sendKeys(Todates);
			driver.findElement(TypeBoth).click();
			driver.findElement(SearchButton).click();
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
			if(wait.until(ExpectedConditions.alertIsPresent())==null)
			{
			    System.out.println("alert was not present");
			    
			}
			else
			{
			    System.out.println("alert was present");
			    driver.switchTo().alert().accept();
			    String Alert="yes";
			    ScenarioContext.setContext(alertpresent, Alert);
			}
			}

		public void userselectsclaimnumber(String claimnum, String alertpresent) throws InterruptedException {
			// TODO Auto-generated method stub
			
			String Alertpresented = (String) ScenarioContext.getContext(alertpresent);	
			if(Alertpresented!= null)
			{
			
			}
			else
			{
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait.until(ExpectedConditions.elementToBeClickable(NewSearch));
				String Claimnumber=driver.findElement(ClaimNumber).getText();
				System.out.println("Claimnumber:"+Claimnumber);
				ScenarioContext.setContext(claimnum, Claimnumber);
				driver.findElement(ClaimNumber).click();	
			}
		}

		public void userValidatesclaimnumberinclaiminformationpage(String claimnum, String alertpresent) {
			// TODO Auto-generated method stub
			String Alertpresented = (String) ScenarioContext.getContext(alertpresent);	
			if(Alertpresented!= null)
			{
			
			}
			else
			{
			String Claimnumber = (String) ScenarioContext.getContext(claimnum);
			ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newtabs.get(1));
			System.out.println("newtabs:"+newtabs);
			WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
			wait.until(ExpectedConditions.elementToBeClickable(PrintOption));
			String HAPClaimnumber=driver.findElement(HAPClaimNumber).getText();
			
			System.out.println("Claimsummary claimnumber:"+HAPClaimnumber);
			Assert.assertTrue(HAPClaimnumber.equals(Claimnumber), "Claim number is not matching with Claim Information");
			driver.switchTo().window(newtabs.get(0));
		}
		}

		public void usersearchusingClaimnumber(String claimnum, String alertpresent) throws InterruptedException {
			// TODO Auto-generated method stub
			String Alertpresented = (String) ScenarioContext.getContext(alertpresent);	
			if(Alertpresented!= null)
			{
				driver.findElement(NewSearch).click();
				String Claimnumber = (String) ScenarioContext.getContext(claimnum);
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait.until(ExpectedConditions.elementToBeClickable(ClaimsHeading));
				driver.findElement(ClaimnumberYes).click();
				driver.findElement(ClaimIDText).sendKeys(Claimnumber);
				Thread.sleep(2000);
				driver.findElement(SearchButton).click();
				wait.until(ExpectedConditions.elementToBeClickable(PrintOption));
				String HAPClaimnumber=driver.findElement(HAPClaimNumber).getText();
				System.out.println("Claimsummary claimnumber:"+HAPClaimnumber);
				Assert.assertTrue(HAPClaimnumber.equals(Claimnumber), "Claim number is not matching with Claim Information");
			}
			else
			{
			
		}
		}
	
	
	}
