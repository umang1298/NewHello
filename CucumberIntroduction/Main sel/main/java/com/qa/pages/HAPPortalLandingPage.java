package com.qa.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import org.testng.*;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Then;

public class HAPPortalLandingPage extends TestBase {

	// Page Factory

	By HAPLogo = By.xpath("(//a/img[1])[1]");
	By MaintainanceErr = By.xpath("//div[contains(text(),\"We're sorry! Secure account access is currently un\")]");
	By LogOutBtn = By.xpath("//a[contains(text(),'Log Out')] | //span[contains(text(),'Log Out')]");
	By JadamsLogOut =  By.xpath("//a[contains(text(),'Log Out')]");
	By OpenSettingsBtn = By.xpath("//span[@alt='Open Settings'] | //button[@id='producername']/i");//open settings for member and agent portal
	By UserlogOutBtn = By.xpath("//a[@title='Log Out']");
	By Welcome = By.xpath("//h3[contains(text(),'welcome')]");
	By Doctor=By.xpath("//span[contains(text(),'Doctor')]");
	By CareaffiliatePagelogo = By.xpath("//span[@id='SiteTitleLabel']");
	By ReferralAuthorizationsRecordPage = By.xpath("(//span[contains(text(),'Referral Authorization Records')])[2]");
	By QuickLinksButton = By.xpath("//button[@title='Quick Links']");
	By RelatedLinksButton = By.xpath("//button[@title='Related Links']");
	By ResourcesLink = By.xpath("//span[contains(text(),'Resources')]");
	By ManageusersButton = By.xpath("//a[contains(text(),'Manage Users')]");
	By UpdateProfileButtonForChildUser = By.xpath("//a[contains(text(),'Update Profile')]");
	By UpdateProfileHeadingMember = By.xpath("//div[contains(text(),'Update')]");
	By LogOutButton = By.xpath("//span[contains(text(),'Log Out')]");
	By ProspectiveMemberLogOut = By.xpath("//a[contains(text(),'Logout')]");
	By ProspectiveLogOutPopButton = By.xpath("//input[@value='Log out']");
	By ProscpectiveFrameHandle = By.xpath("//iframe[@id='lpSS_719654549']");
	
	public HAPPortalLandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	public void verifyHAPLogoandLogOut() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(HAPLogo));
		WebElement logo = driver.findElement(HAPLogo);
		Thread.sleep(3000);
		boolean imagePresent = logo.isDisplayed();
		Assert.assertEquals(imagePresent, true);
		driver.findElement(LogOutBtn).isDisplayed();
		Thread.sleep(2000l);

	}
	public void verifyHAPDoctor() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(Doctor)));
		WebElement hapdoctor = driver.findElement(Doctor);
		boolean doctorpresent = hapdoctor.isDisplayed();
		Assert.assertEquals(doctorpresent,true);
		Thread.sleep(5000l);
	}
	public void verifyHAPWelcome() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
			WebElement hapwelcome = driver.findElement(Welcome);
			boolean welcomepresent = hapwelcome.isDisplayed();
			Assert.assertEquals(welcomepresent,true);
			Thread.sleep(5000l);
	}
	public void userClickOnOneOfTheMenusFromTheLandingPage(String menuOptionToPass) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(2000);
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		String MenuOptionXpath = "(//span[contains(text(),'" + menuOptionToPass + "')])[2]";
		WebElement Menu = driver.findElement(By.xpath(MenuOptionXpath));
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(Menu));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", Menu);
		Thread.sleep(5000);
		
	}

	public void userlogout() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(OpenSettingsBtn));
		driver.findElement(OpenSettingsBtn).click();
		Thread.sleep(3000);
		driver.findElement(UserlogOutBtn).click();
	}

	
	public void verifyHAPLogoandLogOutForAgent() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		Thread.sleep(4000l);
		wait.until(ExpectedConditions.presenceOfElementLocated(HAPLogo));
		WebElement logo = driver.findElement(HAPLogo);
		Thread.sleep(4000);
		boolean imagePresent = logo.isDisplayed();
		Assert.assertEquals(imagePresent, true);
		driver.findElement(JadamsLogOut).isDisplayed();
		Thread.sleep(5000l);	
	}

	public void UserClickontheAuthorizationsFromTheLandingpage() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		driver.findElement(By.xpath("(//span[contains(text(),'Authorizations')])[2]")).click();
		Thread.sleep(3000);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.presenceOfElementLocated(CareaffiliatePagelogo));
		String CareAffiliateURL = driver.getCurrentUrl();
		softassert.assertTrue(CareAffiliateURL.contains("CareAffiliate"));
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		softassert.assertAll();
	}
	public void UserClicksonQuickLinks() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(QuickLinksButton));
		WebElement QuickLinks = driver.findElement(QuickLinksButton);
		js.executeScript("arguments[0].click();", QuickLinks);
		Thread.sleep(5000);
		
	}
	public void UserClicksOnReleatedLinks() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(RelatedLinksButton));
		WebElement RelatedLinks = driver.findElement(RelatedLinksButton);
		js.executeScript("arguments[0].click();", RelatedLinks);
		Thread.sleep(5000);
	}
	public void UserClicksonResourcesLink() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ResourcesLink));
		WebElement Resources = driver.findElement(ResourcesLink);
		js.executeScript("arguments[0].click();", Resources);
				
	}
	public void UserClicksontheManagerUsers(String menuoption) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(OpenSettingsBtn));
		Thread.sleep(3000);
		driver.findElement(OpenSettingsBtn).click();
		Thread.sleep(3000);
		String xpath = "//a[contains(text(),'"+menuoption+"')]";
		driver.findElement(By.xpath(xpath)).click();
	}
	public void usernavigatestothepaperlessoption(String paperlessoption) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(UpdateProfileHeadingMember));
		String xpath = "//a[contains(text(),'Paperless')]";
		driver.findElement(By.xpath(xpath)).click();
	}
	public void UserNavigatesToTheUpdateProfile(String updateProfile) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(LogOutButton));
		String xpath = "//a[contains(text(),'Update Profile')]";
		driver.findElement(By.xpath(xpath)).click();
	}
	public void UserLogsOut() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(LogOutButton));
		String xpath = "//span[contains(text(),'Log Out')]";
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void userlogsoutofprospectivemember() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ProspectiveMemberLogOut));
		driver.findElement(ProspectiveMemberLogOut).click();
		Thread.sleep(15000);
		wait.until(ExpectedConditions.elementToBeClickable(ProspectiveLogOutPopButton));
		driver.findElement(ProspectiveLogOutPopButton).click();
		Thread.sleep(15000);
		
		 
	}
	

}
