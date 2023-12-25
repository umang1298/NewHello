package com.qa.pages;

import java.util.List;
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

public class ProviderListofClaimsPage extends TestBase{

	public ProviderListofClaimsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	
	By ErrorMessage = By.xpath("//div[contains(text(),'Your search criteria has returned more than 500 rows.')]");
	By NumberofResults = By.xpath("//*[@id='page-content-wrapper']/div/div/div[2]/div[2]/div/div/div/div/b/span");
	By Heading = By.xpath("//*[@id='page-content-wrapper']/div/div/div[2]/div[2]/div/div/div/div/b");
	By ClaimList = By.xpath("//*[@id='summaryTable']/tbody/tr");
	By FirstClaimID = By.xpath("//*[@id='summaryTable']/tbody/tr[1]/td[2]/a/span");
	By ErrorMessageOK = By.xpath("//button[contains(text(),'OK')]");
	By FirstPatientNumber = By.xpath("//*[@id='summaryTable']/tbody/tr[1]/td[3]/span");
	By FirstMemberId = By.xpath("//*[@id='summaryTable']/tbody/tr[1]/td[8]/span");
	
	public void uservalidateserrormessage(int int1) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(Heading, " Results Found"));
		
		String Count = driver.findElement(NumberofResults).getText();
		
		int ResultsCount = Integer.valueOf(Count);
		if(int1 == ResultsCount) {
			wait.until(ExpectedConditions.presenceOfElementLocated(ErrorMessageOK));
			wait.until(ExpectedConditions.elementToBeClickable(ErrorMessageOK));
			WebElement errormsg = driver.findElement(ErrorMessage);
			
			softassert.assertTrue(errormsg.isDisplayed(),"Validating Error Message for More that 500 rows of Claim results");
			softassert.assertAll();
			driver.findElement(ErrorMessageOK).click();
			
		}
	}

	public void userclicksonclaimwithcompletedstatus(String claimNumber, String claimStatus) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ClaimList));
		List <WebElement> claimlist= driver.findElements(ClaimList);
		int Count = claimlist.size();
		System.out.println("Claim List Size --> " + Count);
		for (int i=1;i<=Count;i++) {
			wait.until(ExpectedConditions.elementToBeClickable(FirstClaimID));
			String ClaimStatus = driver.findElement(By.xpath("//*[@id='summaryTable']/tbody/tr[" + i + "]/td[10]/span/span")).getText();
			System.out.println("Claim Status--> " + ClaimStatus);
			if(ClaimStatus.equalsIgnoreCase(claimStatus)) {
				String ClaimID = driver.findElement(By.xpath("//*[@id='summaryTable']/tbody/tr[" + i + "]/td[2]/a/span")).getText();
				driver.findElement(By.xpath("//*[@id='summaryTable']/tbody/tr[" + i + "]/td[2]/a/span")).click();
				ScenarioContext.setContext(claimNumber, ClaimID);
				break;
			}
		}
	}

	public void usertakesclaimnumberandsetsvalueforpartialandfullcalimnumber(String claimNumber,
			String partialClaimNumber, String fullClaimNumber) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ClaimList));
		wait.until(ExpectedConditions.elementToBeClickable(FirstClaimID));
		String ClaimNumber = driver.findElement(FirstClaimID).getText();
		ScenarioContext.setContext(fullClaimNumber, ClaimNumber);
		String PartialClaimNumber = ClaimNumber.substring(0, 10);
		System.out.println("Partial Claim number :" + PartialClaimNumber);
		ScenarioContext.setContext(partialClaimNumber, PartialClaimNumber);
		
		//driver.findElement(By.xpath("(//span[contains(text(),'Claims')])[2]")).click();
	}

	public void usergetsthepatientaccountnumberandsetsthevalue(String patientNumber) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ClaimList));
		wait.until(ExpectedConditions.elementToBeClickable(FirstClaimID));
		String PatientNumberinUI = driver.findElement(FirstPatientNumber).getText();	
		ScenarioContext.setContext(patientNumber,PatientNumberinUI );
		
	}

	public void usergetsmemberIDfromListofClaims(String memberId) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ClaimList));
		wait.until(ExpectedConditions.elementToBeClickable(FirstClaimID));
		String MemberIdinUI = driver.findElement(FirstMemberId).getText();
		ScenarioContext.setContext(memberId, MemberIdinUI);
	}
	
	
}
