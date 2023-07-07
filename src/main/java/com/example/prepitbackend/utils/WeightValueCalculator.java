package com.example.prepitbackend.utils;

public class WeightValueCalculator {
    public static Double calculate(int calories, int priceScore, int timeScore, Double priceScoreMultiplier, Double timeScoreMultiplier, Double caloriesMultiplier) {
        Double caloriesNormalized = Scaling.logScaling(calories);
        Double priceScoreNormalized = Scaling.minMaxScaling(0, 5, priceScore);
        Double timeScoreNormalized = Scaling.minMaxScaling(0, 5, timeScore);

        return ((caloriesNormalized*caloriesMultiplier) + 0 + (timeScoreMultiplier * timeScoreNormalized));
    }

    public static Double calculate(int nbMatchedIngredients, int nbIngredients){
        // rewrite this to something like: nbIngredients - nbMatchedIngredients (this means that 0 is the biggest value)
        return 9.0;
    }
}
