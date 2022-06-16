package Common.entities;

import Common.enums.AstartesCategory;
import Common.enums.WeaponType;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * Класс объектов, хранимых в коллекции, которой управляет программа
 */
public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {

    /**
     * Количество полей примитивного типа, которые необходимо передавать при инициализации
     * нового дракона в одной строчке с используемой командой
     */
    public static final int COUNT_OF_PRIMITIVE_ARGS = 3;
    /**
     * Счетчик id элементов, служит для обеспечения уникальности поля id у каждого элемента
     */
    private static long idCounter = 1;
    public int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    public String name; //Поле не может быть null, Строка не может быть пустой
    public Coordinates coordinates;//Поле не может быть null
    public static Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    public Long health; //Поле не может быть null, Значение поля должно быть больше 0
    public String achievements; //Поле не может быть null
    public AstartesCategory category; //Поле может быть null
    public WeaponType weaponType; //Поле не может быть null
    public Chapter chapter; //Поле не может быть null


    /**
     * Метод, устанавливающий id текущему элементу коллекции. Генерация происходит автоматически,
     * аргументы на вход не поступают
     */
    public void setId() {
        this.id = (int) idCounter++;
    }

    /**
     * Метод, возвращающий значение поля id у текущего элемента коллекции
     *
     * @return id дракона
     */
    public int getId() {
        return id;
    }

    /**
     * Метод, устанавливающий значение поля name у текущего элемента коллекции
     *
     * @param name имя дракокна
     */
    public void setName(String name) {
        if (name == null || " ".equals(name)) {
            throw new IllegalArgumentException("Имя не может быть пустым или null, попробуйте снова");
        }
        this.name = name;
    }

    /**
     * Метод, возвращающий значение поля name у текущего элемента коллекции
     *
     * @return имя дракона
     */
    public String getName() {
        return this.name;
    }

    /**
     * Метод, устанавливающий значение поля coordinates у текущего элемента коллекции
     *
     * @param coordinates координаты дракона
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Не переданы координаты или они некорректны, попробуйте снова");
        }
        this.coordinates = coordinates;
    }

    /**
     * Метод, возвращающий содержимое поля coordinates текущего элемента коллекции
     *
     * @return объект координат
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setHealth(long health) {
        if (health <= 0) {
            throw new IllegalArgumentException("Некорректный возраст дракона, попробуйте снова");
        }
        this.health = health;
    }

    /**
     * Метод, возвращающий значение поля age текущего элемента коллекции
     *
     * @return значение возраста дракона
     */
    public long getHealth() {
        return this.health;
    }

    public void setWingspan(String achievements) {
        if (achievements == null) {
            throw new IllegalArgumentException("Некорректный размах крыльев дракона, попробуйте снова");
        }
        this.wingspan = wingspan;
    }

    /**
     * Метод, возвращающий значение поля wingspan у текущего элемента коллекции
     *
     * @return значение размаха крыльев дракона
     */
    public int getWingspan() {
        return this.wingspan;
    }

    /**
     * Метод, устанавливающий значение поля color у текущего элемента коллекции
     *
     * @param astartesCategory цвет дракона
     */
    public void setAstartesCategory(AstartesCategory astartesCategory) {
        this.astartesCategory= astartesCategory;
    }

    /**
     * Метод, возвращающий значение поля color у текущего элемента коллекции
     *
     * @return цвет дракона
     */
    public AstartesCategory getAstartesCategory() {
        return this.astartesCategory;
    }

    /**
     * Метод, устанавливающий значение поля character у текущего элемента коллекции
     *
     * @param weaponType характер дракона
     */
    public void setWeaponType(WeaponType weaponType) {
        if (weaponType == null) {
            throw new IllegalArgumentException("Не передан характер дракона, попробуйте снова");
        }
        this.weaponType = weaponType;
    }

    /**
     * Метод, возвращающий значение поля character у текущего элемента коллекции
     *
     * @return характер дракона
     */
    public WeaponType getWeaponType() {
        return this.weaponType;
    }

    public void setCave(Chapter chapter) {
        if (chapter == null) {
            throw new IllegalArgumentException("Не переданы данные о пещере или они некорректны, попробуйте снова");
        }
        this.chapter = chapter;
    }

    public Chapter getCapter() {
        return this.chapter;
    }


    public void setCreationDate() {
        this.creationDate = new Date();
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public int compareByChapter(SpaceMarine o) {
        if (o == null) {
            return 1;
        }
        return getChapter().compareTo(o.getCapter());
    }

    public int compareByName(SpaceMarine o) {
        if (o == null) {
            return 1;
        }
        return getName().compareTo(o.getName());
    }

    @Override
    public int compareTo(SpaceMarine o) {
        if (o == null) {
            return 1;
        }
        return Comparator.comparing(SpaceMarine::getAge).thenComparing(SpaceMarine::getName).thenComparing(SpaceMarine::getWingspan).compare(this, o);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SpaceMarine spaceMarine = (SpaceMarine) obj;

        return getName().equals(spaceMarine.getName())
                && age == spaceMarine.age
                && coordinates.equals(spaceMarine.coordinates)
                && wingspan == spaceMarine.wingspan
                && cave.equals(spaceMarine.cave)
                && id == spaceMarine.id
                && astartesCategory.equals(spaceMarine.astartesCategory)
                && weaponType.equals(spaceMarine.weaponType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, cave, astartesCategory, weaponType, age, wingspan, creationDate);
    }

    @Override
    public String toString() {
        return "\nDragon #" + id + "\nname: " + name
                + "\ncreationDate: " + getCreationDate()
                + "\nage: " + age + "\nwingspan: " + wingspan
                + "\ncoordinates: " + coordinates.toString() + "\nastartesCategory: " + astartesCategory
                + "\nweaponType: " + weaponType + "\ncave: " + cave.toString() + "\n========================";
    }
}
