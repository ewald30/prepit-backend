package com.example.prepitbackend.utils.tdeeCalculator;

public interface CaloricCalculatorBuilder {
    
    public void reset();

    public void setFemaleWeightMultiplier();

    public void setFemaleHeightMultiplier();

    public void setFemaleAgeMultiplier();

    public void setMaleWeightMultiplier();

    public void setMaleHeightMultiplier();

    public void setMaleAgeMultiplier();

    public void setMaleConstant();

    public void setFemaleConstant();

    public void setSedentaryActivityMultiplier();
    
    public void setLightActivityMultiplier();

    public void setModerateActivityMultiplier();

    public void setAboveAverageActivityMultiplier();

    public void setVeryActiveActivityMultiplier();

    public CaloricCalculatorInterface getResult();

    public void build();
}
