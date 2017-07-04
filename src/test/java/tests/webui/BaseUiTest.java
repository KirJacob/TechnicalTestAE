package tests.webui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import helpers.AccessData;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseUiTest implements AccessData {

    private static void printEnvInfo() {
        String f = "%-25s %s\n";
        System.out.printf(f, "timeout, ms:", "<" + Configuration.timeout + ">");
        System.out.printf(f, "mainURL:", "<" + mainURL + ">");
        System.out.printf(f, "webdriver:", "<" + getWebDriver().toString() + ">");
        System.out.println("");
    }

    public static void setDriver(String browser) throws MalformedURLException {

        switch (browser){
            case "firefox": {
                Configuration.browser = "firefox";
            } break;

            case "chrome": {
                String driverPath = "bin/chromedriver";
                System.setProperty("webdriver.chrome.driver", driverPath);
                Configuration.browser = "chrome";

            } break;
            default:
                break;
        }

        Configuration.timeout = 8000;
        printEnvInfo();
    }
}
