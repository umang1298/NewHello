package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import io.restassured.path.xml.XmlPath;

public class MyDeductiblesPage extends TestBase{

	public MyDeductiblesPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	
	}
	Properties prop = initialize_properties();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page factory
	By BenifityearXpath=By.xpath("//div[@id='benefityearwrapper']/div/span[2]");
	By CollapseAllXpath=By.xpath("//span[contains(text(),'Collapse All')]");
	By ExpandallXpath=By.xpath("//span[contains(text(),'Expand All')]");
	By Agemessage=By.xpath("//div[@id='depB181']/div[1]");
//	By Agemessage=By.xpath("//div[@id='CollapseB180']/div/div[1]");
	By OutnetworkAgemessage=By.xpath("//div[@id='depB18Out1']/div[1]");
//	By OutnetworkAgemessage=By.xpath("//div[@id='CollapseB18Out0']/div/div[1]");
	By Medicaidmessage=By.xpath("//div[contains(text(),'You do not have copays, coinsurance or a deductibl')]");
	By DeductibleHeading=By.xpath("/html/body/div[2]/ol/li[2]/span");
	By InNetworkXpath=By.xpath("//a[contains(text(),'In Network')]");
	By OutNetworkXpath=By.xpath("//a[contains(text(),'Out of network')]");
	By InformationUnavailable=By.xpath("//div[contains(text(),'Information for this benefit year is currently una')]");
	
	
	public void uservalidatesInnetworkDeductibleandOOPpage(String responseXML, String dateval) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXML)));
		System.out.println("Contents-->" +contents);
		XmlPath xt = new XmlPath(contents);
		String MembersXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member";
		String MedicareMemberXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].IsMedicareAdvantage";
		String MedicaidMemberXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member[0].MedicaidNumber";
		List<org.w3c.dom.Element> ListofTags = xt.getList(MembersXpath);
		int NumberofMembers = ListofTags.size();
		System.out.println("NumberofMembers:"+NumberofMembers);
		String MedicareMember=xt.getString(MedicareMemberXpath);
		String MedicaidMember=xt.getString(MedicaidMemberXpath);
		String XMLPathtoWriteResponse ="XMLs/GetMemberDeductibleResponse.xml";
		if(!MedicaidMember.isEmpty())
		{
			Assert.assertTrue(driver.findElement(Medicaidmessage).isDisplayed());
		}
		else if(MedicareMember.equals("true"))
		{
			for(int i=0,j=1;i<NumberofMembers;i++,j++)
			{
			sendSoapRequestandWriteResponse(responseXML, XMLPathtoWriteResponse,i,dateval);
			validateBenefityear(XMLPathtoWriteResponse);
			validateMedicareDedOOPAmount(responseXML, XMLPathtoWriteResponse,i,j);
			}
		}
		else
		{
		driver.findElement(ExpandallXpath).click();
		
		for(int i=0,j=1;i<NumberofMembers;i++,j++)
		{
			sendSoapRequestandWriteResponse(responseXML, XMLPathtoWriteResponse,i,dateval);
			String contents1 = new String(Files.readAllBytes(
					Paths.get(System.getProperty("user.dir") + File.separator + XMLPathtoWriteResponse)));
			System.out.println("Contents Deductible-->" +contents1);
			
			XmlPath xt1 = new XmlPath(contents1);
			try
			{
				String TierAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum";
				List<org.w3c.dom.Element> ListofTierLevel = xt1.getList(TierAccumXpath);
				int NoofTierAccum = ListofTierLevel.size();
				System.out.println("NoofTierLevel:"+NoofTierAccum);
					for(int x=0;x<NoofTierAccum;x++)
					{
						String TierLevelXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].TierLevel";
						String TierLevel=xt1.getString(TierLevelXpath);
						int y=0;
						if(TierLevel.equals("1")){
							y=1;
						}
						else{
							y=2;
							
						}
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);	
			String AgeXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].Age";
			String SuffixXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[" + i +"].Suffix";
			String Age=xt.getString(AgeXpath);
			int age=Integer.parseInt(Age);
			String Suffix=xt.getString(SuffixXpath);
			int MemberSuffix=Integer.parseInt(Suffix);
			if(MemberSuffix==0)	
			{
				validateInnetworkdedcopayanddedamount(XMLPathtoWriteResponse,j,y,x);
			}
			else
			{
				if(age<18)		
				{
				validateInnetworkdedcopayanddedamount(XMLPathtoWriteResponse,j,y,x);
				}
				else
				{
					String expectedHIPPA18="dependents under the age of 18";
					String HIPPA18=driver.findElement(Agemessage).getText();
					driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
					System.out.println("message:"+HIPPA18);
					driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
					Assert.assertTrue(HIPPA18.contains(expectedHIPPA18), "HIPPA message is not displayed as expected");
				}
			}
			}
			}
			catch(NumberFormatException e)
			{
				System.out.println(e);
			}
			
		}
		validateInnetworkFamilyDedCopayandDedAmount(XMLPathtoWriteResponse);
		 validateBenefityear(XMLPathtoWriteResponse);
		 validatebilledamountLink();
		}
		}
	private void validatebilledamountLink() throws InterruptedException {
		// TODO Auto-generated method stub
		By BilledAmountLink=By.xpath("//div[@id='accordion']/div[1]/div/div/div[1]/div/div[1]/div[3]/div[2]");
		String BilledAmount=driver.findElement(BilledAmountLink).getText().replace(",", "").substring(1);
		By ClaimTable=By.xpath("//div[@id=\"pocketclaimsb11\"]/div/div");
		double billedamount=Double.parseDouble(BilledAmount);
		System.out.println("billedamount:"+billedamount);
		if(billedamount>0)
		{
			driver.findElement(BilledAmountLink).click();
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
			Assert.assertTrue(driver.findElement(ClaimTable).isDisplayed());
		}
		
	}
	private void validateMedicareDedOOPAmount(String responseXML, String XMLPathtoWriteResponse, int i, int j) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXML)));
		
		XmlPath xt = new XmlPath(contents);
		 
		 String contents1 = new String(Files.readAllBytes(
					Paths.get(System.getProperty("user.dir") + File.separator + XMLPathtoWriteResponse)));
			System.out.println("Contents Deductible-->" +contents1);
			
			XmlPath xt1 = new XmlPath(contents1);
			String InnetworkXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum";
			String OutnetworkXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndOutNetworkDetails.TieredAccum";
			String CombinedXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndCombinedNetworkDetails.CombinedAccum.";
			List<org.w3c.dom.Element> Innetwork = xt1.getList(InnetworkXpath);
			int Innetworkvalue = Innetwork.size();
			List<org.w3c.dom.Element> Outnetwork = xt1.getList(OutnetworkXpath);
			int Outnetworkvalue = Outnetwork.size();
			List<org.w3c.dom.Element> Combinednetwork = xt1.getList(CombinedXpath);
			int Combinedvalue = Combinednetwork.size();
			if(Innetworkvalue==0&Outnetworkvalue==0&Combinedvalue==0)
			{
				System.out.println("No information");
				Assert.assertTrue(driver.findElement(InformationUnavailable).isDisplayed(),"Information unavailable message is not displayed");
			}
			else
			{
			String TierAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum";
			List<org.w3c.dom.Element> ListofTierLevel = xt1.getList(TierAccumXpath);
			int NoofTierAccum = ListofTierLevel.size();
			System.out.println("NoofTierLevel:"+NoofTierAccum);
			if(NoofTierAccum==0)
			{
				validateCombinedNetwork(XMLPathtoWriteResponse);
			}
			else
			{
				for(int x=0;x<NoofTierAccum;x++)
				{
					try
					{
					String TierLevelXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].TierLevel";
					String TierLevel=xt1.getString(TierLevelXpath);
					System.out.println("TierLevel:"+TierLevel);
					int y=0;
					if(TierLevel.equals("1")){
						y=1;
					}
					else{
						y=2;
						
					}
			String DedLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].DeductibleDetails.DedLimit";
			String DedRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].DeductibleDetails.DedRemaining";
			String MedDedAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].DeductibleDetails.TotalAccum";
			String OOPLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].OOPDetails.OopLimit";
			String OOPRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].OOPDetails.OopRemaining";
			String OOPAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].OOPDetails.TotalOopAccum";
			String DedLimit=xt1.getString(DedLimitXpath);
			String DedRemaining=xt1.getString(DedRemainingXpath);
			String TotalDedAccum=xt1.getString(MedDedAccumXpath);
			String OOPLimit=xt1.getString(OOPLimitXpath);
			String OOPRemaining=xt1.getString(OOPRemainingXpath);
			String OOPAccum=xt1.getString(OOPAccumXpath);
			System.out.println("InnetworkDedLimit_WS"+DedLimit);
			System.out.println("InnetworkDedRemaining_WS"+DedRemaining);
			System.out.println("InnetworkTotalDedAccum_WS"+TotalDedAccum);
			String OOPPlanLimitUI=driver.findElement(By.xpath("//div[@id='individual1']/div[1]/div[3]/div[2]/div[2]")).getText().replace(",", "").substring(1);
			String OOPBilledAmountUI=driver.findElement(By.xpath("//div[@id='individual1']/div[1]/div[3]/div[3]/div[2]")).getText().replace(",", "").substring(1);
			String OOPRemainingLimitUI=driver.findElement(By.xpath("//div[@id='individual1']/div[1]/div[3]/div[4]/div[2]")).getText().replace(",", "").substring(1);		
			String DedPlanLimitUI=driver.findElement(By.xpath("//div[@id='individual1']/div[1]/div[1]/div[2]/div[2]")).getText().replace(",", "").substring(1);
			String DedBilledAmountUI=driver.findElement(By.xpath("//div[@id='individual1']/div[1]/div[1]/div[3]/div[2]")).getText().replace(",", "").substring(1);
			String DedRemainingLimitUI=driver.findElement(By.xpath("//div[@id='individual1']/div[1]/div[1]/div[4]/div[2]")).getText().replace(",", "").substring(1);
			System.out.println("InnetworkdedLimitUI"+DedPlanLimitUI);
			System.out.println("InnetworkRemainingLimitUI"+DedRemainingLimitUI);
			System.out.println("InnetworkdedBilledAmountUI"+DedBilledAmountUI);
			System.out.println("InnetworkOOPPlanLimitUI"+OOPPlanLimitUI);
			System.out.println("InnetworkOOPRemaining_UI"+OOPRemainingLimitUI);
			
			System.out.println("InnetworkOOPLimit_WS"+OOPLimit);
			System.out.println("InnetworkOOPRemaining_WS"+OOPRemaining);
			Assert.assertTrue(DedPlanLimitUI.contains(DedLimit), "Innetwork Ded Limit is wrongly populated");
			Assert.assertTrue(DedBilledAmountUI.contains(TotalDedAccum), "Innetwork Ded Remaining is wrongly populated");
			Assert.assertTrue(DedRemainingLimitUI.contains(DedRemaining),"Innetwork Total Billed Amount is wrongly populated");
			Assert.assertTrue(OOPPlanLimitUI.contains(OOPLimit), "Innetwork OOP Plan Limit is wrongly populated");
			Assert.assertTrue(OOPRemainingLimitUI.contains(OOPRemaining), "Innetwork OOP Remaining is wrongly populated");
			Assert.assertTrue(OOPBilledAmountUI.contains(OOPAccum),"Innetwork OOP Billed Amount is wrongly populated");
			
				}
			catch(NumberFormatException e)
			{
				System.out.println(e);
			}
				}
			}
			}
		}
	

	private void validateCombinedNetwork(String XMLPathtoWriteResponse) throws IOException {
		// TODO Auto-generated method stub
		String contents1 = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + XMLPathtoWriteResponse)));
		System.out.println("Contents-->" +contents1);
		try
		{
		XmlPath xt1 = new XmlPath(contents1);
		String OOPLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndCombinedNetworkDetails.CombinedAccum.OOPDetails.OopLimit";
		String OOPRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndCombinedNetworkDetails.CombinedAccum.OOPDetails.OopRemaining";
		String OOPAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndCombinedNetworkDetails.CombinedAccum.OOPDetails.TotalOopAccum";
		String OOPLimit=xt1.getString(OOPLimitXpath);
		String OOPRemaining=xt1.getString(OOPRemainingXpath);
		String OOPAccum=xt1.getString(OOPAccumXpath);
		String OOPPlanLimitUI=driver.findElement(By.xpath("//div[@id='home']/div/div/div[1]/div[2]/div[2]")).getText().replace(",", "").substring(1);
		String OOPBilledAmountUI=driver.findElement(By.xpath("//div[@id='home']/div/div/div[1]/div[3]/div[2]")).getText().replace(",", "").substring(1);
		String OOPRemainingLimitUI=driver.findElement(By.xpath("//div[@id='home']/div/div/div[1]/div[4]/div[2]")).getText().replace(",", "").substring(1);		
		System.out.println("CombinedOOPPlanLimitUI"+OOPPlanLimitUI);
		System.out.println("CombinedOOPRemaining_UI"+OOPRemainingLimitUI);
		System.out.println("CombinedTotalDedAccum_UI"+OOPBilledAmountUI);
		System.out.println("CombinedTotalDedAccum_WS"+OOPAccumXpath);
		System.out.println("CombinedOOPLimit_WS"+OOPLimit);
		System.out.println("CombinedOOPRemaining_WS"+OOPRemaining);
		Assert.assertTrue(OOPPlanLimitUI.contains(OOPLimit), "Combined OOP Plan Limit is wrongly populated");
		Assert.assertTrue(OOPRemainingLimitUI.contains(OOPRemaining), "Combined OOP Remaining is wrongly populated");
		Assert.assertTrue(OOPBilledAmountUI.contains(OOPAccum),"Combined OOP Billed Amount is wrongly populated");
		
	}
		catch(NumberFormatException e)
		{
			System.out.println(e);
		}
	}
	private void sendSoapRequestandWriteResponse(String responseXML, String XMLPathtoWriteResponse,int i,String dateval) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXML)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
	String ContrivedKeyXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].ContrivedKey";
	String productIDXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].EligibilityInfo.Eligibility[0].ProductId";
	String contrivedKey=xt.getString(ContrivedKeyXpath);
	String ProductID=xt.getString(productIDXpath);
	String DeductibleRequestXmlPath="XMLs/GetMemberDeductible.xml";
	APIMethods.setupSOAPRequestforMemberDeductibleService(ProductID, contrivedKey, dateval, DeductibleRequestXmlPath);
	String EndpointURL=prop.getProperty("EndpointURLMemberDeductibleService");
	String DedRequestXmlPath="//XMLs//GetMemberDeductible.xml";
