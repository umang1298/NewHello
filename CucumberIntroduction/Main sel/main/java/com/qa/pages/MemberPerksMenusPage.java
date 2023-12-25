package com.qa.pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class MemberPerksMenusPage extends TestBase {

	public MemberPerksMenusPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	//Page Factory
	By MemberPerksLink =By.xpath("//div/a/span[contains(text(),'Member Perks')]");
	By MemberDiscountsLink=By.xpath("//a/span[contains(text(),'Member Discounts')]");
	By MemberEventsLink=By.xpath("//a/span[contains(text(),'Member Events')]");
	By MemberResourcesLink=By.xpath("//a/span[contains(text(),'Member Resources')]");
	By AssistAmericaLink=By.xpath("//a/span[contains(text(),'Assist America Travel Assistance')]");
	By IdentityLink=By.xpath("//a/span[contains(text(),'Identity Theft Protection')]");
	By WeightProgramLink=By.xpath("//a/span[contains(text(),'Weight Management Program')]");
	By FillingLink=By.xpath("//a/span[contains(text(),'Filling Your Prescription')]");
	By Frame=By.xpath("//iframe[@id='document_iframe']");
	By MemberDiscounts=By.xpath("//div[contains(text(),'Money Saving Discounts')]");
	By MemberEvents=By.xpath("//div[contains(text(),'Member Events')]");
	By MemberResources=By.xpath("//div[contains(text(),'Member Resources')]");
	By AssistTravel=By.xpath("//div[contains(text(),'Worldwide travel assistance')]");
	By TheftProtection=By.xpath("//div[contains(text(),'Identity Theft Protection')]");
	By Haplogo=By.xpath("//div[@class='logo']//a//img");
	SoftAssert softassert = new SoftAssert();
	public void ValidatingMemberPerksStaticlinks(String Usertype) throws InterruptedException {
		// TODO Auto-generated method stub
		if(Usertype.equals("Medicare-Individual"))
		{
			ValidateMemberDiscounts();
			ValidateMemberEvents();
			ValidateMemberResources();
			ValidateAssistAmerica();
			ValidateIdentityTheft();
		}
		else if(Usertype.equals("Medicare-EGWP"))
		{
			ValidateMemberDiscounts();
			ValidateMemberEvents();
			ValidateMemberResources();
			ValidateAssistAmerica();
			ValidateIdentityTheft();
		}
		else
		{
			ValidateMemberDiscounts();
			ValidateMemberEvents();
			ValidateMemberResources();
			ValidateAssistAmerica();
			ValidateIdentityTheft();
	//ValidatWeightProgram();
	//ValidateFillingPrescription();
		}
	
	}
	private void ValidatWeightProgram() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	//	wait.until(ExpectedConditions.elementToBeClickable(MemberPerksLink));
		driver.findElement(MemberPerksLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(WeightProgramLink));
		driver.findElement(WeightProgramLink).click();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(Haplogo));
		softassert.assertTrue(driver.findElement(Haplogo).isDisplayed(),"Weight program page is not displayed");
		softassert.assertAll();
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}
	private void ValidateFillingPrescription() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		//wait.until(ExpectedConditions.elementToBeClickable(MemberPerksLink));
		//driver.findElement(MemberPerksLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(FillingLink));
		driver.findElement(FillingLink).click();
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Haplogo));
		softassert.assertTrue(driver.findElement(Haplogo).isDisplayed(),"Filling prescription page is not displayed");
		softassert.assertAll();
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}
	private void ValidateIdentityTheft() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(MemberPerksLink));
		driver.findElement(MemberPerksLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(IdentityLink));
		driver.findElement(IdentityLink).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Frame));
		WebElement iFrame= driver. findElement(Frame);
		Thread.sleep(2000);
		driver.switchTo().frame(iFrame);
	//	WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
//		wait.until(ExpectedConditions.presenceOfElementLocated(TheftProtection));
		driver.manage().timeouts().pageLoadTimeout(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		String IdentityTheft=driver.findElement(TheftProtection).getText();
		Assert.assertTrue("Identity theft page is not displayed",IdentityTheft.contains("Identity Theft Protection"));
		driver.switchTo().defaultContent();	
	}
	private void ValidateAssistAmerica() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(MemberPerksLink));
		driver.findElement(MemberPerksLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(AssistAmericaLink));
		driver.findElement(AssistAmericaLink).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Frame));
		WebElement iFrame= driver. findElement(Frame);
		Thread.sleep(2000);
		driver.switchTo().frame(iFrame);
	//	WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
//		wait.until(ExpectedConditions.presenceOfElementLocated(AssistTravel));
		driver.manage().timeouts().pageLoadTimeout(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		String Travel=driver.findElement(AssistTravel).getText();
		Assert.assertTrue("Assist America page is not displayed",Travel.contains("Worldwide travel assistance"));
		driver.switchTo().defaultContent();	
	}
	public void ValidateMemberResources() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(MemberPerksLink));
		driver.findElement(MemberPerksLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(MemberResourcesLink));
		driver.findElement(MemberResourcesLink).click();
		Thread.sleep(2000);
		WebElement iFrame= driver. findElement(Frame);
		driver.switchTo().frame(iFrame);
		Thread.sleep(2000);
		driver.manage().timeouts().pageLoadTimeout(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
//		wait.until(ExpectedConditions.presenceOfElementLocated(MemberResources));
		String Resources=driver.findElement(MemberResources).getText();
		Assert.assertTrue("Resources page is not displayed",Resources.contains("Member Resources"));
		driver.switchTo().defaultContent();	
	}
	public void ValidateMemberEvents() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(MemberPerksLink));
		driver.findElement(MemberPerksLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(MemberEventsLink));
		driver.findElement(MemberEventsLink).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Frame));
		WebElement iFrame= driver. findElement(Frame);
		Thread.sleep(2000);
		driver.switchTo().frame(iFrame);
		driver.manage().timeouts().pageLoadTimeout(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		//WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	//	wait.until(ExpectedConditions.presenceOfElementLocated(MemberEvents));
		String Events=driver.findElement(MemberEvents).getText();
		Assert.assertTrue("Events page is not displayed",Events.contains("Member Events"));
		driver.switchTo().defaultContent();	
	}
	public void ValidateMemberDiscounts() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(MemberPerksLink));
		driver.findElement(MemberPerksLink).click();
		wait.until(ExpectedConditions.elementToBeClickable(MemberDiscountsLink));
		driver.findElement(MemberDiscountsLink).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Frame));
		WebElement iFrame= driver. findElement(Frame);
		driver.switchTo().frame(iFrame);
		Thread.sleep(2000);
		driver.manage().timeouts().pageLoadTimeout(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		String Discounts=driver.findElement(MemberDiscounts).getText();
		System.out.println("disc:"+Discounts);
		driver.manage().timeouts().pageLoadTimeout(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		Assert.assertTrue("Discounts page is not displayed",Discounts.contains("Money Saving Discounts"));
		driver.switchTo().defaultContent();	
		
		
	}

}
