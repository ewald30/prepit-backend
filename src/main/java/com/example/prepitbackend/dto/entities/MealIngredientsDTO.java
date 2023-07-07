package com.example.prepitbackend.dto.entities;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MealIngredientsDTO {

    private Set<String> ingredients;
    private Integer caloricThreshold;
}
