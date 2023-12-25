package com.qa.pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class ApplicationMenusPage extends TestBase {

	public ApplicationMenusPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
//Page Factory
	By FindDoctorXpath=By.xpath("//span[contains(text(),'Find a Doctor')]");
	By SearchXpath=By.xpath("//span[contains(text(),'Search')]");
	By SearchTextXpath=By.xpath("//input[@id='hapSearchInput']");
	By SearchIcorn=By.xpath("//i[@class='icon-magnifying-glass']");
	By MessageXpath=By.xpath("//span[@class='primary-nav-items']//img[@class='small-icons']");
	By SearchResultsText=By.xpath("//div[@class='hap-small-headings xs-hap-small-headings sm-hap-small-headings']");
	By ComposeXpath=By.xpath("//button[@id='composemessagebutton']");
	By ComposeLabelXpath=By.xpath("//h1[contains(text(),'Compose Message')]");
	By EmailIDXpath=By.xpath("//input[@id='Email']");
	By PhoneXpath=By.xpath("//input[@id='Phone']");
	By SubjectXpath=By.xpath("//input[@id='Subject1']");
	By MessageTxtXpath=By.xpath("//textarea[@id='Message']");
	By SubmitButton=By.xpath("//button[@class='xs-width100 advanced-search-btn submit']");
	By VerifiedSubmit=By.xpath("//button[@id='submitverifiedmessage']");
	By ConfirmationMessage=By.xpath("//span[contains(text(),'Your question has been successfully sent.')]");
	By DoctorWelcome=By.xpath("//h1[@id='welcomeMsg']");
	By CloseBtn = By.xpath("//button[contains(text(),'Close')]");
	By Firstinbox=By.xpath("//div[@id='inboxitems']/div/div/span/table/tbody/tr[1]/td[1]/a/span");
	By HapIDinInbox=By.xpath("//span[contains(text(),'HAP ID')]");
	By ReplySubmit=By.xpath("//button[@id='postreplysubmitbtn']");
	
	
	public void validatingApplicationMenus() throws InterruptedException {
		// TODO Auto-generated method stub
		validatingFindADoctor();
		validatingSearch();
		validateMessageView();
		validateMessageCompose();
		
			
		
	}
	private void validateMessageView() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(MessageXpath));
		driver.findElement(MessageXpath).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		Thread.sleep(1000);
		boolean Firstmessage=driver.findElements(Firstinbox).size()>0;
		if(Firstmessage==false)
		{
			System.out.println("No Message in inbox");
		}
		else
		{
			driver.findElement(Firstinbox).click();
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			Assert.assertTrue("Hap ID is not displayed in view Message", driver.findElement(HapIDinInbox).isDisplayed());
			Assert.assertTrue("Reply Submit button is not available", driver.findElement(ReplySubmit).isDisplayed());
		}
	}
	private void validateMessageCompose() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	//	wait.until(ExpectedConditions.elementToBeClickable(ComposeXpath));
		driver.findElement(ComposeXpath).click();
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		String Composepage=driver.findElement(ComposeLabelXpath).getText();
		Thread.sleep(2000);
		System.out.println("compose:"+Composepage);
		Assert.assertTrue("Compose Message Page is not displayed",Composepage.equals("Compose Message"));
		wait.until(ExpectedConditions.presenceOfElementLocated(EmailIDXpath));
		driver.findElement(EmailIDXpath).sendKeys("test@hap.org");
		driver.findElement(PhoneXpath).sendKeys("3123456783");
		driver.findElement(SubjectXpath).sendKeys("Text");
		driver.findElement(MessageTxtXpath).sendKeys("Text");
		driver.findElement(SubmitButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(VerifiedSubmit));
		driver.findElement(VerifiedSubmit).click();
		wait.until(ExpectedConditions.elementToBeClickable(CloseBtn));
		String Confirmation=driver.findElement(ConfirmationMessage).getText();
		Assert.assertTrue("Question has not sent",Confirmation.contains("Your question has been successfully sent."));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		
	}
	private void validatingSearch() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SearchXpath));
		driver.findElement(SearchXpath).click();
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.findElement(SearchTextXpath).sendKeys("Information");
		driver.findElement(SearchIcorn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(SearchResultsText));
		String Search=driver.findElement(SearchResultsText).getText();
		Assert.assertTrue("Search results is not fetched",Search.contains("Search Results"));
		
	}
	private void validatingFindADoctor() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(FindDoctorXpath));
		driver.findElement(FindDoctorXpath).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.urlContains("search"));
		Assert.assertTrue("Doctor page is not displayed properly",driver.findElement(DoctorWelcome).isDisplayed());
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}
	
}
