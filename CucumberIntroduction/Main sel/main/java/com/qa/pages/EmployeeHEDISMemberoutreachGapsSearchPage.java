package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class EmployeeHEDISMemberoutreachGapsSearchPage extends TestBase {

	public EmployeeHEDISMemberoutreachGapsSearchPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	    
		TestBase.driver = driver;
}
	SoftAssert softAssert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	//Page Factory
	
	By SearchIconOne = By.id("search-box-icon");
	By MembersearchButton = By.xpath("//button[@id='searchMember']");
	By MemberLastNameTextBox = By.xpath("//input[@id='LastName']");
	By MemberFirstNameTextBox = By.xpath("//input[@id='FirstName']");
	By MemberFirstSearchresult = By.xpath("//table[@id='searchResult']//tbody//tr[5]//td//span");
	By Membersearchresultstable = By.xpath("//table[@id='searchResult']");
	By MemberFirstSearchButton = By.xpath("//button[@id='search-button']");
	By MemberIdTextBox = By.xpath("//input[@id='memberId']");
	By HEDISGapresultsPageHeading = By.xpath("//div[contains(text(),'HEDIS Gaps as')]");
	By HEDISGapResults = By.xpath("(//div[@class='table-responsive'])[2]");
	By ErrorMessageOkButton = By.xpath("//button[contains(text(),'Ok')]");
	                                       
	public void usersearchesforMemberidwithmembername(String firstName, String lastName) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchIconOne));
		driver.findElement(SearchIconOne).click();
		wait.until(ExpectedConditions.elementToBeClickable(MembersearchButton));
		wait.until(ExpectedConditions.presenceOfElementLocated(MemberLastNameTextBox));
		driver.findElement(MemberLastNameTextBox).sendKeys(lastName);
		driver.findElement(MemberFirstNameTextBox).sendKeys(firstName);
		Thread.sleep(2000);
		driver.findElement(MembersearchButton).click();
		WebElement element = driver.findElement(MemberFirstSearchresult);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
		wait.until(ExpectedConditions.presenceOfElementLocated(Membersearchresultstable));
		wait.until(ExpectedConditions.elementToBeClickable(MemberFirstSearchresult));
		driver.findElement(MemberFirstSearchresult).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(MemberIdTextBox));
		driver.findElement(MemberFirstSearchButton).click();
		Thread.sleep(4000);
	}

	public void uservalidatesHEDISGapsresultsaredisplayed() {
		// TODO Auto-generated method stub
		boolean errormessagedisplayed = driver.findElements(ErrorMessageOkButton).size()>0;
		if(errormessagedisplayed==true) {
			driver.findElement(ErrorMessageOkButton).click();
		}
		else {
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(MemberFirstSearchButton));
		WebElement HEDISGapsResult = driver.findElement(HEDISGapResults);
		WebElement PageHeading = driver.findElement(HEDISGapresultsPageHeading);
		softAssert.assertTrue(PageHeading.isDisplayed(), "Validating whether Hedis gaps Results page is loaded");
		softAssert.assertTrue(HEDISGapsResult.isDisplayed(), "Validating whether Hedis Results are displayed");
		softAssert.assertAll();
		}
	}

}
