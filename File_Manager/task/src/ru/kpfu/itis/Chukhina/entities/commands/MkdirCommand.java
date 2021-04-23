package ru.kpfu.itis.Chukhina.entities.commands;

import ru.kpfu.itis.Chukhina.entities.commands.exceptions.UnknownOptionException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Command that create a new directory or some directories if they don't exist
 * Use "mkdir (dir name/ dir names)" to create 1 new directory
 * Use "mkdir -p (dir paths)" with option '-p' to create all the directories inside the written path
 * @author Chukhina Anastasia
 */
public class MkdirCommand implements Command{
    private final String CREATE_ALL_DIRS_IN_PATH_OPTION = "-p";
    private String newDir;
    private String option;
    private Path path;

    public MkdirCommand(String currentDir, String opt, String data){
        path = Paths.get(currentDir);
        newDir = data;
        option = opt;
    }

    @Override
    public void execute() {
        if(option != null){
            if(option.equals(CREATE_ALL_DIRS_IN_PATH_OPTION)){
                for(Path p : getDir()) new File(p.toString()).mkdirs();
            }
            else{
                throw new UnknownOptionException("Unknown option. Use '-p' to create all the directories inside path.");
            }
        }
        else {
            for(Path p : getDir()){
                if (p.toString().lastIndexOf("\\") > path.toString().length()) {
                    throw new IllegalStateException("Error: use option '-p' to create all the directories inside path.");
                }
                new File(p.toString()).mkdir();
            }
        }
    }

    private Path[] getDir(){
        String[] input = newDir.split(" ");
        Path[] paths = new Path[input.length];
        int i = 0;
        for(String dir : input) {
            if ((new File(dir)).isAbsolute()) {
                paths[i++] = Paths.get(dir).normalize().toAbsolutePath();
            }
            else paths[i++] = path.resolve(Paths.get(dir)).normalize().toAbsolutePath();
        }
        return paths;
    }
}
