package com.example.prepitbackend.utils;

public interface CaloricCalculatorInterface {

    public Double calculateBMR(char gender, Double weight, Double height, int age);

    public Double calculateTDEE(char gender, Double weight, Double height, int age, String activityMultiplier);

    public void setMaleWeightMultiplier(double weightMultiplier);

    public void setFemaleWeightMultiplier(double weightMultiplier);

    public void setMaleHeightMultiplier(double heightMultiplier);

    public void setFemaleHeightMultiplier(double heightMultiplier);

    public void setMaleAgeMultiplier(double ageMultiplier);

    public void setFemaleAgeMultiplier(double ageMultiplier);

    public void setFemaleConstant(double constant);

    public void setMaleConstant(double constant);

    public void setSedentaryActivityMultiplier(double multiplier);
    
    public void setLightActivityMultiplier(double multiplier);

    public void setModerateActivityMultiplier(double multiplier);

    public void setAboveAverageActivityMultiplier(double multiplier);

    public void setVeryActiveActivityMultiplier(double multiplier);
    
}
