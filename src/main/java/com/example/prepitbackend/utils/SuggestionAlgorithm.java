package com.example.prepitbackend.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.MealAlgorithmDTO;
import com.example.prepitbackend.dto.MealDTO;
import com.example.prepitbackend.service.bl.MealService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

class Pair
{
    MealDTO key;
    Integer value;

    public Pair(MealDTO key, Integer value)
    {
        this.key = key;
        this.value = value;
    }
    public MealDTO getKey()
    {
        return key;
    }
    public void setKey(MealDTO key)
    {
        this.key = key;
    }
    public Integer getValue()
    {
        return value;
    }
    public void setValue(Integer value)
    {
        this.value = value;
    }
}

public class SuggestionAlgorithm {

    ArrayList<MealDTO> meals;

    public SuggestionAlgorithm(ArrayList<MealDTO> meals){
        this.meals = meals;
    }

    public ArrayList<ArrayList<MealDTO>> runForDay(ArrayList<MealAlgorithmDTO> targets){
        ArrayList<ArrayList<MealDTO>> result = new ArrayList<ArrayList<MealDTO>>();
        for(MealAlgorithmDTO target : targets){
            ArrayList<MealDTO> suggestions = this.runForMeal(target);
            result.add(suggestions);
        }

        return result;
    }

    public ArrayList<MealDTO> runForMeal(MealAlgorithmDTO target){
        ArrayList<MealDTO> result = new ArrayList<>();

        //List<MockObject> arr = utils.generateNumberArray();
        Collections.shuffle(this.meals);
        result = printKclosest(target, 3);

        return result;
    }

    public ArrayList<MealDTO> printKclosest(MealAlgorithmDTO x, int k) {
        ArrayList<MealDTO> result = new ArrayList<>();

        // Make a max heap.
        PriorityQueue<Pair> pq = new PriorityQueue<>(
                new Comparator<Pair>() {
                    public int compare(Pair p1, Pair p2) {
                        return p2.getValue().compareTo(
                                p1.getValue());
                    }
                });

        // Build heap of difference with
        // first k elements that fit into the desired caloric range
        int index=0;
        int nb=0;
        while(nb < k && index < this.meals.size()){
            if(this.meals.get(index).getKcalories() <= x.getCalories()){    // make this possible to change from <= to >= based on target (losing/gaining weight)
                pq.offer(new Pair(this.meals.get(index), (int) Math.abs(calculateDistance(this.meals.get(index), x))));
                nb++;
            }
            index++;
        }

        // Now process remaining elements.
        for (int i = k; i < this.meals.size(); i++) {

            int diff = (int) Math.abs(calculateDistance(this.meals.get(i), x));    // this differrence could be rewritten as something else

            // If difference with current
            // element is more than root,
            // then ignore it.
            if (!isAccepted(diff, this.meals.get(i), x, pq.peek().getValue()))     // can change the condition to match
                continue;

            // Else remove root and insert
            pq.poll();
            pq.offer(new Pair(this.meals.get(i), diff));

/*            if(isAccepted(diff, arr.get(i), x, pq.peek().getValue())){
                pq.poll();
                pq.offer(new Pair(arr.get(i), diff));
            }*/

        }

        // Print contents of heap.
        while (!pq.isEmpty()) {
            result.add(pq.poll().getKey());
        }

        return result;
    }

    static boolean isAccepted(int diff, MealDTO element, MealAlgorithmDTO target,  int pq){
        boolean value = diff < pq && target.getCalories()>= element.getKcalories(); //&& (element.getPrice() < 40 && element.getPrice() > 35);
        return value;

    }

    static double calculateDistance(MealDTO candidate, MealAlgorithmDTO target){
        int candidateWeight = WeightValueCalculator.calculate(candidate.getKcalories(), Integer.valueOf(candidate.getPriceScore()), Integer.valueOf(candidate.getTimeScore()), 0.5, 0.5);
        return Math.abs(target.getWeight() - candidateWeight);
    }


    
}
