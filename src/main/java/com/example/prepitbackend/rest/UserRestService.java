package com.example.prepitbackend.rest;

import java.security.Principal;

import com.example.prepitbackend.dao.UserInfoDao;
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
        UserInfoDao userInfoDao = new UserInfoDao();
        
        userInfoDao.setId(userObj.getId());
        userInfoDao.setUsername(userObj.getUsername());
        userInfoDao.setEmail(userObj.getEmail());
        userInfoDao.setFirstName(userObj.getFirstName());
        userInfoDao.setLastName(userObj.getLastName());
        userInfoDao.setWeight(userObj.getWeight());
        userInfoDao.setHeight(userObj.getHeight());
        userInfoDao.setAge(userObj.getAge());
        userInfoDao.setActivityType(userObj.getActivityType());
        userInfoDao.setGender(userObj.getGender());
        userInfoDao.setRoles(userObj.getAuthorities().toArray());

        return this.renderResponse(userInfoDao);
    }
    
}
