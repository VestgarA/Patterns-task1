package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        LocalDate dateDay = LocalDate.now().plusDays(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = dateDay.format(formatter);
        return date;
    }

    public static String generateCity() {
        String[] cities = {"Майкоп", "Горно-Алтайск", "Барнаул", "Благовещенск", "Архангельск", "Астрахань",
                "Уфа", "Белгород", "Брянск", "Улан-Удэ", "Владимир", "Волгоград", "Вологда", "Воронеж", "Махачкала",
                "Биробиджан", "Чита", "Иваново", "Магас", "Иркутск", "Нальчик", "Калининград", "Калуга", "Черкесск",
                "Петрозаводск", "Москва", "Великий Новгород", "Нижний Новгород", "Севастополь", "Саратов",
                "Санкт-Петербург", "Владивосток"};
        return cities[new Random().nextInt(cities.length)];

    }


    public static String generateName(Faker faker) {
        faker = new Faker(new Locale("ru"));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(Faker faker) {
        faker = new Faker(new Locale("ru"));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}