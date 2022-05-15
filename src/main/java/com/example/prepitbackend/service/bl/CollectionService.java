package com.example.prepitbackend.service.bl;

import java.util.ArrayList;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.dto.entities.CollectionMealDTO;

public interface CollectionService {
    public ArrayList<Collection> findAll();
    public Collection findById(Long id);
    public ArrayList<Collection> findByUser(User user);
    public Collection save(Collection collection);
    public Collection saveMeal(CollectionMealDTO collectionMealDTO);
}
