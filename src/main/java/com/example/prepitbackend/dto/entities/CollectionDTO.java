package com.example.prepitbackend.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDTO {
    private String name;
    private String description;
    private Long userId;
}