//	String XMLPathtoWriteResponse ="XMLs/GetMemberDeductibleResponse.xml";
	APIMethods.SendSOAPRequestforMemberDeductibleServiceandWriteResponsetoFile(EndpointURL, DedRequestXmlPath, XMLPathtoWriteResponse);
	
	}

	private void validateBenefityear(String XMLPathtoWriteResponse) throws IOException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(DeductibleHeading));
		String BeginDateXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.BenefitBeginDate";
		String EndDateXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.BenefitEndDate";
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + XMLPathtoWriteResponse)));
		XmlPath xt = new XmlPath(contents);
		String BeginDate=xt.getString(BeginDateXpath);
		String EndDate=xt.getString(EndDateXpath);
		String BenefitYearUI=driver.findElement(BenifityearXpath).getText();
		String BeginDateUI=BenefitYearUI.substring(0,10);
		String EndateUI=BenefitYearUI.substring(13);
		String BeginDateconversion=CommonMethods.returnConverteddateformatMMDDYYYY(BeginDate);
		String EndDateconversion=CommonMethods.returnConverteddateformatMMDDYYYY(EndDate);
		System.out.println("Begin Date:"+BeginDate);
		System.out.println("Begin Date:"+BeginDateconversion);
		System.out.println("Begin DateUI:"+BeginDateUI);
		Assert.assertTrue(BeginDateUI.equals(BeginDateconversion),"Benefit Begin year is not matching with webservice");
		Assert.assertTrue(EndateUI.equals(EndDateconversion),"Benefit End year is not matching with Webservice");
	}
	

	private void validateInnetworkFamilyDedCopayandDedAmount(String xMLPathtoWriteResponse) throws IOException {
		// TODO Auto-generated method stub
		String contents1 = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + xMLPathtoWriteResponse)));
		
		XmlPath xt1 = new XmlPath(contents1);
		String TierAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum";
		List<org.w3c.dom.Element> ListofTierLevel = xt1.getList(TierAccumXpath);
		int NoofTierLevel = ListofTierLevel.size();
		System.out.println("NoofTierLevel:"+NoofTierLevel);
		
		for(int x=0;x<NoofTierLevel;x++)
		{
			try
			{
			System.out.println("Family:");
			String TierLevelXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].TierLevel";
			String TierLevel=xt1.getString(TierLevelXpath);
			System.out.println("TierLevel:"+TierLevel);
			int y=0;
			if(TierLevel.equals("1"))
			{
				y=1;
				
			}
			else
			{
				y=2;
				
			}
			String FamilyDedLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamInNetworkDetails.TieredAccum["+x+"].DeductibleDetails.DedLimit";
			String FamilyDedRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamInNetworkDetails.TieredAccum["+x+"].DeductibleDetails.DedRemaining";
			String FamilyTotalDedAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamInNetworkDetails.TieredAccum["+x+"].DeductibleDetails.TotalDedAccum";
			String FamilyOOPLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamInNetworkDetails.TieredAccum["+x+"].OOPDetails.OOPLimit";
			String FamilyOOPRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamInNetworkDetails.TieredAccum["+x+"].OOPDetails.OOPRemaining";
			String FamilyOOPAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamInNetworkDetails.TieredAccum["+x+"].OOPDetails.TotalOopAccum";
			String FamilyDedLimit=xt1.getString(FamilyDedLimitXpath);
			String FamilyDedRemaining=xt1.getString(FamilyDedRemainingXpath);
			String FamilyTotalDedAccum=xt1.getString(FamilyTotalDedAccumXpath);
			String FamilyOOPLimit=xt1.getString(FamilyOOPLimitXpath);
			String FamilyOOPRemaining=xt1.getString(FamilyOOPRemainingXpath);
			String FamilyOOPAccum=xt1.getString(FamilyOOPAccumXpath);
			String FamdedLimitUI=driver.findElement(By.xpath("//div[@id='family']/div["+y+"]/div/div[1]/div[2]/div[2]")).getText().replace(",", "").substring(1);
			String FamdedBilledAmountUI=driver.findElement(By.xpath("//div[@id='family']/div["+y+"]/div/div[1]/div[3]/div[2]")).getText().replace(",", "").substring(1);
			String FamRemainingLimitUI=driver.findElement(By.xpath("//div[@id='family']/div["+y+"]/div/div[1]/div[4]/div[2]")).getText().replace(",", "").substring(1);		
			String FamOOPPlanLimitUI=driver.findElement(By.xpath("//div[@id='family']/div["+y+"]/div/div[5]/div[2]/div[2]")).getText().replace(",", "").substring(1);
			String FamOOPBilledAmountUI=driver.findElement(By.xpath("//div[@id='family']/div["+y+"]/div/div[5]/div[3]/div[2]")).getText().replace(",", "").substring(1);
			String FamOOPRemainingLimitUI=driver.findElement(By.xpath("//div[@id='family']/div["+y+"]/div/div[5]/div[4]/div[2]")).getText().replace(",", "").substring(1);
			System.out.println("InnetworkFamilydedLimitUI"+FamdedLimitUI);
			System.out.println("InnetworkFamilyRemainingLimitUI"+FamRemainingLimitUI);
			System.out.println("InnetworkFamilydedBilledAmountUI"+FamdedBilledAmountUI);
			System.out.println("InnetworkFamilyOOPPlanLimitUI"+FamOOPPlanLimitUI);
			System.out.println("InnetworkFamilyOOPRemaining_WS"+FamOOPRemainingLimitUI);
			System.out.println("InnetworkFamilyDedLimit_WS"+FamilyDedLimit);
			System.out.println("InnetworkFamilyDedRemaining_WS"+FamilyDedRemaining);
			System.out.println("InnetworkFamilyTotalDedAccum_WS"+FamilyTotalDedAccum);
			System.out.println("InnetworkFamilyOOPLimit_WS"+FamilyOOPLimit);
			System.out.println("InnetworkFamilyOOPRemaining_WS"+FamilyOOPRemaining);
						
			Assert.assertTrue(FamdedLimitUI.contains(FamilyDedLimit), "Family Ded Limit is wrongly populated");
			Assert.assertTrue(FamRemainingLimitUI.contains(FamilyDedRemaining), "Family Ded Remaining is wrongly populated");
			Assert.assertTrue(FamdedBilledAmountUI.contains(FamilyTotalDedAccum),"Family Med Billed Amount is wrongly populated");
			Assert.assertTrue(FamOOPPlanLimitUI.contains(FamilyOOPLimit), "Family OOP Plan Limit is wrongly populated");
			Assert.assertTrue(FamOOPRemainingLimitUI.contains(FamilyOOPRemaining), "Family OOP Remaining is wrongly populated");
			Assert.assertTrue(FamOOPBilledAmountUI.contains(FamilyOOPAccum),"Family OOP Billed Amount is wrongly populated");
			
		}
			catch(NumberFormatException e)
			{
				System.out.println(e);
			}
	}
	}


	private void validateInnetworkdedcopayanddedamount(String XMLPathtoWriteResponse,int j, int y, int x) throws IOException {
		// TODO Auto-generated method stub
		String contents1 = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + XMLPathtoWriteResponse)));
		System.out.println("Contents Deductible-->" +contents1);
		try
		{
		XmlPath xt1 = new XmlPath(contents1);
		String DedLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].DeductibleDetails.DedLimit";
		String DedRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].DeductibleDetails.DedRemaining";
		String MedDedAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].DeductibleDetails.TotalDedAccum";
		String OOPLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].OOPDetails.OOPLimit";
		String OOPRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].OOPDetails.OOPRemaining";
		String OOPAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndInNetworkDetails.TieredAccum["+x+"].OOPDetails.TotalOopAccum";
		String DedLimit=xt1.getString(DedLimitXpath);
		String DedRemaining=xt1.getString(DedRemainingXpath);
		String TotalDedAccum=xt1.getString(MedDedAccumXpath);
		String OOPLimit=xt1.getString(OOPLimitXpath);
		String OOPRemaining=xt1.getString(OOPRemainingXpath);
		String OOPAccum=xt1.getString(OOPAccumXpath);
		
		String dedLimitUI=driver.findElement(By.xpath("//div[@id='accordion']/div["+j+"]/div/div/div["+y+"]/div/div[1]/div[2]/div[2]")).getText().replace(",", "").substring(1);
		String dedBilledAmountUI=driver.findElement(By.xpath("//div[@id='accordion']/div["+j+"]/div/div/div["+y+"]/div/div[1]/div[3]/div[2]")).getText().replace(",", "").substring(1);
		String RemainingLimitUI=driver.findElement(By.xpath("//div[@id='accordion']/div["+j+"]/div/div/div["+y+"]/div/div[1]/div[4]/div[2]")).getText().replace(",", "").substring(1);		
		String OOPPlanLimitUI=driver.findElement(By.xpath("//div[@id='accordion']/div["+j+"]/div/div/div["+y+"]/div/div[5]/div[2]/div[2]")).getText().replace(",", "").substring(1);
		String OOPBilledAmountUI=driver.findElement(By.xpath("//div[@id='accordion']/div["+j+"]/div/div/div["+y+"]/div/div[5]/div[3]/div[2]")).getText().replace(",", "").substring(1);
		String OOPRemainingLimitUI=driver.findElement(By.xpath("//div[@id='accordion']/div["+j+"]/div/div/div["+y+"]/div/div[5]/div[4]/div[2]")).getText().replace(",", "").substring(1);
		System.out.println("InnetworkdedLimitUI"+dedLimitUI);
		System.out.println("InnetworkRemainingLimitUI"+RemainingLimitUI);
		System.out.println("InnetworkdedBilledAmountUI"+dedBilledAmountUI);
		System.out.println("InnetworkOOPPlanLimitUI"+OOPPlanLimitUI);
		System.out.println("InnetworkOOPRemaining_WS"+OOPRemainingLimitUI);
		System.out.println("InnetworkDedLimit_WS"+DedLimit);
		System.out.println("InnetworkDedRemaining_WS"+DedRemaining);
		System.out.println("InnetworkTotalDedAccum_WS"+TotalDedAccum);
		System.out.println("InnetworkOOPLimit_WS"+OOPLimit);
		System.out.println("InnetworkOOPRemaining_WS"+OOPRemaining);
		Assert.assertTrue(dedLimitUI.contains(DedLimit), "Innetwork Ded Limit is wrongly populated");
		Assert.assertTrue(RemainingLimitUI.contains(DedRemaining), "Innetwork Ded Remaining is wrongly populated");
		Assert.assertTrue(dedBilledAmountUI.contains(TotalDedAccum),"Innetwork Total Billed Amount is wrongly populated");
		Assert.assertTrue(OOPPlanLimitUI.contains(OOPLimit), "Innetwork OOP Plan Limit is wrongly populated");
		Assert.assertTrue(OOPRemainingLimitUI.contains(OOPRemaining), "Innetwork OOP Remaining is wrongly populated");
		Assert.assertTrue(OOPBilledAmountUI.contains(OOPAccum),"Innetwork OOP Billed Amount is wrongly populated");
		
	}
		catch(NumberFormatException e)
		{
			System.out.println(e);
		}
	}


	public void uservalidatesOutnetworkDeductibleandOOPpage(String responseXML, String dateval) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXML)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		String MembersXpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member";
		String MedicareMemberXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member.EligibilityInfo.Eligibility[1].IsMedicareAdvantage";
		String MedicaidMemberXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member[0].MedicaidNumber";
	
		List<org.w3c.dom.Element> ListofTags = xt.getList(MembersXpath);
		int NumberofMembers = ListofTags.size();
		System.out.println("NumberofMembers:"+NumberofMembers);
		String MedicareMember=xt.getString(MedicareMemberXpath);
		String MedicaidMember=xt.getString(MedicaidMemberXpath);
		String XMLPathtoWriteResponse ="XMLs/GetMemberDeductibleResponse.xml";
		if(!MedicaidMember.isEmpty())
		{
			Assert.assertTrue(driver.findElement(Medicaidmessage).isDisplayed());
		}
		else if(MedicareMember.equals("true"))
		{
			for(int i=0,j=1;i<NumberofMembers;i++,j++)
			{
			sendSoapRequestandWriteResponse(responseXML, XMLPathtoWriteResponse,i,dateval);
			validateBenefityear(XMLPathtoWriteResponse);
			//outnetwork data not available
			}
		}
		else
		{
		driver.findElement(OutNetworkXpath).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(CollapseAllXpath));
		for(int i=0,j=1;i<NumberofMembers;i++,j++)
		{
			
			sendSoapRequestandWriteResponse(responseXML, XMLPathtoWriteResponse,i,dateval);
			
			try
			{
							
						
			String AgeXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].Age";
			String SuffixXpath="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[" + i +"].Suffix";
			String Age=xt.getString(AgeXpath);
			int age=Integer.parseInt(Age);
			String Suffix=xt.getString(SuffixXpath);
			int MemberSuffix=Integer.parseInt(Suffix);
			
			if(MemberSuffix==0)	
			{
				System.out.println("Outnetworkmembersuffixloop");
				validateOutnetworkdedcopayanddedamount(XMLPathtoWriteResponse,j);	
			}
			else
			{
				if(age<18)		
				{
				System.out.println("Outnetworkageloop");
				validateOutnetworkdedcopayanddedamount(XMLPathtoWriteResponse,j);
				}
				else
				{
					String expectedHIPPA18="dependents under the age of 18";
					String HIPPA18=driver.findElement(OutnetworkAgemessage).getText();
					driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
					System.out.println("message:"+HIPPA18);
					driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
					Assert.assertTrue(HIPPA18.contains(expectedHIPPA18), "HIPPA message is not displayed as expected");
				}
			}
			}
			
			catch(NumberFormatException e)
			{
				System.out.println(e);
			}
		}
			validateOutnetworkFamilyDedCopayandDedAmount(XMLPathtoWriteResponse);
		}
	}
	
	





