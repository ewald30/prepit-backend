package com.example.prepitbackend.service.bl;

import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.domain.VerificationToken;
import com.example.prepitbackend.dto.UserRegisterDTO;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    
    public User getUserByUsername(String username);

    public User loadUserByUsername(String username);

    public User registerNewUser(UserRegisterDTO userDto);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

}
