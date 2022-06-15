package Server.fileHandlers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import Common.entities.CollectionManager;
import Common.entities.SpaceMarine;
import Common.handlers.DragonValidator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Класс, отвечающий за стартовую обработку xml-файла с данными о коллекции
 */
public class XMLReader {

    public HashSet<SpaceMarine> read(File file) throws IOException, NumberFormatException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("dragon", SpaceMarine.class);
        xStream.alias("set", CollectionManager.class);
        xStream.addImplicitCollection(CollectionManager.class, "dragons");
        StringBuilder xmlText = new StringBuilder();
        FileReader reader = new FileReader(file);
        Scanner sc = new Scanner(reader);
        while (sc.hasNextLine()) {
            xmlText.append(sc.nextLine());
        }
        reader.close();
        CollectionManager manager = (CollectionManager) xStream.fromXML(xmlText.toString());
        if (!fileIsCorrect(manager)) {
            System.out.println("Ошибка чтения файла на уровне валидации");
            System.exit(0);
        }
        return manager.getDragons();
    }

    private boolean fileIsCorrect(CollectionManager manager) {
        for (SpaceMarine dragon : manager.getDragons()) {
            if (!DragonValidator.validateDragon(dragon)) {
                return false;
            }
        }
        return true;
    }
}