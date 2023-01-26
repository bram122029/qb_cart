package com.example.cart.order.entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
public class Stock {

    private String productId;
    private String productName;
    private int quantity;
    private double price;
}
