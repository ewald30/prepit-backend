package com.example.prepitbackend.service.bl;

import com.example.prepitbackend.domain.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    
    public User getUserByUsername(String username);

    public UserDetails loadUserByUsername(String username);

}
