package ru.users_management.exception;

public class InvalidPhoneNumberFormatException extends RuntimeException {
    public InvalidPhoneNumberFormatException(String message) {
        super(message);
    }
}
