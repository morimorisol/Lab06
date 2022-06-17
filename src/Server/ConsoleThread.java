package Server;

import Server.fileHandlers.XMLWriter;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleThread extends Thread {
    private static final Scanner scanner = ServerConfig.scanner;
    private volatile boolean running = true;

    @Override
    public void run() {
        ServerConfig.logger.info("Консоль запушена");
        while (running) {
            String line = scanner.nextLine();
            if ("save".equalsIgnoreCase(line)) {
                try {
                    XMLWriter.write(Server.file, ServerConfig.manager);
                    ServerConfig.logger.info("Коллекция сохранена");
                } catch (IOException e) {
                    ServerConfig.logger.info("Не удалось сохранить коллекцию");
                }
            }
            if ("exit".equalsIgnoreCase(line)) {
                ServerConfig.logger.info("Сервер закрыт");
                System.exit(0);
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
