package org.maxmcold.readable;

import java.util.HashMap;

public class SunPosition implements Readable{

    @Override
    public long getValue() {

        //TODO manage errors
        ReadableImpl ri = new ReadableImpl(ReadableFactory.Type.SUNPOSITION);
        return ri.getValue();
    }


    @Override
    public HashMap<String, Object> getValues() {
        ReadableImpl ri = new ReadableImpl(ReadableFactory.Type.SUNPOSITION);
        return ri.getValues();
    }
}
