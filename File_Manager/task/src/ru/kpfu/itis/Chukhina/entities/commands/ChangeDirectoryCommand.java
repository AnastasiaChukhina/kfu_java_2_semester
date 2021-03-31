package ru.kpfu.itis.Chukhina.entities.commands;

import ru.kpfu.itis.Chukhina.App;

import java.nio.file.Paths;
import java.util.Scanner;

public class ChangeDirectoryCommand implements Command {
    private App application;
    private Scanner sc;

    public ChangeDirectoryCommand(App app, Scanner scanner){
        application = app;
        sc = scanner;
    }

    @Override
    public void execute() {
        String newDir = Paths.get(application.getCurrentDir()).resolve(sc.next()).normalize().toAbsolutePath().toString();
        application.setCurrentDir(newDir);
    }
}
