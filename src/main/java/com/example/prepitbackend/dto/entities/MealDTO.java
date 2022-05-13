package com.example.prepitbackend.dto.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

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
@Entity
public class MealDTO {

    @Id
    private String uniqId;
    private String title;
    private String description;
    private String instructions;
    private String nutritionInfo;
    private String ingredients;
    private int kcalories;
    private String type;
    private String serving;
    private String image;
    private String url;
    private String priceScore;
    private String timeScore;
    private String prepTime;
    private String cookTime;
    private String totalTime;
    private String totalRatings;
    private String keywords;
    private String author;
    private String source;
    private String crawledAt;
    private String publishedDate;

}
