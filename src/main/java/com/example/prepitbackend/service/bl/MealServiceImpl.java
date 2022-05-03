package com.example.prepitbackend.service.bl;

import java.util.List;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.service.dao.MealRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    MealRepo mealRepo;

    @Override
    public List<Meal> geAll() {
        // get all from the database
        
        // get all from the JSON file

        // merge these two together
        mealRepo.readAll();

        return null;
    }
    
}
