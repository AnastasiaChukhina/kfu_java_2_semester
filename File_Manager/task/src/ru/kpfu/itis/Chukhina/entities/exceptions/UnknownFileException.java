package ru.kpfu.itis.Chukhina.entities.exceptions;

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
