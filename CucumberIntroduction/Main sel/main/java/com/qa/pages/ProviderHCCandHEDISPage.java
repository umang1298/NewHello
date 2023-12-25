package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

public class ProviderHCCandHEDISPage extends TestBase{

	public ProviderHCCandHEDISPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	
	By PanelManagementreportLink = By.xpath("//a[contains(text(),'Gaps Panel Management Report')]");
	By MemberIDTextBox = By.xpath("//input[@id='memberId']");
	By SubmitButton = By.xpath("//button[@id='btnSubmit']");
	
	public void userentersmemberIDandClickssearch(String memberID) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(PanelManagementreportLink));
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		String MemberID = (String) ScenarioContext.getContext(memberID);
		driver.findElement(MemberIDTextBox).sendKeys(MemberID);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(SubmitButton).click();
		
	}

	public void userclicksonPanelManagementReportLink() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(PanelManagementreportLink));
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(PanelManagementreportLink).click();
	}
	
}
