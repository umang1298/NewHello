package com.qa.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.HttpHostConnectException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class ProviderHCCandHEDISResultsPage extends TestBase{

	public ProviderHCCandHEDISResultsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	
	By HCCandHedisResultspageHeading = By.xpath("//div[contains(text(),'HCC & HEDIS Program')]");
	By ResultsBreadCrums = By.xpath("//span[contains(text(),'HCC & HEDIS Program Results')]");
	By MemberIDValue = By.xpath("//span[@id='memberIDValue']");
	By Attachmentfordropdown = By.xpath("//select[@id='attachmentForID']");
	By AddAttachmentlink = By.xpath("//label[contains(text(),'Add Attachment')]");
	By uploadAttachment = By.xpath("//input[@id='upload-attachment']");
	By HEDISMessageOKButton = By.xpath("//button[contains(text(),'OK')]");
	By HEDISMessage = By.xpath("//div[contains(text(),'Supplemental HEDIS data submission for plan year 2020 is under construction')]");
	By DocConfirmationCheckBox = By.xpath("//input[@id='DocConfirmationBox']");
	By UploadButton = By.xpath("//button[contains(text(),'Upload')]");
	By HCCGapsHeading = By.xpath("//div[contains(text(),'HCC Gaps as of ')]");
	By HEDISGapsHeading = By.xpath("//div[contains(text(),'HEDIS Gaps as of ')]");
	By UploadSuccessfulMessage = By.xpath("//span[contains(text(),'Upload Successful.')]");
	By HEDISTable = By.xpath("//th[contains(text(),'Description')]");
	By PrintIcon = By.xpath("//form[@id='hccForm']//i");
	By MemberIDinPrintPage = By.xpath("//*[@id=\"hcc-details-print\"]/div[2]/div[4]/div/div[1]/div[2]/span[2]");
	
	
	public void userverifiesHCCandHEDISProgramresultisdispalyedfortheMemberID(String memberID) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(HCCandHedisResultspageHeading));
		WebElement BreadCrumResult = driver.findElement(ResultsBreadCrums);
		WebElement PageHeading = driver.findElement(HCCandHedisResultspageHeading);
		softassert.assertTrue(PageHeading.isDisplayed(), "Validating whether Hedis Results page is loaded");
		softassert.assertTrue(BreadCrumResult.isDisplayed(), "Validating whether Hedis Results page is loaded");
		String memberIDValue = (String) ScenarioContext.getContext(memberID);
		String MemberIDinUI =driver.findElement(MemberIDValue).getText();
		softassert.assertTrue(memberIDValue.equals(MemberIDinUI), "Validating whether HCC AND HEDIS program results is loaded for correct Member ID");
		softassert.assertAll();
	}



	public void userselectsmedicalrecordattachmentanduploadsthedocument(String documentfor) {
		// TODO Auto-generated method stub
		Boolean HCCisPresent = driver.findElements(HCCGapsHeading).size() > 0;
		Boolean HEDISisPresent = driver.findElements(HEDISTable).size() > 0;
		Boolean BothHCCandHEDISisnotPresent = driver.findElements(Attachmentfordropdown).size() == 0;
		
		if(BothHCCandHEDISisnotPresent == true) {
			System.out.println("Both HCC and HEDIS Gaps is not present for this member");
		}
		else {
			
		//	Select attachmentfordropdown = new Select(driver.findElement(Attachmentfordropdown));
			String AttachmentFile = System.getProperty("user.home")+"/Downloads/" + "TestPDF.pdf";
			WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
			
		if(HCCisPresent==true && HEDISisPresent == true) {
		if (documentfor.equals("HCC")) {
			Select attachmentfordropdown = new Select(driver.findElement(Attachmentfordropdown));
		attachmentfordropdown.selectByValue("hcc");
		driver.findElement(uploadAttachment).sendKeys(AttachmentFile);
		boolean alertispresent ;
		try 
	    { 
	        driver.switchTo().alert(); 
	        alertispresent = true;
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        alertispresent = false; 
	    }   
		
		if(alertispresent ==true) {
			driver.switchTo().alert().accept();
		}
		else {
		//wait.until(ExpectedConditions.elementToBeClickable(DocConfirmationCheckBox));
		driver.findElement(DocConfirmationCheckBox).click();
		driver.findElement(UploadButton).click();
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(UploadSuccessfulMessage));
		WebElement SuccessMessage = driver.findElement(UploadSuccessfulMessage);
		softassert.assertTrue(SuccessMessage.isDisplayed(), "Validating Upload Success Message");
		}
		}
		else if(documentfor.equals("HEDIS")){
			Select attachmentfordropdown = new Select(driver.findElement(Attachmentfordropdown));
			attachmentfordropdown.selectByValue("hedis");
			
			wait.until(ExpectedConditions.elementToBeClickable(HEDISMessageOKButton));
			WebElement HedisMessage = driver.findElement(HEDISMessage);
			softassert.assertTrue(HedisMessage.isDisplayed(), "Verifying Message is dispalyed when HEDIS is selected in the Dropdown for Attachment");
			driver.findElement(HEDISMessageOKButton).click();
		}	
		}
		else if(HCCisPresent == true && HEDISisPresent ==false) {
			if (documentfor.equals("HCC")) {
				Select attachmentfordropdown = new Select(driver.findElement(Attachmentfordropdown));
				attachmentfordropdown.selectByValue("hcc");
				driver.findElement(uploadAttachment).sendKeys(AttachmentFile);
				boolean alertispresent ;
				try 
			    { 
			        driver.switchTo().alert(); 
			        alertispresent = true;
			    }   // try 
			    catch (NoAlertPresentException Ex) 
			    { 
			        alertispresent = false; 
			    }   
				
				if(alertispresent ==true) {
					driver.switchTo().alert().accept();
				}
				else {
				//wait.until(ExpectedConditions.elementToBeClickable(DocConfirmationCheckBox));
				driver.findElement(DocConfirmationCheckBox).click();
				driver.findElement(UploadButton).click();
				driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
				wait.until(ExpectedConditions.visibilityOfElementLocated(UploadSuccessfulMessage));
				WebElement SuccessMessage = driver.findElement(UploadSuccessfulMessage);
				softassert.assertTrue(SuccessMessage.isDisplayed(), "Validating Upload Success Message");
				}
				}
				else if(documentfor.equals("HEDIS")){
					System.out.println("HEDIS GAPS is Not available for this Member");
				}
		}
		else if(HCCisPresent == false && HEDISisPresent== true) {
			if (documentfor.equals("HCC")) {
				System.out.println("HCC Gaps is  not available for this Member");
				}
				else if(documentfor.equals("HEDIS")){
					Select attachmentfordropdown = new Select(driver.findElement(Attachmentfordropdown));
					attachmentfordropdown.selectByValue("hedis");
					
					wait.until(ExpectedConditions.elementToBeClickable(HEDISMessageOKButton));
					WebElement HedisMessage = driver.findElement(HEDISMessage);
					softassert.assertTrue(HedisMessage.isDisplayed(), "Verifying Message is dispalyed when HEDIS is selected in the Dropdown for Attachment");
					driver.findElement(HEDISMessageOKButton).click();
				}
		}
		else if(HCCisPresent ==false && HEDISisPresent == false) {
			System.out.println("Both HCC AND HEDIS Gaps is not availble for this Member");
		}
		}
		softassert.assertAll();
	}



	public void userverifiesprintfunction(String memberID) {
		// TODO Auto-generated method stub
		String memberIDValue = (String) ScenarioContext.getContext(memberID);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(PrintIcon));
		driver.findElement(PrintIcon).click();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.presenceOfElementLocated(MemberIDinPrintPage));
		String MembIDinprintpage = driver.findElement(MemberIDinPrintPage).getText();
		softassert.assertTrue(MembIDinprintpage.equals(memberIDValue), "Verifying MemberID in  HCC and HEDIS Print Page");
		softassert.assertAll();
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	
	}
	
	
	
	
	
}
