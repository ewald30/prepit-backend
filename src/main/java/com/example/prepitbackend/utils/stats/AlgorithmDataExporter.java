package com.example.prepitbackend.utils.stats;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.prepitbackend.dto.entities.MealAlgorithmDTO;
import com.example.prepitbackend.dto.entities.MealChartDTO;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.dto.mappers.MealMapper;
import com.example.prepitbackend.utils.SuggestionAlgorithmImpl;

import org.springframework.stereotype.Service;

@Service
public class AlgorithmDataExporter {

    public static final ArrayList<MealChartDTO> export(ArrayList<MealDTO> meals,MealAlgorithmDTO targetMeal,int nbIterations, Double priceMultiplier, Double timeMultiplier, Double accuracyMultiplier){
        ArrayList<ArrayList<MealDTO>> results = new ArrayList<ArrayList<MealDTO>>();

        for(int i=0; i<nbIterations; i++){
            SuggestionAlgorithmImpl suggestionAlgorithm = new SuggestionAlgorithmImpl(meals, "LOSE", priceMultiplier, timeMultiplier, accuracyMultiplier);
            results.add(suggestionAlgorithm.runForMeal(targetMeal));
        }  

        return getMap(results, targetMeal.getWeight());

    }


    /**
     * Maps the data to a map with key = unique_id of each meal and value a Pair containing the meal data and how many times it was suggested
     * @param <code>ArrayList<ArrayList<MealDTO>></code> containing the suggested meals
     * @return <code> HashMap </code> containing the mapped data
     */
     private static ArrayList<MealChartDTO> getMap(ArrayList<ArrayList<MealDTO>> meals, double targetWeight){
        ArrayList<MealChartDTO> result = new ArrayList<MealChartDTO>();

        for (ArrayList<MealDTO> resultSet: meals){
            for(MealDTO suggestion: resultSet){
                MealChartDTO mealConverted = MealMapper.toMealChartDTO(suggestion, targetWeight);
                int index = result.indexOf(mealConverted);
                if(index >= 0){
                    result.get(index).setNbOfOccurences(result.get(index).getNbOfOccurences() + 1);
                } else{
                    result.add(mealConverted);
                }
            }
        }
        
        return result;
     }
}
