package com.practicesoftwaretesting.user.assertions;

import com.practicesoftwaretesting.user.model.RegisterUserResponse;
import lombok.AllArgsConstructor;
import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class RegisterUserResponseAsserts {

    private RegisterUserResponse registerUserResponse;

    public RegisterUserResponseAsserts firstNameIs(String expectedFirstName) {
        assertThat(registerUserResponse.getFirstName())
                .withFailMessage(String.format("firstName should be %s but was %s",
                        expectedFirstName,
                        registerUserResponse.getFirstName()))
                .isEqualTo(expectedFirstName);
        return this;
    }

    public RegisterUserResponseAsserts lastNameIs(String expectedLastName) {
        assertThat(registerUserResponse.getLastName())
                .withFailMessage(String.format("lastName should be %s but was %s",
                        expectedLastName,
                        registerUserResponse.getLastName()))
                .isEqualTo(expectedLastName);
        return this;
    }

    public RegisterUserResponseAsserts addressIs(String expectedAddress) {
        assertThat(registerUserResponse.getAddress())
                .withFailMessage(String.format("address should be %s but was %s",
                        expectedAddress,
                        registerUserResponse.getAddress()))
                .isEqualTo(expectedAddress);
        return this;
    }

    public RegisterUserResponseAsserts cityIs(String expectedCity) {
        assertThat(registerUserResponse.getCity())
                .withFailMessage(String.format("city should be %s but was %s",
                        expectedCity,
                        registerUserResponse.getCity()))
                .isEqualTo(expectedCity);
        return this;
    }

    public RegisterUserResponseAsserts stateIs(String expectedState) {
        assertThat(registerUserResponse.getState())
                .withFailMessage(String.format("state should be %s but was %s",
                        expectedState,
                        registerUserResponse.getState()))
                .isEqualTo(expectedState);
        return this;
    }

    public RegisterUserResponseAsserts countryIs(String expectedCountry) {
        assertThat(registerUserResponse.getCountry())
                .withFailMessage(String.format("country should be %s but was %s",
                        expectedCountry,
                        registerUserResponse.getCountry()))
                .isEqualTo(expectedCountry);
        return this;
    }

    public RegisterUserResponseAsserts postcodeIs(String expectedPostcode) {
        assertThat(registerUserResponse.getPostcode())
                .withFailMessage(String.format("postcode should be %s but was %s",
                        expectedPostcode,
                        registerUserResponse.getPostcode()))
                .isEqualTo(expectedPostcode);
        return this;
    }

    public RegisterUserResponseAsserts phoneIs(String expectedPhone) {
        assertThat(registerUserResponse.getPhone())
                .withFailMessage(String.format("phone should be %s but was %s",
                        expectedPhone,
                        registerUserResponse.getPhone()))
                .isEqualTo(expectedPhone);
        return this;
    }

    public RegisterUserResponseAsserts dobIs(String expectedDob) {
        assertThat(registerUserResponse.getDob())
                .withFailMessage(String.format("dob should be %s but was %s",
                        expectedDob,
                        registerUserResponse.getDob()))
                .isEqualTo(expectedDob);
        return this;
    }

    public RegisterUserResponseAsserts emailIs(String expectedEmail) {
        assertThat(registerUserResponse.getEmail())
                .withFailMessage(String.format("email should be %s but was %s",
                        expectedEmail,
                        registerUserResponse.getEmail()))
                .isEqualTo(expectedEmail);
        return this;
    }

    public RegisterUserResponseAsserts idIsNotNull() {
        assertThat(registerUserResponse.getId())
                .withFailMessage("id should not be null")
                .isNotNull();
        return this;
    }

    public RegisterUserResponseAsserts createdAtIsNotNull() {
        assertThat(registerUserResponse.getCreatedAt())
                .withFailMessage("createdAt should not be null")
                .isNotNull();
        return this;
    }
}
