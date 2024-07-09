package com.practicesoftwaretesting;

import com.practicesoftwaretesting.pages.*;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class UserTest extends BaseTest {

    private final String USER_LAST_NAME = "Washington";

    HomePage homePage = new HomePage();
    Header header = new Header();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    AccountPage accountPage = new AccountPage();

    @Test
    void registerNewUserAndLogin() {
        homePage.open()
                .isLoaded();
        header.clickSignInMenuItem();
        loginPage.isLoaded()
                .clickRegisterYourAccount();
        registerPage.isLoaded()
                .assertThat()
                .hasCorrectInfo();

        var user = getUser();
        registerPage.registerNewUser(user);

        loginPage.isLoaded()
                .login(user.getEmail(), user.getPassword());

        accountPage.isLoaded();
        header.assertThat().isSignedIn(user.getFirstName() + " " + user.getLastName());
    }

    private RegisterUserRequest getUser() {
        return RegisterUserRequest.builder()
                .firstName("Georgy")
                .lastName(USER_LAST_NAME)
                .address("326 Good Street")
                .city("Illinoise")
                .state("Arcanzas")
                .country("Uruguay")
                .postcode("54321")
                .phone("12345678")
                .dob("09/03/1895")
                .email("hi991@gmail.com")
                .password("123#HalloWorld")
                .build();
    }

    @AfterEach
    void cleanUp() {
        var users = searchUsers(USER_LAST_NAME);
        users.getData().forEach(userToDelete -> deleteUser(userToDelete.getId()));
    }
}
