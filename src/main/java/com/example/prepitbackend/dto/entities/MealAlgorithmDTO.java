package com.example.prepitbackend.dto.entities;

import com.example.prepitbackend.utils.WeightValueCalculator;

import lombok.Getter;

@Getter
public class MealAlgorithmDTO {
    private Double weight;  
    private String category;
    private int calories;

    public MealAlgorithmDTO(int calories, int priceScore, int timeScore, String category, Double priceScoreMultiplier, Double timeScoreMultiplier, Double calorieAccuracyMultiplier) {
        this.category = category;
        this.calories = calories;
        this.weight = WeightValueCalculator.calculate(calories, priceScore, timeScore, priceScoreMultiplier, timeScoreMultiplier, calorieAccuracyMultiplier);
    }

}
