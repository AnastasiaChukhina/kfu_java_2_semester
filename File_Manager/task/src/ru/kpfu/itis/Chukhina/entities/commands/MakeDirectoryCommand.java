package ru.kpfu.itis.Chukhina.entities.commands;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MakeDirectoryCommand implements Command{
    private Scanner sc;
    private Path path;

    public MakeDirectoryCommand(Scanner scanner, String currentDir){
        sc = scanner;
        path = Paths.get(currentDir);
    }

    @Override
    public void execute() {
        boolean isCreated = new File(getDir().toString()).mkdir();
        if (isCreated) System.out.println("Directory was successfully created.");
        else System.out.println("Can't create the directory.");
    }

    private Path getDir(){
        String str = sc.next();
        if(str.startsWith("\\")){
            return Paths.get(str).normalize().toAbsolutePath();
        }
        return path.resolve(Paths.get(str)).normalize().toAbsolutePath();
    }
}
