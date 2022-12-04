package org.maxmcold.readable;

import java.util.HashMap;

public class AwningPosition implements Readable{

    @Override
    public long getLongValue() {
        return 0;
    }

    @Override
    public long getValue() {

        //TODO manage errors
        ReadableDefaultImpl ri = new ReadableDefaultImpl(ReadableFactory.Type.AWNINGINPUT);
        return ri.getValue();
    }


    @Override
    public HashMap<String, Object> getValues() {
        ReadableDefaultImpl ri = new ReadableDefaultImpl(ReadableFactory.Type.AWNINGINPUT);
        return ri.getValues();
    }

    @Override
    public String getStringValue() {
        return null;
    }
}
