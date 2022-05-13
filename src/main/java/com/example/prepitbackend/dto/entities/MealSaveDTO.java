package com.example.prepitbackend.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealSaveDTO {

    private String uniqId;
    private String title;
    private String description;
    private String instructions;
    private String nutritions_info;
    private String ingredients;
    private String type;
    private String serving;
    private String image;
    private String url;
    private String price_score;
    private String time_score;
    private String prep_time;
    private String cook_time;
    private String total_time;
    private String total_ratings;
    private String keywords;
    private String author;
    private String source;
    private String crawled_at;
    private String published_date;

}