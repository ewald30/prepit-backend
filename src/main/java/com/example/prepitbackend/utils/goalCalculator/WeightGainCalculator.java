package com.example.prepitbackend.utils.goalCalculator;

public class WeightGainCalculator implements CalorieGoalCalculator{

    @Override
    public Double calculateCalories(int percentage, Double TDEE) {
        Double value = (percentage * TDEE)/100;
        return TDEE + value;
    }
    
}
