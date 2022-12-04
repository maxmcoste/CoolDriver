package org.maxmcold.readable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;


public class ReadableFactory {

    public enum Type {TEMPERATURE,WINDOWPOSITION,HUMIDITY,SUNPOSITION,AWNINGINPUT,BOILERSTATUS}

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public static Readable getReadable(Type readableType)  {
        logger.debug("Calling readable with type "+readableType);
        //Set default Readable to temperature (most used)
        Readable out = new Temperature();
        switch (readableType){

            case TEMPERATURE -> out = new Temperature();
            case SUNPOSITION -> out = new SunPosition();
            case HUMIDITY -> out = new Humidty();
            case WINDOWPOSITION -> out = new WindowPosition();
            case AWNINGINPUT -> out = new AwningPosition();
            case BOILERSTATUS -> out = new BoilerStatus();


        }
        return out;
    }

}



