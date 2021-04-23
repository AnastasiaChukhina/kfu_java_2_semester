package ru.kpfu.itis.Chukhina.entities.commands.exceptions;

/**
 * Exception which throws if file with the written name doesn't exist
 * @author Chukhina Anastasia
 */
public class UnknownFileException extends RuntimeException{

    public UnknownFileException(String mes){
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
