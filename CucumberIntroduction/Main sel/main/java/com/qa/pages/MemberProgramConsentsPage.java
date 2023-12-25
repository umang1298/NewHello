package com.qa.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class MemberProgramConsentsPage extends TestBase{

	public MemberProgramConsentsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		TestBase.driver = driver;
	}
	//Page Factory
	By Programconsents=By.xpath("//a[contains(text(),'Program Consents')]");
	By accesshistroyHeading=By.xpath("//div[contains(text(),'Access History')]");
	By ConsentLink1=By.xpath("(//a[@id=\"FHIR Testing/e2d61e0d\"])[1]");
	By ConsentLink2=By.xpath("//*[@id=\"FHIR Testing/e2d61e0d/custrep02\"]");
	By CloseButton=By.xpath("//button[@id='closeButton']");
	By ConsentRemoveTime=By.xpath("//div[@id=\"thirdpartyaccess\"]/div[1]/div[10]/div[1]/div[1]/div[2]/span");
	By ConsentRemoveTime2=By.xpath("//div[@id=\"thirdpartyaccess\"]/div/div[11]/div[1]/div[1]/div[2]/span");
	public void valiadateAccess_history() {
		// TODO Auto-generated method stub
		driver.findElement(Programconsents).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(accesshistroyHeading));
		FirstconsentLink();
		SecondconsentLink();
	}
	public void FirstconsentLink() {
		
		Boolean consentlinkpresent=driver.findElements(ConsentLink1).size()>0;
		if(consentlinkpresent==true)
		{
		driver.findElement(ConsentLink1).click();
		driver.findElement(CloseButton).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(accesshistroyHeading));
		DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		Date date=new Date();
		String date1=dateformat.format(date);
		String Removedate=driver.findElement(ConsentRemoveTime).getText();
		System.out.println("RemoveDate:"+Removedate);
		System.out.println("SystemDate:"+date1);
		Assert.assertTrue("Remove consent is not recorded in AccessHistory", Removedate.contains(date1));
		}
		else {
			System.out.println("Link is not present");
		}
		
	}
	public void SecondconsentLink()
	{
		Boolean consentlinkpresent=driver.findElements(ConsentLink2).size()>0;
		if(consentlinkpresent==true)
		{
		
		driver.findElement(ConsentLink2).click();
		driver.findElement(CloseButton).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(accesshistroyHeading));
		DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		Date date=new Date();
		String date1=dateformat.format(date);
		String Removedate=driver.findElement(ConsentRemoveTime2).getText();
		System.out.println("RemoveDate:"+Removedate);
		System.out.println("SystemDate:"+date1);
		Assert.assertTrue("Remove consent is not recorded in AccessHistory", Removedate.contains(date1));
		}
		else {
			System.out.println("Link is not present");
		}
}

}
