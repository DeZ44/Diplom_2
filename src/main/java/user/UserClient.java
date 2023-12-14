package user;

import com.google.gson.JsonObject;
import data.UserCredentials;
import data.UserData;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestUser {
    private static final String USER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String UPDATE_USER_INFO_PATH = "/api/auth/user";
    private static final String ORDERS_PATH = "/api/orders";
    private static final String DELETE_USER_PATH = "/api/auth/user";
    private static final String GET_INGREDIENTS_PATH = "/api/ingredients";

    @Step("Вызов ручки создания пользователя POST /api/auth/register")
    public Response createUser(UserData user){
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(USER_PATH);
    }

    @Step("Вызов ручки логина пользователя POST /api/auth/login")
    public ValidatableResponse loginUser(UserCredentials userCredentials){
        return given()
                .spec(requestSpecification())
                .and()
                .body(userCredentials)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    @Step("Вызов ручки обновления данных пользователя с авторизацией PATCH /api/auth/user")
    public Response updateUserInfo(String json, String accessToken){
        return given()
                .spec(requestSpecification())
                .and()
                .header("Authorization", accessToken)
                .and()
                .body(json)
                .when()
                .patch(UPDATE_USER_INFO_PATH);
    }

    @Step("Вызов ручки обновления данных пользователя без авторизации PATCH /api/auth/user")
    public Response updateUserInfoWithoutAuthorization(UserData user){
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .patch(UPDATE_USER_INFO_PATH);
    }

    @Step("Вызов ручки удаления пользователя")
    public void deleteUser(String accessToken) {
        given()
                .spec(requestSpecification())
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_USER_PATH)
                .then()
                .statusCode(202);
    }

    @Step("Вызов ручки получения ингредиентов GET /api/ingredients")
     public String getHashOfIngredient(int id) {
        return given()
                .spec(requestSpecification())
                .get(GET_INGREDIENTS_PATH)
                .getBody()
                .path("data[" + id + "]._id").toString();
     }

    @Step("Вызов ручки создания заказа с авторизацией POST /api/orders")
    public Response createOrderWithAuthorization(JsonObject json, String accessToken){
        return given()
                .spec(requestSpecification())
                .and()
                .header("Authorization", accessToken)
                .and()
                .body(json.toString())
                .when()
                .post(ORDERS_PATH);
    }

    @Step("Вызов ручки создания заказа без авторизации POST /api/orders")
    public Response createOrderWithoutAuthorization(JsonObject json){
        return given()
                .spec(requestSpecification())
                .and()
                .body(json.toString())
                .when()
                .post(ORDERS_PATH);
    }

    @Step("Вызов ручки получения заказов с авторизацией GET /api/orders")
    public Response getOrdersWithAuthorization(String accessToken){
        return given()
                .spec(requestSpecification())
                .and()
                .header("Authorization", accessToken)
                .when()
                .get(ORDERS_PATH);
    }

    @Step("Вызов ручки получения заказов без авторизации GET /api/orders")
    public Response getOrdersWithoutAuthorization(){
        return given()
                .spec(requestSpecification())
                .when()
                .get(ORDERS_PATH);
    }

}
