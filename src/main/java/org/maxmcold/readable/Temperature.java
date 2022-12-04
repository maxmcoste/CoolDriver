package org.maxmcold.readable;

import java.util.HashMap;

public class Temperature implements Readable {
    //TODO all readables will implement the same code, create a library or common implementation

    @Override
    public long getLongValue() {
        return 0;
    }

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
        ReadableDefaultImpl ri = new ReadableDefaultImpl(ReadableFactory.Type.TEMPERATURE);
        return ri.getValue();
    }


    @Override
    public HashMap<String, Object> getValues() {
        ReadableDefaultImpl ri = new ReadableDefaultImpl(ReadableFactory.Type.TEMPERATURE);
        return ri.getValues();
    }

    @Override
    public String getStringValue() {
        return null;
    }
}
