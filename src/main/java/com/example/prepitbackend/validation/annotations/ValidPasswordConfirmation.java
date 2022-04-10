package com.example.prepitbackend.validation.annotations;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.prepitbackend.validation.validator.PasswordConfirmationValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordConfirmationValidator.class)
@Documented
public @interface ValidPasswordConfirmation {
    String message() default "Passwords don't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
