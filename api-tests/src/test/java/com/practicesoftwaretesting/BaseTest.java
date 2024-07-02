package com.practicesoftwaretesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.github.javafaker.Faker;
import com.practicesoftwaretesting.user.UserController;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

public abstract class BaseTest {

    protected static final String DEFAULT_PASSWORD = "Super-secret211@@";
    private static final String ADMIN_EMAIL = "admin@practicesoftwaretesting.com";
    private static final String ADMIN_PASSWORD = "welcome01";

    static {
        configureRestAssured();
        RestAssured.requestSpecification = new RequestSpecBuilder()
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

    public void registerUser(String userEmail, String userPassword) {
        var userController = new UserController();
        var registerUserRequest = buildUser(userEmail, userPassword);
        userController.registerUser(registerUserRequest).as();
    }

    public String loginUser(String userEmail, String userPassword) {
        var userController = new UserController();
        var userLoginResponse = userController.loginUser(new LoginRequest(userEmail, userPassword))
                .as();
        return userLoginResponse.getAccessToken();
    }

    public String registerAndLoginNewUser() {
        var userEmail = getUserEmail();
        registerUser(userEmail, DEFAULT_PASSWORD);
        return loginUser(userEmail, DEFAULT_PASSWORD);
    }

    public String loginAsAdmin() {
        return loginUser(ADMIN_EMAIL, ADMIN_PASSWORD);
    }

    protected RegisterUserRequest buildUser(String email, String password) {
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
                .password(password)
                .email(email)
                .build();
    }

    protected String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }
}
