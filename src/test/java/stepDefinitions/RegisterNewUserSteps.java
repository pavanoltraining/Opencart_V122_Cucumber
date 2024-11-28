package stepDefinitions;


import org.openqa.selenium.WebDriver;

import base.BaseClass;
import hooks.TestContext;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.LogoutPage;
import pageobjects.MyAccountPage;
import pageobjects.RegistrationPage;

public class RegisterNewUserSteps {

	  // Page object references
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private MyAccountPage myAccountPage;
    private LogoutPage logoutPage;

    // WebDriver instance
    private final WebDriver driver = BaseClass.getDriver();

    
    // TestContext to manage shared data between steps
    private TestContext testContext;
    
    // Constructor to initialize TestContext
    public RegisterNewUserSteps(TestContext testContext)
    {
    	this.testContext=testContext;
    }
    
    @When("the user registers an account")
    public void accountRegistration() {

        // Navigate to the application URL
        String applicationUrl = BaseClass.getProperties().getProperty("appURL");
        driver.get(applicationUrl);

        // Initialize the HomePage object and navigate to 'My Account'
        homePage = new HomePage(driver);
        homePage.clickMyAccount();

        // Navigate to the Registration Page
        registrationPage = homePage.clickRegister();

        // Generate random user details for registration
        registrationPage.setFirstName(BaseClass.generateString().toUpperCase());
        registrationPage.setLastName(BaseClass.generateString().toUpperCase());
        
        String email = BaseClass.generateString() + "@gmail.com";
        String password = BaseClass.generateAlphaNumeric();
        
        // Set user details in the registration form
        registrationPage.setEmail(email);
        registrationPage.setTelephone(BaseClass.generateNumber());
        registrationPage.setPassword(password);
        registrationPage.setConfirmPassword(password);

        // Agree to the Privacy Policy and submit the registration form
        registrationPage.setPrivacyPolicy();
        registrationPage.clickContinue();

        // Store the email and password in TestContext for further use
        testContext.setEmail(email);
        testContext.setPassword(password);
        
        // Print the registered email for reference
        System.out.println("Account registered with email: " + email);
        
        // Navigate to the My Account Page and log out
        myAccountPage = new MyAccountPage(driver);
        logoutPage = myAccountPage.clickLogout();
    }
}
