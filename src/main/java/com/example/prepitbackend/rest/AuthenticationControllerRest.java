package com.example.prepitbackend.rest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.prepitbackend.auth.JWTTokenHelper;
import com.example.prepitbackend.dto.UserRegisterDTO;
import com.example.prepitbackend.registration.RegistrationCompleteEvent;
import com.example.prepitbackend.domain.AuthRequest;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.domain.VerificationToken;
import com.example.prepitbackend.service.bl.UserService;
import com.example.prepitbackend.validation.exceptions.UserAlreadyExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationControllerRest extends BaseService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest authRequest) throws InvalidKeySpecException, NoSuchAlgorithmException{
        final Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken (
                authRequest.getUsername(),
                authRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtTokenHelper.generateToken(user.getUsername());

        return renderResponse(jwtToken);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRegisterDTO userDto, HttpServletRequest request) {
        try{
            User newUser = userService.registerNewUser(userDto);
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new RegistrationCompleteEvent(newUser, request.getLocale(), appUrl));
        } catch (UserAlreadyExistException e){
            return renderServerError(e.getMessage());
        } catch (RuntimeException e){
            throw e;
        }

        return renderResponse(userDto); //maybe make this return the user with the given id
    }

    @GetMapping("/verify")
    public ResponseEntity<Object> verify(WebRequest request,  @RequestParam("token") String token){
        VerificationToken verificationToken = userService.getVerificationToken(token);

        if (verificationToken == null){
            return renderServerError("Invalid token");
        }

        User user = verificationToken.getUser();
        if (verificationToken.verify(Calendar.getInstance())){
            return renderServerError("Token Expired");
        }

        user.setEnabled(true); 
        userService.saveRegisteredUser(user); 
        return renderResponse("Success! \n Please go back and login!");
    }
    
}
