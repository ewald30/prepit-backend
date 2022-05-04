package com.example.prepitbackend.utils.goalCalculator;

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

    private int calculatePercentage(){
        switch (goalTier){
            case FIRST_GOAL_TIER:
                return 11;
            case SECOND_GOAL_TIER:
                return 22;
        } 
        return 0;
    }

    public Double calculate(Double TDEE){
        int percentage = this.calculatePercentage();
        Double calories = this.goalTierCalculator.calculateCalories(percentage, TDEE);
        return calories;
    }

}
