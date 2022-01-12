package org.maxmcold.actuators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;

import java.util.HashMap;


public class ThermoActuator implements Actuator {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());
    public int status = 0;

    public void execute(HashMap<String,Object> params){

    }

    public int switchOn(){
        if (status == 1) logger.info("Already On -> do nothing");
        else {
            logger.info("SWITCHING ON...");
            this.status = 1;
        }
        return status;

    }
    public int switchOff(){
        if (status == 0) logger.info("Already Off -> do nothing");
        else {
            logger.info("SWITCHING OFF...");
            this.status = 0;
        }
        return status;
    }



}
