package com.qa.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import io.cucumber.java.Scenario;

public class ProviderClaimsPage extends TestBase{

	public ProviderClaimsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
				TestBase.driver = driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	Scenario scenario;
	//Page Factory
	By FromDate = By.xpath("//input[@id='dateOfServiceFrom']");
	By ToDate = By.xpath("//input[@id='dateOfServiceTo']");
	By ProviderNameHeading = By.xpath("//span[contains(text(),'Provider :')]");
	By SearchButton = By.xpath("//input[@type='submit']");
	By TypeBoth = By.xpath("//input[@id='both']");
	By ProviderNPIHeading = By.xpath("//span[contains(text(),'NPI :')]");
	By ProviderName = By.xpath("(//button[@data-id='selectedProvider']//..//span)[1]");
	By ProviderNPI = By.xpath("(//span[contains(text(),'NPI :')]//..//span)[3]");
	By ProviderTaxID=By.xpath("(//button[@data-id='taxIdChange']//..//span)[1]");
	By Searchfilterdropdown = By.xpath("//select[@id='searchfilter']");
	By Searchtextbox = By.xpath("//input[@class='form-control forminput filterinputs']");
	By DropdownButton = By.xpath("//button[@data-id='searchfilter']");
	By MemberIDTextBox = By.xpath("//input[@id='memberid']");
	By ClaimNumberTextBox = By.xpath("//input[@id='claimnumber']");
	By PatientAccountNumberTextBox = By.xpath("//input[@id='pan']");
	By HAPClaimNumber = By.xpath("/html/body/div[2]/section/div/div/form/div[7]/div/div[2]/div[1]/div[1]/span[2]");
	By FirstclaiminResults = By.xpath("//*[@id='summaryTable']/tbody/tr/td[2]/a/span");
	By ErrorMessageOKButton = By.xpath("//button[contains(text(),'OK')]");
	By MemberIDinClaimsPage = By.xpath("//*[@id='page-content-wrapper']/div/div/div[2]/div[1]/div[2]/div/span/div[1]/span[2]/span");
	By PatientIDinClaimsPage = By.xpath("//*[@id='summaryTable']/tbody/tr[1]/td[3]/span");
	
	
	
