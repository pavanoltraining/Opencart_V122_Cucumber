package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set explicit wait duration
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstname;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    public void setFirstName(String fname) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtFirstname)).sendKeys(fname);
        } catch (Exception e) {
            System.out.println("Exception while setting first name: " + e.getMessage());
        }
    }

    public void setLastName(String lname) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtLastname)).sendKeys(lname);
        } catch (Exception e) {
            System.out.println("Exception while setting last name: " + e.getMessage());
        }
    }

    public void setEmail(String email) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtEmail)).sendKeys(email);
        } catch (Exception e) {
            System.out.println("Exception while setting email: " + e.getMessage());
        }
    }

    public void setTelephone(String tel) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtTelephone)).sendKeys(tel);
        } catch (Exception e) {
            System.out.println("Exception while setting telephone: " + e.getMessage());
        }
    }

    public void setPassword(String pwd) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtPassword)).sendKeys(pwd);
        } catch (Exception e) {
            System.out.println("Exception while setting password: " + e.getMessage());
        }
    }

    public void setConfirmPassword(String pwd) {
        try {
            wait.until(ExpectedConditions.visibilityOf(txtConfirmPassword)).sendKeys(pwd);
        } catch (Exception e) {
            System.out.println("Exception while setting confirm password: " + e.getMessage());
        }
    }

    public void setPrivacyPolicy() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(chkdPolicy)).click();
        } catch (Exception e) {
            System.out.println("Exception while setting privacy policy: " + e.getMessage());
        }
    }

    public void clickContinue() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
            // Or use JavaScriptExecutorUtils.clickElement(btnContinue); if necessary
        } catch (Exception e) {
            System.out.println("Exception while clicking Continue: " + e.getMessage());
        }
    }

    public String getConfirmationMsg() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(msgConfirmation)).getText();
        } catch (Exception e) {
            System.out.println("Exception while getting confirmation message: " + e.getMessage());
            return e.getMessage();
        }
    }
}
