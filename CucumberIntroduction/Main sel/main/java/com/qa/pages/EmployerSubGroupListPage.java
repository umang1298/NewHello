package com.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.itextpdf.text.log.SysoCounter;
import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.RestClient;
import com.qa.util.ScenarioContext;
import com.qa.util.XMLUtil.XmlParser;

import io.netty.handler.timeout.TimeoutException;
import io.restassured.path.xml.XmlPath;

public class EmployerSubGroupListPage extends TestBase {

	Properties prop = initialize_properties();
	// Page Factory
	By subGroupIDLinks = By.xpath("//div[2]/table/tbody/tr/td[1]/b");
	By ProductLinks = By.xpath("(//div[2]/table/tbody/tr/td[2]/b/a)");
	By SubscriberContract1 = By.xpath("//*[@id='applRightPane']/form/a[1]/b");
	By SummaryOfContractChange1 = By.xpath("//*[@id='applRightPane']/form/a[2]/b");
	By RiderDocumentLinks = By.xpath("//*[@id='applRightPane']/form/a");
	By BackButton = By.xpath("//span[text()='Back']");
	By ContractDocumentLinks = By.xpath("//*[@id='applRightPane']/form/a/b");
	By SBCProductLinks = By.xpath("//div[@id='applRightPane']//table//tr[1]//td[2]//a");
	By CloseButton =By.xpath("//button[contains(text(),'Close')]");
	By DocumentCouldNotBeLocated = By.xpath("//p[contains(text(),'Plan documents could not be located at this time.')]");
	By ProductCode = By.xpath("//label[contains(text(),'Product Code:')]");
	

	SoftAssert softAssert = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public EmployerSubGroupListPage(WebDriver driver) {

		TestBase.driver = driver;

	}

