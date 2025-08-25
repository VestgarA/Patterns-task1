package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardDeliveryOrderTest {

    @BeforeEach
    public void openPage() {
        Selenide.open("http:/localhost:9999");
    }

    @Test
    public void autoDataGeneration() {
        var date = DataGenerator.generateDate(5);
        var name = DataGenerator.generateName(new Faker());
        var phone = DataGenerator.generatePhone(new Faker());
        $("[data-test-id='city'] input").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue(name);
        $("[data-test-id='phone'] input").setValue(phone);
        $("[data-test-id='agreement'").click();
        $$(".button").find(Condition.text("Запланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date));
        $$(".button").find(Condition.text("Запланировать")).click();
        $("[data-test-id='replan-notification']")
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$(".button").find(Condition.text("Перепланировать")).click();
        $("[data-test-id='success-notification']")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date));
    }
}
