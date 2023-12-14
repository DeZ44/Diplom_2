import data.UserData;
import data.UserGenerator;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class UpdateUserTest extends CreateUserBaseTest {


    @Test
    public void userCantUpdateInfoUnauthorizedTest() {
        UserData newCredentialsUser = UserGenerator.getRandomUser();
        Response response = userClient.updateUserInfoWithoutAuthorization(newCredentialsUser);
        response.then()
                .statusCode(401)
                .body("success", is(false));
    }

    @Test
    public void userCanUpdateLoginTest() {
        String json = "{\"email\": \"" + RandomStringUtils.randomAlphabetic(8) + "@mail.ru\"}";
        Response response = userClient.updateUserInfo(json, accessToken);
        response.then()
                .statusCode(200)
                .body("success", is(true));
    }

    @Test
    public void userCanUpdatePasswordTest() {
        String json = "{\"password\": \"" + RandomStringUtils.randomAlphabetic(8) + "\"}";
        Response response = userClient.updateUserInfo(json, accessToken);
        response.then()
                .statusCode(200)
                .body("success", is(true));
    }

    @Test
    public void userCanUpdateNameTest() {
        String json = "{\"name\": \"" + RandomStringUtils.randomAlphabetic(8) + "\"}";
        Response response = userClient.updateUserInfo(json, accessToken);
        response.then()
                .statusCode(200)
                .body("success", is(true));
    }

}
