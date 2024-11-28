package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import base.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ProductPage;
import pageobjects.SearchResultsPage;

public class CartSteps {
    private SearchResultsPage searchResultsPage;
    private ProductPage productPage;
    private final WebDriver driver = BaseClass.getDriver();
    private final Logger logger = BaseClass.getLogger();

    @When("I select the product {string} from the search results")
    public void selectProductFromSearchResults(String productName) {
    	searchResultsPage=new SearchResultsPage(driver);
        productPage = searchResultsPage.selectProduct(productName);
        logger.info("Selected product: {}", productName);
    }

    @When("I set the quantity to {string}")
    public void setQuantityTo(String quantity) {
        productPage.setQuantity(quantity);
        logger.info("Set quantity to: {}", quantity);
    }

    @When("I add the product to the cart")
    public void addProductToCart() {
        productPage.addToCart();
        logger.info("Product added to cart.");
    }

    @Then("I should see a success message confirming the product was added to the cart")
    public void shouldSeeSuccessMessageForAddToCart() {
        Assert.assertTrue("Success message not displayed.", productPage.checkConfMsg());
        logger.info("Success message verified for product added to cart.");
    }
}
