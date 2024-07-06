package com.practicesoftwaretesting.pages;

import com.practicesoftwaretesting.asserts.CheckoutPageAsserts;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.practicesoftwaretesting.utils.SelectorUtils.byDataTest;

public class CheckoutPage {

    private static final By STEPS_INDICATOR = By.className("steps-4");
    private static final By PROCEED_TO_CHECKOUT_BUTTON = byDataTest("proceed-1");
    private static final By PROCEED_TO_CHECKOUT_SIGNED_IN_BUTTON = byDataTest("proceed-2");
    private static final By PROCEED_TO_CHECKOUT_BILLING_ADDRESS_BUTTON = byDataTest("proceed-3");
    private static final By CHOOSE_PAYMENT_METHOD = byDataTest("payment-method");
    private static final String CASH_PAYMENT = "Cash on Delivery";
    private static final By CONFIRM_BUTTON = byDataTest("finish");
    protected static final By MESSAGE = byClassName("help-block");

    public CheckoutPage isLoaded() {
        $(STEPS_INDICATOR).shouldBe(visible);
        return this;
    }

    public CheckoutPage proceedToCheckout() {
        $(PROCEED_TO_CHECKOUT_BUTTON).click();
        return this;
    }

    public CheckoutPage proceedToCheckoutSignedIn() {
        $(PROCEED_TO_CHECKOUT_SIGNED_IN_BUTTON).click();
        return this;
    }

    public CheckoutPage proceedToCheckoutBillingAddress() {
        $(PROCEED_TO_CHECKOUT_BILLING_ADDRESS_BUTTON).click();
        return this;
    }

    public CheckoutPage choosePaymentMethodAndConfirm() {
        $(CHOOSE_PAYMENT_METHOD).selectOption(CASH_PAYMENT);
        $(CONFIRM_BUTTON).click();
        return this;
    }

    public CheckoutPageAsserts assertThat() {
        return new CheckoutPageAsserts();
    }
}
