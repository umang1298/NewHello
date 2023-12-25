package com.qa.pages;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;

public class HapConnectivityPage extends TestBase {

	public HapConnectivityPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	JavascriptExecutor js = (JavascriptExecutor) driver;
	By QuoteLink = By.xpath("//a[@id='uxLinkGetIndividualQuote']");
	By HapConnectivity = By.xpath("//div[@id='mainPageHeader']/div[1]/img");
	By ZipCode = By.xpath("//input[@id='ZipCode']");
	By viewMyCoverage = By.xpath("//button[@id='uxButtonViewCoverageOptions']");
	By shopforCoverage = By.xpath("//input[@id='uxButtonShopForCoverage']");
	By SelectCoverage = By.xpath("//input[@id='uxSelectProducts']");
	By AddIndividuals = By.xpath("//input[@id='add_individuals_btn']");
	// form/div/div/input[@id='add_individuals_btn']");

	By EnrollmentRadio = By.xpath("//input[@id='MedicalEnrollment_MedicalEnrollmentQESelection'][1]");
	By SelectedQualifyingRadioYes = By.xpath("//input[@id=\"MedicalEnrollment_MedicalEnrollmentQESelection\"]");
	By QualifyingEvent = By.xpath("//input[@id='SelectedQualifyingEvents1073741842']");
	By DateofEvent = By.xpath("//input[@id='MedicalEnrollment_DateOfTheEvent']");
	By Firstname = By.xpath("//input[@id='CoverageForms_0__ApplicantsFirstName']");
	By Lastname = By.xpath("//input[@id='CoverageForms_0__ApplicantsLastName']");
	By DOB = By.xpath("//input[@id='PrimaryDateOfBirth_0']");
	By Gender = By.xpath("//input[@id='uxRadioCoverageFormFemale'][1]");
	By Tobaccouse = By.xpath("//input[@id='CoverageForms_0__IsTobaccoUser'][@value='False']");
	By StartShopping = By.xpath("//input[@id='uxButtonReviewPlans']");
	By AddCart = By.xpath("//input[@id='uxButtonAddToCart_12704']");
	By GotoCart = By.xpath("//a[@id='uxButtonGoToMyCart']");
	By BeginAppliction = By.xpath("//input[@id='MyBenefitsPackageButton']");
	By DetalCoverageRadio = By.xpath("//input[@id='CoverageOption'][@value='2']");
	By Continue = By.xpath("//input[@id='btnConfirm']");
	By NewAccountLink = By.xpath("//span[@id='uxSpanCreateNewAccountLinkText']");
	By FnameNewAccount = By.xpath("//input[@id='FirstName']");
	By LnameNewAccount = By.xpath("//input[@id='LastName']");
	By EmailNewAccount = By.xpath("//input[@id='uxTextBoxEmailAddress']");
	By ConfirmEmailNewAccount = By.xpath("//input[@id='uxTextBoxConfirmEmailAddress']");
	By CreateAccount = By.xpath("//input[@id='uxButtonCreateAccount']");
	By EnrollmentManager = By.xpath("//span[@id='uxApplicationManagerHeader']");
	By StartFormButton = By.xpath("//a[@id='uxSpanStartAppActionLinkText']");
	// WelcomePage
	By WelcomePageLabel = By.xpath("//div[contains(text(),'How did you hear about HAP?')]");
	By DentalAttestation = By.xpath("//select[@id='ctl00_cph_uxCustomContentControl_s8_c176_ddlMCQ_Response']");

	By WelcomePageContinue = By.xpath("//input[@id='uxButtonContinueBottom']");
	By ApplicationName = By.xpath("//span[@id='uxLabelApplicationName']");
	// ApplicationInfo
	By SSNnumber = By.xpath("//input[@id='ctl00_cph_uxCustomContentControl_s10_c219_txtNQ_Response']");

	By MartailStatus = By.xpath("//select[@id='ctl00_cph_uxCustomContentControl_s10_c220_ddlMCQ_Response']");

	By EligibleMedicare = By.xpath("//select[@id='ctl00_cph_uxCustomContentControl_s10_c223_ddlMCQ_Response']");
	By MedicalCoverage = By.xpath("//input[@id='ctl00_cph_uxCustomContentControl_s11_c227_rblMCQ_Response_1']");
	By AddressLine1 = By.xpath("//input[@id='ctl00_cph_uxCustomContentControl_s12_c229_txtSLTQ_Response']");
	By City = By.xpath("//input[@id='ctl00_cph_uxCustomContentControl_s12_c231_txtSLTQ_Response']");
	By phonenumber = By.xpath("//input[@id='ctl00_cph_uxCustomContentControl_s12_c235_txtNQ_Response']");
	By MailID = By.xpath("//input[@id='ctl00_cph_uxCustomContentControl_s12_c237_txtSLTQ_Response']");
	By ContinueApp = By.xpath("//input[@id='uxButtonContinueBottom']");

