package ui.components;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FormHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FormHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillText(String id, String text) {
        By locator = By.id(id);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        element.clear();
        element.sendKeys(text);
        blur();
    }
    public void clearTextField(String id) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);;
        blur();
    }

    public void clickButtonByLabel(String ariaLabel) {
        By locator = By.xpath("//button[@aria-label='%s']".formatted(ariaLabel));

        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void blur() {
        ((JavascriptExecutor) driver).executeScript("document.activeElement.blur()");
    }

    public String getFieldError(String fieldId) {
        By locator = By.xpath(
                "//input[@id='%s']/ancestor::form-field//crd-error".formatted(fieldId)
        );

        try {
            return new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator))
                    .getText();
        } catch (TimeoutException e) {
            return "";
        }
    }


}