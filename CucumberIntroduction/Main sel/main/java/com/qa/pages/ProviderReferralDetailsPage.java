package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class ProviderReferralDetailsPage extends TestBase {

	public ProviderReferralDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	By ReferralDetailsHeading = By.xpath("//div[contains(text(),'Referral Details')]");
	By RetrivedPatientName = By.xpath("//*[@id='memberinfo']/div/div/div[1]/div[2]/span/span[1]");
	
	public void uservalidatesthereferraldetailsdisplayed(String patientName) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(ReferralDetailsHeading));
		driver.findElement(By.xpath("//*[@id='page-content-wrapper']/div/div/div[2]/div/div[1]/div/div[2]/div"));
		String retrivedpatientname = driver.findElement(RetrivedPatientName).getText();
		String datavalue = (String) ScenarioContext.getContext(patientName);
		System.out.println("Retrivedname:"+datavalue);
		softassert.assertTrue(retrivedpatientname.equals(datavalue),"Verifying Referral Details is displayed for searched PatientName");
		
	}

}

