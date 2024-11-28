package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

	WebDriver driver;
	WebDriverWait wait;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set explicit wait duration
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;

	@FindBy(linkText = "Login")
	WebElement linkLogin;

	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement txtSearchbox;

	@FindBy(xpath = "//div[@id='search']//button[@type='button']")
	WebElement btnSearch;

	// Is HomePage exists?
	public boolean isHomePageExists() {
		try {
			return driver.getTitle().equals("Your Store");

		} catch (Exception e) {
			return false;
		}
	}

	// Click "My Account" link
	public void clickMyAccount() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(lnkMyaccount)).click();
		} catch (Exception e) {
			System.out.println("Exception occurred while clicking 'My Account': " + e.getMessage());
		}
	}

	// Click "Register" link
	public RegistrationPage clickRegister() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(lnkRegister)).click();
			return new RegistrationPage(driver);
		} catch (Exception e) {
			System.out.println("Exception occurred while clicking 'Register': " + e.getMessage());
			return null;
		}
	}

	// Click "Login" link
	public LoginPage clickLogin() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(linkLogin)).click();
			return new LoginPage(driver);
		} catch (Exception e) {
			System.out.println("Exception occurred while clicking 'Login': " + e.getMessage());
			return null;
		}
	}

	// Enter product name in the search box
	public void enterProductName(String pName) {
		try {
			wait.until(ExpectedConditions.visibilityOf(txtSearchbox)).sendKeys(pName);
		} catch (Exception e) {
			System.out.println("Exception occurred while entering product name: " + e.getMessage());
		}
	}

	// Click the search button
	public SearchResultsPage clickSearch() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
			return new SearchResultsPage(driver);
		} catch (Exception e) {
			System.out.println("Exception occurred while clicking 'Search': " + e.getMessage());
			return null;
		}
	}
}
