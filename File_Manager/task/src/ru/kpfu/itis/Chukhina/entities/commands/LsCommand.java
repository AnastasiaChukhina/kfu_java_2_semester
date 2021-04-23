package ru.kpfu.itis.Chukhina.entities.commands;


import ru.kpfu.itis.Chukhina.entities.commands.exceptions.UnknownOptionException;
import ru.kpfu.itis.Chukhina.entities.fileData.FileAttributesData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Command which shows the list of regular files in the given directory
 * Use "ls (dir)" to get a list of regular files in the directory
 * Use "ls -l (dir)" with option '-l' to get the whole info about files in directory
 * Don't write the directory to get files/ files' information in the current directory
 * @author Chukhina Anastasia
 */
public class LsCommand implements Command{
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private final String GET_INFO_ABOUT_FILES_OPTION = "-l";
    private String option;
    private Path dir;

    public LsCommand(String currentDir, String opt, String newDir){
        dir = setDir(currentDir, newDir);
        option = opt;
    }

    @Override
    public void execute() {
        if (option == null) {
            for(Path p : getListOfFilePaths()){
                System.out.println(ANSI_YELLOW + p.getFileName() + ANSI_RESET);
            }
        }
        else if(option.equals(GET_INFO_ABOUT_FILES_OPTION)){
            for(Path p : getListOfFilePaths()){
                FileAttributesData fileAttributesData = new FileAttributesData(p);
                System.out.println(ANSI_YELLOW + "File " + p.getFileName() + ":" + ANSI_RESET);
                fileAttributesData.getFullInformation();
            }
        }
        else throw new UnknownOptionException("Unknown option. Use '-l' to get information about files in directory.");
    }

    private List<Path> getListOfFilePaths(){
        List<Path> list = new ArrayList<>();
        try {
            Files.walk(dir, 1)
                    .filter(Files::isRegularFile)
                    .forEach(x -> list.add(dir.resolve(x).normalize().toAbsolutePath()));
        } catch (IOException e) {
            e.getMessage();
            System.out.println(ANSI_RED + "Problems..." + ANSI_RESET);
        }
        return list;
    }

    private Path setDir(String currentDir, String newDir) {
        if(newDir==null) return Paths.get(currentDir);
        else if((new File(newDir)).isAbsolute()){
            return Paths.get(newDir).normalize().toAbsolutePath();
        }
        else return Paths.get(currentDir).resolve(newDir).normalize().toAbsolutePath();
    }
}
