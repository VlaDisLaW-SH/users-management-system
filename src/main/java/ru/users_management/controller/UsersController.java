package ru.users_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.users_management.dto.UserCreateDto;
import ru.users_management.dto.UserDto;
import ru.users_management.dto.UserUpdateDto;
import ru.users_management.exception.record.ErrorResponse;
import ru.users_management.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Управление пользователями", description = "Операции для работы с пользователями")
public class UsersController {

    private final UserService userService;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить пользователя по UUID",
            description = "Возвращает пользователя по его уникальному идентификатору"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Пользователь успешно найден",
            content = @Content(schema = @Schema(implementation = UserDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Пользователь не найден",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<UserDto> findByUuid(
            @Parameter(
                    name = "UUID",
                    description = "Уникальный идентификатор пользователя",
                    required = true,
                    example = "cb566065-d80e-40a8-89dc-f37ef040e5e7"
            )
            @PathVariable UUID id
    ) {
        var user = userService.findByUuid(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать нового пользователя",
            description = "Создает нового пользователя с предоставленными данными"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Пользователь успешно создан",
            content = @Content(schema = @Schema(implementation = UserDto.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Некорректные входные данные",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserCreateDto userData) {
        var user = userService.create(userData);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Частично обновить данные пользователя",
            description = "Обновляет указанные поля пользователя по его ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Данные пользователя успешно обновлены",
            content = @Content(schema = @Schema(implementation = UserDto.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Некорректные входные данные",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Пользователь не найден",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public ResponseEntity<UserDto> updatePartial(
            @PathVariable UUID id,
            @Valid @RequestBody UserUpdateDto userData
    ) {
        var user = userService.update(userData , id);
        return ResponseEntity
                .ok(user);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить пользователя",
            description = "Удаляет пользователя по его уникальному идентификатору"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Пользователь успешно удален"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Пользователь не найден",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    public void remove(
            @Parameter(
                    name = "id",
                    description = "Уникальный идентификатор пользователя",
                    required = true,
                    example = "cb566065-d80e-40a8-89dc-f37ef040e5e7"
            )
            @PathVariable UUID id
    ) {
        userService.delete(id);
    }
}
