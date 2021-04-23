package ru.kpfu.itis.Chukhina.entities.commands;

import ru.kpfu.itis.Chukhina.entities.commands.exceptions.UnknownOptionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Command which shows the tree of files and directories inside current directory
 * Use "tree" to get the tree of files and directories in the current directory
 * @author Chukhina Aanstasia
 */
public class TreeCommand implements Command{
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private Path dir;
    private StringBuilder sb;
    private String option;

    public TreeCommand(String path, String opt){
        dir = Paths.get(path);
        option = opt;
        sb = new StringBuilder();
    }

    @Override
    public void execute() {
        if(option != null){
            throw new UnknownOptionException("rm-command doesn't recognize any option, try again");
        }
        addParents(sb, dir);
        try {
            Files.walk(dir)
                    .skip(1)
                    .forEach(x -> {if(Files.isRegularFile(x)){
                                       sb.append('\n').append(addSpaces(x.getNameCount()+3)).append(ANSI_RED +
                                               "|").append(ANSI_YELLOW + "____" + ANSI_PURPLE).append(x.getFileName());}
                                   else{
                                       sb.append('\n').append(addSpaces(x.getNameCount()+1)).append(ANSI_RED +
                                                "|").append(ANSI_YELLOW + "__" + ANSI_BLUE).append(x.getFileName());}
                                   });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(ANSI_RED + "Problems..." + ANSI_RESET);
        }
        System.out.println(ANSI_PURPLE + sb.toString() + ANSI_RESET);
    }

    private void addParents(StringBuilder sb, Path path){
        sb.append(path.getRoot().toString());
        for(int i=0; i < path.getNameCount(); i++){
            sb.append('\n').append(addSpaces(i+1)).append(ANSI_RED + "|").append(ANSI_YELLOW + "_" + ANSI_RESET)
                                                                                            .append(dir.getName(i));
        }

    }

    private String addSpaces(int n){
        String str = "";
        for(int i = 0; i<n; i++){
            str += " ";
        }
        return str;
    }
}
