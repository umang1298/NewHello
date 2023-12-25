package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class ProviderUpdateUserPage extends TestBase{

	public ProviderUpdateUserPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver=driver;
	}

	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	
	//Page Factory
	By PhoneNumberTextBox = By.xpath("//input[@id='phoneNumber']");
	By UpdateButton = By.id("updateusersubmit");
	By ConfirmPopUpOk = By.xpath("//button[@id='viewallusers']");
	
	public void userupdatesinfo(String info) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(UpdateButton));
		int Number = CommonMethods.getRndNumber();
		String num = String.valueOf(Number);
		System.out.println("Number-->: "+ Number);
		driver.findElement(PhoneNumberTextBox).clear();
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.findElement(PhoneNumberTextBox).sendKeys(num);
		driver.findElement(UpdateButton).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ConfirmPopUpOk));
		WebElement element = driver.findElement(ConfirmPopUpOk);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(ConfirmPopUpOk).click();
		ScenarioContext.setContext(info, num);
		
	}

	public void userstoresthelistofavailableapplications(String availableapplications) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(UpdateButton));
		
		int numberofavailableapplications = driver.findElements(By.xpath("//div[contains(text(),'Authorized Applications:')]//..//div[2]//div//div//span")).size();
		String AllAvailableApplications = "";
		for(int i=1;i<=numberofavailableapplications;i++) {
			
			String xpath = "//div[contains(text(),'Authorized Applications:')]//..//div[2]//div["+i+"]//div//span";
			String ApplicationName = driver.findElement(By.xpath(xpath)).getText();
			
			AllAvailableApplications += ApplicationName;
		}
		
		System.out.println("All Available Applications: "+AllAvailableApplications);
		ScenarioContext.setContext(availableapplications, AllAvailableApplications);
	}

	public void uservalidatestheavailableapplicationsremainsunchanged(String availableapplications) {
		// TODO Auto-generated method stub
		String AvailableApplicationsbeforeupdate = (String) ScenarioContext.getContext(availableapplications);
		System.out.println("Avail1; "+AvailableApplicationsbeforeupdate);
		
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(UpdateButton));
		
		int numberofavailableapplications = driver.findElements(By.xpath("//div[contains(text(),'Authorized Applications:')]//..//div[2]//div//div//span")).size();
		String AllAvailableApplicationsafterupdate = "";
		for(int i=1;i<=numberofavailableapplications;i++) {
			
			String xpath = "//div[contains(text(),'Authorized Applications:')]//..//div[2]//div["+i+"]//div//span";
			String ApplicationName = driver.findElement(By.xpath(xpath)).getText();
			
			AllAvailableApplicationsafterupdate += ApplicationName;
		}
		System.out.println("All Available Applications: "+AllAvailableApplicationsafterupdate);
		Assert.assertTrue(AllAvailableApplicationsafterupdate.equals(AvailableApplicationsbeforeupdate), "Validating the available applications is unchanged after update");
	}
}
