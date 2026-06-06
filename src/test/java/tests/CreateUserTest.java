package tests;

import io.restassured.RestAssured;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Utils.ConfigReader;
import Utils.WireMockManager;

public class CreateUserTest {
	
	@BeforeSuite
	public void startMock() {
	    WireMockManager.startServer();
	}

	@AfterSuite
	public void stopMock() {
	    WireMockManager.stopServer();
	}
	
	@BeforeClass
	public void setup() {
	    RestAssured.baseURI = ConfigReader.getBaseUrl();
	}

    @Test
    public void createUser() {

    	String payload =
    	        "{\n" +
    	        "  \"name\":\"John\"\n" +
    	        "}";

        RestAssured
                .given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/users")
                .then()
                .statusCode(201);
    }
}