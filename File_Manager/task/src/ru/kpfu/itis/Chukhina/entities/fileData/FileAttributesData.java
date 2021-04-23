package ru.kpfu.itis.Chukhina.entities.fileData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class which provides the information about file attributes
 * @author Chukhina Anastasia
 */
public class FileAttributesData {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private Path path;
    private BasicFileAttributes attr;

    public FileAttributesData(Path p){
        try {
            attr = Files.readAttributes(p, BasicFileAttributes.class);
            path = p;
        } catch (IOException e) {
            throw new FileAccessException("Can't read file attributes.");
        }
    }

    /**
     * Method shows the full information about file: file owner, file size, creation, access and modification time
     * @throws FileAccessException if file attributes can't be read
     */
    public void getFullInformation(){
        try {
            System.out.println(ANSI_BLUE + "File owner: " + Files.getOwner(path));
            System.out.println("Size: " + new File(path.toString()).length() + " bytes.");
            getAccessInformation();
        } catch (IOException e) {
            throw new FileAccessException("Can't read file attributes.");
        }
    }

    /**
     * Method shows the information creation, access, last modification time of file
     */
    public void getAccessInformation(){
        System.out.println(ANSI_BLUE + "Creation time: " + attr.creationTime());
        System.out.println("Access time: " + attr.lastAccessTime());
        System.out.println("Last modified: " + attr.lastModifiedTime() + ANSI_RESET);
    }

    /**
     * Method sets the last modification time of file
     * @param newLastModifiedTime is a date which should be set
     * @throws ParseException if the given string doesn't compare with the template
     */
    public void setLastModifiedTime(String newLastModifiedTime){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date date = sdf.parse(newLastModifiedTime);
            (new File(path.toString())).setLastModified(date.getTime());
        } catch (ParseException e) {
            e.getMessage();
            System.out.println("Write date in accordance with the template dd/MM/yyyy HH:mm");
        }
    }
}
