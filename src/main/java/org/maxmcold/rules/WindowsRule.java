package org.maxmcold.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.actions.WindowAction;
import org.maxmcold.actuators.Actuator;
import org.maxmcold.models.Action;

public class WindowsRule implements Rule{

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String code;
    private String name;

    @Override
    public Action getAction(){
        return new WindowAction();
    }
    private Long refTemp ;
    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public int perform(org.maxmcold.models.Readable r, Actuator a){
        logger.debug("Entering Actuator");


        return 1;
    }

}
