package com.qa.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.CommonMethods;
import com.qa.util.Constants;
import com.qa.util.ScenarioContext;

import io.restassured.path.xml.XmlPath;

public class ForgotPasswordPage extends TestBase{

	public ForgotPasswordPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver =driver;
	}

	//Page Factory
	By YesRadioButton = By.xpath("//input[@value='yes']//..//label");
	By NoRadioButton = By.xpath("//input[@value='no']//..//label");
	By MedicareRadioButton = By.xpath("//input[@value='MA']//..//label");
	By MedicaidRadioButton = By.xpath("//input[@value='MIDWEST']//..//label");
	By UserNameorIDNumberTextBox = By.xpath("//input[@id='userIdAndName']");
	By IdNumberTextBox = By.xpath("//input[@id='memberId']");
	By DOBTextBox = By.xpath("//input[@id='dateOfBirth']");
	By GroupIdTextBox = By.xpath("//input[@id='groupId']");
	By ZipCodeTextBox = By.xpath("//input[@id='zipcode']");
	By MedicaidIDTextBox = By.xpath("//input[@id='medicaidID']");
	By SubmitButton = By.xpath("//input[@id='submit-btn-id']");
	By SecurityAnsSubmit = By.xpath("//button[@id='submit-btn-id']");
	By NewPasswordSubmit = By.xpath("//button[contains(text(),'Submit')]");
	By SecurityAnsTextBox = By.xpath("//input[@id='challengeAnswer']");
	By SecurityAnsTextBoxProvider = By.xpath("//input[@id='forgotPasswordChallengeAns']");
	By NewPasswordTextBox = By.xpath("//input[@id='password']");
	By ConfirmNewPasswordTextBox = By.xpath("//input[@id='verifyPassword']");
	By NewPasswordTextBoxProvider = By.xpath("//input[@id='newPassword']");
	By ConfirmNewPasswordTextBoxProvider = By.xpath("//input[@id='confirmNewPassword']");
	By SuccessfulMessage = By.xpath("//div[contains(text(),'Password Changed Successfully!')]");
	By ProspectiveSuccesmessage= By.xpath("//div[contains(text(),'Password Changed Confirmation')]");
	By LoginLink = By.xpath("//a[contains(text(),'Login')]");
	By LoginLinkProvider = By.xpath("//a[contains(text(),'hap.org')]");
	By Okbutton = By.xpath("//button[contains(text(),'OK')]");
	By SSNTextBox=By.xpath("//input[@name='ssn']");
	By prospectiveDOB=By.xpath("//input[@name='dateOfBirth']");
	By SecurityAnswerContinue=By.xpath("//input[@id='submit-btn-id']");
	By EmailAddressTextBox = By.xpath("//input[@id='emailAddress']");
	By EmailAddressTextBoxProvider = By.xpath("//input[@id='forgotPasswordEmailId']");
	By ProducerSecurityAnsTextBox = By.xpath("//input[@id='challengeAnswer1']");
	By YouhaveAnsweredIncorrectlyMessage = By.xpath("//div[contains(text(),'You have answered incorrectly three times. Your account has been deactivated. Please register again.')]");
	By NewPasswordContinueProvider = By.xpath("//div[@id='submitbuttondiv']//input");
	By ChallengeAnswer = By.xpath("//input[@id='challengeAnswer1']");
	By NewPasswordContinueProducer = By.xpath("//div[@id='submitbuttondiv']//input");
	By NewPasswordTextBoxProducer = By.xpath("//input[@id='password']");
	By ConfirmPasswordTextBoxProducer = By.xpath("//input[@id='verifyPassword']");
	By LoginProducerLink = By.xpath("//a[contains(text(),'Producer Portal')]");
	By ProducerRegistrationSuccessfulMessage = By.xpath("//div[contains(text(),'Password Changed Successfully')]");
	
	
	
	SoftAssert softassert = new SoftAssert();
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	
	public void userselectsyesornoforthequestion(String yesorno) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(YesRadioButton));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='"+yesorno+"']//..//label")).click();
		if(yesorno.equals("yes")) {
			wait.until(ExpectedConditions.elementToBeClickable(MedicareRadioButton));
		}
		else {
			wait.until(ExpectedConditions.elementToBeClickable(UserNameorIDNumberTextBox));
		}
		
	}


	public void userentersrequiredvaluesinforgotpwdpageformemberfromresponsexml(String memberRecordNumber,
			String responseXMLPath) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String MemRecNumVal = (String) ScenarioContext.getContext(memberRecordNumber);
		
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXMLPath)));
		System.out.println("Contents-->" +contents);
		
		XmlPath xt = new XmlPath(contents);
		
		String xpathtoMembers = "Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member";
		
		List<org.w3c.dom.Element> listOfMembers = xt.getList(xpathtoMembers);
		int CountofMembers = listOfMembers.size();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		for(int i=0;i<CountofMembers;i++) {
			String xpathtomemberrecnumber = "Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member["+i+"].RecordNumber";
			String MemberRecordNumber = xt.getString(xpathtomemberrecnumber);
			System.out.println("MemberRecordNumber for " +i+ " Member -->" +MemberRecordNumber);
			if(MemberRecordNumber.equals(MemRecNumVal)) {
				String XpathtoMedicaidNumber = "Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member["+i+"].MedicaidNumber";
				String MedicaidNumber = xt.getString(XpathtoMedicaidNumber);
				String xpathtoGroupID ="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member["+i+"].EligibilityInfo.Eligibility[0].GroupId";
				String XpathtoSubGroupID ="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member["+i+"].EligibilityInfo.Eligibility[0].SubgroupId";
				String xpathtoDOB ="Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member["+i+"].BirthDate";
				String xpathtoMemberID = "Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member["+i+"].MemberId";
				String xpathtozipCode = "Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber[0].Members.Member["+i+"].Addresses.Address.Zip";
				String ZipCode = xt.getString(xpathtozipCode);
				String GroupId = xt.getString(xpathtoGroupID);
				System.out.println("GroupId -->"+GroupId);
				String SubGroupId = xt.getString(XpathtoSubGroupID);
				System.out.println("SubGroupId-->"+SubGroupId);
				String MemberId = xt.getString(xpathtoMemberID);
				String DOBresponse = xt.getString(xpathtoDOB);
				String DOB =  DOBresponse.substring(0, 10);
				String GroupIdtoEnter = GroupId+SubGroupId;
				String DobToEnter = CommonMethods.returnConverteddateformatMMDDYYYY(DOB);
				
				if(!MedicaidNumber.isEmpty()) {
					
					wait.until(ExpectedConditions.elementToBeClickable(MedicaidRadioButton));
					driver.findElement(MedicaidRadioButton).click();
					wait.until(ExpectedConditions.elementToBeClickable(MedicaidIDTextBox));
					driver.findElement(IdNumberTextBox).sendKeys(MemberId);
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(ZipCodeTextBox));
					driver.findElement(DOBTextBox).sendKeys(DobToEnter);
					driver.findElement(MedicaidIDTextBox).sendKeys(MedicaidNumber);
					driver.findElement(ZipCodeTextBox).sendKeys(ZipCode);
					
				}
				else {
					wait.until(ExpectedConditions.elementToBeClickable(IdNumberTextBox));
					driver.findElement(IdNumberTextBox).sendKeys(MemberId);
					js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(ZipCodeTextBox));
					driver.findElement(DOBTextBox).sendKeys(DobToEnter);
					driver.findElement(GroupIdTextBox).sendKeys(GroupIdtoEnter);
					driver.findElement(ZipCodeTextBox).sendKeys(ZipCode);
					
				}
				
			}
			//break;
		}
		Thread.sleep(3000);
		driver.findElement(SubmitButton).click();
		Thread.sleep(3000);
	}


	public void userentersSecurityAnswerandClickssubmit(String securityAnswer) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnsSubmit));
		driver.findElement(SecurityAnsTextBox).sendKeys(securityAnswer);
		driver.findElement(SecurityAnsSubmit).click();
	}


	public void usersetsnewpasswordformember() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(NewPasswordSubmit));
		int Number = CommonMethods.getRndNumber();
		driver.findElement(NewPasswordTextBox).sendKeys("Today"+Number);
		driver.findElement(ConfirmNewPasswordTextBox).sendKeys("Today"+Number);
		driver.findElement(NewPasswordSubmit).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(SuccessfulMessage));
		wait.until(ExpectedConditions.elementToBeClickable(LoginLink));
	}


	public void userenterswrongsecurityanswer() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnsSubmit));
		driver.findElement(SecurityAnsTextBox).sendKeys("B");
		driver.findElement(SecurityAnsSubmit).click();
		driver.findElement(Okbutton).click();;
		driver.findElement(SecurityAnsTextBox).sendKeys("B");
