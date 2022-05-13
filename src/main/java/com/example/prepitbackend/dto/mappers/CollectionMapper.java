package com.example.prepitbackend.dto.mappers;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.dto.entities.CollectionDTO;
import com.example.prepitbackend.service.bl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollectionMapper {

    @Autowired
    private UserService userService;

    public CollectionDTO toDto(Collection collection) {
        return new CollectionDTO(
            collection.getName(), 
            collection.getDescription(),
            collection.getUser().getId());
    }

    public Collection toCollection(CollectionDTO collectionDto){
        User user = userService.getUserById(collectionDto.getUserId());
        return new Collection(null,
            collectionDto.getName(),
            collectionDto.getDescription(),
            user);
    }
    
}
