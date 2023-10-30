package com.guruqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.pollingInterval = 400;
        Configuration.timeout = 4000;
        Configuration.browserSize = "1920x1080";
    }
    @Test
    void successfulCheckHeaderTest() {
        open("https://github.com");
        $("[aria-label='Global']").$(byText("Solutions")).hover();
        $("[aria-labelledby='solutions-for-heading']").$(byText("Enterprise")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI Powered\nDeveloper Platform"));
    }

    @Test
    void successfulDragNDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        sleep(4000);
        $("#column-a").dragAndDrop(DragAndDropOptions.to("#column-b"));
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
        sleep(4000);
        actions().clickAndHold($("#column-b")).moveToElement($("#column-a")).release().perform();
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
    }
}
