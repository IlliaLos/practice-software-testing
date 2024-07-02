package com.practicesoftwaretesting.cart.model;

import com.practicesoftwaretesting.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private String id;
    private int quantity;
    private double discountPercentage;
    private String cartId;
    private String productId;
    private Product product;
}
