package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ConfigReader;
import Utils.ExtentReportManager;
import Utils.WireMockManager;
import io.restassured.RestAssured;

public class UserApiTest extends BaseTest{
	
	@BeforeClass
	public void startServer() {
	    WireMockManager.start();;
	}

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
    }

    @Test(priority = 1)
    public void verifyStatusCode() {

        given()
        .when()
            .get("/users/1")
        .then()
            .statusCode(200);
    }

    @Test(priority = 2)
    public void verifyResponseBody() {

        given()
        .when()
            .get("/users/1")
        .then()
            .body("id", equalTo(1))
            .body("name", equalTo("John Doe"));
    }

    @Test(priority = 3)
    public void verifyHeaders() {

        given()
        .when()
            .get("/users/1")
        .then()
            .header("Content-Type",
                    containsString("application/json"));
    }
    
    
}