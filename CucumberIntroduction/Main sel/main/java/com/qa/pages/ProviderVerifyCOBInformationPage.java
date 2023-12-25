package com.qa.pages;

import java.util.concurrent.TimeUnit;

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

public class ProviderVerifyCOBInformationPage extends TestBase {

	public ProviderVerifyCOBInformationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	By SubmitButton = By.xpath("//button[contains(text(),'Submit')]");
	By VerifyandConfirmCOBInformationPageHeading = By.xpath("//div[contains(text(),'Verfiy & Confirm COB Information')]");
	By COBInformationConfirmationPageHeading = By.xpath("//div[contains(text(),'COB Information Confirmation')]");
	By CloseButton = By.xpath("//button[contains(text(),'Close')]");
	By AddressValue = By.xpath("//span[contains(text(),'Address:')]//..//span[2]//span");
	By ConfirmationNumber = By.xpath("//div[contains(text(),'confirmation number is')]//span//span");
	
	
	public void UserVerifyCOBInformationandValidatestheInformation(String cOBAvailable) throws InterruptedException {
		// TODO Auto-generated method stub
		String COBAvailable = (String) ScenarioContext.getContext(cOBAvailable);
		if (COBAvailable.equals("COB Not Available")) {
			//do Nothing
		}
		else {
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		wait.until(ExpectedConditions.presenceOfElementLocated(VerifyandConfirmCOBInformationPageHeading));
//		WebElement Submit = driver.findElement(SubmitButton);
		String Address = driver.findElement(AddressValue).getText();
		softassert.assertTrue(Address.equals("TamilNadu"), "Validating Address is Displayed correctly as entered");
		js.executeScript("arguments[0].click();", driver.findElement(SubmitButton));
		wait.until(ExpectedConditions.presenceOfElementLocated(COBInformationConfirmationPageHeading));
		wait.until(ExpectedConditions.elementToBeClickable(CloseButton));
		WebElement confirmationNum = driver.findElement(ConfirmationNumber);
		softassert.assertTrue(confirmationNum.isDisplayed(), "Validating Conformation Number is Generated");
		driver.findElement(CloseButton).click();
		softassert.assertAll();
		}
	}
	

}
