package com.example.prepitbackend.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String instructions;
    private String nutritionInfo;
    private String ingredients;
    private int calories;
    private String type;
    private String serving;
    private String image;
    private String url;
    private String priceScore;
    private String timeScore;
    private String preparationTime;
    private String cookTime;
    private String totalTime;
    private String totalRatings;
    private String keywords;
    private String author;
    private String source;
    private String crawledAt;
    private String publishedDate;

    /**
     * When a meal is inserted into a collection we receive a MealDTO without it's id since 
     *      it isn't available on the front-end. This means that we have to search for the meal in database with something else: uniq_id
     * This will be stored at saveMeal and will ensure that no meal duplication is done
     */
    private String uniqId;


    // Many to many relationship with Collection, a meal belongs to certain collection
    // containedMeals is a field in Collection object that maps the relationship
    @ManyToMany(mappedBy = "containedMeals")
    Set<Collection> belongsTo;

}
