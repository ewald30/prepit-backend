package com.example.prepitbackend.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestService extends BaseService {

    @GetMapping(value ="/", produces = "application/json")
    public ResponseEntity<Object> getUsers(){
        return renderResponse("hiii");
    }
    
}
