package com.tools2test.apitests;


import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class LogoutAPITest {

    @Test
    public void testSuccessfulLogout() {
        // Set base URI
        RestAssured.baseURI = "http://crater.primetech-apps.com/api/v1/auth";
        
        Response response = given()
        	    .header("Content-Type", "application/json")
        	    .header("Company", "primetech")
        	    .body("{ \"username\": \"entityadmin@primetechschool.com\", \"password\": \"primetech@school\", \"device_name\": \"mobile_app\" }")
        	.when()
        	    .post("/login");

        String responseBody = response.getBody().asString();

        JSONObject jsonResponse = new JSONObject(responseBody);
        String authToken = jsonResponse.getString("token");

        given()
            .header("Authorization", "Bearer " + authToken)
        .when()
            .post("/logout")
        .then()
            .statusCode(200)
            .body("success", equalTo(true));
    }

    @Test
    public void testIncorrectAuthorization() {
        RestAssured.baseURI = "http://crater.primetech-apps.com/api/v1/auth";
        
        Response response = given()
        	    .header("Content-Type", "application/json")
        	    .header("Company", "primetech")
        	    .body("{ \"username\": \"entityadmin@primetechschool.com\", \"password\": \"primetech@school\", \"device_name\": \"mobile_app\" }")
        	.when()
        	    .post("/login");

        String responseBody = response.getBody().asString();

        JSONObject jsonResponse = new JSONObject(responseBody);
        String authToken = jsonResponse.getString("token");

        // Assuming you have an incorrect auth token
        String incorrectAuthToken = authToken + "cbBEd84909zTt";

        given()
            .header("Authorization", "Bearer " + incorrectAuthToken)
        .when()
            .post("/logout")
        .then()
            .statusCode(401)
            .body("message", equalTo("Unauthenticated."));
    }

    @Test
    public void testIncorrectEndpoint() {
        RestAssured.baseURI = "http://crater.primetech-apps.com/api/v1/auth";

        Response response = given()
        	    .header("Content-Type", "application/json")
        	    .header("Company", "primetech")
        	    .body("{ \"username\": \"entityadmin@primetechschool.com\", \"password\": \"primetech@school\", \"device_name\": \"mobile_app\" }")
        	.when()
        	    .post("/login");

        String responseBody = response.getBody().asString();

        JSONObject jsonResponse = new JSONObject(responseBody);
        String authToken = jsonResponse.getString("token");

        given()
            .header("Authorization", "Bearer " + authToken)
        .when()
            .post("/incorrectEndpoint")
        .then()
            .statusCode(404);
    }
}

