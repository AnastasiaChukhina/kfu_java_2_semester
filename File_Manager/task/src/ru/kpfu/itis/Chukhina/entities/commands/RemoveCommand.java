package ru.kpfu.itis.Chukhina.entities.commands;

import ru.kpfu.itis.Chukhina.entities.exceptions.UnknownFileException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class RemoveCommand implements Command{
    private Scanner sc;
    private Path dir;


    public RemoveCommand(Scanner scanner, String currentDir){
        sc = scanner;
        dir = Paths.get(currentDir);
    }

    @Override
    public void execute() {
        for(Path path : getDir()){
            (new File(path.toString())).delete();
        }
    }

    private Path[] getDir(){
        sc.nextLine();
        String[] input = sc.nextLine().split(" ");
        Path[] paths = new Path[input.length];
        int i = 0;
        for(String path : input) {
            if (path.startsWith("\\")) {
                if (!Files.exists(Paths.get(path).normalize().toAbsolutePath())) {
                    throw new UnknownFileException("This directory doesn't exist.");
                }
                paths[i++] = Paths.get(path).normalize().toAbsolutePath();
            }
            else{
                if (!Files.exists(dir.resolve(path).normalize().toAbsolutePath())) {
                    throw new UnknownFileException("This directory doesn't exist.");
                }
                paths[i++] = dir.resolve(path).normalize().toAbsolutePath();
            }
        }
        return paths;
    }
}
