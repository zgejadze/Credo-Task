package tests;

import core.base.BaseTest;
import enums.Language;
import io.qameta.allure.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.components.LanguageSelector;
import ui.components.Alerts;
import ui.pages.LoginPage;
import testdata.LoginTestData;
import utils.AssertionHelper;

@Feature("Authorization")
public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private SoftAssert soft;
    private AssertionHelper assertion;

    @BeforeMethod
    public void setUpPage(Object[] params) {
        loginPage = new LoginPage(driver);
        LanguageSelector languageSelector = new LanguageSelector(driver);
        Alerts alerts = new Alerts(driver);
        soft = new SoftAssert();
        assertion = new AssertionHelper(soft, loginPage, alerts);
        if (params.length > 0) {
            languageSelector.selectLanguage((Language) params[0]);
        }
    }

    @Test(dataProvider = "languages", dataProviderClass = LoginTestData.class)
    @Story("Field validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that submitting empty username and password shows validation messages on both fields")
    public void emptyFieldsTest(Language language, String expectedValidationText, String _alertText) {
        loginPage.fillLoginForm("", "");

        assertion.assertUsernameValidation(language, expectedValidationText);
        assertion.assertPasswordValidation(language, expectedValidationText);
        assertion.assertStaysOnLoginPage(language);
        soft.assertAll();
    }

    @Test(dataProvider = "languages", dataProviderClass = LoginTestData.class)
    @Story("Field validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that submitting empty username with valid password shows username validation message")
    public void emptyUsernameTest(Language language, String expectedValidationText, String _alertText) {
        loginPage.fillLoginForm("", RandomStringUtils.randomAlphabetic(8));

        assertion.assertUsernameValidation(language, expectedValidationText);
        assertion.assertStaysOnLoginPage(language);
        soft.assertAll();
    }

    @Test(dataProvider = "languages", dataProviderClass = LoginTestData.class)
    @Story("Field validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that submitting empty password with valid username shows password validation message")
    public void emptyPasswordTest(Language language, String expectedValidationText, String _alertText) {
        loginPage.fillLoginForm(RandomStringUtils.randomAlphabetic(8), "");

        assertion.assertPasswordValidation(language, expectedValidationText);
        assertion.assertStaysOnLoginPage(language);
        soft.assertAll();
    }

    @Test(dataProvider = "languages", dataProviderClass = LoginTestData.class)
    @Story("Invalid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that wrong credentials show the server error alert and user stays on login page")
    public void wrongCredentialsTest(Language language, String _expectedValidationText, String alertText) {
        loginPage.fillLoginForm(
                RandomStringUtils.randomAlphabetic(8),
                RandomStringUtils.randomAlphabetic(8)
        );

        assertion.assertAlert(language, alertText);
        assertion.assertStaysOnLoginPage(language);
        soft.assertAll();
    }

    @Test(dataProvider = "languages", dataProviderClass = LoginTestData.class)
    @Story("Field validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that filling then clearing username triggers validation message")
    public void fillThenClearUsernameTest(Language language, String expectedValidationText, String _alertText) {
        loginPage.fillThenClearUsername();

        assertion.assertUsernameValidation(language, expectedValidationText);
        soft.assertAll();
    }

    @Test(dataProvider = "languages", dataProviderClass = LoginTestData.class)
    @Story("Field validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that filling then clearing password triggers validation message")
    public void fillThenClearPasswordTest(Language language, String expectedValidationText, String _alertText) {
        loginPage.fillThenClearPassword();
        assertion.assertPasswordValidation(language, expectedValidationText);

        soft.assertAll();
    }

    @Test(dataProvider = "languages", dataProviderClass = LoginTestData.class)
    @Story("Field validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that whitespace-only credentials are treated as empty and trigger field validation — expected to fail as a known bug")
    public void whitespaceCredentialsTest(Language language, String expectedValidationText, String alertText) {
        loginPage.fillUsername("   ");
        assertion.assertUsernameValidation(language, expectedValidationText);

        loginPage.fillPassword("   ");
        assertion.assertPasswordValidation(language, expectedValidationText);

        loginPage.submitLogin();
        assertion.assertStaysOnLoginPage(language);

        soft.assertAll();
    }
}