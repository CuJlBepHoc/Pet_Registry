-- Создание базы данных
CREATE DATABASE IF NOT EXISTS Human_Friends;

-- Использование базы данных Human_Friends
USE Human_Friends;

-- Создание таблицы Animals
CREATE TABLE IF NOT EXISTS Animals (
    AnimalID INT AUTO_INCREMENT,
    AnimalType VARCHAR(255) NOT NULL,
    PRIMARY KEY (AnimalID)
);

-- Создание таблицы Pets
CREATE TABLE IF NOT EXISTS Pets (
    PetID INT AUTO_INCREMENT,
    AnimalID INT,
    PetType VARCHAR(255) NOT NULL,
    PRIMARY KEY (PetID),
    FOREIGN KEY (AnimalID) REFERENCES Animals(AnimalID)
);

-- Создание таблицы WorkingAnimals
CREATE TABLE IF NOT EXISTS WorkingAnimals (
    WorkingAnimalID INT AUTO_INCREMENT,
    AnimalID INT,
    WorkingAnimalType VARCHAR(255) NOT NULL,
    PRIMARY KEY (WorkingAnimalID),
    FOREIGN KEY (AnimalID) REFERENCES Animals(AnimalID)
);

-- Создание таблицы Dogs
CREATE TABLE IF NOT EXISTS Dogs (
    DogID INT AUTO_INCREMENT,
    PetID INT,
    Name VARCHAR(255),
    Command VARCHAR(255),
    BirthDate DATE,
    PRIMARY KEY (DogID),
    FOREIGN KEY (PetID) REFERENCES Pets(PetID)
);

-- Создание таблицы Cats
CREATE TABLE IF NOT EXISTS Cats (
    CatID INT AUTO_INCREMENT,
    PetID INT,
    Name VARCHAR(255),
    Command VARCHAR(255),
    BirthDate DATE,
    PRIMARY KEY (CatID),
    FOREIGN KEY (PetID) REFERENCES Pets(PetID)
);

-- Создание таблицы Hamsters
CREATE TABLE IF NOT EXISTS Hamsters (
    HamsterID INT AUTO_INCREMENT,
    PetID INT,
    Name VARCHAR(255),
    Command VARCHAR(255),
    BirthDate DATE,
    PRIMARY KEY (HamsterID),
    FOREIGN KEY (PetID) REFERENCES Pets(PetID)
);

-- Создание таблицы Horses
CREATE TABLE IF NOT EXISTS Horses (
    HorseID INT AUTO_INCREMENT,
    WorkingAnimalID INT,
    Name VARCHAR(255),
    Command VARCHAR(255),
    BirthDate DATE,
    PRIMARY KEY (HorseID),
    FOREIGN KEY (WorkingAnimalID) REFERENCES WorkingAnimals(WorkingAnimalID)
);

-- Создание таблицы Camels
CREATE TABLE IF NOT EXISTS Camels (
    CamelID INT AUTO_INCREMENT,
    WorkingAnimalID INT,
    Name VARCHAR(255),
    Command VARCHAR(255),
    BirthDate DATE,
    PRIMARY KEY (CamelID),
    FOREIGN KEY (WorkingAnimalID) REFERENCES WorkingAnimals(WorkingAnimalID)
);

-- Создание таблицы Donkeys
CREATE TABLE IF NOT EXISTS Donkeys (
    DonkeyID INT AUTO_INCREMENT,
    WorkingAnimalID INT,
    Name VARCHAR(255),
    Command VARCHAR(255),
    BirthDate DATE,
    PRIMARY KEY (DonkeyID),
    FOREIGN KEY (WorkingAnimalID) REFERENCES WorkingAnimals(WorkingAnimalID)
);

-- Добавление типов животных в таблицу Animals
INSERT INTO Animals (AnimalType) VALUES ('Pets'), ('Working Animals');

-- Добавление записей в таблицу Pets
INSERT INTO Pets (AnimalID, PetType) VALUES
  ((SELECT AnimalID FROM Animals WHERE AnimalType = 'Pets'), 'Dogs'),
  ((SELECT AnimalID FROM Animals WHERE AnimalType = 'Pets'), 'Cats'),
  ((SELECT AnimalID FROM Animals WHERE AnimalType = 'Pets'), 'Hamsters');

