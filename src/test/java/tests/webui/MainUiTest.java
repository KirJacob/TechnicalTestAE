package tests.webui;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.CommonWD.waitForLoad;

public class MainUiTest extends BaseUiTest {

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browser) throws MalformedURLException {
        setDriver(browser);
        open(mainURL);
        waitForLoad(getWebDriver());
    }

    @Test(priority = 1)
    public void test1(){
        System.out.println("Hello Twitter!!!");
        sleep(10000);
    }

}
