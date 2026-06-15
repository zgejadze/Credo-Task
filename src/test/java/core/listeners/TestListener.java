package core.listeners;

import core.driver.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot("Failure Screenshot");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenshot("Skipped Screenshot");
    }

    private void takeScreenshot(String name) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(name, "image/png",
                        new ByteArrayInputStream(screenshot), ".png");
            } catch (Exception e) {
                log.error("Failed to take screenshot", e);
            }
        }
    }
}