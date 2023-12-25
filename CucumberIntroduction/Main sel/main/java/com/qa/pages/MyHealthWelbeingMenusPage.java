package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.qa.base.TestBase;
import com.qa.util.Constants;



public class MyHealthWelbeingMenusPage extends TestBase{

	public MyHealthWelbeingMenusPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	SoftAssert softassert = new SoftAssert();
	By myhealthXpath=By.xpath("(//span[contains(text(),'My Health & Wellbeing')])[2]");
	By iStriveLogo=By.xpath("//div[@id='chrome-primary-logo']");
	By istriveLink=By.xpath("//a/span[contains(text(),'iStrive for Better Health')]");
	By AssessmentLink=By.xpath("//a/span[contains(text(),'Take Your Health Assessment')]");
	By myChartLink=By.xpath("//a/span[contains(text(),'Henry Ford MyChart Info')]");
	By henryfordlabel=By.xpath("//div[@class='col-xs-12 paddingrl0 hap-small-headings']");
	By SilverFit=By.xpath("//a/span[contains(text(),'Silver & Fitness')]");
	By ActiveFit=By.xpath("//a/span[contains(text(),'Active & Fit')]");
	By ActiveFitLogin=By.xpath("//a[contains(text(),'Log In')]");
	By SilverFitDashboard=By.xpath("//span[contains(text(),'Dashboard')]");
	By HealthyHapLogo=By.xpath("//div[@id='wrapper']/div/header/div/div[1]/a/img");
	By HealthyLiving=By.xpath("//a/span[contains(text(),'Healthy Living Rewards')]");
	public void validateallthemenus(String usertype) {
		// TODO Auto-generated method stub
		System.out.println("usertype:"+usertype);
		if(usertype.equals("ActiveFit")) 
		{
			validateActiveFit();
		}
		else if(usertype.equals("SilverFit"))
		{
			validateSilverFit();
		}
		else if(usertype.equals("Medicare-Individual"))
		{
			validateHealthyLivingRewards();
			validateHenryFordMyChart();
			
		}
		else if(usertype.equals("Medicare-EGWP"))
		{
			validateHealthyLivingRewards();
			validateHenryFordMyChart();
		}
		else
		{
			validateIstrivepage();
			validateHealthAssessment();
			validateHenryFordMyChart();
		}
		System.out.println("usertype:"+usertype);

	}
	private void validateHealthyLivingRewards() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(HealthyLiving));
		driver.findElement(HealthyLiving).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(HealthyHapLogo));
		Assert.assertTrue("HealthyLivingRewards page is not loaded",driver.findElement(HealthyHapLogo).isDisplayed());
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}
	private void validateActiveFit() {
		// TODO Auto-generated method stub
		Boolean Active=driver.findElements(ActiveFit).size()>0;
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ActiveFit));
		if(Active.equals(true))
		{
			driver.findElement(ActiveFit).click();
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newtabs.get(1));
			wait.until(ExpectedConditions.elementToBeClickable(ActiveFitLogin));
			Assert.assertTrue("Active fit page is not loaded",driver.findElement(ActiveFitLogin).isDisplayed());
			driver.close();
			driver.switchTo().window(newtabs.get(0));
			
		}
		
	}

	private void validateSilverFit() {
		// TODO Auto-generated method stub
		Boolean Silver=driver.findElements(SilverFit).size()>0;
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SilverFit));
		if(Silver.equals(true))
		{
			driver.findElement(SilverFit).click();
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newtabs.get(1));
			wait.until(ExpectedConditions.elementToBeClickable(SilverFitDashboard));
			Assert.assertTrue("Validating Silver fit page is loaded",driver.findElement(SilverFitDashboard).isDisplayed());
			driver.close();
			driver.switchTo().window(newtabs.get(0));
			
		}
		
	}
	private void validateHenryFordMyChart() {
		// TODO Auto-generated method stub
		//driver.findElement(myhealthXpath).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(myChartLink).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		String HenryFordLabel=driver.findElement(henryfordlabel).getText();
		Assert.assertEquals("Validating Hendry Ford Mychart login page is displayed", "Henry Ford MyChart Login", HenryFordLabel);
	}

	private void validateHealthAssessment() {
		// TODO Auto-generated method stub
		//driver.findElement(myhealthXpath).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(AssessmentLink));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(AssessmentLink).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.urlContains("webmdhealth"));
		softassert.assertTrue(driver.findElement(iStriveLogo).isDisplayed(),"Validating Health Assesment page is loaded");
		softassert.assertAll();
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}

	private void validateIstrivepage() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(istriveLink));
		driver.findElement(istriveLink).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.urlContains("webmdhealth"));
		Assert.assertTrue("Validating Istrive page is loaded",driver.findElement(iStriveLogo).isDisplayed());
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
	}

}
