package com.qa.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class ReferralAndAuthorizations extends TestBase{

	public ReferralAndAuthorizations(WebDriver driver) {

		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	By AgeRestriction=By.xpath("//div/div/div/div/div[2]/div[2]/div/span");
	By ReferralDetailXpath=By.xpath("//h1[@class='inherittext']");
	By MemberDropdown=By.id("member-picker");
	By FromDateofService= By.xpath("//input[@id='frompicker']");
	By Searchbutton=By.xpath("//button[@id='ref-search-button']");
	By Membername=By.xpath("//div/div/div/div/div[1]/div[2]/div/div[2]/label");
	By ReferralnumXpath=By.xpath("//tbody/tr/td[2]/a/span");
	public void clickonreferralsearchforgivenperiod(String Memberfirst, String Date) throws InterruptedException {
		// TODO Auto-generated method stub
Boolean isPresent = driver.findElements(MemberDropdown).size() > 0;
		
		if(isPresent==true)
		{
			
			
			try {
				Select drop1=new Select(driver.findElement(MemberDropdown));
//				drop1.selectByIndex(5);
//				String membername=drop1.getOptions().get(5).getText();
//				String Age=driver.findElement(AgeRestriction).getText();
//				Assert.asserttrue(Age.contains(" yourself and dependents under the age of 1"));
				drop1.selectByIndex(7);
				String membername=drop1.getOptions().get(7).getText();
			
				Thread.sleep(2000);
				System.out.println("name:"+membername);
				driver.findElement(FromDateofService).clear();
				driver.findElement(FromDateofService).sendKeys(Date);
				Thread.sleep(2000);
				WebElement SearchButn = driver.findElement(Searchbutton);
				SearchButn.click();
				
				Boolean linkpresent=driver.findElement(ReferralnumXpath).isDisplayed();
				if(linkpresent==true)
				{
					System.out.println("ifloop");
					driver.findElement(ReferralnumXpath).click();
					driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
					softassert.assertEquals(driver.findElement(ReferralDetailXpath).getText(),"Referral Details");
					softassert.assertAll();
				}
				else
				{
					System.out.println("elseloop");
					Assert.fail("There are no Referrals found for the specified dates.. Please try with Different Dates");
				}
			}
			
			
			catch (org.openqa.selenium.StaleElementReferenceException ex)
			{
				
			}
				}
			
		
		else
		{
			
			System.out.println("Member type is without dropdown");
//			String membername=driver.findElement(Membername).getText();
//			System.out.println("Member name " +membername);
			WebElement Dateofservice=driver.findElement(FromDateofService);
			Dateofservice.clear();
			Dateofservice.sendKeys(Date);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			WebElement SearchButn = driver.findElement(Searchbutton);
			js.executeScript("arguments[0].click();", SearchButn);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			Boolean linkpresent=driver.findElements(ReferralnumXpath).size() >0;
			if(linkpresent==true)
			{
				System.out.println("ifloop");
				driver.findElement(ReferralnumXpath).click();
				driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
				softassert.assertEquals(driver.findElement(ReferralDetailXpath).getText(),"Referral Details");
				softassert.assertAll();
			}
			else
			{
				System.out.println("elseloop");
				Assert.fail("There are no Referrals found for the specified dates.. Please try with Different Dates");
			}
		}

	}
		
}
