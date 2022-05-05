package com.example.prepitbackend.utils;

import java.util.ArrayList;

import com.example.prepitbackend.dto.MealDTO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MealJsonDTOAdapter {

    public ArrayList<MealDTO> convert(JSONArray jsonArray){
        ArrayList<MealDTO> meals = new ArrayList<MealDTO>();

        for (Object object : jsonArray){
            meals.add(this.createObject((JSONObject) object));
        }

        return meals;
    }

    public MealDTO createObject(JSONObject object){
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

    private int getCalories(JSONObject object){
        String nutritions_info = (String) object.get("nutritions_info");
        String[] info_delimited = nutritions_info.split(" \\| ");
        return Integer.parseInt(info_delimited[0].replaceAll("[^0-9]", ""));
    }
}
