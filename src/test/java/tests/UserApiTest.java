package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.ConfigReader;

public class UserApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
        System.out.println("Base URI = " + RestAssured.baseURI);
    }

    @Test
    public void verifyUserDetails() {

        RestAssured
                .given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .log().all();
    }
}