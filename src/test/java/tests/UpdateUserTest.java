package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class UpdateUserTest extends BaseTest{

    @Test(priority = 9)
    public void updateUser() {

        String payload =
                "{\"name\":\"Updated User\"}";

        given()
            .contentType("application/json")
            .body(payload)
        .when()
            .put("/users/1")
        .then()
            .statusCode(200)
            .body("message",
                  equalTo("User Updated"));
    }
}