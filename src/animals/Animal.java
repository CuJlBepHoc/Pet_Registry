package animals;

import animals.pet.Cat;
import animals.pet.Dog;
import animals.pet.Hamster;
import animals.working.Camel;
import animals.working.Donkey;
import animals.working.Horse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Animal {
    protected String name;
    protected ArrayList<String> commands = new ArrayList<>();
    protected LocalDate birthDate;

    // Конструктор класса Animal
    public Animal(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
    // Метод для добавления команды животному
    public void addCommand(String command) {
        commands.add(command);
    }

    // Метод для получения списка команд животного
    public ArrayList<String> getCommands() {
        return commands;
    }

    // Метод для получения имени животного
    public String getName() {
        return name;
    }

    // Метод для получения даты рождения животного
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Метод для сериализации данных животного
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(";").append(name).append(";")
                .append(birthDate.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"))).append(";");
        for (String command : commands) {
            sb.append(command).append(";");
        }
        return sb.toString();
    }

    // Метод для десериализации данных животного
    public static Animal deserialize(String data) {
        String[] parts = data.split(";");
        if (parts.length < 3) {
            return null;
        }
        String type = parts[0];
        String name = parts[1];
        LocalDate birthDate = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        ArrayList<String> commands = new ArrayList<>();
        for (int i = 3; i < parts.length; i++) {
            commands.add(parts[i]);
        }
        Animal animal = null;
        switch (type) {
            case "Dog":
                animal = new Dog(name, birthDate);
                break;
            case "Cat":
                animal = new Cat(name, birthDate);
                break;
            case "Hamster":
                animal = new Hamster(name, birthDate);
                break;
            case "Horse":
                animal = new Horse(name, birthDate);
                break;
            case "Camel":
                animal = new Camel(name, birthDate);
                break;
            case "Donkey":
                animal = new Donkey(name, birthDate);
                break;
            default:
                System.out.println("Неизвестный тип животного: \n" + type);
        }
        if (animal != null) {
            for (String command : commands) {
                animal.addCommand(command);
            }
        }
        return animal;
    }

    // Абстрактный метод для вычисления возраста животного
    public abstract int calculateAge();
}
