package com.example.prepitbackend.rest;

import java.util.ArrayList;

import com.example.prepitbackend.domain.Goal;
import com.example.prepitbackend.dto.MealAlgorithmDTO;
import com.example.prepitbackend.dto.UserMeasurementsDTO;
import com.example.prepitbackend.service.bl.MealService;
import com.example.prepitbackend.utils.SuggestionAlgorithm;
import com.example.prepitbackend.utils.caloricSplitter.CaloricSplitter;
import com.example.prepitbackend.utils.goalCalculator.GoalCalculator;
import com.example.prepitbackend.utils.tdeeCalculator.CaloricCalculator;
import com.example.prepitbackend.utils.tdeeCalculator.Director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/meal")
@CrossOrigin
public class MealServiceRest extends BaseService {
    
    @Autowired
    private MealService mealService;


    @PostMapping("/generate")
    public ResponseEntity<Object> generate(@RequestBody UserMeasurementsDTO entity) {
        return renderResponse(mealService.generateForADay(entity));
    }
}
