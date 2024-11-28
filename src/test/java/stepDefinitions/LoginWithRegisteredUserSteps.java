package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import base.BaseClass;
import hooks.TestContext;
import io.cucumber.java.en.Then;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;

public class LoginWithRegisteredUserSteps {

    // Page object references
    private HomePage homePage;
    private LoginPage loginPage;
    private MyAccountPage myAccountPage;

    // WebDriver instance
    private final WebDriver driver = BaseClass.getDriver();

    // TestContext to manage shared data between steps
    private TestContext testContext;
    
    // Constructor to initialize TestContext
    public LoginWithRegisteredUserSteps(TestContext testContext)
    {
    	this.testContext=testContext;
    }
    
    @Then("the user logs in with the registered credentials")
    public void userLogin() {

        // Navigate to the application URL
        String applicationUrl = BaseClass.getProperties().getProperty("appURL");
        driver.get(applicationUrl);

        // Initialize the HomePage object and navigate to 'My Account'
        homePage = new HomePage(driver);
        homePage.clickMyAccount();

        // Navigate to the Login Page
        loginPage = homePage.clickLogin();

        // Enter login credentials and submit the login form
        loginPage.setEmail(testContext.getEmail());
        loginPage.setPassword(testContext.getPassword());
        loginPage.clickLogin();

        // Initialize the MyAccountPage object and verify successful login
        myAccountPage = new MyAccountPage(driver);
        Assert.assertTrue("Login failed: My Account page not displayed", myAccountPage.isMyAccountPageExists());
    }
}
