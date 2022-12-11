package org.maxmcold.items;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Item {

    enum Status{ON, OFF}
    boolean openStatus();
    boolean closeStatus();
    boolean onStatus();
    boolean offStatus();

    Status getStatus();
    boolean setPosition(Long position) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    boolean setStatus(Status status) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException;
    String getType();
    String getWriterType();
    String getWriterURI();
    Long getPosition();
    String getID();
    void setID(String id);



}
