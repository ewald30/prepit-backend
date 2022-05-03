package com.example.prepitbackend.rest;

import com.example.prepitbackend.dto.UserMeasurementsDTO;
import com.example.prepitbackend.service.bl.MealService;
import com.example.prepitbackend.utils.CaloricCalculator;
import com.example.prepitbackend.utils.Director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
        //wwmealService.geAll();
        Director.makeHarrisBenedict();
        CaloricCalculator calculator = (CaloricCalculator) Director.getResult();
        Double tdee = calculator.calculateTDEE(entity.getGender(), entity.getWeight(), entity.getHeight(), entity.getAge(), entity.getActivityType());
        return renderResponse(tdee);
    }
}
