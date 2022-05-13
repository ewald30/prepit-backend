package com.example.prepitbackend.service.bl;

import java.util.ArrayList;

import com.example.prepitbackend.domain.Goal;
import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.entities.MealAlgorithmDTO;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.dto.entities.UserMeasurementsDTO;
import com.example.prepitbackend.service.dao.MealRepo;
import com.example.prepitbackend.utils.MealJsonDTOAdapter;
import com.example.prepitbackend.utils.SuggestionAlgorithm;
import com.example.prepitbackend.utils.caloricSplitter.CaloricSplitter;
import com.example.prepitbackend.utils.goalCalculator.CalorieGoalCalculator;
import com.example.prepitbackend.utils.goalCalculator.GoalCalculator;
import com.example.prepitbackend.utils.goalCalculator.WeightGainCalculator;
import com.example.prepitbackend.utils.goalCalculator.WeightLossCalculator;
import com.example.prepitbackend.utils.tdeeCalculator.CaloricCalculator;
import com.example.prepitbackend.utils.tdeeCalculator.Director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Meal Service
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepo mealRepo;

    @Autowired
    private MealJsonDTOAdapter mealJsonDTOAdapter;

    @Override
    public ArrayList<MealDTO> geAll() {
        // get all from the database
        
        // merge these two together
        ArrayList<MealDTO> meals =  mealJsonDTOAdapter.convert(mealRepo.readAllJSON());

        return meals;
    }
    
    @Override
    public ArrayList<ArrayList<MealDTO>> generateForADay(UserMeasurementsDTO entity) {
        CalorieGoalCalculator goalTierCalculator;

        switch (Goal.valueOf(entity.getGoal())) {
                case LOSE:
                    goalTierCalculator = new WeightLossCalculator();
                    break;
                case GAIN:
                    goalTierCalculator = new WeightGainCalculator();
                    break;
                default:
                    goalTierCalculator = new WeightGainCalculator();
        }

        Director.makeHarrisBenedict();
        CaloricCalculator calculator = (CaloricCalculator) Director.getResult();

        Double TDEE = calculator.calculateTDEE(entity.getGender(), entity.getWeight(), entity.getHeight(), entity.getAge(), entity.getActivityType());
        
        GoalCalculator goalCalculator = new GoalCalculator(goalTierCalculator, entity.getGoalTier());
        Double goal = goalCalculator.calculate(TDEE);
        
        ArrayList<MealAlgorithmDTO> targets = CaloricSplitter.split(entity.getNumberOfMeals(), goal, 0.5, 0.5);
        SuggestionAlgorithm suggestionAlgorithm = new SuggestionAlgorithm(this.geAll());
        ArrayList<ArrayList<MealDTO>> result =  suggestionAlgorithm.runForDay(targets);
        return result;
    }

    @Override
    public Meal save(Meal meal) {
        return mealRepo.save(meal);
    }

    
    
}
