package com.example.prepitbackend.service.dao;

import java.util.List;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.MealDTO;

public interface MealJSONRepo {
    public List<MealDTO> readAll();
}
