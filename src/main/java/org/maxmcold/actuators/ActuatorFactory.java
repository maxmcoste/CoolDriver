package org.maxmcold.actuators;

import org.maxmcold.models.Readable;
import org.maxmcold.utils.CoolProperties;

import java.io.IOException;
import java.util.Properties;

public class ActuatorFactory {
    private static Actuator instance;

    public static Actuator getActuator() throws IOException {
        //TOD0: returns only ThermoActuator for now
        if (null == instance) {

            instance = new ThermoActuator();

        }
        return instance;
    }
}
