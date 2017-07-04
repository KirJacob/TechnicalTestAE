package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.Common;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class BasePage extends Common {

    public SelenideElement userMenuBtn() {
        return $(xpath("//*[contains(@id,'user-dropdown-toggle')]"));
    }

    public SelenideElement logOutLnk() {
        return $(xpath("//*[contains(@id,'signout-button')]/button"));
    }

    public void clickUserMenuBtn() {
        click(userMenuBtn());
    }

    public void clickLogOutLnk() {
        click(logOutLnk());
    }

}
