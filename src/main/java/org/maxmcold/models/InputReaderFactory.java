package org.maxmcold.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.utils.CoolProperties;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Properties;

public class InputReaderFactory {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public static InputReader getInputReader(Readable.Type type) {


        try {

            Properties properties = CoolProperties.getProperties();
            String tmp = "";
            switch (type){
                case AWNINGINPUT -> tmp = CoolProperties.sensorAwningsStreamType;
                case HUMIDITY -> tmp = CoolProperties.sensorHumidityStreamType;
                case WINDOWPOSITION -> tmp = CoolProperties.sensorWindowstatusStreamType;
                case TEMPERATURE -> tmp = CoolProperties.sensorTemperatureStreamType;
                case SUNPOSITION -> tmp = CoolProperties.sensorSunStreamType;
            }
            //TODO: Make the package dynamic
            String className = "org.maxmcold.models."+buildClassName(tmp);
            logger.debug("ClassName:" + className);
            Class c = Class.forName(className);
            return (InputReader) c.getDeclaredConstructor(type.getClass()).newInstance(type);


        } catch(IOException e){
            logger.error(e.getMessage(), e);
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
        } finally {
            //in case of error return the default input reader which is file
            return new FileInputReader(type);
        }


    }
    private static String buildClassName(String type){

        String out = type.substring(0,1);
        out = out.toUpperCase() + type.substring(1,type.length()) + "InputReader";

        return out;

    }


}
