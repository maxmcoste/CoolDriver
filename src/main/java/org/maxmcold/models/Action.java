package org.maxmcold.models;

import java.io.IOException;

public interface Action {
    public int execute(String actionType) throws IOException;

}
