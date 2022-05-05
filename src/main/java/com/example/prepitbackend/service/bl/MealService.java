package com.example.prepitbackend.service.bl;

import java.util.ArrayList;
import java.util.List;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.MealDTO;
import com.example.prepitbackend.dto.UserMeasurementsDTO;

public interface MealService {
    ArrayList<MealDTO> geAll();
    ArrayList<ArrayList<MealDTO>> generateForADay(UserMeasurementsDTO entity);
}
