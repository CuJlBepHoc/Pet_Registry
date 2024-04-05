import animals.Animal;
import animals.managers.AnimalManager;
import io.FileHandler;
import io.InputHandler;
import io.OutputHandler;
import java.util.ArrayList;

public class Main {
    private static final ArrayList<Animal> animals = new ArrayList<>();
    private static final AnimalManager animalManager = new AnimalManager(animals);

    public static void main(String[] args) {
        FileHandler.loadDataFromFile(animals);

        while (true) {
            OutputHandler.displayMenu();

            String choice = InputHandler.getStringInput("Выберите действие: ");

            switch (choice) {
                case "1":
                    animalManager.addNewAnimal();
                    break;
                case "2":
                    animalManager.viewAnimalCommands();
                    break;
                case "3":
                    animalManager.trainAnimal();
                    break;
                case "4":
                    OutputHandler.displayAllAnimals(animals);
                    break;
                case "5":
                    animalManager.removeAnimal();
                    break;
                case "0":
                    FileHandler.saveDataToFile(animals);
                    System.out.println("Выход из программы.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }
}