-- Добавление записей в таблицу WorkingAnimals
INSERT INTO WorkingAnimals (AnimalID, WorkingAnimalType) VALUES
  ((SELECT AnimalID FROM Animals WHERE AnimalType = 'Working Animals'), 'Horses'),
  ((SELECT AnimalID FROM Animals WHERE AnimalType = 'Working Animals'), 'Camels'),
  ((SELECT AnimalID FROM Animals WHERE AnimalType = 'Working Animals'), 'Donkeys');

-- Вставка данных для собак
INSERT INTO Dogs (PetID, Name, Command, BirthDate) VALUES 
    ((SELECT PetID FROM Pets WHERE PetType = 'Dogs'), 'Buddy', 'Sit', '2019-06-01'),
    ((SELECT PetID FROM Pets WHERE PetType = 'Dogs'), 'Max', 'Stay', '2020-07-03');

-- Вставка данных для кошек
INSERT INTO Cats (PetID, Name, Command, BirthDate) VALUES 
    ((SELECT PetID FROM Pets WHERE PetType = 'Cats'), 'Whiskers', 'Jump', '2018-11-13'),
    ((SELECT PetID FROM Pets WHERE PetType = 'Cats'), 'Shadow', 'Fetch', '2019-09-16');

-- Вставка данных для хомяков
INSERT INTO Hamsters (PetID, Name, Command, BirthDate) VALUES 
    ((SELECT PetID FROM Pets WHERE PetType = 'Hamsters'), 'Nibbles', 'Spin', '2021-02-04'),
    ((SELECT PetID FROM Pets WHERE PetType = 'Hamsters'), 'Ginger', 'Roll', '2021-08-12');

-- Вставка данных для лошадей
INSERT INTO Horses (WorkingAnimalID, Name, Command, BirthDate) VALUES 
    ((SELECT WorkingAnimalID FROM WorkingAnimals WHERE WorkingAnimalType = 'Horses'), 'Spirit', 'Trot', '2017-03-17'),
    ((SELECT WorkingAnimalID FROM WorkingAnimals WHERE WorkingAnimalType = 'Horses'), 'Champion', 'Gallop', '2018-05-25');

-- Вставка данных для верблюдов
INSERT INTO Camels (WorkingAnimalID, Name, Command, BirthDate) VALUES 
    ((SELECT WorkingAnimalID FROM WorkingAnimals WHERE WorkingAnimalType = 'Camels'), 'Sandy', 'Kneel', '2016-10-30'),
    ((SELECT WorkingAnimalID FROM WorkingAnimals WHERE WorkingAnimalType = 'Camels'), 'Caramel', 'Stand', '2015-04-18');

-- Вставка данных для осла
INSERT INTO Donkeys (WorkingAnimalID, Name, Command, BirthDate) VALUES 
    ((SELECT WorkingAnimalID FROM WorkingAnimals WHERE WorkingAnimalType = 'Donkeys'), 'Eeyore', 'Pull', '2018-07-27'),
    ((SELECT WorkingAnimalID FROM WorkingAnimals WHERE WorkingAnimalType = 'Donkeys'), 'Burrito', 'Carry', '2019-12-15');
    
   
-- Удаление всех верблюдов из таблицы Camels
DELETE FROM Camels;

-- Создание новой таблицы для рабочих животных семейства лошадиных
CREATE TABLE IF NOT EXISTS WorkingEquids (
    EquidID INT AUTO_INCREMENT,
    WorkingAnimalID INT,
    Name VARCHAR(255),
    Command VARCHAR(255),
    BirthDate DATE,
    Type VARCHAR(255),
    PRIMARY KEY (EquidID),
    FOREIGN KEY (WorkingAnimalID) REFERENCES WorkingAnimals(WorkingAnimalID)
);

-- Вставка данных из таблицы Horses в новую таблицу WorkingEquids
INSERT INTO WorkingEquids (WorkingAnimalID, Name, Command, BirthDate, Type)
SELECT WorkingAnimalID, Name, Command, BirthDate, 'Horse' FROM Horses;

-- Вставка данных из таблицы Donkeys в новую таблицу WorkingEquids
INSERT INTO WorkingEquids (WorkingAnimalID, Name, Command, BirthDate, Type)
SELECT WorkingAnimalID, Name, Command, BirthDate, 'Donkey' FROM Donkeys;

