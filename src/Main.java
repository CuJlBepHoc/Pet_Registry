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
        FileHandler.loadDataFromFile(animals); // Загрузка данных о животных из файла

        while (true) {
            OutputHandler.displayMenu(); // Отображение главного меню программы

            String choice = InputHandler.getStringInput("Выберите действие: "); // Запрос выбора действия у пользователя

            switch (choice) {
                case "1":
                    animalManager.addNewAnimal(); // Добавление нового животного
                    break;
                case "2":
                    animalManager.viewAnimalCommands(); // Просмотр списка команд для животного
                    break;
                case "3":
                    animalManager.trainAnimal(); // Обучение животного новым навыкам
                    break;
                case "4":
                    OutputHandler.displayAllAnimals(animals); // Отображение списка всех животных
                    break;
                case "5":
                    animalManager.removeAnimal(); // Удаление животного из реестра
                    break;
                case "0":
                    FileHandler.saveDataToFile(animals); // Сохранение данных о животных в файл
                    System.out.println("Выход из программы.");
                    System.exit(0); // Завершение программы
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }
}
