package com.example.prepitbackend.dto.entities;

import com.example.prepitbackend.domain.Meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionMealDTO {
    private Long collectionId;
    private Long userId;
    private String collectionName;
    private String collectionDescription;
    private MealDTO meal;
}
