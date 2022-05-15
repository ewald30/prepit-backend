package com.example.prepitbackend.dto.mappers;

import java.util.HashSet;
import java.util.Set;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.dto.entities.CollectionDTO;
import com.example.prepitbackend.dto.entities.CollectionMealDTO;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.service.bl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollectionMapper {

    @Autowired
    private UserService userService;

    public CollectionDTO toDto(Collection collection) {
        Set<MealDTO> containedMeals = new HashSet<MealDTO>();
        for(Meal meal : collection.getContainedMeals()) {
            MealDTO mealDTO = new MealDTO(meal);
            containedMeals.add(mealDTO);
       }

       CollectionDTO collectionDTO = new CollectionDTO(collection);
       collectionDTO.setContainedMeals(containedMeals);
       return collectionDTO;
    }

    public Collection toCollection(CollectionDTO collectionDto){
        User user = userService.getUserById(collectionDto.getUserId());
        return new Collection(null,
            collectionDto.getName(),
            collectionDto.getDescription(),
            user, new HashSet<>());
    }

    /**
     * Used for avoiding duplicated code in service (save for existing collection + for non-existing collection)
     * Returns a collection based on the specified parameters, if an id is provided, it returns null and the collection has to be provided from db
     * Otherwise returns a new collection with a new hasSet, this will be further inserted into the database
     * @param collectionMealDTO - <code>CollectionMealDTO</code> instance containing information about a collection and a meal to insert 
     * @return <code>Collection</code> instance, a new collection if an id is not specified, or null if an id is specified
     */
    public Collection toCollection(CollectionMealDTO collectionMealDTO){
        // if a collection id was specified then we return the existing collection from database
        if (collectionMealDTO.getCollectionId() != null) {
            return null;
        }

        // otherwise we return a new collection for storing it
        User user = userService.getUserById(collectionMealDTO.getUserId());
        return new Collection(null,
            collectionMealDTO.getCollectionName(),
            collectionMealDTO.getCollectionDescription(),
            user, new HashSet<>());
    }

 }
    

