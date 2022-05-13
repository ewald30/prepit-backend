package com.example.prepitbackend.service.bl;

import java.util.ArrayList;
import java.util.List;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.dto.entities.UserMeasurementsDTO;

public interface MealService {
    public ArrayList<MealDTO> geAll();
    public ArrayList<ArrayList<MealDTO>> generateForADay(UserMeasurementsDTO entity);
    public Meal save(Meal meal);
}
