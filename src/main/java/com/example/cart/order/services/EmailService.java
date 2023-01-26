package com.example.cart.order.services;

import com.example.cart.order.entities.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);
}
