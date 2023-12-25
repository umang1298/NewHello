package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class EmployeeLGQuotingToolPage extends TestBase {

	public EmployeeLGQuotingToolPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softAssert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	//Page Factory
	By SubmitButton = By.xpath("//button[contains(text(),'Submit')]");
	By DropDown = By.xpath("//select[@id='catalogtype']");
	By DropDownButton = By.xpath("//button[@data-id='catalogtype']");
	By SearchTextBox = By.xpath("//input[@id='inputValue']");
	By SearchResultsTable = By.xpath("//table[@class='table quotetables']");
	By FirstQuoteIDinResults = By.xpath("//table[@class='table quotetables']/tbody/tr[1]/td[1]/a/span");
	By SearchResultCancelButton = By.xpath("//button[contains(text(),'Cancel')]");
	By BrowseCatalogButton = By.xpath("//button[@id='browsecatalog']");
	By ResetLink = By.xpath("//a[@id='resetVal']");
	
	public void userperformssearchforexistingquotingusingcompanyname(String Searchwith, String valueContext) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		userselectsfromthedropdown(Searchwith);
		String value = (String) ScenarioContext.getContext(valueContext);
		driver.findElement(SearchTextBox).clear();
		System.out.println("Value-->"+ value);
		driver.findElement(SearchTextBox).sendKeys(value);
		userclicksonsubmitbutton();
	}
	
	public void userselectsfromthedropdown(String option) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		Select dropdown = new Select(driver.findElement(DropDown));
		driver.findElement(DropDownButton).click();
		dropdown.selectByValue(option);
		
	}
	
	public void userclicksonsubmitbutton() {
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(SubmitButton).click();
		
	}

	public void usertakesQuoteIDandGroupIDfromsearchresults(String quoteID, String groupID) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FirstQuoteIDinResults));
		String QuoteIDFromSearchResults = driver.findElement(FirstQuoteIDinResults).getText();
		ScenarioContext.setContext(quoteID, QuoteIDFromSearchResults);
		int numberofresults = driver.findElements(By.xpath("//table[@class='table quotetables']/tbody/tr")).size();
		System.out.println("Number of Results"+ numberofresults);
		for(int i=1;i<=numberofresults;i++) {
			String xpathtoGroupID = "//table[@class='table quotetables']/tbody/tr["+i+"]/td[3]/span";
			String GroupIDfromsearchResult =driver.findElement(By.xpath(xpathtoGroupID)).getText();
			//System.out.println("Group ID-->"+GroupIDfromsearchResult);
			if(!GroupIDfromsearchResult.isEmpty()) {
				System.out.println("Group ID-->"+GroupIDfromsearchResult);
				ScenarioContext.setContext(groupID, GroupIDfromsearchResult);
				break;
			}
		}
		
	}

	public void uservalidatesSearchresultTableisdisplayed() throws InterruptedException {
		
		// TODO Auto-generated method stub
		Thread.sleep(3000);
		boolean resulttableisdisplayed = driver.findElements(SearchResultsTable).size()>0;
		
		if(resulttableisdisplayed==true) {
		
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FirstQuoteIDinResults));
		Assert.assertTrue(driver.findElement(SearchResultsTable).isDisplayed(), "Validating search result table is displayed");
		WebElement CancelButton = driver.findElement(SearchResultCancelButton);
		js.executeScript("arguments[0].click();", CancelButton);
		}
	}


	public void userclicksonquoteidlink(String quoteID) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FirstQuoteIDinResults));
		String QuoteIDFromSearchResults = driver.findElement(FirstQuoteIDinResults).getText();
		ScenarioContext.setContext(quoteID, QuoteIDFromSearchResults);
		driver.findElement(FirstQuoteIDinResults).click();
	}

	public void userclicksonBrowseCatalog() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(BrowseCatalogButton));
		driver.findElement(BrowseCatalogButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(ResetLink));
	}

}
