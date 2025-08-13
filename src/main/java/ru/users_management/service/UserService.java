package ru.users_management.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;
import ru.users_management.dto.UserCreateDto;
import ru.users_management.dto.UserDto;
import ru.users_management.dto.UserUpdateDto;
import ru.users_management.enumeration.RoleName;
import ru.users_management.exception.DuplicateException;
import ru.users_management.exception.FieldsValidationException;
import ru.users_management.exception.InvalidPhoneNumberFormatException;
import ru.users_management.exception.ResourceNotFoundException;
import ru.users_management.mapper.UserMapper;
import ru.users_management.repository.UserRepository;
import ru.users_management.util.HasPhoneNumber;
import ru.users_management.util.PhoneUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto findByUuid(UUID uuid) {
        var user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        return userMapper.map(user);
    }

    public UserDto create(UserCreateDto userDto) {
        normalizePhone(userDto);
        checkDuplicatePhoneNumber(userDto.getPhoneNumber());
        validRoleName(userDto.getRoleName());
        var user = userMapper.map(userDto);
        userRepository.save(user);
        return userMapper.map(user);
    }

    public UserDto update(UserUpdateDto userDto, UUID id) {
        var user = userRepository.findByUuid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        if (userDto.getPhoneNumber() != null) {
            normalizePhone(userDto);
            checkDuplicatePhoneNumber(userDto.getPhoneNumber());
        }
        if (userDto.getRoleName() != null) {
            validRoleName(userDto.getRoleName());
        }
        userMapper.update(userDto, user);
        userRepository.save(user);
        return userMapper.map(user);
    }

    public void delete(UUID uuid) {
        var user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        userRepository.delete(user);
    }

    private <T extends HasPhoneNumber> void normalizePhone(T userDto) {
        try {
            var normalizedPhone = PhoneUtils.normalizePhoneNumber(userDto.getPhoneNumber());
            userDto.setPhoneNumber(normalizedPhone);
        } catch (IllegalArgumentException ex) {
            throw new InvalidPhoneNumberFormatException(ex.getMessage());
        }
    }

    private void checkDuplicatePhoneNumber(String phoneNumber) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DuplicateException("Номер телефона уже используется");
        }
    }

    private void validRoleName(final String roleName) {
        if (!EnumUtils.isValidEnumIgnoreCase(RoleName.class, roleName)) {
            throw new FieldsValidationException("Некорректное название роли: " + roleName);
        }
    }
}
