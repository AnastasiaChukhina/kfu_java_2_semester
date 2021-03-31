package ru.kpfu.itis.Chukhina.entities.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TreeCommand implements Command{
    private Path dir;
    private StringBuilder sb;

    public TreeCommand(String path){
        dir = Paths.get(path);
        sb = new StringBuilder();
    }

    @Override
    public void execute() {
        addParents(sb, dir);
        try {
            Files.walk(dir)
                    .skip(1)
                    .forEach(x -> sb.append('\n').append(addSpaces(x.getNameCount()*2)).append("|")
                                                              .append("____").append(x.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }

    private void addParents(StringBuilder sb, Path path){
        sb.append(path.getRoot().toString());
        for(int i=0; i < path.getNameCount(); i++){
            sb.append('\n').append(addSpaces(i+1)).append("|").append("_").append(dir.getName(i));
        }

    }

    private String addSpaces(int n){
        String str = "";
        for(int i = 0; i<n; i++){
            str+= " ";
        }
        return str;
    }
}
