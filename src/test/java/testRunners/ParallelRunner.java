package testRunners;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(

		features= {"src/test/resources/features/"},
		//features = { "src/test/resources/features/Registration.feature", "src/test/resources/features/Login.feature" },
		// features= {"src/test/resources/features/Registration.feature"},
		//features= {"src/test/resources/features/Login.feature"},
		//features= {"@target/rerun.txt"},
		//features= {"src/test/resources/features/LoginWithRegisteredUser.feature"},
		glue = { "stepDefinitions", "hooks" }, 
		plugin = { "pretty", // Prints formatted Gherkin steps in the console
				"html:reports/cucumber.html", // Generates an HTML report
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", // Extent Report integration
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", // Allure Report integration
				"rerun:target/rerun.txt"

		}, 
		dryRun = false,   
		monochrome=true,
		tags= "@sanity"  // this will execute only sanity
		//tags= "@regression"  // this will execute only sanity
		//tags="@sanity and @regression" // Executes scenarios with both @sanity and @regression
		//tags="@sanity or @regression"   //Executess sceanrio either sanity or regression
		//tags="@sanity and not @regression"  // Executes scenarios with @sanity but not @regression
)
public class ParallelRunner extends AbstractTestNGCucumberTests {

	 	@DataProvider(parallel=true)
	    public Object[][] scenarios() {
	      return super.scenarios();
	    }

}
