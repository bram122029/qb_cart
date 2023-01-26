package com.example.cart.order.services.ServImpl;

import com.example.cart.order.entities.Cart;
import com.example.cart.order.entities.CartItem;
import com.example.cart.order.entities.Stock;
import com.example.cart.order.repository.CartRepository;
import com.example.cart.order.services.CartFeignService;
import com.example.cart.order.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartFeignService cartFeignService;

    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findCart(String cartId) {
        Optional<Cart> myCart = cartRepository.findById(cartId);
        if(myCart.isPresent()){
            return myCart.get();
        }
        return null;
    }

    @Override
    public List<CartItem> addToCart(String cartId, CartItem cartItem) {

        Stock stock = cartFeignService.reduceStock(cartItem.getSKU_ID(), String.valueOf(cartItem.getQuantity()));
        if(stock == null)
            return null;

        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        List<CartItem> list = optionalCart.get().getCartItems();
        if(optionalCart.isPresent()) {
            //list = optionalCart.get().getCartItems();
            //System.out.println(list);
            if(list.size() > 0) {
                for (CartItem cartItem1 : list) {
                    if (cartItem1.getSKU_ID().equals(cartItem.getSKU_ID())) {
                        double qty = optionalCart.get().getCartItems().get(optionalCart.get().getCartItems().indexOf(cartItem1)).getQuantity();
                        optionalCart.get().getCartItems().get(optionalCart.get().getCartItems().indexOf(cartItem1)).setQuantity(qty + cartItem.getQuantity());
                        cartRepository.save(optionalCart.get());
                        return cartRepository.findById(cartId).get().getCartItems();
                    }

                }
            }
        }
        optionalCart.get().getCartItems().add(cartItem);
        cartRepository.save(optionalCart.get());
        return cartRepository.findById(cartId).get().getCartItems();
    }

    @Override
    public String deleteCartById(String cartId) {
        if(cartRepository.existsById(cartId)){
            cartRepository.deleteById(cartId);
            return "Deleted Successfully";
        }
        return "Unsuccessful deletion";
    }

    @Override
    public List<CartItem> findAllCartItems(String cartId) {
        if(cartRepository.existsById(cartId)) {
            return cartRepository.findById(cartId).get().getCartItems();
        }
        //return cartRepository.findAllCartItemByCartId(cartId);
        return null;
    }

    @Override
    public Boolean deleteAllCartItems(String cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if(cartOptional.isPresent()){
            List<CartItem> cartItems = cartOptional.get().getCartItems();
            for(CartItem cartItem : cartItems) {
                Stock stock = cartFeignService.reduceStock(cartItem.getSKU_ID(), "-" + String.valueOf(cartItem.getQuantity()));
//                if (stock == null)
//                    return null;
            }

            cartOptional.get().setCartItems(new ArrayList<CartItem>());
            cartRepository.save(cartOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public List<CartItem> deleteOneCartItem(String cartId, String skuId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        CartItem singleCartItem = new CartItem();
        if(cartOptional.isPresent()){
            List<CartItem> cartItems = cartOptional.get().getCartItems();
            for(CartItem cartItem : cartItems){
                if(cartItem.getSKU_ID().equals(skuId)){
                    singleCartItem = cartItem;
                    if(cartItem.getQuantity() > 1){
                        //cartItem.setQuantity(cartItem.getQuantity()-1);
                        cartOptional.get().getCartItems().get(cartOptional.get().getCartItems().indexOf(cartItem)).setQuantity(cartItem.getQuantity()-1);
                        cartRepository.save(cartOptional.get());
                        return cartRepository.findById(cartId).get().getCartItems();
                    }
                }
            }
            cartOptional.get().getCartItems().remove(cartOptional.get().getCartItems().indexOf(singleCartItem));
            cartRepository.save(cartOptional.get());
            return cartRepository.findById(cartId).get().getCartItems();
        }
        return null;
    }

    public List<Cart> findAll(){
        return cartRepository.findAll();
    }

}
