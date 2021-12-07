package org.maxmcold.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CoolProperties {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public static String temperatureFieldName;
    public static String humidityFieldName;
    public static String awningsFieldName;
    public static String sunFieldName;
    public static String windowstatusFieldName;


    public static String temperatureCodeName;
    public static String humidityCodeName;
    public static String awningsCodeName;
    public static String windowstatusCodeName;
    public static String sunCodeName;

    public static String sensorTemperatureStreamType;
    public static String sensorTemperatureStreamTypeFileName;
    public static String sensorHumidityStreamType;
    public static String sensorHumidityStreamTypeFileName;
    public static String sensorSunStreamType;
    public static String sensorSunStreamTypeFileName;
    public static String sensorAwningsStreamType;
    public static String sensorAwningsStreamTypeFileName;
    public static String sensorWindowstatusStreamType;
    public static String sensorWindowstatusStreamTypeFileName;

    public enum TempInputType {
        FILE,
        KAFKA,
        SOCKET
    }

    public static Properties getProperties() throws IOException {

        Properties prop;
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        logger.debug(rootPath);
        String appConfigPath = rootPath + "config.properties";
        prop = new Properties();
        prop.load(new FileInputStream(appConfigPath));

        //Configure all properties from file
        temperatureFieldName = prop.getProperty("temperature.field.name");
        humidityFieldName = prop.getProperty("humidity.field.name");
        awningsFieldName = prop.getProperty("awnings.field.name");
        sunFieldName = prop.getProperty("sun.field.name");
        windowstatusFieldName = prop.getProperty("windowstatus.field.name");

        temperatureCodeName = prop.getProperty("temperature.code.name");
        humidityCodeName = prop.getProperty("humidity.code.name");
        awningsCodeName = prop.getProperty("awnings.code.name");
        windowstatusCodeName = prop.getProperty("windowstatus.code.name");
        sunCodeName = prop.getProperty("sun.code.name");

        sensorTemperatureStreamType = prop.getProperty("sensor.temperature.stream.type");
        sensorTemperatureStreamTypeFileName = prop.getProperty("sensor.temperature.stream.type.file.name");


        sensorHumidityStreamType = prop.getProperty("sensor.humidity.stream.type");
        sensorHumidityStreamTypeFileName = prop.getProperty("sensor.humidity.stream.type.file.name");


        sensorSunStreamType = prop.getProperty("sensor.sun.stream.type");
        sensorSunStreamTypeFileName = prop.getProperty("sensor.sun.stream.type.file.name");


        sensorAwningsStreamType = prop.getProperty("sensor.awnings.stream.type");
        sensorAwningsStreamTypeFileName = prop.getProperty("sensor.awnings.stream.type.file.name");


        sensorWindowstatusStreamType = prop.getProperty("sensor.windowstatus.stream.type");
        sensorWindowstatusStreamTypeFileName = prop.getProperty("sensor.windowstatus.stream.type.file.name");

        return prop;
    }

}
