package com.example.prepitbackend.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import com.example.prepitbackend.domain.Meal;
import com.example.prepitbackend.dto.entities.MealAlgorithmDTO;
import com.example.prepitbackend.dto.entities.MealDTO;
import com.example.prepitbackend.service.bl.MealService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

class Pair
{
    MealDTO key;
    Double value;

    public Pair(MealDTO key, Double value)
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
    public Double getValue()
    {
        return value;
    }
    public void setValue(Double value)
    {
        this.value = value;
    }
}

public class SuggestionAlgorithm {

    private ArrayList<MealDTO> meals;
    public static Double eps = 0.5;

    public SuggestionAlgorithm(ArrayList<MealDTO> meals){
        this.meals = meals;
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
     *  Returns a list of recommendations for one meal
     * @param target - a target meal, used to determine recommendations closest to it
     * @return <code>ArrayList<MealDTO></code> of recommendations for the target meal
     */
    public ArrayList<MealDTO> runForMeal(MealAlgorithmDTO target){
        ArrayList<MealDTO> result = new ArrayList<>();

        //List<MockObject> arr = utils.generateNumberArray();
        Collections.shuffle(this.meals);
        result = getKclosest(target, 3);

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
            MealDTO meal = this.meals.get(index);
            if((meal.getCalories() <= target.getCalories()) && (!meal.getType().equals("dessert"))){    // make this possible to change from <= to >= based on target (losing/gaining weight)
                pq.offer(new Pair(this.meals.get(index), Math.abs(calculateDistance(this.meals.get(index), target))));
                nb++;
            }
            index++;
        }

        // Now process remaining elements.
        for (int i = k; i < this.meals.size(); i++) {

            Double diff = Math.abs(calculateDistance(this.meals.get(i), target));    // this differrence could be rewritten as something else

            // If difference with current
            // element is more than root,
            // then ignore it.
            if (!isAccepted(diff, this.meals.get(i), target, pq.peek().getValue()))     // can change the condition to match
                continue;

            // Else remove root and insert
            MealDTO meal = this.meals.get(i);
            pq.poll();
            pq.offer(new Pair(this.meals.get(i), diff));
            this.meals.removeIf(m -> m.getUniq_id().equals(meal.getUniq_id())); // removes the already chosen meal

/*            if(isAccepted(diff, arr.get(i), x, pq.peek().getValue())){
                pq.poll();
                pq.offer(new Pair(arr.get(i), diff));
            }*/

            // check if we found the closest matches that correspond to the epsilon and stop the search
            Double rootDifference = pq.peek().getValue();
            if (rootDifference < eps + 0.1)
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
    private static boolean isAccepted(Double diff, MealDTO candidate, MealAlgorithmDTO target,  Double pq){
        if (diff > pq || diff < eps){
            return false;
        }

        if (!getChance())
            return false;

        boolean value = (diff < pq) && (!candidate.getType().equals("dessert") && (target.getCalories()>= candidate.getCalories())); //&& (element.getPrice() < 40 && element.getPrice() > 35);
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
    private static double calculateDistance(MealDTO candidate, MealAlgorithmDTO target){
        Double candidateWeight = WeightValueCalculator.calculate(candidate.getCalories(), Integer.valueOf(candidate.getPrice_score()), Integer.valueOf(candidate.getTime_score()), 0.5, 0.5);
        return Math.abs(target.getWeight() - candidateWeight);
    }


    
}
