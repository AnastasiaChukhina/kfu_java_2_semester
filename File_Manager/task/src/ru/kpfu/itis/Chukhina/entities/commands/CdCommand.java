package ru.kpfu.itis.Chukhina.entities.commands;

import ru.kpfu.itis.Chukhina.App;
import ru.kpfu.itis.Chukhina.entities.commands.exceptions.UnknownOptionException;

import java.io.File;
import java.nio.file.Paths;

/**
 * Command for changing of current directory
 * Use "cd (path)" to change the current directory
 * @author Chukhina Anastasia
 */
public class CdCommand implements Command {
    private App application;
    private String newDir;
    private String option;

    public CdCommand(App app, String opt, String data){
        application = app;
        option = opt;
        newDir = data;
    }

    @Override
    public void execute() {
        if(option != null){
            throw new UnknownOptionException("cd-command doesn't recognize any option, try again");
        }
        if(newDir == null){
            throw new IllegalStateException("Write a directory.");
        }
        if((new File(newDir)).isAbsolute()){
            newDir = Paths.get(newDir).normalize().toAbsolutePath().toString();
        }
        else{
            newDir = Paths.get(application.getCurrentDir()).resolve(newDir).normalize().toAbsolutePath().toString();
        }
        application.setCurrentDir(newDir);
    }
}
