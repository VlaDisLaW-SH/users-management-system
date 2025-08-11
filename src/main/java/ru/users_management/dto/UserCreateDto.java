package ru.users_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "DTO для создания нового пользователя")
public class UserCreateDto {

    @Schema(
            description = "ФИО пользователя",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Иванов Иван Иванович"
    )
    @NotBlank(message = "ФИО обязательно для заполнения")
    private String fullName;

    @Schema(
            description = "Номер телефона",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "+7(999)999-99-99"
    )
    @Column(unique = true)
    @NotBlank(message = "Номер телефона обязателен для заполнения")
    private String phoneNumber;

    @Schema(
            description = "Аватар пользователя (URL)",
            example = "https://cdn.example.com/avatars/user1234.jpg",
            maxLength = 512
    )
    @Size(max = 512, message = "URL не должен превышать 512 символов")
    private String avatar;

    @Schema(
            description = "Название роли",
            example = "USER"
    )
    private String roleName;
}
