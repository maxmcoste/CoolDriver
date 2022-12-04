package org.maxmcold.readable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;


public class ReadableFactory {

    public enum Type {TEMPERATURE,WINDOWPOSITION,HUMIDITY,SUNPOSITION,AWNINGINPUT,BOILERSTATUS}

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public static Readable getReadable(Type readableType)  {

        //Set default Readable to temperature (most used)
        Readable out = (Readable) new Temperature();
        switch (readableType){

            case TEMPERATURE -> { out = (Readable) new Temperature();}
            case HUMIDITY -> { out = (Readable) new Humidty();}
            case SUNPOSITION -> {out = (Readable) new SunPosition();}
            case WINDOWPOSITION -> {out = (Readable) new WindowPosition();}
            case AWNINGINPUT -> {out = (Readable) new AwningPosition();}


        }
        return out;
    }

}



