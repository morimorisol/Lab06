package Client.commandDispatcher;

import Common.entities.Coordinates;
import Common.entities.Chapter;
import Common.entities.SpaceMarine;
import Common.enums.AstartesCategory;
import Common.enums.WeaponType;
import Common.handlers.MarineValidator;
import Common.handlers.TextFormatter;


import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс, отвечающий за работу с пользователем во время
 * ввода данных о новом элементе коллекции
 */
public class ArgumentsListener {

    private final Scanner scanner = new Scanner(System.in);

    public SpaceMarine inputSpaceMarine(String name, long health, String achievements) {
        SpaceMarine spaceMarine = new SpaceMarine();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        try {
            spaceMarine.setName(name);
            spaceMarine.setHealth(health);
            spaceMarine.setAchievements(achievements);
            boolean correctName = MarineValidator.validateField(spaceMarine, "name");
            boolean correctHealth = MarineValidator.validateField(spaceMarine, "health");
            boolean correctAchievements = MarineValidator.validateField(spaceMarine, "achievements");
            if (!correctName | !correctHealth | !correctAchievements) {
                return null;
            }
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Аргументы имеют неверный формат");
            return null;
        }
        spaceMarine.setCoordinates(inputCoordinates());
        inputAstartesCategory(spaceMarine);
        inputWeaponType(spaceMarine);
        spaceMarine.setChapter(inputChapter());
        return spaceMarine;
    }

    public SpaceMarine inputSpaceMarineWithPrimitives() {
        SpaceMarine spaceMarine = new SpaceMarine();
        inputPrimitives(spaceMarine);
        spaceMarine.setCoordinates(inputCoordinates());
        inputAstartesCategory(spaceMarine);
        inputWeaponType(spaceMarine);
        spaceMarine.setChapter(inputChapter());
        return spaceMarine;
    }


    public void inputPrimitives(SpaceMarine spaceMarine) {
        TextFormatter.printMessage("Введите аргументы для корабля: имя, здоровье[>0], достижения[>0]");
        String[] inputArray = scanner.nextLine().split(" ");
        if (inputArray.length == 3) {
            try {
                spaceMarine.setName(inputArray[0]);
                spaceMarine.setHealth(Long.parseLong(inputArray[1]));
                spaceMarine.setAchievements(inputArray[2]);
                boolean correctName = MarineValidator.validateField(spaceMarine, "name");
                boolean correctHealth = MarineValidator.validateField(spaceMarine, "health");
                boolean correctAchievements = MarineValidator.validateField(spaceMarine, "achievements");
                if (!correctName | !correctHealth | !correctAchievements) {
                    TextFormatter.printErrorMessage("Введенные данные некорректны");
                    inputPrimitives(spaceMarine);
                }
            } catch (IllegalArgumentException e) {
                TextFormatter.printErrorMessage("Введены некорректные данные, верный формат: имя здоровье[>0] достижения[>0]");
                inputPrimitives(spaceMarine);
            }
        } else {
            TextFormatter.printErrorMessage("Введены некорректные данные, верный формат: имя здоровье[>0] достижения[>0]");
            inputPrimitives(spaceMarine);
        }
    }


    public Coordinates inputCoordinates() {
        TextFormatter.printInfoMessage("Введите координаты:");
        Coordinates newCoordinates = new Coordinates();
        inputX(newCoordinates);
        inputY(newCoordinates);
        return newCoordinates;
    }


    private void inputX(Coordinates coordinates) {
        TextFormatter.printInfoMessage("Введите координату x (целое число): ");
        try {
            int x = Integer.parseInt(scanner.nextLine());
            coordinates.setX(x);
            boolean correctField = MarineValidator.validateField(coordinates, "x");
            if (!correctField) {
                TextFormatter.printErrorMessage("Значение поля некорректно, попробуйте еще раз");
                inputX(coordinates);
            }
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Число имеет неверный формат");
            inputX(coordinates);
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage("Число не входит в допустимый диапазон");
            inputX(coordinates);
        }
    }


    private void inputY(Coordinates coordinates) {
        TextFormatter.printInfoMessage("Введите Y(число с плавающей точкой): ");
        try {
            float y = Float.parseFloat(scanner.nextLine());
            coordinates.setY(y);
            boolean correctField = MarineValidator.validateField(coordinates, "y");
            if (!correctField) {
                inputY(coordinates);
            }
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Ошибка ввода");
            inputY(coordinates);
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage(e.getMessage());
            inputY(coordinates);
        }
    }

    public Chapter inputChapter() {
        TextFormatter.printInfoMessage("Введите данные о части:");
        Chapter chapter = new Chapter();
        inputName(chapter);
        inputParentLegion(chapter);
        return chapter;
    }

    private void inputDepth(DragonCave cave) {
        TextFormatter.printInfoMessage("Введите глубину пещеры (число с плавающей точкой): ");
        try {
            cave.setDepth(Double.parseDouble(scanner.nextLine()));
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Ошибка ввода");
            inputDepth(cave);
        }
    }

    private void inputNumOfTreasures(DragonCave cave) {
        TextFormatter.printInfoMessage("Введите количество сокровищ (целое число, большее 0): ");
        try {
            cave.setNumberOfTreasures(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Ошибка ввода");
            inputNumOfTreasures(cave);
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage(e.getMessage());
            inputNumOfTreasures(cave);
        }
    }


    public void inputAstartesCategory(SpaceMarine spaceMarine) {
        TextFormatter.printInfoMessage("Введите достижения корабля, доступные достижения: " + Arrays.toString(AstartesCategory.values()) + ", если у дракона нет достижения, нажмите Enter: ");
        try {
            String color = scanner.nextLine().toUpperCase();
            if ("".equals(color)) {
                spaceMarine.setAstartesCategory(null);
            } else {
                spaceMarine.setAstartesCategory(AstartesCategory.valueOf(color));
            }
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage("Ошибка ввода, такого цвета не существует");
            inputAstartesCategory(spaceMarine);
        }
    }

    public void inputWeaponType(SpaceMarine spaceMarine) {
        TextFormatter.printInfoMessage("Введите тип оружия, доступные типы: " + Arrays.toString(WeaponType.values()) + ": ");
        try {
            spaceMarine.setWeaponType(WeaponType.valueOf(scanner.nextLine().toUpperCase()));
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage("Ошибка ввода, такого типа оружия не существует");
            inputWeaponType(spaceMarine);
        }
    }
}
