package data;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Step("Получаем креды текущего пользователя")
    public static UserCredentials from(UserData user){
        return new UserCredentials(user.getEmail(), user.getPassword());
    }

    @Step("Получаем логин текущего пользователя и генерируем неверный пароль")
    public static UserCredentials badPassword(UserData user){
        String password = RandomStringUtils.randomAlphabetic(8);
        return new UserCredentials(user.getEmail(), password);
    }

    @Step("Получаем пароль текущего пользователя и генерируем email")
    public static UserCredentials badEmail(UserData user){
        String email = RandomStringUtils.randomAlphabetic(8);
        return new UserCredentials(email, user.getPassword());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
