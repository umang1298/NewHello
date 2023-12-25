package com.qa.pages;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import io.cucumber.java.Scenario;
import io.restassured.path.xml.XmlPath;

public class MyClaimsPage extends TestBase{

	public MyClaimsPage(WebDriver driver) {

		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	By Membername= By.xpath("//*[@id='claimsForm']/div[2]/div/div[2]/label");
	By MemberDropdown=By.id("member-picker");
	By dualmember=By.id("member-picker");
	By claimsearchresultcount=By.xpath("//*[@id='claimDetailForm']/div/div[1]/div[2]/div[4]/div[1]/span[1]");
	By FromDateofService= By.xpath("//input[@id='frompicker']");
	By ClaimResultsCount=By.xpath("//tr//td[3]//a//span");
	By Searchbutton=By.xpath("//button[@id='ref-search-button']");
	By ListofClaims=By.xpath("//h2[contains(text(),'List of Claims')]");
	By FirstResultclaimnumber = By.xpath("//tr[1]//td[3]//a//span");
	By SelectEOB=By.xpath("//div[contains(text(),'EOB')]");
	By EOBDesc=By.xpath("//div[@class='th-inner sortable both asc']");
	By EOBYes=By.xpath("//a[contains(text(),'Yes')]");
	By ClaimPage=By.xpath("//h1[contains(text(),'Claim Details')]");
	By BackButton=By.xpath("//button[contains(text(),'Back')]");
	By ServiceDetail= By.xpath("//h4/span[contains(text(),'Service Detail ')]");
	By SearchText=By.xpath("//span[@class='hap-sub-headings']");
	By HIPPAmessage13to18=By.xpath("//*[@id='page-content-wrapper']/div/div/div/div/div[3]/div[1]/div/span[3]");
	By HIPPAmessage18=By.xpath("//div[1]/div/span[3]");
	By ExplanationLink=By.xpath("//span[contains(text(),'Explanations')]");
	By EOBCount=By.xpath("//tbody/tr/td[4]");
	

	public void clickonsearchforgivenperiod(String Memberfirst,String Date) throws InterruptedException
	{
		
		
		Boolean isPresent = driver.findElements(MemberDropdown).size() > 0;
		
		if(isPresent==true)
		{
			Select drop1=new Select(driver.findElement(MemberDropdown));
			drop1.selectByIndex(2);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			String membername= drop1.getOptions().get(2).getText();
			int length= membername.length();
			System.out.println("Member name " +membername);
			String memberfirstname=membername.substring(membername.indexOf(",") + 2,length);
			System.out.println("First Name " +memberfirstname);
			ScenarioContext.setContext(Memberfirst, memberfirstname);
			WebElement Dateofservice=driver.findElement(FromDateofService);
			Dateofservice.clear();
			Dateofservice.sendKeys(Date);
			WebElement SearchButn = driver.findElement(Searchbutton);
			js.executeScript("arguments[0].click();", SearchButn);
			driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			Thread.sleep(2000);
		}
		else
		{
			
			System.out.println("Member type is without dropdown");
			String membername=driver.findElement(Membername).getText();
			int length= membername.length();
			System.out.println("Member name " +membername);
			String memberfirstname=membername.substring(membername.indexOf(",") + 2,length);
			System.out.println("First Name " +memberfirstname);
			ScenarioContext.setContext(Memberfirst, memberfirstname);
			WebElement Dateofservice=driver.findElement(FromDateofService);
			Dateofservice.clear();
			Dateofservice.sendKeys(Date);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			WebElement SearchButn = driver.findElement(Searchbutton);
			js.executeScript("arguments[0].click();", SearchButn);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
			Thread.sleep(3000);
		}

	}
	public void validateDependantageRestriction(String responseXMLPath, String Memberfirst,String Claimspresentvalue) throws IOException, ParseException, InterruptedException
	{

		String Memberfirstname=(String) ScenarioContext.getContext(Memberfirst);
		String MembersfirstnameXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.FirstName";
			
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
				
		List<org.w3c.dom.Element> ListofTags = xt.getList(MembersfirstnameXpath);
		int NumberofMembers = ListofTags.size();
		List<String> listoffirstname=new ArrayList<String>();
		System.out.println(NumberofMembers);
		int a = 0;
		for (int x = 1; x <= NumberofMembers; x++)
		{
			String MembersnameXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[" + x +"].FirstName";
			String memberfirstname= xt.getString(MembersnameXpath);
System.out.println("name1:"+memberfirstname);
System.out.println("name2:"+Memberfirstname);
			listoffirstname.add(memberfirstname);
			if(memberfirstname.equalsIgnoreCase(Memberfirstname))
			{
				a=x;
				System.out.println("Number: " +a);
			}
	
		}
		String LastNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+ a +"].LastName";
		String FirstNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+ a +"].FirstName";
		String BirthdateXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[" + a +"].BirthDate";
		String SubscriberFirstNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.FirstName";
		String AgeXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[" + a +"].Age";
		String SuffixXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[" + a +"].Suffix";
		String lname=xt.getString(LastNameXpath);
		String fname=xt.getString(FirstNameXpath);
		System.out.println(FirstNameXpath);
		System.out.println("Member Infor" +fname);
		System.out.println("Member Infor" +lname);
		String BirthDate=xt.getString(BirthdateXpath);
		String SubscriberFirstName=xt.getString(SubscriberFirstNameXpath);
		String Age=xt.getString(AgeXpath);
		String Suffix=xt.getString(SuffixXpath);
		System.out.println("Age from Webservice:"+Age);
		System.out.println("SubscriberFirstName:" +SubscriberFirstName);
		int age=Integer.parseInt(Age);
		int MemberSuffix=Integer.parseInt(Suffix);
		System.out.println("MemberSuffix: "+MemberSuffix);
		
		if(MemberSuffix==0)
		{
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(Constants.Page_Load_TimeOut,TimeUnit.SECONDS);
			boolean claimspresent = driver.findElements(FirstResultclaimnumber).size() >0;
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			if(claimspresent==true)
			{
			System.out.println("Show Claims");
			
			}
			else
			{
			
				System.out.println("Claims not present for specified dates... Please try with different dates");
				System.out.println("Caims" +claimspresent);
				ScenarioContext.setContext(Claimspresentvalue, claimspresent);
				//Assert.fail("Claims not present for specified dates... Please try with different dates");
			}
		}
		else
		{
			Boolean isPresent = driver.findElements(MemberDropdown).size() > 0;
			
			if(isPresent==true)
			{
				if(age<13)
				{
			System.out.println("ShowClaims for age below 13");		}
				else if(age>13 && age<18)
		{
			System.out.println("Restricted with text");
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			String expectedHIPPA13to18="dependents between the ages of 13 and 18";
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			String HIPPA13to18=driver.findElement(HIPPAmessage13to18).getText();
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			softassert.assertTrue(HIPPA13to18.contains(expectedHIPPA13to18), "HIPPA message is not displayed as expected");
			softassert.assertAll();
			System.out.println(HIPPA13to18);
						
		}
		else
		{
				String expectedHIPPA18="dependents under the age of 18";
				Thread.sleep(2000);
				String HIPPA18=driver.findElement(HIPPAmessage18).getText();
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				softassert.assertTrue(HIPPA18.contains(expectedHIPPA18), "HIPPA message is not displayed as expected");
				System.out.println(HIPPA18);
				boolean claimspresent = driver.findElements(FirstResultclaimnumber).size() >0;
				System.out.println("Caims" +claimspresent);
				ScenarioContext.setContext(Claimspresentvalue, claimspresent);
				
				//Assert.fail("Dependent age is above 18. Claims should not display. Hence stopped the Executions");
		}
			softassert.assertAll();
			}
					else
			{
				System.out.println("show claims");
			}
		}
						
		softassert.assertAll();
	}
	
	public void clickonEOBLink(String responseXMLPath, String memberName,String Claimspresentvalue) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Boolean Claimsavailable=(Boolean) ScenarioContext.getContext(Claimspresentvalue);
		System.out.println("Caimsavailable:"+Claimsavailable);
		if(Claimsavailable==null)
		{
		
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		String Memberfirstname=(String) ScenarioContext.getContext(memberName);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		String MembersfirstnameXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.FirstName";
			
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);		
		List<org.w3c.dom.Element> ListofTags = xt.getList(MembersfirstnameXpath);
		int NumberofMembers = ListofTags.size();
		List<String> listoffirstname=new ArrayList<String>();
		int a = 0;
		for (int x = 1; x <= NumberofMembers; x++)
		{
			String MembersnameXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[" + x +"].FirstName";
			String memberfirstname= xt.getString(MembersnameXpath);

			listoffirstname.add(memberfirstname);
			if(memberfirstname.equalsIgnoreCase(Memberfirstname))
			{
				a=x;
				System.out.println("Number: " +a);
			}
		}
		String MedicaidXpath= "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+ a +"].MedicaidNumber";
		String Medicaidnumber=xt.getString(MedicaidXpath);
		if(Medicaidnumber.isEmpty())
		{
			
		driver.findElement(SelectEOB).click();
		List<WebElement> EOBcount=driver.findElements(EOBCount);
		int EOByesnoCount =EOBcount.size();
		try
		{
		for(int i=1;i<=EOByesnoCount;i++)
		{
			System.out.println("EOBCount:" +EOByesnoCount);
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			String xpath="//table[@id='claimSearchResult']/tbody/tr["+i+"]/td[4]";
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			String EOBValue =driver.findElement(By.xpath(xpath)).getText();
			System.out.println("EOBValue: " +EOBValue);
			
		if(EOBValue.equals("Yes"))
		{
			
		System.out.println("ifloop"+i);
		String EOBYesxpath="//table[@id='claimSearchResult']/tbody/tr["+i+"]/td[4]/a";
		
		WebElement EOBYESLink = driver.findElement(By.xpath(EOBYesxpath));

		js.executeScript("arguments[0].click();", EOBYESLink);
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		//Thread.sleep(8000);
		
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.urlContains("EOBPDF"));
		System.out.println("Title:"+driver.getCurrentUrl());
		Assert.assertTrue("EOB document is not loaded",driver.getCurrentUrl().contains("getEOBPDF"));
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		driver.findElement(By.xpath("//tbody/tr["+i+"]/td[3]/a/span")).click();
		wait.until(ExpectedConditions.elementToBeClickable(ExplanationLink));
		Assert.assertTrue("Link is not present", driver.findElements(ExplanationLink).size() >0);
		
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(BackButton).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		
		
		}
		
		else
		{
		
		System.out.println("elseloop"+i);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//tbody/tr["+i+"]/td[3]/a/span")).click();
		//driver.findElement(FirstResultclaimnumber).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		Assert.assertTrue("Explanation Link should not present",driver.findElements(ExplanationLink).isEmpty());
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(BackButton).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		break;
		}
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		}
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println(e);
		}
		}
		else
		{
			System.out.println("Since this is the Medicaid member EOB is not available");
		}
		}
		else
		{
			//Nothing to do when there is no claims
		}
	}
	public void getClaimNumber(String ClaimNumber,String Claimspresentvalue)
	{
		Boolean Claimsavailable=(Boolean) ScenarioContext.getContext(Claimspresentvalue);
		System.out.println("Caimsavailable:"+Claimsavailable);
		if(Claimsavailable==null)
		{
		
		boolean claimspresent = driver.findElements(FirstResultclaimnumber).size() >0;
		if(claimspresent==true){
				String claimnumber = driver.findElement(FirstResultclaimnumber).getText();
				String Claimnum = claimnumber.replace("-", "");
				System.out.println("This is the First ClaimNumber from search results --> " + Claimnum );
				ScenarioContext.setContext(ClaimNumber, Claimnum);
				}
				else
				{
					System.out.println("There are no claims found for the specified dates ");
		System.out.println("Caims"+claimspresent);
					ScenarioContext.setContext(Claimspresentvalue, claimspresent);
						//Assert.fail("There are no claims found for the specified dates.. Please try with Different Dates");
				}
		}
		else
		{
			//nothing to validate
		}
				}
	public void clickonFirstClaimNumber(String Claimnumber,String Claimspresentvalue) {
		// TODO Auto-generated method stub
		Boolean Claimsavailable=(Boolean) ScenarioContext.getContext(Claimspresentvalue);
		System.out.println("Caimsavailable:"+Claimsavailable);
		if(Claimsavailable==null)
		{
			
		boolean claimspresent = driver.findElements(FirstResultclaimnumber).size() >0;
		if(claimspresent==true){
	   
		WebElement Claimnumberlink= driver.findElement(FirstResultclaimnumber);
		js.executeScript("arguments[0].click();", Claimnumberlink);
	//	WebElement Claimspage= driver.findElement(ClaimPage);
		String expectedUrl = "getClaimDetail";

			
		try{
		  softassert.assertTrue((driver.getCurrentUrl().contains(expectedUrl)));
		  System.out.println("Navigated to Claims Details page");
		}
		catch(Throwable pageNavigationError){
		  System.out.println("Didn't navigate to Claim Details Page");
		}
		}
		else 
		{
			
			System.out.println("There are no claims found for the specified dates");
			Assert.assertFalse("fail", claimspresent);
			
		}
		
	driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);	
	}
		else
		{
			//Nothing to do when no Claims
		}
		}
	public void getmemRecordNumber(String responseXMLPath, String memrecordnumber) throws IOException {
		// TODO Auto-generated method stub
		
		String SubscriberXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber";
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		List<org.w3c.dom.Element> ListofTags = xt.getList(SubscriberXpath);
		int Numberofsubscribers = ListofTags.size();
		System.out.println("Number of Subscribers:" +Numberofsubscribers);
		if(Numberofsubscribers>1)
			{
			String MembRecordnumberXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[1].MemberRecordNumber";
			String memRecordNumber=xt.getString(MembRecordnumberXpath);
			System.out.println("Member Record number---->" +memRecordNumber);
			ScenarioContext.setContext(memrecordnumber, memRecordNumber);
			}
		else
		{
			String MembRecordnumberXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.MemberRecordNumber";
			String memRecordNumber=xt.getString(MembRecordnumberXpath);
			System.out.println("Member Record number---->" +memRecordNumber);
			ScenarioContext.setContext(memrecordnumber, memRecordNumber);
		}
				
	}
	
	public void getmemRecordNumberforMemberID(String memberID, String responseXMLPath, String memrecordnumber) throws IOException {
		// TODO Auto-generated method stub
		String MemberXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member";
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		List<org.w3c.dom.Element> ListofTags = xt.getList(MemberXpath);
		int NumberofMembers = ListofTags.size();
		System.out.println("Number of Members:" +NumberofMembers);
		for(int i=0; i<NumberofMembers; i++) {
			String MemberIDXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member["+i+"].MemberId";
			String MemberID=xt.getString(MemberIDXpath);
			if (MemberID==memberID) {
				String MemberRecordNumberXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member["+i+"].RecordNumber";
				String MemberRecordNumber = xt.getString(MemberRecordNumberXpath);
				System.out.println("Member Record number---->" +MemberRecordNumber);
				ScenarioContext.setContext(memrecordnumber, MemberRecordNumber);
			}
		}
	}
	
	public void validationonClaimSearchResultscount(String responseXMLPath) throws IOException {
		// TODO Auto-generated method stub
	//	String subcriberXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber";
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);

		
		XmlPath xt = new XmlPath(contents);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		String Claimsummaryxpath="Envelope.Body.GetClaimSummaryResponse.GetClaimSummaryResult.ResponseData.ClaimSummary";
		List<org.w3c.dom.Element> ListofTags = xt.getList(Claimsummaryxpath);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		int NoofClaims = ListofTags.size();
		//List<String> listoffirstname=new ArrayList<String>();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
	System.out.println("No of claims from Claim summary: "+NoofClaims);
	Select drop1=new Select(driver.findElement(dualmember));
	List<WebElement> options=drop1.getOptions();
	int dropvaluecount=options.size();
	System.out.println("count:" +dropvaluecount);
	
	for(int i=2;i<dropvaluecount;i++)
	{
		Select drop2=new Select(driver.findElement(dualmember));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		drop2.selectByIndex(i);
		String membername=drop2.getOptions().get(i).getText();
		System.out.println("Options:"+membername);
		
		WebElement Dateofservice=driver.findElement(FromDateofService);
		Dateofservice.clear();
		Dateofservice.sendKeys("01/01/2018");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebElement SearchButn = driver.findElement(Searchbutton);
		js.executeScript("arguments[0].click();", SearchButn);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		List<WebElement> Claimcount=driver.findElements(ClaimResultsCount);
	//	int ClaimResultcount =Claimcount.size();
		String resultcount=driver.findElement(claimsearchresultcount).getText();
		System.out.println("test"+resultcount);
		String fetchresult=resultcount.substring(19,21);
		
		System.out.println("substring:" +fetchresult);
		int ClaimResultcount=Integer.parseInt(fetchresult);
		System.out.println("No of Claims in ClaimSearchResult UI: "+ClaimResultcount);
		softassert.assertTrue(ClaimResultcount==NoofClaims,"Claim search result count is not matching");
		
	}
	softassert.assertAll();
	}
	
	
	
		

	
	
}