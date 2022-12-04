package org.maxmcold.readable;

import org.maxmcold.statuses.Status;

import java.util.HashMap;

public interface Readable {

    long getLongValue();
    long getValue();
    HashMap<String,Object> getValues();
    String getStringValue();
}
