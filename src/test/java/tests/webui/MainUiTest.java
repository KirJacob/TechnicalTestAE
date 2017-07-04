package tests.webui;

import helpers.CommonWD;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.TweetPage;

import java.io.IOException;
import java.net.MalformedURLException;

import static api.Requests.*;
import static api.Requests.updateStatus;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MainUiTest extends BaseUiTest {

    private TweetPage tweetPage;

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browser) throws MalformedURLException {
        setDriver(browser);
        LoginPage loginPage = openPage(mainURL + "/login", LoginPage.class);
        loginPage.login(login, password);
        shouldBeVisible(loginPage.userMenuBtn(), "Failed to log in");
    }

    @Test(priority = 1, enabled = true)
    public void statusesAreShownTest_homeTimeline_uiTest() throws IOException {
        HomePage homePage = openPage(mainURL, HomePage.class);
        String lastMessageText = getHomeStatuses(0).getString("text");

        homePage.tweetTextFld("1").shouldHave(text(lastMessageText));
    }

    @Test(priority = 2, enabled = true)
    public void removeStatus_uiTest() throws IOException {
        tweetPage = openPage(tweetURL, TweetPage.class);

        String textForStatus = "Mars cannot be terraformed";
        JSONObject statusForRemove = updateStatus(textForStatus);
        String id_str = statusForRemove.getString("id_str");

        removeStatus(id_str);

        openPage(tweetURL, TweetPage.class);
        CommonWD.waitForLoad(getWebDriver());
        tweetPage.tweetWithTxt(textForStatus).shouldNotBe(visible);
    }

    @Test(priority = 3, enabled = true)
    public void updatedStatus_uiTest() throws IOException {
        String textForStatus = "Venus cannot be terraformed";
        updateStatus(textForStatus);

        tweetPage = openPage(tweetURL, TweetPage.class);
        CommonWD.waitForLoad(getWebDriver());
        tweetPage.tweetWithTxt(textForStatus).shouldBe(visible);
    }

    @Test(priority = 4, enabled = true)
    public void statusDuplicationMessage_uiTest() throws IOException {

        String textFor = "Moon cannot be terraformed";
        updateStatus(textFor);
        duplicateStatus(textFor);

        tweetPage = openPage(tweetURL, TweetPage.class);
        CommonWD.waitForLoad(getWebDriver());
        Assert.assertTrue(tweetPage.tweetsWithTxt(textFor).size() != 2 );
        tweetPage.tweetsWithTxt(textFor).shouldHaveSize(1);
    }

    @AfterClass(enabled = true)
    public void clean() throws IOException {
        cleanAllStatuses();
    }

    @AfterClass(enabled = true)
    public void logOut() throws IOException {
        System.out.println("Logging Out From the system");
        tweetPage = openPage(tweetURL, TweetPage.class);
        tweetPage.clickUserMenuBtn();
        tweetPage.clickLogOutLnk();
        CommonWD.waitForLoad(getWebDriver());
    }
}
