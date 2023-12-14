import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class UserOrderTest extends CreateUserBaseTest {

    @Test
    public void userCanTakeOrderTest() {
        Response response = userClient.getOrdersWithAuthorization(accessToken);
        response.then()
                .statusCode(200)
                .body("success", is(true));

    }

    @Test
    public void createOrderPositiveTest() {
        JsonObject json = new JsonObject();
        JsonArray ingredients = new JsonArray();
        ingredients.add(userClient.getHashOfIngredient(0));

        json.add("ingredients", ingredients);

        Response response = userClient.createOrderWithAuthorization(json, accessToken);
        response.then()
                .statusCode(200)
                .body("success", is(true));
    }

    @Test
    public void createOrderWithoutIngredientsTest() {
        JsonObject json = new JsonObject();
        JsonArray ingredients = new JsonArray();

        json.add("ingredients", ingredients);

        Response response = userClient.createOrderWithAuthorization(json, accessToken);
        response.then()
                .statusCode(400)
                .body("success", is(false));;
    }

    @Test
    public void createOrderWithBadIngredientsHashTest() {
        JsonObject json = new JsonObject();
        JsonArray ingredients = new JsonArray();
        ingredients.add(RandomStringUtils.randomAlphabetic(8));

        json.add("ingredients", ingredients);

        Response response = userClient.createOrderWithAuthorization(json, accessToken);
        response.then().statusCode(500);
    }


    }

