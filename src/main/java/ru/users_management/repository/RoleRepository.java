package ru.users_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.users_management.model.Role;

import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс-репозиторий для ролей пользователей
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * Поиск роли по UUID
     * @param uuid UUID роли
     * @return {@link Optional < Role >} объект, содержащий найденную роль, если она существует
     */
    Optional<Role> findByUuid(UUID uuid);
}
