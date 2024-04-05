package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import animals.Animal;

public class FileHandler {
    private static final String FILE_PATH = "animals.txt";

    public static void loadDataFromFile(ArrayList<Animal> animals) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
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

    public static void saveDataToFile(ArrayList<Animal> animals) {
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
