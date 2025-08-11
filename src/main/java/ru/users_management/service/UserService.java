package ru.users_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.users_management.dto.UserCreateDto;
import ru.users_management.dto.UserDto;
import ru.users_management.exception.DuplicateException;
import ru.users_management.exception.ResourceNotFoundException;
import ru.users_management.mapper.UserMapper;
import ru.users_management.repository.UserRepository;

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
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())) {
            throw new DuplicateException("Номер телефона уже используется");
        }
        var user = userMapper.map(userDto);
        userRepository.save(user);
        return userMapper.map(user);
    }

    public void delete(UUID uuid) {
        var user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        userRepository.delete(user);
    }
}
