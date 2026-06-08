package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductApiTest extends BaseTest{

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
    }

    @Test(priority = 4)
    public void verifyResponseTime() {

        Response response =
                given()
                .when()
                .get("/products");

        Assert.assertTrue(response.time() < 2000);
    }

    @Test(priority = 5)
    public void verifyProductCount() {

        given()
        .when()
            .get("/products")
        .then()
            .body("size()", equalTo(2));
    }

    @Test(priority = 6)
    public void verifyProductDetails() {

        given()
        .when()
            .get("/products")
        .then()
            .body("[0].id", equalTo(1))
            .body("[0].name", equalTo("Laptop"))
            .body("[1].id", equalTo(2))
            .body("[1].name", equalTo("Mouse"));
    }
}