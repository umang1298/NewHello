package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
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

public class ProviderClaimsAppealPage extends TestBase{

	public ProviderClaimsAppealPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	Scenario scenario;
	
	//Page Factory
	By AppealOptionsDropDown = By.xpath("//select[@id='appealId']");
	By NextBtn = By.xpath("//button[contains(text(),'Next')]");
	By AppealNotesNextBtn = By.xpath("(//button[contains(text(),'Next')])[2]");
	By AppealNotesPreviousBtn = By.xpath("//button[contains(text(),'Previous')]");
	By Notes = By.xpath("//textarea[@id='myTextNotes']");
	By CompletedBy = By.xpath("//input[@id='myTextCompletedBy']");
	By EmailAddress = By.xpath("//input[@id='myEmailAddress']");
	By PhoneNumber = By.xpath("//input[@id='myTextPhone']");
	By SubmitBtn = By.xpath("//button[contains(text(),'Submit')]");
	By AppealTrackingNumber = By.xpath("//span[@id='appealTrackingNumber']");
	By CaseIDText = By.xpath("//div[contains(text(),'Please retain this case number for your records: ')]");
	By NoButton = By.xpath("//button[contains(text(),'No')]");
	
	
	public void userselectsoptionforAppealandClicksNext(String forappeal) {
		// TODO Auto-generated method stub
		String Forappeal =(String) ScenarioContext.getContext(forappeal);
		if(Forappeal.equals("true")) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(AppealOptionsDropDown));
		wait.until(ExpectedConditions.elementToBeClickable(NextBtn));
		//driver.findElement(AppealOptionsDropDown).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		Select options = new Select(driver.findElement(AppealOptionsDropDown));
		options.selectByVisibleText("PRICING DISPUTE");
		driver.findElement(NextBtn).click();
		}
	}

	public void userenterappealnotesandclicksNext(String forappeal) {
		// TODO Auto-generated method stub
		String Forappeal =(String) ScenarioContext.getContext(forappeal);
		if(Forappeal.equals("true")) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(AppealNotesPreviousBtn));
		driver.findElement(Notes).sendKeys("Notes");
		driver.findElement(CompletedBy).sendKeys("Test Automation");
		driver.findElement(EmailAddress).sendKeys("noreply@hap.org");
		driver.findElement(PhoneNumber).sendKeys("9894667738");
		driver.findElement(AppealNotesNextBtn).click();
		}
	}

	public void usersubmitstheappeal(String forappeal) {
		// TODO Auto-generated method stub
		String Forappeal =(String) ScenarioContext.getContext(forappeal);
		if(Forappeal.equals("true")) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
		driver.findElement(SubmitBtn).click();
		CommonMethods.Takescreenshot(scenario);
		}
	}

	public void userverifiescaseidandclicksNo(String forappeal) {
		// TODO Auto-generated method stub
		String Forappeal =(String) ScenarioContext.getContext(forappeal);
		if(Forappeal.equals("true")) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(NoButton));
		WebElement CaseID = driver.findElement(AppealTrackingNumber);
		CommonMethods.Takescreenshot(scenario);
		softassert.assertTrue(CaseID.isDisplayed(), "Verifying Case ID is displayed or Not");
		softassert.assertAll();
		String TrackingNumber = CaseID.getText();
		System.out.println("Generated Trackig Number -->" + TrackingNumber);

		}
	}
	
	
}
	
	
	

