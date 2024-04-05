package animals.managers;
import animals.Animal;
import animals.pet.Cat;
import animals.pet.Dog;
import animals.pet.Hamster;
import animals.working.Camel;
import animals.working.Donkey;
import animals.working.Horse;
import io.FileHandler;
import io.InputHandler;
import io.OutputHandler;
import java.time.LocalDate;
import java.util.ArrayList;

public class AnimalManager {
    private final ArrayList<Animal> animals;

    public AnimalManager(ArrayList<Animal> animals) {

        this.animals = animals;
    }
    // Метод для добавления нового животного в список
    public void addNewAnimal() {
        System.out.println("Выберите тип животного: ");
        System.out.println("1. Собака");
        System.out.println("2. Кошка");
        System.out.println("3. Хомяк");
        System.out.println("4. Лошадь");
        System.out.println("5. Верблюд");
        System.out.println("6. Осел");
        int animalTypeChoice;
        do {
            animalTypeChoice = InputHandler.getIntInput("Введите номер типа: ");
            if (animalTypeChoice < 1 || animalTypeChoice > 6) {
                System.out.println("Некорректный номер типа. Пожалуйста, повторите ввод");
            }
        } while (animalTypeChoice < 1 || animalTypeChoice > 6);

        String name = InputHandler.getStringInput("Введите имя животного: ");
        LocalDate birthDate = InputHandler.getDateInput("Введите дату рождения животного в формате DD.MM.YYYY: ");

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
                return;
        }

        this.animals.add(animal);
        System.out.println(name + " добавлен в реестр.");


        FileHandler.saveDataToFile(this.animals);
    }
    // Метод для просмотра команд животного
    public void viewAnimalCommands() {
        OutputHandler.displayAllAnimals(this.animals);

        int animalNumber = InputHandler.getIntInput("Введите номер животного для просмотра его команд: ");
        if (animalNumber < 1 || animalNumber > this.animals.size()) {
            System.out.println("Некорректный номер животного.");
            return;
        }

        Animal selectedAnimal = this.animals.get(animalNumber - 1);
        OutputHandler.displayAnimalCommands(selectedAnimal);
    }
    // Метод для обучения животного новой команде
    public void trainAnimal() {
        OutputHandler.displayAllAnimals(this.animals);

        int animalIndex = InputHandler.getIntInput("Выберите животное для обучения: ") - 1;
        if (animalIndex < 0 || animalIndex >= this.animals.size()) {
            System.out.println("Некорректный номер животного.");
            return;
        }

        String newCommand = InputHandler.getStringInput("Введите новую команду для обучения животного: ");
        this.animals.get(animalIndex).addCommand(newCommand);
        System.out.println(this.animals.get(animalIndex).getName() + " теперь знает команду: " + newCommand);

        FileHandler.saveDataToFile(this.animals);
    }
    // Метод для удаления животного из списка
    public  void removeAnimal() {
        OutputHandler.displayAllAnimals(this.animals);

        int animalIndex = InputHandler.getIntInput("Введите номер животного для удаления из реестра: ") - 1;
        if (animalIndex < 0 || animalIndex >= this.animals.size()) {
            System.out.println("Некорректный номер животного.");
            return;
        }

        Animal removedAnimal = this.animals.remove(animalIndex);
        System.out.println(removedAnimal.getName() + " удален из реестра.");

        FileHandler.saveDataToFile(this.animals);
    }

}