package org.maxmcold.items;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.io.FileInputReader;

public class Window implements Item {

    final static Logger logger = LogManager.getLogger(FileInputReader.class);

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
        return null;
    }

    @Override
    public boolean setPosition(Long position) {
        return false;
    }

    @Override
    public boolean setStatus(Status status) {
        return false;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getWriterType() {
        return null;
    }

    @Override
    public String getWriterURL() {
        return null;
    }

    @Override
    public Long getPosition() {
        return null;
    }
}
