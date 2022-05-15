package com.example.prepitbackend.service.bl;

import java.util.ArrayList;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.dto.entities.CollectionMealDTO;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.dto.mappers.CollectionMapper;
import com.example.prepitbackend.dto.mappers.MealMapper;
import com.example.prepitbackend.service.dao.CollectionRepo;
import com.example.prepitbackend.service.dao.MealRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl implements CollectionService{

    @Autowired
    private CollectionRepo collectionRepo;

    @Autowired
    private MealService mealService;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private MealMapper mealMapper;

    public ArrayList<Collection> findAll(){
        ArrayList<Collection> result = collectionRepo.findAll();
        return result;
    }

    @Override
    public ArrayList<Collection> findByUser(User user) {
        ArrayList<Collection> result = collectionRepo.findByUser(user);
        return result;
    }

    @Override
    public Collection save(Collection collection) {
        return collectionRepo.save(collection);
        
    }

    @Override
    public Collection findById(Long id) {
        return collectionRepo.getById(id);
    }


    @Override
    public Collection saveMeal(CollectionMealDTO collectionMealDTO) {
        Collection collection = collectionMapper.toCollection(collectionMealDTO);

        // if dto mapper returned null -> collection has to be provided from database since it exists
        if (collection == null) {
            collection = this.findById(collectionMealDTO.getCollectionId());
        }

        MealDTO mealDto = collectionMealDTO.getMeal();
        
        Meal meal = this.getMeal(mealDto);

        collection.addMeal(meal);
        return collectionRepo.save(collection);
    }

    /**
     * Returns a meal form database, if it doesn't exist, then it inserts one and returns it afterwards
     * This is used to avoid duplication of Meals in database, since we don't have access to Meal id in front-end, we need to check for uniq_id
     * @param mealDto <code>MealDTO</code> instance containing a meal without an id specified
     * @return <code>Meal</code> instance from database if the meal exists, or a newly created one otherwise
     */
    private Meal getMeal(MealDTO mealDto){
        // check wether the meal is already in the database (this is where uniq_id is useful since we don't have access to actual id)
        Meal meal = mealService.findByUniqId(mealDto.getUniq_id());

        // if the meal was not already inserted into db, we need to insert it before updating the collection
        if(meal == null) {
            Meal mealToSave = mealMapper.toMeal(mealDto);
            return mealService.save(mealToSave);
        }

        return meal;
    }

}