	By ReviewLabel = By.xpath("//span[@id='uxLabelApplicationName']");
	By continueAppli = By.xpath("//input[@id='uxButtonContinue_top']");
	By continueAppliTop = By.xpath("//input[@id='uxButtonContinueTop']");
	By EnrollmentContinue = By.xpath("//input[@id='uxButtonSubmitEnrollment_top']");
	// Attestation
	By Attestationcheckbox = By.xpath("//input[@id='ctl00_cph_uxCustomContentControl_s17_c363_chkSCQ_Response']");
	By Agentname = By.xpath("//input[@id='ctl00_cph_uxCustomContentControl_s16_c134_txtSLTQ_Response']");
	By SubmitOnline = By.xpath("//input[@id='uxButtonSubmitOnline']");
	// Summary
	By FirstnameSummary = By.xpath("//input[@id='ctl00_cph_electronicSignatureUserControl0_txtSignFirstName']");
	By LastnameSummary = By.xpath("//input[@id='ctl00_cph_electronicSignatureUserControl0_txtSignLastName']");
	By FirstnameConfirm = By.xpath("//input[@id='ctl00_cph_electronicSignatureUserControl0_txtConfirmFirstName']");
	By LastnameConfirm = By.xpath("//input[@id='ctl00_cph_electronicSignatureUserControl0_txtConfirmLastName']");
	// Add documents
	By AdddocumentsLink = By.xpath("//h4[@id='uxSpanAddDocument']");
	By BrowseButton = By.xpath("//input[@id='fileAttachment']");
	By BrowseText = By.xpath("//input[@id='fileAttachment']");
	By uploadButton = By.xpath("//div[@id=\"_GlobalMainLayoutDialog\"]/div/form/div[8]/div[5]/input");
	By MakePaymentlink = By.xpath("//input[@id='uxButtonMakePayment_top']");
	By SelectPaymentOption = By.xpath("//input[@id='PaymentOption1']");
	By MakePayment = By.xpath("//input[@type='submit']");
	By PayButton = By.xpath("//input[@id='checkout-pay']");
	By SelectFundingSource = By.xpath("//input[@id='select_fundingsource']");
	By TermsandConditions = By.xpath("//label/input[@id='checkout-agree-terms-checkbox']");
	By selectdropdown = By.xpath("//input[@id='select_fundingsource']");
	By newcard = By.xpath("//ul[@id=\"select_fundingsource_list\"]/li[2]/a/span");
	By CardNumber = By.xpath("//input[@id=\"checkout-cardNumber\"]");
	By CVV = By.xpath("//input[@id=\"checkout-cvv\"]");
	By ExpDate = By.xpath("//input[@id=\"checkout-exp-card\"]");
	By PaymentName = By.xpath("//input[@id=\"checkout-personalaccountnameoncard\"]");
	By PaymentAddress = By.xpath("//input[@id=\"checkout-address1\"]");
	By PaymentCity = By.xpath("//input[@id=\"checkout-city\"]");
	By PaymentState = By.xpath("//select[@id=\"checkout-state\"]");
	By PaymentZip = By.xpath("//input[@id=\"checkout-zip1\"]");
	By Planlist = By.xpath("//table[@id='tblQuote']/tbody[1]/tr/td[1]/div[1]");
	By PaymentConfirmation = By
			.xpath("//div[@id=\"page-content-wrapper\"]/div/div/div/div/div/div[1]/div/div[1]/div[1]/div/div/div[2]");

