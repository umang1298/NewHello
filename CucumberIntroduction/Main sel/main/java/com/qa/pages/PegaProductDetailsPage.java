package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class PegaProductDetailsPage extends TestBase{

	
	public PegaProductDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	
	//Page Factory
	
	By DocumentLink = By.xpath("//a[@class='Case_tools']");
	By ProductTab = By.xpath("(//span[@data-stl='1'])[3]");
	By DownloadBtn = By.xpath("//span[contains(text(),'Download')]");
	
	
	public void userclicksonfirstdocumentlink() throws InterruptedException {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.switchTo().frame("PegaGadget2Ifr");
		Thread.sleep(3000);
		for(int i=0; i<=2;i++){

			  try{
				  driver.findElement(DocumentLink).click();
			     break;
			  }
			  catch(Exception e){
				  System.out.println("Error on Clicking Document Link");
			     System.out.println(e.getMessage());
			  }

			}		
	}


	public void userdownloadsPDF() {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		
		for(int i=0; i<=2;i++){

			  try{
				  driver.findElement(DownloadBtn).click();
				  Thread.sleep(5000);
			     break;
			  }
			  catch(Exception e){
				  System.out.println("Error on Clicking Download PDF");
			     System.out.println(e.getMessage());
			  }

			}		
		
	}
}
