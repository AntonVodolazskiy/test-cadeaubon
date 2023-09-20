package cadeaubon;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CadeaubonBaseTests {
    public static WebDriver driver;
    protected WebDriverWait wait;
    public static String baseUrl;

    @Before
    public void setUp() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-fullscreen");
            options.addArguments("--disable-popup-blocking");
           // options.addArguments("--headless");
            Duration implicitWaitTimeout = Duration.ofSeconds(3);
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, implicitWaitTimeout);
            baseUrl = "https://www.cadeaubon.nl/";
        } catch (Exception e) {
            System.err.println("Failed to initialize WebDriver: " + e.getMessage());
            throw e;
        }
    }
    @After
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Failed to close WebDriver: " + e.getMessage());
        }
    }
}
