package com.tools2test.apitests;

import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginAPITest {

    @Test
    public void testSuccessfulLogin() {
        RestAssured.baseURI = "http://crater.primetech-apps.com/api/v1/auth";

        given()
            .header("Content-Type", "application/json")
            .header("Company", "primetech")  // Use the correct company ID
            .body("{ \"username\": \"entityadmin@primetechschool.com\", \"password\": \"primetech@school\", \"device_name\": \"mobile_app\" }")
        .when()
            .post("/login")
        .then()
            .statusCode(200)
            .body("type", equalTo("Bearer"))
            .body("token", matchesPattern("[A-Za-z0-9|-]+"));
    }

    @Test
    public void testInvalidEndpoint() {
        RestAssured.baseURI = "http://crater.primetech-apps.com/api/v1/auth";

        given()
            .header("Content-Type", "application/json")
            .header("Company", "primetech")  // Use the correct company ID
            .body("{ \"username\": \"entityadmin@primetechschool.com\", \"password\": \"primetech@school\", \"device_name\": \"mobile_app\" }")
        .when()
            .post("/incorrectEndpoint")
        .then()
            .statusCode(404);
    }

    @Test
    public void testMissingRequestBody() {
        RestAssured.baseURI = "http://crater.primetech-apps.com/api/v1/auth";

        given()
            .header("Content-Type", "application/json")
            .header("Company", "primetech")  // Use the correct company ID
        .when()
            .post("/login")
        .then()
            .statusCode(422);
    }

    @Test
    public void testInvalidUsernameAndPassword() {
        RestAssured.baseURI = "http://crater.primetech-apps.com/api/v1/auth";

        given()
            .header("Content-Type", "application/json")
            .header("Company", "primetech")  // Use the correct company ID
            .body("{ \"username\": \"invalid_username\", \"password\": \"invalid_password\", \"device_name\": \"mobile_app\" }")
        .when()
            .post("/login")
        .then()
            .statusCode(405);
    }
}
