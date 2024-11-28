package hooks;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import base.BaseClass;
import io.cucumber.java.*;

public class Hooks {

    @Before(order=0)
    public void setup() throws Exception {
        BaseClass.initialSetup();
    }
    
 
    @After
    public void tearDown() {
        BaseClass.quitDriver();
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        WebDriver driver = BaseClass.getDriver();
        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
    }
}
