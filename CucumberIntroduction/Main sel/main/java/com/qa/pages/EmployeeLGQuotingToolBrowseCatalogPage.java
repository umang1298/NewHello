package com.qa.pages;

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

public class EmployeeLGQuotingToolBrowseCatalogPage extends TestBase{

	public EmployeeLGQuotingToolBrowseCatalogPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softAssert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	//Page Factory
	By LOBDropdownButton = By.xpath("//button[@data-id='lineofbusinessform']");
	By LOBDropdown = By.xpath("//select[@id='lineofbusinessform']");
	By HSADropdownButton = By.xpath("//button[@data-id='hsaform']");
	By HSADropdown = By.xpath("//select[@id='hsaform']");
	By ResetLink = By.xpath("//a[@id='resetVal']");
	By DeductibleButton = By.xpath("//div[@id='dedRes']/button");
	By DedfromRangeTextBox = By.xpath("//input[@id='option1-rangeSelectedtext1']");
	By DedToRangeTextBox = By.xpath("//input[@id='option1-rangeSelectedtext2']");
	By DeductibleSlider = By.xpath("//input[@id='option1-rangeSelected']");
	By CoinsuranceButton = By.xpath("//div[@id='dedRes']/following-sibling::div/button");
	By CoinsfromRangeTextBox = By.xpath("//input[@id='option3-rangeSelectedtext1']");
	By CoinsToRangeTextBox = By.xpath("//input[@id='option3-rangeSelectedtext2']");
	By CoinsSlider = By.xpath("//input[@id='option3-rangeSelected']");
	By SearchBrowseCatalog = By.xpath("//button[@id='clickSearch']");
	By PrescriptionPlanBenefitsHeader = By.xpath("//div[contains(text(),'Prescription (Rx) Plan Benefits')]");
	By BacktoBrowseCatalog = By.xpath("//a[contains(text(),'to Browse Catalog')]");
	By ResultsFoundHeader = By.xpath("//span[contains(text(),'Results Found')]");
	By FilterByLOBExpandsymbol = By.xpath("(//span[@class='panel-arrow'])[2]");
	By FilterByDeductibleExpandsymbol = By.xpath("(//span[@class='panel-arrow'])[4]");
	By EPORadioButton = By.xpath("//input[@value='EPO']/following-sibling::label");
	By HMORadioButton = By.xpath("//input[@value='HMO']/following-sibling::label");
	By PPORadioButton = By.xpath("//input[@value='PPO']/following-sibling::label");
	By FilterByMedicalPlanBenfitsExpandArrow = By.xpath("//a[contains(text(),'Medical Plan Benefits')]//i");
	By FilterByApplyButton = By.xpath("//button[@id='apply']");
	
	public void userselectsLOB(String lOBValue) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ResetLink));
		Select LOBDropdownSelect = new Select(driver.findElement(LOBDropdown));
		driver.findElement(LOBDropdownButton).click();
		Thread.sleep(3000);
		LOBDropdownSelect.selectByValue(lOBValue);
	}


	public void userselectsHSA(String hSAValue) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ResetLink));
		Select HSADropdownSelect = new Select(driver.findElement(HSADropdown));
		driver.findElement(HSADropdownButton).click();
		Thread.sleep(3000);
		HSADropdownSelect.selectByValue(hSAValue);
	}


	public void usersetsrangeformedicalplanbenfitsandvalidatessliderfunctions() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ResetLink));
		Thread.sleep(3000);
		WebElement element = driver.findElement(DeductibleButton);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
		driver.findElement(DeductibleButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(DedfromRangeTextBox));
		driver.findElement(DedfromRangeTextBox).sendKeys("500");
		driver.findElement(DedToRangeTextBox).clear();
		driver.findElement(DedToRangeTextBox).sendKeys("7000");
		Thread.sleep(3000);
		String DedSliderValue = driver.findElement(DeductibleSlider).getAttribute("value");
		System.out.println("Ded SliderValue -- "+DedSliderValue);
		softAssert.assertTrue(DedSliderValue.equals("500,7000"), "Validating Ded Slider functionality");
		Thread.sleep(2000);
		driver.findElement(CoinsuranceButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(CoinsfromRangeTextBox));
		driver.findElement(CoinsfromRangeTextBox).sendKeys("5");
		driver.findElement(CoinsToRangeTextBox).clear();
		driver.findElement(CoinsToRangeTextBox).sendKeys("35");
		Thread.sleep(2000);
		String CoinsSliderValue = driver.findElement(CoinsSlider).getAttribute("value");
		System.out.println("Coins SliderValue -- "+CoinsSliderValue);
		softAssert.assertTrue(CoinsSliderValue.equals("5,35"), "Validating Coins Slider");
		driver.findElement(PrescriptionPlanBenefitsHeader).click();
		softAssert.assertAll();
	}


	public void userclicksSearch() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchBrowseCatalog));
		WebElement element = driver.findElement(SearchBrowseCatalog);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(SearchBrowseCatalog).click();	
	}


	public void uservalidatessearchresultisloaded() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(BacktoBrowseCatalog));
		Assert.assertTrue(driver.findElement(ResultsFoundHeader).isDisplayed(), "Validating search results are displayed");
	}


	public void usermodifiessearchinFilterByoptions() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FilterByLOBExpandsymbol));
		driver.findElement(FilterByLOBExpandsymbol).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(EPORadioButton));
		driver.findElement(EPORadioButton).click();
		WebElement MedicalPlanBenefitExpandArrow = driver.findElement(FilterByMedicalPlanBenfitsExpandArrow);
		js.executeScript("arguments[0].scrollIntoView(true);", MedicalPlanBenefitExpandArrow);
		MedicalPlanBenefitExpandArrow.click();
		wait.until(ExpectedConditions.elementToBeClickable(FilterByDeductibleExpandsymbol));
		driver.findElement(FilterByDeductibleExpandsymbol).click();
		wait.until(ExpectedConditions.elementToBeClickable(DedfromRangeTextBox));
		driver.findElement(DedfromRangeTextBox).clear();
		driver.findElement(DedfromRangeTextBox).sendKeys("0");
		driver.findElement(DedToRangeTextBox).clear();
		driver.findElement(DedToRangeTextBox).sendKeys("7350");
		WebElement Apply = driver.findElement(FilterByApplyButton);
		js.executeScript("arguments[0].scrollIntoView(true);", Apply);
		Apply.click();
	}

}
