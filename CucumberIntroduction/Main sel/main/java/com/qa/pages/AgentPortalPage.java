package com.qa.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class AgentPortalPage extends TestBase{
	public AgentPortalPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	//Page Factory
	By logoXpath=By.xpath("//div[1]/header/div[2]/div[1]/a/img | //a[@id='logoLink']/img | //div[@id='mainPageHeader']/div[1]/img");
	By Enrollmentxpath=By.xpath("//a[@id='logoLink']/img | //span[@id='logo'] | //div[@id='mainPageHeader']/div[1]/img");
	By AnnouncementLink=By.xpath("//img[@id='agentAnnNotification']");
	By AnnouncementPage=By.xpath("//div[contains(text(),'Announcements')]");
	By HapConnectivity=By.xpath("//div[@id='mainPageHeader']/div[1]/img");
	By SearchXpath=By.xpath("//i[@id='searchboxtext']");
	By SearchTextXpath=By.xpath("//input[@id='agentHapSearchInput']");
	By SearchIcorn=By.xpath("//i[@class='icon-magnifying-glass']");
	By SearchResultsText=By.xpath("//div[@class='hap-small-headings xs-hap-small-headings sm-hap-small-headings']");
	By ProducerLink=By.xpath("//span[contains(text(),'Producer Registration')]");
	By ProducerPage=By.xpath("//div[contains(text(),'Producer Registration')]");
	By Expand=By.xpath("//h4/i[contains(text(),'keyboard')]");
	By Backbutton=By.xpath("//button[contains(text(),'Back')]");
	By QuoteLink=By.xpath("//a[@id='uxLinkGetIndividualQuote']");
	By StaticpageHeading=By.xpath("//div[@id='page-content-wrapper']/div/div/div/div/div/div[1]/div/div[1]/div");
	
	JavascriptExecutor js=(JavascriptExecutor) driver;
	public void clickontheAgentPortalmenu(String menuoptiontopass) {
		// TODO Auto-generated method stub
		String xpath="//div/h3[contains(text(),'"+menuoptiontopass+"')]";
		driver.findElement(By.xpath(xpath)).click();
	}


	public void validateAgentPortalmenupage() throws InterruptedException {
		// TODO Auto-generated method stub
		
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Enrollmentxpath));
		Thread.sleep(3000);
		Assert.assertTrue("Agent Portal menu page is not displayed",driver.findElement(Enrollmentxpath).isDisplayed());
	    driver.close();
		driver.switchTo().window(newtabs.get(0));
	}


	public void validateAgentAnnouncementpage() {
		// TODO Auto-generated method stub
		driver.findElement(AnnouncementLink).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(AnnouncementPage));
		Assert.assertTrue("Announcement Page is not displayed",driver.findElement(AnnouncementPage).isDisplayed());
		
		
	}
	public void validateAgentPortalSearch() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchXpath));
		driver.findElement(SearchXpath).click();
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.findElement(SearchTextXpath).sendKeys("Producer Registration");
		//driver.findElement(SearchIcorn).click();
		driver.findElement(SearchTextXpath).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.presenceOfElementLocated(SearchResultsText));
		String Search=driver.findElement(SearchResultsText).getText();
		Assert.assertTrue("Search results is not fetched",Search.contains("Search Results"));
		driver.findElement(ProducerLink).click();
//		Thread.sleep(3000);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.presenceOfElementLocated(ProducerPage));
	WebElement producer=driver.findElement(ProducerPage);
		Assert.assertTrue("Producer Registration Page is not displased",producer.isDisplayed());
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}

	

	

	public void clickandvalidatetheLink(String planName, String linkname) throws InterruptedException {
		// TODO Auto-generated method stub
		String plannameXpath= "//h4[contains(text(),'" + planName + "')]";
		driver.findElement(By.xpath(plannameXpath)).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		Thread.sleep(4000);
		String LinkXpath="//h4[contains(text(),'" +planName+ "')]//..//..//..//child::div[@class='panel-collapse collapse in']//h3[contains(text(),'" + linkname + "')]";
		System.out.println(LinkXpath);
		//Thread.sleep(2000);
		WebElement Link=driver.findElement(By.xpath(LinkXpath));
		js.executeScript("arguments[0].click();", Link);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("document_iframe")));
		Thread.sleep(2000);
		WebElement staticpageHeading=driver.findElement(StaticpageHeading);
		Assert.assertTrue("Respective page is not Displayed",staticpageHeading.isDisplayed());
		driver.findElement(Backbutton).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(plannameXpath)));
