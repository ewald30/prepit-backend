package com.example.prepitbackend.service.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.springframework.core.io.ClassPathResource;

public class MealJSONRepoImpl implements MealJSONRepo {

    @Value("${file.recipe.path}")
    private String recipeFilePath;

    @Override
    public JSONArray readAllJSON() {
        JSONParser parser = new JSONParser();

        try {
            // Use ClassPathResource to get the file as an InputStream
            InputStream inputStream = new ClassPathResource(recipeFilePath).getInputStream();
            JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(inputStream));
            return jsonArray;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
