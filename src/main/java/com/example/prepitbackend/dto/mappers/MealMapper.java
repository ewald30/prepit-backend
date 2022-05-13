package com.example.prepitbackend.dto.mappers;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.dto.entities.MealSaveDTO;

import org.springframework.stereotype.Component;

@Component
public class MealMapper {
    public Meal toMeal(MealSaveDTO mealDTO){
        return new Meal(
            null,
            mealDTO.getTitle(),
            mealDTO.getDescription(),
            mealDTO.getInstructions(),
            mealDTO.getNutritions_info(),
            mealDTO.getIngredients(),
            this.getCalories(mealDTO.getNutritions_info()),
            mealDTO.getType(),
            mealDTO.getServing(),
            mealDTO.getImage(),
            mealDTO.getUrl(),
            mealDTO.getPrice_score(),
            mealDTO.getTime_score(),
            mealDTO.getPrep_time(),
            mealDTO.getCook_time(),
            mealDTO.getTotal_time(),
            mealDTO.getTotal_ratings(),
            mealDTO.getKeywords(),
            mealDTO.getAuthor(),
            mealDTO.getSource(),
            mealDTO.getCrawled_at(),
            mealDTO.getPublished_date() 
        );
    }

    private Integer getCalories(String info){
        String[] info_delimited = info.split(" \\| ");
        return Integer.parseInt(info_delimited[0].replaceAll("[^0-9]", ""));
    }

}
