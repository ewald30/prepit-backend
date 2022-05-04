package com.example.prepitbackend.utils.caloricSplitter;

public interface MealSplitter {
    public int getBreakfast(Double TDEE);
    public int getLunch(Double TDEE);
    public int getDinner(Double TDEE);
    public int getSnack(Double TDEE);
}
