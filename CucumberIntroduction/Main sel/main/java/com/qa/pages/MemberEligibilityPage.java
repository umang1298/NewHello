package com.qa.pages;

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
import com.qa.util.ScenarioContext;

public class MemberEligibilityPage extends TestBase{

	public MemberEligibilityPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	//Page Factory
	
	By  EnterMemberIDField = By.xpath("//input[@id='hapId1']");
	By  EnterMemberIDField2 = By.xpath("//input[@id='hapId2']");
	By  EnterMemberIDField3 = By.xpath("//input[@id='hapId3']");
	By SubmitButton = By.xpath("//button[contains(text(),'Submit')]");
	By AddMemberID = By.xpath("//span[contains(text(),'Add Member ID')]");
	By DisplayContractHistoryCheckBox = By.xpath("//input[@id='selectAllContracts']//..//span");
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;

	public void userClicksMemerRosterLink(String memberRoster) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		WebElement MemberRosterLink = driver.findElement(By.xpath("//a[contains(text(),'" + memberRoster + "')]"));
		js.executeScript("arguments[0].click();", MemberRosterLink);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);

	}

	public void entermemberid(String memberID) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		String MemberID = (String) ScenarioContext.getContext(memberID);
		driver.findElement(EnterMemberIDField).sendKeys(MemberID);
	}

	public void ClickSubmit() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		WebElement SubmitBtn = driver.findElement(SubmitButton);
		js.executeScript("arguments[0].click();", SubmitBtn);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
	}

	public void entermultiplememberIDs(String memberID1, String memberID2, String memberID3) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		String MemberID1 = (String) ScenarioContext.getContext(memberID1);
		String MemberID2 = (String) ScenarioContext.getContext(memberID2);
		String MemberID3 = (String) ScenarioContext.getContext(memberID3);
		driver.findElement(EnterMemberIDField).sendKeys(MemberID1);
		driver.findElement(AddMemberID).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(EnterMemberIDField2).sendKeys(MemberID2);
		driver.findElement(AddMemberID).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(EnterMemberIDField3).sendKeys(MemberID3);
	}

	public void checkdisplayContractHistory() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(DisplayContractHistoryCheckBox).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
	} 
	
	
	
	
}
