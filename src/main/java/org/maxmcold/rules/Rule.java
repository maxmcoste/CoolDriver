package org.maxmcold.rules;

import org.maxmcold.Actuator;


public interface Rule {
    public Actuator perform(org.maxmcold.models.Readable readable);
}
