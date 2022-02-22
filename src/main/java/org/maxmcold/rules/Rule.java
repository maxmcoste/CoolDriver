package org.maxmcold.rules;

import org.maxmcold.actuators.Actuator;
import org.maxmcold.models.Action;
import org.maxmcold.models.OutputWriter;


public interface Rule {
    String getCode();
    String getName();
    Action getAction();
    int perform(org.maxmcold.models.Readable readable, Actuator a);
}
