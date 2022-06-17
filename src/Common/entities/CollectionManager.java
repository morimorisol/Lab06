package Common.entities;

import Common.handlers.TextFormatter;
import java.util.Date;
import java.util.HashSet;

public class CollectionManager {

    private final Date creationDate;
    private HashSet<SpaceMarine> spaceMarines;

    public CollectionManager() {
        spaceMarines = new HashSet<>();
        creationDate = new Date();
    }

    public HashSet<SpaceMarine> getSpaceMarines() {
        return spaceMarines;
    }

    public void addSpaceMarines(HashSet<SpaceMarine> sm) {
        if(sm.iterator().hasNext()) {
            sm.iterator().next().setId();
            spaceMarines.add(sm.iterator().next());
        }
    }

    public void addSpaceMarine(SpaceMarine sm) {
            sm.setId();
            spaceMarines.add(sm);

    }

    public void clear() {
        spaceMarines.clear();
        if (this.getSpaceMarines().isEmpty()) {
            TextFormatter.printInfoMessage("Коллекция успешно очищена");
        } else {
            TextFormatter.printErrorMessage("Что-то пошло не так, попробуйте снова");
        }
    }

    public String removeById(int id) {
        try {
            SpaceMarine spaceMarine = getById(id);
            if (spaceMarine != null) {
                spaceMarines.remove(spaceMarine);
                return TextFormatter.colorInfoMessage("Корабль успешно заменен");
            } else {
                return TextFormatter.colorErrorMessage("Корабль с данным ID не найден");
            }
        } catch (NumberFormatException e) {
            return TextFormatter.colorErrorMessage("ID имеет некорректный формат");
        }
    }

    public SpaceMarine getById(int id) {
        return null;
                //spaceMarines.stream().filter(sm -> sm.getId().equals(id)).findAny().orElse(null);
    }

    public String showInfo() {
        return TextFormatter.colorInfoMessage("Информация о коллекции: ")
                + TextFormatter.colorMessage("Тип коллекции: " + spaceMarines.getClass()
                + " дата инициализации: " + creationDate
                + " количество кораблей: " + spaceMarines.size());
    }

    public SpaceMarine getMaxByChapter() {
        return spaceMarines.stream().max(SpaceMarine::compareByChapter).get();
    }

    public SpaceMarine getMax() {
        return spaceMarines.stream().max(SpaceMarine::compareTo).get();
    }

    public SpaceMarine getMin() {
        return spaceMarines.stream().min(SpaceMarine::compareTo).get();
    }
}
