package ui.pages;

import core.config.ConfigReader;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.components.FormHelper;
import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final FormHelper form;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.form = new FormHelper(driver);
    }


    @Step("Fill username: {username}")
    public void fillUsername(String username) {
        form.fillText("username", username);
    }

    @Step("Fill username and then delete it")
    public void fillThenClearUsername() {
        form.fillText("username", RandomStringUtils.randomAlphabetic(8));
        form.clearTextField("username");
    }

    @Step("Fill password")
    public void fillPassword(String password) {
        form.fillText("password", password);
    }

    @Step("Fill password and then delete it")
    public void fillThenClearPassword() {
        form.fillText("password", RandomStringUtils.randomAlphabetic(8));
        form.clearTextField("password");

    }

    @Step("Submit login form")
    public void submitLogin() {
        form.clickButtonByLabel("sign in");
    }

    @Step("Fill login form with username and password")
    public void fillLoginForm(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        submitLogin();
    }

    @Step("Get username validation message")
    public String getUsernameValidationMessage() {
        return form.getFieldError("username");
    }

    @Step("Get password validation message")
    public String getPasswordValidationMessage() {
        return form.getFieldError("password");
    }

    @Step("Check if still on login page")
    public boolean isStillOnLoginPage() {
        return driver.getCurrentUrl().contains(ConfigReader.get("APP_URL"));
    }
}