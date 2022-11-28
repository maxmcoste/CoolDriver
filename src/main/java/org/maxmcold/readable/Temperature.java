package org.maxmcold.readable;

import org.maxmcold.io.InputReader;
import org.maxmcold.io.InputReaderFactory;
import org.maxmcold.statuses.Status;

import java.util.HashMap;

public class Temperature implements Readable {
    //TODO all readables will implement the same code, create a library or common implementation
    /**
     * Returns the last long value of input stream temperature written in the form:
     * key = <i>value</i>
     * where key is a String and value is a long
     * ...
     * @return long
     */
    @Override
    public long getValue() {

        //TODO manage errors
        ReadableImpl ri = new ReadableImpl(ReadableFactory.Type.TEMPERATURE);
        return ri.getValue();
    }


    @Override
    public HashMap<String, Object> getValues() {
        ReadableImpl ri = new ReadableImpl(ReadableFactory.Type.TEMPERATURE);
        return ri.getValues();
    }
}