	public void getCountOfSubGroupIds(String subGroupIdCount) {
		// Identify the number of subGroup links and assign to Webelement List
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(subGroupIDLinks)));
		List<WebElement> linkscountSubGroup = driver.findElements(subGroupIDLinks);
		int linkCount = linkscountSubGroup.size();
		System.out.println(linkCount);
		// Count the total Link list on Web Page
		System.out.println("Total Number of subGroups = " + linkCount);
		// Print the total count // of links //
		ScenarioContext.setContext(subGroupIdCount, linkCount);
		System.out.println(subGroupIdCount);
	}

	public void verifyDocumentsForBenifitCodes(String GroupID, String countOfSubGroups, String renewalKey,
			String memberType, String Dateval) throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

		int countOfSubGroupsVal = (int) ScenarioContext.getContext(countOfSubGroups);
		System.out.println(countOfSubGroupsVal);
		for (int i = 1; i <= countOfSubGroupsVal; i++) {

			String GetSubgroupID = driver.findElement(By.xpath("(//div[2]/table/tbody/tr/td[1]/b)[" + i + "]"))
					.getText();
			String SubgroupID = GetSubgroupID.substring(2, 6);
			System.out.println("This is the subgroupID-> " + SubgroupID + " for " + i);
			// SetupSoapRequest
			String dateVal = (String) ScenarioContext.getContext(Dateval);
			System.out.println(dateVal);
			String Groupid = (String) ScenarioContext.getContext(GroupID);
			System.out.println(Groupid);
			String xMLPath = "XMLs/GetProductsbySubgroup.xml";
			APIMethods.setUpSOAPRequestForGetProductsBySubGroup(Groupid, dateVal, SubgroupID, xMLPath);
			// SendSoapRequest
			String endPointURL = prop.getProperty("EndpointURLBenifitService");
			String PathtoXML = "//XMLs//GetProductsbySubgroup.xml";
			String xPathToRenewalMonth = "Envelope.Body.GetProductsbySubgroupResponse.GetProductsbySubgroupResult.ResponseData.Group[1].SubgroupRenewalMonth";
			APIMethods.SendSOAPRequestforGetProductsBySubgroup(endPointURL, PathtoXML, xPathToRenewalMonth, renewalKey);
			String RenewalKeyVal = (String) ScenarioContext.getContext(renewalKey);
			System.out.println("This is the renewal key for subgroupID- " + SubgroupID + " count- " + i
					+ " renewalkeyval- " + RenewalKeyVal);
			// Get the count of Products within the Subgroup
			List<WebElement> ProductsCount = driver.findElements(ProductLinks);
			int CountofProducts = ProductsCount.size();
			System.out
					.println("For this subgroup->" + SubgroupID + " this is the count of products-" + CountofProducts);
			// Read the documents by Products
			for (int x = 1; x <= CountofProducts; x++) {

				String BenifitCodeXPath1 = "(//div[2]/table/tbody/tr/td[1]/b)[" + i + "]/following::a[" + x + "]";

				String ProductID = driver.findElement(By.xpath(BenifitCodeXPath1)).getText();
				//Thread.sleep(2000);
				System.out.println("This is the product id for the product id count-" + x + " " + ProductID
						+ " of the subgroup " + SubgroupID);
				// Get the first letter of the product id
				String ProductIDFirstLetter = ProductID.substring(0, 1);
				System.out.println("This is the first letter of the product id-> " + ProductIDFirstLetter);
				//Thread.sleep(1000);
				// Click on the Product Code
				driver.findElement(By.xpath(BenifitCodeXPath1)).click();
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

				switch (ProductIDFirstLetter) {
				case "X":
					List<WebElement> RiderDocumentsCount = driver.findElements(RiderDocumentLinks);
					int NumberofRiderDocuments = RiderDocumentsCount.size();
					System.out.println("This is the number of total Rider documents for " + ProductID + "- "
							+ NumberofRiderDocuments);

					for (int k = 1; k <= NumberofRiderDocuments; k++) {

						String RiderDocumentsxpath = "//*[@id='applRightPane']/form/a[" + k + "]";
						WebElement RiderDocLink = driver.findElement(By.xpath(RiderDocumentsxpath));

						js.executeScript("arguments[0].click();", RiderDocLink);
						Thread.sleep(4000);
						// driver.findElement(By.xpath(RiderDocumentsxpath)).click();
						ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
						driver.switchTo().window(newtabs.get(1));
						//Thread.sleep(3000);
						String PageSource = driver.getPageSource();
						System.out.println("This is the Page Source" + PageSource);
						String RiderDocumentURL = driver.getCurrentUrl();
						if (PageSource.contains("pdf was not found on this server")) {
							softAssert.fail("Document Not Found for the " + i + " Subgroup " + ProductID
									+ " Product for url -->" + RiderDocumentURL);
							driver.close();
							driver.switchTo().window(newtabs.get(0));
						} else {

							String pdfContent = CommonMethods.returnPDFContent(RiderDocumentURL);
							System.out.println("This is the PDF Content of" + k + " rider Document  for the product "
									+ ProductID + pdfContent);
							String getRiderDocNumberFromURL = RiderDocumentURL
									.substring(RiderDocumentURL.indexOf(".pdf") - 3);
							String riderDocNumber = getRiderDocNumberFromURL.substring(0, 3);
							softAssert.assertTrue(pdfContent.contains(riderDocNumber),
									"Validating Employer Rider Document for " + ProductID + "for url"
											+ RiderDocumentURL);

							driver.close();
							driver.switchTo().window(newtabs.get(0));
							driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,
									TimeUnit.SECONDS);
							System.out.println("Verified Contract Documentsfor the " + i + " Subgroup " + ProductID
									+ " Product " + k + " RiderDocument ");
							//Thread.sleep(2000l);

						}
					}
					break;

				default:

					List<WebElement> ContractDocumentsCount = driver.findElements(ContractDocumentLinks);
					List<WebElement> RiderDocumentsCount1 = driver.findElements(RiderDocumentLinks);
					int NumberofContractDocuments = ContractDocumentsCount.size();
					int NumberofRiderDocuments1 = RiderDocumentsCount1.size();
					System.out.println(
							"This is the number of total documents for " + ProductID + "-" + NumberofRiderDocuments1);
					System.out.println("This is the number of contract documents for " + ProductID + "-"
							+ NumberofContractDocuments);

					for (int j = 1; j <= NumberofContractDocuments; j++) {

						String ContractDocumentsxpath = "//*[@id='applRightPane']/form/a[" + j + "]/b";
						WebElement ContractDocLink = driver.findElement(By.xpath(ContractDocumentsxpath));

						js.executeScript("arguments[0].click();", ContractDocLink);
						Thread.sleep(4000);
						// driver.findElement(By.xpath(ContractDocumentsxpath)).click();
						driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
						ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
						driver.switchTo().window(newtabs.get(1));
						//Thread.sleep(3000);
						String PageSource = driver.getPageSource();
						System.out.println("This is the Page Source" + PageSource);
						String ContractDocumentURL = driver.getCurrentUrl();
						if (PageSource.contains("pdf was not found on this server")) {
							softAssert.fail("Document Not Found for the " + i + " Subgroup " + ProductID
									+ " Product for url --> " + ContractDocumentURL);
							driver.close();
							driver.switchTo().window(newtabs.get(0));
						} else {

							int currentMonthVal = CommonMethods.returnCurrentMonth();
							int result = Integer.parseInt(RenewalKeyVal);
							System.out.println("Thsi is current month val -->" + currentMonthVal);
							System.out.println("This is result -->" + result);
							if (result <= currentMonthVal) {
								System.out.println(memberType);
								System.out.println("Inside If Loop");

								// softAssert.softAssertTrue(ContractDocumentURL.contains(memberType));
								String pdfContent = CommonMethods.returnPDFContent(ContractDocumentURL);
								System.out.println("This is the PDF Content of" + j
										+ " Contract Document  for the product " + ProductID + pdfContent);
								softAssert.assertTrue(pdfContent.contains(String.valueOf(currentYear)),
										"Validating Employer Contract Documents for YEAR for" + ProductID
												+ "for URL -->" + ContractDocumentURL);
							} else {
								System.out.println("Inside else loop");
								// softAssert.softAssertTrue(ContractDocumentURL.contains(memberType));
								String pdfContent = CommonMethods.returnPDFContent(ContractDocumentURL);
								System.out.println("This is the PDF Content of" + j
										+ " Contract Document  for the product " + ProductID + pdfContent);
								softAssert.assertTrue(pdfContent.contains(String.valueOf(currentYear - 1)),
										"Validating Employer Contract Documents for YEAR for" + ProductID
												+ "for URL -->" + ContractDocumentURL);
							}

							driver.close();
							driver.switchTo().window(newtabs.get(0));
							driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,
									TimeUnit.SECONDS);
							System.out.println("Verified Contract Documentsfor the " + i + " Subgroup " + ProductID
									+ " Product " + j + " ContractDocument ");
							//Thread.sleep(2000l);
						}
					}
					// Validate Rider Documents- For medical doc the rider doc index start with
					// index of 3 and for medical it is 1

					for (int k = 3; k <= NumberofRiderDocuments1; k++) {

						String RiderDocumentsxpath = "//*[@id='applRightPane']/form/a[" + k + "]";
						System.out.println(RiderDocumentsxpath);
						WebElement RiderDocLink = driver.findElement(By.xpath(RiderDocumentsxpath));
						js.executeScript("arguments[0].click();", RiderDocLink);
						Thread.sleep(4000);
						// driver.findElement(By.xpath(RiderDocumentsxpath)).click();
						ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
						driver.switchTo().window(newtabs.get(1));
						//Thread.sleep(3000);
						String PageSource = driver.getPageSource();
						System.out.println("This is the Page Source" + PageSource);
						String RiderDocumentURL = driver.getCurrentUrl();
						if (PageSource.contains("pdf was not found on this server")) {
							softAssert.fail("Document Not Found for the " + i + " Subgroup " + ProductID
									+ " Product for URL -->" + RiderDocumentURL);
							driver.close();
							driver.switchTo().window(newtabs.get(0));
						} else {

							String pdfContent = CommonMethods.returnPDFContent(RiderDocumentURL);
							System.out.println("This is the PDF Content of" + (k - 2)
									+ " Rider Document  for the product " + ProductID + pdfContent);
							String getRiderDocNumberFromURL = RiderDocumentURL
									.substring(RiderDocumentURL.indexOf(".pdf") - 3);
							String riderDocNumber = getRiderDocNumberFromURL.substring(0, 3);
							softAssert.assertTrue(pdfContent.contains(riderDocNumber),
									"Validating Employer Rider Document for " + ProductID + "Product for URL -->"
											+ RiderDocumentURL);
							driver.close();
							driver.switchTo().window(newtabs.get(0));
							driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,
									TimeUnit.SECONDS);
							System.out.println("Verified Contract Documentsfor the " + i + " Subgroup " + ProductID
									+ " Product " + (k - 2) + " RiderDocument ");
						}
					}
					break;
				}

				driver.findElement(BackButton).click();

			//	Thread.sleep(4000l);
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(subGroupIDLinks)));
			}

			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

		}

		softAssert.assertAll();

	}

	public void verifyDocumentsForBenefitCodesPATransitional(String groupID, String countofSubgroupIds,
			String renwalMonthKey, String memberType, String Dateval, String texttovalidate)
			throws ClientProtocolException, IOException, InterruptedException {
		// TODO Auto-generated method stub

		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

		int countOfSubGroupsVal = (int) ScenarioContext.getContext(countofSubgroupIds);
		System.out.println(countOfSubGroupsVal);
		for (int i = 1; i <= countOfSubGroupsVal; i++) {

			String GetSubgroupID = driver.findElement(By.xpath("(//div[2]/table/tbody/tr/td[1]/b)[" + i + "]"))
					.getText();
			String SubgroupID = GetSubgroupID.substring(2, 6);
			System.out.println("This is the subgroupID-> " + SubgroupID + " for " + i);
			// SetupSoapRequest
			String dateVal = (String) ScenarioContext.getContext(Dateval);
			System.out.println(dateVal);
			String Groupid = (String) ScenarioContext.getContext(groupID);
			System.out.println(Groupid);
			String xMLPath = "XMLs/GetProductsbySubgroup.xml";
			APIMethods.setUpSOAPRequestForGetProductsBySubGroup(Groupid, dateVal, SubgroupID, xMLPath);
			// SendSoapRequest
			String endPointURL = prop.getProperty("EndpointURLBenifitService");
			String PathtoXML = "//XMLs//GetProductsbySubgroup.xml";
			String xPathToRenewalMonth = "Envelope.Body.GetProductsbySubgroupResponse.GetProductsbySubgroupResult.ResponseData.Group[1].SubgroupRenewalMonth";
			APIMethods.SendSOAPRequestforGetProductsBySubgroup(endPointURL, PathtoXML, xPathToRenewalMonth,
					renwalMonthKey);
			String RenewalKeyVal = (String) ScenarioContext.getContext(renwalMonthKey);
			System.out.println("This is the renewal key for subgroupID- " + SubgroupID + " count- " + i
					+ " renewalkeyval- " + RenewalKeyVal);
			// Get the count of Products within the Subgroup

			List<WebElement> ProductsCount = driver.findElements(ProductLinks);
			int CountofProducts = ProductsCount.size();
			System.out
					.println("For this subgroup->" + SubgroupID + " this is the count of products-" + CountofProducts);
			// Read the documents by Products
			for (int x = 1; x <= CountofProducts; x++) {

				String BenifitCodeXPath1 = "(//div[2]/table/tbody/tr/td[1]/b)[" + i + "]/following::a[" + x + "]";
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ProductLinks)));
				String ProductID = driver.findElement(By.xpath(BenifitCodeXPath1)).getText();
				System.out.println("This is the product id for the product id count-" + x + " " + ProductID
						+ " of the subgroup " + SubgroupID);
				// Get the first letter of the product id
				String ProductIDFirstLetter = ProductID.substring(0, 1);
				System.out.println("This is the first letter of the product id-> " + ProductIDFirstLetter);
				Thread.sleep(1000);
				// Click on the Product Code
				driver.findElement(By.xpath(BenifitCodeXPath1)).click();
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

				switch (ProductIDFirstLetter) {
				case "X":
					List<WebElement> RiderDocumentsCount = driver.findElements(RiderDocumentLinks);
					int NumberofRiderDocuments = RiderDocumentsCount.size();
					System.out.println("This is the number of total Rider documents for " + ProductID + "- "
							+ NumberofRiderDocuments);

					for (int k = 1; k <= NumberofRiderDocuments; k++) {

						String RiderDocumentsxpath = "//*[@id='applRightPane']/form/a[" + k + "]";
						WebElement RiderDocLink = driver.findElement(By.xpath(RiderDocumentsxpath));

						js.executeScript("arguments[0].click();", RiderDocLink);
						Thread.sleep(4000);
						// driver.findElement(By.xpath(RiderDocumentsxpath)).click();
						ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
						driver.switchTo().window(newtabs.get(1));
						Thread.sleep(3000);
						String PageSource = driver.getPageSource();
						System.out.println("This is the Page Source" + PageSource);
						String RiderDocumentURL = driver.getCurrentUrl();
						if (PageSource.contains("pdf was not found on this server")) {
							softAssert.fail("Document Not Found for the " + i + " Subgroup " + ProductID
									+ " Product for url --> " + RiderDocumentURL);
							driver.close();
							driver.switchTo().window(newtabs.get(0));
						} else {

							String pdfContent = CommonMethods.returnPDFContent(RiderDocumentURL);
							System.out.println("This is the PDF Content of" + k + " rider Document  for the product "
									+ ProductID + pdfContent);
							String getRiderDocNumberFromURL = RiderDocumentURL
									.substring(RiderDocumentURL.indexOf(".pdf") - 3);
							String riderDocNumber = getRiderDocNumberFromURL.substring(0, 3);
							softAssert.assertTrue(pdfContent.contains(riderDocNumber),
									"Validating Employer Rider Document for " + ProductID + "for url -->"
											+ RiderDocumentURL);

							driver.close();
							driver.switchTo().window(newtabs.get(0));
							driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,
									TimeUnit.SECONDS);
							System.out.println("Verified Rider Documentsfor the " + i + " Subgroup " + x + " Product "
									+ k + " RiderDocument ");
							Thread.sleep(2000l);
						}
					}
					break;

				default:

					List<WebElement> ContractDocumentsCount = driver.findElements(ContractDocumentLinks);
					List<WebElement> RiderDocumentsCount1 = driver.findElements(RiderDocumentLinks);
					int NumberofContractDocuments = ContractDocumentsCount.size();
					int NumberofRiderDocuments1 = RiderDocumentsCount1.size();
					System.out.println(
							"This is the number of total documents for " + ProductID + "-" + NumberofRiderDocuments1);
					System.out.println("This is the number of contract documents for " + ProductID + "-"
							+ NumberofContractDocuments);

					for (int j = 1; j <= NumberofContractDocuments; j++) {

						String ContractDocumentsxpath = "//*[@id='applRightPane']/form/a[" + j + "]/b";
						WebElement ContractDocLink = driver.findElement(By.xpath(ContractDocumentsxpath));
						js.executeScript("arguments[0].click();", ContractDocLink);
						Thread.sleep(4000);
						// driver.findElement(By.xpath(ContractDocumentsxpath)).click();
						driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
						ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
						driver.switchTo().window(newtabs.get(1));
						Thread.sleep(3000);
						String PageSource = driver.getPageSource();
						System.out.println("This is the Page Source" + PageSource);
						String ContractDocumentURL = driver.getCurrentUrl();
						if (PageSource.contains("pdf was not found on this server")) {
							softAssert.fail("Document Not Found for the " + i + " Subgroup " + ProductID
									+ " Product for url --> " + ContractDocumentURL);
							driver.close();
							driver.switchTo().window(newtabs.get(0));
						} else {

							int currentMonthVal = CommonMethods.returnCurrentMonth();
							int result = Integer.parseInt(RenewalKeyVal);
							if (result <= currentMonthVal) {
								System.out.println(memberType);
								// softAssert.softAssertTrue(ContractDocumentURL.contains(memberType));
								String pdfContent = CommonMethods.returnPDFContent(ContractDocumentURL);
								System.out.println("This is the PDF Content of" + j
										+ " Contract Document  for the product " + ProductID + pdfContent);
								// softAssert.assertTrue(pdfContent.contains(String.valueOf(currentYear)),
								// "Validating Employer Contract Document for year for" + ProductID + "for url
								// --> " + ContractDocumentURL);
								softAssert.assertTrue(pdfContent.contains(texttovalidate),
										"Validating Employer Contract Document for " + texttovalidate + "for"
												+ ProductID + "for url -->" + ContractDocumentURL);
							} else {
								// softAssert.softAssertTrue(ContractDocumentURL.contains(memberType));
								String pdfContent = CommonMethods.returnPDFContent(ContractDocumentURL);
								System.out.println("This is the PDF Content of" + j
										+ " Contract Document  for the product " + ProductID + pdfContent);
								// softAssert.assertTrue(pdfContent.contains(String.valueOf(currentYear - 1)),
								// "Validating Employer Contract Document for year for" + ProductID + "for url
								// -->" + ContractDocumentURL);
								softAssert.assertTrue(pdfContent.contains(texttovalidate),
										"Validating Employer Contract Document for " + texttovalidate + "for"
												+ ProductID + "for url -->" + ContractDocumentURL);
							}

							driver.close();
							driver.switchTo().window(newtabs.get(0));
							driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,
									TimeUnit.SECONDS);
							System.out.println("Verified Contract Documentsfor the " + i + " Subgroup " + x
									+ " Product " + j + " ContractDocument ");
							Thread.sleep(2000l);
						}
					}
					// Validate Rider Documents- For medical doc the rider doc index start with
					// index of 3 and for medical it is 1

					for (int k = 2; k <= NumberofRiderDocuments1; k++) {

						String RiderDocumentsxpath = "//*[@id='applRightPane']/form/a[" + k + "]";
						System.out.println(RiderDocumentsxpath);
						WebElement RiderDocLink = driver.findElement(By.xpath(RiderDocumentsxpath));
						js.executeScript("arguments[0].click();", RiderDocLink);
						Thread.sleep(4000);
						// driver.findElement(By.xpath(RiderDocumentsxpath)).click();
						ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
						driver.switchTo().window(newtabs.get(1));
						Thread.sleep(3000);
						String PageSource = driver.getPageSource();
						System.out.println("This is the Page Source" + PageSource);
						String RiderDocumentURL = driver.getCurrentUrl();
						if (PageSource.contains("pdf was not found on this server")) {
							softAssert.fail("Document Not Found for the " + i + " Subgroup " + ProductID
									+ " Product for url --> " + RiderDocumentURL);
							driver.close();
							driver.switchTo().window(newtabs.get(0));
						} else {

							String pdfContent = CommonMethods.returnPDFContent(RiderDocumentURL);
							System.out.println("This is the PDF Content of" + k + " Rider Document  for the product "
									+ ProductID + pdfContent);
							String getRiderDocNumberFromURL = RiderDocumentURL
									.substring(RiderDocumentURL.indexOf(".pdf") - 3);
							String riderDocNumber = getRiderDocNumberFromURL.substring(0, 3);
							softAssert.assertTrue(pdfContent.contains(riderDocNumber),
									"Validating Employer Rider Document for " + ProductID + "for url -->"
											+ RiderDocumentURL);
							driver.close();
							driver.switchTo().window(newtabs.get(0));
							driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut,
									TimeUnit.SECONDS);
							System.out.println("Verified Rider Documentsfor the " + i + " Subgroup " + x + " Product "
									+ (k - 1) + " RiderDocument ");
						}
					}
					break;
				}

				driver.findElement(BackButton).click();

				Thread.sleep(90000);

				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(subGroupIDLinks)));
			}

			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

		}

		softAssert.assertAll();

	}

	public void verifyDocumentsForSBCBenifitCodes(String GroupID, String countOfSubGroups, String medicalKey,
			String pharmacyKey, String memberType, String Dateval)
			throws IOException, InterruptedException, AWTException {
//Get the count of Employer subgroups
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		int countOfSubGroupsVal = (int) ScenarioContext.getContext(countOfSubGroups);
		System.out.println(countOfSubGroupsVal);
		for (int i = 1; i <= countOfSubGroupsVal; i++) {

			String GetSubgroupID = driver.findElement(By.xpath("(//div[2]/table/tbody/tr/td[1]/b)[" + i + "]")).getText();
			String SubgroupID = GetSubgroupID.substring(2, 6);
			System.out.println("This is the subgroupID-> " + SubgroupID + " for " + i);
// SetupSoapRequest
			String dateVal = (String) ScenarioContext.getContext(Dateval);
			System.out.println(dateVal);
			String Groupid = (String) ScenarioContext.getContext(GroupID);
			System.out.println(Groupid);
			String xMLPath = "XMLs/GetProductsbySubgroup.xml";
			APIMethods.setUpSOAPRequestForGetProductsBySubGroup(Groupid, dateVal, SubgroupID, xMLPath);
// SendSoapRequest
			String endPointURL = prop.getProperty("EndpointURLBenifitService");
			String PathtoXML = "//XMLs//GetProductsbySubgroup.xml";
			String xPathToMedical = "Envelope.Body.GetProductsbySubgroupResponse.GetProductsbySubgroupResult.ResponseData.Group[0].ProductID";
			String xPathToPharmacy = "Envelope.Body.GetProductsbySubgroupResponse.GetProductsbySubgroupResult.ResponseData.Group[1].ProductID";
			APIMethods.SendSOAPRequestforSBCGetProductsBySubgroup(endPointURL, PathtoXML, xPathToMedical, medicalKey,
					xPathToPharmacy, pharmacyKey);
			String MedicalKeyVal = (String) ScenarioContext.getContext(medicalKey);
			System.out.println("This is the Medical key for subgroupID- " + SubgroupID + " count- " + i
					+ " medicalkeyval- " + MedicalKeyVal);
			String PharmacyKeyVal = (String) ScenarioContext.getContext(pharmacyKey);
			System.out.println("This is the Pharmacy key for subgroupID- " + SubgroupID + " count- " + i
					+ " pharmacykeyval- " + PharmacyKeyVal);
			// Get the count of SBC Products within the Subgroup
			List<WebElement> SBCProductsCount = driver.findElements(SBCProductLinks);
			int SBCCountofProducts = SBCProductsCount.size();

			// Read the documents by Products
			for (int j = 1; j <= SBCCountofProducts; j++) {
				System.out.println(countOfSubGroupsVal);
				System.out.println(
						"For this subgroup->" + SubgroupID + " this is the count of products-" + SBCCountofProducts);
				String SBCCodeXPath1 = "(//div[@id='applRightPane']//table//tr//td[2]//a)["+j+"]";
				String SBCProductID = driver.findElement(By.xpath(SBCCodeXPath1)).getText();
				Thread.sleep(2000);
				System.out.println("This is the product id for the product id count-" + j + " " + SBCProductID
						+ " of the subgroup " + SubgroupID);
				WebElement SBCProductIDLink = driver.findElement(By.xpath(SBCCodeXPath1));
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("plugins.always_open_pdf_externally", true);
				ChromeOptions options = new ChromeOptions();
				String home = System.getProperty("user.home");
				String downloadFilepath = home + "/Downloads/"; 
				chromePrefs.put("download.default_directory", downloadFilepath);
				options.setExperimentalOption("prefs", chromePrefs);
										
				js.executeScript("arguments[0].click();", SBCProductIDLink);
				Thread.sleep(15000l);
				File sourceFileLocWhereTheDownloadWillHappen = new File(downloadFilepath + "HAP_SBC.pdf");
				// Load the file
				PDDocument document = PDDocument.load(sourceFileLocWhereTheDownloadWillHappen);
				// Instantiate PDFTextStripper class
				PDFTextStripper pdfStripper = new PDFTextStripper();
				// Retrieving text from PDF document
				String PDFContent = pdfStripper.getText(document);
				System.out.println("This is the SBC PDF Content -->" + PDFContent);
				// Validate Medical Product ID in the Document
				softAssert.assertTrue(PDFContent.contains(MedicalKeyVal));
				softAssert.assertTrue(PDFContent.contains(PharmacyKeyVal));
				// Closing the document
				document.close();
				Thread.sleep(4000l);
				if (sourceFileLocWhereTheDownloadWillHappen.delete()) {
					System.out.println("File deleted from HAPAutomation");
					//Thread.sleep(5000l);
				} else
					System.out.println("File doesn't deleted from HAPAutomation");
				//Thread.sleep(5000);

				//driver.close();

			}

		}
		softAssert.assertAll();
	}
	
	public void verifyDocumentsForSBCBenifitCodes1(String GroupID, String countOfSubGroups, String medicalKey,String pharmacyKey,String memberType, String Dateval) throws IOException, InterruptedException, AWTException
	{
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

		int countOfSubGroupsVal = (int) ScenarioContext.getContext(countOfSubGroups);
		System.out.println(countOfSubGroupsVal);
		for (int i = 1; i <= countOfSubGroupsVal; i++) {

			String GetSubgroupID = driver.findElement(By.xpath("(//div[2]/table/tbody/tr/td[1]/b)[" + i + "]"))
					.getText();
			String SubgroupID = GetSubgroupID.substring(2, 6);
			System.out.println("This is the subgroupID-> " + SubgroupID + " for " + i);
			// SetupSoapRequest
			String dateVal = (String) ScenarioContext.getContext(Dateval);
			System.out.println(dateVal);
			String Groupid = (String) ScenarioContext.getContext(GroupID);
			System.out.println(Groupid);
			String xMLPath = "XMLs/GetProductsbySubgroup.xml";
			APIMethods.setUpSOAPRequestForGetProductsBySubGroup(Groupid, dateVal, SubgroupID, xMLPath);
			// SendSoapRequest
			String endPointURL = prop.getProperty("EndpointURLBenifitService");
			String PathtoXML = "//XMLs//GetProductsbySubgroup.xml";
			String xPathToMedical = "Envelope.Body.GetProductsbySubgroupResponse.GetProductsbySubgroupResult.ResponseData.Group[0].ProductID";
			String xPathToPharmacy = "Envelope.Body.GetProductsbySubgroupResponse.GetProductsbySubgroupResult.ResponseData.Group[1].ProductID";
			APIMethods.SendSOAPRequestforSBCGetProductsBySubgroup(endPointURL,PathtoXML,xPathToMedical, medicalKey,xPathToPharmacy, pharmacyKey);
			String MedicalKeyVal = (String) ScenarioContext.getContext(medicalKey);
			System.out.println("This is the Medical key for subgroupID- " + SubgroupID + " count- " + i
					+ " medicalkeyval- " + MedicalKeyVal);
			String PharmacyKeyVal = (String) ScenarioContext.getContext(pharmacyKey);
			System.out.println("This is the Pharmacy key for subgroupID- " + SubgroupID + " count- " + i
					+ " pharmacykeyval- " + PharmacyKeyVal);
			// Get the count of SBC Products within the Subgroup
			List<WebElement> SBCProductsCount = driver.findElements(SBCProductLinks);
			int SBCCountofProducts = SBCProductsCount.size();
			
			// Read the documents by Products
			for(int j=1;j<=SBCCountofProducts;j++)
			{
			System.out.println(countOfSubGroupsVal);
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			System.out.println("For this subgroup->" + SubgroupID + " this is the count of products-" + SBCCountofProducts);
			String SBCCodeXPath1 ="(//div[@id='applRightPane']//table//tr//td[2]//a)["+j+"]";
			//WebElement Produc=(WebElement) driver.findElements(SBCProductLinks);
			String SBCProductID = driver.findElement(By.xpath(SBCCodeXPath1)).getText();
			Thread.sleep(2000);
			System.out.println("This is the product id for the product id count-" + j + " " + SBCProductID
					+ " of the subgroup " + SubgroupID);
			WebElement SBCProductIDLink=driver.findElement(By.xpath(SBCCodeXPath1));
			js.executeScript("arguments[0].click();", SBCProductIDLink);
			ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newtabs.get(1));
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			System.out.println("newtab"+newtabs);
			
			
			Thread.sleep(2000);
			System.out.println("switched");
			
			String Pagesource=driver.getTitle();
		    System.out.println("This is Pagesource"+Pagesource);
			Thread.sleep(3000);
			Actions action = new Actions(driver);
			action.contextClick().build().perform();
			Thread.sleep(5000);
			Robot robot = new Robot();
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(6000);
			Robot robot1 = new Robot();
			robot1.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			robot1.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot1.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot1.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			String home = System.getProperty("user.home");
		//	String SBCDownloadLocation = System.getProperty("user.dir") + File.separator + "pdfs/PDFTODownload/HAP_SBC.pdf";
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
			softAssert.assertTrue(PDFContent.contains(MedicalKeyVal),"Validating SBC document for Medical ProductID");
			softAssert.assertTrue(PDFContent.contains(PharmacyKeyVal),"Validating SBC document for Pharmacy ProductID");
			
			// Closing the document
			document.close();
			Thread.sleep(5000);
			if (sourceFileLocWhereTheDownloadWillHappen.delete()) {
				System.out.println("File deleted from HAPAutomation");
			} else
				System.out.println("File doesn't deleted from HAPAutomation");
			Thread.sleep(5000);

			driver.close();
			driver.switchTo().window(newtabs.get(0));
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		
			}
			else
			{
				System.out.println("Document Not Found in the server");
			}
		
			
			
		}
		}
		softAssert.assertAll();
}
public  void ValidateDocumentsfromPEGARESPONSE(String Document, int i, String MedicalProduct, String PharmacyProduct) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(BackButton));
		//(By.xpath("//a"))
		if(Document.isEmpty()){
			
			System.out.println("No result for Document in Response. Hence Validation not Required");
		}
			else {
			
				System.out.println("This is the Number " + (i+1) + " Document " + Document);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
				Thread.sleep(3000);
				boolean linkispresent = driver.findElements(By.xpath("//b[contains(text(),'" + Document + "')]")).size() >0;
				//String pagesource = driver.getPageSource();
				//Assert.assertTrue(pagesource.contains("<b>"+ Document +"</b>"), "The Document Link for " + Document + " is not Displayed in the GUI for Product " + MedicalProduct + "-" +PharmacyProduct);
				Assert.assertTrue(linkispresent = true, "The Document Link for " + Document + " is not Displayed in the GUI for Product " + MedicalProduct + "-" +PharmacyProduct);
				/*WebElement ContractDocumentLink = driver.findElement(By.xpath("//b[contains(text(),'" + Document + "')]"));
				js.executeScript("arguments[0].click();", ContractDocumentLink);
				Thread.sleep(4000);
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
				ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(newtabs.get(1));
				System.out.println("Switched to Contract documents Tab");
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
				//Thread.sleep(3000l);
				String PageSource = driver.getPageSource();
				
				
				if(PageSource.contains("application/pdf")) {
					
					System.out.println("This is the Page Source" + PageSource);
					softAssert.assertTrue(PageSource.contains("application/pdf"), "validating Contract Document is displayed Properly");
					driver.close();
					driver.switchTo().window(newtabs.get(0));
					System.out.println("Switched Back to Main window");
				}
				else {
					//WebElement DocumentsCouldnotBeLocatedError = driver.findElement(DocumentCouldNotBeLocated);
					softAssert.fail( "This Plan Document Could not be located at this time -->" + " " + Document );
					driver.findElement(CloseButton).click();
					//driver.close();
					driver.switchTo().window(newtabs.get(0));
					System.out.println("Switched Back to Main window");
					}*/
				
				}
				
			}
