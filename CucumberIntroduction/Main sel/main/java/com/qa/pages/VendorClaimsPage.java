package com.qa.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class VendorClaimsPage extends TestBase{

	public VendorClaimsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver =driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	By HAPMidwestButton = By.xpath("//button[contains(text(),'HAP Empowered Historical')]");
	By TrustedHPButton = By.xpath("//button[contains(text(),'For Trusted HP')]");
	By TrustedHomepageSubmitButton = By.xpath("//button[@id='loginButton']");

	public void userclicksonHAPMidwestbutton() {
		// TODO Auto-generated method stub
		
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(HAPMidwestButton));
		driver.findElement(HAPMidwestButton).click();
		
	}

	public void userclicksonTrustedHPButton() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(TrustedHPButton));
		driver.findElement(TrustedHPButton).click();
		Thread.sleep(3000);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		wait.until(ExpectedConditions.elementToBeClickable(TrustedHomepageSubmitButton));
		String TrustedHPURL = driver.getCurrentUrl();
		softassert.assertTrue(TrustedHPURL.contains("healthx"));
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		softassert.assertAll();
	}

}
