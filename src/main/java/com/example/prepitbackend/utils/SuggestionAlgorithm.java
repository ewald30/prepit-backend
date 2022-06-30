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


@AllArgsConstructor
public class SuggestionAlgorithm {

    private ArrayList<MealDTO> meals;
    private int numberOfSuggestions = 3;
    private String goal;
    private Double eps = 0.8;

    private Double priceMultiplier;
    private Double timeMultiplier;
    private Double caloriesMultiplier;


    public SuggestionAlgorithm(ArrayList<MealDTO> meals, String goal, Double priceMultiplier, Double timeMultiplier, Double caloriesMultiplier){
        this.meals = meals;
        this.goal = goal;
        this.priceMultiplier = priceMultiplier;
        this.timeMultiplier = timeMultiplier;
        this.caloriesMultiplier = caloriesMultiplier;
    }

    /**
     * Returns a list of 3,4 or 5 recommendations for each meal 
     * @param targets - a list containing meal targets for the generating meal plan, the algorithm will search for the 
     *                  meals closest to these targets
     * @return <code>ArrayList<ArrayList<MealDTO>></code> of recommendations for each meal
     */
    public ArrayList<ArrayList<MealDTO>> runForDay(ArrayList<MealAlgorithmDTO> targets){
        ArrayList<ArrayList<MealDTO>> result = new ArrayList<ArrayList<MealDTO>>();
        for(MealAlgorithmDTO target : targets){
            ArrayList<MealDTO> suggestions = this.runForMeal(target);
            result.add(suggestions);
        }

        return result;
    }

    /**
     * Returns a list of recommendations for one meal
     * @param target - a target meal, used to determine recommendations closest to it
     * @return <code>ArrayList<MealDTO></code> of recommendations for the target meal
     */
    public ArrayList<MealDTO> runForMeal(MealAlgorithmDTO target){
        ArrayList<MealDTO> result = new ArrayList<>();

        //List<MockObject> arr = utils.generateNumberArray();
        Collections.shuffle(this.meals);
        result = getKclosest(target, numberOfSuggestions);

        return result;
    }

    /**
     * Returns the closest k meals for a target meal
     * @param target - representing the target used as a reference by the algorithm when searching for related meals
     * @param k - the number of recommendations for the target meal
     * @return <code>ArrayList<MealDTO></code> of recommendations for the target meal
     */
    private ArrayList<MealDTO> getKclosest(MealAlgorithmDTO target, int k) {
        ArrayList<MealDTO> result = new ArrayList<>();

        // Make a max heap.
        PriorityQueue<Pair<MealDTO, Double>> pq = new PriorityQueue<>(
                new Comparator<Pair<MealDTO, Double>>() {
                    public int compare(Pair<MealDTO, Double> p1, Pair<MealDTO, Double> p2) {
                        return p2.getValue().compareTo(
                                p1.getValue());
                    }
                });

        // Build heap of difference with
        // first k elements that fit into the desired caloric range 
        int index = 0;
        int number = 0;
        while(number < k && index < this.meals.size()){
            MealDTO candidate = this.meals.get(index);
            if((chooseBasedOnGoal(candidate, target)) && (!candidate.getType().equals("dessert"))){    // make this possible to change from <= to >= based on target (losing/gaining weight)
                pq.offer(new Pair<MealDTO, Double>(this.meals.get(index), Math.abs(calculateDistance(this.meals.get(index), target))));
                number++;
            }
            index++;
        }

        // Now process remaining elements.
        for (int i = k; i < this.meals.size(); i++) {

            Double diff = Math.abs(calculateDistance(this.meals.get(i), target));    // this differrence could be rewritten as something else
            

            // If difference with current
            // element is more than root,
            // then ignore it.
            if (!isAccepted(diff, this.meals.get(i), target, pq.peek().getValue()))
                continue;

            // Else remove root and insert
            MealDTO meal = this.meals.get(i);
            pq.poll();
            pq.offer(new Pair<MealDTO, Double>(this.meals.get(i), diff));
            //if(!getChance())  // for 100 iters: - ~100 cals not so good
            this.meals.removeIf(m -> m.getUniq_id().equals(meal.getUniq_id())); // removes the already chosen meal


            // check if we found the closest matches that correspond to the epsilon and stop the search
            Double rootDifference = pq.peek().getValue();
            if (rootDifference < eps)
                break;
        }

        // Print contents of heap.
        while (!pq.isEmpty()) {
            result.add(pq.poll().getKey());
        }

        return result;
    }

    /**
     * Checks if the candidate meal is accepted
     * If the candidate is closer to the target than what is in the priority queue, the candidate gets a chance of 50% of getting recommended
     * @param diff - a value used to illustrate how different the candidate and target are
     * @param candidate - the candidate meal
     * @param target - the target meal
     * @param pq -  
     * @return <code>true</code> if the candidate is accepted, <code>false</code> otherwise
     */
    private boolean isAccepted(Double diff, MealDTO candidate, MealAlgorithmDTO target,  Double pq){

        if(Math.abs(candidate.getCalories()-target.getCalories()) > 130-(this.caloriesMultiplier*10)){ 
            return false;           
        }

        boolean value = (diff < pq) && (!candidate.getType().equals("dessert")); //&& (element.getPrice() < 40 && element.getPrice() > 35);
        return value;

    }

    private static boolean getChance(){
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Calculates the distance used to illustrate how different the candidate and target meals are
     * @param candidate - the candidate meal
     * @param target - the target meal
     * @return <code>double</code> the difference between candidate's and target's weight values
     */
    private double calculateDistance(MealDTO candidate, MealAlgorithmDTO target){
        Double candidateWeight = WeightValueCalculator.calculate(candidate.getCalories(), Integer.valueOf(candidate.getPrice_score()), Integer.valueOf(candidate.getTime_score()), priceMultiplier, timeMultiplier, caloriesMultiplier);
        // if (candidateWeight > target.getWeight()){
        //     return 1;           // in order to try to equalize the number of meals bigger than target with the ones smaller
        // }
        return Math.abs(target.getWeight() - candidateWeight);
    }


    /**
     * Used  when deciding what initial meals to insert into the priority queue
     * @param meal - the candidate to insert into the priority queue
     * @param target - the target meal used as a reference for the candidates
     * @return  <code>true</code> if based on the goal, the condition between candidate and target is satisfied, <code>false</code> otherwise
     */
    private boolean chooseBasedOnGoal(MealDTO meal, MealAlgorithmDTO target){
        switch (Goal.valueOf(this.goal)) {
            case LOSE:
                return meal.getCalories() <= target.getCalories();
            case GAIN:
                return meal.getCalories() >= target.getCalories();
            default:
                return meal.getCalories() <= target.getCalories();
        }
    }
}
