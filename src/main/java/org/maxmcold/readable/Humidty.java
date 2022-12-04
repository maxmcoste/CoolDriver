package org.maxmcold.readable;

import java.util.HashMap;

public class Humidty implements Readable {
    @Override
    public long getLongValue() {
        return 0;
    }

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
        ReadableDefaultImpl ri = new ReadableDefaultImpl(ReadableFactory.Type.HUMIDITY);
        return ri.getValue();
    }


    @Override
    public HashMap<String, Object> getValues() {
        ReadableDefaultImpl ri = new ReadableDefaultImpl(ReadableFactory.Type.HUMIDITY);
        return ri.getValues();
    }

    @Override
    public String getStringValue() {
        return null;
    }
}

