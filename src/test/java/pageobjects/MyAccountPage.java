package pageobjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait duration
        PageFactory.initElements(driver, this);
    }

    // WebElements using @FindBy
    @FindBy(xpath = "//h2[text()='My Account']") // MyAccount Page heading
    WebElement msgHeading;

    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']") // Logout link
    WebElement lnkLogout;

    // Method to verify if "My Account" page heading is displayed
    public boolean isMyAccountPageExists() {
        try {
            wait.until(ExpectedConditions.visibilityOf(msgHeading)); // Wait for heading to be visible
            return msgHeading.isDisplayed();
        } catch (Exception e) {
            System.out.println("My Account page heading not found: " + e.getMessage());
            return false;
        }
    }

    // Method to click on Logout link
    public LogoutPage clickLogout() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(lnkLogout)); // Wait for the logout link to be clickable
            lnkLogout.click();
            return new LogoutPage(driver);
        } catch (Exception e) {
            System.out.println("Unable to click Logout link: " + e.getMessage());
            return null;
        }
    }
}
