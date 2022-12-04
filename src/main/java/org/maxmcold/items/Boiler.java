package org.maxmcold.items;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.io.FileInputReader;
import org.maxmcold.utils.CoolProperties;

public class Boiler implements Item{
    Long position;
    String status;
    final static Logger logger = LogManager.getLogger(FileInputReader.class);

    public Boiler(){
        //TODO: read latest status from InputReader
        this.status = "ON";

    }

    public boolean switchOn(){
        logger.debug("Switching on boiler");
        return true;
    }


    @Override
    public boolean openStatus() {
        return false;
    }

    @Override
    public boolean closeStatus() {
        return false;
    }

    @Override
    public boolean onStatus() {
        return false;
    }

    @Override
    public boolean offStatus() {
        return false;
    }

    @Override
    public Status getStatus() {

        switch (this.status) {
            case "ON" -> {
                return Status.ON;
            }
            case "OFF" -> {
                return Status.OFF;
            }
            default -> {
                return Status.ON;
            }
        }

    }

    @Override
    public boolean setPosition(Long posit) {
        this.position = posit;
        return true;
    }

    @Override
    public boolean setStatus(Status status) {
        logger.debug("Changing status to " + status);
        return false;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getWriterType() {

        return CoolProperties.boilerWriterType;
    }

    @Override
    public String getWriterURL() {
        return CoolProperties.boilerWriterFile;
    }

    @Override
    public Long getPosition() {
        if (this.position == null) return Long.parseLong("-1");
        return this.position;
    }


}
