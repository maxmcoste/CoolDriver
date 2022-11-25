package org.maxmcold.actuators;

import java.io.IOException;

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
