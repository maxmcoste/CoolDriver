package org.maxmcold;

import org.maxmcold.models.OutputWriter;


public class Actuator {
    public Actuator(){

    }
    private OutputWriter writer;
    public enum Status { ON, OFF}

    public boolean setStatus(Status status){

        return true;
    }

}
