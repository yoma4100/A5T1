package ru.netology.data;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale), new Random());
        String city = faker.address().city();
        return city;
    }

    public static String generateDate (int shift, String format) {
        LocalDate ltd = LocalDate.now().plusDays(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(ltd);
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale), new Random());
        String name = faker.name().fullName();
        String formattedName = name.replaceAll("[ёЁ]","е");
        return formattedName;
    }

    public static String generatePhone (String locale) {
        Faker faker = new Faker(new Locale(locale), new Random());
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String formattedPhone = phoneNumber.replaceFirst("^\\+\\d{1,3}", "+7").replaceAll("[^\\d+]","");
        return formattedPhone;
    }
}
