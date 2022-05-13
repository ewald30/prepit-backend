package com.example.prepitbackend.service.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.MealDTO;

import org.json.simple.JSONArray;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Repository for meal recipes
 */
public interface MealRepo extends JpaRepository<Meal, Long>, MealJSONRepo {
    JSONArray readAllJSON();
}
