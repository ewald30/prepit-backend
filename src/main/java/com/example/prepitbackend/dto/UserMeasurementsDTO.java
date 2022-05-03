package com.example.prepitbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserMeasurementsDTO {
    private Double height;
    private Double weight;
    private int age;
    private char gender;
    private int numberOfMeals;
    private String activityType;
    private String goal;
}
