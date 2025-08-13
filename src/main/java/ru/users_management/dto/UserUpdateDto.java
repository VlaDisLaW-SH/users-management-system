package ru.users_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import ru.users_management.enumeration.RoleName;
import ru.users_management.util.HasPhoneNumber;
import ru.users_management.validation.FullName;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "DTO для обновления данных пользователя")
public class UserUpdateDto implements HasPhoneNumber {

    @Schema(description = "ФИО пользователя", example = "Иванов Иван Иванович")
    @FullName
    private String fullName;

    @Schema(
            description = "Номер телефона",
            example = "+7(999)999-99-99"
    )
    @Size(min = 10, max = 20, message = "Длина номера телефона должна быть от 10 до 20 символов.")
    private String phoneNumber;

    @Schema(
            description = "Аватар пользователя (URL)",
            example = "https://cdn.example.com/avatars/user1234.jpg"
    )
    @Size(max = 512, message = "URL не должен превышать 512 символов")
    @URL(protocol = "https", message = "Аватар должен быть корректным HTTPS URL")
    private String avatar;

    @Schema(
            description = "Название роли пользователя в системе",
            example = "USER",
            implementation = RoleName.class
    )
    private String roleName;
}
