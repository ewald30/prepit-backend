package com.example.prepitbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class PrepitBackendApplication {

    @Autowired
    private static JavaMailSender mailSender;

    public static void main(String[] args) {
        SpringApplication.run(PrepitBackendApplication.class, args);
        //sendMail();
    }

    private static void sendMail(){
        String recipientAddress = "berla.ewald30@gmail.com";
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = "http://localhost:8080/PrepIt" + "/regitrationConfirm.html?token=" + "123";
        String message = "Confirmation Link";
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }

}
