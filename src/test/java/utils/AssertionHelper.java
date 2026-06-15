package utils;

import enums.Language;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;
import ui.components.Alerts;
import ui.pages.LoginPage;

public class AssertionHelper {

    private final SoftAssert soft;
    private final LoginPage loginPage;
    private final Alerts alerts;

    public AssertionHelper(SoftAssert soft, LoginPage loginPage, Alerts alerts) {
        this.soft = soft;
        this.loginPage = loginPage;
        this.alerts = alerts;
    }

    @Step("Verify user remains on login page")
    public void assertStaysOnLoginPage(Language language) {
        soft.assertTrue(loginPage.isStillOnLoginPage(),
                "[" + language + "] User should remain on login page");
    }

    @Step("Verify error alert is visible with correct text")
    public void assertAlert(Language language, String expectedAlertText) {
        soft.assertTrue(alerts.isAlertVisible(),
                "[" + language + "] Alert should be visible");
        soft.assertEquals(alerts.getAlertText(), expectedAlertText,
                "[" + language + "] Alert text mismatch");
    }

    @Step("Verify username field validation message")
    public void assertUsernameValidation(Language language, String expected) {
        soft.assertEquals(loginPage.getUsernameValidationMessage(), expected,
                "[" + language + "] Username validation message mismatch");
    }

    @Step("Verify password field validation message")
    public void assertPasswordValidation(Language language, String expected) {
        soft.assertEquals(loginPage.getPasswordValidationMessage(), expected,
                "[" + language + "] Password validation message mismatch");
    }
}