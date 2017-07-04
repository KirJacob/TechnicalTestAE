package helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;

import java.io.IOException;
import java.util.ArrayList;

import static api.Requests.getUserStatusesCollection;
import static api.Requests.removeStatus;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class Common {
    protected static <PageObjectClass> PageObjectClass openPage(String relativeOrAbsoluteUrl,
                                                                Class<PageObjectClass> pageObjectClassClass) {
        relativeOrAbsoluteUrl = relativeOrAbsoluteUrl.replaceAll("(?<!(http:|https:))[//]+", "/");
        return open(relativeOrAbsoluteUrl, "", "", "", pageObjectClassClass);
    }

    protected static void waitForElement(SelenideElement element){
        element.waitUntil(Condition.visible, 5000);
    }

    protected void shouldBeVisible(SelenideElement element, String errorMsg) {
        try {
            element.shouldBe(visible);
        } catch (ElementNotFound | ElementShould | NullPointerException e) {
            System.err.println(e.getStackTrace());
            e.printStackTrace();
            throw new RuntimeException(errorMsg);
        }
    }

    protected void shouldBeEnabled(SelenideElement element, String errorMsg) {
        try {
            element.shouldBe(enabled);
        } catch (ElementNotFound | ElementShould |NullPointerException e) {
            System.err.println(e.getStackTrace());
            e.printStackTrace();
            throw new RuntimeException(errorMsg);
        }
    }

    protected void setFieldVal(SelenideElement element, String value) {
        shouldBeVisible(element, "Field is not visible: " + element);
        element.setValue(value);
    }

    public void click(SelenideElement element) {
        shouldBeVisible(element, "Element is not visible: " + element);
        shouldBeEnabled(element, "Element is not enabled: " + element);
        element.click();
    }

    public void cleanAllStatuses() throws IOException {
        System.out.println("Cleaning statuses after all tests");
        ArrayList idList = getUserStatusesCollection();
        String tempId = null;
        for (Object obj : idList){
            tempId = obj.toString();
            removeStatus(tempId);
        }
    }
}
