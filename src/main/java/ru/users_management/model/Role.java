package ru.users_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

/**
 * Роль пользователя
 */
@Getter
@Setter
@Entity
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Role {
    /**
     * UUID роли
     */
    @Id
    @UuidGenerator
    @Column(name = "uuid", nullable = false, updatable = false, unique = true)
    private UUID uuid;

    /**
     * Название роли
     */
    @NotBlank
    @Column(name = "role_name")
    private String roleName;

    /**
     * Пользователь, которому принадлежит роль
     */
    @OneToOne(mappedBy = "role")
    private User user;
}