//		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnsSubmit));
		driver.findElement(SecurityAnsSubmit).click();
		driver.findElement(Okbutton).click();;
		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnsSubmit));
//		driver.findElement(SecurityAnsTextBox).sendKeys("B");
		driver.findElement(SecurityAnsSubmit).click();
		driver.findElement(By.xpath("//div[@id='loading-modal']/div/div/div[2]/div[2]/div/div/button")).click();
	}


	public void Userentersusername(String username) {
		// TODO Auto-generated method stub
		driver.findElement(UserNameorIDNumberTextBox).sendKeys(username);
		driver.findElement(SubmitButton).click();
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnswerContinue));
	}


	public void Prospectiveuserenterswrongsecurityanswer(String dOB, String sSN) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		driver.findElement(prospectiveDOB).clear();
		driver.findElement(prospectiveDOB).click();
		driver.findElement(prospectiveDOB).sendKeys(dOB);
		driver.findElement(SSNTextBox).sendKeys(sSN);
		driver.findElement(SecurityAnsTextBox).sendKeys("B");
		driver.findElement(SecurityAnswerContinue).click();
		wait.until(ExpectedConditions.elementToBeClickable(Okbutton));
		driver.findElement(Okbutton).click();
		driver.findElement(SecurityAnsTextBox).sendKeys("B");
		driver.findElement(SecurityAnswerContinue).click();
		wait.until(ExpectedConditions.elementToBeClickable(Okbutton));
		driver.findElement(Okbutton).click();
		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnswerContinue));
		driver.findElement(SecurityAnswerContinue).click();
		wait.until(ExpectedConditions.elementToBeClickable(Okbutton));
		Assert.assertTrue(driver.findElement(YouhaveAnsweredIncorrectlyMessage).isDisplayed(), "Unregister unsuccessfull");
		driver.findElement(Okbutton).click();
	}


	public void unregisterProducer(String email, String sSN) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnswerContinue));
		driver.findElement(EmailAddressTextBox).sendKeys(email);
		driver.findElement(SSNTextBox).sendKeys(sSN);
		driver.findElement(ProducerSecurityAnsTextBox).sendKeys("Wrong Answer");
		driver.findElement(SecurityAnswerContinue).click();
		wait.until(ExpectedConditions.elementToBeClickable(Okbutton));
		driver.findElement(Okbutton).click();
		driver.findElement(SecurityAnswerContinue).click();
		wait.until(ExpectedConditions.elementToBeClickable(Okbutton));
		driver.findElement(Okbutton).click();
		driver.findElement(SecurityAnswerContinue).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(YouhaveAnsweredIncorrectlyMessage));
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(YouhaveAnsweredIncorrectlyMessage).isDisplayed(), "Unregister unsuccessfull");
		
		
	}


	public void userentersPrimaryEmail(String email) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnswerContinue));
		driver.findElement(EmailAddressTextBoxProvider).sendKeys(email);
	}


	public void userentersSecurityAnswerforProviderandClickssubmit(String securityAnswer) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnswerContinue));
		driver.findElement(SecurityAnsTextBoxProvider).sendKeys(securityAnswer);
		driver.findElement(SecurityAnswerContinue).click();
	}


	public void usersetsnewpasswordforProvider() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(NewPasswordContinueProvider));
		int Number = CommonMethods.getRndNumber();
		driver.findElement(NewPasswordTextBoxProvider).sendKeys("Today"+Number);
		driver.findElement(ConfirmNewPasswordTextBoxProvider).sendKeys("Today"+Number);
		driver.findElement(NewPasswordContinueProvider).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(SuccessfulMessage));
		wait.until(ExpectedConditions.elementToBeClickable(LoginLinkProvider));
	}


	public void userentersPrimaryEmailandSSN(String email, String sSN) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(EmailAddressTextBox).sendKeys(email);
		driver.findElement(SSNTextBox).sendKeys(sSN);
	}


	public void userentersSecurityanswerandclicksonSubmitButton(String securityAnswer) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		driver.findElement(ChallengeAnswer).sendKeys(securityAnswer);
		driver.findElement(SubmitButton).click();
	}


	public void usersetsnewpasswordforProducer() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(NewPasswordContinueProducer));
		int Number = CommonMethods.getRndNumber();
		driver.findElement(NewPasswordTextBoxProducer).sendKeys("Today"+Number);
		driver.findElement(ConfirmPasswordTextBoxProducer).sendKeys("Today"+Number);
		driver.findElement(NewPasswordContinueProducer).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ProducerRegistrationSuccessfulMessage));
		wait.until(ExpectedConditions.elementToBeClickable(LoginProducerLink));
		
	}

	public void userentersDOBandSSN(String dob, String ssn, String securityAnswer) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(SecurityAnswerContinue));
		driver.findElement(prospectiveDOB).clear();
		driver.findElement(prospectiveDOB).click();
		driver.findElement(prospectiveDOB).sendKeys(dob);
		driver.findElement(SSNTextBox).sendKeys(ssn);
		driver.findElement(SecurityAnsTextBox).sendKeys(securityAnswer);
		driver.findElement(SecurityAnswerContinue).click();
		//new password set
		
	}


	public void usersetsnewpasswordforProspectiveMember() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(NewPasswordSubmit));
		int Number = CommonMethods.getRndNumber();
		driver.findElement(NewPasswordTextBox).sendKeys("Today"+Number);
		driver.findElement(ConfirmNewPasswordTextBox).sendKeys("Today"+Number);
		driver.findElement(NewPasswordSubmit).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ProspectiveSuccesmessage));
		wait.until(ExpectedConditions.elementToBeClickable(LoginLink));
	}
}
