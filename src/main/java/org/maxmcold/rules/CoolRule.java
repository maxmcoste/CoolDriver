package org.maxmcold.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.maxmcold.Controller;
import org.maxmcold.actuators.Actuator;
import org.maxmcold.actuators.ActuatorFactory;
import org.maxmcold.actuators.ThermoActuator;
import org.maxmcold.utils.CoolProperties;

import java.io.IOException;

@Rule(name = "Cooling rule", description = "if it's hot then cool down")
public class CoolRule {
    final static Logger logger = LogManager.getLogger(Controller.class.getName());



    @Condition
    public boolean when(@Fact("temperature") int temperature) {
        int refTemp = 20;
        try {
            refTemp = Integer.parseInt(CoolProperties.getProperties().getProperty("refTemp"));
        } catch (IOException e) {
            logger.error("Error on getting reference temperature from properties. Setting to default 20 degrees Celsius");

        }

        return temperature > refTemp;
    }

    @Action
    public void coolDown(){
        try {
            Actuator a = ActuatorFactory.getActuator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
