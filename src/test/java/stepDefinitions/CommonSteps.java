package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import base.BaseClass;
import io.cucumber.java.en.Given;

public class CommonSteps {
    private final WebDriver driver = BaseClass.getDriver();
    private final Logger logger = BaseClass.getLogger();

    @Given("I navigate to the application URL")
    public void navigateToApplicationURL() {
        String applicationUrl = BaseClass.getProperties().getProperty("appURL");
        driver.get(applicationUrl);
        logger.info("Navigated to application URL: {}", applicationUrl);
    }
}
