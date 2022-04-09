package com.example.prepitbackend.dao;

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
public class UserInfoDao {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private int weight;
    private int height;
    private int age;
    private String activityType;
    private String gender;
    private Object roles;
}