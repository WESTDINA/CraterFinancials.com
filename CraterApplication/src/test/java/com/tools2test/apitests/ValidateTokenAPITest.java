package com.tools2test.apitests;



import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ValidateTokenAPITest {

    @Test
    public void testSuccessfulValidation() {
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
        //System.out.println("Auth Token: " + authToken);


        given()
            .header("Authorization", "Bearer " + authToken)
        .when()
            .get("/check")
        .then()
            .statusCode(200);
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
        //System.out.println("Auth Token: " + authToken);

        String incorrectAuthToken = authToken + "cbBEd84909zTt";

        given()
            .header("Authorization", "Bearer " + incorrectAuthToken)
        .when()
            .get("/check")
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
        //System.out.println("Auth Token: " + authToken);


        given()
            .header("Authorization", "Bearer " + authToken)
        .when()
            .get("/intuit")
        .then()
            .statusCode(404);
    }
}
