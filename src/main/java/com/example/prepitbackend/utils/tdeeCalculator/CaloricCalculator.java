package com.example.prepitbackend.utils.tdeeCalculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class CaloricCalculator implements CaloricCalculatorInterface{

    private Double maleWeightMultiplier;
    private Double maleHeightMultiplier;
    private Double maleAgeMultiplier;
    private Double femaleWeightMultiplier;
    private Double femaleHeightMultiplier;
    private Double femaleAgeMultiplier;
    private Double maleConstant;
    private Double femaleConstant;

    private Double SEDENTARY_MULTIPLIER;
    private Double LIGHT_ACTIVITY_MULTIPLIER;
    private Double MODERATE_ACTIVITY_MULTIPLIER;
    private Double ABOVE_AVERAGE_ACTIVITY_MULTIPLIER;
    private Double VERY_ACTIVE_ACTIVITY_MULTIPLIER;


    public Double calculateBMR(char gender, Double weight, Double height, int age){
        switch(gender){
            case 'm':
                return maleConstant + (maleWeightMultiplier * weight) + (maleHeightMultiplier * height) - (maleAgeMultiplier * age);
            case 'f':
                return femaleConstant + (femaleWeightMultiplier * weight) + (femaleHeightMultiplier * height) -(femaleAgeMultiplier * age);
        }
        return null;
    }

    public Double calculateTDEE(char gender, Double weight, Double height, int age, String activityType){
        Double bmr = this.calculateBMR(gender, weight, height, age);
        switch(activityType){
            case "SEDENTARY":
                return this.SEDENTARY_MULTIPLIER * bmr;
            case "LIGHT_ACTIVITY":
                return this.LIGHT_ACTIVITY_MULTIPLIER * bmr;
            case "MODERATE_ACTIVITY":
                return this.MODERATE_ACTIVITY_MULTIPLIER * bmr;
            case "ABOVE_AVERAGE_ACTIVITY":
                return this.ABOVE_AVERAGE_ACTIVITY_MULTIPLIER * bmr;
            case "VERY_ACTIVE":
                return this.VERY_ACTIVE_ACTIVITY_MULTIPLIER * bmr;
        }
        return bmr;
    }

    @Override
    public void setMaleWeightMultiplier(double weightMultiplier) {
        this.maleWeightMultiplier = weightMultiplier;
        
    }

    @Override
    public void setFemaleWeightMultiplier(double weightMultiplier) {
        this.femaleWeightMultiplier = weightMultiplier;
        
    }

    @Override
    public void setMaleHeightMultiplier(double heightMultiplier) {
        this.maleHeightMultiplier = heightMultiplier;
        
    }

    @Override
    public void setFemaleHeightMultiplier(double heightMultiplier) {
        this.femaleHeightMultiplier = heightMultiplier;
        
    }

    @Override
    public void setMaleAgeMultiplier(double ageMultiplier) {
        this.maleAgeMultiplier = ageMultiplier;
        
    }

    @Override
    public void setFemaleAgeMultiplier(double ageMultiplier) {
        this.femaleAgeMultiplier = ageMultiplier;
        
    }

    @Override
    public void setFemaleConstant(double constant) {
        this.femaleConstant = constant;
        
    }

    @Override
    public void setMaleConstant(double constant) {
        this.maleConstant = constant;
        
    }

    @Override
    public void setSedentaryActivityMultiplier(double multiplier) {
        this.SEDENTARY_MULTIPLIER = multiplier;
        
    }

    @Override
    public void setLightActivityMultiplier(double multiplier) {
        this.LIGHT_ACTIVITY_MULTIPLIER = multiplier;
        
    }

    @Override
    public void setModerateActivityMultiplier(double multiplier) {
        this.MODERATE_ACTIVITY_MULTIPLIER = multiplier;
        
    }

    @Override
    public void setAboveAverageActivityMultiplier(double multiplier) {
        this.ABOVE_AVERAGE_ACTIVITY_MULTIPLIER = multiplier;
        
    }

    @Override
    public void setVeryActiveActivityMultiplier(double multiplier) {
        this.VERY_ACTIVE_ACTIVITY_MULTIPLIER = multiplier;
        
    }


}
