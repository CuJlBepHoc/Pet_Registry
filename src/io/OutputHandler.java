package io;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import animals.Animal;

public class OutputHandler {
    // Метод для отображения главного меню программы
    public static void displayMenu() {
        System.out.println("\nГлавное меню:");
        System.out.println("1. Зарегистрировать новое животное");
        System.out.println("2. Просмотреть список команд для животного");
        System.out.println("3. Добавить животному новые навыки");
        System.out.println("4. Просмотреть список всех животных");
        System.out.println("5. Удалить из реестра");
        System.out.println("0. Выйти из программы");
    }

    // Метод для отображения списка команд для определенного животного
    public static void displayAnimalCommands(Animal animal) {
        if (animal.getCommands().isEmpty()) {
            System.out.println("Животное не знает команд.");
        } else {
            System.out.println(animal.getName() + " может выполнить следующие команды:");
            System.out.println(String.join(", ", animal.getCommands()));
        }
    }

    // Метод для отображения списка всех животных
    public static void displayAllAnimals(ArrayList<Animal> animals) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int index = 1;
        for (Animal a : animals) {
            System.out.println(index + ". Тип: " + a.getClass().getSimpleName() + ", Имя: " + a.getName() + ", Дата рождения: " + a.getBirthDate().format(formatter));
            index++;
        }
    }
}
