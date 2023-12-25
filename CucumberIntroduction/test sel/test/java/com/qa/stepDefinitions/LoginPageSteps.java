package com.qa.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;

import com.qa.base.TestBase;
import com.qa.pages.EmployeePortalLandingPage;
import com.qa.pages.HAPPortalLandingPage;
import com.qa.pages.LoginPage;
import com.qa.util.CommonMethods;
import com.qa.util.ScenarioContext;
import com.qa.util.Recorder.MyScreenRecorder;

import gherkin.formatter.Reporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginPageSteps extends TestBase {

	TestBase base;
	static Properties prop;
	LoginPage loginPage =  new LoginPage(driver);
	HAPPortalLandingPage hAPPortalLandingPage;
	EmployeePortalLandingPage employeePortalLandingPage;
	private String ScenarioName1=null;
	boolean startrecording;
	
	@Given("user launches the portal {string}")
	public void user_launches_the_portal(String appname) {
		// Write code here that turns the phrase above into concrete actions
		base = new TestBase();
		prop = base.initialize_properties();
		driver = base.init_driver(prop, appname);
	}
	
	@Then("user hits the URL {string}")
	public void user_hits_the_URL(String appname) {
	    // Write code here that turns the phrase above into concrete actions
		String URL = prop.getProperty(appname);
		CommonMethods.LaunchURL(URL);
	}

	@Given("user logs in to the SPortal {string} application with user id {string} and password {string}")
	public void user_logs_in_to_the_SPortal_application_with_user_id_and_password(String usertype,String UserID,
		String PasswordToLogin) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		hAPPortalLandingPage = loginPage.LoginToApp(usertype,UserID, PasswordToLogin);
	}

	@Given("user logs in to the {string} Portal with user id {string} and password {string}")
	public void user_logs_in_to_the_Employee_Portal_with_user_id_and_password(String usertype,String UserId, String Password) {
	    // Write code here that turns the phrase above into concrete actions
	    employeePortalLandingPage = loginPage.logintoEmployeePortal(usertype,UserId,Password);
	}


	@Then("user clicks signs in with organisational account")
	public void user_signs_in_with_organisational_account() throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
		loginPage.userclicksonSignInWithOrganisationalAccountLink();
	}
	
	@Given("user sets the {string} key to value {string}")
	public void user_sets_the_key_to_value(String MemberIDKey, String MemberVal) {
		// Write code here that turns the phrase above into concrete actions
		ScenarioContext.setContext(MemberIDKey, MemberVal);
	}

	@Given("user sets the value of date to {int} days from now and saves it to {string} key")
	public void user_sets_the_value_of_date_to_days_from_now_and_saves_it_to_key(int daycount, String keytoStoreDate) {
		// Write code here that turns the phrase above into concrete actions
		String date = CommonMethods.generateADateValueinTheFuture(daycount);
		ScenarioContext.setContext(keytoStoreDate, date);

	}

	@Given("I start the video recording for this scenario")
	public void i_start_the_video_recording_for_this_scenario() throws Exception {
		// Write code here that turns the phrase above into concrete actions

		MyScreenRecorder.startRecording(ScenarioName1);
		startrecording = true;
	}
	@After
	@Given("I stop the video recording for this scenario")
	public void i_stop_the_video_recording_for_this_scenario() throws Exception {
		// Write code here that turns the phrase above into concrete actions
		if(startrecording==true) {
		MyScreenRecorder.stopRecording(ScenarioName1);
		System.out.println("Stopped Recording");
		}
	}

	@Before()
	public void setUp(Scenario scenario) {
		System.out.println("STARTING TEST EXECUTION FOR->  " + scenario.getName());
		ScenarioName1= scenario.getName();
	}

	@After()
	public void closeBrowser(Scenario scenario) throws WebDriverException, SQLException {
		if (scenario.isFailed()) {
			try {
				ScenarioName1= scenario.getName();
				TakesScreenshot ts = (TakesScreenshot) driver;
				byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
				//scenario.attach(screenshot, "image/png", ScenarioName1);
				scenario.embed(screenshot, "image/png");

			} catch (Exception e) {
				// Do Something
				System.out.println("Couldnt take the screenshot!");
			}

		}
		driver.quit();

	}
	
	@Then("user clears the username and password field")
	public void user_clears_the_username_and_password_field() {
	    // Write code here that turns the phrase above into concrete actions
		loginPage.UserClearsTheUsernameAndPasswordField();
	}

}
