package com.example.prepitbackend.service.bl;

import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private UserRepo repository;

    @Override
    public User getUserByUsername(String username) {
        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        
        return user;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        System.out.println("Logged in: " + user.toString());
        return user;
    }

}