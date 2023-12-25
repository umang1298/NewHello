package com.qa.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;

import de.redsix.pdfcompare.CompareResult;
import de.redsix.pdfcompare.PageArea;
import de.redsix.pdfcompare.PdfComparator;
import io.cucumber.java.Scenario;

public class PegaPDFValidation extends TestBase{

	public PegaPDFValidation(WebDriver driver) {
		// TODO Auto-generated constructor stub
		TestBase.driver = driver;
	}

	SoftAssert softassert = new SoftAssert();
	
	public void ValidatePEGAPDF(String referencePDF, String downloadedPDF) throws IOException {
		// TODO Auto-generated method stub
		File DownloadedPDF = new File(System.getProperty("user.home") + "/Downloads/" + downloadedPDF);
		File ReferencePDF = new File("PegaReferencePDFs/" + referencePDF);
		softassert.assertTrue(DownloadedPDF.exists() , "Download PDF -->" + downloadedPDF + " Did  not get Downloaded");
		softassert.assertTrue(ReferencePDF.exists() , "Reference PDF -->" + referencePDF + " Does not Exist");
		//For Downloaded PDF
		
				// Load the file
				PDDocument Downloadeddocument = PDDocument.load(DownloadedPDF);
				// Instantiate PDFTextStripper class
				PDFTextStripper pdfStripper = new PDFTextStripper();
				// Retrieving text from PDF document
				String DownloadedPDFContent = pdfStripper.getText(Downloadeddocument);
				System.out.println("This is the Downloaded PDF Content -->" +DownloadedPDFContent);
				// Write the PDF Content to .txt file
				String DownloadedPDFTextFilePath = "DownloadPDFTextFiles/DownloadPDFText.txt";
				FileUtils.writeStringToFile(new File(DownloadedPDFTextFilePath), DownloadedPDFContent);
				
		// For Reference PDF
				
				// Load the file
				PDDocument Referencedocument = PDDocument.load(ReferencePDF);
				// Instantiate PDFTextStripper class
					//Not Required
				// Retrieving text from PDF document
				String ReferencePDFContent = pdfStripper.getText(Referencedocument);
				System.out.println("This is the Reference PDF Content -->" +ReferencePDFContent);
				// Write the PDF Content to .txt file
				String ReferencePDFTextFilePath = "ReferencePDFTextFiles/ReferencePDFText.txt";
				FileUtils.writeStringToFile(new File(ReferencePDFTextFilePath), ReferencePDFContent);
				
				
		// Compare two text files
				
			//Comparetwotextfiles(DownloadedPDFTextFilePath, ReferencePDFTextFilePath);
				
				Bcompare(downloadedPDF, DownloadedPDFTextFilePath, ReferencePDFTextFilePath);
				
		softassert.assertAll();
	}
	
	public void Bcompare( String DownloadPdfName, String DownloadedPDFTextFilePath, String ReferencePDFTextFilePath) {
		
		//ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Beyond Compare 4\\BCompare.exe",
				//DownloadedPDFTextFilePath, ReferencePDFTextFilePath, "/fv=Text Compare", "/qc=binary");
		int length = DownloadPdfName.length();
		String DownloadPDFName = DownloadPdfName.substring(0, length-4);
		System.out.println("NameReq--> "+ DownloadPDFName);
		 try{
			   Process process = new ProcessBuilder("C:\\Program Files\\Beyond Compare 4\\BCompare", "@ShellScript/MyScript.txt",DownloadedPDFTextFilePath,
			    		ReferencePDFTextFilePath,"target\\" + DownloadPDFName +".html","/fv=Text Compare", "/qc=binary").start();
			   Comparetwotextfiles(DownloadedPDFTextFilePath, ReferencePDFTextFilePath);
			   }catch(Exception e){
			   }
		
		}
	
	
	public void userchecksPDFdownloadedsuccessfully(String downloadPDF) {
		// TODO Auto-generated method stub
		File DownloadedPDF = new File(System.getProperty("user.home") + "/Downloads/" + downloadPDF);
		softassert.assertTrue(DownloadedPDF.exists() , "Download PDF -->" + downloadPDF + " Did  not get Downloaded");
		softassert.assertAll();
	}

	public void ComparePDFandgeneratereport(String referencePDF, String downloadedPDF) throws IOException {
		
		File DownloadedPDF = new File(System.getProperty("user.home") + "/Downloads/" + downloadedPDF);
		File ReferencePDF = new File("PegaReferencePDFs/" + referencePDF);
		
//		boolean isEquals = new PdfComparator(ReferencePDF, DownloadedPDF).compare().writeTo("diffOutput");
//		if (!isEquals) {
//		    System.out.println("Differences found!");
//		}
		CompareResult output = new PdfComparator(ReferencePDF, DownloadedPDF).compare();
		Collection<PageArea> differenes = output.getDifferences();
		System.out.println("This is differenes Output-->" +differenes);
		}

	private void Comparetwotextfiles(String downloadedPDFTextFilePath, String referencePDFTextFilePath) throws IOException {
		// TODO Auto-generated method stub
		
		 BufferedReader br1 = new BufferedReader(new FileReader(downloadedPDFTextFilePath));
         
	        BufferedReader br2 = new BufferedReader(new FileReader(referencePDFTextFilePath));
	        
//	        FileInputStream fstream1 = new FileInputStream("C:\\text1.txt");
//	        FileInputStream fstream2 = new FileInputStream("C:\\text2.txt");
//
//	        DataInputStream in1= new DataInputStream(fstream1);
//	        DataInputStream in2= new DataInputStream(fstream2);

//	        BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
//	        BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));

	        String strLine1, strLine2;
	        int LineNum = 0;

	        while((strLine1 = br1.readLine()) != null && (strLine2 = br2.readLine()) != null){
	            
	        	LineNum++;
	        	
	        	if(strLine1.equals(strLine2)){
	            	
	            }
	            else {
	            	Assert.fail("In Line Number " + LineNum +"'" + strLine1 + "' is not same as '" + strLine2 + "'");
	            }
	        }
	    
	}


	
	
	        
	       
	    }
	
	
	

	
