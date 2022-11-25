package org.maxmcold.items;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.models.FileInputReader;

public class Boiler implements Item{

    final static Logger logger = LogManager.getLogger(FileInputReader.class);

    public boolean switchOn(){
        logger.debug("Switching on boiler");
        return true;
    }

    public boolean switchOff(){
        logger.debug("Switching off boiler");
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
}
