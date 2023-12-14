import data.UserData;
import data.UserGenerator;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import user.UserClient;


public class CreateUserBaseTest {
    String accessToken;
    UserClient userClient;
    UserData user;
    Response createResponse;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();
        createResponse = userClient.createUser(user);
        accessToken = createResponse.path("accessToken");
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(accessToken);
    }
}
