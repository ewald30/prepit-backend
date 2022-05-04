package com.example.prepitbackend.service.bl;

import java.util.ArrayList;

import com.example.prepitbackend.domain.Goal;
import com.example.prepitbackend.dto.MealDTO;
import com.example.prepitbackend.dto.UserMeasurementsDTO;
import com.example.prepitbackend.service.dao.MealRepo;
import com.example.prepitbackend.utils.MealJsonDTOAdapter;
import com.example.prepitbackend.utils.SuggestionAlgorithm;
import com.example.prepitbackend.utils.caloricSplitter.CaloricSplitter;
import com.example.prepitbackend.utils.goalCalculator.GoalCalculator;
import com.example.prepitbackend.utils.tdeeCalculator.CaloricCalculator;
import com.example.prepitbackend.utils.tdeeCalculator.Director;
import com.example.prepitbackend.dto.MealAlgorithmDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    MealRepo mealRepo;

    @Autowired
    MealJsonDTOAdapter mealJsonDTOAdapter;

    @Override
    public ArrayList<MealDTO> geAll() {
        // get all from the database
        
        // get all from the JSON file

        // merge these two together
        ArrayList<MealDTO> meals =  mealJsonDTOAdapter.convert(mealRepo.readAllJSON());

        return meals;
    }

    @Override
    public ArrayList<ArrayList<MealDTO>> generateForADay(UserMeasurementsDTO entity) {
        Director.makeHarrisBenedict();
        CaloricCalculator calculator = (CaloricCalculator) Director.getResult();

        Double TDEE = calculator.calculateTDEE(entity.getGender(), entity.getWeight(), entity.getHeight(), entity.getAge(), entity.getActivityType());
        GoalCalculator goalCalculator = new GoalCalculator(Goal.valueOf(entity.getGoal()), entity.getGoalTier());
        Double goal = goalCalculator.calculate(TDEE);
        
        ArrayList<MealAlgorithmDTO> targets = CaloricSplitter.split(3, TDEE, 0.5, 0.5);
        SuggestionAlgorithm suggestionAlgorithm = new SuggestionAlgorithm(this.geAll());
        ArrayList<ArrayList<MealDTO>> result =  suggestionAlgorithm.runForDay(targets);
        return result;
    }

    
    
}
