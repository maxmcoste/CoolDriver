package org.maxmcold.readable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;

import java.util.HashMap;

public class SunPosition implements Readable{
    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    @Override
    public long getValue() {

        //TODO manage errors
        ReadableImpl ri = new ReadableImpl(ReadableFactory.Type.SUNPOSITION);
        if (null == ri) {
            logger.debug("READABLE: SUNPOSITION IS NULL");
        }
        return ri.getValue();
    }


    @Override
    public HashMap<String, Object> getValues() {
        ReadableImpl ri = new ReadableImpl(ReadableFactory.Type.SUNPOSITION);
        return ri.getValues();
    }
}
