package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

import io.restassured.path.xml.XmlPath;

public class ReferralDetailPage  extends TestBase{

	public ReferralDetailPage(WebDriver driver) {

		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	//page factory
	
//	By ExpandallXpath=By.xpath("//span[@class='hap-links expand-collapse-btn expand']");
	By PlanIDUIXpath=By.xpath("//div/div[1]/div[2]/div[2]/span[1]/span");
	By PlandescUIXpath=By.xpath("//div/div[1]/div[2]/div[2]/span[2]/span");
	By PCPnameUIXpath=By.xpath("//div/div[2]/div[2]/div[2]/span/span");
	By AuthTypeUIXpath=By.xpath("//div/div/table/tbody/tr/td[3]");
	By DiagnosisUIXpath=By.xpath("//div/div/table/tbody/tr/td[4]/span[1]");
	By FromProviderUIXpath=By.xpath("//div[@id=\"yourcosts\"]/div/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span");
	By ToProviderUIXpath=By.xpath("//div[@id=\"yourcosts\"]/div/div/div/div[2]/div/div/div[1]/div[1]/div[2]/span");
	By ToFacilityUIXpath=By.xpath("//div[@id=\"yourcosts\"]/div/div/div/div[3]/div/div/div[1]/div[1]/div[2]/span");
	By ProcedureCodeUIXpath=By.xpath("//div/div/table/tbody/tr/td[3]/div/span[1]");
	By ProcedureDescUIXpath=By.xpath("//div/div/table/tbody/tr/td[3]/div/span[2]");
	By ExpandAllLink = By.xpath("//span[contains(text(),'Expand All')]");
	
	public void validateReferralDetailpage(String responseXMLPath, String membername) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ExpandAllLink));
		driver.findElement(ExpandAllLink).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		String planIDXpath="Envelope.Body.GetReferralListByMemberIdResponse.GetReferralListByMemberIdResult.ResponseData.ReferralDetails.MemberInfo.planId";
		String plandescXpath="Envelope.Body.GetReferralListByMemberIdResponse.GetReferralListByMemberIdResult.ResponseData.ReferralDetails.MemberInfo.planDesc";
		String PCPNameXpath="Envelope.Body.GetReferralListByMemberIdResponse.GetReferralListByMemberIdResult.ResponseData.ReferralDetails.MemberInfo.pcpName";
		String TypeXpath="Envelope.Body.GetReferralListByMemberIdResponse.GetReferralListByMemberIdResult.ResponseData.ReferralDetails.referralLineItems.ReferralLineItem.referralType";
		String DiagnosisXpath="Envelope.Body.GetReferralListByMemberIdResponse.GetReferralListByMemberIdResult.ResponseData.ReferralDetails.referralLineItems.ReferralLineItem.diagnosisDesc";
		String RefferedfromXpath="Envelope.Body.GetReferralListByMemberIdResponse.GetReferralListByMemberIdResult.ResponseData.ReferralDetails.ReferredFromProvider.providerName";
		String RefferedtoXpath="Envelope.Body.GetReferralListByMemberIdResponse.GetReferralListByMemberIdResult.ResponseData.ReferralDetails.ReferredToProvider.providerName";
		String RefferedtofacilityXpath="Envelope.Body.GetReferralListByMemberIdResponse.GetReferralListByMemberIdResult.ResponseData.ReferralDetails.ReferredToFacility.providerName";
		String ProcedureCodeXpath="Envelope.Body.GetReferralListByMemberIdResponse.GetReferralListByMemberIdResult.ResponseData.ReferralDetails.referralLineItems.ReferralLineItem.procedureCode";
		String ProcedureDescXpath="Envelope.Body.GetReferralListByMemberIdResponse.GetReferralListByMemberIdResult.ResponseData.ReferralDetails.referralLineItems.ReferralLineItem.procedureDesc";
		validateMemberInformation(responseXMLPath,planIDXpath,plandescXpath,PCPNameXpath);
		validateReferralInformation(responseXMLPath,TypeXpath,DiagnosisXpath);
		validateProviderInformation(responseXMLPath,RefferedfromXpath,RefferedtoXpath,RefferedtofacilityXpath);
		validateProcedureInformation(responseXMLPath,ProcedureCodeXpath,ProcedureDescXpath);
	}
	private void validateProcedureInformation(String responseXMLPath, String procedureCodeXpath,
			String procedureDescXpath) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		String ProcedureCode=xt.getString(procedureCodeXpath);
		String ProcedureDesc=xt.getString(procedureDescXpath);
		String ProcedureCodeUI=driver.findElement(ProcedureCodeUIXpath).getText();
		String ProcedureDescUI=driver.findElement(ProcedureDescUIXpath).getText();
		softassert.assertEquals(ProcedureCode,ProcedureCodeUI);
		softassert.assertTrue(ProcedureDesc.equalsIgnoreCase(ProcedureDescUI));
		softassert.assertAll();
	}
	private void validateProviderInformation(String responseXMLPath, String refferedfromXpath, String refferedtoXpath,
			String refferedtofacilityXpath) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		String RefferedFromProvider=xt.getString(refferedfromXpath);
		String RefferedToProvider=xt.getString(refferedtoXpath);
		String RefferedtoFacility=xt.getString(refferedtofacilityXpath);
		String FromProvidername=driver.findElement(FromProviderUIXpath).getText();
		String ToProvidername=driver.findElement(ToProviderUIXpath).getText();
		String ToFacilityname=driver.findElement(ToFacilityUIXpath).getText();
		softassert.assertTrue(RefferedFromProvider.equalsIgnoreCase(FromProvidername),"Reffered From Providername is not matching with UI");
		softassert.assertTrue(RefferedToProvider.equalsIgnoreCase(ToProvidername),"Reffered To Provider name is not matching with UI");
		softassert.assertTrue(RefferedtoFacility.equalsIgnoreCase(ToFacilityname),"Refferred to facility is not equal to facility");
