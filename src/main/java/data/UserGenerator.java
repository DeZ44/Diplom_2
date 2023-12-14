package data;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public static final String DOMAIN = "@mail.ru";

    @Step("Генерация пользователя со всеми полями")
    public static UserData getRandomUser(){
        String email = RandomStringUtils.randomAlphabetic(8) + DOMAIN;
        String password = RandomStringUtils.randomAlphabetic(8);
        String name = RandomStringUtils.randomAlphabetic(8);

        return new UserData(email, password, name);
    }

    @Step("Генерация пользователя без пароля")
    public static UserData getRandomUserWithoutPassword(){
        String email = RandomStringUtils.randomAlphabetic(8) + DOMAIN;
        String name = RandomStringUtils.randomAlphabetic(8);

        return new UserData(email, name);
    }

    @Step("Генерация пользователя только с паролем")
    public static UserData getRandomUserOnlyPassword(){
        String password = RandomStringUtils.randomAlphabetic(8);

        return new UserData(password);
    }
}
