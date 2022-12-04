package org.maxmcold.items;

public interface Item {

    enum Status{ON, OFF}
    boolean openStatus();
    boolean closeStatus();
    boolean onStatus();
    boolean offStatus();

    Status getStatus();
    boolean setPosition(Long position);
    boolean setStatus(Status status);
    String getType();
    String getWriterType();
    String getWriterURL();
    Long getPosition();



}
