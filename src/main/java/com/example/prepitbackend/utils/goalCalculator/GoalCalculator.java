package com.example.prepitbackend.utils.goalCalculator;

import java.util.Random;

import com.example.prepitbackend.domain.Goal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalCalculator {
    
    private Goal goal;
    private String goalTier;
    private GoalTierCalculator goalTierCalculator;

    private static final String FIRST_GOAL_TIER = "0.25";
    private static final String SECOND_GOAL_TIER = "0.5";

    private static final int MIN_RANDOMIZE_VALUE = -10;
    private static final int MAX_RANDOMIZE_VALUE = -10;

    private static final int FIRST_GOAL_TIER_PERCENTAGE = 11;
    private static final int SECOND_GOAL_TIER_PERCENTAGE = 22;


    public GoalCalculator(Goal goal, String goalTier) {
        this.goalTier = goalTier;
        this.goal = goal;

        switch (goal) {
            case LOSE:
                goalTierCalculator = new WeightLossCalculator();
                break;
            case GAIN:
                goalTierCalculator = new WeightGainCalculator();
                break;
        }

    }

    /**
     * Calculates the value of calories for a given goal
     * @param TDEE - Total Daily Energy Expenditure
     * @return <code>double</code> value of calories for a given goal
     */
    public Double calculate(Double TDEE){
        int percentage = this.calculatePercentage();
        Double calories = this.goalTierCalculator.calculateCalories(percentage, TDEE);
        return this.randomize(calories);
    }

    /**
     * Calculates the percentage for each given goal
     * @return <code>Double</code> the percentage for each goal
     */
    private int calculatePercentage(){
        switch (goalTier){
            case FIRST_GOAL_TIER:
                return FIRST_GOAL_TIER_PERCENTAGE;
            case SECOND_GOAL_TIER:
                return SECOND_GOAL_TIER_PERCENTAGE;
        } 
        return 0;
    }

    /**
     * Since a difference of +-10 calories for a day is not that big, this function is used to create results that differ more
     * @param TDEE - Total Daily Energy Expenditure: this will be altered by either adding or removing 10 
     * @return <code>double</code> representing the modified value of TDEE
     */
    private Double randomize(Double TDEE){
        Random random = new Random();
        int value = random.nextInt((MAX_RANDOMIZE_VALUE - MIN_RANDOMIZE_VALUE) + 1) + MIN_RANDOMIZE_VALUE;
        return TDEE + value;
    }

}
