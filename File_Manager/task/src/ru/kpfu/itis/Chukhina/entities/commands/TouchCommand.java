package ru.kpfu.itis.Chukhina.entities.commands;

import ru.kpfu.itis.Chukhina.entities.commands.exceptions.UnknownFileException;
import ru.kpfu.itis.Chukhina.entities.commands.exceptions.UnknownOptionException;
import ru.kpfu.itis.Chukhina.entities.fileData.FileAttributesData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Command which gives information about given files or create them if the don't exist
 * Use "touch (filenames)" to get information about files
 * Use "touch -m (filename)" with option '-m' to change the time of the last file's modification
 * @author Chukhina Anastasia
 */
public class TouchCommand implements Command{
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private final String CHANGE_THE_lAST_MODIFICATION_TIME_OPTION = "-m";
    private String fileNames;
    private String option;
    private Path dir;

    public TouchCommand(String currentDir, String opt, String data){
        fileNames = data;
        option = opt;
        dir = Paths.get(currentDir);
    }

    @Override
    public void execute() {
        if(option != null){
            if(option.equals(CHANGE_THE_lAST_MODIFICATION_TIME_OPTION)){
                Path path;
                if((new File(fileNames)).isAbsolute()){
                    path = Paths.get(fileNames).normalize().toAbsolutePath();
                }
                else path = dir.resolve(fileNames).normalize().toAbsolutePath();
                changeLastModifiedTime(path);
            }
            else throw new UnknownOptionException("Use -m to change the time of last modification.");
        }
        else {
            for (Path path : getPaths()) {
                if (!Files.exists(path)) {
                    try {
                        boolean isCreated = (new File(path.toString())).createNewFile();
                        if (isCreated)
                            System.out.println(ANSI_YELLOW + "New file " + path.getFileName() + " was created."
                                                                                                  + ANSI_RESET);
                    } catch (IOException e) {
                        e.getMessage();
                        System.out.println(ANSI_RED + "Problems..." + ANSI_RESET);
                    }
                } else {
                    FileAttributesData fileAttributesData = new FileAttributesData(path);
                    System.out.println(ANSI_YELLOW + "File " + path.getFileName() + ":" + ANSI_RESET);
                    fileAttributesData.getAccessInformation();
                }
            }
        }
    }

    private Path[] getPaths(){
        String[] input = fileNames.split(" ");
        Path[] filePaths = new Path[input.length];
        int i = 0;
        for(String file : input) {
            filePaths[i++] = dir.resolve(file).normalize().toAbsolutePath();
        }
        return filePaths;
    }

    private void changeLastModifiedTime(Path path){
        if(Files.isRegularFile(path)) {
            Scanner sc = new Scanner(System.in);
            FileAttributesData fad = new FileAttributesData(path);
            System.out.println("Write date in accordance with the template dd/MM/yyyy HH:mm");
            fad.setLastModifiedTime(sc.nextLine());
        }
        else throw new UnknownFileException("Unknown file");
    }
}
