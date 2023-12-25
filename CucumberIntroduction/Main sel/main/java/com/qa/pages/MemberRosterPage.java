package com.qa.pages;

import java.util.List;
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
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class MemberRosterPage extends TestBase{

	public MemberRosterPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	//Page Factory
	
	By Dropdown = By.id("select-picker");
	By SearchButton = By.id("roster-search-btn");
	By SearchIcon = By.id("search-box-icon");
	By SearchProvider = By.id("searchProvider");
	By FirstSearchResultProvider = By.xpath("//table[@id='searchResult']//tbody//tr//td[1]//span");
	By LastNameTextBox = By.xpath("//input[@id='LastName']");
	By MemberRosterHeading = By.xpath("//div[contains(text(),'Member Roster as of ')]");
	By FirstSearchResultMemberid = By.xpath("//td[2]//a//span");
	By ThirdSearchResultsMemberid = By.xpath("//table[@id='rosterresults']//tbody//tr[3]//td[2]//a//span");
	By Options = By.xpath("//option");
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	public void selectvaluefromDropdownandClickSearch() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Options));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		Thread.sleep(4000);
		Select dropdown = new Select(driver.findElement(Dropdown));
		dropdown.selectByIndex(3);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebElement SearchBtn = driver.findElement(SearchButton);
		js.executeScript("arguments[0].click();", SearchBtn);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		//WebElement SearchResultHeading = driver.findElement(MemberRosterHeading);
		wait.until(ExpectedConditions.elementToBeClickable(MemberRosterHeading));
	}
	public void getmemberid(String memberID) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FirstSearchResultMemberid));
		String MemberIDVal = driver.findElement(FirstSearchResultMemberid).getText();
		System.out.println("This is the First Member id from search results --> " + MemberIDVal );
		ScenarioContext.setContext(memberID, MemberIDVal);
	}
	
	
	public void getsubscriberidfrommemberid(String subscriberID, String memberID) {
		// TODO Auto-generated method stub
		String MemberIDVal = (String) ScenarioContext.getContext(memberID);
		String SubscriberIDVal = MemberIDVal.substring(0, 9);
		System.out.println("This is the Subscriber ID --> " +SubscriberIDVal);
		ScenarioContext.setContext(subscriberID, SubscriberIDVal);
	}
	
	public void getsubscriberidandsuffixfrommemberID(String subscriberID, String memberSuffix, String memberID) {
		// TODO Auto-generated method stub
		String MemberIDVal = (String) ScenarioContext.getContext(memberID);
		String SubscriberIDVal = MemberIDVal.substring(0, 9);
		String MemberSuffixVal = MemberIDVal.substring(10, 11);
		System.out.println("This is the Subscriber ID --> " +SubscriberIDVal);
		System.out.println("This is the Suffix  --> " +MemberSuffixVal);
		ScenarioContext.setContext(subscriberID, SubscriberIDVal);
		ScenarioContext.setContext(memberSuffix, MemberSuffixVal);
	}
	
	public void getthreememberids(String mem1, String mem2, String mem3) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FirstSearchResultMemberid));
		List<WebElement> MemberIDResults = driver.findElements(FirstSearchResultMemberid);
		int numberofResult = MemberIDResults.size();
		System.out.println("Number of Results Member id's -->" +numberofResult);
		if(numberofResult>=3) {
			String Mem1 = driver.findElement(By.xpath("(//td[2]//a//span)[1]")).getText();
			String Mem2 = driver.findElement(By.xpath("(//td[2]//a//span)[2]")).getText();
			String Mem3 = driver.findElement(By.xpath("(//td[2]//a//span)[3]")).getText();
			ScenarioContext.setContext(mem1, Mem1);
			ScenarioContext.setContext(mem2, Mem2);
			ScenarioContext.setContext(mem3, Mem3);
		}
		else {
			System.out.println("Number of Results in Member roster is less than 3");
		}
	}
	public void usersearchesproviderandselectsone(String lastname) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchIcon));
		driver.findElement(SearchIcon).click();
		wait.until(ExpectedConditions.elementToBeClickable(SearchProvider));
		driver.findElement(LastNameTextBox).sendKeys(lastname);
		driver.findElement(SearchProvider).click();
		wait.until(ExpectedConditions.elementToBeClickable(FirstSearchResultProvider));
		driver.findElement(FirstSearchResultProvider).click();		
	}
	
	public void userclickssearchbutton() {
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton));
		driver.findElement(SearchButton).click();
		
	}
	
	
}
