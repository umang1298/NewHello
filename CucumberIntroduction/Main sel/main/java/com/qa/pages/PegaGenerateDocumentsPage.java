package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.Constants;

public class PegaGenerateDocumentsPage extends TestBase{

	public PegaGenerateDocumentsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}
	JavascriptExecutor js=(JavascriptExecutor) driver;
	//Page Factory
	
	By MedicalProductTextBox = By.xpath("//input[@id='MedicalName']");
	By PharmacyProductTextBox = By.xpath("//input[@id='RxName']");
	By GenerateDocButton = By.xpath("//button[contains(text(),'Generate Documents')]");

	public void userentersproductidinthetextbox(String product, String field) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.switchTo().frame("PegaGadget1Ifr");
		WebElement Field = driver.findElement(By.xpath("//input[@id='" + field + "']"));
//		Field.click();
//		Field.click();
//		WebDriverWait wait = new WebDriverWait(driver, Constants.Page_Load_TimeOut);
//		wait.until(ExpectedConditions.elementToBeClickable(MedicalProductTextBox));
//		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		Field.sendKeys(product);
		
	
	
	}

	public void userselectsthecheckbox(String documenttype) {
		// TODO Auto-generated method stub
		//driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		//WebElement myDynamicElement1 = new WebDriverWait(driver, 20).until(
		  //  ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(),'" + documenttype + "')]//..//input[2]")));
		for(int i=0; i<=2;i++){

			  try{
				  Thread.sleep(3000);
				driver.findElement(By.xpath("//label[contains(text(),'" + documenttype + "')]//..//input[2]")).click();
				
				
			     break;
			  }
			  catch(Exception e){
			     System.out.println(e.getMessage());
			  }

			}

	
	}

	public void clickGenrateDocuments() throws InterruptedException {
		// TODO Auto-generated method stub
		WebElement myDynamicElement1 = new WebDriverWait(driver, 20).until(
			    ExpectedConditions.presenceOfElementLocated(GenerateDocButton));
		Thread.sleep(3000);
		for(int i=0; i<=2;i++){

			  try{

				  driver.findElement(By.xpath("//button[contains(text(),'Generate Documents')]")).click();
				  Thread.sleep(3000);
				  driver.switchTo().defaultContent();
			     break;

			  }

			  catch(Exception e){

			     System.out.println(e.getMessage());

			  }

			}
		
	}

	public void userentersproductidinthetextbox(String medicalProduct, String pharmacyProduct, String medicalTextBox,
			String rxTextBox) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.switchTo().frame("PegaGadget1Ifr");
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		WebElement myDynamicElement1 = new WebDriverWait(driver, 20).until(
			    ExpectedConditions.presenceOfElementLocated(MedicalProductTextBox));
		for(int i=0; i<=2;i++){

			  try{

		driver.findElement(By.xpath("//input[@id='" + medicalTextBox + "']")).sendKeys(medicalProduct);
		 break;

			  }

			  catch(Exception e){

			     System.out.println(e.getMessage());

			  }

			}
		WebElement myDynamicElement2 = new WebDriverWait(driver, 20).until(
			    ExpectedConditions.presenceOfElementLocated(PharmacyProductTextBox));
		for(int i=0; i<=2;i++){

			  try{
				  driver.findElement(By.xpath("//input[@id='" + rxTextBox + "']")).sendKeys(pharmacyProduct);
		 break;

			  }

			  catch(Exception e){

			     System.out.println(e.getMessage());

			  }

			}
	}

}
