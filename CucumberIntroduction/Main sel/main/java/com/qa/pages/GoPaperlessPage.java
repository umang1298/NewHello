package com.qa.pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class GoPaperlessPage  extends TestBase{

	public GoPaperlessPage(WebDriver driver) {

		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
}
	By OpenSettingsBtn = By.xpath("//span[@alt='Open Settings']");
	By Gopaperless = By.xpath("//a[contains(text(),'Go paperless')]");
	By Edit=By.xpath("//a[@id='refresh']");
	By textfield=By.xpath("//input[@id='email']");
	By confirmation=By.xpath("//*[@id='error-message-modal']/div/div/div[2]/div[1]/div/p");
	
	By okbutton=By.xpath("//button[contains(text(),'OK')]");
	By EOBCheck=By.xpath("//form[@id='goGreenForm']/div[4]/div[1]/div[1]");
	By Submitbutton=By.xpath("//input[@id='submitButton1']");
	By Submitbutton2=By.xpath("//input[@id='submitButton2']");
	By EOBemailcheck=By.xpath("(//input[@id='checkbox1'])[1]//..//span[1]");
	By EOBpapercheck=By.xpath("(//input[@id='checkbox2'])[1]//..//span[1]");
	By EOBemail=By.xpath("(//input[@id='checkbox1'])[1]");
	By EOBpaper=By.xpath("(//input[@id='checkbox2'])[1]");
	By Gopaperlessemail=By.xpath("(//input[@id='gopaperless'])[1]");
	By Gopaperlesscheck=By.xpath("(//input[@id='gopaperless'])[1]//..//span[1]");
	By EOBLabelxpath=By.xpath("//div[contains(text(),'Explanation of Benefits (EOBs) :')]");
	
	SoftAssert softAssert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public void clickonthemenu(String menuOptionToPass)
	{
		driver.findElement(OpenSettingsBtn).click();
	driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
	String MenuOptionXpath = "(//a[contains(text(),'" + menuOptionToPass + "')])";
	driver.findElement(By.xpath(MenuOptionXpath)).click();

	}
	public void uservalidatescontactInformation() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		String textmail=driver.findElement(textfield).getAttribute("value"); 
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		System.out.println("Text:"+textmail);
		driver.findElement(Edit).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(textfield).clear();
		driver.findElement(textfield).sendKeys("test4@hap.org");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(Submitbutton).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(okbutton));
		String confirmationmessage=driver.findElement(confirmation).getText();
		System.out.println("confirmation:"+confirmationmessage);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(okbutton).click();
		Assert.assertTrue("Your email has NOT updated.",confirmationmessage.contains("Your email has been updated"));
		
		
		
	}
	public void uservalidatespaperless() {
		// TODO Auto-generated method stub
		Boolean isPresent = driver.findElements(EOBCheck).size() > 0;
		
		if(isPresent==true)
		{
			if(driver.findElement(EOBemail).isSelected())
			{
				System.out.println("email checked");
				driver.findElement(EOBpapercheck).click();
				driver.findElement(Submitbutton2).click();
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait.until(ExpectedConditions.elementToBeClickable(okbutton));
				String confirmationmessage=driver.findElement(confirmation).getText();

				System.out.println("EOB paper confirmation:"+confirmationmessage);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				driver.findElement(okbutton).click();
				Assert.assertTrue("EOB preference is not updated.",confirmationmessage.contains("Your preference has been confirmed."));
				
			}
			else
				
			{
				System.out.println("paperless checked");
				driver.findElement(EOBemailcheck).click();
				driver.findElement(Submitbutton2).click();
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait.until(ExpectedConditions.elementToBeClickable(okbutton));
				String confirmationmessage=driver.findElement(confirmation).getText();

				System.out.println("EOB email confirmation:"+confirmationmessage);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				driver.findElement(okbutton).click();
				Assert.assertTrue("EOB preference is not updated.",confirmationmessage.contains("Your preference has been confirmed."));
				
			}
		}
			else
			{
				driver.findElement(Gopaperlesscheck).click();
				driver.findElement(Submitbutton2).click();
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait.until(ExpectedConditions.elementToBeClickable(okbutton));
				String confirmationmessage=driver.findElement(confirmation).getText();

				System.out.println("gopaperless email Confirmation:"+confirmationmessage);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				driver.findElement(okbutton).click();
				Assert.assertTrue("EOB preference is not updated.",confirmationmessage.contains("Your preference has been confirmed."));
				
			}
		
	}
	public void usersetsthevalues(String email, String eOBcheck2, String paperlesscheck) throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		Thread.sleep(4000);
		String emailtext = driver.findElement(textfield).getAttribute("value"); 
Boolean isPresent = driver.findElements(EOBLabelxpath).size() > 0;
		
		if(isPresent==true)
		{
		Boolean EOBcheck=driver.findElement(EOBpaper).isSelected();
		String EOBCheckbox=Boolean.toString(EOBcheck);
		Boolean Gopaperlesscheck=driver.findElement(Gopaperlessemail).isSelected();
		String Gopaperlessemail=Boolean.toString(Gopaperlesscheck);
		ScenarioContext.setContext(email, emailtext);
		ScenarioContext.setContext(eOBcheck2, EOBCheckbox);
		ScenarioContext.setContext(paperlesscheck, Gopaperlessemail);
		driver.close();
		}
		else
		{
			Boolean Gopaperlesscheck=driver.findElement(Gopaperlessemail).isSelected();
			String Gopaperlessemail=Boolean.toString(Gopaperlesscheck);
			ScenarioContext.setContext(email, emailtext);
			ScenarioContext.setContext(paperlesscheck, Gopaperlessemail);
			driver.close();
		}
	}
	public void uservalidatestheupdatedinformation(String email, String eOBcheck2, String paperlesscheck) throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
Boolean isPresent = driver.findElements(EOBLabelxpath).size() > 0;
		
		if(isPresent==true)
		{
		String Email = (String) ScenarioContext.getContext(email);
		String EOBpaperch = (String) ScenarioContext.getContext(eOBcheck2);
		String gopaperlessemail = (String) ScenarioContext.getContext(paperlesscheck);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		Thread.sleep(4000);
		String emailtext=driver.findElement(textfield).getAttribute("value"); 
		
		Boolean EOBpapcheck = driver.findElement(EOBpaper).isSelected();
		System.out.println("EOBCheck:"+EOBpapcheck);
		String EOBpaperCheckbox=Boolean.toString(EOBpapcheck);
		Boolean Gopaperlesscheck=driver.findElement(Gopaperlessemail).isSelected();
		String Gopaperlesskbox=Boolean.toString(Gopaperlesscheck);
		Assert.assertTrue(Email.equals(emailtext));
		Assert.assertTrue("EOB check not updated properly", EOBpaperch.equals(EOBpaperCheckbox));
		Assert.assertEquals(gopaperlessemail, Gopaperlesskbox);
		}
		else
		{
			String Email = (String) ScenarioContext.getContext(email);
			String gopaperlessemail = (String) ScenarioContext.getContext(paperlesscheck);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			Thread.sleep(4000);
			String emailtext=driver.findElement(textfield).getAttribute("value"); 
			
			Boolean Gopaperlesscheck=driver.findElement(Gopaperlessemail).isSelected();
			String Gopaperlesskbox=Boolean.toString(Gopaperlesscheck);
			Assert.assertTrue(Email.equals(emailtext));
			Assert.assertEquals(gopaperlessemail, Gopaperlesskbox);
		}
		
		
	}
	
}
