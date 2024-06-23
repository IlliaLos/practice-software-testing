package com.practicesoftwaretesting.cart.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private String id;
    private String name;
    private String description;
    private double price;
    private boolean isLocationOffer;
    private boolean isRental;
    private boolean inStock;
}
