package com.example.prepitbackend.service.dao;

import java.util.List;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.MealDTO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepo extends JpaRepository<Meal, Long>, MealJSONRepo {
    List<MealDTO> readAll();
}
