package ru.users_management.exception.record;

import java.util.List;

public record ErrorResponse(List<FieldErrorDto> errors) {}
