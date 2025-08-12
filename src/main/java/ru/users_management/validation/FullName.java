package ru.users_management.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FullNameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FullName {

    String message() default "ФИО может содержать только буквы одного алфавита, пробелы и дефисы";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
