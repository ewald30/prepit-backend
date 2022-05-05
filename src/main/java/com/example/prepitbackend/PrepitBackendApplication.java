package com.example.prepitbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class PrepitBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrepitBackendApplication.class, args);
        //sendMail();
    }

}
