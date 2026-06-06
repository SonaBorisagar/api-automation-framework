package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.ConfigReader;

public class ProductApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
    }

    @Test
    public void verifyProducts() {

        given()
        .when()
            .get("/products")
        .then()
            .statusCode(200)
            .body("size()", equalTo(2))
            .body("[0].id", equalTo(1))
            .body("[0].name", equalTo("Laptop"))
            .body("[1].id", equalTo(2))
            .body("[1].name", equalTo("Mouse"))
            .log().all();
    }
}