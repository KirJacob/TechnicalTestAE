package tests.webui;

import com.codeborne.selenide.Configuration;
import helpers.AccessData;
import helpers.Common;

import java.net.MalformedURLException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseUiTest extends Common implements AccessData{

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
