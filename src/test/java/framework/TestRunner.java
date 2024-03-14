package framework;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions (
		monochrome = true,
		dryRun = false,
		features = "src/test/java/features",
		glue = {"logic","framework"},
		plugin = {"pretty","json:target/cucumber.json", "html:target/cucumber-html-report"},
		tags = "@createData"
		)
public class TestRunner extends AbstractTestNGCucumberTests {

}
