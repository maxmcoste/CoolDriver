package org.maxmcold.rules;

import org.maxmcold.actuators.Actuator;
import org.maxmcold.models.Action;
import org.maxmcold.models.ReadableImpl;


public interface Rule {

    String getCode();
    String getName();
    Action getAction();

    int perform(ReadableImpl readableImpl, Actuator a);
    boolean when();
    boolean and();
    boolean moreThan();
    boolean lessThan();

}
