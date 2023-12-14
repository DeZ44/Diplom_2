import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class CreateUserTest extends CreateUserBaseTest {


    @Test
    public void userCanBeCreatedTest() {
        createResponse.then()
                .statusCode(200)
                .body("success", is(true));

    }

    @Test
    public void duplicateUserCantBeCreatedTest() {
        Response duplicateResponse = userClient.createUser(user);

        duplicateResponse.then()
                .statusCode(403)
                .body("success", is(false));

    }



}
