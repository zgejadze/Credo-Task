package testdata;

import enums.Language;
import org.testng.annotations.DataProvider;

public class LoginTestData {

    @DataProvider(name = "languages")
    public Object[][] languages() {
        return new Object[][] {
                {Language.GEORGIAN,     "სავალდებულო ველი", "მონაცემები არასწორია"},
                {Language.ENGLISH,      "Required field", "Please make sure the entered details are correct"},
                {Language.RUSSIAN,      "Обязательное поле", "Пожалуйста, убедитесь, что введенные данные верны."}
        };
    }
}