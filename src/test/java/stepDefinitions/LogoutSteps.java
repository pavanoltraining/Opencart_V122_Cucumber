package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import base.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.LogoutPage;
import pageobjects.MyAccountPage;

public class LogoutSteps {
    private MyAccountPage myAccountPage;
    private LogoutPage logoutPage;
    private HomePage homePage;
    private final WebDriver driver = BaseClass.getDriver();
    private final Logger logger = BaseClass.getLogger();

    @When("I click on the Logout link")
    public void clickOnLogoutLink() {
    	myAccountPage=new MyAccountPage(driver);
        logoutPage = myAccountPage.clickLogout();
        logger.info("Clicked on 'Logout' link.");
    }

    @When("I click on the Continue button")
    public void clickOnContinueButton() {
        homePage = logoutPage.clickContinue();
        logger.info("Clicked on 'Continue' button to navigate back to Home Page.");
    }

    @Then("I should be navigated back to the Home Page")
    public void shouldBeNavigatedBackToHomePage() {
        Assert.assertTrue("Home Page not displayed after logout.", homePage.isHomePageExists());
        logger.info("Logout successful, navigated back to Home Page.");
    }
}
