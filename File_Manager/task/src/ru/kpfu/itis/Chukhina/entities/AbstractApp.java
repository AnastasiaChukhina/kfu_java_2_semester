package ru.kpfu.itis.Chukhina.entities;

/**
 * @author Chukhina Anastasia
 */
public abstract class AbstractApp {

    public AbstractApp(){
        init();
        start();
    }

    public abstract void init();
    public abstract void start();
}