// Method for New Arch Employer
	public void verifyGroupContractDocumentsQHPEmployerPegaFlow(String GroupID, String countOfSubGroups, String medicalKey,String pharmacyKey,String memberType, String Dateval) throws IOException, InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		
		int countOfSubGroupsVal = (int) ScenarioContext.getContext(countOfSubGroups);
		System.out.println("Count OF sUBGROUP"+countOfSubGroupsVal);
		for (int i = 1; i <= countOfSubGroupsVal; i++) {
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			String GetSubgroupID = driver.findElement(By.xpath("(//div[2]/table/tbody/tr[" + i + "]/td[1]/b)"))
					.getText();
			System.out.println("cHECK --> " +GetSubgroupID);
			String SubgroupID = GetSubgroupID.substring(2, 6);
			System.out.println("This is the subgroupID-> " + SubgroupID + " for " + i);
			// SetupSoapRequest
			String dateVal = (String) ScenarioContext.getContext(Dateval);
			System.out.println(dateVal);
			String Groupid = (String) ScenarioContext.getContext(GroupID);
			System.out.println(Groupid);
			String xMLPath = "XMLs/GetProductsbySubgroup.xml";
			APIMethods.setUpSOAPRequestForGetProductsBySubGroup(Groupid, dateVal, SubgroupID, xMLPath);
			// SendSoapRequest
			String endPointURL = prop.getProperty("EndpointURLBenifitService");
			String PathtoXML = "//XMLs//GetProductsbySubgroup.xml";
			String xPathToMedical = "Envelope.Body.GetProductsbySubgroupResponse.GetProductsbySubgroupResult.ResponseData.Group[0].ProductID";
			String xPathToPharmacy = "Envelope.Body.GetProductsbySubgroupResponse.GetProductsbySubgroupResult.ResponseData.Group[1].ProductID";
			APIMethods.SendSOAPRequestforSBCGetProductsBySubgroup(endPointURL,PathtoXML,xPathToMedical, medicalKey,xPathToPharmacy, pharmacyKey);
			String xMLPath1="XMLs/GetBenefitDocumentsMetaDataPAQHPMember.xml";
			
			List<WebElement> ProductsCount = driver.findElements(By.xpath("(//div[2]/table/tbody/tr[" + i + "]/td[2]/b)"));
			int CountofProducts = ProductsCount.size();

			// Read the documents by Products
			
				for (int x = 1; x <= CountofProducts; x++) {
					driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);	
					Thread.sleep(3000);
					String BenifitCodeXPath1 ="(//div[2]/table/tbody/tr[" + i + "]/td[2]/b[" + x + "]/a)";
					WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
					wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ProductLinks)));
					String ProductID = driver.findElement(By.xpath(BenifitCodeXPath1)).getText();
					
					System.out.println("This is the product id for the product id count-" + x + " " + ProductID
							+ " of the subgroup " + SubgroupID);
						
				String MedicalProductID =	ProductID.substring(0, 8);
				
				System.out.println("This is the Medical key for subgroupID- " + SubgroupID + " count- " + i
						+ " medicalkeyval- " + MedicalProductID);
				
				String PharmacyProductID= ProductID.substring(11, 19);
				System.out.println("This is the Pharmacy key for subgroupID- " + SubgroupID + " count- " + i
						+ " pharmacykeyval- " + PharmacyProductID);
				
				//sets the SOAP Request xml for Pega Service for PA QHP 
				APIMethods.setUpSOAPRequestForPegaServiceforPAQHPEmployer(dateVal,xMLPath1,Groupid,SubgroupID,MedicalProductID,PharmacyProductID);
				//sends the REST POST request
				String baseUrl=prop.getProperty("BaseURLPEGAREST");
				String basePath="/member/benefitDoc/getProductBenefitDocuments";
				String requestPath="//XMLs//GetBenefitDocumentsMetaDataPAQHPMember.xml";
				String filepath="/XMLs/ResponsePEGAGetBenifit.xml";
				RestClient.sendRESTPOSTRequest(baseUrl,basePath,requestPath);
				
				RestClient.parseTheRESTPOSTResponse(baseUrl,basePath,requestPath,filepath);
				js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(BenifitCodeXPath1)));
				driver.findElement(By.xpath(BenifitCodeXPath1)).click();
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
				String contents = new String(Files.readAllBytes(
						Paths.get(System.getProperty("user.dir") + File.separator + filepath)));

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
				
				for(int a = 0; a<listOfContractsDoc.size(); a++) {
					System.out.println("Validating Contracts");
					String Document = listOfContractsDoc.get(a).getTextContent();
				ValidateDocumentsfromPEGARESPONSE(Document, a, MedicalProductID, PharmacyProductID);	
				
				}
				for(int a = 0; a<listOfBenefitRiders.size(); a++) {
					System.out.println("Validating Benefit Riders");
					String Document = listOfBenefitRiders.get(a).getTextContent();
					
				ValidateDocumentsfromPEGARESPONSE(Document, a, MedicalProductID, PharmacyProductID);	
					
				}
