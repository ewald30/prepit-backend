package com.example.prepitbackend.utils;

import java.util.ArrayList;

import com.example.prepitbackend.dto.entities.MealDTO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

/**
 *  Adapter class used by the MealService class in order to feed the suggestion algorithm
 *  objects of type <code>MealDTO</code> 
 * 
 *  This class with MealService and MealRepo form the Adapter design pattern
 */
@Component
public class MealJsonDTOAdapter {

    /**
     * Public method used by the <code>MealService</code> to convert data from JSON to <code>MealDTO</code> objects
     * @param jsonArray - array of recipes stored as JSON objects
     * @return array of <code>MealDTO</code> objects
     */
    public ArrayList<MealDTO> convert(JSONArray jsonArray){
        ArrayList<MealDTO> meals = new ArrayList<MealDTO>();

        for (Object object : jsonArray){
            meals.add(this.createObject((JSONObject) object));
        }

        return meals;
    }

    /**
     * Used to create a <code>MealDTO</code> object from a JSON object
     * @param object - a recipe stored as a JSON object
     * @return <code>MealDTO</code> object converted from JSON
     */
    private MealDTO createObject(JSONObject object){
        MealDTO mealDTO = new MealDTO();

        mealDTO.setUniqId((String) object.get("uniq_id"));
        mealDTO.setTitle((String) object.get("title"));
        mealDTO.setDescription((String) object.get("description"));
        mealDTO.setInstructions((String) object.get("instructions"));
        mealDTO.setIngredients((String) object.get("ingredients"));
        mealDTO.setImage((String) object.get("image"));
        mealDTO.setUrl((String) object.get("url"));
        mealDTO.setAuthor((String) object.get("author"));
        mealDTO.setPrepTime((String) object.get("prep_time"));
        mealDTO.setCookTime((String) object.get("cook_time"));
        mealDTO.setTotalTime((String) object.get("total_time"));
        mealDTO.setKeywords((String) object.get("keywords"));
        mealDTO.setAuthor((String) object.get("author"));
        mealDTO.setCrawledAt((String) object.get("crawled_at"));
        mealDTO.setPublishedDate((String) object.get("published_at"));
        mealDTO.setNutritionInfo((String) object.get("nutritions_info"));
        mealDTO.setTotalRatings((String) object.get("total_ratings"));
        mealDTO.setType((String) object.get("type"));
        mealDTO.setKcalories(getCalories(object));
        mealDTO.setPriceScore((String) object.get("price_score"));
        mealDTO.setTimeScore((String) object.get("time_score"));
        mealDTO.setServing(String.valueOf(object.get("serving")));

        return mealDTO;
    }

    /**
     * Used to get the number of calories from a JSON object, since the calories is only a
     * part of the attribute "nutritions_info" of the object
     * @param object JSON object to get number of calories from
     * @return <code>int</code> representing the number of calories of the recipe
     */
    private int getCalories(JSONObject object){
        String nutritions_info = (String) object.get("nutritions_info");
        String[] info_delimited = nutritions_info.split(" \\| ");
        return Integer.parseInt(info_delimited[0].replaceAll("[^0-9]", ""));
    }
}
