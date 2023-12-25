package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 

import com.qa.base.TestBase;
import com.qa.util.Constants;

import io.restassured.path.xml.XmlPath;

public class MemberPortalPayMyBillPage extends TestBase{

	public MemberPortalPayMyBillPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		TestBase.driver = driver;
	}
	
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//PageFactory
	
	By Paymybill=By.xpath("//div[contains(text(),'Pay My Bill')]");
	By InvoiceSettings=By.xpath("//a[@id='setpaperlessbtn']");
	 By PaperinvoiceRadio=By.xpath("//input[@id='yesauto']//..//label[1]");
	 By NoPaperinvoiceRadio=By.xpath("//input[@id='noauto']//..//label[1]");
	 By Paperinvoice=By.xpath("//input[@id='yesauto']");
	 By NoPaperinvoice=By.xpath("//input[@id='noauto']");
	 By Savebutton=By.xpath("//button[@id='allow-access-btn']");
	 By AutopayPreference=By.xpath("//a[@id='autoPay-submit']");
	 By schedulepayement=By.xpath("//span[contains(text(),'Schedule Payment')][2]");
	 By haplogo=By.xpath("//div[@id='header_body']/img");
	 By Paymentsettings=By.xpath("//a[@id='submit-btn-manage']");
	 By Invoice=By.xpath("//span[@id='invoiceNo0']");
	 By PayInvoiceButton=By.xpath("//button[@id='submit-btn-final']");
	 By NoInvoices=By.xpath("//div[contains(text(),'You do not have any invoices that need to be paid ')]");
	 By PaymentsPage=By.xpath("//h1[contains(text(),'Payments')]");
	
	 By donotsetupAutoRadio=By.xpath("//input[@id='noauto']//..//label[1]");
	 By setupAutoRadio=By.xpath("//input[@id='yesauto']//..//label[1]");
	 By donotsetupAuto=By.xpath("//input[@id='noauto']");
	 By setupAuto=By.xpath("//input[@id='yesauto']");
	 
	 By ManageAlert=By.xpath("////div[@id='success-modal-autopay']/div/div/div[2]/div[1]/div/div[1]/div");
	 By ManageInvoiceAlert=By.xpath("//div[@id='success-modal-manageInvoice']/div/div/div[2]/div[1]/div/div[1]/div"); 
	 By Backbutton=By.xpath("//button[contains(text(),'Back')]");
	 By closeButton=By.xpath("//button[contains(text(),'Close')][1]");
	 By AlreadyAutomessage=By.xpath("//div/div[3]/div/div[2]/div/div[1]");
	
	 By Continue=By.xpath("//button[contains(text(),'Continue')]");
	 By Haplogo=By.xpath("//div[@id='ctl00_mainContent']/div[1]/img");
	 By AddPaymentLink=By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ctl12']/div[2]/input[1]");
	 By AddPaymentLabel=By.xpath("//h1[contains(text(),'Add Payment Method')]");
	 By BillPaySite=By.xpath("//span[contains(text(),'Bill Pay Site')]");
	 By NoThanksButton = By.xpath("//input[@value='No Thanks']");
	 By DoneButton = By.xpath("//input[@value='Done']");
 
 
	public void clickonpaymybillLinks(String menuOptionToPass) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		String MenuOptionXpath = "//a[contains(text(),'" + menuOptionToPass +"')]";
		WebElement Menu = driver.findElement(By.xpath(MenuOptionXpath));
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(Menu));
		//Thread.sleep(3000);
		js.executeScript("arguments[0].click();", Menu);
	
	}
	public void validatesAutoPayOption() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		
		driver.findElement(AutopayPreference).click();

		Thread.sleep(10000);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.presenceOfElementLocated(haplogo));
		Assert.assertTrue("Alacriti Account summary page is not displayed",driver.findElement(haplogo).isDisplayed());
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		
	}
	public void validatePaymentMethod() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		driver.findElement(Paymentsettings).click();
		Thread.sleep(10000);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.presenceOfElementLocated(haplogo));
		Assert.assertTrue("Alacriti Account summary page is not displayed",driver.findElement(haplogo).isDisplayed());
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}
//	public void validateManageInvoicePreferences() {
//		// TODO Auto-generated method stub
//		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
////		wait.until(ExpectedConditions.presenceOfElementLocated(Paperinvoice));
//		driver.findElement(InvoiceSettings).click();
//		if(driver.findElement(Paperinvoice).isSelected())
//		{
//			driver.findElement(NoPaperinvoiceRadio).click();
//			driver.findElement(Savebutton).click();
//			wait.until(ExpectedConditions.elementToBeClickable(closeButton));
//			Assert.assertTrue("Invoice preferences is not updated",driver.findElement(ManageInvoiceAlert).isDisplayed());
//			WebElement Close=driver.findElement(closeButton);
//			js.executeScript("arguments[0].click();", Close);
//		}
//		else
//		{
//			driver.findElement(PaperinvoiceRadio).click();
//			driver.findElement(Savebutton).click();
//			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
//			wait.until(ExpectedConditions.elementToBeClickable(closeButton));
//			Assert.assertTrue("Invoice preferences is not updated",driver.findElement(ManageInvoiceAlert).isDisplayed());
//			WebElement Close=driver.findElement(closeButton);
//			js.executeScript("arguments[0].click();", Close);
//		}
//	}
	
	
	
	public void validatePayInvoice(String restResponse) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + restResponse)));
		System.out.println("Content->"+contents);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		Boolean Invoicepresent=driver.findElements(Invoice).size() > 0;
		if(Invoicepresent==true)
		{
			String invoicenoUI=driver.findElement(Invoice).getText();
			System.out.println("invoicenoUI:"+invoicenoUI);
			Assert.assertTrue("Invoice number is not available in Restservice", contents.contains(invoicenoUI));
			driver.findElement(By.xpath("//input[@id=\"0\"]//..//span[1]")).click();
			driver.findElement(PayInvoiceButton).click();
			Thread.sleep(10000);
			ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newtabs.get(1));
			wait.until(ExpectedConditions.presenceOfElementLocated(haplogo));
			Assert.assertTrue("Alacriti Payment page is not displayed",driver.findElement(PaymentsPage).isDisplayed());
			driver.close();
			driver.switchTo().window(newtabs.get(0));
		}
		else
		{
			Assert.assertTrue("Invoices is available",driver.findElement(NoInvoices).isDisplayed());
			
	}
	
	
}
}


