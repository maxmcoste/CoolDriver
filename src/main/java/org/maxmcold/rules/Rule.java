package org.maxmcold.rules;

import org.maxmcold.actuators.Actuator;


public interface Rule {

    int perform(org.maxmcold.models.Readable readable, Actuator a);
}
