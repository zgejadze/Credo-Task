package ui.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import enums.Language;

import java.time.Duration;

public class LanguageSelector {

    private final WebDriverWait wait;

    public LanguageSelector(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public By getLanguageSelectorIcon() {
        return By.xpath("//app-icon[@svgicon='language']/ancestor::button");
    }

    public By getLanguageOption(Language language) {
        return By.xpath(String.format(
                "//li[.//p[normalize-space()='%s']]",
                language.getDisplayName()
        ));
    }

    @Step("Select language: {language}")
    public void selectLanguage(Language language) {
        wait.until(ExpectedConditions.elementToBeClickable(getLanguageSelectorIcon()))
                .click();

        By option = getLanguageOption(language);

        wait.until(ExpectedConditions.elementToBeClickable(option))
                .click();
    }
}