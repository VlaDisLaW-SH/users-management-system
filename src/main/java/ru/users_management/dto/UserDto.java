package ru.users_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "DTO для представления данных пользователя")
public class UserDto {
    @Schema(description = "UUID пользователя", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID uuid;

    @Schema(description = "ФИО пользователя", example = "Иванов Иван Иванович")
    private String fullName;

    @Schema(
            description = "Номер телефона",
            example = "+7(999)999-99-99"
    )
    private String phoneNumber;

    @Schema(
            description = "Аватар пользователя (URL)",
            example = "https://cdn.example.com/avatars/user1234.jpg"
    )
    private String avatar;

    @Schema(
            description = "Название роли пользователя в системе",
            example = "USER"
    )
    private String roleName;

    @Schema(
            description = "Дата и время создания пользователя",
            example = "2023-05-15T14:30:00",
            type = "string",
            format = "date-time"
    )
    private LocalDateTime createdAt;

    @Schema(
            description = "Дата и время обновления пользователя",
            example = "2023-05-15T14:30:00",
            type = "string",
            format = "date-time"
    )
    private LocalDateTime updatedAt;
}
