package Runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Features/" ,
		glue= {"com.qa.stepDefinitions"},
		plugin= {"json:target/cucumber-json-report.json"},
		tags= {"@MEMBERELIGIBILITY03"},
		dryRun= false
		
		)

			

public class TestRunner {
	@AfterClass()
	public static void generateEmail() throws Exception {
		CucumberResultsOverview results = new CucumberResultsOverview();
		results.setOutputDirectory("target");
		results.setOutputName("cucumber-results");
		results.setSourceFile("./target/cucumber-json-report.json");
		results.execute();
	}

}
