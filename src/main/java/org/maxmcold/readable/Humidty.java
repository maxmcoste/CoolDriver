package org.maxmcold.readable;

import org.maxmcold.io.InputReader;
import org.maxmcold.io.InputReaderFactory;
import org.maxmcold.statuses.Status;

import java.util.HashMap;

public class Humidty implements Readable {
    /**
     * Returns the last long value of input stream humidity written in the form:
     * key = <i>value</i>
     * where key is a String and value is a long
     * humidity is intended in percentage
     * ...
     * @return long
     */
    @Override
    public long getValue() {

        //TODO manage errors
        ReadableImpl ri = new ReadableImpl(ReadableFactory.Type.HUMIDITY);
        return ri.getValue();
    }


    @Override
    public HashMap<String, Object> getValues() {
        ReadableImpl ri = new ReadableImpl(ReadableFactory.Type.HUMIDITY);
        return ri.getValues();
    }
}

