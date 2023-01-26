package com.example.cart.order.controller;

import com.example.cart.order.dtos.CartDTO;
import com.example.cart.order.dtos.OrderDTO;
import com.example.cart.order.entities.Cart;
import com.example.cart.order.entities.CartItem;
import com.example.cart.order.entities.EmailDetails;
import com.example.cart.order.entities.Order;
import com.example.cart.order.services.CartService;
import com.example.cart.order.services.EmailService;
import com.example.cart.order.services.OrderService;
import com.example.cart.order.services.ServImpl.OrderSequnceGeneratorServ;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping("/cartController")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @Autowired
    EmailService emailService;

    @Autowired
    OrderSequnceGeneratorServ orderSequnceGeneratorServ;

    @PostMapping("/createCart")
    public ResponseEntity<Cart> createCart(@RequestBody CartDTO cartDTO){

        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDTO,cart);
        if(cart.isGuest()){
            cart.setCartId(((new Random().nextInt(900000) + 100000) + "@Guest"));
        }else{
            cart.setCartId(cart.getCustomer().getEmail());
        }
        return new ResponseEntity<Cart>(cartService.addCart(cart),HttpStatus.CREATED);

    }
    @DeleteMapping("/deleteCart/{cartId}")
    public ResponseEntity<String> deleteCartById(@PathVariable("cartId") String cartId){
        return new ResponseEntity<String>(cartService.deleteCartById(cartId),HttpStatus.OK);
    }


    @GetMapping("/findAllInCart/{cartId}")
    public ResponseEntity<List<CartItem>> findAllItemsInCart(@PathVariable("cartId") String cartId) {
        return new ResponseEntity(cartService.findAllCartItems(cartId), HttpStatus.OK);
    }

    @PostMapping("/addToCart/{cartId}")
    public ResponseEntity<List<CartItem>> addOneItemToCart(@PathVariable("cartId") String cartId, @RequestBody CartItem cartItem){
        return new ResponseEntity<>(cartService.addToCart(cartId, cartItem), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllCartItems/{id}")
    public ResponseEntity<Boolean> deleteAllItems(@PathVariable("id") String cartId){
        return new ResponseEntity<>(cartService.deleteAllCartItems(cartId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteOneItem/{cartId}/{skuId}")
    public ResponseEntity<List<CartItem>> deleteOneItem(@PathVariable("cartId") String cartId, @PathVariable("skuId") String skuId){
        return new ResponseEntity<>(cartService.deleteOneCartItem(cartId, skuId), HttpStatus.OK);
    }

    @PostMapping("/buy/{cartId}")
    public ResponseEntity<Order> orderItems(@PathVariable String cartId){
//        Cart userCart = cartService.findCart(cartId);
//        Order userOrder = new Order();
//        userOrder.setOrderId(orderSequnceGeneratorServ.generateSequence(Order.SEQUENCE_NAME));
//        BeanUtils.copyProperties(userCart, userOrder);
//        userCart.setCartItems(new ArrayList<>());
//        //cartService.deleteCartById(cartId);
//        cartService.addCart(userCart);
//        return new ResponseEntity<>(orderService.addOrder(userOrder), HttpStatus.OK);

        Cart userCart = cartService.findCart(cartId);
        Order userOrder = new Order();
        List<CartItem> orderItems = new ArrayList<>();
        Double totalCost = 0.0;
        for(CartItem cartItem : userCart.getCartItems()){
            totalCost += cartItem.getQuantity()*cartItem.getProductPrice();
            orderItems.add(cartItem);
        }
        //BeanUtils.copyProperties(userCart, userOrder);
        userOrder.setCartItems(orderItems);
        userOrder.setTotalOrderCost(totalCost);
        userOrder.setCustomer(userCart.getCustomer());

        userCart.setCartItems(new ArrayList<>());
        cartService.addCart(userCart);
        userOrder.setOrderId(orderSequnceGeneratorServ.generateSequence(Order.SEQUENCE_NAME));

        return new ResponseEntity<>(orderService.addOrder(userOrder), HttpStatus.OK);
    }

    @GetMapping("/history/{email}")
    public ResponseEntity<List<Order>> orderHistory(@PathVariable("email") String cartId){
        return new ResponseEntity<>(orderService.findByEmail(cartId), HttpStatus.OK);
    }


}