//				for(int a = 0; a<listOfbenefitsummary.size(); a++) {
//					System.out.println("Validating Benefit Summary");
//					String Document = listOfbenefitsummary.get(a).getTextContent();
//					ValidateDocumentsfromPEGARESPONSE(Document, a);	
//				}
				for(int a = 0; a<listOfSBC.size(); a++) {
					System.out.println("Validating SBC");
					String Document = listOfSBC.get(a).getTextContent();
					ValidateDocumentsfromPEGARESPONSE(Document, a, MedicalProductID, PharmacyProductID);	
					
				}
				for(int a = 0; a<listOfMemberRiders.size(); a++) {
					System.out.println("Validating Member Riders");
					String Document = listOfMemberRiders.get(a).getTextContent();
					ValidateDocumentsfromPEGARESPONSE(Document, a, MedicalProductID, PharmacyProductID);	
					
				}
				for(int a = 0; a<listOfSchedulePages.size(); a++) {
					System.out.println("Validating Schedule Pages");
					String Document = listOfSchedulePages.get(a).getTextContent();
					ValidateDocumentsfromPEGARESPONSE(Document, a, MedicalProductID, PharmacyProductID);	
					
				}
				for(int a = 0; a<listOfNoticetoChange.size(); a++) {
					System.out.println("Validating List of Notice Change");
					String Document = listOfNoticetoChange.get(a).getTextContent();
					ValidateDocumentsfromPEGARESPONSE(Document, a, MedicalProductID, PharmacyProductID);	
					
				}
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", driver.findElement(BackButton));
				
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(subGroupIDLinks)));
				}
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		}
		softAssert.assertAll();
		}
		
	
	// Method for Employer Links Old Arch
	public void verifylinksForBenifitCodes(String GroupID, String countOfSubGroups, String renewalKey,
			String memberType, String Dateval) throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

		int countOfSubGroupsVal = (int) ScenarioContext.getContext(countOfSubGroups);
		System.out.println(countOfSubGroupsVal);
		for (int i = 1; i <= countOfSubGroupsVal; i++) {
			
			String GetSubgroupID = driver.findElement(By.xpath("(//div[2]/table/tbody/tr[" + i + "]/td[1]/b)"))
					.getText();
			String SubgroupID = GetSubgroupID.substring(2, 6);
			System.out.println("This is the subgroupID-> " + SubgroupID + " for " + i);
			// SetupSoapRequest
			String dateVal = (String) ScenarioContext.getContext(Dateval);
			System.out.println(dateVal);
			String Groupid = (String) ScenarioContext.getContext(GroupID);
			System.out.println(Groupid);
			String xMLPath = "XMLs/GetProductsbySubgroup.xml";
			APIMethods.setUpSOAPRequestForGetProductsBySubGroup(Groupid, dateVal, SubgroupID, xMLPath);
			// SendSoapRequest
			String endPointURL = prop.getProperty("EndpointURLBenifitService");
			String PathtoXML = "//XMLs//GetProductsbySubgroup.xml";
			String xPathToRenewalMonth = "Envelope.Body.GetProductsbySubgroupResponse.GetProductsbySubgroupResult.ResponseData.Group[1].SubgroupRenewalMonth";
			APIMethods.SendSOAPRequestforGetProductsBySubgroup(endPointURL, PathtoXML, xPathToRenewalMonth, renewalKey);
			String RenewalKeyVal = (String) ScenarioContext.getContext(renewalKey);
			System.out.println("This is the renewal key for subgroupID- " + SubgroupID + " count- " + i
					+ " renewalkeyval- " + RenewalKeyVal);
			// Get the count of Products within the Subgroup
			List<WebElement> ProductsCount = driver.findElements(By.xpath("(//div[2]/table/tbody/tr[" + i + "]/td[2]/b)"));
			int CountofProducts = ProductsCount.size();
			System.out
					.println("For this subgroup->" + SubgroupID + " this is the count of products-" + CountofProducts);
			// Read the documents by Products
			for (int x = 1; x <= CountofProducts; x++) {

				String BenifitCodeXPath1 = "(//div[2]/table/tbody/tr[" + i + "]/td[2]/b[" + x + "]/a)";

				String ProductID = driver.findElement(By.xpath(BenifitCodeXPath1)).getText();
			
			
				String GetRidersXMLpath = "XMLs/GetRidersByProductID.xml";
			// Set up SOAP Request for Get Riders
				APIMethods.SetupSOAPRequestForGetRidersByProductID(ProductID,dateVal,GetRidersXMLpath);
				// Send SOAP Request for Get Riders and write Response to XML
				String endPointURLforGetRiders = prop.getProperty("EndpointURLBenifitService");
				String PathtoXMLForGetRiders = "//XMLs//GetRidersByProductID.xml";
				
				
				
				APIMethods.SendSOAPRequestforGetRidersByProductID(endPointURLforGetRiders, PathtoXMLForGetRiders );
				//Read from response xml and validate
				String PathtoResponseXML = "/XMLs/GetRidersByProductIDResponse.xml";
				String contents = new String(Files.readAllBytes(
						Paths.get(System.getProperty("user.dir") + File.separator + PathtoResponseXML)));
				System.out.println("Contents-->" +contents);
				
				XmlPath xt = new XmlPath(contents);
				

				String xpathtoRiders = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider";
				
				List<org.w3c.dom.Element> listOfRiders = xt.getList(xpathtoRiders);
				
				
				int CountofRiders = listOfRiders.size();
				System.out.println("Count of Riders" + CountofRiders);
					
				
				System.out.println("This is the product id for the product id count-" + x + " " + ProductID
						+ " of the subgroup " + SubgroupID);
				// Get the first letter of the product id
				String ProductIDFirstLetter = ProductID.substring(0, 1);
				System.out.println("This is the first letter of the product id-> " + ProductIDFirstLetter);
				//Thread.sleep(1000);
				// Click on the Product Code
				driver.findElement(By.xpath(BenifitCodeXPath1)).click();
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
				Thread.sleep(8000);
				String PageSourceM = driver.getPageSource();
				int currentMonthVal = CommonMethods.returnCurrentMonth();
				int result = Integer.parseInt(RenewalKeyVal);
				switch (ProductIDFirstLetter) {
				
				case "X":
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
//							List<WebElement> RiderDocumentsLinksinGUI = driver.findElements(RiderDocumentLinks);
//							String LinkText = RiderDocumentsLinksinGUI.get(b).getText();
//							System.out.println("Link Text -->" +LinkText);
//							softAssert.assertTrue(LinkText.contains(RiderDocName), "Validating Rider Link for " + ProductID + " -->"+RiderDocName);		
							softAssert.assertTrue(PageSourceM.contains(RiderDocNew), "Validating Rider Link for " + ProductID + " -->"+RiderDocNew);
					}
					}
					break;

				case "P":
					softAssert.assertTrue(PageSourceM.contains("<b>Health Insurance Policy</b>"), "Validating Contract Document");
					
						if (result <= currentMonthVal) {
						softAssert.assertTrue(PageSourceM.contains("<b>Summary of policy changes for " + String.valueOf(currentYear) +"</b>"),"Validating Sumamry of Policy change Link for Year");
						}
						else {
						softAssert.assertTrue(PageSourceM.contains("<b>Summary of policy changes for " + String.valueOf(currentYear -1) +"</b>"),"Validating Sumamry of Policy change Link for Year");
						}
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
//								List<WebElement> RiderDocumentsLinksinGUI = driver.findElements(RiderDocumentLinks);
//								String LinkText = RiderDocumentsLinksinGUI.get(b).getText();
//								System.out.println("Link Text -->" +LinkText);
//								softAssert.assertTrue(LinkText.contains(RiderDocName), "Validating Rider Link for " + ProductID + " -->"+RiderDocName);		

						softAssert.assertTrue(PageSourceM.contains(RiderDocNew), "Validating Rider Link for " + ProductID + " -->"+RiderDocNew);
						}
						}
					break;
				default:
						softAssert.assertTrue(PageSourceM.contains("<b>HMO Subscriber Contract</b>"), "Validating Contract Document");
						
						if (result <= currentMonthVal) {
							softAssert.assertTrue(PageSourceM.contains("<b>Summary of contract changes for " + String.valueOf(currentYear) +"</b>"),"Validating Sumamry of Policy change Link for Year");
						}
						else {
							softAssert.assertTrue(PageSourceM.contains("<b>Summary of contract changes for " + String.valueOf(currentYear -1) +"</b>"),"Validating Sumamry of Policy change Link for Year");
						}
						for(int b =0;b<CountofRiders;b++) {
							String XpathtoRiderDocName = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider["+b+"].RiderDescription";
							String RiderDocName = xt.getString(XpathtoRiderDocName);
							System.out.println("Rider Doc Name-->" +RiderDocName);
							String RiderDocNew =  CommonMethods.ConvertRiderDocName(RiderDocName);
							String xpathtoRiderCode = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider["+b+"].RiderCode";
							String RiderCode = xt.getString(xpathtoRiderCode);
							if(RiderCode.startsWith("X") || RiderCode.startsWith("AST")) {
								System.out.println("Ignoring Rider Document --> " + RiderDocNew + " as the Rider Code Starts with 'X' or 'AST'");
							}
							else {
//								List<WebElement> RiderDocumentsLinksinGUI = driver.findElements(RiderDocumentLinks);
//								String LinkText = RiderDocumentsLinksinGUI.get(b).getText();
//								System.out.println("Link Text -->" +LinkText);
//								softAssert.assertTrue(LinkText.contains(RiderDocName), "Validating Rider Link for " + ProductID + " -->"+RiderDocName);		

						softAssert.assertTrue(PageSourceM.contains(RiderDocNew), "Validating Rider Link for " + ProductID + " -->"+RiderDocNew);
						}
						}
					break;
				}
				Thread.sleep(2000);
				js.executeScript("arguments[0].click();", driver.findElement(BackButton));

				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(subGroupIDLinks)));
			}

			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

		}

		softAssert.assertAll();

	}

	public void verifylinksForBenifitCodesPA(String GroupID, String countOfSubGroups, String renewalKey,
			String memberType, String Dateval) throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

		int countOfSubGroupsVal = (int) ScenarioContext.getContext(countOfSubGroups);
		System.out.println(countOfSubGroupsVal);
		for (int i = 1; i <= countOfSubGroupsVal; i++) {
			Thread.sleep(3000);
			String GetSubgroupID = driver.findElement(By.xpath("(//div[2]/table/tbody/tr[" + i + "]/td[1]/b)"))
					.getText();
			String SubgroupID = GetSubgroupID.substring(2, 6);
			System.out.println("This is the subgroupID-> " + SubgroupID + " for " + i);
			// SetupSoapRequest
			String dateVal = (String) ScenarioContext.getContext(Dateval);
			System.out.println(dateVal);
			String Groupid = (String) ScenarioContext.getContext(GroupID);
			System.out.println(Groupid);
			String xMLPath = "XMLs/GetProductsbySubgroup.xml";
			APIMethods.setUpSOAPRequestForGetProductsBySubGroup(Groupid, dateVal, SubgroupID, xMLPath);
			// SendSoapRequest
			String endPointURL = prop.getProperty("EndpointURLBenifitService");
			String PathtoXML = "//XMLs//GetProductsbySubgroup.xml";
			String xPathToRenewalMonth = "Envelope.Body.GetProductsbySubgroupResponse.GetProductsbySubgroupResult.ResponseData.Group[1].SubgroupRenewalMonth";
			APIMethods.SendSOAPRequestforGetProductsBySubgroup(endPointURL, PathtoXML, xPathToRenewalMonth, renewalKey);
			String RenewalKeyVal = (String) ScenarioContext.getContext(renewalKey);
			System.out.println("This is the renewal key for subgroupID- " + SubgroupID + " count- " + i
					+ " renewalkeyval- " + RenewalKeyVal);
			// Get the count of Products within the Subgroup
			List<WebElement> ProductsCount = driver.findElements(By.xpath("(//div[2]/table/tbody/tr[" + i + "]/td[2]/b)"));
			int CountofProducts = ProductsCount.size();
			System.out
					.println("For this subgroup->" + SubgroupID + " this is the count of products-" + CountofProducts);
			// Read the documents link by Products
			for (int x = 1; x <= CountofProducts; x++) {

				String BenifitCodeXPath1 = "(//div[2]/table/tbody/tr[" + i + "]/td[2]/b[" + x + "]/a)";

				String ProductID = driver.findElement(By.xpath(BenifitCodeXPath1)).getText();
			
			
				String GetRidersXMLpath = "XMLs/GetRidersByProductID.xml";
			// Set up SOAP Request for Get Riders
				APIMethods.SetupSOAPRequestForGetRidersByProductID(ProductID,dateVal,GetRidersXMLpath);
				// Send SOAP Request for Get Riders and write Response to XML
				String endPointURLforGetRiders = prop.getProperty("EndpointURLBenifitService");
				String PathtoXMLForGetRiders = "//XMLs//GetRidersByProductID.xml";
				
				
				
				APIMethods.SendSOAPRequestforGetRidersByProductID(endPointURLforGetRiders, PathtoXMLForGetRiders );
				//Read from response xml and validate
				String PathtoResponseXML = "/XMLs/GetRidersByProductIDResponse.xml";
				String contents = new String(Files.readAllBytes(
						Paths.get(System.getProperty("user.dir") + File.separator + PathtoResponseXML)));
				System.out.println("Contents-->" +contents);
				
				XmlPath xt = new XmlPath(contents);
				

				String xpathtoRiders = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider";
				
				List<org.w3c.dom.Element> listOfRiders = xt.getList(xpathtoRiders);
				
				
				int CountofRiders = listOfRiders.size();
				System.out.println("Count of Riders" + CountofRiders);
					
				
				System.out.println("This is the product id for the product id count-" + x + " " + ProductID
						+ " of the subgroup " + SubgroupID);
				// Get the first letter of the product id
				String ProductIDFirstLetter = ProductID.substring(0, 1);
				System.out.println("This is the first letter of the product id-> " + ProductIDFirstLetter);
				//Thread.sleep(1000);
				// Click on the Product Code
				driver.findElement(By.xpath(BenifitCodeXPath1)).click();
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ProductCode)));
				driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
				driver.findElement(ProductCode).click();
				Thread.sleep(8000);
				String PageSourceM = driver.getPageSource();
				int currentMonthVal = CommonMethods.returnCurrentMonth();
				int result = Integer.parseInt(RenewalKeyVal);
				switch (ProductIDFirstLetter) {
				
				case "X":
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
//							List<WebElement> RiderDocumentsLinksinGUI = driver.findElements(RiderDocumentLinks);
//							String LinkText = RiderDocumentsLinksinGUI.get(b).getText();
//							System.out.println("Link Text -->" +LinkText);
//							softAssert.assertTrue(LinkText.contains(RiderDocName), "Validating Rider Link for " + ProductID + " -->"+RiderDocName);		
							softAssert.assertTrue(PageSourceM.contains(RiderDocNew), "Validating Rider Link for " + ProductID + " -->"+RiderDocNew);
					}
					}
					break;

				case "P":
					softAssert.assertTrue(PageSourceM.contains("<b>Health Insurance Policy</b>"), "Validating Contract Document");
					
						
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
//								List<WebElement> RiderDocumentsLinksinGUI = driver.findElements(RiderDocumentLinks);
//								String LinkText = RiderDocumentsLinksinGUI.get(b).getText();
//								System.out.println("Link Text -->" +LinkText);
//								softAssert.assertTrue(LinkText.contains(RiderDocName), "Validating Rider Link for " + ProductID + " -->"+RiderDocName);		

						softAssert.assertTrue(PageSourceM.contains(RiderDocNew), "Validating Rider Link for " + ProductID + " -->"+RiderDocNew);
						}
						}
					break;
				default:
						softAssert.assertTrue(PageSourceM.contains("<b>HMO Subscriber Contract</b>"), "Validating Contract Document");
						
						if (result <= currentMonthVal) {
							softAssert.assertTrue(PageSourceM.contains("<b>Summary of contract changes for " + String.valueOf(currentYear) +"</b>"),"Validating Sumamry of Policy change Link for Year");
						}
						else {
							softAssert.assertTrue(PageSourceM.contains("<b>Summary of contract changes for " + String.valueOf(currentYear -1) +"</b>"),"Validating Sumamry of Policy change Link for Year");
						}
						for(int b =0;b<CountofRiders;b++) {
							String XpathtoRiderDocName = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider["+b+"].RiderDescription";
							String RiderDocName = xt.getString(XpathtoRiderDocName);
							System.out.println("Rider Doc Name-->" +RiderDocName);
							String RiderDocNew =  CommonMethods.ConvertRiderDocName(RiderDocName);
							String xpathtoRiderCode = "Envelope.Body.GetRidersbyProductIdResponse.GetRidersbyProductIdResult.ResponseData.Product.riders.Rider["+b+"].RiderCode";
							String RiderCode = xt.getString(xpathtoRiderCode);
							if(RiderCode.startsWith("X") || RiderCode.startsWith("AST")) {
								System.out.println("Ignoring Rider Document --> " + RiderDocNew + " as the Rider Code Starts with 'X' or 'AST'");
							}
							else {
//								List<WebElement> RiderDocumentsLinksinGUI = driver.findElements(RiderDocumentLinks);
//								String LinkText = RiderDocumentsLinksinGUI.get(b).getText();
//								System.out.println("Link Text -->" +LinkText);
//								softAssert.assertTrue(LinkText.contains(RiderDocName), "Validating Rider Link for " + ProductID + " -->"+RiderDocName);		

						softAssert.assertTrue(PageSourceM.contains(RiderDocNew), "Validating Rider Link for " + ProductID + " -->"+RiderDocNew);
						}
						}
					break;
				}

				driver.findElement(BackButton).click();

				Thread.sleep(90000);
				}

			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);

		}

		softAssert.assertAll();

	}

	
	}


