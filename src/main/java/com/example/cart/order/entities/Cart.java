package com.example.cart.order.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.List;


@Data
@Document(collection="CartDTO")
public class Cart {

    @Id
    @Indexed(unique = true)
    private String cartId;
    private Customer customer;
   // private String cartOwnership;
    private boolean isGuest;
    private List<CartItem> cartItems;
    private Double totalOrderCost;
    private String createdBy;
    private String updatedBy;
    private Date dateTime;

}
