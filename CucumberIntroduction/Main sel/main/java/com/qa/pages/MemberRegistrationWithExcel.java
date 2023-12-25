package com.qa.pages;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.util.Constants;
import com.qa.util.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MemberRegistrationWithExcel {
	public static void main(String[] args) throws InterruptedException {
		
		
		By HAPLogo = By.xpath("(//a/img[1])[1]");
		 By memberID= By.xpath("//input[@id='memberId']");
		 By groupID =By.xpath("//input[@id='groupID1']");
		 By lastName= By.xpath("//input[@id='lastName']");
		 By firstName=By.xpath("//input[@id='firstName']");
		 By zipCode=By.xpath("//input[@id='zipcode']");
		 By dateofBirth=By.xpath("//input[@id='dateOfBirth']");
		 By pass=By.xpath("//input[@id='password']");
		 By confirmpass=By.xpath("//input[@id='verifyPassword']");
		 By question=By.xpath("//input[@id='challengeQuestion']");
		 By answer=By.xpath("//input[@id='challengeAnswer']");
		 By verifyanswer= By.xpath("//input[@id='verifyChallengeAnswer']");
		 By AcceptPrivacy=By.xpath("//span[@class='checkmark']");
		 By Submit= By.xpath("//input[contains(@value,'Submit')]");
		 By alreadymember=By.xpath("//p[contains(text(),'If you canno')]");
		 By RegisComplete=By.xpath("//div[2]//div/div[2][contains(., 'Congratulations')]");
		 By OKButton=By.xpath("//*[contains(text(),'OK')]");
		 By Password = By.xpath("//input[@name='password']");
		 By UserId = By.xpath("//input[@id='userid']");
		 By Login = By.xpath("//span[contains(text(),'Log In')]");
		 By HAPlogo = By.xpath("(//a/img[1])[1]");
		
		
		Xls_Reader Reader=new Xls_Reader(System.getProperty("user.dir") + File.separator + "Excel_Data_Sheet/MemberRegistration.xlsx");
		String sheetName="Sheet1";
		
		int RowCount=Reader.getRowCount(sheetName);
		for(int RowNumber=2;RowNumber<=RowCount;RowNumber++)
		{
			System.out.println("Rownumber in the sheet-"+ RowNumber);
			WebDriverManager.chromiumdriver().setup();
			WebDriver driver= new ChromeDriver();
			driver.get("https://sportal.hap.org/membernew/memberportal/ora/public/displayMemberRegistration");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			
			driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
			String IDnum=Reader.getCellData(sheetName, "ID Number", RowNumber);
	    WebElement IDnumber=driver.findElement(memberID);
	    IDnumber.sendKeys(IDnum);
		
		String GroupId=Reader.getCellData(sheetName, "Group ID", RowNumber);
		WebElement groupid=driver.findElement(groupID);
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
		Thread.sleep(4000);
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
		Thread.sleep(5000);
		
		String pagesource=driver.getPageSource();
		
		if(pagesource.contains("Congratulations!"))
		{
			System.out.println("Congratulations! Your registration is complete.");
			
			 driver.navigate().to("https://sportal.hap.org");
			 String username=Reader.getCellData(sheetName, "MEME_RECORD_NO", RowNumber);
			 System.out.println("Registration is completed for " +username);
			// Reader.addColumn(sheetName,"Status");
			 Reader.setCellData(sheetName, "Status", RowNumber, "Member Created");
			 driver.findElement(UserId).sendKeys(username);
				driver.findElement(Password).sendKeys(pwd);
				driver.findElement(Login).click();
				WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
				WebElement logo = driver.findElement(HAPLogo);
				Thread.sleep(3000);
				boolean imagePresent = logo.isDisplayed();
				Assert.assertEquals(imagePresent, true);
		
			driver.close();
		}
		else
		{
			System.out.println("You are already a registered member.");
			WebElement okbutton= driver.findElement(OKButton);
			okbutton.click();
			driver.close();
			Reader.setCellData(sheetName, "Status", RowNumber, "Not Created");
		}
		
		}
}
}