package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import base.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.RegistrationPage;

public class RegistrationSteps {
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private final WebDriver driver = BaseClass.getDriver();
    private final Logger logger = BaseClass.getLogger();

    @When("I navigate to My Account and click Register")
    public void navigateToMyAccountAndClickRegister() {
        homePage = new HomePage(driver);
        homePage.clickMyAccount();
        registrationPage = homePage.clickRegister();
        logger.info("Navigated to Registration page.");
    }

    @When("I fill in the registration details")
    public void fillInRegistrationDetails() {
        registrationPage.setFirstName(BaseClass.generateString().toUpperCase());
        registrationPage.setLastName(BaseClass.generateString().toUpperCase());
        registrationPage.setEmail(BaseClass.generateString() + "@gmail.com");
        registrationPage.setTelephone(BaseClass.generateNumber());
        String userPassword = BaseClass.generateAlphaNumeric();
        registrationPage.setPassword(userPassword);
        registrationPage.setConfirmPassword(userPassword);
        logger.info("Registration details entered.");
    }

    @When("I agree to the Privacy Policy and submit the registration form")
    public void agreeToPrivacyPolicyAndSubmitForm() {
        registrationPage.setPrivacyPolicy();
        registrationPage.clickContinue();
        logger.info("Agreed to Privacy Policy and submitted registration.");
    }

    @Then("I should see the confirmation message {string}")
    public void shouldSeeConfirmationMessage(String expectedMessage) {
        Assert.assertEquals("Registration failed: Confirmation message mismatch.",
                            expectedMessage, registrationPage.getConfirmationMsg());
        logger.info("Confirmation message validated.");
    }
}
