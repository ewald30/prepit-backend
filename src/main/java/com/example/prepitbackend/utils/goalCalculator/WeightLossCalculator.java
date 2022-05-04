package com.example.prepitbackend.utils.goalCalculator;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WeightLossCalculator implements GoalTierCalculator{

    @Override
    public Double calculateCalories(int percentage, Double TDEE) {
        Double value = (percentage * TDEE)/100;
        return TDEE - value;
    }
}
