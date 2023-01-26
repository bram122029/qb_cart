package com.example.cart.order.entities;


import com.example.cart.order.services.ServImpl.OrderSequnceGeneratorServ;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "OrderDTO")
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";
    @Id
    @Indexed(unique = true)
    private String orderId;
    private String cartId;
    private Customer customer;
    private List<CartItem> cartItems;
    private Double totalOrderCost;
    private String createdBy;
    private String updatedBy;
    private Date dateTime;
}
