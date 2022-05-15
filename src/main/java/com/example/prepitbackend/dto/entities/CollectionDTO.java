package com.example.prepitbackend.dto.entities;

import java.util.Set;

import com.example.prepitbackend.domain.Collection;
import com.example.prepitbackend.domain.Meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDTO {


    public CollectionDTO(Collection collection) {
        this.name = collection.getName();
        this.description = collection.getDescription();
        this.userId = collection.getUser().getId();
        this.containedMeals = null;
        this.collectionId = collection.getId();
    }

    private Long collectionId;
    private String name;
    private String description;
    private Long userId;
    private Set<MealDTO> containedMeals;
}
