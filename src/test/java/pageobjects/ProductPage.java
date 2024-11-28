package pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

	WebDriver driver;
	WebDriverWait wait;

	// Constructor
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait duration
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "quantity") // Quantity input box
	WebElement txtQuantity;

	@FindBy(xpath = "//button[@id='button-cart']") // Add to cart button
	WebElement btnAddToCart;

	@FindBy(xpath = "//div[contains(text(),'Success: You have added')]") // Confirmation message
	WebElement cnfMsg;
	
    @FindBy(xpath = "//div[@id='cart']") // Items button
    WebElement btnItems;
    

    @FindBy(xpath = "//strong[normalize-space()='View Cart']") // View Cart link
    WebElement lnkViewCart;
    

	// Set the quantity for the product
	public void setQuantity(String qty) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtQuantity)); // Wait for quantity field to be visible
			txtQuantity.clear();
			txtQuantity.sendKeys(qty);
		} catch (Exception e) {
			System.out.println("Error setting quantity: " + e.getMessage());
		}
	}

	// Add the product to the cart
	public void addToCart() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnAddToCart)); // Wait for add-to-cart button to be
																				// clickable
			btnAddToCart.click();
		} catch (Exception e) {
			System.out.println("Error adding product to cart: " + e.getMessage());
		}
	}

	// Check if the confirmation message is displayed
	public boolean checkConfMsg() {
		try {
			wait.until(ExpectedConditions.visibilityOf(cnfMsg)); // Wait for confirmation message to be visible
			return cnfMsg.isDisplayed();
		} catch (Exception e) {
			System.out.println("Confirmation message not found: " + e.getMessage());
			return false;
		}
	}
	
	  // Method to click on Items button to navigate to the cart
    public void clickItemsToNavigateToCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnItems)); // Wait for the Items button to be clickable
            btnItems.click();
        } catch (Exception e) {
            System.out.println("Unable to click Items button: " + e.getMessage());
        }
    }
    
    // Method to click on View Cart link
    public ShoppingCartPage clickViewCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(lnkViewCart)); // Wait for the View Cart link to be clickable
            lnkViewCart.click();
            return new ShoppingCartPage(driver);
        } catch (Exception e) {
            System.out.println("Unable to click View Cart link: " + e.getMessage());
            return null;
        }
    }
    

}
