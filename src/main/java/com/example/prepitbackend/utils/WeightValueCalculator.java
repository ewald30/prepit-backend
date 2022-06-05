package com.example.prepitbackend.utils;

public class WeightValueCalculator {
    public static Double calculate(int calories, int priceScore, int timeScore, Double priceScoreMultiplier, Double timeScoreMultiplier, Double caloriesMultiplier) {
        Double caloriesNormalized = Scaling.logScaling(calories);
        Double priceScoreNormalized = Scaling.minMaxScaling(0, 5, priceScore);
        Double timeScoreNormalized = Scaling.minMaxScaling(0, 5, timeScore);

        return ((caloriesNormalized*caloriesMultiplier) + (priceScoreMultiplier * priceScoreNormalized) + (timeScoreMultiplier * timeScoreNormalized));
    }
}
