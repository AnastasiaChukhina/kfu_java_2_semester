package ru.kpfu.itis.Chukhina;

import ru.kpfu.itis.Chukhina.entities.AbstractApp;
import ru.kpfu.itis.Chukhina.entities.commands.*;
import ru.kpfu.itis.Chukhina.entities.exceptions.UnknownDirectoryException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App extends AbstractApp {
    private Scanner sc;
    protected Map<String , Command> commands;
    private String currentDir;

    public App(){
        super();
    }

    @Override
    public void init() {
        sc = new Scanner(System.in);
        currentDir = (new File("")).getAbsolutePath();
        addCommands();
    }

    @Override
    public void start() {
        while(true){
            addCommands();
            System.out.print(currentDir + " > ");
            String command = sc.next().toLowerCase();
            boolean isFound = false;
            for(String el : commands.keySet()){
                if(command.equals(el)){
                    commands.get(el).execute();
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                System.out.println("Unknown command. Call 'help' command to find out about you possibilities.");
            }
        }
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String newDir){
        if(Files.exists(Paths.get(newDir))) {
            currentDir = newDir;
        }
        else throw new UnknownDirectoryException("Directory doesn't exist");
    }

    private void addCommands(){
        commands = new HashMap<>();
        commands.put("exit", new ExitCommand());
        commands.put("cd", new ChangeDirectoryCommand(this, sc));
        commands.put("dir", new DirectoryCommand(currentDir));
        commands.put("mkdir", new MakeDirectoryCommand(sc, currentDir));
        commands.put("rm", new RemoveCommand(sc, currentDir));
        commands.put("touch", new TouchCommand(sc,currentDir));
        commands.put("tree", new TreeCommand(currentDir));
        commands.put("help", new HelpCommand(commands));
    }


    public static void main(String[] args){
        new App();
    }
}
