package com.example.prepitbackend.dto.entities;

import java.util.Set;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.Meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {


    public MealDTO(Meal meal) {
        this.uniq_id = meal.getUniqId();
        this.title = meal.getTitle();
        this.description = meal.getDescription();
        this.instructions = meal.getInstructions();
        this.nutritions_info = meal.getNutritionInfo();
        this.calories = meal.getCalories();
        this.ingredients = meal.getIngredients();
        this.type = meal.getType();
        this.serving = meal.getServing();
        this.image = meal.getImage();
        this.url = meal.getUrl();
        this.price_score = meal.getPriceScore();
        this.time_score = meal.getTimeScore();
        this.prep_time = meal.getPreparationTime();
        this.cook_time = meal.getCookTime();
        this.total_time = meal.getTotalTime();
        this.total_ratings = meal.getTotalRatings();
        this.keywords = meal.getKeywords();
        this.author = meal.getAuthor();
        this.source = meal.getSource();
        this.crawled_at = meal.getCrawledAt();
        this.published_date = meal.getPublishedDate();
        this.belongsTo = null;
    }


    private String uniq_id;
    private String title;
    private String description;
    private String instructions;
    private String nutritions_info;
    private int calories;
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
    private Set<CollectionDTO> belongsTo;
}