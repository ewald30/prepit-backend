package com.example.prepitbackend.utils.caloricSplitter;

import java.util.ArrayList;

import com.example.prepitbackend.dto.MealAlgorithmDTO;

import lombok.Getter;
import lombok.Setter;

/**
 *  This class will split a given number of calories into a given number of meals
 */

@Setter
@Getter
public class CaloricSplitter {

    private static MealSplitter mealSplitter;

    /***
     * Will create a list of meal dtos used by the algorithm to search for
     * @param numberOfMeals - number of meals to search for
     * @param TDEE - Total Daily Energy Expenditure (total calories available in a day)
     * @return <code>List</code> of meals dto
     */
    public static ArrayList<MealAlgorithmDTO> split(int numberOfMeals, Double TDEE, Double priceScorePercent, Double timeScorePercent){
        ArrayList<MealAlgorithmDTO> meals = new ArrayList<MealAlgorithmDTO>();

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

        MealAlgorithmDTO breakfast = createMeal("breakfast", mealSplitter.getBreakfast(TDEE), priceScorePercent, timeScorePercent);
        MealAlgorithmDTO lunch = createMeal("lunch", mealSplitter.getLunch(TDEE), priceScorePercent, timeScorePercent);
        MealAlgorithmDTO dinner = createMeal("dinner", mealSplitter.getDinner(TDEE), priceScorePercent, timeScorePercent);

        meals.add(breakfast);
        meals.add(lunch);
        meals.add(dinner);

        if (numberOfMeals == 4){
            MealAlgorithmDTO snack1 = createMeal("snack", mealSplitter.getSnack(TDEE), priceScorePercent, timeScorePercent);
            meals.add(snack1);
        } else if (numberOfMeals == 5){
            MealAlgorithmDTO snack1 = createMeal("snack", mealSplitter.getSnack(TDEE), priceScorePercent, timeScorePercent);
            MealAlgorithmDTO snack2 = createMeal("snack", mealSplitter.getSnack(TDEE), priceScorePercent, timeScorePercent);
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
    private static MealAlgorithmDTO createMeal(String type, int calories, Double priceScorePercent, Double timeScorePercent){
        switch(type){
            case "breakfast":
                return new MealAlgorithmDTO(calories, 5, 5, "breakfast", priceScorePercent, timeScorePercent);
            case "lunch":
                return new MealAlgorithmDTO(calories, 5, 5, "lunch", priceScorePercent, timeScorePercent);
            case "dinner":
                return new MealAlgorithmDTO(calories, 5, 5, "dinner", priceScorePercent, timeScorePercent);
            case "snack":
                return new MealAlgorithmDTO(calories, 5, 5, "snack", priceScorePercent, timeScorePercent);
        }
        
        return null;
    }


}
