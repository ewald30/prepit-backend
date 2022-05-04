package com.example.prepitbackend.utils.caloricSplitter;

import java.util.Random;

public class ThreeMealsSplitter implements MealSplitter{

    private static int MEALS_BREAKFAST_PERCENTAGE_START = 30;
    private static int MEALS_LUNCH_PERCENTAGE_START = 37;
    private static int MEALS_DINNER_PERCENTAGE_START = 28;

    private static int MEALS_BREAKFAST_PERCENTAGE_END = 33;
    private static int MEALS_LUNCH_PERCENTAGE_END = 40;
    private static int MEALS_DINNER_PERCENTAGE_END = 32;


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
        return 0;
    }
    
}
