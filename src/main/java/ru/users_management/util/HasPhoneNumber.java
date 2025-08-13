package ru.users_management.util;

/**
 * Общий контракт для DTO-объектов, содержащих номер телефона.
 * <p>
 * Интерфейс определяет методы доступа (getter и setter) к полю {@code phoneNumber}.
 * Его реализация позволяет обрабатывать объекты разных типов,
 * не зная их конкретного класса, но будучи уверенным, что у них есть телефон.
 * </p>
 */
public interface HasPhoneNumber {
    /**
     * Возвращает текущий номер телефона.
     *
     * @return строка с номером телефона, может быть {@code null}
     */
    String getPhoneNumber();

    /**
     * Устанавливает новый номер телефона.
     *
     * @param phoneNumber новый номер телефона в виде строки
     */
    void setPhoneNumber(String phoneNumber);
}
