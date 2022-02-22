package org.maxmcold.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.models.Action;
import org.maxmcold.models.FileInputReader;

import java.io.IOException;

public class WindowAction implements Action {
    final static Logger logger = LogManager.getLogger(FileInputReader.class);

    private int position = -1;
    public int execute(String actionType) throws IOException{
        if (actionType.equalsIgnoreCase("setPosition")){
            this.setPosition();
        }
        return 0;
    }
    private void setPosition() throws IOException {
        if (-1 == this.position) throw new IOException("No position defined for item: windows");
        logger.debug("setting position to: " + this.position);
    }
}
