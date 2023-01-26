package com.example.cart.order.services;

import com.example.cart.order.entities.Order;
import com.example.cart.order.services.ServImpl.OrderItemSequenceGeneratorServ;

import java.util.List;
import java.util.Optional;

public interface OrderService  {

    public Order addOrder(Order order);
    public String deleteOrderById(String OrderId);
    public List<Order> findAll();
    public Order findByOrderId(String OrderId);
    List<Order> findByEmail(String cartId);
    //public OrderItems addToOrderItems(OrderItems orderItems);
}
