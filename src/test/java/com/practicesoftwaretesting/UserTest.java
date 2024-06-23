package com.practicesoftwaretesting;

import com.github.javafaker.Faker;
import com.practicesoftwaretesting.user.UserController;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.LoginResponse;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import com.practicesoftwaretesting.user.model.RegisterUserResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest extends BaseTest {

    public String userEmail;
    public static final String USER_PASSWORD = "Super-secret211@@";

    UserController userController = new UserController();

    @Test
    void testUser() {
        userEmail = getUserEmail();

        var registerUserRequest = buildUser();
        var registerUserResponse = userController.registerUser(registerUserRequest)
                .as(RegisterUserResponse.class);
        assertNotNull(registerUserResponse.getId());

        var loginRequestBody = new LoginRequest(userEmail, USER_PASSWORD);
        var loginResponse = userController.loginUser(loginRequestBody)
                .as(LoginResponse.class);;
        assertNotNull(loginResponse.getAccessToken());

        var adminLoginRequestBody = new LoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminLoginResponse = userController.loginUser(adminLoginRequestBody)
                .as(LoginResponse.class);

        var userId = registerUserResponse.getId();
        var token = adminLoginResponse.getAccessToken();
        userController.deleteUser(userId, token)
                .then()
                .statusCode(204);
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
