package com.example.prepitbackend.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import com.example.prepitbackend.domain.Goal;
import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.entities.MealAlgorithmDTO;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.service.bl.MealService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

public interface SuggestionAlgorithm {

    public ArrayList<ArrayList<MealDTO>> runForDayByWeight(ArrayList<MealAlgorithmDTO> targets);

    public ArrayList<ArrayList<MealDTO>> suggestRecipesByIngredients(ArrayList<String> targets);

    public ArrayList<MealDTO> getKclosest(MealAlgorithmDTO target, int k);

    public boolean isAccepted(Double diff, MealDTO candidate, MealAlgorithmDTO target,  Double pq);

    public double calculateDistance(MealDTO candidate, MealAlgorithmDTO target);

    public double calculateDistance(MealDTO candidate, ArrayList<String> ingredients);

    default boolean getChance(){
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Creates a new, empty instance of PriorityQueue containing pairs of MealDTO and Double (the weight of each individual meal)
     * @return <code>PriorityQueue<Pair<MealDTO, Double>></code>
     */
    default PriorityQueue<Pair<MealDTO, Double>> createMaxHeap(){
        return new PriorityQueue<Pair<MealDTO, Double>>(   
            new Comparator<Pair<MealDTO, Double>>() {
                        public int compare(Pair<MealDTO, Double> p1, Pair<MealDTO, Double> p2) {
                            return p2.getValue().compareTo(
                                    p1.getValue());
                        }
                    });
    }

}
