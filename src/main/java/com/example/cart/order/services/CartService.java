package com.example.cart.order.services;

import com.example.cart.order.entities.Cart;
import com.example.cart.order.entities.CartItem;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CartService {

    public Cart addCart(Cart cart);
    public Cart findCart(String cartId);
    public List<CartItem> addToCart(String cartId, CartItem cartItems);
    public String deleteCartById(String CartId);
    public List<CartItem> findAllCartItems(String cartId);
    public Boolean deleteAllCartItems(String cartId);
    public List<CartItem> deleteOneCartItem(String cartId, String skuId);
}