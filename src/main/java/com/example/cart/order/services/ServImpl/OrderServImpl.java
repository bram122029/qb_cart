package com.example.cart.order.services.ServImpl;

import com.example.cart.order.entities.CartItem;
import com.example.cart.order.entities.EmailDetails;
import com.example.cart.order.entities.Order;
import com.example.cart.order.entities.Stock;
import com.example.cart.order.repository.OrderRepository;
import com.example.cart.order.services.CartFeignService;
import com.example.cart.order.services.EmailService;
import com.example.cart.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartFeignService cartFeignService;

    @Autowired
    EmailService emailService;

    public Order addOrder(Order order){
//        for(CartItem cartItem : order.getCartItems()){
//            Stock stock = cartFeignService.reduceStock(cartItem.getSKU_ID(), String.valueOf(cartItem.getQuantity()));
//            if(stock == null)
//                return null;
//        }
        EmailDetails emailDetails= new EmailDetails();
        emailDetails.setRecipient(order.getCustomer().getEmail());
        emailDetails.setMsgBody("Total cost : "+ order.getTotalOrderCost());
        emailDetails.setAttachment("---------------");
        emailDetails.setSubject("Thank you for placing the order");

        String mailStatus = emailService.sendSimpleMail(emailDetails);
        System.out.println(mailStatus);

        return orderRepository.save(order);
    }

    public String  deleteOrderById(String orderId){
        if(orderRepository.existsById(orderId)){
            orderRepository.deleteById(orderId);
            return "Deleted Successfully";
        }
        return "Unsuccessful deletion";
    }
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findByOrderId(String orderId){

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            return  optionalOrder.get();
        }
        return null;
    }

    @Override
    public List<Order> findByEmail(String email) {
        return orderRepository.findAllByCustomer_Email(email);
    }


}
