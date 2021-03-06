package com.example.prepitbackend.utils.goalCalculator;

public interface CalorieGoalCalculator {

    /**
     * Calculates the calories for a given goal and a given TDEE
     * @param percentage - the percentage of the goal to calculate
     * @param TDEE - Total Daily Energy Expenditure
     * @return <code>double</code> the modified value for the given TDEE and goal
     */
    public Double calculateCalories(int percentage, Double TDEE);
}
