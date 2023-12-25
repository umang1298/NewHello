package com.qa.pages;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.Constants;

import io.restassured.path.xml.XmlPath;

public class MyHealthRemainderPage extends TestBase{

	public MyHealthRemainderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	By HealthReminder=By.xpath("//h1[contains(text(),'My Health Reminders')]");
	By MemberDropdown=By.id("member-picker");
	By cancerScreeningXpath=By.xpath("//h2[contains(text(),'Cancer Screening Reminders')]");
	By chronicXpath=By.xpath("//h2[contains(text(),'Chronic Health')]");
	By PreventionXpath=By.xpath("//h2[contains(text(),'Prevention and')]");
	public void validatesMyHealthRemainderPage(String responseXML) throws IOException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(HealthReminder));
		String subscriberXPath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber";
		String MembersXpath=	"Envelope.Body.GetAllContractsByMemberRecordNumberResponse.GetAllContractsByMemberRecordNumberResult.ResponseData.Subscriber.Members.Member";
		String healthReminder=driver.findElement(HealthReminder).getText();
		Assert.assertEquals(healthReminder,"My Health Reminders");
		ValidatesdropdownFunctionality(responseXML,subscriberXPath,MembersXpath);
		
	}

	private void ValidatesdropdownFunctionality(String responseXML, String subscriberXPath, String membersXpath) throws IOException {
		// TODO Auto-generated method stub
		String contents = new String(Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + File.separator + responseXML)));
			XmlPath xt = new XmlPath(contents);
			List<org.w3c.dom.Element> ListofTags = xt.getList(membersXpath);
			int NumberofMembers = ListofTags.size();
			if(NumberofMembers>1)
			{
				System.out.println("Dropdown should available");
				Select drop1=new Select(driver.findElement(MemberDropdown));
				List<WebElement> options = drop1.getOptions();
			for(int i=1;i<options.size();i++)
			{
				String membername=options.get(i).getText();
				System.out.println("Name:"+membername);
				String Cancer=driver.findElement(cancerScreeningXpath).getText();
				String Chronic=driver.findElement(chronicXpath).getText();
				String Prevention=driver.findElement(PreventionXpath).getText();
				Assert.assertTrue(Cancer.contains("Cancer Screening"));
				Assert.assertTrue(Chronic.contains("Chronic Health"));
				Assert.assertTrue(Prevention.contains("Prevention and Wellness"));
			}
			}
			else
			{
				System.out.println("Dropdown should not be available");
				String Cancer=driver.findElement(cancerScreeningXpath).getText();
				String Chronic=driver.findElement(chronicXpath).getText();
				String Prevention=driver.findElement(PreventionXpath).getText();
				Assert.assertTrue(Cancer.contains("Cancer Screening"));
				Assert.assertTrue(Chronic.contains("Chronic Health"));
				Assert.assertTrue(Prevention.contains("Prevention and Wellness"));
			}
	}
}