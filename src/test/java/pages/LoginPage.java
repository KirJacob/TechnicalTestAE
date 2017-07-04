package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class LoginPage extends BasePage {

    public SelenideElement emailFld(){
        return $(xpath("//*[contains(@id,'page-outer')]//*[contains(@class,'js-username-field')]"));
    }

    public SelenideElement passwordFld(){
        return $(xpath("//*[contains(@id,'page-outer')]//*[contains(@class,'js-password-field')]"));
    }

    public SelenideElement signInFBtn() {
        return $(xpath("//*[contains(@id,'page-outer')]//*[contains(@class,'submit')]"));
    }

    public void login(String login, String password) {
        setFieldVal(emailFld(), login);
        setFieldVal(passwordFld(), password);
        click(signInFBtn());
    }
}
