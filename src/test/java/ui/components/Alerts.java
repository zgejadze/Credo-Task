package ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class Alerts {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By alertMessage = By.xpath("//div[@role='alert']//p");

    public Alerts(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isAlertVisible() {
        try {
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(alertMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getAlertText() {
        return wait.until(ExpectedConditions
                        .visibilityOfElementLocated(alertMessage))
                .getText();
    }
}