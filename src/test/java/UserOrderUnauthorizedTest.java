import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import constans.BaseUri;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import user.UserClient;

import static org.hamcrest.CoreMatchers.is;


public class UserOrderUnauthorizedTest {
    UserClient userClient;

    @Before
    public void setup() {
        userClient = new UserClient();
        RestAssured.baseURI = BaseUri.BASE_URI;
    }

    @Test
    public void createOrderUnauthorizedTest() {
        JsonObject json = new JsonObject();
        JsonArray ingredients = new JsonArray();
        ingredients.add(userClient.getHashOfIngredient(0));

        json.add("ingredients", ingredients);

        Response response = userClient.createOrderWithoutAuthorization(json);
        response.then()
                .statusCode(401)
                .body("success", is(false));;

    }

    @Test
    public void userCantTakeOrderUnauthorizedTest() {
        Response response = userClient.getOrdersWithoutAuthorization();
        response.then()
                .statusCode(401)
                .body("success", is(false));
    }
}
