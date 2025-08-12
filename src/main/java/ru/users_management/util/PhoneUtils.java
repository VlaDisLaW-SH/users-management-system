package ru.users_management.util;

public class PhoneUtils {

    /**
     * Парсит и нормализует номер телефона к формату 7XXXXXXXXXX
     * @param input номер телефона в любом формате
     * @return нормализованный номер в формате 7XXXXXXXXXX
     * @throws IllegalArgumentException если номер невалиден
     */
    public static String normalizePhoneNumber(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Номер телефона не должен быть пустым");
        }

        var digits = input.replaceAll("\\D", "");

        if (digits.length() == 11 && (digits.startsWith("7") || digits.startsWith("8"))) {
            if (digits.startsWith("8")) {
                digits = "7" + digits.substring(1);
            }
            return digits;
        }

        if (digits.length() == 10) {
            if (input.trim().startsWith("7") || input.trim().startsWith("+7") || input.trim().startsWith("8")) {
                throw new IllegalArgumentException("Номер телефона указан не полностью");
            }
            return "7" + digits;
        }
        throw new IllegalArgumentException("Неверный формат номера телефона. Допустимый: +7(XXX)XXX-XX-XX");
    }
}
