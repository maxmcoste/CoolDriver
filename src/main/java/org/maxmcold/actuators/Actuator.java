package org.maxmcold.actuators;

import java.util.HashMap;

public interface Actuator {
   void execute(HashMap<String,Object> params);

    int switchOn();
    int switchOff();

}