//		softassert.assertEquals(RefferedtoFacility, ToFacilityname);
		softassert.assertAll();
	}
	private void validateReferralInformation(String responseXMLPath, String typeXpath, String DiagnosisXpath) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		String ReferralType=xt.getString(typeXpath);
		String Diagnosis=xt.getString(DiagnosisXpath);
		String ReferralTypeUI=driver.findElement(AuthTypeUIXpath).getText();
		String DiagnosisUI=driver.findElement(DiagnosisUIXpath).getText();
		softassert.assertEquals(ReferralType, ReferralTypeUI);
		softassert.assertEquals(Diagnosis,DiagnosisUI);
		softassert.assertAll();
		
	}
	private void validateMemberInformation(String responseXMLPath, String planIDXpath, String plandescXpath,
			String pCPNameXpath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		String planID=xt.getString(planIDXpath);
		System.out.println("PlanIDAPI :" + planID);
		String planDesc=xt.getString(plandescXpath);
		System.out.println("PlandesAPI :" + planDesc);
		String PCPname=xt.getString(pCPNameXpath);
		System.out.println("PCPnameAPI :" + PCPname);
		Thread.sleep(5000);
		String PlanIDUI=driver.findElement(PlanIDUIXpath).getText();
		System.out.println("PlanIDui :" + PlanIDUI);
		String PlandescUI=driver.findElement(PlandescUIXpath).getText();
		System.out.println("Plandesui :" + PlandescUI);
		String PCPnameUI=driver.findElement(PCPnameUIXpath).getText();
		System.out.println("Pcpnameui :" + PCPnameUI);
		softassert.assertEquals(planID, PlanIDUI);
		softassert.assertEquals(planDesc, PlandescUI);
		softassert.assertTrue(PCPname.equalsIgnoreCase(PCPnameUI),"PCP name not matching with UI");
		softassert.assertAll();
					
	}
	
}
