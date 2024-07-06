package com.practicesoftwaretesting;

import com.practicesoftwaretesting.pages.*;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class UserTest {

    HomePage homePage = new HomePage();
    Header header = new Header();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    AccountPage accountPage = new AccountPage();

    @Test
    void registerNewUserAndLogin() {
        open("https://practicesoftwaretesting.com");
        homePage.isLoaded();
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
                .firstName("George")
                .lastName("Washington")
                .address("326 Good Street")
                .city("Illinoise")
                .state("Arcanzas")
                .country("Uruguay")
                .postcode("54321")
                .phone("12345678")
                .dob("09/03/1895")
                .email("hi887@gmail.com")
                .password("123#HalloWorld")
                .build();
    }
}
