package it.cantest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:4001";
        RestAssured.filters(List.of(new AllureRestAssured()));
    }

    @Test
    public void testSuccessfulSignIn() {
        String requestBody = """
            {
                "username": "admin",
                "password": "admin"
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users/signin")
                .then()
                .statusCode(200)
                .body("username", equalTo("admin"))
                .body("token", notNullValue())
                .body("email", containsString("@"));
    }

    @Test
    public void testInvalidLoginDetails() {
        String requestBody = """
            {
                "username": "invalidUser",
                "password": "wrongPassword"
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users/signin")
                .then()
                .statusCode(422)
                .body("message", containsString("Invalid username/password"));
    }

    @Test
    public void testValidationError() {
        String requestBody = """
            {
                "username": ""
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users/signin")
                .then()
                .statusCode(400)
                .body("username", is("Minimum username length: 4 characters"));
    }

}

