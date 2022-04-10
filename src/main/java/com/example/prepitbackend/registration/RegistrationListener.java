package com.example.prepitbackend.registration;

import java.util.UUID;

import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.service.bl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Component
public class RegistrationListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService service;
 
    @Autowired
    private MessageSource messages;
 
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        this.confirmRegistration(event);
        
    }

    
    private void confirmRegistration(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);
        
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
        String message = "Confirmation Link";
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
    
}
