package org.maxmcold.items;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.io.FileInputReader;

public class Window {

    final static Logger logger = LogManager.getLogger(FileInputReader.class);

    public boolean open(){
        logger.debug("Opening window");
        return true;
    }

    public boolean close(){
        logger.debug("Closing Window");
        return true;
    }

    public boolean setPosition(int position){
        logger.debug("Setting position to: " + position);
        return true;
    }
}
