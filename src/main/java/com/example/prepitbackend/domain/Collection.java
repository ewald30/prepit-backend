package com.example.prepitbackend.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Collection {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Many  to many relationship between collection and meal
    // collection_meal is the name of the relationship table
    @ManyToMany
    @JoinTable(
        name = "collection_meal", 
        joinColumns = @JoinColumn(name = "collection_id"), 
        inverseJoinColumns = @JoinColumn(name = "meal_id"))
    Set<Meal> containedMeals = new HashSet<>();;

    public void addMeal(Meal meal){
        this.containedMeals.add(meal);
        meal.getBelongsTo().add(this);
    }

    public void removeMeal(long mealId) {
        Meal meal = this.containedMeals.stream().filter(m -> m.getId() == mealId).findFirst().orElse(null);
        if (meal != null) {
          this.containedMeals.remove(meal);
          meal.getBelongsTo().remove(this);
        }
      }
}
