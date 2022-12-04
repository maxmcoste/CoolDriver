package org.maxmcold.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.readable.ReadableFactory;
import org.maxmcold.utils.CoolProperties;

import java.lang.reflect.InvocationTargetException;

public class InputReaderFactory {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public static InputReader getInputReader(ReadableFactory.Type type) {
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
                case BOILERSTATUS -> {
                    tmp = CoolProperties.boilerReadType;
                    readType = CoolProperties.boilerFieldName;
                }
            }

            String packageName = InputReaderFactory.class.getPackageName();
            String className = packageName+"."+buildClassName(tmp);
            //logger.debug("ClassName: " + className);
            Class c = Class.forName(className);
            //FileInputReader fir = new FileInputReader(readType);
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
