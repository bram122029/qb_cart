package com.example.cart.order.entities;

import lombok.Data;

@Data
public class Customer {
    private String userId;
    private String userName;
    private String userAddress;
    private String email;
}
