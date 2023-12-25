package com.qa.pages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.AWTException;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import io.restassured.path.xml.XmlPath;

public class MyCoveragePage extends TestBase {
	
	SoftAssert softassert = new SoftAssert();
	
	public MyCoveragePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

//PageFactory
	By MemberDropdown=By.id("member-picker");
	By Searchbutton=By.xpath("//button[@id='searchButton']");
	By contractinfo=By.xpath("//h2[contains(text(),'Contract Information')]");
	By Expandall=By.xpath("//span[@class='hap-links expand-collapse-btn expand']");
	By GroupnameUIXpath=By.xpath("//div/div[2]/div[1]/div[2]/span");
	By dualmember=By.xpath("//select[@id='member-picker']");
	By SBCDocument = By.xpath("//a[contains(text(),'Summary of Benefits and Coverage.')]");
	By Errorimage = By.xpath("//img[@class='pnf-pencil-image margin-top-10']");
	By BackButton = By.xpath("//button[@id='homeButton']");
	By EffectivDate= By.xpath("//div[@id=\"providerinfo\"]/div/div[4]/div[2]/div[2]/span");
	By CloseButton=By.xpath("//button[@id='closeButton']");
	By viewmyIDCard=By.xpath("//span[contains(text(),'View my ID card')]");
	By RxCoverage= By.xpath("//a[contains(text(),'My Rx Coverage')]");
	By CompareCost= By.xpath("//a[contains(text(),'Compare Cost')]");
	By Rxcovlogo=By.xpath("//body/div[@id='hdrWrap']/div[@id='hdr2']/div[1]");
	//contract information
	By AppGroupID=By.xpath("//div[@id=\"providerinfo\"]/div/div[1]/div[1]/div[2]/span");
	By AppGroupName=By.xpath("//div[@id=\"providerinfo\"]/div/div[2]/div[1]/div[2]/span");
	By AppPlan=By.xpath("//div[@id=\"providerinfo\"]/div/div[3]/div[1]/div[2]/span");
	By AppRxPCN=By.xpath("//div[@id=\"providerinfo\"]/div/div[2]/div[2]/div[2]/span");
	By AppRxGroup=By.xpath("//div[@id=\"providerinfo\"]/div/div[3]/div[2]/div[2]/span");
	By AppRxBin=By.xpath("//div[@id=\"providerinfo\"]/div/div[1]/div[2]/div[2]/span");
	By AppEffectivedatemycoverage=By.xpath("//div[@id='providerinfo']/div/div[4]/div[2]/div[2]/span");
	By COBEffectivedate=By.xpath("//div[@id='cobenefitsinfo']/div/div/table[@class='table hap-table p5 hap-secondary-table stackable-tablet claimmembertable']/tbody/tr/td[4]");
	
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	public void Userselectsoptionfrommycoverage(String MenuoptiontoPass) throws InterruptedException {		
		String MenuoptionXpath = "(//h2[contains(text(),'" + MenuoptiontoPass + "')])";
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(MenuoptionXpath))));
		driver.findElement(By.xpath(MenuoptionXpath)).click();
		System.out.println("Clicked on " +MenuoptiontoPass);
	}

	
	public void UserClicksSummaryOfBenefitsCoverageJS(String MedicalProductID, String PharmacyProductID)
			throws MalformedURLException, IOException, AWTException, InterruptedException
	{
		WebElement doc = driver.findElement(SBCDocument);
		String MedicalProduct = (String) ScenarioContext.getContext(MedicalProductID);
		System.out.println(MedicalProduct);
		String PharmacyProduct = (String) ScenarioContext.getContext(PharmacyProductID);
		System.out.println(PharmacyProduct);
		System.out.println("Clicked on the link for the SBC document");
		Thread.sleep(3000);
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])",doc,"download","");
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])",doc,"target","_blank");
		js.executeScript("arguments[0].click();", doc);
		Thread.sleep(30001);
		String home = System.getProperty("user.home");
		File sourceFileLocWhereTheDownloadWillHappen = new File(home+"/Downloads/" + "HAP_SBC.pdf");
		

		if ( sourceFileLocWhereTheDownloadWillHappen.exists()) {
						
		// Load the file
		PDDocument document = PDDocument.load(sourceFileLocWhereTheDownloadWillHappen);
		// Instantiate PDFTextStripper class
		PDFTextStripper pdfStripper = new PDFTextStripper();
		// Retrieving text from PDF document
		String PDFContent = pdfStripper.getText(document);
		System.out.println("This is the SBC PDF Content -->" +PDFContent);
		//Validate Medical Product ID in the Document
		softassert.assertTrue(PDFContent.contains(MedicalProduct),"Validating SBC document for Medical ProductID");
		softassert.assertTrue(PDFContent.contains(PharmacyProduct),"Validating SBC document for Pharmacy ProductID");
		
		// Closing the document
		document.close();
		Thread.sleep(5000);
		if (sourceFileLocWhereTheDownloadWillHappen.delete()) {
			
			System.out.println("File deleted from Downloads");
		} else
		{
			System.out.println("File doesn't deleted from Downloads");
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		}
		}
		else
		{
			System.out.println("Document Not Found in the server");
		}
	
			softassert.assertAll();
	}
	public void userverifiesSBCDocumentLink()
	{
		WebElement SBCDocumentlink = driver.findElement(SBCDocument);
		String pagesource= driver.getPageSource();
		softassert.assertTrue(pagesource.contains("Summary of Benefits and Coverage"),"Validating SBC Document Link is Present");
			//System.out.println("SBC Document Link is Present");
			softassert.assertAll();
	}


	public void validatescontractinformation(String responseXMLPath) throws IOException, ParseException, InterruptedException {
		// TODO Auto-generated method stub
		String MembersfirstnameXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.FirstName";
		String PlanXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].BenefitPlanDesc";
		String GroupIDXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].GroupId";
		String SubGroupIDXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].SubgroupId";
		String GroupNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].GroupName";
		String RxGroupIDXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].RxGroupID";
		String RxBinXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].RxBin";
		String RxPCNXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].RxPCN";
		String EffectiveDateXPath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].EffectiveDate";                                                                                      
		String SubscriberXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber";
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		List<org.w3c.dom.Element> ListofTags = xt.getList(SubscriberXpath);
		int NumberofContracts = ListofTags.size();
		System.out.println("Number of contracts: " +NumberofContracts);
		if(NumberofContracts==1)
		{
			System.out.println("This user is having only one contract");
			ValidateEffectivedate(responseXMLPath, EffectiveDateXPath);
			ValidateviewmyIDcardlink(EffectiveDateXPath);
			ValidateRxInformation(responseXMLPath,RxGroupIDXpath,RxBinXpath,RxPCNXpath);
			ValidateGroupIDGroupnameandplan(responseXMLPath,GroupNameXpath,GroupIDXpath,SubGroupIDXpath,PlanXpath);
		}
		else
		{
			Boolean isPresent = driver.findElements(MemberDropdown).size() > 0;
			
			if(isPresent==true)
			{
				Select drop1=new Select(driver.findElement(MemberDropdown));
				drop1.selectByIndex(1);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				String groupname= drop1.getOptions().get(1).getText();
				System.out.println("Groupname:"+groupname);
				driver.findElement(Searchbutton).click();
				driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
				driver.findElement(contractinfo).click();
				driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
				By GroupnameUIXpath=By.xpath("//div/div[2]/div[1]/div[2]/span");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				String GroupnameUI=driver.findElement(GroupnameUIXpath).getText();
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				System.out.println("GroupnameUI:"+GroupnameUI);
				Assert.assertTrue(groupname.equals(GroupnameUI), "Groupname is not populated as per the selection in drop down for Dual contracts");
				
			}
			else
			{
				Assert.fail("Dropdown is not present for dual contracts");
						
			}
		
		}
		
	}


	public void validatescoveredmembersdetails(String responseXMLPath,String usertype) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		String MembersfirstnameXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.FirstName";
		
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		Thread.sleep(3000);
		XmlPath xt = new XmlPath(contents);
		if(usertype.equals("Dependent"))
		{
			String MemberIDXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[1].MemberId";
			String LastNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[1].ProviderHistory.Provider.LastName";
			String FirstNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[1].ProviderHistory.Provider.FirstName";
			String memberid=xt.getString(MemberIDXpath);
			System.out.println("MemID:"+memberid);
			String memberIDUI=driver.findElement(By.xpath("//div[@id='coveredMember']/div/div/div/table/tbody/tr[1]/td[3]/span")).getText();
			System.out.println("memberID UI:"+memberIDUI);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			Assert.assertEquals(memberid,memberIDUI, "Dependent MemberID not matching");
			String LastName=xt.getString(LastNameXpath);
			String FirstName=xt.getString(FirstNameXpath);
			System.out.println("firstname:"+FirstName);
			System.out.println("Lastname:"+LastName);
			String Providername=FirstName+" "+LastName;
			String PCP=driver.findElement(By.xpath("//div[@id='coveredMember']/div/div/div/table/tbody/tr[1]/td[5]/span")).getText();
			System.out.println("Providername:"+Providername);
			System.out.println("PCP name UI:"+PCP);
			Assert.assertTrue(Providername.equalsIgnoreCase(PCP), "Dependent PCP name populated incorrectly");
		
		}
		else
		{
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);		
		List<org.w3c.dom.Element> ListofTags = xt.getList(MembersfirstnameXpath);
		int NumberofMembers = ListofTags.size();
		
		System.out.println("Webservice member count:" +NumberofMembers);
		By Memeber=By.xpath("//div[@id=\"coveredMember\"]/div/div/div/table/tbody/tr/td[3]/span[1]");
		List<WebElement> Membernamecount= driver.findElements(Memeber);
		int linkCount = Membernamecount.size();
		System.out.println("UI member count:"+linkCount);
		Assert.assertTrue(linkCount==NumberofMembers,"Members count is not matching with webservice");
		
		for(int i=0,j=1;i<NumberofMembers;i++,j++)
		{
			String MemberIDXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].MemberId";
			String AgeXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[" +i+"].Age";
			String SuffixXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].Suffix";
			String LastNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].ProviderHistory.Provider.LastName";
			String FirstNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].ProviderHistory.Provider.FirstName";
			String Suffix=xt.getString(SuffixXpath);
			String memberid=xt.getString(MemberIDXpath);
			System.out.println("memberID XML:"+memberid);
			String Age=xt.getString(AgeXpath);
			String memberIDUI=driver.findElement(By.xpath("//div[@id=\"coveredMember\"]/div/div/div/table/tbody/tr["+j+"]/td[3]/span")).getText();
			System.out.println("memberID UI:"+memberIDUI);
			softassert.assertEquals(memberid,memberIDUI, "MemberID not matching");
			softassert.assertAll();
			int age=Integer.parseInt(Age);
			System.out.println("Age:" +age);
			int MemberSuffix=Integer.parseInt(Suffix);
			System.out.println("MemberSuffix: "+MemberSuffix);
			String LastName=xt.getString(LastNameXpath);
			String FirstName=xt.getString(FirstNameXpath);
			if(MemberSuffix==0)
			{
				if(LastName.isEmpty())
				{
					String PCP=driver.findElement(By.xpath("//div[@id=\"coveredMember\"]/div/div/div/table/tbody/tr["+j+"]/td[5]/span")).getText();
					System.out.println("PCP name UI:"+PCP);
					String NoProvider="No PCP on record";
					Assert.assertTrue(PCP.contains(NoProvider), "PCP information is wrong");
				}
				else
				{
				String Providername=FirstName+" "+LastName;
				String PCP=driver.findElement(By.xpath("//div[@id=\"coveredMember\"]/div/div/div/table/tbody/tr["+j+"]/td[5]/span")).getText();
				System.out.println("Providername:"+Providername);
				System.out.println("PCP name UI:"+PCP);
				Assert.assertTrue(Providername.equalsIgnoreCase(PCP), "PCP name populated incorrectly");
				}
				}
			else
			{
			if(age>18)
			{
				String PCP=driver.findElement(By.xpath("//div[@id=\"coveredMember\"]/div/div/div/table/tbody/tr["+j+"]/td[5]/span")).getText();
				System.out.println("PCP name Dependent UI:"+PCP);
				String dependentageabove18=" dependents under the age of 18";
				Assert.assertTrue(PCP.contains(dependentageabove18), "PCP information is wrong");
			}
		
			else
			{
			
				System.out.println("firstname:"+FirstName);
				System.out.println("Lastname:"+LastName);
				String Providername=FirstName+" "+LastName;
			//	System.out.println("Providername:"+Providername);
				String PCP=driver.findElement(By.xpath("//div[@id=\"coveredMember\"]/div/div/div/table/tbody/tr["+j+"]/td[5]/span")).getText();
				System.out.println("Providername:"+Providername);
				System.out.println("PCP name UI:"+PCP);
				Assert.assertTrue(Providername.equalsIgnoreCase(PCP), "PCP name populated incorrectly");
			}
				 
		}
		}
		}

	
	}
	public void ValidateEffectivedate(String responseXMLPath, String xpathtoEffDate) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		String EffectiveDate = xt.getString(xpathtoEffDate);
		System.out.println("Effective date from Response -->" + EffectiveDate);
		String EffectivedateinUI = driver.findElement(AppEffectivedatemycoverage).getText();
		System.out.println("Effective date in UI -->" + EffectivedateinUI);
		String Effdatefromresponse = EffectiveDate.substring(0, 10);
		String ExpectedEffectivedate = CommonMethods.returnConverteddateformatMMDDYYYY(Effdatefromresponse);
		System.out.println("Expected Eff Date from Respone -->" + ExpectedEffectivedate);
		softassert.assertEquals(EffectivedateinUI, ExpectedEffectivedate , "Validating Effective/Term dates");
		ScenarioContext.setContext(xpathtoEffDate, Effdatefromresponse);
		softassert.assertAll();
	}
	public void ValidateGroupIDGroupnameandplan(String responseXMLPath,String GroupNameXpath,String GroupIDXpath,String SubGroupIDXpath,String PlanXpath) throws IOException
	{
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		
		String GroupName=xt.getString(GroupNameXpath);
		String GroupID=xt.getString(GroupIDXpath);
		String SubGroupID=xt.getString(SubGroupIDXpath);
		String Plan=xt.getString(PlanXpath);
		System.out.println("My Coverage GroupName: "+GroupName);
		System.out.println("My Coverage GroupID: "+GroupID);
		System.out.println("My Coverage SubGroupID: "+SubGroupID);
		System.out.println("My Coverage Plan: "+Plan);
		String GroupIDXML=GroupID+SubGroupID;
		//UI related text
		String UIGroupID=driver.findElement(AppGroupID).getText();
		String UIGroupname=driver.findElement(AppGroupName).getText();
		String UIPlan=driver.findElement(AppPlan).getText();
					
		softassert.assertTrue(UIGroupname.equalsIgnoreCase(GroupName),"Group name is not as expected");
		softassert.assertTrue(UIPlan.equalsIgnoreCase(Plan),"Plan is not as expected");
		softassert.assertTrue(UIGroupID.equalsIgnoreCase(GroupIDXML), "GroupID is not as expected");
		softassert.assertAll();
	}
	public void ValidateRxInformation(String responseXMLPath,String RxGroupIDXpath,String RxBinXpath,String RxPCNXpath) throws IOException
	{
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		String RxGroupID=xt.getString(RxGroupIDXpath);
		String RxBin=xt.getString(RxBinXpath);
		String RxPCN=xt.getString(RxPCNXpath);
		
		System.out.println("My Coverage RxGroupID: "+RxGroupID);
		System.out.println("My Coverage RxBin: "+RxBin);
		System.out.println("My Coverage RxPCN: "+RxPCN);
		
		//UI related text
		
		String UIRxBin=driver.findElement(AppRxBin).getText();
		String UIRxPCN=driver.findElement(AppRxPCN).getText();
		String UIRxGroup=driver.findElement(AppRxGroup).getText();
		String UIEffecDate=driver.findElement(AppEffectivedatemycoverage).getText();

		

		if(RxBin.isEmpty()||RxGroupID.isEmpty()||RxPCN.isEmpty())
		{
			softassert.assertTrue(UIRxBin.contains("Not"),"RxBin is not as expected");
			softassert.assertTrue(UIRxGroup.contains("Not"),"RxGroupID is not as expected");
			softassert.assertTrue(UIRxPCN.contains("Not"),"RxPCN is not as expected");
			softassert.assertAll();
		}
		else
		{
		
		System.out.println("UIRxBin:" +UIRxBin);
		System.out.println("UIRxPCN:" +UIRxPCN);
		System.out.println("UIRxGroup:" +UIRxGroup);
		softassert.assertTrue(UIRxBin.equalsIgnoreCase(RxBin),"RxBin is not as expected");
		softassert.assertTrue(UIRxGroup.equalsIgnoreCase(RxGroupID),"RxGroupID is not as expected");
		softassert.assertTrue(UIRxPCN.equalsIgnoreCase(RxPCN),"RxPCN is not as expected");
		softassert.assertAll();
		}
	}
	public void ValidateviewmyIDcardlink(String xpathtoEffDate) throws ParseException, InterruptedException
	{
		String EffectiveDate = (String) ScenarioContext.getContext(xpathtoEffDate);
		System.out.println("Effectve Date:" +EffectiveDate);
		DateFormat df = new SimpleDateFormat("yyyy-dd-mm");
		Date startDate = df.parse(EffectiveDate);
		   Date date = new Date();  
		    System.out.println(df.format(date));  
		System.out.println("Current Date:" +date);
		System.out.println("Actual Date:" +startDate);
		
		
if(startDate.before(date))
{
	System.out.println("date ID card link should avalable");
	Boolean isPresent = driver.findElements(viewmyIDCard).size() > 0;
		if(isPresent==true)
		{
			 driver.findElement(viewmyIDCard).click();
			 Thread.sleep(12000);
			
				driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
				ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(newtabs.get(1));
				driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
			String idcardpage=driver.getCurrentUrl();
			System.out.println("IDcard page:" +idcardpage);
		Assert.assertTrue(driver.getCurrentUrl().contains("getMemberIDcard"),"Myidcard link not reached to expected page");
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		}
		else
			Assert.fail("My ID card link is not available");
}
	}
	

	public void userverifiesthirdpartyLinks(String responseXMLPath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String medicareXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].IsMedicareAdvantage";
		String MedicaidXpath= "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[1].MedicaidNumber";
		String TieredXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].isTier";
		validateSBClink(responseXMLPath,medicareXpath,MedicaidXpath);
		validateRxCoverage();
		validateCompareCostlink(responseXMLPath,medicareXpath,MedicaidXpath,TieredXpath);
	}


	private void validateCompareCostlink(String responseXMLPath,String medicareXpath,String MedicaidXpath,String tiredXpath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		String Medicaremember=xt.getString(medicareXpath);
		String Medicaidmember=xt.getString(MedicaidXpath);
		String Tieredmember=xt.getString(tiredXpath);
		System.out.println("Tiered:"+Tieredmember);
		System.out.println("Medicaid:" +Medicaidmember);
		System.out.println("Medicare:"+Medicaremember);
		if((Medicaremember.contentEquals("true"))||(Medicaidmember.isEmpty())||(Tieredmember.contentEquals("true")))
		{
			System.out.println("Medicaremember/Medicaidmember/Tieredmember Compare cost link should not be available");
		}
		else
			
		{
			
			String linkname=driver.findElement(CompareCost).getText();
			WebElement mycoveragelinks=driver.findElement(CompareCost);
			js.executeScript("arguments[0].click();", mycoveragelinks);
			System.out.println("Clicked on " +linkname);
			Thread.sleep(12001);
			driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
			//driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
			ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newtabs.get(1));
			driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
			String targetpageurl=driver.getCurrentUrl();
			System.out.println(targetpageurl);
			softassert.assertTrue(targetpageurl.contains("healthSparq"),"Correct page is not loaded");
			softassert.assertAll();  
			driver.close();
			driver.switchTo().window(newtabs.get(0));
		
		}
	}


	private void validateRxCoverage() throws InterruptedException {
		// TODO Auto-generated method stub
		
		String linkname=driver.findElement(RxCoverage).getText();
		WebElement mycoveragelinks=driver.findElement(RxCoverage);
		js.executeScript("arguments[0].click();", mycoveragelinks);
		System.out.println("Clicked on " +linkname);
		Thread.sleep(12001);
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(Rxcovlogo));
		String targetpageurl=driver.getCurrentUrl();
		System.out.println(targetpageurl);
		softassert.assertTrue(targetpageurl.contains("express-scripts"),"Correct page is not loaded");
		softassert.assertAll();  
		driver.close();
		driver.switchTo().window(newtabs.get(0));
	}


	private void validateSBClink(String responseXMLPath, String medicareXpath,String MedicaidXpath) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		String Medicaremember=xt.getString(medicareXpath);
		String Medicaidmember=xt.getString(MedicaidXpath);
		System.out.println("Medicaid:" +Medicaidmember);
		System.out.println("Medicare:"+Medicaremember);
		if((Medicaremember.contentEquals("true"))||(Medicaidmember.isEmpty()))
		{
			System.out.println("medicaremember or medicaid member SBC document should not be available");
		}
		else
			
		{
			System.out.println("validate for SBCLink");
			WebElement SBCDocumentlink = driver.findElement(SBCDocument);
			String pagesource= driver.getPageSource();
			softassert.assertTrue(pagesource.contains("Summary of Benefits and Coverage"),"Validating SBC Document Link is Present");
				softassert.assertAll();
		}
	}


	public void userverifiesterminatedandfuturemember() {
		// TODO Auto-generated method stub
		Boolean termina=driver.findElements(By.xpath("//span[contains(text(),'Termination date:')]")).size()>0;
		Boolean viewmyIdcardlink=driver.findElements(viewmyIDCard).size()>0;
		if(termina==true)
		{
			System.out.println("Terminatedmember");
			Assert.assertFalse(viewmyIdcardlink==true,"ViewmyIDCard link should not present");
		}
		else
		{
			System.out.println("Future date ID card link should be available");
			Assert.assertFalse(viewmyIdcardlink==false,"ViewmyIDCard link should present");
		}
	}


	public void validatesyourcostdetails(String responseXMLPath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		String MedicaidXpath= "Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].MedicaidNumber";
		String Medicaidnumber=xt.getString(MedicaidXpath);
		System.out.println("Member Informations- Medicaid: " +Medicaidnumber);
		if(Medicaidnumber.isEmpty())
		{
			
			validatemedical1(responseXMLPath);
			validatePharmacy1(responseXMLPath);
			validateVision1(responseXMLPath);
		}
		else
		{
			System.out.println("medicaidmember");
	validatemedical1(responseXMLPath);
		
		}
}
	
	


	public void uservalidatesdualcontractdropdown(String responseXMLPath) throws IOException{
		// TODO Auto-generated method stub
		
		String SubscriberXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber";
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
	//	String BenefitdetailXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].BenefitDetails.BenefitDetail.Type";
		XmlPath xt = new XmlPath(contents);
		List<org.w3c.dom.Element> ListofTags = xt.getList(SubscriberXpath);
		int NumberofContracts = ListofTags.size();
		System.out.println("Number of contracts: " +NumberofContracts);
		if(NumberofContracts==1)
		{
			System.out.println("This user is having only one contract");
		}
		else
		{
			Boolean isPresent = driver.findElements(MemberDropdown).size() > 0;
			
			if(isPresent==true)
			{
				Select drop1=new Select(driver.findElement(MemberDropdown));
				drop1.selectByIndex(0);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				String groupname= drop1.getOptions().get(0).getText();
				System.out.println("Groupname:"+groupname);
				driver.findElement(Searchbutton).click();
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				driver.findElement(contractinfo).click();
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				By GroupnameUIXpath=By.xpath("//div[@id=\"providerinfo\"]/div/div[2]/div[1]/div[2]/span");
				String GroupnameUI=driver.findElement(GroupnameUIXpath).getText();
				System.out.println("GroupnameUI:"+GroupnameUI);
				Assert.assertTrue(groupname.equals(GroupnameUI), "Groupname is not populated as per the selection in drop down for Dual contracts");
			
			}
			else
			{
				Assert.fail("Dropdown is not present for dual contracts");
						
			}
		}
	}


	public void validatesCOBMemberDetails(String responseXMLPath) throws IOException, ParseException, InterruptedException {
		// TODO Auto-generated method stub
		String SubscriberXpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber";
		String EffectiveDateXPath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].COBInfo.CoordinationOfBenefits.MecbEffectiveDate";
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		List<org.w3c.dom.Element> ListofTags = xt.getList(SubscriberXpath);
		int NumberofContracts = ListofTags.size();
		System.out.println("Number of contracts: " +NumberofContracts);
		if(NumberofContracts==1)
		{
			System.out.println("This user is having only one contract");
			ValidatecobEffectivedate(responseXMLPath, EffectiveDateXPath);
		ValidateviewmyIDcardlink(EffectiveDateXPath);
		}
		else
		{
			Boolean isPresent = driver.findElements(MemberDropdown).size() > 0;
			
			if(isPresent==true)
			{
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				Select drop1=new Select(driver.findElement(MemberDropdown));
				drop1.selectByIndex(1);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				String groupname= drop1.getOptions().get(1).getText();
				System.out.println("Groupname:"+groupname);
				wait.until(ExpectedConditions.elementToBeClickable(Searchbutton));
				driver.findElement(Searchbutton).click();
				driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
				wait.until(ExpectedConditions.elementToBeClickable(Expandall));
				driver.findElement(Expandall).click();
				wait.until(ExpectedConditions.elementToBeClickable(GroupnameUIXpath));
			
				String GroupnameUI=driver.findElement(GroupnameUIXpath).getText();
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				System.out.println("GroupnameUI:"+GroupnameUI);
				Assert.assertTrue(groupname.equals(GroupnameUI), "Groupname is not populated as per the selection in drop down for Dual contracts");
				ValidateEffectivedate(responseXMLPath, EffectiveDateXPath);
				ValidateviewmyIDcardlink(EffectiveDateXPath);
				userverifiesthirdpartyLinks(responseXMLPath);
				validatePCPInformation(responseXMLPath);
				validateyourcostdetails(responseXMLPath);
			}
			else
			{
				Assert.fail("Dropdown is not present for dual contracts");
						
			}
				
		}
	}


	private void validateyourcostdetails(String responseXMLPath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		validatemedical1(responseXMLPath);
			validatePharmacy1(responseXMLPath);
			validateVision1(responseXMLPath);
	}
	public void validatemedical1(String responseXMLPath) throws IOException, InterruptedException
	{
		String pcpcopayxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].PCPOfficeVisitCopay";
		String specofficecopayxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].SpecOfficeVisitCopay";
		String urgentcopayxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].UrgentCareCopay";
		String ercopayxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].ERCopay";
		String pcpcoinsath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].PCPOfficeVisitCoins";
		String specofficecoinsxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].SpecOfficeVisitCoins";
		String urgentcoinsxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].UrgentCareCoins";
		String ercoinsxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].ERCoins";
		String pcpSubtodedxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].SubjectToDeductible.isSubToDedOVP";
		String specofficesubtodedxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].SubjectToDeductible.isSubToDedOVS";
		String urgentsubtodedxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].SubjectToDeductible.isSubToDedUC";
		String ersubtodedxpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].SubjectToDeductible.isSubToDedER";
		
		
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
			XmlPath xt = new XmlPath(contents);
			String medicalpcpcopay=xt.getString(pcpcopayxpath);
			String medicalspeccopay=xt.getString(specofficecopayxpath);
			String medicalurgentcopay=xt.getString(urgentcopayxpath);
			String medicalercopay=xt.getString(ercopayxpath);
			String medicalpcpcoins=xt.getString(pcpcoinsath);
			String medicalspeccoins=xt.getString(specofficecoinsxpath);
			String medicalurgentcoins=xt.getString(urgentcoinsxpath);
			String medicalercoins=xt.getString(ercoinsxpath);
			String pcpsubtoded=xt.getString(pcpSubtodedxpath);
			String specsubtoded=xt.getString(specofficesubtodedxpath);
			String ursubtoded=xt.getString(urgentsubtodedxpath);
			String ersubtoded=xt.getString(ersubtodedxpath);
			int a=0;
			boolean Tierd=driver.findElements(By.xpath("//th[contains(text(),'Tier 2')]")).size()>0;
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			Thread.sleep(2000);
			if(Tierd==true)
			{
				a=3;
			}
			else
			{
				a=4;
			}
		
			By PCPUIxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[1]/td["+a+"]/span/div[1]/div[2]");
			WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		//	wait.until(ExpectedConditions.elementToBeClickable(PCPUIxpath));
			By specUIxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[2]/td["+a+"]/span/div[1]/div[2]");
			By urgentUIxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[4]/td["+a+"]/span/div[1]/div[2]");
			By erUIxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[5]/td["+a+"]/span/div[1]/div[2]");
			String PCPUIcopay=driver.findElement(PCPUIxpath).getText().substring(1);
			String specUIcopay=driver.findElement(specUIxpath).getText().substring(1);
			String urgentUIcopay=driver.findElement(urgentUIxpath).getText().substring(1);
			String erUIcopay=driver.findElement(erUIxpath).getText().substring(1);
			By PCPUIsubtodedxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[1]/td["+a+"]/span/div[3]/div[2]");
			By specUIsubtodedxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[2]/td["+a+"]/span/div[3]/div[2]");
			By urgentUIsubtodedxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[4]/td["+a+"]/span/div[3]/div[2]");
			By erUIsubtodedxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[5]/td["+a+"]/span/div[3]/div[2]");
			String PCPUIsubtoded=driver.findElement(PCPUIsubtodedxpath).getText();
			String specUIsubtoded=driver.findElement(specUIsubtodedxpath).getText();
			String urgentUIsubtoded=driver.findElement(urgentUIsubtodedxpath).getText();
			String erUIsubtoded=driver.findElement(erUIsubtodedxpath).getText();
			By PCPUIcoinsxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[1]/td["+a+"]/span/div[2]/div[2]");
			By specUIcoinsxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[2]/td["+a+"]/span/div[2]/div[2]");
			By urgentUIcoinsxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[3]/td["+a+"]/span/div[2]/div[2]");
			By erUIcoinsxpath=By.xpath("//div/div[1]/div[2]/table/tbody/tr[4]/td["+a+"]/span/div[2]/div[2]");
			String PCPUIcoins=driver.findElement(PCPUIcoinsxpath).getText();
			String specUIcoins=driver.findElement(specUIcoinsxpath).getText();
			String urgentUIcoins=driver.findElement(urgentUIcoinsxpath).getText();
			String erUIcoins=driver.findElement(erUIcoinsxpath).getText();
			softassert.assertTrue(medicalpcpcopay.equals(PCPUIcopay),"PCP copay");
			System.out.println("copayweb"+medicalpcpcopay);
			System.out.println("copayUI"+PCPUIcopay);
			System.out.println("copayweb"+medicalurgentcopay);
			System.out.println("copayUI"+urgentUIcopay);
			System.out.println("copayweb"+medicalercopay);
			System.out.println("copayUI"+erUIsubtoded);
			softassert.assertTrue(medicalspeccopay.equals(specUIcopay),"Spec copay");
			softassert.assertTrue(medicalurgentcopay.equals(urgentUIcopay),"UR copay");
			softassert.assertTrue(medicalercopay.equals(erUIcopay),"ER copay");
			softassert.assertTrue(PCPUIcoins.contains(medicalpcpcoins),"PCP coins");
			softassert.assertTrue(specUIcoins.contains(medicalspeccoins),"Spec coins");
			softassert.assertTrue(urgentUIcoins.contains(medicalurgentcoins),"UR coins");
			softassert.assertTrue(erUIcoins.contains(medicalercoins),"ER coins");
			softassert.assertEquals(PCPUIsubtoded.contains("No"), pcpsubtoded.contains("false"),"subjec to deductibles is wrong");
			softassert.assertEquals(specUIsubtoded.contains("No"), specsubtoded.contains("false"),"subjec to deductibles is wrong");
			softassert.assertEquals(urgentUIsubtoded.contains("No"), ursubtoded.contains("false"),"subjec to deductibles is wrong");
			softassert.assertEquals(erUIsubtoded.contains("No"), ersubtoded.contains("false"),"subjec to deductibles is wrong");
			softassert.assertAll();
}
	public void validatePharmacy1(String responseXMLPath) throws IOException, InterruptedException
	{
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		String BenefitdetailXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[1].BenefitDetails.BenefitDetail.Type";
		XmlPath xt = new XmlPath(contents);
		List<org.w3c.dom.Element> ListofTags = xt.getList(BenefitdetailXpath);
		int NumberofBenefitDetails = ListofTags.size();
		System.out.println(("Number of Pharmacy Benefit Details: " +NumberofBenefitDetails));
		String pharbenefit=xt.getString(BenefitdetailXpath);
		System.out.println("phar bene:"+pharbenefit);
		int a=0;
		boolean Tierd=driver.findElements(By.xpath("//th[contains(text(),'Tier 2')]")).size()>0;
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		Thread.sleep(2000);
		if(Tierd==true)
		{
			a=3;
		}
		else
		{
			a=4;
		}
	
		for(int i=1;i<=NumberofBenefitDetails;i++)
		{
			String PharmacyBenefitTypeXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[1].BenefitDetails.BenefitDetail["+i+"].Type";
			String PharmacyBenefit=xt.getString(PharmacyBenefitTypeXpath);
			String PharmacyCopayAmountXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[1].BenefitDetails.BenefitDetail["+i+"].CopayAmount";
			String PharmacyCoinsAmountXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[1].BenefitDetails.BenefitDetail["+i+"].CoinPct";
			String PharmacyCopay=xt.getString(PharmacyCopayAmountXpath);
			String PharmacyCoins=xt.getString(PharmacyCoinsAmountXpath);
		
			if(PharmacyBenefit.equals("RTL1"))
			{
				System.out.println("Preferred Generic:");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				By PharmacyNameXpathUI=By.xpath("//div/div[1]/div[3]/table/tbody/tr[1]/td["+a+"]");
				By PharmacyCopayxpathUI=By.xpath("//table/tbody/tr[1]/td["+a+"]/div[1]/div[2]");
				By PharmacyCoinsxpathUI=By.xpath("//table/tbody/tr[1]/td["+a+"]/div[2]/div[2]");
				String PharmacyNameUI=driver.findElement(PharmacyNameXpathUI).getText();
				String PharmacyCopayUI=driver.findElement(PharmacyCopayxpathUI).getText().substring(1);
				String PharmacyCoinsUI=driver.findElement(PharmacyCoinsxpathUI).getText();
				String SubtodeductiblesXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[1].SubjectToDeductible.isSubToDedRTL1";
				String RTL1Deduc=xt.getString(SubtodeductiblesXpath);
				By RTL1Subtoded=By.xpath("//table/tbody/tr[1]/td["+a+"]/div[3]/div[2]");
				String RTL1SubtodedUI=driver.findElement(RTL1Subtoded).getText();
				System.out.println("RTL1 CopayAmount:"+PharmacyCopay);
				System.out.println("RTL1 CoinsAmount:"+PharmacyCoins);
				System.out.println("RTL1 CopayAmountUI:"+PharmacyCopayUI);
				System.out.println("RTL1 CoinsAmountUI:"+PharmacyCoinsUI);
				System.out.println("RTL1 subtodedUI:"+RTL1SubtodedUI);
				System.out.println("RTL1 subtoded:"+RTL1Deduc);
				softassert.assertEquals(RTL1SubtodedUI.contains("No"), RTL1Deduc.contains("false"),"RTL1 subject to deductibles is wrong");
				softassert.assertTrue(PharmacyCopay.contains(PharmacyCopayUI), "RTL1 Copay Amount is not matching with the webservice");
				softassert.assertTrue(PharmacyCoinsUI.contains(PharmacyCoins),"RTL1 Coins Amount is not matching with the webservice");
				
			}
			else if(PharmacyBenefit.equals("RT1A"))
			{
				System.out.println("Non-Preferred Generic:");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				By PharmacyNameXpathUI=By.xpath("//div/div[1]/div[3]/table/tbody/tr[2]/td["+a+"]");
				By PharmacyCopayxpathUI=By.xpath("//table/tbody/tr[2]/td["+a+"]/div[1]/div[2]");
				By PharmacyCoinsxpathUI=By.xpath("//table/tbody/tr[2]/td["+a+"]/div[2]/div[2]");
				String PharmacyCopayUI=driver.findElement(PharmacyCopayxpathUI).getText().substring(1);
				String PharmacyCoinsUI=driver.findElement(PharmacyCoinsxpathUI).getText();
				String SubtodeductiblesXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[1].SubjectToDeductible.isSubToDedRT1A";
				String RT1ADeduc=xt.getString(SubtodeductiblesXpath);
				System.out.println("Pharmacy Benefit Type:"+PharmacyBenefit);
				System.out.println("RT1A CopayAmount:"+PharmacyCopay);
				System.out.println("RT1A CoinsAmount:"+PharmacyCoins);
			
				By RT1ASubtoded=By.xpath("//table/tbody/tr[2]/td["+a+"]/div[3]/div[2]");
				String RT1ASubtodedUI=driver.findElement(RT1ASubtoded).getText();
				System.out.println("RT1A CopayAmountUI:"+PharmacyCopayUI);
				System.out.println("RT1A CoinsAmountUI:"+PharmacyCoinsUI);
				System.out.println("RT1A subtodedUI:"+RT1ASubtodedUI);
				System.out.println("RT1A subtoded:"+RT1ADeduc);
				softassert.assertEquals(RT1ASubtodedUI.contains("No"), RT1ADeduc.contains("false"),"subjec to deductibles is wrong");
//				softassert.assertTrue(PharmacyCopay.contains(PharmacyCopayUI), "RT1A Copay Amount is not matching with the webservice");
//				softassert.assertTrue(PharmacyCoinsUI.contains(PharmacyCoins),"RT1A Coins Amount is not matching with the webservice");
				
			}
			else if(PharmacyBenefit.equals("RTL2"))
			{
				System.out.println("Preferred Brand:");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				By PharmacyNameXpathUI=By.xpath("//div/div[1]/div[3]/table/tbody/tr[3]/td["+a+"]");
				By PharmacyCopayxpathUI=By.xpath("//table/tbody/tr[3]/td["+a+"]/div[1]/div[2]");
				By PharmacyCoinsxpathUI=By.xpath("//table/tbody/tr[3]/td["+a+"]/div[2]/div[2]");
				String PharmacyNameUI=driver.findElement(PharmacyNameXpathUI).getText();
				String PharmacyCopayUI=driver.findElement(PharmacyCopayxpathUI).getText().substring(1);
				String PharmacyCoinsUI=driver.findElement(PharmacyCoinsxpathUI).getText();
				String SubtodeductiblesXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].SubjectToDeductible.isSubToDedRTL2";
				String RTL2Deduc=xt.getString(SubtodeductiblesXpath);
				System.out.println("Pharmacy Benefit Type:"+PharmacyBenefit);
				System.out.println("RTL2 CopayAmount:"+PharmacyCopay);
				System.out.println("RTL2 CoinsAmount:"+PharmacyCoins);
				By RTL2Subtoded=By.xpath("//table/tbody/tr[3]/td["+a+"]/div[3]/div[2]");
				String RTL2SubtodedUI=driver.findElement(RTL2Subtoded).getText();
				System.out.println("RTL2 CopayAmountUI:"+PharmacyCopayUI);
				System.out.println("RTL2 CoinsAmountUI:"+PharmacyCoinsUI);
				System.out.println("RTL2 subtodedUI:"+RTL2SubtodedUI);
				System.out.println("RTL2 subtoded:"+RTL2Deduc);
				softassert.assertEquals(RTL2SubtodedUI.contains("No"), RTL2Deduc.contains("false"),"subjec to deductibles is wrong");
				softassert.assertTrue(PharmacyCopay.equals(PharmacyCopayUI), "RTL2 Copay Amount is not matching with the webservice");
				softassert.assertTrue(PharmacyCoinsUI.contains(PharmacyCoins),"RTL2 Coins Amount is not matching with the webservice");
				
			}
			else if(PharmacyBenefit.equals("RTL3"))
			{
				System.out.println("Non-Preferred Brand:");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				By PharmacyNameXpathUI=By.xpath("//div/div[1]/div[3]/table/tbody/tr[4]/td["+a+"]");
				By PharmacyCopayxpathUI=By.xpath("//table/tbody/tr[4]/td["+a+"]/div[1]/div[2]");
				By PharmacyCoinsxpathUI=By.xpath("//table/tbody/tr[4]/td["+a+"]/div[2]/div[2]");
				String PharmacyNameUI=driver.findElement(PharmacyNameXpathUI).getText();
				String PharmacyCopayUI=driver.findElement(PharmacyCopayxpathUI).getText().substring(1);
				String PharmacyCoinsUI=driver.findElement(PharmacyCoinsxpathUI).getText();
				String SubtodeductiblesXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[0].SubjectToDeductible.isSubToDedRTL3";
				String RTL3Deduc=xt.getString(SubtodeductiblesXpath);
				System.out.println("Pharmacy Benefit Type:"+PharmacyBenefit);
				System.out.println("RTL3 CopayAmount:"+PharmacyCopay);
				System.out.println("RTL3 CoinsAmount:"+PharmacyCoins);
				By RTL3Subtoded=By.xpath("//table/tbody/tr[4]/td["+a+"]/div[3]/div[2]");
				String RTL3SubtodedUI=driver.findElement(RTL3Subtoded).getText();
				System.out.println("RTL3 CopayAmountUI:"+PharmacyCopayUI);
				System.out.println("RTL3 CoinsAmountUI:"+PharmacyCoinsUI);
				softassert.assertEquals(RTL3SubtodedUI.contains("No"), RTL3Deduc.contains("false"),"subjec to deductibles is wrong");
				softassert.assertTrue(PharmacyCopay.contains(PharmacyCopayUI), "RTL3 Copay Amount is not matching with the webservice");
				softassert.assertTrue(PharmacyCoinsUI.contains(PharmacyCoins),"RTL3 Coins Amount is not matching with the webservice");
				
			}
			else if(PharmacyBenefit.equals("SPCL"))
			{
				
				System.out.println("Preferred Specialty:");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				By PharmacyNameXpathUI=By.xpath("//div/div[1]/div[3]/table/tbody/tr[5]/td["+a+"]");
				By PharmacyCopayxpathUI=By.xpath("//table/tbody/tr[5]/td["+a+"]/div[1]/div[2]");
				By PharmacyCoinsxpathUI=By.xpath("//table/tbody/tr[5]/td["+a+"]/div[2]/div[2]");
				String PharmacyCopayUI=driver.findElement(PharmacyCopayxpathUI).getText().substring(1);
				String PharmacyCoinsUI=driver.findElement(PharmacyCoinsxpathUI).getText();
				String SubtodeductiblesXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member[1].EligibilityInfo.Eligibility[0].SubjectToDeductible.isSubToDedSPCL";
				String SPCLDeduc=xt.getString(SubtodeductiblesXpath);
				By SPCLSubtoded=By.xpath("//table/tbody/tr[5]/td["+a+"]/div[3]/div[2]");
				String SPCLSubtodedUI=driver.findElement(SPCLSubtoded).getText();
				softassert.assertEquals(SPCLSubtodedUI.contains("No"), SPCLDeduc.contains("false"),"subjec to deductibles is wrong");
				softassert.assertTrue(PharmacyCopay.contains(PharmacyCopayUI), "SPCL Copay Amount is not matching with the webservice");
				softassert.assertTrue(PharmacyCoinsUI.contains(PharmacyCoins),"SPCL Coins Amount is not matching with the webservice");		
				
			}
			else if(PharmacyBenefit.equals("SPC2"))
			{
				System.out.println("Non-Preferred Specialty:");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				By PharmacyNameXpathUI=By.xpath("//div/div[1]/div[3]/table/tbody/tr[6]/td["+a+"]");
				By PharmacyCopayxpathUI=By.xpath("//table/tbody/tr[6]/td["+a+"]/div[1]/div[2]");
				By PharmacyCoinsxpathUI=By.xpath("//table/tbody/tr[6]/td["+a+"]/div[2]/div[2]");
				String PharmacyNameUI=driver.findElement(PharmacyNameXpathUI).getText();
				String PharmacyCopayUI=driver.findElement(PharmacyCopayxpathUI).getText().substring(1);
				String PharmacyCoinsUI=driver.findElement(PharmacyCoinsxpathUI).getText();
				String SubtodeductiblesXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member[1].EligibilityInfo.Eligibility[0].SubjectToDeductible.isSubToDedSPC2";
				String SPC2Deduc=xt.getString(SubtodeductiblesXpath);
				By SPC2Subtoded=By.xpath("//table/tbody/tr[6]/td["+a+"]/div[3]/div[2]");
				String SPC2SubtodedUI=driver.findElement(SPC2Subtoded).getText();
				softassert.assertEquals(SPC2SubtodedUI.contains("No"), SPC2Deduc.contains("false"),"subjec to deductibles is wrong");
				softassert.assertTrue(PharmacyCopay.contains(PharmacyCopayUI), "SPC2 Copay Amount is not matching with the webservice");
				softassert.assertTrue(PharmacyCoinsUI.contains(PharmacyCoins),"SPC2 Coins Amount is not matching with the webservice");
				
			}
			softassert.assertAll();
		}
	}

	private void validateVision1(String responseXMLPath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		XmlPath xt = new XmlPath(contents);
		String VisionbenefitdetailXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member[0].EligibilityInfo.Eligibility[0].BenefitDetails.BenefitDetail.Type";
		List<org.w3c.dom.Element> ListofTags = xt.getList(VisionbenefitdetailXpath);
		int NumberofBenefitDetails = ListofTags.size();
		System.out.println(("Number of Vision Benefit Details: " +NumberofBenefitDetails));
		int a=0;
		boolean Tierd=driver.findElements(By.xpath("//th[contains(text(),'Tier 2')]")).size()>0;
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		Thread.sleep(2000);
		if(Tierd==true)
		{
			a=3;
		}
		else
		{
			a=4;
		}
		
		for(int i=1;i<=NumberofBenefitDetails;i++)
		{
			String BenefittypeXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility.BenefitDetails.BenefitDetail["+i+"].Type";
			String CopayAmountXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member[0].EligibilityInfo.Eligibility[0].BenefitDetails.BenefitDetail["+i+"].CopayAmount";
			String CoinsAmountXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member[0].EligibilityInfo.Eligibility[0].BenefitDetails.BenefitDetail["+i+"].CoinPct";
			String VisionBenefitName=xt.getString(BenefittypeXpath);
			String VisionCopayAmountString=xt.getString(CopayAmountXpath);
			
			String VisionCoinsAmountString=xt.getString(CoinsAmountXpath);
				try
				{
			
			if(VisionBenefitName.equals("HAID"))
			{
				System.out.println("Hearing:");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
//				By VisionNameXpathUI=By.xpath("//div/div[1]/div[4]/table/tbody/tr[1]/td[2]");
				By VisionCopayxpathUI=By.xpath("//table/tbody/tr[1]/td["+a+"]/span/div[1]/div[2]");
				By VisionCoinsxpathUI=By.xpath("//table/tbody/tr[1]/td["+a+"]/span/div[2]/div[2]");
				String VisionStringCopayUI=driver.findElement(VisionCopayxpathUI).getText().substring(1);
				String VisionStringCoinsUI=driver.findElement(VisionCoinsxpathUI).getText();
				String VisionCoinssubUI=VisionStringCoinsUI.substring(0, VisionStringCoinsUI.length()-1);
				int VisionCopayUI=Integer.parseInt(VisionStringCopayUI);
				int VisionCoinsUI=Integer.parseInt(VisionStringCoinsUI);
				int VisionCopayAmount=Integer.parseInt(VisionCopayAmountString);
				int VisionCoinsAmount=Integer.parseInt(VisionCoinsAmountString);
				System.out.println("HAID CopayAmount:"+VisionCopayAmount);
				System.out.println("HAID CoinsAmount:"+VisionCoinsAmount);
				System.out.println("HAID CopayAmountUI:"+VisionCopayUI);
				System.out.println("HAID CoinsAmountUI:"+VisionCoinssubUI);
				softassert.assertEquals(VisionCopayAmount,VisionCopayUI, "HAID Copay Amount is not matching with the webservice");
				softassert.assertTrue(VisionCoinsUI==VisionCoinsAmount,"HAID Coins Amount is not matching with the webservice");
				
			}else if(VisionBenefitName.equals("PVVS"))
			{
				System.out.println("Routine Eye Exam");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				By VisionCopayxpathUI=By.xpath("//div/div[1]/div[4]/table/tbody/tr[3]/td["+a+"]/span/div[1]/div[2]");
				By VisionCoinsxpathUI=By.xpath("//div/div[1]/div[4]/table/tbody/tr[3]/td["+a+"]/span/div[2]/div[2]");
				String VisionStringCopayUI=driver.findElement(VisionCopayxpathUI).getText().substring(1);
				String VisionStringCoinsUI=driver.findElement(VisionCoinsxpathUI).getText();
				String VisionCoinssubUI=VisionStringCoinsUI.substring(0, VisionStringCoinsUI.length()-1);
				int VisionCopayUI=Integer.parseInt(VisionStringCopayUI);
				int VisionCoinsUI=Integer.parseInt(VisionCoinssubUI);
				int VisionCopayAmount=Integer.parseInt(VisionCopayAmountString);
				int VisionCoinsAmount=Integer.parseInt(VisionCoinsAmountString);
				System.out.println("PVVS CopayAmount:"+VisionCopayAmount);
				System.out.println("PVVS CoinsAmount:"+VisionCoinsAmount);
				System.out.println("PVVS CopayAmountUI:"+VisionCopayUI);
				System.out.println("PVVS CoinsAmountUI:"+VisionCoinsUI);
				softassert.assertEquals(VisionCopayAmount,VisionCopayUI, "PVVS Copay Amount is not matching with the webservice");
				softassert.assertTrue(VisionCoinsAmount==VisionCoinsUI,"PVVS Coins Amount is not matching with the webservice");
				
			}else if(VisionBenefitName.equals("VSOV"))
			{
				
				System.out.println("Medical Office Visit/Eye Exam:");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				By VisionCopayxpathUI=By.xpath("//div/div[1]/div[4]/table/tbody/tr[4]/td["+a+"]/span/div[1]/div[2]");
				By VisionCoinsxpathUI=By.xpath("//div/div[1]/div[4]/table/tbody/tr[4]/td["+a+"]/span/div[2]/div[2]");
				String VisionStringCopayUI=driver.findElement(VisionCopayxpathUI).getText().substring(1);
				String VisionStringCoinsUI=driver.findElement(VisionCoinsxpathUI).getText();
				String VisionCoinssubUI=VisionStringCoinsUI.substring(0, VisionStringCoinsUI.length()-1);
				int VisionCopayUI=Integer.parseInt(VisionStringCopayUI);
				int VisionCoinsUI=Integer.parseInt(VisionCoinssubUI);
				int VisionCopayAmount=Integer.parseInt(VisionCopayAmountString);
				int VisionCoinsAmount=Integer.parseInt(VisionCoinsAmountString);
				System.out.println("VSOV CopayAmount:"+VisionCopayAmount);
				System.out.println("VSOV CoinsAmount:"+VisionCoinsAmount);
				System.out.println("VSOV CopayAmountUI:"+VisionCopayUI);
				System.out.println("VSOV CoinsAmountUI:"+VisionCoinsUI);
				softassert.assertTrue(VisionCopayAmount==VisionCopayUI, "VSOV Copay Amount is not matching with the webservice");
				softassert.assertTrue(VisionCoinsUI==VisionCoinsAmount,"VSOV Coins Amount is not matching with the webservice");
				
			}else if(VisionBenefitName.equals("VSHD")||VisionBenefitName.equals("VAHD"))
			{
				System.out.println("Glasses and Contact Lense:");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				By VisionCopayxpathUI=By.xpath("//div/div[1]/div[4]/table/tbody/tr[5]/td["+a+"]/span/div[1]/div[2]");
				By VisionCoinsxpathUI=By.xpath("//div/div[1]/div[4]/table/tbody/tr[5]/td["+a+"]/span/div[2]/div[2]");
				String VisionStringCopayUI=driver.findElement(VisionCopayxpathUI).getText().substring(1);
				String VisionStringCoinsUI=driver.findElement(VisionCoinsxpathUI).getText();
				String VisionCoinssubUI=VisionStringCoinsUI.substring(0, VisionStringCoinsUI.length()-1);
				int VisionCopayUI=Integer.parseInt(VisionStringCopayUI);
				int VisionCoinsUI=Integer.parseInt(VisionCoinssubUI);
				int VisionCopayAmount=Integer.parseInt(VisionCopayAmountString);
				int VisionCoinsAmount=Integer.parseInt(VisionCoinsAmountString);
				System.out.println("VSHD CopayAmount:"+VisionCopayAmount);
				System.out.println("VSHD CoinsAmount:"+VisionCoinsAmount);
				System.out.println("VSHD CopayAmountUI:"+VisionStringCopayUI);
				System.out.println("VSHD CoinsAmountUI:"+VisionStringCoinsUI);
				softassert.assertTrue(VisionCopayAmount==VisionCopayUI, "VSHD Copay Amount is not matching with the webservice");
				softassert.assertTrue(VisionCoinsUI==VisionCoinsAmount,"VSHD Coins Amount is not matching with the webservice");
			}
			softassert.assertAll();
		
				}
		
		
			catch(NumberFormatException e)
			{
			System.out.println(e);
			}
				
				
			}
		
		}
	



	private void validatePCPInformation(String responseXMLPath) throws IOException {
		// TODO Auto-generated method stub
String MembersfirstnameXpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Member";
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
			XmlPath xt = new XmlPath(contents);
			
			String LastNameXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[1].Members.Member[0].ProviderHistory.Provider.LastName";
			String FirstNameXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[1].Members.Member[0].ProviderHistory.Provider.FirstName";
			String LastName=xt.getString(LastNameXpath);
			String FirstName=xt.getString(FirstNameXpath);
			
			String PCP=driver.findElement(By.xpath("//div[@id=\"coveredMember\"]/div/div/div/table/tbody/tr/td[5]/span")).getText();
			System.out.println("PCP name UI:"+PCP);
				String Providername=FirstName+" "+LastName;
				System.out.println("firstname:"+FirstName);
				System.out.println("Lastname:"+LastName);
				System.out.println("Providername:"+Providername);
				Assert.assertTrue(Providername.equalsIgnoreCase(PCP), "PCP name populated incorrectly");
				}
		


	private void ValidatecobEffectivedate(String responseXMLPath, String xpathtoEffDate) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		String EffectiveDate = xt.getString(xpathtoEffDate);
		System.out.println("Effective date from Response -->" + EffectiveDate);
		driver.findElement(Expandall).click();
		WebElement COBEffectiveUI=driver.findElement(AppEffectivedatemycoverage);
		String EffectivedateinUI = COBEffectiveUI.getText();
		System.out.println("Effective date in UI -->" + EffectivedateinUI);
		String Effdatefromresponse = EffectiveDate.substring(0, 10);
		String ExpectedEffectivedate = CommonMethods.returnConverteddateformatMMDDYYYY(Effdatefromresponse);
		System.out.println("Expected Eff Date from Respone -->" + ExpectedEffectivedate);
		By COBEffecDate=By.xpath("//div[@id='cobenefitsinfo']/div/div/table/tbody/tr/td[4]");
		Thread.sleep(1000);
		String COBEffectiveDate=driver.findElement(COBEffecDate).getText();
		
		Thread.sleep(1000);
		//softassert.assertEquals(EffectivedateinUI, ExpectedEffectivedate , "Validating Effective/Term dates");
		softassert.assertEquals(ExpectedEffectivedate, COBEffectiveDate,"Validating COB menu Effective date");
		ScenarioContext.setContext(xpathtoEffDate, Effdatefromresponse);
		softassert.assertAll();
		
	}
	}

			
	














