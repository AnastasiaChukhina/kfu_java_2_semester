package ru.kpfu.itis.Chukhina.entities.utils.exceptions;

/**
 * Exception which throws if the output string can't be written
 * @author Chukhina Anastasia
 */
public class UserInteractorWriteException extends RuntimeException{

    public UserInteractorWriteException(String mes){
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
