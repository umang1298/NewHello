package com.qa.pages;


import org.openqa.selenium.NoSuchElementException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;
import com.qa.util.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.xml.XmlPath;

public class MemberRegistrationPage extends TestBase {
	//Page Factory
			By HAPLogo = By.xpath("(//a/img[1])[1]");
			 By memberID= By.xpath("//input[@id='memberId']");
			 By groupID =By.xpath("//input[@id='groupID1']");
			 By MedicaidID=By.xpath("//input[@id='medicaidID2']");
			 By lastName= By.xpath("//input[@id='lastName']");
			 By firstName=By.xpath("//input[@id='firstName']");
			 By zipCode=By.xpath("//input[@id='zipcode']");
			 By dateofBirth=By.xpath("//input[@id='dateOfBirth']");
			 By email=By.xpath("//input[@id='emailAddress']");
			 By phone=By.xpath("//input[@id='cellPhone']");
			 By pass=By.xpath("//input[@id='password1']");
			 By confirmpass=By.xpath("//input[@id='password2']");
			 By question=By.xpath("//input[@id='challengeQuestion']");
			 By answer=By.xpath("//input[@id='challengeAnswer']");
			 By EOBXpath= By.xpath("//input[@id='checkbox1'][1]//..//span[1]");
			 By Gopaperless=By.xpath("//input[@id='gopaperless'][1]//..//span[1]");
			 By verifyanswer= By.xpath("//input[@id='verifyChallengeAnswer']");
			 By AcceptPrivacy=By.xpath("//input[@id='declaration'][1]//..//span[1]");
			 By Submit= By.xpath("//button[@id='submitRegister']");
			 By alreadymember=By.xpath("//p[contains(text(),'If you canno')]");
			 By RegisComplete=By.xpath("//div[2]//div/div[2][contains(., 'Congratulations')]");
			 By PasswordField = By.xpath("//input[@name='password']");
			 By UserIdField = By.xpath("//input[@id='userid']");
			 By LoginButton = By.xpath("//span[contains(text(),'Log In')]");
			 By OKButton=By.xpath("//button[contains(text(),'OK')]");
			 By Password = By.xpath("//input[@name='password']");
			 By UserId = By.xpath("//input[@id='userid']");
			 By Login = By.xpath("//span[contains(text(),'Log In')]");
			 By HAPlogo = By.xpath("(//a/img[1])[1]");
			 By gaurdian=By.xpath("//input[@id='acno']");
			 By lnameGuardian=By.xpath("//input[@id='guardianLastName']");
			 By fnameGuardian=By.xpath("//input[@id='guardianFirstName']");
			 By relationship=By.xpath("//select[@id='select-picker']");
			 By phonenumber=By.xpath("//input[@id='phoneNumber']");
			 By MedicaidRadio=By.xpath("//div[@id=\"yourinformationdiv\"]/div[2]/div[2]/div[2]/label");
			//input[@id='guardianLastName']
			 SoftAssert softAssert = new SoftAssert();
	public MemberRegistrationPage(WebDriver driver) {
		
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
		
		}
	
