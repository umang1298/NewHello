package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.CustomSoftAssert;
import com.qa.util.ScenarioContext;
import com.qa.util.XMLUtil.XmlParser;

import io.restassured.path.xml.XmlPath;

public class ViewFormsAndDocumentsPage extends TestBase  {

	By BenifitYearVal = By.xpath("//div[contains(text(),'Benefit Year:')]//following::div[1]");
	By LinksForRiderDocuments = By.xpath("//*[@id='memberinfo']/div/span/div/a");
	By SubscriberContract = By.xpath("//*[@id='memberinfo']/div/span/div/div/a");
	By SummaryOfContractChange = By.xpath("(//*[@id='memberinfo']/div/span/div/div/a)[2]");
	By CloseButton =By.xpath("//button[contains(text(),'Close')]");
	By DocumentCouldNotBeLocated = By.xpath("//span[contains(text(),'Plan documents could not be located at this time.')]");
	int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	SoftAssert softassert = new SoftAssert();
//	CustomSoftAssert softassert = new CustomSoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	public ViewFormsAndDocumentsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	public void VerifytheBenifitYear(String benefitStartDate, String benefitEndDate) throws ParseException {
		// TODO Auto-generated method stub
		String ActualBenifitearVal = driver.findElement(BenifitYearVal).getText();
		System.out.println("Actual Benefit Year in the GUI -->" + ActualBenifitearVal);
		String BenefitstartDateFromSoap = (String) ScenarioContext.getContext(benefitStartDate);
		String BenefitendDateFromSoap = (String) ScenarioContext.getContext(benefitEndDate);
		System.out.println(BenefitstartDateFromSoap);
		System.out.println(BenefitendDateFromSoap);
		String GetExpectedBenefityearStart = BenefitstartDateFromSoap.substring(0, 10);
		System.out.println(GetExpectedBenefityearStart);
		String GetExpectedBenefityearEnd = BenefitendDateFromSoap.substring(0, 10);
		System.out.println(GetExpectedBenefityearEnd);
		String ExpectedBenefityearStart = CommonMethods.returnConverteddateformatMMDDYYYY(GetExpectedBenefityearStart);
		String ExpectedBenefityearEnd = CommonMethods.returnConverteddateformatMMDDYYYY(GetExpectedBenefityearEnd);
		String ExpectedBenefityear = (ExpectedBenefityearStart + " - " + ExpectedBenefityearEnd);
		System.out.println("Expected Benefit Year value from SOAP Response" +ExpectedBenefityear);
		Assert.assertEquals(ActualBenifitearVal, ExpectedBenefityear);

	}


	public void VerifytheRidersDocuments(String memberType, String renewalMonth)
			throws InterruptedException, IOException {
		String renewmonthval = (String) ScenarioContext.getContext(renewalMonth);
		List<WebElement> linksForRidersDocs = driver.findElements(LinksForRiderDocuments); // Identify the number of
																							// Link on webpage and
																							// assign into Webelement
																							// List
		int linkCount = linksForRidersDocs.size(); // Count the total Link list on Web Page
		System.out.println("Total Number of Riders documents on webpage = " + (linkCount - 1)); // Print the total count
																								// of links on webpage
		for (int count = 2; count <= linkCount; count++) {
//Get the XPAth and then find the url link- for the Rider Link based on the count
			String RiderLinkXpath = "(//*[@id='memberinfo']/div/span/div/a)[" + count + "]";
			WebElement CurrentRiderDocLink = driver.findElement(By.xpath(RiderLinkXpath));
			Actions actions = new Actions(driver);
			actions.moveToElement(CurrentRiderDocLink);
			actions.perform();
			js.executeScript("arguments[0].click();", CurrentRiderDocLink);
			Thread.sleep(4000);
//Create Array object to switch to the new window
			ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newtabs.get(1));
			System.out.println("Switched to Riders Contract Tab");
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
//Get the URL for the Rider's pdf link
			//Thread.sleep(3000l);
			String PageSource = driver.getPageSource();
			System.out.println("This is the Page Source" + PageSource);
			String RidersContractURL = driver.getCurrentUrl();
			if(PageSource.contains("pdf was not found on this server")) {
				softassert.fail("Rider Document Not Found for link" + RidersContractURL);
				
				driver.close();
				driver.switchTo().window(newtabs.get(0));
			}
			else {
			
			System.out.println("This is the .pdf url->" + RidersContractURL + " " + count);
//Get the Rider document number- last 3 digits before .pdf
			String getRiderDocNumberFromURL = RidersContractURL.substring(RidersContractURL.indexOf(".pdf") - 3);
//Substring to get just the riders document number and then print the document number in console
			String riderDocNumber = getRiderDocNumberFromURL.substring(0, 3);
			System.out.println("This is the Rider Doc #->" + riderDocNumber);

			
				// Call the method to get the PDF content
				String pdfContent = CommonMethods.returnPDFContent(RidersContractURL);
				// Assertions for the PDF content
				System.out.println("This is the PDF Content for " + (count-1) + " " + pdfContent);
				softassert.assertTrue(pdfContent.contains(riderDocNumber), "Testing RiderDocument" + RidersContractURL);
				
				System.out.println("Found the document number inside the PDF!->" + riderDocNumber);

			}
			
			driver.close();
			driver.switchTo().window(newtabs.get(0));
			System.out.println("Switched back to main window");
			//Thread.sleep(3000);
		}
		
		softassert.assertAll();
		
	}

	public void verifysubscribercontract(String memberTypeforSubscriberContract,
			String renewalMonthforSubscriberContract) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String renrewalMonthValFromSOAPResponse = (String) ScenarioContext
				.getContext(renewalMonthforSubscriberContract);
		System.out.println(renrewalMonthValFromSOAPResponse);
		WebElement SubscriberContractLink = driver.findElement(SubscriberContract);
		js.executeScript("arguments[0].click();", SubscriberContractLink);
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		// driver.findElement(SummaryOfContractChange).click();
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		System.out.println("Switched to Subscriber Contract Tab");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		//Thread.sleep(3000l);
		String PageSource = driver.getPageSource();
		System.out.println("This is the Page Source" + PageSource);
		String SubscriberContractURL = driver.getCurrentUrl();
		if(PageSource.contains("pdf was not found on this server")) {
			softassert.fail("Subscriber Contract Document Not Found for URL -->" + SubscriberContractURL );
			driver.close();
			driver.switchTo().window(newtabs.get(0));
		}
		else {
		
		System.out.println("This is the pdf url->"+SubscriberContractURL);
		
		int currentMonthVal = CommonMethods.returnCurrentMonth();
		int result = Integer.parseInt(renrewalMonthValFromSOAPResponse);
		if (result <= currentMonthVal) {

		
		//	Assert.assertTrue(SubscriberContractURL.contains(String.valueOf(currentYear)));
		//	Assert.assertTrue(SubscriberContractURL.contains(memberTypeforSubscriberContract));
			// Call the method to get the PDF content
			String pdfContent = CommonMethods.returnPDFContent(SubscriberContractURL);
			System.out.println("This is the PDF Content for Subscriber Contract" + pdfContent);
			softassert.assertTrue(pdfContent.contains(String.valueOf(currentYear)), "Validating Year for Subscriber Contract" + SubscriberContractURL);
			softassert.assertTrue(pdfContent.contains(memberTypeforSubscriberContract), "Validating Member Type for Subscriber Contract" + SubscriberContractURL);
		}

		else {
			// Assert.assertTrue(SubscriberContractURL.contains(String.valueOf(currentYear -
			// 1)));
			// Assert.assertTrue(SubscriberContractURL.contains(memberTypeforSubscriberContract));
			// Call the method to get the PDF content
			String pdfContent1 = CommonMethods.returnPDFContent(SubscriberContractURL);
			System.out.println("This is the PDF Content for Subscriber Contract" + pdfContent1);
			softassert.assertTrue(pdfContent1.contains(String.valueOf(currentYear - 1)), "Validaing year for Subscriber Contract" + SubscriberContractURL);
			softassert.assertTrue(pdfContent1.contains(memberTypeforSubscriberContract), "Validating Member Type for Subscriber Contract" + SubscriberContractURL);

		}
		
		
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		System.out.println("Switched back to main window");
		//Thread.sleep(3000);
		
	}
		softassert.assertAll();
	}
	public void verifysummaryofcontractchanges(String memberTypeforSOB, String renewalMonthforSOB)
			throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String renrewalMonthValFromSOAPResponse = (String) ScenarioContext.getContext(renewalMonthforSOB);
		WebElement SummaryOfContractChangeLink = driver.findElement(SummaryOfContractChange);
		js.executeScript("arguments[0].click();", SummaryOfContractChangeLink);
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		System.out.println("Switched to Summary of Contract Changes Tab");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		//Thread.sleep(3000l);
		String PageSource = driver.getPageSource();
		System.out.println("This is the Page Source" + PageSource);
		String SummaryofContractChangeURL = driver.getCurrentUrl();
		if(PageSource.contains("pdf was not found on this server")) {
			softassert.fail("Summary of Contract Changes Document Not Found" + SummaryofContractChangeURL);
			driver.close();
			driver.switchTo().window(newtabs.get(0));
		}
		else {
		
		System.out.println(SummaryofContractChangeURL);
		int currentMonthVal = CommonMethods.returnCurrentMonth();
		int result = Integer.parseInt(renrewalMonthValFromSOAPResponse);
		System.out.println("This is result" +result);
		System.out.println("This is Current month val"+ currentMonthVal);
		if (result <= currentMonthVal) {
		
		//	Assert.assertTrue(SummaryofContractChangeURL.contains(String.valueOf(currentYear)));
			System.out.println("inside if");
			// Call the method to get the PDF content
			String pdfContent = CommonMethods.returnPDFContent(SummaryofContractChangeURL);
			System.out.println("This is the PDF Content for Summary Of Contract Changes" + pdfContent);
			softassert.assertTrue(pdfContent.contains(String.valueOf(currentYear)), "Validating year for Summary of Contract Changes" + SummaryofContractChangeURL);
		}
		else {

		
			//Assert.assertTrue(SummaryofContractChangeURL.contains(String.valueOf(currentYear - 1)));
			System.out.println("inside else");
			// Call the method to get the PDF content
			String pdfContent1 = CommonMethods.returnPDFContent(SummaryofContractChangeURL);
			System.out.println("This is the PDF Content for Summary Of Contract Changes" + pdfContent1);
			softassert.assertTrue(pdfContent1.contains(String.valueOf(currentYear - 1)), "Validating year for Summary of Contract Changes" + SummaryofContractChangeURL);
			
		}
		
		
		driver.close();
		driver.switchTo().window(newtabs.get(0));
		System.out.println("Switched back to main window");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
	}
		softassert.assertAll();
	}
	public void verifyhealthinsurancepolicyforPATransitionalMember(String documentToClick, String textToVerify) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String xpathtoHealthInsurancePolicy = "//span[contains(text(),'" + documentToClick + "')]";
		WebElement HealthInsurancePolicy = driver.findElement(By.xpath(xpathtoHealthInsurancePolicy));
		js.executeScript("arguments[0].click();", HealthInsurancePolicy);
		Thread.sleep(4000);
		//driver.findElement(By.xpath(xpathtoHealthInsurancePolicy)).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		System.out.println("Switched to Health Insurance Policy tab");
		//Thread.sleep(3000l);
		String PageSource = driver.getPageSource();
		System.out.println("This is the Page Source" + PageSource);
		String HealthInsurancePolicyURL = driver.getCurrentUrl();
		if(PageSource.contains("pdf was not found on this server")) {
			softassert.fail("Health Insurance Policy Document Not Found for URL -->" + HealthInsurancePolicyURL);
			driver.switchTo().window(newtabs.get(0));
			driver.close();
			
		}
		else {
	
		String pdfContent = CommonMethods.returnPDFContent(HealthInsurancePolicyURL);
		System.out.println("This is the pdf content fo Health Insurance Policy" + pdfContent);
		softassert.assertTrue(pdfContent.contains(textToVerify), "Validating Health Insurance Policy for -->" + textToVerify + "for URL -->" + HealthInsurancePolicyURL);
		
		driver.close();
		driver.switchTo().window(newtabs.get(0));

		System.out.println("Switched back to main window");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
	
	}
		softassert.assertAll();
	}

	public  void ValidateDocumentsfromPEGARESPONSE(String Document, int i) throws InterruptedException {
		
		if(Document.isEmpty()){
			
			System.out.println("No result for Document in Response. Hence Validation not Required");
		}
			else {
			
				System.out.println("This is the Number " + (i+1) + " Document " + Document);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
				String pagesource = driver.getPageSource();
				Assert.assertTrue(pagesource.contains("<span>"+ Document +"</span>"), "The Document Link for " + Document + " is not Displayed in the GUI");
				/*
				 * WebElement ContractDocumentLink =
				 * driver.findElement(By.xpath("//span[contains(text(),'" + Document + "')]"));
				 * js.executeScript("arguments[0].click();", ContractDocumentLink);
				 * //Thread.sleep(1000);
				 * driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,
				 * TimeUnit.SECONDS); ArrayList<String> newtabs = new
				 * ArrayList<String>(driver.getWindowHandles());
				 * driver.switchTo().window(newtabs.get(1));
				 * System.out.println("Switched to Contract documents Tab");
				 * driver.manage().timeouts().implicitlyWait(Constants.Page_Load_TimeOut ,
				 * TimeUnit.SECONDS); Thread.sleep(3000); String PageSource =
				 * driver.getPageSource(); System.out.println("PageSource-> "+PageSource);
				 * 
				 * if(PageSource.contains("application/pdf")) {
				 * 
				 * System.out.println("This is the Page Source" + PageSource);
				 * softassert.assertTrue(PageSource.contains("application/pdf"),
				 * "validating Contract Document is displayed Properly"); driver.close();
				 * driver.switchTo().window(newtabs.get(0));
				 * System.out.println("Switched Back to Main window"); } else { WebElement
				 * DocumentsCouldnotBeLocatedError =
				 * driver.findElement(DocumentCouldNotBeLocated); softassert.fail(
				 * "This Plan Document Could not be located at this time -->" + " " + Document
				 * ); driver.findElement(CloseButton).click(); //driver.close();
				 * driver.switchTo().window(newtabs.get(0));
				 * System.out.println("Switched Back to Main window"); }
				 * 
				 */				}
				
			}
		
	
	// Verify Document Links New Arch
	public void verifyContractDocumentsQHPMemberPegaFlow(String pathtoresponseXML) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		//Reading from file

				String contents = new String(Files.readAllBytes(
						Paths.get(System.getProperty("user.dir") + File.separator + pathtoresponseXML)));

				System.out.println(contents);

				XmlParser xp = new XmlParser(contents);

				String xpathtoContractDocuments = "/CombinedProdBenefitDocs/contracts/displayName";
				String xpathtoBenefitRiders = "/CombinedProdBenefitDocs/benefitRiders/displayName";
				String xpathtoBenefitSummary ="/CombinedProdBenefitDocs/benefitSummary/displayName";
				String xpathtoSBC = "/CombinedProdBenefitDocs/sbc/displayName";
				String xpathtoMemberRiders = "/CombinedProdBenefitDocs/memberRiders/displayName";
				String xpathtoScheduledpages = "/CombinedProdBenefitDocs/schedulePages/displayName";
				String xpathtonoticeofchange = "/CombinedProdBenefitDocs/noticeofChange/displayName";
				
				List<org.w3c.dom.Element> listOfContractsDoc = xp.getElements(xpathtoContractDocuments);
				List<org.w3c.dom.Element> listOfBenefitRiders = xp.getElements(xpathtoBenefitRiders);
				List<org.w3c.dom.Element> listOfbenefitsummary = xp.getElements(xpathtoBenefitSummary);
				List<org.w3c.dom.Element> listOfSBC = xp.getElements(xpathtoSBC);
				List<org.w3c.dom.Element> listOfMemberRiders = xp.getElements(xpathtoMemberRiders);
				List<org.w3c.dom.Element> listOfSchedulePages = xp.getElements(xpathtoScheduledpages);
				List<org.w3c.dom.Element> listOfNoticetoChange = xp.getElements(xpathtonoticeofchange);
			
			
				System.out.println("This is the count of Contract docs-> " + (listOfContractsDoc.size()));
				System.out.println("This is the count of BenefitRider docs-> " + (listOfBenefitRiders.size()));
				System.out.println("This is the count of Benefit Summary docs-> " + (listOfbenefitsummary.size()));
				System.out.println("This is the count of SBC docs-> " + (listOfSBC.size()));
				System.out.println("This is the count of MemberRider docs-> " + (listOfMemberRiders.size()));
				System.out.println("This is the count of SchedulePages docs-> " + (listOfSchedulePages.size()));
				System.out.println("This is the count of NoticeToChange docs-> " + (listOfNoticetoChange.size()));
				
				for(int i = 0; i<listOfContractsDoc.size(); i++) {
					System.out.println("Validating Contracts");
					String Document = listOfContractsDoc.get(i).getTextContent();
				ValidateDocumentsfromPEGARESPONSE(Document, i);	
				}
				for(int i = 0; i<listOfBenefitRiders.size(); i++) {
					System.out.println("Validating Benefit Riders");
					String Document = listOfBenefitRiders.get(i).getTextContent();
					
				ValidateDocumentsfromPEGARESPONSE(Document, i);	
				}
