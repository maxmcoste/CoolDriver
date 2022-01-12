package org.maxmcold.events;

public interface Event {

    enum Type{USER,SENSOR,SYSTEM}
    void onReceive();

}
