import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

abstract class Animal {
    protected String name;
    protected ArrayList<String> commands = new ArrayList<>();
    protected LocalDate birthDate;

    public Animal(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    protected void addCommand(String command) {
        commands.add(command);
    }

    protected ArrayList<String> getCommands() {
        return commands;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(";").append(name).append(";")
                .append(birthDate.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"))).append(";");
        for (String command : commands) {
            sb.append(command).append(";");
        }
        return sb.toString();
    }
    public static Animal deserialize(String data) {
        String[] parts = data.split(";");
        if (parts.length < 3) {
            return null;
        }
        String type = parts[0];
        String name = parts[1];
        LocalDate birthDate = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd.MM.uuuu")); // Заменил String на LocalDate
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
                System.out.println("Неизвестный тип животного: " + type);
        }
        if (animal != null) {
            for (String command : commands) {
                animal.addCommand(command);
            }
        }
        return animal;
    }

    public abstract int calculateAge();
}

class Pet extends Animal {
    public Pet(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}

class Dog extends Pet {
    public Dog(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}

class Cat extends Pet {
    public Cat(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}

class Hamster extends Pet {
    public Hamster(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}

class WorkingAnimal extends Animal {
    public WorkingAnimal(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}

class Horse extends WorkingAnimal {
    public Horse(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}

class Camel extends WorkingAnimal {
    public Camel(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}

class Donkey extends WorkingAnimal {
    public Donkey(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}

public class Main {
    private static final String FILE_PATH = "animals.txt";
    private static ArrayList<Animal> animals = new ArrayList<>();

    public static void main(String[] args) {
        loadDataFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Завести новое животное");
            System.out.println("2. Увидеть список команд, которые выполняет животное");
            System.out.println("3. Обучить животное новым командам");
            System.out.println("4. Показать всех животных");
            System.out.println("5. Выйти из программы");

            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Выберите тип животного: ");
                    System.out.println("1. Dog");
                    System.out.println("2. Cat");
                    System.out.println("3. Hamster");
                    System.out.println("4. Horse");
                    System.out.println("5. Camel");
                    System.out.println("6. Donkey");
                    System.out.print("Введите номер типа: ");
                    int animalTypeChoice;
                    try {
                        animalTypeChoice = Integer.parseInt(scanner.nextLine());
                        if (animalTypeChoice < 1 || animalTypeChoice > 6) {
                            throw new IllegalArgumentException("Некорректный номер типа животного.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Введенное значение не является целым числом.");
                        continue;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    System.out.print("Введите имя животного: ");
                    String name = scanner.nextLine();

                    System.out.print("Введите дату рождения животного в формате DD.MM.YYYY: ");
                    String birthDateStr = scanner.nextLine();
                    LocalDate birthDate = null;

                    try {
                        birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd.MM.uuuu"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Ошибка: Некорректный формат даты. Используйте формат DD.MM.YYYY");
                        continue;
                    }

                    Animal animal;
                    switch (animalTypeChoice) {
                        case 1:
                            animal = new Dog(name, birthDate);
                            break;
                        case 2:
                            animal = new Cat(name, birthDate);
                            break;
                        case 3:
                            animal = new Hamster(name, birthDate);
                            break;
                        case 4:
                            animal = new Horse(name, birthDate);
                            break;
                        case 5:
                            animal = new Camel(name, birthDate);
                            break;
                        case 6:
                            animal = new Donkey(name, birthDate);
                            break;
                        default:
                            continue;
                    }

                    animals.add(animal);
                    System.out.println(name + " добавлен в реестр.");

                    // Запись данных в файл
                    try {
                        FileWriter writer = new FileWriter(FILE_PATH, true);
                        writer.write(animal.serialize() + "\n");
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    System.out.println("Список всех животных:");
                    int listIndex  = 1;
                    for (Animal a : animals) {
                        System.out.println(listIndex  + ". " + a.getName());
                        listIndex++;
                    }
                    System.out.print("Введите номер животного для просмотра его команд: ");
                    int animalNumber;
                    try {
                        animalNumber = Integer.parseInt(scanner.nextLine());
                        if (animalNumber < 1 || animalNumber > animals.size()) {
                            throw new IllegalArgumentException("Некорректный номер животного.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Введенное значение не является целым числом.");
                        continue;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    Animal selectedAnimal = animals.get(animalNumber - 1);
                    System.out.println(selectedAnimal.getName() + " может выполнить следующие команды:");
                    if (selectedAnimal.getCommands().isEmpty()) {
                        System.out.println("Животное не знает команд.");
                    } else {
                        System.out.println(String.join(", ", selectedAnimal.getCommands()));
                    }
                    break;

                case "3":
                    System.out.println("Выберите животное для обучения:");
                    int index = 1;
                    for (Animal a : animals) {
                        if (a != null) {
                            System.out.println(index + ". " + a.getName() + " (Возраст: " + a.calculateAge() + " лет)");
                            index++;
                        }
                    }
                    System.out.println(index + ". Пропустить");
                    System.out.print("Введите номер: ");
                    String animalChoice = scanner.nextLine();
                    int animalIndex = -1;
                    try {
                        animalIndex = Integer.parseInt(animalChoice) - 1;
                        if (animalIndex < 0 || animalIndex >= animals.size()) {
                            throw new IllegalArgumentException();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный номер животного.");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Некорректный выбор.");
                        break;
                    }
                    System.out.print("Введите новую команду для обучения животного: ");
                    String newCommand = scanner.nextLine();
                    animals.get(animalIndex).addCommand(newCommand);
                    System.out.println(animals.get(animalIndex).getName() + " теперь знает команду: " + newCommand);
                    break;

                case "4":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    for (Animal a : animals) {
                        System.out.println("Тип: " + a.getClass().getSimpleName() + ", Имя: " + a.getName() + ", Дата рождения: " + a.getBirthDate().format(formatter));
                    }
                    break;

                case "5":
                    saveDataToFile();
                    System.out.println("Выход из программы.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }

    private static void loadDataFromFile() {
        try {
            File file = new File(FILE_PATH);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Animal animal = Animal.deserialize(line);
                if (animal != null) {
                    animals.add(animal);
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveDataToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Animal animal : animals) {
                writer.write(animal.serialize() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
