package core.driver;

import core.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver(ConfigReader.get("BROWSER")));
        }
        return driver.get();
    }

    private static WebDriver createDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=" +
                        ConfigReader.get("SCREEN_WIDTH") + "," +
                        ConfigReader.get("SCREEN_HEIGHT"));
                ChromeDriver chromeDriver = new ChromeDriver(options);
                chromeDriver.manage().timeouts().pageLoadTimeout(
                        Duration.ofSeconds(Integer.parseInt(ConfigReader.get("PAGE_LOAD_TIMEOUT"))));
                yield chromeDriver;
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--width=" + ConfigReader.get("SCREEN_WIDTH"));
                options.addArguments("--height=" + ConfigReader.get("SCREEN_HEIGHT"));
                FirefoxDriver firefoxDriver = new FirefoxDriver(options);
                firefoxDriver.manage().timeouts().pageLoadTimeout(
                        Duration.ofSeconds(Integer.parseInt(ConfigReader.get("PAGE_LOAD_TIMEOUT"))));
                yield firefoxDriver;
            }
            default -> throw new RuntimeException("Browser not supported: " + browser);
        };
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}