package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest{

    @Test(priority = 7)
    public void createUser() {

        String payload =
                "{\"name\":\"John Doe\"}";

        given()
            .contentType("application/json")
            .body(payload)
        .when()
            .post("/users")
        .then()
            .statusCode(201);
    }

    @Test(priority = 8)
    public void verifyCreateUserResponse() {

        String payload =
                "{\"name\":\"John Doe\"}";

        given()
            .contentType("application/json")
            .body(payload)
        .when()
            .post("/users")
        .then()
            .body("message",
                  equalTo("User created"))
            .body("id",
                  equalTo(101));
    }
}