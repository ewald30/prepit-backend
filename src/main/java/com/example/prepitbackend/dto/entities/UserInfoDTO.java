package com.example.prepitbackend.dto.entities;

import java.util.List;

import com.example.prepitbackend.domain.Authority;

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
public class UserInfoDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Double weight;
    private Double height;
    private int age;
    private String activityType;
    private String gender;
    private Object roles;
}
