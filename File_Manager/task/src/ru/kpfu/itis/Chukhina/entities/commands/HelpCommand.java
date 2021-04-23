package ru.kpfu.itis.Chukhina.entities.commands;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Command which show all available commands
 * @author Chukhina Anastasia
 */
public class HelpCommand implements Command {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private Map<String, Command> commands;

    public HelpCommand(Map<String, Command> map){
        commands = map;
    }

    @Override
    public void execute() {
        String str = commands.keySet().stream()
                .collect(Collectors.joining(", "));
        System.out.println(ANSI_YELLOW + "You can use commands: " + ANSI_BLUE + str + ANSI_RESET);
    }
}
