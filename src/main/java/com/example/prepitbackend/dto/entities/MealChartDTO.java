package com.example.prepitbackend.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MealChartDTO {
    private String uniqId;
    private int calories;
    private String type;
    private int time_score;
    private int price_score;
    private int nbOfOccurences;
    private double weight;
    private double deviation;
    private double targetWeight;

    @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof MealChartDTO)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        MealChartDTO mealChart = (MealChartDTO) o;
         
        // Compare the data members and return accordingly
        if (mealChart.getUniqId() == uniqId)
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime + (uniqId == null ? 0 : uniqId.hashCode());    
    }
}
