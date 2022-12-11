package org.maxmcold.io;


import org.maxmcold.items.Item;

import java.io.IOException;

public interface OutputWriter {
    boolean write(Item item, String stream) throws IOException;



}
