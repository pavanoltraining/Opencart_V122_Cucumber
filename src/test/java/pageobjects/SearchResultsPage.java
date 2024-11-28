package pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize the driver and WebDriverWait
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait duration
        PageFactory.initElements(driver, this);
    }

    // WebElement for the search results page header
    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement searchPageHeader;

    // WebElements for the product images in the search results
    @FindBy(xpath = "//*[@id='content']/div[3]//img")
    private List<WebElement> searchProducts;

    /**
     * Verify if the search results page exists by checking the header text
     * 
     * @return true if the search results page exists, false otherwise
     */
    public boolean isSearchResultsPageExists() {
        try {
            return searchPageHeader.getText().contains("Search -");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if a product exists in the search results by its name
     * 
     * @param productName The name of the product to search for
     * @return true if the product exists, false otherwise
     */
    public boolean isProductExist(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(searchProducts)); // Wait for product list to be visible
            // Loop through products to find a match by name
            for (WebElement product : searchProducts) {
                if (product.getAttribute("title").equals(productName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error checking product existence: " + e.getMessage());
        }
        return false;
    }

    /**
     * Select a product from the search results by its name
     * 
     * @param productName The name of the product to select
     * @return a ProductPage instance after selecting the product
     */
    public ProductPage selectProduct(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(searchProducts)); // Wait for product list to be visible
            // Loop through products to find the matching product and click it
            for (WebElement product : searchProducts) {
                if (product.getAttribute("title").equals(productName)) {
                    wait.until(ExpectedConditions.elementToBeClickable(product)); // Wait for the product to be clickable
                    product.click();
                    return new ProductPage(driver);
                }
            }
            System.out.println("Product not found: " + productName);
        } catch (Exception e) {
            System.out.println("Error selecting product: " + e.getMessage());
        }
        return null;
    }
}
