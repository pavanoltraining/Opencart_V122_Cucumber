package pageobjects;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {

	WebDriver driver;
	WebDriverWait wait;

	// Constructor
	public LogoutPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait duration
		PageFactory.initElements(driver, this);
	}

	// WebElements using @FindBy
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement btnContinue;

	public HomePage clickContinue() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnContinue)); // Wait for the login button to be
																				// clickable
			btnContinue.click();
			return new HomePage(driver);
		} catch (TimeoutException e) {
			System.out.println("Continue button not clickable: " + e.getMessage());
			return null;
		}
	}

}
