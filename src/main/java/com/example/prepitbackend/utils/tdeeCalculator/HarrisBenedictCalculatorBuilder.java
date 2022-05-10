package com.example.prepitbackend.utils.tdeeCalculator;

/**
 *  Builder class for {@link CaloricCalculatorInterface}
 */
public class HarrisBenedictCalculatorBuilder implements CaloricCalculatorBuilder{

    CaloricCalculatorInterface calculator;

    HarrisBenedictCalculatorBuilder() {
        this.build();
    }

    public void reset() {
        this.calculator = new CaloricCalculator();
    }

    @Override
    public void build() {
        this.reset();
        this.setFemaleAgeMultiplier();
        this.setFemaleHeightMultiplier();
        this.setFemaleWeightMultiplier();
        this.setFemaleConstant();
        this.setMaleAgeMultiplier();
        this.setMaleHeightMultiplier();
        this.setMaleWeightMultiplier();
        this.setMaleConstant();

        this.setSedentaryActivityMultiplier();
        this.setLightActivityMultiplier();
        this.setModerateActivityMultiplier();
        this.setAboveAverageActivityMultiplier();
        this.setVeryActiveActivityMultiplier();
    }

    @Override
    public CaloricCalculatorInterface getResult() {
        return calculator;
    }

    @Override
    public void setFemaleWeightMultiplier() {
        calculator.setFemaleWeightMultiplier(9.5634);
        
    }

    @Override
    public void setFemaleHeightMultiplier() {
        calculator.setFemaleHeightMultiplier(1.8496);        
    }

    @Override
    public void setFemaleAgeMultiplier() {
        calculator.setFemaleAgeMultiplier(4.6756);
    }

    @Override
    public void setMaleWeightMultiplier() {
        calculator.setMaleWeightMultiplier(13.7515);
        
    }

    @Override
    public void setMaleHeightMultiplier() {
        calculator.setMaleHeightMultiplier(5.003);
        
    }

    @Override
    public void setMaleAgeMultiplier() {
        calculator.setMaleAgeMultiplier(6.755);
        
    }

    @Override
    public void setMaleConstant() {
        calculator.setMaleConstant(66.473);
        
    }

    @Override
    public void setFemaleConstant() {
        calculator.setFemaleConstant(655.0955);
        
    }

    @Override
    public void setSedentaryActivityMultiplier() {
        calculator.setSedentaryActivityMultiplier(1.2);
        
    }

    @Override
    public void setLightActivityMultiplier() {
        calculator.setLightActivityMultiplier(1.375);
        
    }

    @Override
    public void setModerateActivityMultiplier() {
        calculator.setModerateActivityMultiplier(1.55);
        
    }

    @Override
    public void setAboveAverageActivityMultiplier() {
        calculator.setAboveAverageActivityMultiplier(1.725);
        
    }

    @Override
    public void setVeryActiveActivityMultiplier() {
        calculator.setVeryActiveActivityMultiplier(1.9);
        
    }
    
}
