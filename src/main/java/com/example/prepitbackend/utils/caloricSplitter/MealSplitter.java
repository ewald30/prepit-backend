package com.example.prepitbackend.utils.caloricSplitter;

public interface MealSplitter {

    /**
     * Returns the calories for the breakfast and a given TDEE
     * @param TDEE - Total Daily Energy Expenditure
     * @return <code>int</code> - the calories to eat for breakfast
     */
    public int getBreakfast(Double TDEE);

    /**
     * Returns the calories for the lunch and a given TDEE
     * @param TDEE - Total Daily Energy Expenditure
     * @return <code>int</code> - the calories to eat for lunch
     */
    public int getLunch(Double TDEE);

    /**
     * Returns the calories for the dinner and a given TDEE
     * @param TDEE - Total Daily Energy Expenditure
     * @return <code>int</code> - the calories to eat for dinner
     */
    public int getDinner(Double TDEE);

    /**
     * Returns the calories for the snack and a given TDEE
     * @param TDEE - Total Daily Energy Expenditure
     * @return <code>int</code> - the calories to eat for snack
     */
    public int getSnack(Double TDEE);
}
