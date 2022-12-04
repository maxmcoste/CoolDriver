package org.maxmcold.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class CoolProperties {


    private static Properties instance;
    public static String activeSensor;
    public static String currentConfig;

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
    public static String houseName;
    public static String houseDescription;
    public static String boilerWriterType;
    public static String boilerWriterFile;

    public static String ruleConfigFile;
    public static String boilerReadType;
    public static String boilerReaderURL;
    public static String delay;
    public static String boilerFieldName;

    public static Properties getProperties() throws IOException {

        //Use a singleton value
        if (null != instance) return instance;
        Properties prop;
        logger.debug("SETTING ROOT PATH...");
        Path currentWorkingDir = Paths.get("").toAbsolutePath();

        String rootPath = currentWorkingDir.normalize().toString();


        //String tempPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        logger.debug(rootPath);
        logger.debug("PRINTING RESOURCE PATH...");

        //logger.debug(tempPath);
        String appConfigPath = rootPath + "/target/classes/config.properties";

        prop = new Properties();
        prop.load(new FileInputStream(appConfigPath));
        logger.debug(appConfigPath);

        //Configure overall params
        houseName = prop.getProperty("house.name");
        houseDescription = prop.getProperty("house.description");
        currentConfig = prop.getProperty("currentConfig");
        activeSensor = prop.getProperty("base.controller.activeSensors");
        ruleConfigFile = prop.getProperty("rule.config.file");
        delay = prop.getProperty("read.interval");

        //Configure all properties from file
        temperatureFieldName = prop.getProperty("temperature.field.name");
        humidityFieldName = prop.getProperty("humidity.field.name");
        awningsFieldName = prop.getProperty("awnings.field.name");
        sunFieldName = prop.getProperty("sun.field.name");
        windowstatusFieldName = prop.getProperty("windowstatus.field.name");
        boilerFieldName = prop.getProperty("boiler.field.name");


        temperatureCodeName = prop.getProperty("temperature.code.name");
        humidityCodeName = prop.getProperty("humidity.code.name");
        awningsCodeName = prop.getProperty("awnings.code.name");
        windowstatusCodeName = prop.getProperty("windowstatus.code.name");
        sunCodeName = prop.getProperty("sun.code.name");

        sensorTemperatureStreamType = prop.getProperty("sensor.temperature.stream.type");
        sensorTemperatureStreamTypeFileName = prop.getProperty("sensor.temperature.stream.type.uri.name");

        sensorHumidityStreamType = prop.getProperty("sensor.humidity.stream.type");
        sensorHumidityStreamTypeFileName = prop.getProperty("sensor.humidity.stream.type.uri.name");

        sensorSunStreamType = prop.getProperty("sensor.sunposition.stream.type");
        sensorSunStreamTypeFileName = prop.getProperty("sensor.sunposition.stream.type.uri.name");

        sensorAwningsStreamType = prop.getProperty("sensor.awnings.stream.type");
        sensorAwningsStreamTypeFileName = prop.getProperty("sensor.awnings.stream.type.uri.name");

        sensorWindowstatusStreamType = prop.getProperty("sensor.windowstatus.stream.type");
        sensorWindowstatusStreamTypeFileName = prop.getProperty("sensor.windowstatus.stream.type.uri.name");

        boilerWriterType = prop.getProperty("item.boiler.write.type");
        boilerWriterFile = prop.getProperty("item.boiler.filewriter.file.name");

        boilerReadType = prop.getProperty("sensor.boiler.stream.type");
        boilerReaderURL = prop.getProperty("sensor.boiler.stream.type.uri.name");

        instance = prop;
        return instance;
    }
    public static void loadProperties() throws IOException {
        CoolProperties.getProperties();
    }
    public static String getFileName(String type){

        Properties prop = new Properties();
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        String appConfigPath = rootPath + "config.properties";

        try {
            prop.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        //TODO change this logic... too much coupled to config logic
        String key = "sensor." +type+".stream.type.uri.name";
        return prop.getProperty(key);
    }

}