private void validateOutnetworkdedcopayanddedamount(String XMLPathtoWriteResponse,int j) throws IOException {
	// TODO Auto-generated method stub
	String contents1 = new String(Files.readAllBytes(
			Paths.get(System.getProperty("user.dir") + File.separator + XMLPathtoWriteResponse)));
	System.out.println("Contents Deductible-->" +contents1);
	
	XmlPath xt1 = new XmlPath(contents1);
	try
	{
	String OutDedLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndOutNetworkDetails.TieredAccum.DeductibleDetails.DedLimit";
	String OutDedRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndOutNetworkDetails.TieredAccum.DeductibleDetails.DedRemaining";
	String OutTotalDedAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndOutNetworkDetails.TieredAccum.DeductibleDetails.TotalDedAccum";
	String OutOOPLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndOutNetworkDetails.TieredAccum.OOPDetails.OOPLimit";
	String OutOOPRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndOutNetworkDetails.TieredAccum.OOPDetails.OOPRemaining";
	String OutOOPAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.IndOutNetworkDetails.TieredAccum.OOPDetails.TotalOopAccum";
	String OutDedLimit=xt1.getString(OutDedLimitXpath);
	String OutDedRemaining=xt1.getString(OutDedRemainingXpath);
	String OutTotalDedAccum=xt1.getString(OutTotalDedAccumXpath);
	String OutOOPLimit=xt1.getString(OutOOPLimitXpath);
	String OutOOPRemaining=xt1.getString(OutOOPRemainingXpath);
	String OutOOPAccum=xt1.getString(OutOOPAccumXpath);
	String OutdedLimitUI=driver.findElement(By.xpath("(//div[@id='accordion'])[2]/div["+j+"]/div/div/div[1]/div[1]/div[2]/div[2]")).getText().replace(",", "").substring(1);
	String OutdedBilledAmountUI=driver.findElement(By.xpath("(//div[@id='accordion'])[2]/div["+j+"]/div/div/div[1]/div[1]/div[3]/div[2]")).getText().replace(",", "").substring(1);
	String OutRemainingLimitUI=driver.findElement(By.xpath("(//div[@id='accordion'])[2]/div["+j+"]/div/div/div[1]/div[1]/div[4]/div[2]")).getText().replace(",", "").substring(1);		
	String OutOOPPlanLimitUI=driver.findElement(By.xpath("(//div[@id='accordion'])[2]/div["+j+"]/div/div/div[1]/div[3]/div[2]/div[2]")).getText().replace(",", "").substring(1);
	String OutOOPBilledAmountUI=driver.findElement(By.xpath("(//div[@id='accordion'])[2]/div["+j+"]/div/div/div[1]/div[5]/div[3]/div[2]")).getText().replace(",", "").substring(1);
	String OutOOPRemainingLimitUI=driver.findElement(By.xpath("(//div[@id='accordion'])[2]/div["+j+"]/div/div/div[1]/div[5]/div[4]/div[2]")).getText().replace(",", "").substring(1);
	System.out.println("OutdedLimitUI"+OutdedLimitUI);
	System.out.println("OutRemainingLimitUI"+OutRemainingLimitUI);
	System.out.println("OutdedBilledAmountUI"+OutdedBilledAmountUI);
	System.out.println("OutOOPPlanLimitUI"+OutOOPPlanLimitUI);
	System.out.println("OutOOPRemaining_WS"+OutOOPRemainingLimitUI);
	System.out.println("OutDedLimit_WS"+OutDedLimit);
	System.out.println("OutDedRemaining_WS"+OutDedRemaining);
	System.out.println("OutTotalDedAccum_WS"+OutTotalDedAccum);
	System.out.println("OutOOPLimit_WS"+OutOOPLimit);
	System.out.println("OutOOPRemaining_WS"+OutOOPRemaining);
	
	Assert.assertTrue(OutdedLimitUI.contains(OutDedLimit), "Out Ded Limit is wrongly populated");
	Assert.assertTrue(OutRemainingLimitUI.contains(OutDedRemaining), " Out Ded Remaining is wrongly populated");
	Assert.assertTrue(OutdedBilledAmountUI.contains(OutTotalDedAccum),"Out Total Billed Amount is wrongly populated");
	Assert.assertTrue(OutOOPPlanLimitUI.contains(OutOOPLimit), "Out OOP Plan Limit is wrongly populated");
	Assert.assertTrue(OutOOPRemainingLimitUI.contains(OutOOPRemaining), "Out OOP Remaining is wrongly populated");
	Assert.assertTrue(OutOOPBilledAmountUI.contains(OutOOPAccum),"Out OOP Billed Amount is wrongly populated");
	
	}catch(NumberFormatException e)
	{
		System.out.println(e);
	}
}

	
private void validateOutnetworkFamilyDedCopayandDedAmount(String xMLPathtoWriteResponse) throws IOException {
	// TODO Auto-generated method stub
	String contents1 = new String(Files.readAllBytes(
			Paths.get(System.getProperty("user.dir") + File.separator + xMLPathtoWriteResponse)));
	
	XmlPath xt1 = new XmlPath(contents1);
	
		System.out.println("OutFamily:");
		try
		{
		String OutFamilyDedLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamOutNetworkDetails.TieredAccum.DeductibleDetails.DedLimit";
		String OutFamilyDedRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamOutNetworkDetails.TieredAccum.DeductibleDetails.DedRemaining";
		String OutFamilyTotalDedAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamOutNetworkDetails.TieredAccum.DeductibleDetails.TotalDedAccum";
		String OutFamilyOOPLimitXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamOutNetworkDetails.TieredAccum.OOPDetails.OOPLimit";
		String OutFamilyOOPRemainingXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamOutNetworkDetails.TieredAccum.OOPDetails.OOPRemaining";
		String OutFamilyOOPAccumXpath="Envelope.Body.GetMemberDeductibleResponse.GetMemberDeductibleResult.ResponseData.MemberDeductibleTierResponse.FamOutNetworkDetails.TieredAccum.OOPDetails.TotalOopAccum";
		String OutFamilyDedLimit=xt1.getString(OutFamilyDedLimitXpath);
		String OutFamilyDedRemaining=xt1.getString(OutFamilyDedRemainingXpath);
		String OutFamilyTotalDedAccum=xt1.getString(OutFamilyTotalDedAccumXpath);
		String OutFamilyOOPLimit=xt1.getString(OutFamilyOOPLimitXpath);
		String OutFamilyOOPRemaining=xt1.getString(OutFamilyOOPRemainingXpath);
		String OutFamilyOOPAccum=xt1.getString(OutFamilyOOPAccumXpath);
		String OutFamdedLimitUI=driver.findElement(By.xpath("//div[@id='familyOut']/div[1]/div[1]/div[2]/div[2]")).getText().replace(",", "").substring(1);
		String OutFamdedBilledAmountUI=driver.findElement(By.xpath("//div[@id='familyOut']/div[1]/div[1]/div[3]/div[2]")).getText().replace(",", "").substring(1);
		String OutFamRemainingLimitUI=driver.findElement(By.xpath("//div[@id='familyOut']/div[1]/div[1]/div[4]/div[2]")).getText().replace(",", "").substring(1);		
		String OutFamOOPPlanLimitUI=driver.findElement(By.xpath("//div[@id='familyOut']/div[1]/div[5]/div[2]/div[2]")).getText().replace(",", "").substring(1);
		String OutFamOOPBilledAmountUI=driver.findElement(By.xpath("//div[@id='familyOut']/div[1]/div[5]/div[3]/div[2]")).getText().replace(",", "").substring(1);
		String OutFamOOPRemainingLimitUI=driver.findElement(By.xpath("//div[@id='familyOut']/div[1]/div[5]/div[4]/div[2]")).getText().replace(",", "").substring(1);
		System.out.println("OutFamilydedLimitUI"+OutFamdedLimitUI);
		System.out.println("OutFamilyRemainingLimitUI"+OutFamRemainingLimitUI);
		System.out.println("OutFamilydedBilledAmountUI"+OutFamdedBilledAmountUI);
		System.out.println("OutFamilyOOPPlanLimitUI"+OutFamOOPPlanLimitUI);
		System.out.println("OutFamilyOOPRemaining_WS"+OutFamOOPRemainingLimitUI);
		System.out.println("OutFamilyDedLimit_WS"+OutFamilyDedLimit);
		System.out.println("OutFamilyDedRemaining_WS"+OutFamilyDedRemaining);
		System.out.println("OutFamilyTotalDedAccum_WS"+OutFamilyTotalDedAccum);
		System.out.println("OutFamilyOOPLimit_WS"+OutFamilyOOPLimit);
		System.out.println("OutFamilyOOPRemaining_WS"+OutFamilyOOPRemaining);
		Assert.assertTrue(OutFamdedLimitUI.contains(OutFamilyDedLimit), "Out Family Ded Limit is wrongly populated");
		Assert.assertTrue(OutFamRemainingLimitUI.contains(OutFamilyDedRemaining), "Out Family Ded Remaining is wrongly populated");
		Assert.assertTrue(OutFamdedBilledAmountUI.contains(OutFamilyTotalDedAccum),"Out Family Med Billed Amount is wrongly populated");
		Assert.assertTrue(OutFamOOPPlanLimitUI.contains(OutFamilyOOPLimit), "Out Family OOP Plan Limit is wrongly populated");
		Assert.assertTrue(OutFamOOPRemainingLimitUI.contains(OutFamilyOOPRemaining), "Out Family OOP Remaining is wrongly populated");
		Assert.assertTrue(OutFamOOPBilledAmountUI.contains(OutFamilyOOPAccum),"Out Family OOP Billed Amount is wrongly populated");
		
		}
		catch(NumberFormatException e)
		{
			System.out.println(e);
		}
}
}
	


	