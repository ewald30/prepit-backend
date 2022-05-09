package com.example.prepitbackend.registration;

import java.util.UUID;

import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.service.bl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${application.verification.url}")
    private String applicationUrl;

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
        String confirmationUrl  = event.getAppUrl() + "/auth/verify?token=" + token;
        String message = "Confirmation Link";
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + applicationUrl+ confirmationUrl);
        mailSender.send(email);
    }
    
}
