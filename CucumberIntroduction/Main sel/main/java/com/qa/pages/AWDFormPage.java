package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

import io.restassured.path.xml.XmlPath;

public class AWDFormPage extends TestBase{
	
	public AWDFormPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	//PageFactory
	By AWDFormPage=By.xpath("//div[contains(text(),'Authorization for Automatic Withdrawal Request')]");
	By InstitutionName=By.xpath("//input[@id='nameOfFI']");
	By HolderName=By.xpath("//input[@id='nameOfAH']");
	By RoutingNumber=By.xpath("//input[@id='routingNumber']");
	By AccountNumber=By.xpath("//input[@id='accountNumber']");
	
	By Checkbox=By.xpath("//input[@id='authorize']/following-sibling::span");
	By SuccessMessage=By.xpath("//div[contains(text(),'submitted successfully')]");
	By DateSubmitted=By.xpath("//table[@class='table grey-white-table']/tbody/tr/td[3]");
	By signbutton=By.xpath("//button[@id='submitButton']");
	
	public void clickontheonlineformlink(String menuOptionToPass, String response) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + response)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		String GroupIDXpath= "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].GroupId";
		String GroupIDValue=xt.getString(GroupIDXpath);
		System.out.println("groupid:"+GroupIDValue);
		if((GroupIDValue.equals("10000003"))|(GroupIDValue.equals("10000026"))|(GroupIDValue.equals("10000029")))
			{
			System.out.println("inside if loop");
		String AWDlinkXpath=("//a[contains(text(),'"+ menuOptionToPass +"')]");
		WebElement AWDlink=driver.findElement(By.xpath(AWDlinkXpath));
		AWDlink.click();
		Assert.assertTrue("AWD Form Request Page is not Displayed", driver.findElement(AWDFormPage).isDisplayed());
		}
		
	}

	public void AWDRequestForm() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	//	wait.until(ExpectedConditions.presenceOfElementLocated(InstitutionName));
		driver.findElement(InstitutionName).sendKeys("Test");
		driver.findElement(HolderName).sendKeys("Test");
		driver.findElement(AccountNumber).sendKeys("2424244534");
		driver.findElement(RoutingNumber).sendKeys("7878588778");
		driver.findElement(Checkbox).click();
		Thread.sleep(3000);
		driver.findElement(signbutton).click();
		String success=driver.findElement(SuccessMessage).getText();
		Thread.sleep(2000);
		Assert.assertTrue("Request submitted successfully",success.contains("submitted successfully"));
		
	}
	
}
