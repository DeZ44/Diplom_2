package data;

import io.qameta.allure.Step;

public class UserData {

    private String email;
    private String password;
    private String name;

    public UserData(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserData(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public UserData(String password) {
        this.password = password;
    }

    public UserData() {
    }

    @Step("Получаем email текущего пользователя")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Step("Получаем пароль текущего пользователя")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
