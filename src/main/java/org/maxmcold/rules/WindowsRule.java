package org.maxmcold.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.actuators.Actuator;

public class WindowsRule implements Rule{

    private Long refTemp ;
    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public int perform(org.maxmcold.models.Readable r, Actuator a){
        logger.debug("Entering Actuator");


        return 1;
    }

}