-- Удаление таблиц, после объединения
DROP TABLE Horses;
DROP TABLE Donkeys;

-- Создание новой таблицы для молодых животных
CREATE TABLE IF NOT EXISTS YoungAnimals (
    YoungAnimalID INT AUTO_INCREMENT,
    Name VARCHAR(255),
    BirthDate DATE,
    AgeMonths INT,
    PRIMARY KEY (YoungAnimalID)
);

-- Вставка данных о молодых животных из таблицы Dogs
INSERT INTO YoungAnimals (Name, BirthDate, AgeMonths)
SELECT Name, BirthDate, TIMESTAMPDIFF(MONTH, BirthDate, CURDATE()) AS AgeMonths
FROM Dogs
WHERE BirthDate > CURDATE() - INTERVAL 3 YEAR AND BirthDate <= CURDATE() - INTERVAL 1 YEAR;

-- Вставка данных о молодых животных из таблицы Cats:
INSERT INTO YoungAnimals (Name, BirthDate, AgeMonths)
SELECT Name, BirthDate, TIMESTAMPDIFF(MONTH, BirthDate, CURDATE()) AS AgeMonths
FROM Cats
WHERE BirthDate > CURDATE() - INTERVAL 3 YEAR AND BirthDate <= CURDATE() - INTERVAL 1 YEAR;

-- Вставка данных о молодых животных из таблицы Hamsters
 INSERT INTO YoungAnimals (Name, BirthDate, AgeMonths)
SELECT Name, BirthDate, TIMESTAMPDIFF(MONTH, BirthDate, CURDATE()) AS AgeMonths
FROM Hamsters
WHERE BirthDate > CURDATE() - INTERVAL 3 YEAR AND BirthDate <= CURDATE() - INTERVAL 1 YEAR;

-- Вставка данных о молодых животных из таблицы WorkingEquids
INSERT INTO YoungAnimals (Name, BirthDate, AgeMonths)
SELECT Name, BirthDate, TIMESTAMPDIFF(MONTH, BirthDate, CURDATE()) AS AgeMonths
FROM WorkingEquids
WHERE BirthDate > CURDATE() - INTERVAL 3 YEAR AND BirthDate <= CURDATE() - INTERVAL 1 YEAR;
   
-- Создание общей таблицы AllAnimals
CREATE TABLE IF NOT EXISTS AllAnimals (
    AnimalID INT AUTO_INCREMENT,
    Name VARCHAR(255),
    BirthDate DATE,
    Command VARCHAR(255),
    AgeMonths INT,
    PRIMARY KEY (AnimalID)
);

-- Добавление столбца для типа животного (например, Dog, Cat и т.д.)
ALTER TABLE AllAnimals ADD COLUMN AnimalType VARCHAR(255);

-- Добавление столбца для указания на принадлежность к Pet или WorkingAnimal
ALTER TABLE AllAnimals ADD COLUMN CategoryType VARCHAR(255);

-- Вставка данных из таблицы Dogs
INSERT INTO AllAnimals (Name, BirthDate, Command, AgeMonths, AnimalType, CategoryType)
SELECT Name, BirthDate, Command, TIMESTAMPDIFF(MONTH, BirthDate, CURDATE()), 'Dog', 'Pet'
FROM Dogs;

-- Вставка данных из таблицы Cats
INSERT INTO AllAnimals (Name, BirthDate, Command, AgeMonths, AnimalType, CategoryType)
SELECT Name, BirthDate, Command, TIMESTAMPDIFF(MONTH, BirthDate, CURDATE()), 'Cat', 'Pet'
FROM Cats;

-- Вставка данных из таблицы Hamsters:
INSERT INTO AllAnimals (Name, BirthDate, Command, AgeMonths, AnimalType, CategoryType)
SELECT Name, BirthDate, Command, TIMESTAMPDIFF(MONTH, BirthDate, CURDATE()), 'Hamster', 'Pet'
FROM Hamsters;

-- Вставка данных из таблицы WorkingEquids:
INSERT INTO AllAnimals (Name, BirthDate, Command, AgeMonths, AnimalType, CategoryType)
SELECT Name, BirthDate, Command, TIMESTAMPDIFF(MONTH, BirthDate, CURDATE()), Type, 'WorkingAnimal'
FROM WorkingEquids;


SELECT * FROM AllAnimals;
   
   
   
   
   
   