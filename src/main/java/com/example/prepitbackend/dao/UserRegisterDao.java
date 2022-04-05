package com.example.prepitbackend.dao;

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
public class UserRegisterDao {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int weight;
    private int height;
    private int age;
    private String activityType;
    private String gender;
}
