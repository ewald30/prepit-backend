package com.example.prepitbackend.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.prepitbackend.dto.UserRegisterDTO;
import com.example.prepitbackend.validation.annotations.ValidPasswordConfirmation;

public class PasswordConfirmationValidator implements ConstraintValidator<ValidPasswordConfirmation, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserRegisterDTO userDto = (UserRegisterDTO) value;
        return userDto.getPassword().equals(userDto.getPasswordConfirmation());
    }
    
}
