package com.qa.pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

public class DNSPSearchPage extends TestBase  {

	public DNSPSearchPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softAssert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	
	//Page Factory
	By SearchTextBox=By.xpath("//input[@id='providerName']");
	By NPISearchTextBox=By.xpath("//input[@id='providerNPI']");
	By SearchButton=By.xpath("//button[@id='search-user-button']");
	By SearchFilter=By.xpath("//button[@data-id='searchfilter']");
	By Firstfield=By.xpath("//table[@id='providerDetails']/tbody/tr[1]/td[3]");
	By ProviderDetailsTable=By.xpath("//table[@id='providerDetails']");
	By IPADetailsTable=By.xpath("//table[@id='dsnipIpaNamesDetails']");
	By TrainingDetailsTable=By.xpath("//table[@id='dsnipTrainingDetails']");
	By ListofProviders=By.id("targetListProvider");
	By Trainingyear=By.id("trainingYear");
	By FromDate=By.xpath("//input[@id='fromDate']");
	By ToDate=By.xpath("//input[@id='toDate']");
	By TargetListname=By.xpath("//input[@id='dsnipTargetListName']");
	By DNSPSaveButton=By.xpath("//button[@id='dsnip-save-user-button']");
	By DNSPRunButton=By.xpath("//button[@id='dsnip-run-user-button']");
	By FirstNPI=By.xpath("//table[@id='runDsnipDataResults']/tbody/tr[1]/td[2]");
	By SavedName=By.xpath("//table[@id='dsnipSavedTargetDataResults']/tbody/tr/td[2]");
	By Delete=By.xpath("//span[contains(text(),'Delete')][1]");
	By Okay=By.xpath("//button[contains(text(),'Okay')]");
	By Download= By.xpath("//button[@id='dsnp_download-run-button']");
	By dashboardlabel=By.xpath("//div[contains(text(),'Training Compliance')]");
	
	public void passvalueintextboxandclickonsearchbutton(String providernamemenu, String providername) {
		// TODO Auto-generated method stub
		String MenuoptionXpath = "(//a/span[contains(text(),'"+providernamemenu+"')])";
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(SearchButton)));
		driver.findElement(SearchFilter).click();
		driver.findElement(By.xpath(MenuoptionXpath)).click();
		System.out.println("Clicked on " +providernamemenu);
		if(providernamemenu.equals("NPI"))
		{
			driver.findElement(NPISearchTextBox).sendKeys(providername);
		}
		else
		{
		driver.findElement(SearchTextBox).sendKeys(providername);
		}
		driver.findElement(SearchButton).click();
	}

	public void validateprovidnamesearchresult() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(ProviderDetailsTable));
		System.out.println("Test:");
		WebElement element = driver.findElement(ProviderDetailsTable);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Assert.assertTrue(driver.findElement(ProviderDetailsTable).isDisplayed(),"Provider Details Table is not present");
		Assert.assertTrue(driver.findElement(IPADetailsTable).isDisplayed(),"IPA details Table is not present");
		Assert.assertTrue(driver.findElement(TrainingDetailsTable).isDisplayed(),"Training details Table is not present");
	//	Assert.assertTrue(driver.findElement(Firstfield).isDisplayed(),"Provider do not have NPI details");
		}
	

	public void runtheTrainingReport() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(DNSPRunButton)));
		Select drop1=new Select(driver.findElement(ListofProviders));
		drop1.selectByIndex(2);
		Thread.sleep(2000);
		Select drop2=new Select(driver.findElement(Trainingyear));
		drop2.selectByIndex(1);
		Thread.sleep(2000);
//		driver.findElement(FromDate).sendKeys("12/11/2019");
//		driver.findElement(FromDate).sendKeys("12/11/2020");
		driver.findElement(DNSPRunButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(FirstNPI)));
		Assert.assertTrue(driver.findElement(FirstNPI).isDisplayed(),"Search result is not found");
		
	}

	public void savetheTrainingReport() throws InterruptedException {
		// TODO Auto-generated method stub
		
		driver.findElement(TargetListname).sendKeys("Automation");
		driver.findElement(DNSPSaveButton).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(Okay)));
		driver.findElement(Okay).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(DNSPRunButton)));
		String Savedname=driver.findElement(SavedName).getText();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		System.out.println("savedname"+Savedname);
		Assert.assertTrue(Savedname.contains("Automation"), "List is not saved");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		
		
	}

	public void deletetheTrainingReport() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		driver.findElement(Delete).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(Okay)));
		driver.findElement(Okay).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		String Removedname1=driver.findElement(SavedName).getText();
		Assert.assertTrue(!Removedname1.contains("Automation"),"File name is not Deleted");
		
	}

	public void downloadtheTrainingReport() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.findElement(Download).click();
		Thread.sleep(3000);
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		    Date date = new Date();  
		    String date1=formatter.format(date);
		    System.out.println("date:"+date1);  
//		String Filename="DsnpGapProviderData_2021-01-04_10_51_34";
//		File TrainingExcel = new File (System.getProperty("user.home") + "/Downloads/" +Filename+ ".xlsx");
//		System.out.println("File: " + TrainingExcel);
//		Thread.sleep(4000);
//		Assert.assertTrue(TrainingExcel.exists(),"Verifying excel file is downloaded for Training Report");
//		Thread.sleep(2000);
//		TrainingExcel.delete();
	}

	

	public void validatesDashboard() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(dashboardlabel));
		Assert.assertTrue(driver.findElement(dashboardlabel).isDisplayed(),"Dashboard page is not displayed");
	}
	
}
