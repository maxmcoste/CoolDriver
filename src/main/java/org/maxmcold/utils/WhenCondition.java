package org.maxmcold.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;

import java.util.StringTokenizer;

public class WhenCondition {
    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public WhenCondition(String when){
        StringTokenizer st = new StringTokenizer(when);
        while (st.hasMoreElements()){
            logger.debug(st.nextToken());
        }
    }

    private boolean eval(String condition){
        return true;
    }
}
