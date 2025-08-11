package ru.users_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.users_management.model.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс-репозиторий для пользователей
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Поиск пользователя по UUID
     * @param uuid UUID пользователя
     * @return {@link Optional<User>} объект, содержащий найденного пользователя, если он существует
     */
    Optional<User> findByUuid(UUID uuid);

    /**
     * Проверка на наличие номера телефона в репозитории
     * @param phoneNumber номера телефона
     * @return булево значение
     */
    boolean existsByPhoneNumber(String phoneNumber);
}
