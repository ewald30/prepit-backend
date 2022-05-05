package com.example.prepitbackend.utils.caloricSplitter;

import java.util.Random;

public class FourMealsSplitter implements MealSplitter{

    private static int MEALS_BREAKFAST_PERCENTAGE_START = 26;
    private static int MEALS_LUNCH_PERCENTAGE_START = 36;
    private static int MEALS_DINNER_PERCENTAGE_START = 26;
    private static int MEALS_SNACK_PERCENTAGE_START = 7;
    
    private static int MEALS_BREAKFAST_PERCENTAGE_END = 28;
    private static int MEALS_LUNCH_PERCENTAGE_END = 39;
    private static int MEALS_DINNER_PERCENTAGE_END = 29;
    private static int MEALS_SNACK_PERCENTAGE_END = 9;

    @Override
    public int getBreakfast(Double TDEE) {
        Random random = new Random();
        int percentage = random.nextInt((MEALS_BREAKFAST_PERCENTAGE_END - MEALS_BREAKFAST_PERCENTAGE_START) + 1) + MEALS_BREAKFAST_PERCENTAGE_START;
        Double result = (TDEE * percentage) / 100;
        return result.intValue();
    }

    @Override
    public int getLunch(Double TDEE) {
        Random random = new Random();
        int percentage = random.nextInt((MEALS_LUNCH_PERCENTAGE_END - MEALS_LUNCH_PERCENTAGE_START) + 1) + MEALS_LUNCH_PERCENTAGE_START;
        Double result = (TDEE * percentage) / 100;
        return result.intValue();
    }

    @Override
    public int getDinner(Double TDEE) {
        Random random = new Random();
        int percentage = random.nextInt((MEALS_DINNER_PERCENTAGE_END - MEALS_DINNER_PERCENTAGE_START) + 1) + MEALS_DINNER_PERCENTAGE_START;
        Double result = (TDEE * percentage) / 100;
        return result.intValue();
    }

    @Override
    public int getSnack(Double TDEE) {
        Random random = new Random();
        int percentage = random.nextInt((MEALS_SNACK_PERCENTAGE_END - MEALS_SNACK_PERCENTAGE_START) + 1) + MEALS_SNACK_PERCENTAGE_START;
        Double result = (TDEE * percentage) / 100;
        return result.intValue();
    }
    
}
