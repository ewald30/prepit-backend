package com.example.prepitbackend.utils.caloricSplitter;

import java.util.ArrayList;

import com.example.prepitbackend.dto.entities.MealAlgorithmDTO;

import lombok.Getter;
import lombok.Setter;

/**
 *  This class will split a given number of calories into a given number of meals
 */

@Setter
@Getter
public class CaloricSplitter {

    private MealSplitter mealSplitter;
    private Double priceMultiplier;
    private Double timeMultiplier;
    private Double caloriesAccuracyMultiplier;

    public CaloricSplitter(int numberOfMeals, Double priceMultiplier, Double timeMultiplier, Double caloriesAccuracyMultiplier){
        switch (String.valueOf(numberOfMeals)){
            case "3":
                mealSplitter = new ThreeMealsSplitter();
                break;
            case "4":
                mealSplitter = new FourMealsSplitter();
                break;
            case "5":
                mealSplitter = new FiveMealsSplitter();
                break;
        }
        this.priceMultiplier = priceMultiplier;
        this.timeMultiplier = timeMultiplier;
        this.caloriesAccuracyMultiplier = caloriesAccuracyMultiplier;
    }

    /***
     * Will create a list of meal dtos used by the algorithm to search for
     * @param numberOfMeals - number of meals to search for
     * @param TDEE - Total Daily Energy Expenditure (total calories available in a day)
     * @return <code>List</code> of meals dto
     */
    public ArrayList<MealAlgorithmDTO> split(int numberOfMeals, Double TDEE) {
        ArrayList<MealAlgorithmDTO> meals = new ArrayList<MealAlgorithmDTO>();

        MealAlgorithmDTO breakfast = createMeal("breakfast", mealSplitter.getBreakfast(TDEE));
        MealAlgorithmDTO lunch = createMeal("lunch", mealSplitter.getLunch(TDEE));
        MealAlgorithmDTO dinner = createMeal("dinner", mealSplitter.getDinner(TDEE));

        meals.add(breakfast);
        meals.add(lunch);
        meals.add(dinner);

        if (numberOfMeals == 4){
            MealAlgorithmDTO snack1 = createMeal("snack", mealSplitter.getSnack(TDEE));
            meals.add(snack1);
        } else if (numberOfMeals == 5){
            MealAlgorithmDTO snack1 = createMeal("snack", mealSplitter.getSnack(TDEE));
            MealAlgorithmDTO snack2 = createMeal("snack", mealSplitter.getSnack(TDEE));
            meals.add(snack1);
            meals.add(snack2);
        }
        
        return meals;

    }

    /**
     * Will create a meal DTO used by the algorithm to know what to search for
     * @param type - type of the meal (lunch, dinner, etc.) 
     * @param calories - number of calories of the meal
     * @return <code>MealDTO</code> instance used by the suggestion algorithm
     */
    private MealAlgorithmDTO createMeal(String type, int calories){
        switch(type){ //todo refactor this code to return only one value (remove switch)
            case "breakfast":
                return new MealAlgorithmDTO(calories, 5, 5, "breakfast", priceMultiplier, timeMultiplier, caloriesAccuracyMultiplier);
            case "lunch":
                return new MealAlgorithmDTO(calories, 5, 2, "lunch", priceMultiplier, timeMultiplier, caloriesAccuracyMultiplier);
            case "dinner":
                return new MealAlgorithmDTO(calories, 5, 2, "dinner", priceMultiplier, timeMultiplier, caloriesAccuracyMultiplier);
            case "snack":
                return new MealAlgorithmDTO(calories, 5, 5, "snack", priceMultiplier, timeMultiplier, caloriesAccuracyMultiplier);
        }
        
        return null;
    }


}
