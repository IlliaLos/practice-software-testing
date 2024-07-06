package com.practicesoftwaretesting.asserts;

import com.practicesoftwaretesting.pages.RegisterPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPageAsserts extends RegisterPage {

    public RegisterPageAsserts hasCorrectInfo() {
        $(FIRST_NAME_LABLE).shouldHave(text("First name"));
        $(FIRST_NAME_INPUT).shouldHave(attribute("placeholder", "First name *"));

        $(LAST_NAME_LABLE).shouldHave(text("Last name"));
        $(LAST_NAME_INPUT).shouldHave(attribute("placeholder", "Your last name *"));

        $(DOB_LABLE).shouldHave(text("Date of birth *"));
        $(DOB_INPUT).shouldHave(attribute("type", "date"));

        $(ADDRESS_LABLE).shouldHave(text("Address"));
        $(ADDRESS_INPUT).shouldHave(attribute("placeholder", "Your Address *"));

        $(POSTCODE_LABLE).shouldHave(text("Postcode"));
        $(POSTCODE_INPUT).shouldHave(attribute("placeholder", "Your Postcode *"));

        $(CITY_LABLE).shouldHave(text("City"));
        $(CITY_INPUT).shouldHave(attribute("placeholder", "Your City *"));

        $(STATE_LABLE).shouldHave(text("State"));
        $(STATE_INPUT).shouldHave(attribute("placeholder", "Your State *"));

        $(COUNTRY_LABLE).shouldHave(text("Country"));
        $(COUNTRY_INPUT).shouldHave(tagName("select"));
        $(COUNTRY_INPUT).findAll("option")
                .filter(text("Your country *"))
                .shouldHave(size(1))
                .get(0)
                .shouldBe(selected);

        $(PHONE_LABLE).shouldHave(text("Phone"));
        $(PHONE_INPUT).shouldHave(attribute("placeholder", "Your phone *"));

        $(EMAIL_LABLE).shouldHave(text("Email address"));
        $(EMAIL_INPUT).shouldHave(attribute("placeholder", "Your email *"));

        $(PASSWORD_LABLE).shouldHave(text("Password"));
        $(PASSWORD_INPUT).shouldHave(attribute("placeholder", "Your password *"));
        return this;
    }
}
