package com.example.prepitbackend.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.prepitbackend.validation.annotations.ValidEmail;
import com.example.prepitbackend.validation.annotations.ValidPasswordConfirmation;

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
@ValidPasswordConfirmation
public class UserRegisterDTO {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String passwordConfirmation;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    private String firstName;
    
    @NotNull
    @NotEmpty
    private String lastName;
}
