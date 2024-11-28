package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import base.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.SearchResultsPage;

public class SearchSteps {
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private final WebDriver driver = BaseClass.getDriver();
    private final Logger logger = BaseClass.getLogger();

    @When("I enter a product name {string} in the search field")
    public void enterProductNameInSearchField(String productName) {
        homePage = new HomePage(driver);
        homePage.enterProductName(productName);
        logger.info("Entered product name: {}", productName);
    }

    @When("I click on the search button")
    public void clickOnSearchButton() {
        searchResultsPage = homePage.clickSearch();
        logger.info("Clicked on 'Search' button.");
    }

    @Then("I should see the search results page")
    public void shouldSeeSearchResultsPage() {
        Assert.assertTrue("Search results page is not displayed.", searchResultsPage.isSearchResultsPageExists());
        logger.info("Search results page is displayed.");
    }

    @Then("I should see the product {string} in the search results")
    public void shouldSeeProductInSearchResults(String productName) {
        Assert.assertTrue("Product not found: " + productName, searchResultsPage.isProductExist(productName));
        logger.info("Product '{}' found in search results.", productName);
    }
}
