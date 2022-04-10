package com.example.prepitbackend.rest;

import java.security.Principal;

import com.example.prepitbackend.dto.UserInfoDTO;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.service.bl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestService extends BaseService {

    @Autowired 
    UserService userService;

    @GetMapping(value ="/info", produces = "application/json")
    public ResponseEntity<Object> getUserInfo(Principal user){
        User userObj = userService.loadUserByUsername(user.getName()) ;       
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        
        userInfoDTO.setId(userObj.getId());
        userInfoDTO.setUsername(userObj.getUsername());
        userInfoDTO.setEmail(userObj.getEmail());
        userInfoDTO.setFirstName(userObj.getFirstName());
        userInfoDTO.setLastName(userObj.getLastName());
        userInfoDTO.setWeight(userObj.getWeight());
        userInfoDTO.setHeight(userObj.getHeight());
        userInfoDTO.setAge(userObj.getAge());
        userInfoDTO.setActivityType(userObj.getActivityType());
        userInfoDTO.setGender(userObj.getGender());
        userInfoDTO.setRoles(userObj.getAuthorities().toArray());

        return this.renderResponse(userInfoDTO);
    }
    
}
