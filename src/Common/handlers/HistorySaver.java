package Common.handlers;

import Common.commands.CommandAbstract;

import java.util.ArrayList;
import java.util.List;

public final class HistorySaver {
    private static List<String> commandsHistory = new ArrayList<>();

    private HistorySaver() {}

    public static List<String> getCommandsHistory() {
        return commandsHistory;
    }

    public static void addCommandInHistory(CommandAbstract command) {
        commandsHistory.add(command.getName());
    }
}
