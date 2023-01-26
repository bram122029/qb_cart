package com.example.cart.order.entities;

import lombok.Data;

@Data
public class CartItem {

    private String SKU_ID;
    private ProductDetails productDetails;
    private Double productPrice;
    private Double quantity;

}
