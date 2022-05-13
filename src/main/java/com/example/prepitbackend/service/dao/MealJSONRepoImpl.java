package com.example.prepitbackend.service.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.utils.MealJsonDTOAdapter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MealJSONRepoImpl implements MealJSONRepo {

    @Value("${file.recipe.path}")
    String recipeFilePath;

    @Override
    public JSONArray readAllJSON() {
        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(this.recipeFilePath));
            return jsonArray;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
