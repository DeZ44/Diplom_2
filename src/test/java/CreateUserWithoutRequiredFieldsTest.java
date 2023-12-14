import data.UserData;
import data.UserGenerator;
import io.restassured.response.Response;
import org.junit.Test;
import user.UserClient;

public class CreateUserWithoutRequiredFieldsTest {
    UserClient userClient;
    UserData user;

    @Test
    public void createUserWithoutPasswordTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandomUserWithoutPassword();
        Response response = userClient.createUser(user);

        response.then().statusCode(403);

    }

    @Test
    public void createUserOnlyPasswordTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandomUserOnlyPassword();
        Response response = userClient.createUser(user);

        response.then().statusCode(403);

    }
}
