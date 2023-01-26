package com.example.cart.order.dtos;

import com.example.cart.order.entities.CartItem;
import com.example.cart.order.entities.Customer;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CartDTO {


    private String cartId;
    private Customer customer;
    private boolean isGuest;
    private List<CartItem> cartItems;
    private Double totalOrderCost;
    private String createdBy;
    private String updatedBy;
    private Date dateTime;


}
