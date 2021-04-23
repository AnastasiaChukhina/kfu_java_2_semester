package ru.kpfu.itis.Chukhina.entities.fileData;

/**
 * Exception which throws if access to file can't be get
 * @author Chukhina Anastasia
 */
public class FileAccessException extends RuntimeException{

    public FileAccessException(String mes){
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
