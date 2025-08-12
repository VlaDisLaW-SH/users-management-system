package ru.users_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Позьзователь
 */
@Getter
@Setter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class User {
    /**
     * UUID пользователя
     */
    @Id
    @UuidGenerator
    @Column(name = "uuid", nullable = false, updatable = false, unique = true)
    private UUID uuid;

    /**
     * ФИО
     */
    @NotBlank
    @Column(name = "full_name")
    private String fullName;

    /**
     * Номер телефона
     */
    @NotBlank
    @Size(min = 10, max = 20)
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    /**
     * Аватар
     */
    @Column(name = "avatar", length = 512)
    private String avatar;

    /**
     * Роль пользователя в системе
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "role_uuid")
    private Role role;

    /**
     * Дата и время создания пользователя
     */
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Дата и время обновления пользователя
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
