package org.maxmcold.readable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;

import java.util.HashMap;

public class SunPosition implements Readable{
    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    @Override
    public long getLongValue() {
        return 0;
    }

    @Override
    public long getValue() {

        //TODO manage errors
        ReadableDefaultImpl ri = new ReadableDefaultImpl(ReadableFactory.Type.SUNPOSITION);
        if (null == ri) {
            logger.debug("READABLE: SUNPOSITION IS NULL");
        }
        return ri.getValue();
    }


    @Override
    public HashMap<String, Object> getValues() {
        ReadableDefaultImpl ri = new ReadableDefaultImpl(ReadableFactory.Type.SUNPOSITION);
        return ri.getValues();
    }

    @Override
    public String getStringValue() {
        return null;
    }
}
