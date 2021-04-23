package ru.kpfu.itis.Chukhina.entities.utils.exceptions;

/**
 * Exception which throws if the written string can't be read
 * @author Chukhina Anastasia
 */
public class UserInteracorReadException extends RuntimeException{

    public UserInteracorReadException(String mes){
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
