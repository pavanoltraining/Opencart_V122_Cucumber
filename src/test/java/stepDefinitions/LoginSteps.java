package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import base.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;

public class LoginSteps {
    private HomePage homePage;
    private LoginPage loginPage;
    private MyAccountPage myAccountPage;
    private final WebDriver driver = BaseClass.getDriver();
    private final Logger logger = BaseClass.getLogger();

    @When("I navigate to the Login page from the Home page")
    public void navigateToLoginPage() {
        homePage = new HomePage(driver);
        homePage.clickMyAccount();
        logger.info("Clicked on 'My Account' link.");
        loginPage = homePage.clickLogin();
        logger.info("Navigated to Login Page.");
    }

    @When("I enter valid login credentials")
    public void enterValidCredentials() {
        String userEmail = BaseClass.getProperties().getProperty("email");
        String userPassword = BaseClass.getProperties().getProperty("password");
        loginPage.setEmail(userEmail);
        loginPage.setPassword(userPassword);
        loginPage.clickLogin();
        logger.info("Entered login credentials and clicked 'Login'.");
    }

    @Then("I should be redirected to the My Account page")
    public void verifyMyAccountPage() {
        myAccountPage = new MyAccountPage(driver);
        Assert.assertTrue("Login failed: My Account page not displayed", myAccountPage.isMyAccountPageExists());
        logger.info("Login successful. 'My Account' page is displayed.");
    }
}
