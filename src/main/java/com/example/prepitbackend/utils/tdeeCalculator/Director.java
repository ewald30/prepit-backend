package com.example.prepitbackend.utils.tdeeCalculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Director {

    static CaloricCalculatorBuilder builder;

    public static void makeHarrisBenedict(){
        builder = new HarrisBenedictCalculatorBuilder();
    }

    public static void makeHarrisBenedictRevised(){
        builder = new HarrisBenedictCalculatorBuilder();    // change with harris revised
    }

    public static CaloricCalculatorInterface getResult(){
        return builder.getResult();
    }
}