//		driver.findElement(By.xpath(plannameXpath)).click();
//		Thread.sleep(3000);
	}


	public void ValidatelinkleadstoRespectivePage(String planName, String linkname) throws InterruptedException {
		// TODO Auto-generated method stub
		String plannameXpath= "//h4[contains(text(),'" + planName + "')]";
		driver.findElement(By.xpath(plannameXpath)).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		Thread.sleep(4000);
		String LinkXpath="//h4[contains(text(),'" +planName+ "')]//..//..//..//child::div[@class='panel-collapse collapse in']//h3[contains(text(),'" + linkname + "')]";
		System.out.println(LinkXpath);
		WebElement Link=driver.findElement(By.xpath(LinkXpath));
		js.executeScript("arguments[0].click();", Link);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.presenceOfElementLocated(logoXpath));
		Thread.sleep(3000);
		Assert.assertTrue("Respective Page is not displayed",driver.findElement(logoXpath).isDisplayed());
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		driver.findElement(By.xpath(plannameXpath)).click();
		Thread.sleep(3000);
	}


	public void validatedocumentgetdownloaded(String planName, String linkname) throws InterruptedException {
		// TODO Auto-generated method stub
		String plannameXpath= "//h4[contains(text(),'" + planName + "')]";
		driver.findElement(By.xpath(plannameXpath)).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		Thread.sleep(4000);
		String LinkXpath="//h4[contains(text(),'" +planName+ "')]//..//..//..//child::div[@class='panel-collapse collapse in']//h3[contains(text(),'" + linkname + "')]";
		System.out.println(LinkXpath);
		WebElement Linkelexpath=driver.findElement(By.xpath(LinkXpath));
		js.executeScript("arguments[0].click();", Linkelexpath);
		Thread.sleep(40000);
		String Filename=linkname.substring(linkname.lastIndexOf(' ')+1);
		System.out.println("Filename" +Filename);
		if(Filename.equals("2020"))
		{
		File QuotingExcel = new File (System.getProperty("user.home") + "/Downloads/" +Filename+ " SG Rating Tool v2.1.xlsx");
		System.out.println("File: " + QuotingExcel);
		Thread.sleep(4000);
		Assert.assertTrue("Verifying excel file is downloaded for Quoting",QuotingExcel.exists());
		Thread.sleep(2000);
		QuotingExcel.delete();
		}
		else
		{
			File QuotingExcel = new File (System.getProperty("user.home") + "/Downloads/" +Filename+ " SG Rating Tool v1.xlsx");
			System.out.println("File: " + QuotingExcel);
			Thread.sleep(4000);
			Assert.assertTrue("Verifying excel file is downloaded for Quoting",QuotingExcel.exists());
			Thread.sleep(2000);
			QuotingExcel.delete();
		}
		
		Thread.sleep(1000);
		
	}


	public void validateslinkcontainspdf(String planName, String linkname) throws InterruptedException {
		// TODO Auto-generated method stub
				String plannameXpath= "//h4[contains(text(),'" + planName + "')]";
				driver.findElement(By.xpath(plannameXpath)).click();
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				Thread.sleep(4000);
				String LinkXpath="//h4[contains(text(),'" +planName+ "')]//..//..//..//child::div[@class='panel-collapse collapse in']//h3[contains(text(),'" + linkname + "')]";
				System.out.println(LinkXpath);
				WebElement Link=driver.findElement(By.xpath(LinkXpath));
				js.executeScript("arguments[0].click();", Link);
				ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(newtabs.get(1));
				wait.until(ExpectedConditions.urlContains("pdf"));
				Thread.sleep(3000);
				Assert.assertTrue("Respective Page is not displayed",driver.getCurrentUrl().contains("pdf"));
				driver.close();
				driver.switchTo().window(newtabs.get(0));
				driver.findElement(By.xpath(plannameXpath)).click();
				Thread.sleep(3000);
	}


	

}
