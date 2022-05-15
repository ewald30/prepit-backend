package com.example.prepitbackend.service.bl;

import java.util.Optional;

import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.domain.VerificationToken;
import com.example.prepitbackend.dto.entities.UserMeasurementsDTO;
import com.example.prepitbackend.dto.entities.UserRegisterDTO;
import com.example.prepitbackend.service.dao.UserRepo;
import com.example.prepitbackend.service.dao.VerificationTokenRepo;
import com.example.prepitbackend.validation.exceptions.UserAlreadyExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private UserRepo repository;

    @Autowired
    private VerificationTokenRepo tokenRepository;

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

    @Override
    public User registerNewUser(UserRegisterDTO userDto) {
        if (repository.findByEmail(userDto.getEmail()) != null) {
            throw new UserAlreadyExistException("User " + userDto.getEmail() + "with mail " + userDto.getEmail() + " already exists");
        }

        final User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));

        return repository.save(user);
    }

    @Override
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
    
    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
    
    @Override
    public void saveRegisteredUser(User user) {
        repository.save(user);
    }
    
    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public void updateMeasurements(UserMeasurementsDTO userDto) {
        Optional<User> userById = repository.findById(userDto.getId());
        if (userById.isPresent()) {
            User user = userById.get();
            user.setAge(userDto.getAge());
            user.setGender(Character.toString(userDto.getGender()));
            user.setHeight(userDto.getHeight());
            user.setWeight(userDto.getWeight());
            repository.save(user);
        }
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userById = repository.findById(id);
        if (userById.isPresent()) {
            User user = userById.get();
            return user;
        }
        return null;
    }

}