package ru.kpfu.itis.Chukhina.entities.utils;

import ru.kpfu.itis.Chukhina.entities.utils.exceptions.UserInteracorReadException;
import ru.kpfu.itis.Chukhina.entities.utils.exceptions.UserInteractorWriteException;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class provides interaction with user with console
 * @author Chukhina Anastasia
 */
public class ConsoleUserInteractor implements UserInteractor{
    private Scanner sc;

    public ConsoleUserInteractor(){
        sc = new Scanner(System.in);
    }

    /**
     * Method which read the written string
     * @return the written string
     * @throws UserInteracorReadException if the given string can't be read
     */
    @Override
    public String read() throws UserInteracorReadException {
        try {
            return sc.nextLine();
        }catch(IllegalStateException | NoSuchElementException ex){
            throw new UserInteracorReadException("No data for reading.");
        }
    }

    /**
     * Method which displays the output text
     * @param output - output text
     * @throws UserInteractorWriteException if output text can't be written
     */
    @Override
    public void write(String output) throws UserInteractorWriteException {
        System.out.print(output);
    }
}
