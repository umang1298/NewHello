package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class ProspectiveRegistrationPage extends TestBase {

	public ProspectiveRegistrationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}
// Page Factory
	By RegisterButton=By.xpath("//button[contains(text(),'Register')]");
	By usernameXpath=By.xpath("//input[@id='userID']");
	By LastnameXpath= By.xpath("//input[@id='lastName']");
	By FirstnameXpath=By.xpath("//input[@id='firstName']");
	By SSNXpath=By.xpath("//input[@id='ssn']");
	By DOBXpath=By.xpath("//input[@id='dateOfBirth']");
	By Email=By.xpath("//input[@id='emailAddress']");
	By ConfirmEmail=By.xpath("//input[@id='confirmemailAddress']");
	 By Password = By.xpath("//input[@name='password']");
	 By ConfirmPassword=By.xpath("//input[@id='verifyPassword']");
	 By question=By.xpath("//input[@id='challengeQuestion']");
	 By answer=By.xpath("//input[@id='challengeAnswer']");
	 By verifyanswer= By.xpath("//input[@id='verifyChallengeAnswer']");
	 By AcceptPrivacy=By.xpath("//input[@id='declaration'][1]//..//span[1]");
	 By PrivacyDecl=By.xpath("//input[@id='Privacydeclaration'][1]//..//span[1]");
	 By Submit=By.xpath("//input[@type='submit']");
	 By OKButton=By.xpath("//button[contains(text(),'OK')]");
	 
	public void userclicksonRegisterbutton() {
		// TODO Auto-generated method stub
		driver.findElement(RegisterButton).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(Submit));
	}
	public void userentersRequiredvalues(String username, String lastname, String firstname, String ssn, String dOB,
			String password) {
		// TODO Auto-generated method stub
		
		driver.findElement(usernameXpath).sendKeys(username);
		driver.findElement(LastnameXpath).sendKeys(lastname);
		driver.findElement(FirstnameXpath).sendKeys(firstname);
		driver.findElement(SSNXpath).sendKeys(ssn);
		WebElement dateof=driver.findElement(DOBXpath);
		dateof.click();
		dateof.clear();
		dateof.sendKeys(dOB);
		driver.findElement(Email).sendKeys("NONE@HAP.ORG");
		driver.findElement(ConfirmEmail).sendKeys("NONE@HAP.ORG");
		driver.findElement(Password).click();
		driver.findElement(Password).sendKeys(password);
		driver.findElement(ConfirmPassword).sendKeys(password);
		driver.findElement(question).sendKeys("Q");
		driver.findElement(answer).sendKeys("A");
		driver.findElement(verifyanswer).sendKeys("A");
		driver.findElement(AcceptPrivacy).click();
		driver.findElement(PrivacyDecl).click();
		driver.findElement(Submit).click();
		verifymembercreatedsuccessful();
		
	}
	public void verifymembercreatedsuccessful() throws NoSuchElementException
	{
//		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
//		wait.until(ExpectedConditions.elementToBeClickable(RegisterButton));
		
		Boolean Congratsmessage=driver.findElements(RegisterButton).size()>0;
		
		if(Congratsmessage==true)
		{
			System.out.println("Congratulations! Your registration is complete.");
			Assert.assertTrue(driver.findElement(RegisterButton).isDisplayed(),"Prospective member is Not Registered");
			driver.close();
		}
	else
	{
		System.out.println("You are already a registered member.");
		WebElement okbutton= driver.findElement(OKButton);
		okbutton.click();
		Assert.fail("You are already a registered member.");
	driver.close();
	}
		
	}
	
	
}
