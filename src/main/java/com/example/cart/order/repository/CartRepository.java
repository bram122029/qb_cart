package com.example.cart.order.repository;

import com.example.cart.order.entities.Cart;
import com.example.cart.order.entities.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartRepository extends MongoRepository<Cart,String> {
}
