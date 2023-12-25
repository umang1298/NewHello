package com.qa.pages;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qa.util.CommonMethods;

import oracle.jdbc.driver.OracleDriver;

public class Test {
	

//	public static void main(String[] args) throws NoSuchAlgorithmException {
//
//    String password = "Today1234";
//
//    AES256TextEncryptor encryptor = new AES256TextEncryptor();
//    encryptor.setPassword("some_salt");
//    String myEncryptedText = encryptor.encrypt(password);
//    System.out.println("Encrypted: "+myEncryptedText);
//	}
//    public static void main(String[] args) throws NoSuchAlgorithmException {
//    	 AES256TextEncryptor encryptor = new AES256TextEncryptor();
//    	 encryptor.setPassword("some_salt");
//    String plainText = encryptor.decrypt("9qYZfSZ4sCmuoQWZOQBXoNz6iLRRSmjfxcjMmz63JisJQfLO4ULrYYtSxcmuFV12");
//    System.out.println("Decrypted: "+plainText);
// }
	
    public static void main(String[] args)  {
    	String strProc =
    	         "delete from MANAGE_USERS where user_id in ('50101349','1730508953')";

    	try{
    	    DriverManager.registerDriver ( new OracleDriver () );
    	    Connection connection = DriverManager.getConnection ("jdbc:oracle:thin:@hapdevel2.hap.org:1521:int2Stag","usrprov","tptresp1");  
    	    PreparedStatement psProcToexecute = connection.prepareStatement(strProc);
    	    psProcToexecute.execute();
    	}catch (Exception e) {
    	    System.out.println(e.toString());  
    	}
    }

    
//	public static void main(String[] args) {
//		String encryptedPassword = "bihUrfWdoieW3llZhM59m/ZHr5iDlYnb";
//		 StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
//	     decryptor.setPassword(encryptedPassword);
//	     System.out.println(decryptor.decrypt(encryptedPassword));
//	    
//	}
}