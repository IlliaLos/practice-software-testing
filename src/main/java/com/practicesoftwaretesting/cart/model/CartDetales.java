package com.practicesoftwaretesting.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetales {

    private String id;
    private double additionalDiscountPercentage;
    private double lat;
    private double lng;
    private List<CartItem> cartItems;
}
