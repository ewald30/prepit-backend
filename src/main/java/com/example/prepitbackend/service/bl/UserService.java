package com.example.prepitbackend.service.bl;

import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.domain.VerificationToken;
import com.example.prepitbackend.dto.entities.UserInfoDTO;
import com.example.prepitbackend.dto.entities.UserMeasurementsDTO;
import com.example.prepitbackend.dto.entities.UserRegisterDTO;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    
    public User getUserByUsername(String username);

    public User getUserById(Long id);

    public User loadUserByUsername(String username);

    public User registerNewUser(UserRegisterDTO userDto);

    public User updateMeasurements(UserMeasurementsDTO userDto);
    
    public User update(UserInfoDTO userDto);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

}
