package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.velocity.runtime.parser.node.MathUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import gherkin.formatter.Argument;
import io.restassured.path.xml.XmlPath;

public class MemberEligibilityResultsPage extends TestBase{

	public MemberEligibilityResultsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	//Page Factory
	
	By EligibilityResultsTable = By.xpath("//div[@id='eachTable']");
	By EligibilityResultsHeading = By.xpath("//div[contains(text(),'Eligibility Results')]");
//	By BenefitCode = By.xpath("//div[@id='eachTable']//tr[10]//td[2]//div//div//a//span");
	By BenefitCode = By.xpath("//div[@id='eachTable']//tbody//tr[10]//td[2]//span");
	By MedicaidBenefitcodeUI = By.xpath("//div[@id='eachTable']//tbody//tr[11]//td[2]//span");
	By EffectiveDateUI = By.xpath("//div[@id='eachTable']//tr[6]//td[2]//span");
	By MedicaidEffectiveDateUI = By.xpath("//div[@id='eachTable']//tr[7]//td[2]//span");
	By Planandnetwork = By.xpath("//div[@id='eachTable']//tr[5]//td[2]//span");
	By MedicaidPlanandnetworkUI = By.xpath("//div[@id='eachTable']//tr[6]//td[2]//span");
	By MemberNameLink = By.xpath("//div[@id='eachTable']//tr//td[2]//span");
	