//				for(int i = 0; i<listOfbenefitsummary.size(); i++) {
//					System.out.println("Validating Benefit Summary");
//					String Document = listOfbenefitsummary.get(i).getTextContent();
//					ValidateDocumentsfromPEGARESPONSE(Document, i);	
//				}
				for(int i = 0; i<listOfSBC.size(); i++) {
					System.out.println("Validating SBC");
					String Document = listOfSBC.get(i).getTextContent();
					ValidateDocumentsfromPEGARESPONSE(Document, i);	
				}
				for(int i = 0; i<listOfMemberRiders.size(); i++) {
					System.out.println("Validating Member Riders");
					String Document = listOfMemberRiders.get(i).getTextContent();
					ValidateDocumentsfromPEGARESPONSE(Document, i);	
				}
				for(int i = 0; i<listOfSchedulePages.size(); i++) {
					System.out.println("Validating Schedule Pages");
					String Document = listOfSchedulePages.get(i).getTextContent();
					ValidateDocumentsfromPEGARESPONSE(Document, i);	
				}
				for(int i = 0; i<listOfNoticetoChange.size(); i++) {
					System.out.println("Validating List of Notice Change");
					String Document = listOfNoticetoChange.get(i).getTextContent();
					ValidateDocumentsfromPEGARESPONSE(Document, i);	
				}
				
			softassert.assertAll();
			
	}

	// Document Links validation Method - Old Arch
	public void verifyDocumentLinksforSGTransitionalMember(String memberType, String responseXMLPath, String RenewalMonthKey) throws IOException {
		// TODO Auto-generated method stub
		
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		

		String xpathtoRiders = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider";
		
		List<org.w3c.dom.Element> listOfRiders = xt.getList(xpathtoRiders);
		
		String PageSourceM = driver.getPageSource();
		String RenewalKeyVal = (String) ScenarioContext.getContext(RenewalMonthKey);
		int currentMonthVal = CommonMethods.returnCurrentMonth();
		int result = Integer.parseInt(RenewalKeyVal);
		
		int CountofRiders = listOfRiders.size();
		System.out.println("Count of Riders" + CountofRiders);
		for(int b =0;b<CountofRiders;b++) {
			String XpathtoRiderDocName = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider["+b+"].RiderDescription";
			String RiderDocName = xt.getString(XpathtoRiderDocName);
			System.out.println("Rider Doc Name-->" +RiderDocName);
			String xpathtoRiderCode = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider["+b+"].RiderCode";
			String RiderDocNew =  CommonMethods.ConvertRiderDocName(RiderDocName);
			String RiderCode = xt.getString(xpathtoRiderCode);
			if(RiderCode.startsWith("X") || RiderCode.startsWith("AST")) {
				System.out.println("Ignoring Rider Document --> " + RiderDocNew + " as the Rider Code Starts with 'X' OR 'AST'");
			}
			else {
//				List<WebElement> RiderDocumentsLinksinGUI = driver.findElements(RiderDocumentLinks);
//				String LinkText = RiderDocumentsLinksinGUI.get(b).getText();
//				System.out.println("Link Text -->" +LinkText);
//				softAssert.assertTrue(LinkText.contains(RiderDocName), "Validating Rider Link for " + ProductID + " -->"+RiderDocName);		
				softassert.assertTrue(PageSourceM.contains(RiderDocNew), "Validating Rider Link for -->"+RiderDocNew);
		}
		
		}
		switch(memberType) {
		case "HMO" :
			softassert.assertTrue(PageSourceM.contains("HMO Subscriber Contract"), "Validating Link for HMO Subscriber Contract");
			if (result <= currentMonthVal) {
				softassert.assertTrue(PageSourceM.contains("Summary of contract changes for " + String.valueOf(currentYear)),"Validating Sumamry of Contract change Link for Year");
			}
			else {
				softassert.assertTrue(PageSourceM.contains("Summary of contract changes for " + String.valueOf(currentYear -1)),"Validating Sumamry of Contract change Link for Year");
			}
		break;
		case "ASO" :
			softassert.assertTrue(PageSourceM.contains("Benefit Guide"), "Validating Link for HMO Subscriber Contract");
			if (result <= currentMonthVal) {
				softassert.assertTrue(PageSourceM.contains("Summary of benefit guide changes for " + String.valueOf(currentYear)),"Validating Sumamry of Contract change Link for Year");
			}
			else {
				softassert.assertTrue(PageSourceM.contains("Summary of benefit guide changes for " + String.valueOf(currentYear -1)),"Validating Sumamry of Contract change Link for Year");
			}
			
			break;
		default:
			softassert.assertTrue(PageSourceM.contains("Health Insurance Policy"), "Validating Link for Health Insurance Policy");
			if (result <= currentMonthVal) {
				softassert.assertTrue(PageSourceM.contains("Summary of policy changes for " + String.valueOf(currentYear)),"Validating Sumamry of Policy change Link for Year");
			}
			else {
				softassert.assertTrue(PageSourceM.contains("Summary of policy changes for " + String.valueOf(currentYear -1)),"Validating Sumamry of Policy change Link for Year");
			}
			break;
		}
		
		softassert.assertAll();
	}

	public void verifyDocumentLinksforPATransitionalMember(String memberType, String responseXMLPath, String RenewalMonthKey) throws IOException {
		// TODO Auto-generated method stub
		
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		

		String xpathtoRiders = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider";
		
		List<org.w3c.dom.Element> listOfRiders = xt.getList(xpathtoRiders);
		
		String PageSourceM = driver.getPageSource();
		System.out.println(PageSourceM);
		softassert.assertTrue(PageSourceM.contains("Health Insurance Policy"), "Validating Link for Health Insurance Policy");
		int CountofRiders = listOfRiders.size();
		System.out.println("Count of Riders" + CountofRiders);
		for(int b =0;b<CountofRiders;b++) {
			String XpathtoRiderDocName = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider["+b+"].RiderDescription";
			String RiderDocName = xt.getString(XpathtoRiderDocName);
			System.out.println("Rider Doc Name-->" +RiderDocName);
			
			String RiderDocNew =  CommonMethods.ConvertRiderDocName(RiderDocName);
			System.out.println("RiderDocNew-->" + RiderDocNew);
			String xpathtoRiderCode = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider["+b+"].RiderCode";
			String RiderCode = xt.getString(xpathtoRiderCode);
			if(RiderCode.startsWith("X") || RiderCode.startsWith("AST")) {
				System.out.println("Ignoring Rider Document --> " + RiderDocNew + " as the Rider Code Starts with 'X' OR 'AST'");
			}
			else {
//				List<WebElement> RiderDocumentsLinksinGUI = driver.findElements(RiderDocumentLinks);
//				String LinkText = RiderDocumentsLinksinGUI.get(b).getText();
//				System.out.println("Link Text -->" +LinkText);
//				softAssert.assertTrue(LinkText.contains(RiderDocName), "Validating Rider Link for " + ProductID + " -->"+RiderDocName);		
				softassert.assertTrue(PageSourceM.contains(RiderDocNew), "Validating Rider Link for -->"+RiderDocNew);
		}
		
		}
	
		
		softassert.assertAll();
	}
	
	
}
