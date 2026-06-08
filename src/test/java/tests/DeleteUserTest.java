package tests;

import static io.restassured.RestAssured.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.testng.annotations.Test;

public class DeleteUserTest extends BaseTest{

    @Test(priority = 10)
    public void deleteUser() {

        given()
        .when()
            .delete("/users/1")
        .then()
            .statusCode(204);
    }
}