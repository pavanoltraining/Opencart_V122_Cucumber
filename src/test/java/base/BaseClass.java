package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utilities.ConfigReader;

public class BaseClass {

    // Thread-safe WebDriver instance
    private static final ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    // Logger for logging messages (Log4j)
    public static Logger logger = LogManager.getLogger(BaseClass.class);

    // To store configuration properties
    private static Properties properties;

    // LambdaTest credentials
    private static final String LT_USERNAME = "pavan.teens";
    private static final String LT_ACCESS_KEY = "k1fACqdr0AbbIjITdlORsUEvpRaPDzlkVKWbFFujX5FBanPT1E";

    // Initializes the WebDriver based on the execution environment and browser
    public static void initialSetup() throws MalformedURLException {
        properties = getProperties(); // Load configuration properties
      
        String executionEnv = properties.getProperty("execution_env"); // Environment (local, remote, etc.)
        String browser = properties.getProperty("browser"); // Browser name
        String os = properties.getProperty("os"); // Operating system

        WebDriver driver;

        // Determine which WebDriver to initialize based on the execution environment
        switch (executionEnv.toLowerCase()) {
            case "lambdatest":
                driver = initializeLambdaTestDriver(os, browser); // LambdaTest setup
                break;
            case "remote":
                driver = initializeRemoteDriver(os, browser); // Selenium Grid setup
                break;
            default:
                driver = initializeLocalDriver(browser); // Local WebDriver setup
              }

        // Set WebDriver and apply common configurations
        if (driver != null) {
            tdriver.set(driver);
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
            driver.manage().window().maximize(); // Maximize browser window
            logger.info("WebDriver initialized successfully for browser: " + browser);
        } else {
            logger.error("Failed to initialize WebDriver for browser: " + browser);
        }
    }

    // Returns the current thread's WebDriver instance
    public static WebDriver getDriver() {
        return tdriver.get();
    }

    // Quits the WebDriver and removes it from the thread-local storage
    public static void quitDriver() {
        if (tdriver.get() != null) {
            tdriver.get().quit();
            tdriver.remove();
        }
    }

    // Initialize WebDriver for LambdaTest
    private static WebDriver initializeLambdaTestDriver(String os, String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser); // Set browser
        capabilities.setCapability("platformName", os); // Set platform (OS)

        // LambdaTest-specific options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", LT_USERNAME);
        ltOptions.put("accessKey", LT_ACCESS_KEY);
        ltOptions.put("visual", true); // Enable visual logs
        ltOptions.put("video", true); // Enable video recording
        ltOptions.put("build", "OpencartLambdaTestBuild"); // Build name
        ltOptions.put("project", "OpencartLambdaTestProject"); // Project name
        capabilities.setCapability("LT:Options", ltOptions);

        try {
            return new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), capabilities);
        } catch (Exception e) {
            logger.error("Error initializing LambdaTest driver: " + e.getMessage());
            return null;
        }
    }

    // Initialize WebDriver for Selenium Grid
    private static WebDriver initializeRemoteDriver(String os, String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        configureOSCapabilities(os, capabilities); // Configure OS-specific capabilities
        configureBrowserCapabilities(browser, capabilities); // Configure browser-specific capabilities
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    // Initialize WebDriver for local execution
    private static WebDriver initializeLocalDriver(String browser) {
        switch (browser.toLowerCase()) {
        case "chrome": {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "disable-infobars", "--ignore-certificate-errors");
            chromeOptions.setAcceptInsecureCerts(true);
            return new ChromeDriver(chromeOptions);
        }
        case "edge": {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "disable-infobars", "--ignore-certificate-errors");
            edgeOptions.setAcceptInsecureCerts(true);
            return new EdgeDriver(edgeOptions);
        }
        case "firefox": {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--disable-notifications", "--disable-popup-blocking",
                    "--disable-extensions", "disable-infobars", "--ignore-certificate-errors");
            firefoxOptions.setAcceptInsecureCerts(true);
            firefoxOptions.addPreference("dom.webnotifications.enabled", false);
            firefoxOptions.addPreference("dom.disable_open_during_load", true);
            firefoxOptions.addPreference("extensions.showRecommendedInstalled", false);
            return new FirefoxDriver(firefoxOptions);
        }
        default: {
            logger.error("No matching browser for local execution: " + browser);
            return null;
        }
        }
    }

    // Configure OS-specific capabilities for Selenium Grid
    private static void configureOSCapabilities(String os, DesiredCapabilities capabilities) {
        switch (os.toLowerCase()) {
            case "windows":
                capabilities.setPlatform(Platform.WINDOWS);
                break;
            case "linux":
                capabilities.setPlatform(Platform.LINUX);
                break;
            case "mac":
                capabilities.setPlatform(Platform.MAC);
                break;
            default:
                logger.error("Unsupported OS: " + os);
        }
    }

    // Configure browser-specific capabilities for Selenium Grid
    private static void configureBrowserCapabilities(String browser, DesiredCapabilities capabilities) {
        capabilities.setBrowserName(browser.toLowerCase());
    }

    // Load configuration properties using the ConfigReader utility
    public static Properties getProperties() {
        ConfigReader configreader = new ConfigReader();
        return configreader.getProperties();
    }

    // Get the Logger instance
    public static Logger getLogger() {
        logger = LogManager.getLogger(); // Log4j setup
        return logger;
    }

    // Generate a random alphabetic string
    public static String generateString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    // Generate a random numeric string
    public static String generateNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    // Generate a random alphanumeric string
    public static String generateAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
