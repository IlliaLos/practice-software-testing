package com.practicesoftwaretesting;

import com.practicesoftwaretesting.cart.CartController;
import com.practicesoftwaretesting.cart.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    private static final String PRODUCT_ID = "01J13AYF1NV1WRPMPYW9Z2PZTS";

    CartController cartController = new CartController();

    @Test
    void cartTest() {
        var createdCart = cartController.createCart()
                .as(CreateCartResponse.class);
        assertNotNull(createdCart.getId());

        var cardId = createdCart.getId();
        var updateCartResponse = cartController.addItemToCart(cardId, new AddCartItemRequest(PRODUCT_ID, 1))
                .as(UpdateCartResponse.class);
        assertNotNull(updateCartResponse.getResult());

        var cartDetails = cartController.getCart(cardId)
                .as(CartDetales.class);
        var productIds = cartDetails.getCartItems().stream().map(CartItem::getProductId).toList();
        assertTrue(productIds.contains(PRODUCT_ID));

        cartController.deleteCart(cardId)
                .then()
                .statusCode(204);
    }
}
