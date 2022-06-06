package com.example.prepitbackend.service.bl;

import java.util.Optional;

import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.domain.VerificationToken;
import com.example.prepitbackend.dto.entities.UserInfoDTO;
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
        user.setAccuracyMultiplier(5.0);
        user.setPriceMultiplier(1.0);
        user.setTimeMultiplier(1.0);

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
    public User updateMeasurements(UserMeasurementsDTO userDto) {
        Optional<User> userById = repository.findById(userDto.getId());
        if (userById.isPresent()) {
            User user = userById.get();
            if (userDto.getAge() != 0)
                user.setAge(userDto.getAge());
            
            if (userDto.getGender() !='\0')
                user.setGender(Character.toString(userDto.getGender()));

            if (userDto.getHeight() != null)
                user.setHeight(userDto.getHeight());

            if (userDto.getWeight() != null)
                user.setWeight(userDto.getWeight());

            repository.save(user);
            return user;

        }
        return null;
    }

    @Override
    public User update(UserInfoDTO userDto) {
        Optional<User> userById = repository.findById(userDto.getId());
        if (userById.isPresent()) {
            User user = userById.get();
            
            if (userDto.getAge() != 0)
                user.setAge(userDto.getAge());
            
            if (userDto.getGender() !="")
                user.setGender(userDto.getGender());

            if (userDto.getHeight() != null)
                user.setHeight(userDto.getHeight());

            if (userDto.getWeight() != null)
                user.setWeight(userDto.getWeight());

            if (userDto.getPriceMultiplier() != null)
                user.setPriceMultiplier(userDto.getPriceMultiplier());

            if (userDto.getTimeMultiplier() != null)
                user.setTimeMultiplier(userDto.getTimeMultiplier());

            if (userDto.getAccuracyMultiplier() != null)
                user.setAccuracyMultiplier(userDto.getAccuracyMultiplier());

            if (userDto.getFirstName() != "")
                user.setFirstName(userDto.getFirstName());

            if (userDto.getLastName() != "")
                user.setLastName(userDto.getLastName());

            repository.save(user);
            return user;

        }
        return null;
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