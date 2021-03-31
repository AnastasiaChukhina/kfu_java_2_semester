package ru.kpfu.itis.Chukhina.entities.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class TouchCommand implements Command{
    private Scanner sc;
    private Path dir;

    public TouchCommand(Scanner scanner, String currentDir){
        sc = scanner;
        dir = Paths.get(currentDir);
    }

    @Override
    public void execute() {
        for(Path path : getPaths()) {
            if(!Files.exists(path)){
                try {
                    boolean isCreated = (new File(path.toString())).createNewFile();
                    if(isCreated) System.out.println("New file was created " + path.getFileName() + ".");
                } catch (IOException e) {
                    e.getMessage();
                    System.out.println("Problems...");
                }
            }
            else{
                try {
                    BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                    System.out.println("File " + path.getFileName() + ":");
                    System.out.println("Time of creation: " + attr.creationTime());
                    System.out.println("Last access: " + attr.lastAccessTime());
                    System.out.println("Last modified: " + attr.lastModifiedTime());
                } catch (IOException e) {
                    e.getMessage();
                    System.out.println("Problems...");
                }
            }
        }
    }

    private Path[] getPaths(){
        sc.nextLine();
        String[] input = sc.nextLine().split(" ");
        Path[] filePaths = new Path[input.length];
        int i = 0;
        for(String file : input) {
            filePaths[i++] = dir.resolve(file).normalize().toAbsolutePath();
        }
        return filePaths;
    }
}
