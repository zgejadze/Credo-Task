package core.base;

import core.config.ConfigReader;
import core.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.get("APP_URL"));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}