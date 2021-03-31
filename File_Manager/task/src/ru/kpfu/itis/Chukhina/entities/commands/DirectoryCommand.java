package ru.kpfu.itis.Chukhina.entities.commands;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryCommand implements Command{
    private Path dir;

    public DirectoryCommand(String path){
        dir = Paths.get(path);
    }

    @Override
    public void execute() {
        try {
            Files.walk(dir)
                    .filter(Files::isRegularFile)
                    .forEach(p -> System.out.println(p.subpath(dir.getNameCount(), p.getNameCount())));
        }catch(IOException ex){
            ex.getMessage();
            System.out.println("Problems...");
        }
    }
}
