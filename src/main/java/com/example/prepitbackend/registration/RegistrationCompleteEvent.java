package com.example.prepitbackend.registration;

import java.util.Locale;

import com.example.prepitbackend.domain.User;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent{
    
    private String appUrl;
    private Locale locale;
    private User user;

    
    public RegistrationCompleteEvent(final User user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
