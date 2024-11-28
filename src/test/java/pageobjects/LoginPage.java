package pageobjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait duration
        PageFactory.initElements(driver, this);
    }

    // WebElements using @FindBy
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmailAddress;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;

    // Methods to interact with elements using explicit waits
    public void setEmail(String email) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtEmailAddress)); // Wait for email field to be visible
            txtEmailAddress.clear(); // Clear the field before entering text
            txtEmailAddress.sendKeys(email);
        } catch (TimeoutException e) {
            System.out.println("Email field not visible: " + e.getMessage());
        }
    }

    public void setPassword(String pwd) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtPassword)); // Wait for password field to be visible
            txtPassword.clear(); // Clear the field before entering text
            txtPassword.sendKeys(pwd);
        } catch (TimeoutException e) {
            System.out.println("Password field not visible: " + e.getMessage());
        }
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnLogin)); // Wait for the login button to be clickable
            btnLogin.click();
        } catch (TimeoutException e) {
            System.out.println("Login button not clickable: " + e.getMessage());
        }
    }
}
