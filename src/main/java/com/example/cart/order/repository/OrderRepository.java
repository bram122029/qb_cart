package com.example.cart.order.repository;

import com.example.cart.order.entities.Order;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository  extends MongoRepository<Order,String> {
    List<Order> findAllByCustomer_Email(String cartId);
}
