package com.example.cart.order.entities;


import lombok.Data;

@Data
public class ProductDetails {

    private String productId;
    private String productName;
    private String merchantId;
    private String merchantName;
}