package ru.kpfu.itis.Chukhina.entities.commands.exceptions;

import java.io.PrintStream;

/**
 * Exception which throws if the written option isn't recognize by the command
 * @author Chukhina Aanstasia
 */
public class UnknownOptionException extends RuntimeException{

    public UnknownOptionException(String mes){
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
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }
}
