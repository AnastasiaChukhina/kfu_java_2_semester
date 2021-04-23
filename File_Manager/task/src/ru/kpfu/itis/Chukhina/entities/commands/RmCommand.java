package ru.kpfu.itis.Chukhina.entities.commands;

import ru.kpfu.itis.Chukhina.entities.commands.exceptions.UnknownOptionException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command which deletes the written files if they exist
 * Use "rm (filenames)" to delete files
 * Use "rm *" to delete all regular files in directory
 * Use "rm *.(extension)" to delete all files with the given extension
 * @author Chukhina Anastatia
 */
public class RmCommand implements Command{
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private final String TEMP_TO_RM_ALL_FILES_IN_DIR = "*";
    private final String TEMP_TO_RM_ALL_FILES_WITH_THE_GIVEN_EXTENSION = "*.";
    private Path dir;
    private String rmFiles;
    private String option;

    public RmCommand(String currentDir, String opt, String files){
        dir = Paths.get(currentDir);
        option = opt;
        rmFiles = files;
    }

    @Override
    public void execute() {
        if(option != null){
            throw new UnknownOptionException("rm-command doesn't recognize any option, try again");
        }
        for(Path path : getDir()){
            (new File(path.toString())).delete();
        }
    }

    private List<Path> getDir(){
        List<Path> paths = new ArrayList<>();
        if(rmFiles.equals(TEMP_TO_RM_ALL_FILES_IN_DIR)){
            try {
                paths = Files.walk(dir, 1)
                     .filter(Files::isRegularFile)
                     .collect(Collectors.toList());
            } catch (IOException e) {
                e.getMessage();
                System.out.println(ANSI_RED + "Problems..." + ANSI_RESET);
            }
        }
        else if(rmFiles.contains(TEMP_TO_RM_ALL_FILES_WITH_THE_GIVEN_EXTENSION)){
            try {
                paths = Files.walk(dir, 1)
                        .filter(x->x.toString().contains("." + rmFiles.substring(rmFiles.lastIndexOf
                                                 (TEMP_TO_RM_ALL_FILES_WITH_THE_GIVEN_EXTENSION)+2)))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.getMessage();
                System.out.println(ANSI_RED + "Problems..." + ANSI_RESET);
            }
        }
        else{
            String[] input = rmFiles.split(" ");
            for (String path : input) {
                if ((new File(path)).isAbsolute()) {
                    paths.add(Paths.get(path).normalize().toAbsolutePath());
                }
                else paths.add(dir.resolve(path).normalize().toAbsolutePath());
            }
        }
        return paths;
    }
}
