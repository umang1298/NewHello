package com.qa.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;

import com.qa.base.TestBase;

import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import oracle.jdbc.driver.OracleDriver;

public class CommonMethods extends TestBase {
	
	
	public CommonMethods(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	public static String generateADateValueinTheFuture(int days) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, days);
		String newDate = sdf.format(c.getTime());
// Displaying the new Date after addition of Days
		System.out.println("Date after Addition: " + newDate);
		return newDate;
	}

	public static String returnPDFContent(String PDFURL) throws IOException {
//Create the object of the URL class from JAVA.net package and then pass the pdf url to interact with		
		URL url = new URL(PDFURL);
//Create object for the InputStream class to use the openStream method to open the Stream to read data where the pdf is hosted
		InputStream is = url.openStream();
//Create object for BufferedInputStream and pass the InputStream class object
		BufferedInputStream fileParse = new BufferedInputStream(is);
//Create an object of the PDDocument class, provided by PDFBox
		PDDocument document = null;
//Initialize the object reference of the PDFDocument class to load the buffered input stream class reference
		document = PDDocument.load(fileParse);
//Use the PDFTextStripper class's method - get text which will return a string
		String pdfContent = new PDFTextStripper().getText(document);
		return pdfContent;
	}

	public static int returnCurrentMonth() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("Mdd");
		Date date = new Date();
		String currentMonth = dateFormat.format(date); // 2015/10/26 12:10:39
		System.out.println("This is the current month->" + currentMonth);
		int currentMonthVal = Integer.parseInt(currentMonth);
		return currentMonthVal;

	}

	public static String returnConverteddateformatMMDDYYYY(String dateinformatyyyymmdd) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		String date1 = null;
		try {
			date = dateFormat.parse(dateinformatyyyymmdd);
			date1 = new SimpleDateFormat("MM/dd/yyyy").format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;

	}
	
	public static void writeResponsetoFile(Response response, String filepath) throws IOException {
		InputStream responseAsInptStream = response.asInputStream();
		byte[] buffer = new byte[responseAsInptStream.available()];
		responseAsInptStream.read(buffer);
		File targetFileForXMLResponse = new File(
				System.getProperty("user.dir") + File.separator + filepath);
		FileOutputStream outStream = new FileOutputStream(targetFileForXMLResponse);
		outStream.write(buffer);
		System.out.println("wrote to the file");
	}
	
public static String ConvertRiderDocName(String RiderDocName) {
		
		if(RiderDocName.contains("&")) {
			
			String RiderDocNew = RiderDocName.replace("&", "&amp;");
			RiderDocName = RiderDocNew;
			System.out.println("New Name -->" + RiderDocName);
		}
		
		return RiderDocName;
		
	}
	
public static void Takescreenshot(Scenario scenario) {
	
	try {

		String ScenarioName1 = scenario.getName();
		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
		//scenario.attach(screenshot, "image/png", ScenarioName1);
		scenario.embed(screenshot, "image/png");
	} catch (Exception e) {
		// Do Something
		System.out.println("Couldnt take the screenshot!");
	}
	
}

private static void copyFile(File source, File dest) throws IOException {
    Files.copy(source.toPath(), dest.toPath());
}

public static String CurrentDate() {
	// TODO Auto-generated method stub
	
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Date Todaydate = new Date();
	String currentdate = dateFormat.format(Todaydate);
	System.out.println("To date: " +currentdate);
	return currentdate;
}

public static String LastYearDate() { 
	Calendar instance = Calendar.getInstance();
	Date Todate = new Date();
    instance.setTime(Todate);
    instance.add(Calendar.YEAR, -1);
    SimpleDateFormat isoFormat = new SimpleDateFormat("MM/dd/yyyy");
    String lastyeardate = isoFormat.format(instance.getTime());
	System.out.println("From Date: " + lastyeardate);
	return lastyeardate;
	
}

public static int getRndNumber() {
    Random random=new Random();
    int randomNumber=0;
    boolean loop=true;
    while(loop) {
        randomNumber=random.nextInt();
        if(Integer.toString(randomNumber).length()==10 && !Integer.toString(randomNumber).startsWith("-")) {
            loop=false;
        }
        }
    return randomNumber;
}

public static void executeSql(String Query) {


	try{
	    DriverManager.registerDriver ( new OracleDriver () );
	    Connection connection = DriverManager.getConnection ("jdbc:oracle:thin:@hapdevel2.hap.org:1521:int2Stag","usrprov","tptresp1");  
	    PreparedStatement psProcToexecute = connection.prepareStatement(Query);
	    psProcToexecute.execute();
	}catch (Exception e) {
	    System.out.println(e.toString());  
	}
}

public static void CloseBrowser() {
	driver.quit();
}

public static void LaunchURL(String URL) {
	driver.get("chrome://settings/clearBrowserData");
	driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
	driver.get(URL);
	
}

public static void ThreadSleep(Integer int1) throws InterruptedException {
	// TODO Auto-generated method stub
	Thread.sleep(int1);
}

}


