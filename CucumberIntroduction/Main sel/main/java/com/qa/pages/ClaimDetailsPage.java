package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import io.restassured.path.xml.XmlPath;

public class ClaimDetailsPage  extends TestBase{

	public ClaimDetailsPage(WebDriver driver) {

		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	By Membername= By.xpath("//*[@id=\"claimsForm\"]/div[2]/div/div[2]/label");
	By FromDateofService= By.xpath("//input[@id='frompicker']");
	By Searchbutton=By.xpath("//button[@id='ref-search-button']");
	By ListofClaims=By.xpath("//h2[contains(text(),'List of Claims')]");
	By FirstResultclaimnumber = By.xpath("//tr[1]//td[3]//a//span");
	By SelectEOB=By.xpath("//div[contains(text(),'EOB')]");
	By EOBDesc=By.xpath("//div[@class='th-inner sortable both asc']");
	By EOBYes=By.xpath("//a[contains(text(),'Yes')]");
	By ClaimPage=By.xpath("//h1[contains(text(),'Claim Details')]");
	By BackButton=By.xpath("//button[contains(text(),'Back')]");
	By ServiceDetail= By.xpath("//h4/span[contains(text(),'Service Detail ')]");
	By AmountProviderMayBillYou=By.xpath("//*[@id='lineInfo1']/div/div[7]/div[1]/div[2]/span[1]");
	By DOSXpth=By.xpath("//*[@id='claiminfo']/div/div/div[2]/div[2]/span");
	By ProviderName=By.xpath("//*[@id='claiminfo']/div/div/div[1]/div[2]/span");
	By MemberNameXpathUI=By.xpath("//div[@id='memberinfo']/div/div[1]/div[1]/div[2]/span");
	By MemberIDXpathUI=By.xpath("//div[@id='memberinfo']/div/div[1]/div[2]/div[2]/span");
	By GroupNameXpathUI=By.xpath("//div[@id='memberinfo']/div/div[2]/div[1]/div[2]/span");
	By GroupIDXpathUI=By.xpath("//*[@id='memberinfo']/div/div[2]/div[2]/div[2]/span");
	By GroupIDmedXpathUI=By.xpath("//*[@id='memberinfo']/div/div[3]/div/div[2]/span");
	By MedicaidXpathUI=By.xpath("//*[@id='memberinfo']/div/div[2]/div[2]/div[2]/span");
	By Expandall=By.xpath("//span[@class='hap-links expand-collapse-btn expand']");
	public void ClaimDetailspageValidations(String responseXMLPath,String Claimspresentvalue) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Boolean Claimsavailable=(Boolean) ScenarioContext.getContext(Claimspresentvalue);
		System.out.println("Caimsavailable:"+Claimsavailable);
		if(Claimsavailable==null)
		{
		String xpath = "Envelope.Body.GetLineItemResponse.GetLineItemResult.ResponseData.LineItem";
			
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(Expandall));
		driver.findElement(Expandall).click();
		
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(AmountProviderMayBillYou));
		List<WebElement> servicecount=driver.findElements(ServiceDetail);
		int servicedetailcount =servicecount.size();
		List<org.w3c.dom.Element> ListofTags = xt.getList(xpath);
		int NumberofLineItem = ListofTags.size();
		System.out.println(("Number of line Item: " +NumberofLineItem));
		System.out.println("Number of Service Detail present: "+servicedetailcount);
		
		
		
		
		for(int i=0,j=1;i<=NumberofLineItem;i++,j++)
		{
			String BilledAmountXpath="Envelope.Body.GetLineItemResponse.GetLineItemResult.ResponseData.LineItem["+i+"].BilledAmount";
			String DisallowAmountXpath="Envelope.Body.GetLineItemResponse.GetLineItemResult.ResponseData.LineItem["+i+"].DisallowAmount";
			String AllowedAmountXpath="Envelope.Body.GetLineItemResponse.GetLineItemResult.ResponseData.LineItem["+i+"].AllowedAmount";
			String PaidAmountXpath ="Envelope.Body.GetLineItemResponse.GetLineItemResult.ResponseData.LineItem["+i+"].PaidAmount";
			String CopayAmountXpath="Envelope.Body.GetLineItemResponse.GetLineItemResult.ResponseData.LineItem["+ i+"].CopayAmount";
			String CoinsuranceXpath="Envelope.Body.GetLineItemResponse.GetLineItemResult.ResponseData.LineItem["+ i + "].CoinsuranceAmount";
			String DeductibleAmountXpath="Envelope.Body.GetLineItemResponse.GetLineItemResult.ResponseData.LineItem["+ i + "].DeductibleAmount";
			String MemberDisallowAmountXpath="Envelope.Body.GetLineItemResponse.GetLineItemResult.ResponseData.LineItem["+ i + "].MemberDisallowAmount";
		
		try
		{
			String BilledAmount= xt.getString(BilledAmountXpath);
			System.out.println("Billedamount: " +BilledAmount);
		
			String DisallowAmount= xt.getString(DisallowAmountXpath);
			System.out.println("Disallow: " +DisallowAmount);
		
			String AllowedAmount= xt.getString(AllowedAmountXpath);
			System.out.println("AllowedAmount: " +AllowedAmount);
		
			String PaidAmount= xt.getString(PaidAmountXpath);
			System.out.println("PaidAmount: " +PaidAmount);
		
			String DeductibleAmount= xt.getString(DeductibleAmountXpath);
			double Deductibleamount = Double.parseDouble(DeductibleAmount);
			System.out.println("Double Deductible: " +Deductibleamount);
		
			String MemberDisallowAmount= xt.getString(MemberDisallowAmountXpath);
			double memdisallowamount;
			if(MemberDisallowAmount.isEmpty())
			{
				memdisallowamount=0;
				System.out.println("Double MemberDisallowAmount: " +memdisallowamount);
			}
			else
			{
				memdisallowamount =Double.parseDouble(MemberDisallowAmount);
				System.out.println("Double MemberDisallowAmount: " +memdisallowamount);
			}		
			String CopayAmount=xt.getString(CopayAmountXpath);
			double copayamount= Double.parseDouble(CopayAmount);
			System.out.println("Double copayamount: " +copayamount);
			
			String Coinsurance=xt.getString(CoinsuranceXpath);
			double coninsuranceamount= Double.parseDouble(Coinsurance);
			System.out.println("Double coinsamount: " +coninsuranceamount);
		
			double SumAmountProvidermaybill=Deductibleamount+memdisallowamount + copayamount + coninsuranceamount;
			System.out.println("SumAmountProvidermaybill: " +SumAmountProvidermaybill);

		
			String AmountBilledbyProviderUI=driver.findElement(By.xpath("//div[@id='lineInfo"+j+"']//div[3]//div[1]//div[2]//span")).getText();
			System.out.println("AmountBilledbyProviderUI: " +AmountBilledbyProviderUI);
			
			String PlanDiscountUI=driver.findElement(By.xpath("//div[@id='lineInfo"+j+"']//div[4]//div[1]//div[2]//span")).getText();
			System.out.println("PlanDiscountUI" +PlanDiscountUI);
			String AllowedAmountUI=driver.findElement(By.xpath("//div[@id='lineInfo"+j+"']//div[5]//div[1]//div[2]//span")).getText();
			System.out.println("AllowedAmountUI" +AllowedAmountUI);
			String AmountPaidbyPlanUI=driver.findElement(By.xpath("//div[@id='lineInfo"+j+"']//div[6]//div[1]//div[2]//span")).getText();
			System.out.println("AmountPaidbyPlanUI" +AmountPaidbyPlanUI);
			String AmountProviderMayBillYouUI=driver.findElement(By.xpath("//div[@id='lineInfo"+j+"']//div[7]//div[1]//div[2]//span")).getText();
			System.out.println("AmountProviderMayBillYouUI" +AmountProviderMayBillYouUI);
			String amount=AmountProviderMayBillYouUI.substring(1);
			Double ProviderMayBillYouUI= Double.parseDouble(amount);
			System.out.println("amount:" +ProviderMayBillYouUI);
			
			

			softassert.assertTrue(AmountBilledbyProviderUI.contains(BilledAmount),"AmountBilledbyProvider value is not matching with webservice");
			softassert.assertTrue(PlanDiscountUI.contains(DisallowAmount),"PlanDiscount value is not matching with webservice");
			softassert.assertTrue(AllowedAmountUI.contains(AllowedAmount),"AllowedAmount value is not matching with webservice");
			softassert.assertTrue(AmountPaidbyPlanUI.contains(PaidAmount),"AmountBilledbyProvider value is not matching with webservice");
			softassert.assertEquals(SumAmountProvidermaybill,ProviderMayBillYouUI,"Providermaybill calculation is wrong");
		
		}
		
		catch(NumberFormatException e)
		{
			System.out.println(e);
		
		}
		
		}
		softassert.assertTrue(NumberofLineItem == servicedetailcount, "Number of Service Detail is not dispalyed as Expected ");

	
		softassert.assertAll();
	}
		else
		{
			//Nothing to validate when there is no claims
		}
	}
	
	public void ProviderDetailValidations(String responseXMLPath,String Claimspresentvalue) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Boolean Claimsavailable=(Boolean) ScenarioContext.getContext(Claimspresentvalue);
		System.out.println("Caimsavailable:"+Claimsavailable);
		if(Claimsavailable==null)
		{
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		String ProviderLastNameXpath="Envelope.Body.GetClaimDetailResponse.GetClaimDetailResult.ResponseData.ClaimDetail.ProviderLastName";
		String ProviderFirstNameXpath="Envelope.Body.GetClaimDetailResponse.GetClaimDetailResult.ResponseData.ClaimDetail.ProviderFirstName";
		String ProviderLastName= xt.getString(ProviderLastNameXpath);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		String ProviderFirstName= xt.getString(ProviderFirstNameXpath);
		if(ProviderFirstName.isEmpty())
		{
			String ProviderNameUI=driver.findElement(ProviderName).getText();
			System.out.println("Provider Name: "+ProviderLastName);
			String DateofServiceUI=driver.findElement(DOSXpth).getText();
			System.out.println("DateofServiceUI: "+DateofServiceUI);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			System.out.println("Provider Name UI: "+ProviderNameUI);
			Thread.sleep(2000);
			Assert.assertTrue(ProviderLastName.equalsIgnoreCase(ProviderNameUI), "Provider Name is not matching with Webservice name");
		
		}
		else
		{
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			String ProviderNameUI=driver.findElement(ProviderName).getText();
			System.out.println("Provider Name: "+ProviderLastName);
			System.out.println("Provider Name: "+ProviderFirstName);
			String ProviderName=ProviderLastName+", "+ProviderFirstName;
			System.out.println("Provider Name: " +ProviderName);
			String DateofServiceUI=driver.findElement(DOSXpth).getText();
			System.out.println("DateofServiceUI: "+DateofServiceUI);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			System.out.println("Provider Name UI: "+ProviderNameUI);
			Thread.sleep(2000);
			Assert.assertTrue(ProviderName.equalsIgnoreCase(ProviderNameUI), "Provider Name is not matching with Webservice name");
		
		}
		}
		else
		{
			//Nothing to validate when there is no Claims
		}
	}


	public void MemberDetailValidations(String responseXMLPath, String memberName,String Claimspresentvalue) throws IOException {
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
			String LastNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+ a +"].LastName";
			String FirstNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+ a +"].FirstName";
			String MidInitXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+ a +"].MidInit";
			String MemberIdXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+ a +"].MemberId";
			String GroupIDXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].GroupId";
			String SubGroupIDXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].SubgroupId";
			String GroupNameXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member[0].EligibilityInfo.Eligibility[0].GroupName";
			String MedicaidXpath= "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+a+"].MedicaidNumber";                                                  
			String LastName=xt.getString(LastNameXpath);
			String FirstName=xt.getString(FirstNameXpath);
			String MidInit=xt.getString(MidInitXpath);
			String MemberID=xt.getString(MemberIdXpath);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			String GroupID=xt.getString(GroupIDXpath);
			String SubGroupID=xt.getString(SubGroupIDXpath);
			String GroupName=xt.getString(GroupNameXpath);
			String Medicaidnumber=xt.getString(MedicaidXpath);
			System.out.println("Member Informations- LastName: "+LastName);
			System.out.println("Member Informations- FirstName: "+FirstName);
			System.out.println("Member Informations- MidInit "+MidInit);
			System.out.println("Member Informations- MemberID: "+MemberID);
			System.out.println("Member Informations- GroupID: "+GroupID);
			System.out.println("Member Informations- SubGroupID "+SubGroupID);
			System.out.println("Member Informations- GroupName: "+GroupName);
			System.out.println("Member Informations- Medicaid: " +Medicaidnumber);
					
			String Groupid=GroupID+SubGroupID;
			System.out.println("Group ID: "+Groupid);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			String MemberNameUI=driver.findElement(MemberNameXpathUI).getText();
			
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			String MemberIDUI=driver.findElement(MemberIDXpathUI).getText();
			
			String GroupNameUI=driver.findElement(GroupNameXpathUI).getText();
		
			String GroupIDUI=driver.findElement(GroupIDXpathUI).getText();
			
			
			//System.out.println("Member Informations- Member Name UI: " +MemberNameUI);
			System.out.println("Member Informations- Member ID UI: " +MemberIDUI);
			System.out.println("Member Informations- Group Name UI: " +GroupNameUI);
			System.out.println("Member Informations- Group ID UI: " +GroupIDUI);
			//System.out.println("Medicaid number: " +Medicaidnumber);
			String fullname=null;
			if(!MidInit.isEmpty())
			{
				fullname=FirstName+" "+MidInit+" "+LastName;
				System.out.println("Fullname: " +fullname);
			}
			else
			{
				fullname=FirstName+" "+LastName;
				System.out.println("Fullname: " +fullname);
			}
				
			if(Medicaidnumber.isEmpty())
			{
				System.out.println("This is not a Medicaid number");
				softassert.assertTrue(fullname.equalsIgnoreCase(MemberNameUI), "Member name is not populated correctly");
				softassert.assertTrue(MemberID.equals(MemberIDUI), "Member ID is not populated correctly");
				softassert.assertTrue(GroupName.equalsIgnoreCase(GroupNameUI),"Group name is not populated correctly");
				softassert.assertTrue(Groupid.equals(GroupIDUI), "Group ID is not populated correctly");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
				softassert.assertAll();
			}
			else
			{
				String GroupmedUI=driver.findElement(GroupIDmedXpathUI).getText();
				String MedicaidnumberUI=driver.findElement(MedicaidXpathUI).getText();
				
				softassert.assertTrue(fullname.equalsIgnoreCase(MemberNameUI), "Member name is not populated correctly");
				softassert.assertTrue(MemberID.equals(MemberIDUI), "Member ID is not populated correctly");
				softassert.assertTrue(GroupName.equalsIgnoreCase(GroupNameUI),"Group name is not populated correctly");
				softassert.assertTrue(Medicaidnumber.equals(MedicaidnumberUI), "Medicaidnumber is not populated correctly");
				softassert.assertTrue(Groupid.equals(GroupmedUI),"GroupID is not populated correctly");
				softassert.assertAll();
			}
			
				
			softassert.assertTrue(fullname.equalsIgnoreCase(MemberNameUI), "Member name is not populated correctly");
			softassert.assertTrue(MemberID.equals(MemberIDUI), "Member ID is not populated correctly");
			softassert.assertTrue(GroupName.equalsIgnoreCase(GroupNameUI),"Group name is not populated correctly");
			softassert.assertTrue(Groupid.equals(GroupIDUI), "Group ID is not populated correctly");
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			driver.findElement(BackButton).click();
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			
		}
	
	else
	{
		//Nothing to validate
	}
	}
	

}
