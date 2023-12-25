package com.qa.pages;
import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
public class LoginPage extends TestBase {

	//Page Factory
By PasswordField = By.xpath("//input[@id='password'] | //input[@id='password1']");
By ADFSPasswordField = By.xpath("//input[@id='passwordInput']");
By UserIdField = By.xpath("//input[@id='userid'] | //input[@id='username']");
By ADFSUserIDField = By.xpath("//input[@id='userNameInput']");
By LoginButton = By.xpath("//button[contains(text(),'Log in')] | //a[@id='signOnButton']");
By SignInButton = By.xpath("//span[@id='submitButton']");
By HAPLogo = By.xpath("(//a/img[1])[1]");
By PopUp = By.xpath("//div[@id='datDialogDiv']");
By PopUpClose = By.xpath("//span[@class='dijitDialogCloseIcon']");
By SignInOrgAccountLink = By.xpath("//span[contains(text(),'Sign in with your organizational account')]");
//Constructor class

public LoginPage(WebDriver driver) {
TestBase.driver=driver;	

}

JavascriptExecutor js=(JavascriptExecutor) driver;
//Method to execute the steps
public HAPPortalLandingPage LoginToApp( String usertype,String userID, String pwd) throws InterruptedException {
	String userIdVal = (String) ScenarioContext.getContext(userID);
	WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	By usertypelink=By.xpath("//div[contains(text(),'"+ usertype +"')]");
	wait.until(ExpectedConditions.elementToBeClickable(usertypelink));
	driver.findElement(usertypelink).click();
	wait.until(ExpectedConditions.elementToBeClickable(LoginButton));
	driver.findElement(UserIdField).sendKeys(userIdVal);
	Thread.sleep(3000);
	driver.findElement(PasswordField).sendKeys(pwd);
	driver.findElement(LoginButton).click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(HAPLogo));
	driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
	Thread.sleep(3000);
	int popuppresentornot = driver.findElements(PopUp).size();
	switch(popuppresentornot) {
	case 0 :
		break;
		
	case 1 :
		wait.until(ExpectedConditions.elementToBeClickable(PopUpClose));
		driver.findElement(PopUpClose).click();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.presenceOfElementLocated(HAPLogo));
		wait.until(ExpectedConditions.elementToBeClickable(HAPLogo));
	}
	
	return new HAPPortalLandingPage(driver);
}

public void UserClearsTheUsernameAndPasswordField() {
	// TODO Auto-generated method stub
	WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
	wait.until(ExpectedConditions.elementToBeClickable(LoginButton));
	driver.findElement(UserIdField).clear();
	driver.findElement(PasswordField).clear();
}
public void userclicksonSignInWithOrganisationalAccountLink() throws InterruptedException {
	// TODO Auto-generated method stub
	WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	wait.until(ExpectedConditions.elementToBeClickable(SignInOrgAccountLink));
	driver.findElement(SignInOrgAccountLink).click();
}
public EmployeePortalLandingPage logintoEmployeePortal(String usertype,String userId, String password) {
	// TODO Auto-generated method stub
	String userIdVal = (String) ScenarioContext.getContext(userId);
	WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
	By usertypelink=By.xpath("//div[contains(text(),'"+ usertype +"')]");
	wait.until(ExpectedConditions.elementToBeClickable(usertypelink));
	driver.findElement(usertypelink).click();
	wait.until(ExpectedConditions.elementToBeClickable(SignInButton));
	driver.findElement(ADFSUserIDField).sendKeys(userIdVal);
	driver.findElement(ADFSPasswordField).sendKeys(password);
	driver.findElement(SignInButton).click();
	driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
	wait.until(ExpectedConditions.presenceOfElementLocated(HAPLogo));
	wait.until(ExpectedConditions.elementToBeClickable(HAPLogo));
	return new EmployeePortalLandingPage(driver);
}




	
}
