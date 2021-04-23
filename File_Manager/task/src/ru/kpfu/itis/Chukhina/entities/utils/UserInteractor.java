package ru.kpfu.itis.Chukhina.entities.utils;

import ru.kpfu.itis.Chukhina.entities.utils.exceptions.UserInteracorReadException;
import ru.kpfu.itis.Chukhina.entities.utils.exceptions.UserInteractorWriteException;

public interface UserInteractor {

    String read() throws UserInteracorReadException;
    void write(String output) throws UserInteractorWriteException;
}
