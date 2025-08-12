package ru.users_management.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Роль пользователя в системе
 */
@Getter
@RequiredArgsConstructor
@Schema(enumAsRef = true, description = "Роль пользователя в системе")
public enum RoleName {
    ADMIN("Администратор"),
    USER("Пользователь"),
    GUEST("Гость"),
    MANAGER("Менеджер"),
    CUSTOMER("Клиент"),
    SUPPORT("Технический специалист"),
    DEVELOPER("Разработчик");

    private final String description;
}
