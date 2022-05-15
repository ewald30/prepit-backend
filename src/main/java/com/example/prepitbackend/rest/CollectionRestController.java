package com.example.prepitbackend.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.dto.entities.CollectionDTO;
import com.example.prepitbackend.dto.entities.CollectionMealDTO;
import com.example.prepitbackend.dto.mappers.CollectionMapper;
import com.example.prepitbackend.service.bl.CollectionService;
import com.example.prepitbackend.service.bl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.models.Response;

@RestController
@RequestMapping("/collection")
@CrossOrigin
public class CollectionRestController extends BaseService{
    
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private UserService userService;

    @Autowired 
    private CollectionMapper mapper;


    @GetMapping("/find-by-user")
    public ResponseEntity<Object> findByUser(@RequestParam("id") Long userId){
        User user = userService.getUserById(userId);
        List<CollectionDTO>  collections = collectionService.findByUser(user).stream().map(collection -> mapper.toDto(collection)).collect(Collectors.toList());
        return renderResponse(collections);

    }

    
    @GetMapping("/find-all")
    public ResponseEntity<Object> findAll(){
        List<CollectionDTO> collections = collectionService.findAll().stream().map(collection -> mapper.toDto(collection)).collect(Collectors.toList());
        return renderResponse(collections);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Object> findById(@RequestParam("id") Long id){
        Collection collection = collectionService.findById(id);
        return renderResponse(mapper.toDto(collection));
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insert(Principal user, @RequestBody CollectionDTO collectionDto){
        User userObj = userService.loadUserByUsername(user.getName());  // get user from auth header
        collectionDto.setUserId(userObj.getId());
        Collection collection = mapper.toCollection(collectionDto);
        return renderResponse(mapper.toDto(collectionService.save(collection)));
    }

    @PostMapping("/save-meal")
    public ResponseEntity<Object> saveMeal(Principal user, @RequestBody CollectionMealDTO collectionMealDTO){
        User userObj = userService.loadUserByUsername(user.getName());  // get user from auth header
        collectionMealDTO.setUserId(userObj.getId());
        Collection collection = collectionService.saveMeal(collectionMealDTO);
        CollectionDTO collectionDTO = mapper.toDto(collection);
        return renderResponse(collectionDTO);
    }

}
