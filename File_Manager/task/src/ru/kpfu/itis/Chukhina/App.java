package ru.kpfu.itis.Chukhina;

import ru.kpfu.itis.Chukhina.entities.AbstractApp;
import ru.kpfu.itis.Chukhina.entities.commands.*;
import ru.kpfu.itis.Chukhina.entities.commands.exceptions.UnknownDirectoryException;
import ru.kpfu.itis.Chukhina.entities.utils.ConsoleUserInteractor;
import ru.kpfu.itis.Chukhina.entities.utils.UserInteractor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Console application for working with file system
 * @author Chukhina Anastasia
 */
public class App extends AbstractApp {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String UNDERLINE = "\u001B[4m";
    private String currentDir;
    private String data;
    private String command;
    private String options;
    private UserInteractor userInteractor;
    private Map<String, Command> commands;

    public App(){
        super();
    }

    @Override
    public void init() {
        userInteractor = new ConsoleUserInteractor();
        currentDir = (new File("")).getAbsolutePath();
        data = "";
        command = "";
        options = "";
        addCommands();
    }

    @Override
    public void start() {
        while(true){
            userInteractor.write(ANSI_CYAN + UNDERLINE + currentDir + ANSI_RESET + " > ");
            findData(userInteractor.read());
            boolean isFound = false;
            for(String el : commands.keySet()){
                if(command.equals(el)){
                    addCommands();
                    commands.get(el).execute();
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                userInteractor.write(ANSI_RED + "Unknown command. Call 'help' command to find out about " +
                                                                        "your possibilities." + ANSI_RESET + '\n');
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

    private void findData(String str){
        String regex = "^([\\w_]+)(?:\\s(?:(-[a-z]))?|([^-]+))*$";
        Matcher matcher = Pattern.compile(regex, Pattern.COMMENTS|Pattern.DOTALL).matcher(str);
        if(matcher.find()) {
            command = matcher.group(1);
            options = matcher.group(2);
            data = matcher.group(3);
        }
    }

    private void addCommands(){
        commands = new HashMap<>();
        commands.put("exit", new ExitCommand());
        commands.put("cd", new CdCommand(this, options, data));
        commands.put("ls", new LsCommand(currentDir, options, data));
        commands.put("mkdir", new MkdirCommand(currentDir, options, data));
        commands.put("rm", new RmCommand(currentDir, options, data));
        commands.put("touch", new TouchCommand(currentDir, options, data));
        commands.put("tree", new TreeCommand(currentDir, options));
        commands.put("help", new HelpCommand(commands));
    }

    public static void main(String[] args){
        new App();
    }
}
