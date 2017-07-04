package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class HomePage extends BasePage{

    public SelenideElement tweetTextFld(String index){
        return $(xpath("(//*[contains(@class,'js-tweet-text-container')])[" + index + "]"));
    }
}
