package ru.users_management.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FullNameValidator implements ConstraintValidator<FullName, String> {

    private static final String CYRILLIC_REGEX = "^[А-Яа-яЁё -]+$";

    private static final String LATIN_REGEX = "^[A-Za-z -]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        String trimmed = value.trim();

        if (trimmed.startsWith(" ") || trimmed.endsWith(" ") ||
                trimmed.startsWith("-") || trimmed.endsWith("-")) {
            return false;
        }

        if (trimmed.contains("--") || trimmed.contains("  ")) {
            return false;
        }

        boolean isCyrillic = trimmed.matches(CYRILLIC_REGEX);
        boolean isLatin = trimmed.matches(LATIN_REGEX);

        return isCyrillic || isLatin;
    }
}