	public void Userinformation(String IDNumber,String GroupID,String LastName,
			String FirstName,String Zipcode,String DOB,String password) throws InterruptedException
{
		
		driver.findElement(memberID).sendKeys(IDNumber);
		driver.findElement(groupID).sendKeys(GroupID);		
		driver.findElement(lastName).sendKeys(LastName);	
		driver.findElement(firstName).sendKeys(FirstName);
		driver.findElement(zipCode).sendKeys(Zipcode);
		WebElement dateof=driver.findElement(dateofBirth);
		dateof.click();
		dateof.clear();
		dateof.sendKeys(DOB);
		Thread.sleep(3000);
		
		driver.findElement(pass).click();
		driver.findElement(pass).sendKeys(password);
		driver.findElement(confirmpass).sendKeys(password);
		driver.findElement(question).sendKeys("Q");
		driver.findElement(answer).sendKeys("A");
		driver.findElement(verifyanswer).sendKeys("A");
		Thread.sleep(2000);
		driver.findElement(AcceptPrivacy).click();
		Thread.sleep(2000);
		driver.findElement(Submit).click();
		Thread.sleep(3000);
		verifymembercreatedsuccessful();
		
			
}
	public void verifymembercreatedsuccessful() throws NoSuchElementException
	{
	
		Boolean Congratsmessage=driver.findElements(RegisComplete).size()>0;
		
		if(Congratsmessage==true)
		{
			System.out.println("Congratulations! Your registration is complete.");
			driver.close();
		}
	else
	{
		System.out.println("You are already a registered member.");
		WebElement okbutton= driver.findElement(OKButton);
		okbutton.click();
		softAssert.fail("You are already a registered member.");
	driver.close();
	}
		softAssert.assertAll();
	}
	
/*	public void getnumberofcountfromexcel(int ExcelRowCount)
	{
		Xls_Reader Reader=new Xls_Reader(System.getProperty("user.dir") + File.separator + "Excel_Data_Sheet/MemberRegistration.xlsx");
		String sheetName="Sheet1";
		
		int RowCount=Reader.getRowCount(sheetName);
		
		System.out.println("Spreadsheet contains " +RowCount+ " of Rows");
		ScenarioContext.setContext(ExcelRowCount, RowCount);
		
	}

	public void fetchfeilddetailsfromexcel(int RowCount) throws InterruptedException
	{
		Xls_Reader Reader=new Xls_Reader(System.getProperty("user.dir") + File.separator + "Excel_Data_Sheet/MemberRegistration.xlsx");
		String sheetName="Sheet1";
		int RowCountVal=(int) ScenarioContext.getContext(RowCount);
		
	//	int RowNumber=2;
		for(int RowNumber=2;RowNumber<=RowCountVal;RowNumber++)
		{
		String IDnum=Reader.getCellData(sheetName, "ID Number", RowNumber);
	    WebElement IDnumber=driver.findElement(memberID);
	    IDnumber.clear();
	    IDnumber.sendKeys(IDnum);
	   // System.out.println(IDnum);
		
		String GroupId=Reader.getCellData(sheetName, "Group ID", RowNumber);
		WebElement groupid=driver.findElement(groupID);
		groupid.clear();
		groupid.sendKeys(GroupId);
		String Lname=Reader.getCellData(sheetName, "LAST_NAME", RowNumber);
		driver.findElement(lastName).sendKeys(Lname);
		String Fname=Reader.getCellData(sheetName, "FIRST_NAME", RowNumber);
		driver.findElement(firstName).sendKeys(Fname);
		String Zipnum=Reader.getCellData(sheetName, "ZIP", RowNumber);
		driver.findElement(zipCode).sendKeys(Zipnum);
		String Date=Reader.getCellData(sheetName, "DOB", RowNumber);
		WebElement dateof=driver.findElement(dateofBirth);
		dateof.click();
		dateof.clear();
		dateof.sendKeys(Date);
		Thread.sleep(2000);
		String pwd=Reader.getCellData(sheetName, "Password", RowNumber);
		Thread.sleep(2000);
		driver.findElement(pass).click();
		driver.findElement(pass).sendKeys(pwd);
		driver.findElement(confirmpass).sendKeys(pwd);
		driver.findElement(question).sendKeys("Q");
		driver.findElement(answer).sendKeys("A");
		driver.findElement(verifyanswer).sendKeys("A");
		Thread.sleep(2000);
		driver.findElement(AcceptPrivacy).click();
		Thread.sleep(2000);
		driver.findElement(Submit).click();
		Thread.sleep(4000);
		String pagesource=driver.getPageSource();
		
		if(pagesource.contains("Congratulations!"))
		{
			System.out.println("Congratulations! Your registration is complete.");
			driver.navigate().to("https://sportal.hap.org");
			 String username=Reader.getCellData(sheetName, "MEME_RECORD_NO", RowNumber);
			 driver.findElement(UserId).sendKeys(username);
				driver.findElement(Password).sendKeys(pwd);
				driver.findElement(Login).click();
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				WebElement logo = driver.findElement(HAPLogo);
				Thread.sleep(4000);
				boolean imagePresent = logo.isDisplayed();
				Assert.assertEquals(imagePresent, true);
		
			//driver.close();
			
		}
		else
		{
			System.out.println("You are already a registered member.");
			WebElement okbutton= driver.findElement(OKButton);
			okbutton.click();
		}
		driver.navigate().to("https://sportal.hap.org/membernew/memberportal/ora/public/displayMemberRegistration");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		}
		
		
		}*/

