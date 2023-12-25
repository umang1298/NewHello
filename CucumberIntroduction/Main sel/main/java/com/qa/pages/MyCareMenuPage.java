package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import com.qa.base.TestBase;
import com.qa.util.Constants;

public class MyCareMenuPage extends TestBase{

	public MyCareMenuPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	SoftAssert softassert = new SoftAssert();
	By YourRights=By.xpath("//div[contains(text(),'Your Rights & Responsibilities')]");
	By SpecialityDrugsHaplogo=By.xpath("//div[1]/header/div[2]/div[1]/a/img");
	By SpecialityParamaceuticalsLink=By.xpath("//a/span[contains(text(),'Specialty Pharmaceuticals')]");
	By CovereddrugListLink=By.xpath("//a/span[contains(text(),'Covered Drug Lists')]");
	By CareManagementLink=By.xpath("//a/span[contains(text(),'Care Management')]");
	By YourRightsLink=By.xpath("//a/span[contains(text(),'Your Rights and Responsibilities')]");
	By TeleHealthLink=By.xpath("//a/span[contains(text(),'Telehealth (virtual doctor visit)')]");
	By SpecialityHaplogo=By.xpath("//div[@class='logo']//a//img");
	By Appointmentschedulinglink=By.xpath("//a/span[contains(text(),'Appointment Scheduling')]");
	By AppointmentScheduling=By.xpath("//div[contains(text(),'Appointment Scheduling')]");
	By knowyourPCP=By.xpath("(//span[contains(text(),'Getting to Know Your PCP')])[2]");
	By AccessingPCP=By.xpath("//a/span[contains(text(),'Accessing Care: You and Your PCP')]");
	By AccessingframeXpath=By.xpath("//div[contains(text(),'Accessing Care')]");
	By Frame=By.xpath("//iframe[@id='document_iframe']");
	By gettingknoXpath=By.xpath("//div[contains(text(),'Getting to Know Your PCP')]");
	By Haplogo=By.xpath("//a[@id='ember9']/img");
	By HealthsparqPage=By.xpath("//button[@id='ember12']");
	By mycareXpath=By.xpath("(//span[contains(text(),'My Care')])[2]");
	By HealthcareLinkXpath=By.xpath("(//span[contains(text(),'Health Care Cost Estimator')])[2]");
	By TermsofUseXpath=By.xpath("//ins[contains(text(),'TERMS OF USE')]");
	By OtherCoverageXpath=By.xpath("(//span[contains(text(),'Coordinating with Other Coverage')])[2]");
	By COBXpath=By.xpath("//div[contains(text(),'Coordination of Benefits')]");

	JavascriptExecutor js=(JavascriptExecutor) driver;
	public void validateStaticLinks(String usertype) throws InterruptedException {
		// TODO Auto-generated method stub
		if(usertype.equals("Medicare-Individual"))
		{
			ValidateCoordinatingwithOtherCoverage();
			ValidateGettingtoKnowYourPCP();
			ValidateAppointmentScheduling();
		}
		else if(usertype.equals("Medicare-EGWP"))
		{
			ValidateCoordinatingwithOtherCoverage();
			ValidateGettingtoKnowYourPCP();
			ValidateAppointmentScheduling();
		}
		else
		{
					
		ValidateCoordinatingwithOtherCoverage();
		ValidateGettingtoKnowYourPCP();
		ValidateAccessingCareYouandYourPCP();
		ValidateAppointmentScheduling();
		//ValidateSpecialtyPharmaceuticals();
		//ValidateCareManagement();
		//ValidateCoveredDrugLists ();
		ValidateYourRightsandResponsibilities();
		ValidateTelehealth();
		ValidateHealthcareCostEstimator();
		}
	}

	private void ValidateTelehealth() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(mycareXpath));
		driver.findElement(mycareXpath).click();
		wait.until(ExpectedConditions.elementToBeClickable(TeleHealthLink));
		driver.findElement(TeleHealthLink).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.presenceOfElementLocated(SpecialityDrugsHaplogo));
		softassert.assertTrue(driver.findElement(SpecialityDrugsHaplogo).isDisplayed(),"TeleHealth page is not displyed");
		softassert.assertAll();
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}

	private void ValidateYourRightsandResponsibilities() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(mycareXpath));
		driver.findElement(mycareXpath).click();
		wait.until(ExpectedConditions.elementToBeClickable(YourRightsLink));
		driver.findElement(YourRightsLink).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("document_iframe")));
