package animals.pet;

import java.time.LocalDate;
import java.time.Period;
import animals.Animal;

public class Pet extends Animal {
    // Конструктор класса Pet
    public Pet(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