	public void usersearchesclaimsforlastoneyear() {
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
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ProviderNameHeading));
		CommonMethods.Takescreenshot(scenario);
		driver.findElement(FromDate).sendKeys(previousYearDate);
		driver.findElement(ToDate).sendKeys(Todates);
		driver.findElement(TypeBoth).click();
		driver.findElement(SearchButton).click();
		
	}

	public void userverfiesproviderinformation() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ProviderNameHeading));
		WebElement ProviderNameElement = driver.findElement(ProviderName);
		WebElement ProviderTaxIdElement = driver.findElement(ProviderTaxID);
		softassert.assertTrue(ProviderNameElement.isDisplayed(), "Validating Provider Name is displayed in Provider Claims Page");
		softassert.assertTrue(ProviderTaxIdElement.isDisplayed(), "Validating Provider TAX ID is displayed in Provider Claims Page");
		softassert.assertAll();

		
	}

	public void usersearcheswithoptionandentersdata(String searchwith, String data) throws InterruptedException {
		// TODO Auto-generated method stub
     	String datavalue = (String) ScenarioContext.getContext(data);
     	System.out.println("Data :" + data);
     	System.out.println("Data value :" + datavalue);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Searchfilterdropdown));
		Thread.sleep(5000);
		WebElement Dropdown = driver.findElement(Searchfilterdropdown);
		Select searchdropdown = new Select(Dropdown);
	    
	    if (searchwith.equals("Claim Number")) {
	    	//Dropdown.click();
	    	//wait.until(ExpectedConditions.visibilityOfElementLocated(Searchfilterdropdown));
	    	
			if (data.equals("PartialClaimNumber")) {
				driver.findElement(DropdownButton).click();
		    	searchdropdown.selectByIndex(0);
		    	WebElement searchtextbox = driver.findElement(ClaimNumberTextBox);
		    	searchtextbox.click();
		    	
		    	searchtextbox.sendKeys(datavalue);
		    	
			    driver.findElement(SearchButton).click();
			    driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
			    WebDriverWait wait1 = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
			    wait1.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[contains(text(),'Claim Summary List')]"))));
				 
			WebElement ClaimSummaryListHeading = driver.findElement(By.xpath("//div[contains(text(),'Claim Summary List')]"));
			softassert.assertTrue(ClaimSummaryListHeading.isDisplayed(), "Veryfing Whether search with Partial Claim Number takes user to Claim Summary List");
			String ClaimNumberinUI = driver.findElement(FirstclaiminResults).getText();
			softassert.assertTrue(ClaimNumberinUI.contains(datavalue), "Validating Correct search results are displayed for Partial Claim Number Search");
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//span[contains(text(),'Claims')])[5]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//span[contains(text(),'Claims')])[2]")).click();
			
			}
			else if(data.equals("FullClaimNumber")) {
				driver.findElement(DropdownButton).click();
		    	searchdropdown.selectByIndex(0);
		    	WebElement searchtextbox = driver.findElement(ClaimNumberTextBox);
		    	searchtextbox.click();
		    	
		    	searchtextbox.sendKeys(datavalue);
		    	
			    driver.findElement(SearchButton).click();
			    
				driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
				Thread.sleep(3000);
				WebElement ClaimDetailsHeading = driver.findElement(By.xpath("//div[contains(text(),'Details')]"));
				softassert.assertTrue(ClaimDetailsHeading.isDisplayed(), "Veryfing Whether search with Full Claim Number takes user to Claim Details Page");
				String ClaimNumberinUI = driver.findElement(HAPClaimNumber).getText();
				softassert.assertTrue(datavalue.equals(ClaimNumberinUI), "Verifying Claim Details is displayed for Correct claim");
				driver.findElement(By.xpath("(//span[contains(text(),'Claims')])[2]")).click();
				
			}
			else if (data.equals("IncorrectClaimNumber")) {
				
				driver.findElement(DropdownButton).click();
		    	searchdropdown.selectByIndex(0);
		    	WebElement searchtextbox = driver.findElement(ClaimNumberTextBox);
		    	searchtextbox.click();
		    	
		    	searchtextbox.sendKeys("109765438700");
		    	
			    driver.findElement(SearchButton).click();
			    WebDriverWait wait1 = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait1.until(ExpectedConditions.elementToBeClickable(ErrorMessageOKButton));
				WebElement ErrorOK = driver.findElement(ErrorMessageOKButton);
				softassert.assertTrue(ErrorOK.isDisplayed(), "Validating Whether Eror message is displayed when searching with Incorrect Claim ID");
				driver.findElement(ErrorMessageOKButton).click();
				driver.findElement(By.xpath("(//span[contains(text(),'Claims')])[2]")).click();
			}
		    
	    }
	    else if (searchwith.equals("Member ID"))
	    {
	    	driver.findElement(DropdownButton).click();
	    	searchdropdown.selectByIndex(1);
	    	WebElement searchtextbox = driver.findElement(MemberIDTextBox);
	    	searchtextbox.click();
	    	searchtextbox.sendKeys(datavalue);
		    driver.findElement(SearchButton).click();
		}

	    driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
	   softassert.assertAll(); 
	}

	public void userentersthememberidandclickonsearch(String memberID) {
		// TODO Auto-generated method stub
		WebDriverWait wait1 = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait1.until(ExpectedConditions.elementToBeClickable(SearchButton));
		WebElement searchtextbox = driver.findElement(MemberIDTextBox);
		searchtextbox.click();
		String datavalue = (String) ScenarioContext.getContext(memberID);
		searchtextbox.sendKeys(datavalue);
		driver.findElement(SearchButton).click();
		boolean Errormessageispresent = driver.findElements(ErrorMessageOKButton).size()>0;
		if(Errormessageispresent==true) {
			wait1.until(ExpectedConditions.elementToBeClickable(ErrorMessageOKButton));
			driver.findElement(ErrorMessageOKButton).click();
			driver.findElement(By.xpath("(//span[contains(text(),'Claims')])[2]")).click();
		}
		else {
			wait1.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[contains(text(),'Claim Summary List')]"))));
			WebElement ClaimDetailsHeading = driver.findElement(By.xpath("//div[contains(text(),'Claim Summary List')]"));
			softassert.assertTrue(ClaimDetailsHeading.isDisplayed(), "Veryfing Whether search with memberID takes user to list of Claim  Page");
			String ClaimNumberinUI = driver.findElement(MemberIDinClaimsPage).getText();
			softassert.assertTrue(datavalue.equals(ClaimNumberinUI), "Verifying Claim Details is displayed for searched claim");	
			driver.findElement(By.xpath("(//span[contains(text(),'Claims')])[2]")).click();
			
		}
		softassert.assertAll();
	}

	public void userentersthepatientnumberandclicksonsearch(String patientNumber) {
		// TODO Auto-generated method stub
		WebDriverWait wait1 = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait1.until(ExpectedConditions.presenceOfElementLocated(Searchfilterdropdown));
		driver.findElement(DropdownButton).click();
		WebElement Dropdown = driver.findElement(Searchfilterdropdown);
		Select searchdropdown = new Select(Dropdown);
		searchdropdown.selectByIndex(2);
		WebElement searchtextbox = driver.findElement(PatientAccountNumberTextBox);
		searchtextbox.click();
		String datavalue = (String) ScenarioContext.getContext(patientNumber);
		searchtextbox.sendKeys(datavalue);
		driver.findElement(SearchButton).click();
		wait1.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[contains(text(),'Claim Summary List')]"))));
		WebElement ClaimDetailsHeading = driver.findElement(By.xpath("//div[contains(text(),'Claim Summary List')]"));
		softassert.assertTrue(ClaimDetailsHeading.isDisplayed(), "Veryfing Whether search with PatientID takes user to list of Claim  Page");
		String retrivedpatientnumber = driver.findElement(PatientIDinClaimsPage).getText();	
		softassert.assertTrue(retrivedpatientnumber.equals(datavalue),"Verifying Claim Details is displayed for searched claim");
		driver.findElement(By.xpath("(//span[contains(text(),'Claims')])[2]")).click();
		softassert.assertAll();

	}

		  
}


