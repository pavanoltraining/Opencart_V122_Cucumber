package stepDefinitions;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import base.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import utilities.DataReader;

public class DataDrivenSteps {

	private LoginPage loginPage;
	private MyAccountPage myAccountPage;
	private List<HashMap<String, String>> datamap;
	private final WebDriver driver = BaseClass.getDriver();
	private final Logger logger = BaseClass.getLogger();

	@When("I enter login credentials with email {string} and password {string}")
	public void enterValidCredentialsWithParameters(String email, String password) {
		loginPage = new LoginPage(driver);
		loginPage.setEmail(email);
		logger.info("Entered email: " + email);
		loginPage.setPassword(password);
		logger.info("Entered password.");
		loginPage.clickLogin();
		logger.info("Clicked on the 'Login' button.");
	}
	
	 @Then("I enter different login credentials with {string}")
	    public void enterCredentialsToCheckLogin(String row) {
	        try {
	            datamap = DataReader.data(System.getProperty("user.dir") + "\\testdata\\Data.xlsx", "Sheet1");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        int index = Integer.parseInt(row) - 1;
	        String email = datamap.get(index).get("Email");
	        String password = datamap.get(index).get("Password");

	        loginPage=new LoginPage(driver);
	        loginPage.setEmail(email);
	        loginPage.setPassword(password);
	        loginPage.clickLogin();
	        logger.info("Login credentials entered from Excel data.");
	    }

	    @Then("I should be redirected to the My Account page if data is valid in {string}")
	    public void validateLogin(String row) {
	        myAccountPage = new MyAccountPage(driver);
	        boolean isMyAccountPageExists = myAccountPage.isMyAccountPageExists();

	        int index = Integer.parseInt(row) - 1;
	        String expectedResult = datamap.get(index).get("Result");
	        
	        if (expectedResult.equalsIgnoreCase("valid")) {
	            Assert.assertTrue("Expected successful login, but it failed.", isMyAccountPageExists);
	            logger.info("Login successful with valid credentials.");
	        } else if (expectedResult.equalsIgnoreCase("invalid")) {
	            Assert.assertFalse("Expected login failure, but it succeeded.", isMyAccountPageExists);
	            logger.info("Login failed with invalid credentials.");
	        }
	    }
	
	

}
