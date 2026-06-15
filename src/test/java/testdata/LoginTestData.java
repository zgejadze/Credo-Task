package testdata;

import enums.Language;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

public class LoginTestData {

    private static final String SPECIAL_CHARS = "!@#$%^&*()_+{}|:<>?";
    private static final String TOO_LONG = RandomStringUtils.randomAlphabetic(256);

    @DataProvider(name = "languages")
    public Object[][] languages() {
        return new Object[][] {
                {Language.GEORGIAN,     "სავალდებულო ველი", "მონაცემები არასწორია"},
                {Language.ENGLISH,      "Required field", "Please make sure the entered details are correct"},
                {Language.RUSSIAN,      "Обязательное поле", "Пожалуйста, убедитесь, что введенные данные верны."}
        };
    }
}