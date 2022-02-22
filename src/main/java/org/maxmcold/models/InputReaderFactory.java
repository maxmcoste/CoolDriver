package org.maxmcold.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.utils.CoolProperties;

import javax.print.DocFlavor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Properties;

public class InputReaderFactory {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public static InputReader getInputReader(Readable.Type type) {
        String tmp = "";
        String readType = "";
        //set default input reader as FileInputReader
        InputReader out = null;
        try {


            switch (type){

                case AWNINGINPUT -> {
                    tmp = CoolProperties.sensorAwningsStreamType;
                    readType = CoolProperties.awningsFieldName;
                }
                case HUMIDITY -> {
                    tmp = CoolProperties.sensorHumidityStreamType;
                    readType = CoolProperties.humidityFieldName;
                }
                case WINDOWPOSITION -> {
                    tmp = CoolProperties.sensorWindowstatusStreamType;
                    readType = CoolProperties.windowstatusFieldName;
                }
                case TEMPERATURE -> {
                    tmp = CoolProperties.sensorTemperatureStreamType;
                    readType = CoolProperties.temperatureFieldName;
                }
                case SUNPOSITION -> {
                    tmp = CoolProperties.sensorSunStreamType;
                    readType = CoolProperties.sunFieldName;
                }
            }
            //TODO: Make the package dynamic
            String className = "org.maxmcold.models."+buildClassName(tmp);
            logger.debug("ClassName: " + className);
            Class c = Class.forName(className);
            out = (InputReader) c.getDeclaredConstructor(String.class).newInstance(readType);




        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }

        return out;
    }
    private static String buildClassName(String type){

        String out = type.substring(0,1);
        out = out.toUpperCase() + type.substring(1,type.length()) + "InputReader";

        return out;

    }


}
