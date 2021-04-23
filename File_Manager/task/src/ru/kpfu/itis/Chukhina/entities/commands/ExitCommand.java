package ru.kpfu.itis.Chukhina.entities.commands;

/**
 * Command which stop the implementation of application
 * @author Chukhina Anastasia
 */
public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }
}
