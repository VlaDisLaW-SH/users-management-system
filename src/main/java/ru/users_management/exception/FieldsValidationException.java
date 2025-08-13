package ru.users_management.exception;

public class FieldsValidationException extends RuntimeException {
    public FieldsValidationException(String message) {
        super(message);
    }
}
