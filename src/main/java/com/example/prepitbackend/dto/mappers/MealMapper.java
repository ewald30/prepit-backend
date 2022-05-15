package com.example.prepitbackend.dto.mappers;

import java.util.HashSet;
import java.util.Set;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.entities.CollectionDTO;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.dto.entities.MealDTO;
import org.springframework.stereotype.Component;

@Component
public class MealMapper {
    
    public Meal toMeal(MealDTO mealDTO){
        return new Meal(
            null,
            mealDTO.getTitle(),
            mealDTO.getDescription(),
            mealDTO.getInstructions(),
            mealDTO.getNutritions_info(),
            mealDTO.getIngredients(),
            mealDTO.getCalories(),
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
            mealDTO.getPublished_date(),
            mealDTO.getUniq_id(),
            new HashSet<Collection>()
        );
    }

    public MealDTO toDto(Meal meal) {
        Set<CollectionDTO> belongsTo = new HashSet<CollectionDTO>();
        for(Collection collection : meal.getBelongsTo()) {
            CollectionDTO collectionDto = new CollectionDTO(collection);
            belongsTo.add(collectionDto);
       }
       MealDTO mealSaveDTO = new MealDTO(meal);
        mealSaveDTO.setBelongsTo(belongsTo);
        return mealSaveDTO;
    }

}