//		WebElement iFrame= driver. findElement(Frame);
//		driver.switchTo().frame(iFrame);
		Thread.sleep(4000);
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		String Appointmentscheduling=driver.findElement(YourRights).getText();
		Assert.assertEquals("Your Rights and Responsibilities page is not displyed",Appointmentscheduling,"Your Rights & Responsibilities");
		driver.switchTo().defaultContent();	
	}

	private void ValidateCoveredDrugLists() throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(CovereddrugListLink));
		driver.findElement(CovereddrugListLink).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(SpecialityDrugsHaplogo));
		softassert.assertTrue(driver.findElement(SpecialityDrugsHaplogo).isDisplayed(),"Covered Drug Lists page is not displayed");
		softassert.assertAll();
		driver.navigate().back();
		wait.until(ExpectedConditions.elementToBeClickable(mycareXpath));
	}

	private void ValidateCareManagement() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(CareManagementLink));
		driver.findElement(CareManagementLink).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(SpecialityDrugsHaplogo));
		softassert.assertTrue(driver.findElement(SpecialityDrugsHaplogo).isDisplayed(),"Care management page is not displayed");
		softassert.assertAll();
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}

	private void ValidateSpecialtyPharmaceuticals() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(mycareXpath));
		driver.findElement(mycareXpath).click();
		wait.until(ExpectedConditions.elementToBeClickable(SpecialityParamaceuticalsLink));
		driver.findElement(SpecialityParamaceuticalsLink).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.presenceOfElementLocated(SpecialityDrugsHaplogo));
		Thread.sleep(3000);
		softassert.assertTrue(driver.findElement(SpecialityDrugsHaplogo).isDisplayed(),"Speciality Pharmaceuticals Page is not displayed");
		softassert.assertAll();
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}

	private void ValidateAppointmentScheduling() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(mycareXpath));
		driver.findElement(mycareXpath).click();
		wait.until(ExpectedConditions.elementToBeClickable(Appointmentschedulinglink));
		driver.findElement(Appointmentschedulinglink).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("document_iframe")));
//		WebElement iFrame= driver. findElement(Frame);
//		driver.switchTo().frame(iFrame);
		Thread.sleep(4000);
		String Appointmentscheduling=driver.findElement(AppointmentScheduling).getText();
		Assert.assertEquals("AppointmentScheduling page is not displyed",Appointmentscheduling,"Appointment Scheduling");
		driver.switchTo().defaultContent();	
	}

	private void ValidateAccessingCareYouandYourPCP() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(mycareXpath));
		driver.findElement(mycareXpath).click();
		wait.until(ExpectedConditions.elementToBeClickable(AccessingPCP));
		driver.findElement(AccessingPCP).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("document_iframe")));
//		WebElement iFrame= driver. findElement(Frame);
//		driver.switchTo().frame(iFrame);
		Thread.sleep(4000);
		String AccessingyourPCP=driver.findElement(AccessingframeXpath).getText();
		Assert.assertEquals("Accessing care page is not displayed",AccessingyourPCP,"Accessing Care");
		driver.switchTo().defaultContent();
	}

	private void ValidateGettingtoKnowYourPCP() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(mycareXpath));
		driver.findElement(mycareXpath).click();
		wait.until(ExpectedConditions.elementToBeClickable(knowyourPCP));
		driver.findElement(knowyourPCP).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("document_iframe")));
//		WebElement iFrame= driver. findElement(Frame);
//		driver.switchTo().frame(iFrame);
		Thread.sleep(4000);
		String yourPCP=driver.findElement(gettingknoXpath).getText();
		Assert.assertEquals("Getting to know your PCP page is not displayed",yourPCP,"Getting to Know Your PCP");
		driver.switchTo().defaultContent();
	}

	private void ValidateCoordinatingwithOtherCoverage() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(mycareXpath));
		driver.findElement(mycareXpath).click();
		wait.until(ExpectedConditions.elementToBeClickable(OtherCoverageXpath));
		driver.findElement(OtherCoverageXpath).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("document_iframe")));
//		WebElement iFrame= driver.findElement(Frame);
//		driver.switchTo().frame(iFrame);
		Thread.sleep(4000);
		String COB=driver.findElement(COBXpath).getText();
		Assert.assertEquals("Coordinating with other coverage page is not displayed",COB,"Coordination of Benefits (COB)");
		driver.switchTo().defaultContent();
	}

	private void ValidateHealthcareCostEstimator() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(HealthcareLinkXpath));
		driver.findElement(HealthcareLinkXpath).click();
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(HealthsparqPage));
		Thread.sleep(5000);
		Assert.assertTrue("Health care Cost Estimator page is not displayed",driver.findElement(HealthsparqPage).isDisplayed());
		driver.navigate().back();
		wait.until(ExpectedConditions.elementToBeClickable(mycareXpath));
		}

	public void ValidatemenuLinks(String menuoptiontopass) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		String MenuOptionXpath = "//a/span[contains(text(),'"+menuoptiontopass+")]";
		WebElement Menu = driver.findElement(By.xpath(MenuOptionXpath));
	//	WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(Menu));
		js.executeScript("arguments[0].click();", Menu);
		WebElement iFrame= driver. findElement(Frame);
		driver.switchTo().frame(iFrame);
		wait.until(ExpectedConditions.elementToBeClickable(AppointmentScheduling));
		String Appointmentscheduling=driver.findElement(AppointmentScheduling).getText();
		Assert.assertEquals("AppointmentScheduling page is not displyed",Appointmentscheduling,"Appointment Scheduling");
		driver.switchTo().defaultContent();	
	}

}
