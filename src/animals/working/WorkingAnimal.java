package animals.working;

import java.time.LocalDate;
import java.time.Period;
import animals.Animal;

public class WorkingAnimal extends Animal {
    // Конструктор класса WorkingAnimal
    public WorkingAnimal(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
