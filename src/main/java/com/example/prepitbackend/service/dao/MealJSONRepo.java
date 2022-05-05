package com.example.prepitbackend.service.dao;

import java.util.List;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.MealDTO;

import org.json.simple.JSONArray;

public interface MealJSONRepo {
    public JSONArray readAllJSON();
}