	public void membercreation(String responseXMLPath, String memrecordnumber, String password) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
String xpathtoMembers = "Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member";
String Age="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member[0].Age";
		List<org.w3c.dom.Element> listOfMembers = xt.getList(xpathtoMembers);
		int CountofMembers = listOfMembers.size();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		for(int i=0;i<CountofMembers;i++) { 
			String xpathtomemberrecnumber = "Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member["+i+"].RecordNumber";
			String MemberRecordNumber = xt.getString(xpathtomemberrecnumber);
			System.out.println("MemberRecordNumber for " +i+ " Member -->" +MemberRecordNumber);
			if(MemberRecordNumber.equals(memrecordnumber)) {
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		String MedicaidXpath= "Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member["+i+"].MedicaidNumber";
		String IDNumberXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member["+i+"].MemberId";
		String groupIDXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member["+i+"].EligibilityInfo.Eligibility[0].GroupId";
		String subgroupIDXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member["+i+"].EligibilityInfo.Eligibility[0].SubgroupId";
		String LastnameXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.LastName";
		String FirstnameXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.FirstName";
		String zipcodeXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member["+i+"].Addresses.Address.Zip";
		String DOBXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member["+i+"].BirthDate";
//		String EmailXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member[0].Addresses.Address.Email";
		String CellphoneXpath="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member["+i+"].Addresses.Address.Phone";
		
		String IDnumber=xt.getString(IDNumberXpath);
		String GroupID=xt.getString(groupIDXpath);
		String subgroupID=xt.getString(subgroupIDXpath);
		String Lastname=xt.getString(LastnameXpath);
		String Firstname=xt.getString(FirstnameXpath);
		String Zipcode=xt.getString(zipcodeXpath);
		String DOB=xt.getString(DOBXpath);
		String Cellphone=xt.getString(CellphoneXpath);
		String Medicaid=xt.getString(MedicaidXpath);
		
		WebElement id=	driver.findElement(memberID);
		
		String Groupid=GroupID+subgroupID;
		if(Medicaid.isEmpty())
		{
		driver.findElement(groupID).sendKeys(Groupid);	
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(EOBXpath).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(Gopaperless).click();
		
		}
		else
		{
			driver.findElement(MedicaidRadio).click();
			Thread.sleep(2000);
			driver.findElement(MedicaidID).sendKeys(Medicaid);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			driver.findElement(Gopaperless).click();
		}
		id.sendKeys(IDnumber);
		driver.findElement(lastName).sendKeys(Lastname);	
		driver.findElement(firstName).sendKeys(Firstname);
		driver.findElement(zipCode).sendKeys(Zipcode);
		String DOBconversion=CommonMethods.returnConverteddateformatMMDDYYYY(DOB);
		WebElement dateof=driver.findElement(dateofBirth);
		dateof.click();
		dateof.clear();
		dateof.sendKeys(DOBconversion);
		driver.findElement(email).sendKeys("none@hap.org");
		driver.findElement(phone).sendKeys(Cellphone);
		
		}
	}
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		driver.findElement(pass).click();
		driver.findElement(pass).sendKeys(password);
		driver.findElement(confirmpass).sendKeys(password);
		driver.findElement(question).sendKeys("Q");
		driver.findElement(answer).sendKeys("A");
		driver.findElement(verifyanswer).sendKeys("A");
		Thread.sleep(2000);

		String SubsAge=xt.getString(Age);
		double SubscriberAge = Double.parseDouble(SubsAge);
		if(SubscriberAge<=18)
		{
			driver.findElement(gaurdian).click();
			driver.findElement(lnameGuardian).sendKeys("Guardianlastname");
			driver.findElement(fnameGuardian).sendKeys("Guardianfirstname");
			Select drop1=new Select(driver.findElement(relationship));
			drop1.selectByIndex(2);
			driver.findElement(phonenumber).sendKeys("3456712345");
			driver.findElement(AcceptPrivacy).click();
			Thread.sleep(2000);
			driver.findElement(Submit).click();
			Thread.sleep(3000);
//			driver.close();
			verifymembercreatedsuccessful();
		}
		else
		{
			
		driver.findElement(AcceptPrivacy).click();
		Thread.sleep(2000);
		driver.findElement(Submit).click();
		Thread.sleep(3000);
//		driver.close();
		verifymembercreatedsuccessful();
		}
}
	}
	