	public void validateShopforCoverage(String type) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub

		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(1));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(HapConnectivity));
		Thread.sleep(3000);
		Assert.assertTrue("Agent Portal menu page is not displayed", driver.findElement(HapConnectivity).isDisplayed());
		driver.findElement(QuoteLink).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ZipCode));
		driver.findElement(ZipCode).sendKeys("48125");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(viewMyCoverage).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(shopforCoverage));
		driver.findElement(shopforCoverage).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(SelectCoverage));
		driver.findElement(SelectCoverage).click();

		Thread.sleep(4000);
		validateSelectCoverage();
		validateAddIndividuals();
		validateGotoCart(type);
		validateBeginApplication();
		validateCreatAccount();
		validateWelcomePage();
		validateApplicationInfo();
		validateReview();
		validateAuthorization();
		validateAttestations();
		validateSummary();
		validateAddDocuments();
		validateMakepaymentwindow();
		validatehappayment();
	}

	private void validatehappayment() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		Thread.sleep(3000);
		driver.switchTo().frame("orbipay-checkout-iframe");
		Thread.sleep(5000);
		Assert.assertTrue("Hap payment page is not loaded", driver.findElement(PayButton).isDisplayed());
		driver.findElement(selectdropdown).click();
		Thread.sleep(2000);
		driver.findElement(newcard).click();
		Thread.sleep(3000);
		driver.findElement(CardNumber).sendKeys("4159282222222221");
		Thread.sleep(3000);
		driver.findElement(CVV).sendKeys("123");
		Thread.sleep(3000);
		driver.findElement(ExpDate).sendKeys("08/29");
		Thread.sleep(3000);
		driver.findElement(PaymentName).sendKeys("John");
		driver.findElement(PaymentAddress).sendKeys("Wall street");
		driver.findElement(PaymentCity).sendKeys("Detroit");
		Select payState = new Select(driver.findElement(PaymentState));
		payState.selectByIndex(27);
		driver.findElement(PaymentState).sendKeys("Michigan");
		Thread.sleep(2000);
		driver.findElement(PaymentZip).sendKeys("48125");
		Thread.sleep(5000);
		WebElement Terms = driver.findElement(TermsandConditions);
		js.executeScript("arguments[0].click();", Terms);
		Thread.sleep(2000);
		WebElement Pay = driver.findElement(PayButton);
		js.executeScript("arguments[0].click();", Pay);
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		String message = driver.findElement(PaymentConfirmation).getText();
		System.out.println("Confirmation message:" + message);
		Thread.sleep(2000);
		Assert.assertTrue("Payment is Failed", message.contains("Congratulations!"));

	}

	private void validateMakepaymentwindow() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(MakePayment));
		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.findElement(SelectPaymentOption).click();
		Thread.sleep(2000);
		driver.findElement(MakePayment).click();
		Thread.sleep(3000);

	}

	private void validateAddDocuments() throws AWTException, InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(1000);
		Assert.assertTrue("Add documents page is not displayed", driver.findElement(AdddocumentsLink).isDisplayed());
		driver.findElement(AdddocumentsLink).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		Thread.sleep(3000);
		WebElement browse = driver.findElement(BrowseButton);
		Thread.sleep(3000);

		Thread.sleep(3000);
		String path = System.getProperty("user.dir");
		String otherFolder = path + "\\DigitalReferencePDFs\\test.pdf";
		System.out.println("Path:"+otherFolder);
		driver.findElement(By.xpath("//input[@id='fileAttachment']")).sendKeys(otherFolder);
		Thread.sleep(3000);
		driver.findElement(uploadButton).click();
		Thread.sleep(3000);
		Assert.assertTrue("Document is not uploaded properly", driver.findElement(EnrollmentContinue).isDisplayed());
		driver.findElement(EnrollmentContinue).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(MakePaymentlink));

		Thread.sleep(3000);
		driver.findElement(MakePaymentlink).click();
	}

	private void validateSummary() {
		// TODO Auto-generated method stub
		driver.findElement(FirstnameSummary).sendKeys("Test");
		driver.findElement(LastnameSummary).sendKeys("test");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(FirstnameConfirm).sendKeys("Test");
		driver.findElement(LastnameConfirm).sendKeys("test");
		driver.findElement(SubmitOnline).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		Assert.assertTrue("Online Submit not successfully filled", driver.findElement(EnrollmentManager).isDisplayed());

	}

	private void validateAttestations() {
		// TODO Auto-generated method stub
		driver.findElement(Attestationcheckbox).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		driver.findElement(continueAppliTop).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ReviewLabel));
		Assert.assertTrue("Attestation page not successfully filled", driver.findElement(ReviewLabel).isDisplayed());

	}

	private void validateAuthorization() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(continueAppliTop).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(ReviewLabel));
	}

	private void validateReview() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(ReviewLabel));
		Thread.sleep(2000);
		driver.findElement(continueAppli).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
	}

	private void validateApplicationInfo() throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(ContinueApp));
		Thread.sleep(5000);
		driver.findElement(SSNnumber).click();
		driver.findElement(SSNnumber).sendKeys("231-32-1423");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		Select Martial = new Select(driver.findElement(MartailStatus));
		Martial.selectByIndex(1);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		Select EligibleMedicar = new Select(driver.findElement(EligibleMedicare));
		EligibleMedicar.selectByIndex(2);
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		WebElement MedicalCoverageCheck = driver.findElement(MedicalCoverage);
		js.executeScript("arguments[0].click();", MedicalCoverageCheck);
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(AddressLine1).sendKeys("123");
		driver.findElement(City).sendKeys("Michigan");
		Thread.sleep(2000);
		driver.findElement(phonenumber).click();
		driver.findElement(phonenumber).sendKeys("211-313-1313");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(MailID).sendKeys("none@hap.org");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(ContinueApp).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ReviewLabel));
		Assert.assertTrue("Application Infor page not successfully filled",
		driver.findElement(ReviewLabel).isDisplayed());

	}

	private void validateWelcomePage() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(WelcomePageLabel));
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		Select drop1 = new Select(driver.findElement(DentalAttestation));
		drop1.selectByIndex(1);
		driver.findElement(WelcomePageContinue).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ApplicationName));
		Assert.assertTrue("Welcomepage not successfully filled", driver.findElement(ApplicationName).isDisplayed());

	}

	private void validateCreatAccount() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.findElement(NewAccountLink).click();

		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(CreateAccount));
		driver.findElement(FnameNewAccount).sendKeys("Test");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(LnameNewAccount).sendKeys("test");
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
//	Random randomGenerator = new Random();  
//	int randomInt = randomGenerator.nextInt(1000);  
		int Number = CommonMethods.getRndNumber();
		String mailid = "username" + Number + "@hap.com";

		driver.findElement(EmailNewAccount).sendKeys(mailid);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(ConfirmEmailNewAccount).sendKeys(mailid);
		driver.findElement(CreateAccount).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(EnrollmentManager));
		Assert.assertTrue("Account not successfully created", driver.findElement(EnrollmentManager).isDisplayed());
		driver.findElement(StartFormButton).click();
	}

	public String generateEmail(String domain, int length) {
		return RandomStringUtils.random(length, "abcdefghijklmnopqrstuvwxyz") + "@" + domain;
	}

	private void validateBeginApplication() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(Continue));
		driver.findElement(DetalCoverageRadio).click();
		driver.findElement(Continue).click();

	}

	private void validateGotoCart(String type) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Payment type:"+type);
		if(type.contains("AHL"))
		{
	List<WebElement> PlanType=driver.findElements(Planlist);
	int CountofPlantype = PlanType.size();
	for(int i=2;i<CountofPlantype;i++)
	{
		String Planname=driver.findElement(By.xpath("//table[@id='tblQuote']/tbody[1]/tr["+i+"]/td[1]/div[1]")).getText();
		System.out.println("Planname:"+Planname);
		if(Planname.contains("PPO"))
		{
			Thread.sleep(3000);
			driver.findElement(By.xpath("//table[@id='tblQuote']/tbody[1]/tr["+i+"]/td[6]/input[1]")).click();
			driver.findElement(GotoCart).click();
			driver.findElement(BeginAppliction).click();
			break;
		}
		else
		{
//			System.out.println("There is no PPO Plan");
//			driver.findElement(By.xpath("//table[@id='tblQuote']/tbody[1]/tr"+i+"/td[6]/input[1]")).click();
//			break;
		}
		
	}
		}
	else
	{
		driver.findElement(AddCart).click();
		driver.findElement(GotoCart).click();
		driver.findElement(BeginAppliction).click();

	}
	}

	private void validateAddIndividuals() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(Lastname));
		driver.findElement(Firstname).sendKeys("Test");
		driver.findElement(Lastname).sendKeys("test");
		driver.findElement(DOB).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(DOB).sendKeys("10/10/1998");
		driver.findElement(Gender).click();
		driver.findElement(Tobaccouse).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(StartShopping).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(AddCart));

	}

	private void validateSelectCoverage() throws InterruptedException {
		// TODO Auto-generated method stub
		// driver.findElement(EnrollmentRadio).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		Thread.sleep(2000);
		WebElement QERadio = driver.findElement(SelectedQualifyingRadioYes);
		QERadio.click();
		Thread.sleep(2000);
		driver.findElement(QualifyingEvent).click();
		// wait.until(ExpectedConditions.presenceOfElementLocated(DateofEvent));
		driver.findElement(DateofEvent).click();
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		driver.findElement(DateofEvent).sendKeys("10/20/2021");
		driver.findElement(DateofEvent).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		// wait.until(ExpectedConditions.presenceOfElementLocated(AddIndividuals));
		WebElement AddIndividualsclick = driver.findElement(AddIndividuals);
		js.executeScript("arguments[0].click();", AddIndividualsclick);
		// AddIndividualsclick.submit();
	}

}
