package com.wildessilva.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.wildessilva.cursomc.domain.Pedido;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);
    
    void sendEmail(SimpleMailMessage msg);
}
