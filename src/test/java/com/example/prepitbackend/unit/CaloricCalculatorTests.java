package com.example.prepitbackend.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaloricCalculatorTests {

    private int start;

    @BeforeEach
    public void init(){
        System.out.println("INIT");
        start = 6;
    }

    @Test
    public void Calculate_TDEE_Average_Male(){
        start +=1;
        assertEquals(8, start);
    }

    @Test
    public void Calculate_TDEE_Overweight_Male(){
        start +=1;
        assertEquals(8, start);
    }

}
