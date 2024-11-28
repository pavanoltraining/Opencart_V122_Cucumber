package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage {

	WebDriver driver;
	WebDriverWait wait;

	// Constructor
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait duration
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='content']/div[2]/div/table//strong[text()='Total:']//following::td") // Total price label
	WebElement lblTotalPrice;

	@FindBy(xpath = "//a[text()='Checkout']") // Checkout button
	WebElement btnCheckout;

	// Method to get the total price from the shopping cart
	public String getTotalPrice() {
		try {
			wait.until(ExpectedConditions.visibilityOf(lblTotalPrice)); // Wait for the Total Price label to be visible
			return lblTotalPrice.getText();
		} catch (Exception e) {
			System.out.println("Unable to retrieve total price: " + e.getMessage());
			return null;
		}
	}

	// Method to click on the Checkout button
	public CheckoutPage clickOnCheckout() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnCheckout)); // Wait for the Checkout button to be
																				// clickable
			btnCheckout.click();
			return new CheckoutPage(driver);
		} catch (Exception e) {
			System.out.println("Unable to click Checkout button: " + e.getMessage());
			return null;
		}
	}
}
