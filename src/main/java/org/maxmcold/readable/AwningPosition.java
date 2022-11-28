package org.maxmcold.readable;

import java.util.HashMap;

public class AwningPosition implements Readable{

    @Override
    public long getValue() {

        //TODO manage errors
        ReadableImpl ri = new ReadableImpl(ReadableFactory.Type.AWNINGINPUT);
        return ri.getValue();
    }


    @Override
    public HashMap<String, Object> getValues() {
        ReadableImpl ri = new ReadableImpl(ReadableFactory.Type.AWNINGINPUT);
        return ri.getValues();
    }
}
