package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.asserts.SoftAssert;

import com.qa.util.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	protected int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	Properties prop = new Properties();

// Method to Initialize the driver based on the browser value in the config.prop file
	public WebDriver init_driver(Properties prop, String appname) {
		String browser = prop.getProperty("browser");
		if (browser.equals("chrome")) {
			Map<String, String> mobileEmulation = new HashMap<>();

			mobileEmulation.put("deviceName", "iPhone X");
			WebDriverManager.chromiumdriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized"); // open Browser in maximized mode
			//options.setExperimentalOption("mobileEmulation", mobileEmulation);
			options.addArguments("disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions
			options.addArguments("--disable-gpu"); // applicable to windows os only
			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			options.addArguments("--no-sandbox"); // Bypass OS security model
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			final String regex = "^\\D*$";
			final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			final Matcher matcher = pattern.matcher(System.getProperty("user.name"));
			boolean isHuman = matcher.matches();

			if (isHuman) {
				System.out.println("Chrome starting maximized - isHuman: true " + " process run by "
						+ System.getProperty("user.name"));
				options.addArguments("--start-maximized");
			} else {


				System.out.println("Chrome starting headless - isHuman: false " + " process run by "
						+ System.getProperty("user.name"));
				options.setHeadless(true);
				options.addArguments("--window-size=1980,1080");

			}

			driver = new ChromeDriver(options);
		}

		else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else if (browser.equals("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();

		} else {
			System.out.println("Please pass the correct browser name..");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String Environment = prop.getProperty("Environment");
		if(Environment.equals("sportal")) {
		switch (appname) {
		case "Portal":
			driver.get(prop.getProperty("PortalUrl"));
			System.out.println("URL Launched"+prop.getProperty("PortalUrl"));
			break;
		case "MemberRegistrationPortal":
			driver.get(prop.getProperty("MemberRegistrationPortalURL"));
			break;
		case "PegaApplication":
			driver.get(prop.getProperty("PegaApplicationURL"));
			break;
		case "EmployeePortal":
			driver.get(prop.getProperty("EmployeePortalURL"));
			break;
		case "ForgotPasswordPage":
			driver.get(prop.getProperty("ForgotPasswordURL"));
			break;
		case "ForgotUserNamePage":
            driver.get(prop.getProperty("ForgotUserNameURL"));
            break;
		case "ProducerRegistrationPage":
            driver.get(prop.getProperty("ProducerRegistrationURL"));
            break;
        case "ProspectiveRegistrationPage":
                driver.get(prop.getProperty("ProspectiveRegistrationURL"));
                break;
        case "ProviderRegistrationPage":
            driver.get(prop.getProperty("ProviderRegistrationURL"));
            break;
        case "ProspectForgotUsernamePage":
            driver.get(prop.getProperty("ProspectiveMemberForgotUsernameURL"));
            break;
            
		default:
			break;
		}
			
		}
		else if(Environment.equals("tportal"))  {
			switch (appname) {
			case "Portal":
				driver.get(prop.getProperty("PortalUrlTest"));
				System.out.println("URL Launched"+prop.getProperty("PortalUrlTest"));
				break;
			case "MemberRegistrationPortal":
				driver.get(prop.getProperty("MemberRegistrationPortalURLTest"));
				break;
			case "PegaApplication":
				driver.get(prop.getProperty("PegaApplicationURL"));
				break;
			case "EmployeePortal":
				driver.get(prop.getProperty("EmployeePortalURLTest"));
				break;
			case "ForgotPasswordPage":
				driver.get(prop.getProperty("ForgotPasswordURLTest"));
				break;
			case "ForgotUserNamePage":
	            driver.get(prop.getProperty("ForgotUserNameURLTest"));
	            break;
			case "ProducerRegistrationPage":
	            driver.get(prop.getProperty("ProducerRegistrationURLTest"));
	            break;
	        case "ProspectiveRegistrationPage":
	                driver.get(prop.getProperty("ProspectiveRegistrationURLTest"));
	                break;
	        case "ProviderRegistrationPage":
	            driver.get(prop.getProperty("ProviderRegistrationURLTest"));
	            break;
	        case "ProspectForgotUsernamePage":
	            driver.get(prop.getProperty("ProspectiveMemberForgotUsernameURLTest"));
	            break;
	            
			default:
				break;
			}
		}
			else if(Environment.equals("dportal"))  {
				switch (appname) {
				case "Portal":
					driver.get(prop.getProperty("PortalUrlDev"));
					System.out.println("URL Launched"+prop.getProperty("PortalUrlDev"));
					break;
				case "MemberRegistrationPortal":
					driver.get(prop.getProperty("MemberRegistrationPortalURLDev"));
					break;
				case "PegaApplication":
					driver.get(prop.getProperty("PegaApplicationDev"));
					break;
				case "EmployeePortal":
					driver.get(prop.getProperty("EmployeePortalURLDev"));
					break;
				case "ForgotPasswordPage":
					driver.get(prop.getProperty("ForgotPasswordURLDev"));
					break;
				case "ForgotUserNamePage":
		            driver.get(prop.getProperty("ForgotUserNameURLDev"));
		            break;
				case "ProducerRegistrationPage":
		            driver.get(prop.getProperty("ProducerRegistrationURLDev"));
		            break;
		        case "ProspectiveRegistrationPage":
		                driver.get(prop.getProperty("ProspectiveRegistrationURLDev"));
		                break;
		        case "ProviderRegistrationPage":
		            driver.get(prop.getProperty("ProviderRegistrationURLDev"));
		            break;
		        case "ProspectForgotUsernamePage":
		            driver.get(prop.getProperty("ProspectiveMemberForgotUsernameURLDev"));
		            break;
		            
				default:
					break;
				}
		}
		driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.Implicit_Wait_TimeOut, TimeUnit.SECONDS);
		return driver;
	}

	// Method to initialize the properties from the config.properties file
	public Properties initialize_properties() {

		try {
			// To get to the config file, create an object reference for the FileInput
			// Scream class and then give the path to the config file
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + File.separator + "src/main/java/com/qa/config/config.properties");
			// to load all the properties from the config.properties file
			prop.load(ip);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;
	}

}
