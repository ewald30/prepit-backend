package com.example.prepitbackend.rest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.example.prepitbackend.auth.JWTTokenHelper;
import com.example.prepitbackend.dao.UserInfoDao;
import com.example.prepitbackend.dao.UserRegisterDao;
import com.example.prepitbackend.domain.AuthRequest;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.service.bl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object> register(@RequestBody UserRegisterDao user){
        System.out.println(user);
        return renderResponse("register");
    }
    
}