	public void memberEligibilityvalidations(String MemberId, String responseXMLPath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String MemberIdValue = (String ) ScenarioContext.getContext(MemberId);
		String xpath = null;
		String xpathtoProductID = null;
		String xpathtoEffDate = null;
		String xpathtoplan = null;
		String xpathtoNetwork = null;
		String xpathtoMedicId = null;
	
		String xpathToMember = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member";
		int NumberofMembers = GetnumberofTags(responseXMLPath, xpathToMember);
		for(int i=0;i<NumberofMembers;i++) {
			String xpathToMemberName = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member["+i+"].MemberId";
			String contents = new String(Files.readAllBytes(
					Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
			
			XmlPath xt = new XmlPath(contents);
		String MemIDinResponse = xt.getString(xpathToMemberName);
		if(MemIDinResponse.equals(MemberIdValue)) {
		xpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].EligibilityInfo.Eligibility";
		xpathtoProductID = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].EligibilityInfo.Eligibility[0].ProductId";
		xpathtoEffDate = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].EligibilityInfo.Eligibility[0].EffectiveDate";
		xpathtoplan = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].EligibilityInfo.Eligibility[0].BenefitPlanDesc";
		xpathtoNetwork = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].ProviderHistory.Provider[0].NetworkName";
		xpathtoMedicId = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+i+"].MedicaidNumber";
		}	
		}
		int NumberofTags = compareMedandRxproduct(responseXMLPath);
		validatenumberofresultsloadediscorrect(NumberofTags);
		ValidateBenefitCode(responseXMLPath, xpathtoProductID,xpathtoMedicId);
		ValidateEffectivedate(responseXMLPath, xpathtoEffDate,xpathtoMedicId);
		ValidatePlanandNetwork(responseXMLPath, xpathtoplan,xpathtoNetwork,xpathtoMedicId);
	}
	
	public int compareMedandRxproduct(String responseXMLPath) throws IOException {
		 int NumberofExpectedResults = 0;
		String xpath = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[0].EligibilityInfo.Eligibility";
		int NumberofTags = GetnumberofTags(responseXMLPath, xpath);
		System.out.println("Number of tags in response: "+NumberofTags);
		String MedicalProductPlanClassID = null;
		String MedicalProductEffDate = null;
		String RxProductPlanClasID = null;
		String RxProductEffDate = null;
		List<String> MedPlanClassIDandEffDate = new ArrayList<String>();
		
		List<String> PharmacyPlanClassIDandEffDate = new ArrayList<String>();
		int Count =0;
		for(int i=0;i<NumberofTags;i++) {
			String xpathtoEligType = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[0].EligibilityInfo.Eligibility["+i+"].EligibilityType";
			String contents = new String(Files.readAllBytes(
					Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
			
			XmlPath xt = new XmlPath(contents);
			String EligType = xt.getString(xpathtoEligType);
			System.out.println("Elig Type-->"+ EligType);
			
			if(EligType.equals("M")) {
				System.out.println("Inside M");
				String xpathtoPlanClassID = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[0].EligibilityInfo.Eligibility["+i+"].PlanClassId";
				String xpathtoEffDate = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[0].EligibilityInfo.Eligibility["+i+"].EffectiveDate";
			MedicalProductPlanClassID = xt.getString(xpathtoPlanClassID);
			System.out.println("Med Plan class: "+ MedicalProductPlanClassID);
			MedicalProductEffDate = xt.getString(xpathtoEffDate);
			System.out.println("Med Eff date: "+ MedicalProductEffDate);
			String MedicalPlanClassandEffdate= MedicalProductPlanClassID+MedicalProductEffDate;
			MedPlanClassIDandEffDate.add(MedicalPlanClassandEffdate);
			}
			else if(EligType.equals("R")){
				System.out.println("Insider R");
				String xpathtoRxPlanClassID = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[0].EligibilityInfo.Eligibility["+i+"].PlanClassId";
				String xpathtoRxEffDate = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member[0].EligibilityInfo.Eligibility["+i+"].EffectiveDate";
				RxProductPlanClasID = xt.getString(xpathtoRxPlanClassID);
				System.out.println("Rx Plan : "+ RxProductPlanClasID);
				RxProductEffDate = xt.getString(xpathtoRxEffDate);
				System.out.println("Rx Eff date : "+ RxProductEffDate);
				String PharmacyPlanClassandEffdate= RxProductPlanClasID+RxProductEffDate;
				PharmacyPlanClassIDandEffDate.add(PharmacyPlanClassandEffdate);
			}
		}
		System.out.println("Medical Array-->"+MedPlanClassIDandEffDate);
		System.out.println("Pharmacy Array-->"+PharmacyPlanClassIDandEffDate);
		
	
	 boolean sameLength = (MedPlanClassIDandEffDate.size() == PharmacyPlanClassIDandEffDate.size());
	 if (!sameLength) {
		 
	 }
	 else {  
		
	    for (int i = 0; i <= MedPlanClassIDandEffDate.size()-1 ; i++) {
	    	System.out.println(i + "Array Iteration");
	    	System.out.println(MedPlanClassIDandEffDate.get(i));
	    	System.out.println(PharmacyPlanClassIDandEffDate.get(i));
	    	
	    	if (MedPlanClassIDandEffDate.get(i).equals(PharmacyPlanClassIDandEffDate.get(i))) {
	    		
	    		Count =Count+1;
	    	
	    	}
	    	else if(NumberofTags==2 && MedPlanClassIDandEffDate.get(i).equals(PharmacyPlanClassIDandEffDate.get(i))) {
	    		Count =1;
	    	}
	    	else if(NumberofTags==2 && !MedPlanClassIDandEffDate.get(i).equals(PharmacyPlanClassIDandEffDate.get(i))) {
	    		Count =2;
	    	}
	    	else if(NumberofTags==1) {
	    		Count=1;
	    	}
	    	System.out.println(Count +"Count");
	    }
	 }    
	
		NumberofExpectedResults=Count;
			System.out.println("Final Number"+ NumberofExpectedResults);
			return NumberofExpectedResults;
}


	
	
	public void memberEligValidationsforSubscriber(String responseXMLPath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String xpathToMember = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber[0].Members.Member";
		int NumberofMembers = GetnumberofTags(responseXMLPath, xpathToMember);
		System.out.println("Number of Member:"+ NumberofMembers);
		int NumberofExpectedResultsforSubscriber = 0;
		int NumberofExpectedResultsforMember= 0;
		String MedicalProductPlanClassID = null;
		String MedicalProductEffDate = null;
		String RxProductPlanClasID = null;
		String RxProductEffDate = null;
		List<Integer> x = new ArrayList<Integer>();
		List<String> MedPlanClassIDandEffDate = new ArrayList<String>();
		
		List<String> PharmacyPlanClassIDandEffDate = new ArrayList<String>();
		ArrayList<Integer> CountforMembers = new ArrayList<Integer>();
		int Count =0;
		for (int j=0;j<NumberofMembers;j++) {
			int CountforMember =0;
			String xpathtoEligibility ="Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+j+"].EligibilityInfo.Eligibility";
			int NumberofTags = GetnumberofTags(responseXMLPath, xpathtoEligibility);
			System.out.println("Iteration for Member:"+j);
			for(int i=0;i<NumberofTags;i++) {
				
				System.out.println("Iteration for Elig "+i+" For member "+j);
				String xpathtoEligType = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+j+"].EligibilityInfo.Eligibility["+i+"].EligibilityType";
				String contents = new String(Files.readAllBytes(
						Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
				
				XmlPath xt = new XmlPath(contents);
				String EligType = xt.getString(xpathtoEligType);
				System.out.println("Elig Type-->"+ EligType);
				
				if(EligType.equals("M")) {
					System.out.println("Inside M");
					String xpathtoPlanClassID = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+j+"].EligibilityInfo.Eligibility["+i+"].PlanClassId";
					String xpathtoEffDate = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+j+"].EligibilityInfo.Eligibility["+i+"].EffectiveDate";
				MedicalProductPlanClassID = xt.getString(xpathtoPlanClassID);
				System.out.println("Med Plan class: "+ MedicalProductPlanClassID);
				MedicalProductEffDate = xt.getString(xpathtoEffDate);
				System.out.println("Med Eff date: "+ MedicalProductEffDate);
				String MedicalPlanClassandEffdate= MedicalProductPlanClassID+MedicalProductEffDate;
				MedPlanClassIDandEffDate.add(MedicalPlanClassandEffdate);
				}
				else if(EligType.equals("R")){
					System.out.println("Insider R");
					String xpathtoRxPlanClassID = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+j+"].EligibilityInfo.Eligibility["+i+"].PlanClassId";
					String xpathtoRxEffDate = "Envelope.Body.GetAllContractsBySubscriberIdResponse.GetAllContractsBySubscriberIdResult.ResponseData.Subscriber.Members.Member["+j+"].EligibilityInfo.Eligibility["+i+"].EffectiveDate";
					RxProductPlanClasID = xt.getString(xpathtoRxPlanClassID);
					System.out.println("Rx Plan : "+ RxProductPlanClasID);
					RxProductEffDate = xt.getString(xpathtoRxEffDate);
					System.out.println("Rx Eff date : "+ RxProductEffDate);
					String PharmacyPlanClassandEffdate= RxProductPlanClasID+RxProductEffDate;
					PharmacyPlanClassIDandEffDate.add(PharmacyPlanClassandEffdate);
				}
			}
			
			System.out.println("Medical Array-->"+MedPlanClassIDandEffDate);
			System.out.println("Pharmacy Array-->"+PharmacyPlanClassIDandEffDate);
			
			boolean sameLength = (MedPlanClassIDandEffDate.size() == PharmacyPlanClassIDandEffDate.size());
			 if (!sameLength) {
				 
			 }
			 else {  
				 
			    for (int i = 0; i <= MedPlanClassIDandEffDate.size()-1 ; i++) {
			    	System.out.println(i + "Array Iteration");
			    	System.out.println(MedPlanClassIDandEffDate.get(i));
			    	System.out.println(PharmacyPlanClassIDandEffDate.get(i));
			    	if (  MedPlanClassIDandEffDate.get(i).equals(PharmacyPlanClassIDandEffDate.get(i))) {
			    		
			    		CountforMember =CountforMember+1;
			    	
			    	}
			    	else if ( NumberofTags==2 && MedPlanClassIDandEffDate.get(i).equals(PharmacyPlanClassIDandEffDate.get(i))) {
			    		
			    		CountforMember =1;
			    	
			    	}
			    	else if(NumberofTags==2 && !MedPlanClassIDandEffDate.get(i).equals(PharmacyPlanClassIDandEffDate.get(i))) {
			    		CountforMember =2;
			    	}
			    	else if(NumberofTags==1) {
			    		CountforMember=1;
			    	}
			    	System.out.println(CountforMember +"Count for member "+ i);
			    	
			    }
			    CountforMembers.add(CountforMember);
			 } 
			 
			 System.out.println("Array Print" + CountforMembers);
		}
		    
		for (int i = 0; i < CountforMembers.size(); i++) {
			System.out.println("Checkpointprint"+Integer.valueOf(CountforMembers.get(i)));
			NumberofExpectedResultsforMember += Integer.valueOf(CountforMembers.get(i));
		}
		
			
		
		
		NumberofExpectedResultsforSubscriber = NumberofExpectedResultsforMember;
		System.out.println("Number of Expet for Subs: "+NumberofExpectedResultsforSubscriber);
		validatenumberofresultsloadediscorrect(NumberofExpectedResultsforSubscriber);
	}

	private void ValidatePlanandNetwork(String responseXMLPath, String xpathtoplan, String xpathtoNetwork, String xpathtoMedicId) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		String Plan = xt.getString(xpathtoplan);
		String MedicaidNumber = xt.getString(xpathtoMedicId);
		
		if(MedicaidNumber.isEmpty()) 
		{
		System.out.println("Plan Name from Response--> " + Plan);
		String Network = xt.getString(xpathtoNetwork);
		System.out.println("Network Name from Response -->" + Network);
		String PlanAndNetworkinUI = driver.findElement(Planandnetwork).getText();
		System.out.println("Plan and Network In UI-->"+PlanAndNetworkinUI);
		softassert.assertTrue(PlanAndNetworkinUI.contains(Plan), "Validating Plan name -->" + Plan + "in UI");
		softassert.assertTrue(PlanAndNetworkinUI.contains(Network), "Validating Network Name-->" + Network + "in UI");
		softassert.assertAll();
	}
		else {
			System.out.println("Plan Name from Response--> " + Plan);
			String Network = xt.getString(xpathtoNetwork);
			System.out.println("Network Name from Response -->" + Network);
			String PlanAndNetworkinUI = driver.findElement(MedicaidPlanandnetworkUI).getText();
			softassert.assertTrue(PlanAndNetworkinUI.contains(Plan), "Validating Plan name -->" + Plan + "in UI");
			softassert.assertTrue(PlanAndNetworkinUI.contains(Network), "Validating Network Name-->" + Network + "in UI");
			softassert.assertAll();
		}
	}

	public void ValidateEffectivedate(String responseXMLPath, String xpathtoEffDate, String xpathtoMedicId) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		String EffectiveDate = xt.getString(xpathtoEffDate);
		String MedicAidNumber = xt.getString(xpathtoMedicId);
		if(MedicAidNumber.isEmpty()) {
			System.out.println("Effective date from Response -->" + EffectiveDate);
			String EffectivedateinUI = driver.findElement(EffectiveDateUI).getText();
			System.out.println("Effective date in UI -->" + EffectivedateinUI);
			String Effdatefromresponse = EffectiveDate.substring(0, 10);
			String ExpectedEffectivedate = CommonMethods.returnConverteddateformatMMDDYYYY(Effdatefromresponse);
			System.out.println("Expected Eff Date from Respone -->" + ExpectedEffectivedate);
			softassert.assertEquals(EffectivedateinUI, ExpectedEffectivedate , "Validating Effective/Term dates");
			softassert.assertAll();
		}
	else{
		System.out.println("Effective date from Response -->" + EffectiveDate);
		String EffectivedateinUI = driver.findElement(MedicaidEffectiveDateUI).getText();
		System.out.println("Effective date in UI -->" + EffectivedateinUI);
		String Effdatefromresponse = EffectiveDate.substring(0, 10);
		String ExpectedEffectivedate = CommonMethods.returnConverteddateformatMMDDYYYY(Effdatefromresponse);
		System.out.println("Expected Eff Date from Respone -->" + ExpectedEffectivedate);
		softassert.assertEquals(EffectivedateinUI, ExpectedEffectivedate , "Validating Effective/Term dates");
		softassert.assertAll();
	}
	}
	private void ValidateBenefitCode(String responseXMLPath, String xpathtoProductID, String xpathtoMedicId) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		
		XmlPath xt = new XmlPath(contents);
		String ProductID = xt.getString(xpathtoProductID);
		String MedicaidNumber = xt.getString(xpathtoMedicId);
		System.out.println("Medicaid number -->"+ MedicaidNumber);
		if(MedicaidNumber.isEmpty()) 
		{
		System.out.println("Product ID from Response -->" + ProductID);
		String BenefitCodeinUI = driver.findElement(BenefitCode).getText();
		System.out.println("Benefit Code in UI -->" + BenefitCodeinUI);
		softassert.assertEquals(ProductID, BenefitCodeinUI, "Validating whether Benefit Code is Correct or not in Member Elig Results");
		softassert.assertTrue(ProductID.equalsIgnoreCase(BenefitCodeinUI), "Validating whether Benefit Code is Correct or not in Member Elig Results");
		softassert.assertAll();
	}
		else {
			System.out.println("Product ID from Response Medicaid -->" + ProductID);
			String BenefitCodeinUI = driver.findElement(MedicaidBenefitcodeUI).getText();
			System.out.println("Medicaid - Benefit Code in UI -->" + BenefitCodeinUI);
			softassert.assertEquals(ProductID, BenefitCodeinUI, "Validating whether Benefit Code is Correct or not in Member Elig Results");
			softassert.assertTrue(ProductID.equalsIgnoreCase(BenefitCodeinUI), "Validating whether Benefit Code is Correct or not in Member Elig Results");
			softassert.assertAll();
		}
	}
	public void validatenumberofresultsloadediscorrect(int numberofTags) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(10000);
		System.out.println("Number of Expected Results -->" + numberofTags);
		driver.manage().timeouts().implicitlyWait(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(MemberNameLink));
		wait.until(ExpectedConditions.elementToBeClickable(EligibilityResultsHeading));
		List<WebElement> EligibilityResults = driver.findElements(EligibilityResultsTable);
		System.out.println("Number of Results in UI--> "+ EligibilityResults.size()) ;
		int ActualNumberofResults = EligibilityResults.size();
		softassert.assertTrue(numberofTags == ActualNumberofResults, "Number of Eligibility Results Table is not dispalyed as Expected ");
		softassert.assertAll();
	}

	public int GetnumberofTags(String responseXMLPath, String xpath) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		
		List<org.w3c.dom.Element> ListofTags = xt.getList(xpath);
		int numberofTagsVal = ListofTags.size();
	
		return numberofTagsVal;
	}

	public void verifyresultsloadedforallthemembers(String memberID1, String memberID2, String memberID3) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(10000);
		String MemberID1 = (String) ScenarioContext.getContext(memberID1);
		String MemberID2 = (String) ScenarioContext.getContext(memberID2);
		String MemberID3 = (String) ScenarioContext.getContext(memberID3);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(MemberNameLink));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(EligibilityResultsTable));
		List<WebElement> EligibilityResults = driver.findElements(EligibilityResultsTable);
		int ActualNumberofResults = EligibilityResults.size();
		List<String> ids = new ArrayList<String>(); 
		for(int i=1; i<=ActualNumberofResults; i++) {
			String MemberIDsinUI = driver.findElement(By.xpath("(//div[@id='eachTable'])[" + i + "]//tr[2]//td[2]//span")).getText();
			ids.add(MemberIDsinUI);
		}
		System.out.println("List of IDs in UI -->" + ids);
		softassert.assertTrue(ids.contains(MemberID1), "Validating Result loaded for 1st Memberid -->" + MemberID1);
		softassert.assertTrue(ids.contains(MemberID2), "Validating Result loaded for 2nd Memberid -->" + MemberID2);
		softassert.assertTrue(ids.contains(MemberID3), "Validating Result loaded for 3rd Memberid -->" + MemberID3);
		softassert.assertAll();
		
		
		
	}

	public void ClickonMemberNameLink() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(MemberNameLink));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
		WebElement MemberLink = driver.findElement(MemberNameLink);
		js.executeScript("arguments[0].click();", MemberLink);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,TimeUnit.SECONDS);
	}

	public void ContractHistorymemberEligibilityvalidations(String memberId, String responseXMLPath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		ContractHistorymemberEligValidations(responseXMLPath);
		
	}

	
	public void ContractHistorymemberEligValidations(String responseXMLPath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String xpathToMember = "Envelope.Body.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResponse.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResult.ResponseData.Member";
		int NumberofMembers = GetnumberofTags(responseXMLPath, xpathToMember);
		System.out.println("Number of Member:"+ NumberofMembers);
		int NumberofExpectedResultsforSubscriber = 0;
		int NumberofExpectedResultsforMember= 0;
		String MedicalProductPlanClassID = null;
		String MedicalProductEffDate = null;
		String RxProductPlanClasID = null;
		String RxProductEffDate = null;
		List<Integer> x = new ArrayList<Integer>();
		List<String> MedPlanClassIDandEffDate = new ArrayList<String>();
		ArrayList<Integer> CountforMembers = new ArrayList<Integer>();
		List<String> PharmacyPlanClassIDandEffDate = new ArrayList<String>();
		int Count =0;
		for (int j=0;j<NumberofMembers;j++) {
			int CountforMember =0;
			String xpathtoEligibility ="Envelope.Body.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResponse.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResult.ResponseData.Member["+j+"].EligibilityInfo.Eligibility";
			int NumberofTags = GetnumberofTags(responseXMLPath, xpathtoEligibility);
			System.out.println("Iteration for Member:"+j);
			for(int i=0;i<NumberofTags;i++) {
				
				System.out.println("Iteration for Elig "+i+" For member "+j);
				String xpathtoEligType = "Envelope.Body.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResponse.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResult.ResponseData.Member["+j+"].EligibilityInfo.Eligibility["+i+"].EligibilityType";
				String contents = new String(Files.readAllBytes(
						Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
				
				XmlPath xt = new XmlPath(contents);
				String EligType = xt.getString(xpathtoEligType);
				System.out.println("Elig Type-->"+ EligType);
				
				
				if(EligType.equals("M")) {
					System.out.println("Inside M");
					String xpathtoPlanClassID = "Envelope.Body.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResponse.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResult.ResponseData.Member["+j+"].EligibilityInfo.Eligibility["+i+"].PlanClassId";
				String xpathtoEffDate = "Envelope.Body.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResponse.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResult.ResponseData.Member["+j+"].EligibilityInfo.Eligibility["+i+"].EffectiveDate";
				MedicalProductPlanClassID = xt.getString(xpathtoPlanClassID);
				System.out.println("Med Plan class: "+ MedicalProductPlanClassID);
				MedicalProductEffDate = xt.getString(xpathtoEffDate);
				System.out.println("Med Eff date: "+ MedicalProductEffDate);
				String MedicalPlanClassandEffdate= MedicalProductPlanClassID+MedicalProductEffDate;
				MedPlanClassIDandEffDate.add(MedicalPlanClassandEffdate);
				}
				else if(EligType.equals("R")){
					System.out.println("Insider R");
					String xpathtoRxPlanClassID = "Envelope.Body.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResponse.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResult.ResponseData.Member["+j+"].EligibilityInfo.Eligibility["+i+"].PlanClassId";
					String xpathtoRxEffDate = "Envelope.Body.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResponse.GetMemberCOBAndEligibilityAndProviderBySubscriberIdAndMemberSuffixResult.ResponseData.Member["+j+"].EligibilityInfo.Eligibility["+i+"].EffectiveDate";
					RxProductPlanClasID = xt.getString(xpathtoRxPlanClassID);
					System.out.println("Rx Plan : "+ RxProductPlanClasID);
					RxProductEffDate = xt.getString(xpathtoRxEffDate);
					System.out.println("Rx Eff date : "+ RxProductEffDate);
					String PharmacyPlanClassandEffdate= RxProductPlanClasID+RxProductEffDate;
					PharmacyPlanClassIDandEffDate.add(PharmacyPlanClassandEffdate);
				}
			}
			System.out.println("Medical Array-->"+MedPlanClassIDandEffDate);
			System.out.println("Pharmacy Array-->"+PharmacyPlanClassIDandEffDate);
			
			boolean sameLength = (MedPlanClassIDandEffDate.size() == PharmacyPlanClassIDandEffDate.size());
			 if (!sameLength) {
				CountforMembers.add(MedPlanClassIDandEffDate.size());
			 }
			 else {  
				 
			    for (int i = 0; i <= MedPlanClassIDandEffDate.size()-1 ; i++) {
			    	System.out.println(i + "Array Iteration");
			    	System.out.println(MedPlanClassIDandEffDate.get(i));
			    	System.out.println(PharmacyPlanClassIDandEffDate.get(i));
			    	
			    	if (MedPlanClassIDandEffDate.get(i).equals(PharmacyPlanClassIDandEffDate.get(i))) {
			    		
			    		CountforMember =CountforMember+1;
			    	
			    	}
			    	else if(NumberofTags==2 && MedPlanClassIDandEffDate.get(i).equals(PharmacyPlanClassIDandEffDate.get(i))) {
			    		CountforMember =2;
			    	}
			    	else if(NumberofTags==2 && !MedPlanClassIDandEffDate.get(i).equals(PharmacyPlanClassIDandEffDate.get(i))) {
			    		CountforMember =2;
			    	}
			    	else if(NumberofTags==1) {
			    		CountforMember=1;
			    	}
			    	System.out.println(CountforMember +"Count");
			    	
			    }
			    CountforMembers.add(CountforMember);
			 } 
		}
		    
		for (int i = 0; i < CountforMembers.size(); i++) {
			NumberofExpectedResultsforMember += Integer.valueOf(CountforMembers.get(i));
		}
		
		NumberofExpectedResultsforSubscriber = NumberofExpectedResultsforMember;
		System.out.println("Number of Expet for Subs: "+NumberofExpectedResultsforSubscriber);
		validatenumberofresultsloadediscorrect(NumberofExpectedResultsforSubscriber);
	}

	
//	private boolean compareMedicalRxContracts(MEAEligibility medicalContract,
//            MEAEligibility rxContract) {
//
// 
//
//        if (medicalContract.getEligibility().getPlanClassId()
//                .equals(rxContract.getEligibility().getPlanClassId())
//                && (medicalContract
//                        .getEligibility()
//                        .getEffectiveDate()
//                        .compareTo(
//                                rxContract.getEligibility().getEffectiveDate()) == 0)
//                && (medicalContract.getEligibility().getTerminationDate()
//                        .compareTo(rxContract.getEligibility()
//                                .getTerminationDate())) == 0) {
//            LOGGER.debug("Medical & RX Contract PlanClassId & dates are same");
//
// 
//
//            return true;
//
// 
//
//        } else {
//            LOGGER.debug("Medical & RX Contract PlanClassId & dates are not same");
//            return false;
//        }
//    }
	
}
