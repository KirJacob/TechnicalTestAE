package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;

public class TweetPage extends BasePage{

    public SelenideElement tweetWithTxt(String text){
        return $(xpath("//*[contains(@class,'js-tweet-text')]//*[contains(text(),'" + text + "')]"));
    }

    public ElementsCollection tweetsWithTxt(String text){
        return $$(xpath("//*[contains(@class,'js-tweet-text')]//*[contains(text(),'" + text + "')]"));
    }

}
