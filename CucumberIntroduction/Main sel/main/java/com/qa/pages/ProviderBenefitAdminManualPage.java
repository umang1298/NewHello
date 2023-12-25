package com.qa.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class ProviderBenefitAdminManualPage extends TestBase {

	public ProviderBenefitAdminManualPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	By PolicySearchPageHeading = By.xpath("//label[@class='inherittext']");
	By NumberOfResults = By.xpath("//div[contains(text(),'List of Policies')]//div//span[2]");
	By ResultsFound = By.xpath("//div[contains(text(),'List of Policies')]//div//span[3]");
	By SearchTextBox = By.xpath("//input[@id='bamwords']");
	By SearchButton = By.xpath("//button[@id='searchKeyword']");
	By NoResultsFoundPopUp = By.xpath("//div[contains(text(),'No search results found.')]");
	By PopUpOkButton = By.xpath("//button[contains(text(),'OK')]");
	By SearchResultHeading = By.xpath("//div[contains(text(),'List of Policies')]");
	By PolicyIndexButton = By.xpath("//button[contains(text(),'Policy Index')]");
	By PolicyNameTable = By.xpath("//*[@id=\"bamPolicyIndexResults\"]");
	By CategoricalIndexButton = By.xpath("//button[contains(text(),'Categorical Index')]");
	By CategoricalIndexPageHeading = By.xpath("//div[contains(text(),'Categorical')]");
	By CategoricalPageDropDownHeading = By.xpath("//div[@id='memberheading']");
	By RecentChangesButton = By.xpath("//button[contains(text(),'Recent Changes')]");
	By RecentChangesHeading = By.xpath("//div[contains(text(),'Recent')]");
	By SelectPeriodDropDown = By.xpath("//select[@id='monthId']");
	By MonthlySummaryChangesHeading = By.xpath("(//span[contains(text(),'February  2019')])[3]");
	
	
	public  void UserSearchesPolicyByTextAndValidatesSearchResultsAreDisplayed() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(PolicySearchPageHeading));
		driver.findElement(SearchTextBox).sendKeys("dme");
		driver.findElement(SearchButton).click();
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		System.out.println("Size "+ driver.findElements(SearchResultHeading).size());
		boolean resultsnotfound = driver.findElements(SearchResultHeading).size()==0;
		System.out.println("Boolean" + resultsnotfound);
		if(resultsnotfound==true) {
			Assert.assertTrue("Validating Pop is displayed if no search results is found",driver.findElement(PopUpOkButton).isDisplayed());
			driver.findElement(PopUpOkButton).click();
		}
		else {
			wait.until(ExpectedConditions.presenceOfElementLocated(NumberOfResults));
			wait.until(ExpectedConditions.textToBePresentInElementLocated(ResultsFound, "Results Found"));
			Assert.assertTrue("Validating  search results is found",driver.findElement(ResultsFound).isDisplayed());
		}
	}

	public void UserSearchesPolicyByPolicyIndexAndValidatesTheSearchResultsAreDisplayed() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		driver.findElement(PolicyIndexButton).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(PolicyNameTable));
		
	}

	public void UserSearchesPolicyByCategoricalIndexAndValidatesTheSearchedResultsAreDisplayed() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		driver.findElement(CategoricalIndexButton).click();
		//wait.until(ExpectedConditions.presenceOfElementLocated(CategoricalIndexPageHeading));
		wait.until(ExpectedConditions.presenceOfElementLocated(CategoricalPageDropDownHeading));
	}

	public void UserSearchesPolicyByRecentChangesAndValidateSearchedResultsAreDisplayed() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		driver.findElement(RecentChangesButton).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(RecentChangesHeading));
		WebElement Dropdown = driver.findElement(SelectPeriodDropDown);
		Select selectperioddropdown = new Select(Dropdown);
		Thread.sleep(15000);
		selectperioddropdown.selectByValue("February  2019");
		String Resultsfound = driver.findElement(MonthlySummaryChangesHeading).getText();
		System.out.println("retrievedelement "+Resultsfound);
		softassert.assertTrue(Resultsfound.equals("February  2019"),"Verifying whether the results are displayed for the selected period");

	}

}
