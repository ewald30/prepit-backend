package com.example.prepitbackend.rest;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.dto.entities.CollectionDTO;
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


    @GetMapping("/findByUser")
    public ResponseEntity<Object> getForUser(@RequestParam("userId") Long userId){
        User user = userService.getUserById(userId);
        return renderResponse(collectionService.findByUser(user));

    }

    
    @GetMapping("/findAll")
    public ResponseEntity<Object> getAll(){
        return renderResponse(collectionService.findAll());
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insert(@RequestBody CollectionDTO collectionDto){
        Collection collection = mapper.toCollection(collectionDto);
        return renderResponse(collectionService.save(collection));
    }

}
