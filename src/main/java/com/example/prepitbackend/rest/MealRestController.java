package com.example.prepitbackend.rest;

import java.security.Principal;
import java.util.ArrayList;

import com.example.prepitbackend.domain.Goal;
import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.domain.User;
import com.example.prepitbackend.dto.entities.MealAlgorithmDTO;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.dto.entities.MealIngredientsDTO;
import com.example.prepitbackend.dto.entities.UserMeasurementsDTO;
import com.example.prepitbackend.dto.mappers.MealMapper;
import com.example.prepitbackend.service.bl.MealService;
import com.example.prepitbackend.service.bl.UserService;
import com.example.prepitbackend.utils.SuggestionAlgorithmImpl;
import com.example.prepitbackend.utils.caloricSplitter.CaloricSplitter;
import com.example.prepitbackend.utils.goalCalculator.GoalCalculator;
import com.example.prepitbackend.utils.stats.AlgorithmDataExporter;
import com.example.prepitbackend.utils.tdeeCalculator.CaloricCalculator;
import com.example.prepitbackend.utils.tdeeCalculator.Director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/meal")
@CrossOrigin
public class MealRestController extends BaseService {
    
    @Autowired
    private MealService mealService;

    @Autowired
    private UserService userService;

    @Autowired
    private MealMapper mapper;



    /**
     * Returns a suggested meal plan fot the given user details
     * @param entity - user measurements and preferences
     * @return a list containing the meals to eat for the given measurements and preferences
     */
    @PostMapping("/generate")
    public ResponseEntity<Object> generate(@RequestBody UserMeasurementsDTO entity) {
        if(entity.getId() > 0)
            userService.updateMeasurements(entity);
        return renderResponse(mealService.generateForADay(entity));
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody MealDTO mealDTO){
        Meal meal = mapper.toMeal(mealDTO);
        return renderResponse(mealService.save(meal));
    }

    @PostMapping("/get-by-ingredients")
    public ResponseEntity<Object> getByIngredient(@RequestBody MealIngredientsDTO ingredientsDTO){
        return renderResponse("Response ingredients: " +  ingredientsDTO.getIngredients());
    }

    @GetMapping("/test/plot")
    public ResponseEntity<Object> plot(@RequestParam("iterations") int iterations, @RequestParam("calories") int calories, Principal user){
        User userObj = userService.loadUserByUsername(user.getName());  // get user from auth header
        MealAlgorithmDTO targetMeal = new MealAlgorithmDTO(calories, 5, 5, "other", userObj.getPriceMultiplier(), userObj.getTimeMultiplier(), userObj.getAccuracyMultiplier());
        
        if (userObj.getAuthority().get(0).getCode().equals("ADMIN") || userObj.getAuthority().get(0).getCode().equals("DEVELOPER") || userObj.getAuthority().get(0).getCode().equals("TESTER")){
            ArrayList<MealDTO> meals = mealService.geAll();
            return renderResponse(AlgorithmDataExporter.export(meals, targetMeal, iterations, userObj.getPriceMultiplier(), userObj.getTimeMultiplier(), userObj.getAccuracyMultiplier()));
        }
        return renderUnauthorized("Unauthorized");
    }
}
