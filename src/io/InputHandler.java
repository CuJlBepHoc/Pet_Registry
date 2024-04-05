package io;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    // Метод для получения целочисленного ввода от пользователя
    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка: Введите целое число.");
            System.out.print(prompt);
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    // Метод для получения строкового ввода от пользователя
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Метод для получения даты от пользователя
    public static LocalDate getDateInput(String prompt) {
        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: Некорректный формат даты. Используйте формат dd.MM.yyyy.");
            }
        }
        return date;
    }
}
