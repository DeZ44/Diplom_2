import data.UserCredentials;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class LoginUserTest extends CreateUserBaseTest {

    @Test
    public void userCanBeLoginTest() {
        ValidatableResponse responseLogin = userClient.loginUser(UserCredentials.from(user));
        responseLogin
                .statusCode(200)
                .body("success", is(true));

    }

    @Test
    public void badLoginUserCantLoginTest() {
        ValidatableResponse responseLogin = userClient.loginUser(UserCredentials.badEmail(user));
        responseLogin
                .statusCode(401)
                .body("success", is(false));

    }

    @Test
    public void badPassUserCantLoginTest() {
        ValidatableResponse responseLogin = userClient.loginUser(UserCredentials.badPassword(user));
        responseLogin
                .statusCode(401)
                .body("success", is(false));

    }
}
