package ru.netology;

import ru.netology.data.DataGenerator;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

public class DeliveryFormTest {

    @Test
    public void shouldSendForm() {
        Configuration.holdBrowserOpen = false;

        String city = DataGenerator.generateCity("ru");
        String firstMeetingDate = DataGenerator.generateDate(4,"dd.MM.yyyy");
        String secondMeetingDate = DataGenerator.generateDate(7, "dd.MM.yyyy");
        String name = DataGenerator.generateName("ru");
        String phone = DataGenerator.generatePhone("ru");

        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").click();
        $x("//input[@placeholder='Город']").setValue(city);
        $x("//span[not(@*)]").doubleClick();
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(firstMeetingDate);
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.ESCAPE);
        $x("//input[@name='name']").click();
        $("[data-test-id='name'] input").setValue(name);
        $x("//input[@name='phone']").click();
        $("[data-test-id='phone'] input").setValue(phone);
        $("[data-test-id='agreement']").click();
        $x("//*[text()='Запланировать']").click();
        $(".notification_status_ok .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(Condition.visible);
        $x("//*[text()='Запланировать']").click();
        $x("//span[not(@*)]").doubleClick();
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(secondMeetingDate);
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.ESCAPE);
        $x("//*[text()='Перепланировать']").click();
        $(".notification_status_ok .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate))
                .shouldBe(Condition.visible);
    }
}