package ru.kpfu.itis.Chukhina.entities.commands;

import java.util.Map;
import java.util.stream.Collectors;

public class HelpCommand implements Command {
    private Map<String, Command> commands;

    public HelpCommand(Map<String, Command> map){
        commands = map;
    }

    @Override
    public void execute() {
        String str = commands.keySet().stream()
                .collect(Collectors.joining(", "));
        System.out.println("You can use commands: " + str);
    }
}
