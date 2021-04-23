package ru.kpfu.itis.Chukhina.entities.commands.exceptions;

/**
 * Exception which throws if the written directory doesn't exist
 * @author Chukhina Anastasia
 */
public class UnknownDirectoryException extends RuntimeException{

    public UnknownDirectoryException(String mes){
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
