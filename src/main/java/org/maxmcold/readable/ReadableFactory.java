package org.maxmcold.readable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;


public class ReadableFactory {

    public enum Type {TEMPERATURE,WINDOWPOSITION,HUMIDITY,SUNPOSITION,AWNINGINPUT}

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public static org.maxmcold.models.Readable getReadable(Type readableType)  {

        //Set default Readable to temperature (most used)
        org.maxmcold.models.Readable out = (org.maxmcold.models.Readable) new Temperature();
        switch (readableType){

            case TEMPERATURE -> { out = (org.maxmcold.models.Readable) new Temperature();}

            case HUMIDITY -> {}
            case SUNPOSITION -> {}
            case WINDOWPOSITION -> {}
            case AWNINGINPUT -> {}


        }
        return out;
    }

}



