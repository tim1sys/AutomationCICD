package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="companytim.stepdefinitions", tags="@Regression", monochrome=true,
plugin="html:target/cucumber.html")
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
// we can pick which test to run by seperating it with tags

// we can also run the test with maven in command prompt by calling it and creating a profile for it 