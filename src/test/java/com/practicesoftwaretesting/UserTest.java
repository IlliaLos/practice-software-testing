package com.practicesoftwaretesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    public String userEmail = getUserEmail();
    public static final String USER_PASSWORD = "Super-secret211@@";

    static {
        configureRestAssured();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.practicesoftwaretesting.com")
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    private static void configureRestAssured() {
        var objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        RestAssured.config = RestAssured.config()
                .objectMapperConfig(
                        RestAssured.config()
                                .getObjectMapperConfig()
                                .jackson2ObjectMapperFactory((cls, charset) -> objectMapper)
                );
    }

    @Test
    void testBrands() {
        RestAssured.given()
                .get("/brands")
                .then()
                .statusCode(200);
    }

    @Test
    void testUser() {
        //Register user
        var registerUserRequest = buildUser();
        var registerUserResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(registerUserRequest)
                .post("/users/register")
                .as(RegisterUserResponse.class);
        assertNotNull(registerUserResponse.getId());

        //Login
        var loginRequestBody = new LoginRequest(userEmail, USER_PASSWORD);
        var loginResponse = loginUser(loginRequestBody);
        assertNotNull(loginResponse.getAccessToken());

        //Login as admin
        var adminLoginRequestBody = new LoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminLoginResponse = loginUser(adminLoginRequestBody);

        //Delete user
        var userId = registerUserResponse.getId();
        var token = adminLoginResponse.getAccessToken();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .delete("users/" + userId)
                .then()
                .statusCode(204);
    }

    private LoginResponse loginUser(LoginRequest loginRequestBody) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginRequestBody)
                .post("/users/login")
                .as(LoginResponse.class);
    }

    private RegisterUserRequest buildUser() {
        return RegisterUserRequest.builder()
                .firstName("John")
                .lastName("Lucas")
                .address("Street 1")
                .city("City")
                .state("State")
                .country("Country")
                .postcode("1234AA")
                .phone("0987654321")
                .dob("1970-01-01")
                .password(USER_PASSWORD)
                .email(userEmail)
                .build();
    }

    private String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }
}
