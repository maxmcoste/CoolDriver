package org.maxmcold.models;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.utils.CoolProperties;

import java.io.IOException;
import java.util.HashMap;

public class Readable {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    private static CoolProperties properties;
    private String code;
    private String description;


    public Readable (Type type){
        try {
            CoolProperties.getProperties();
        } catch (IOException e) {
            logger.error("IOError to get Properties", e);
        }
        switch (type){
            case TEMPERATURE -> {
                this.code = CoolProperties.temperatureCodeName;
                this.description = CoolProperties.temperatureFieldName;
            }
            case HUMIDITY -> {
                this.code = CoolProperties.humidityCodeName;
                this.description = CoolProperties.humidityFieldName;
            }
            case SUNPOSITION -> {
                this.code = CoolProperties.sunCodeName;
                this.description = CoolProperties.sunFieldName;
            }
            case WINDOWPOSITION -> {
                this.code = CoolProperties.windowstatusCodeName;
                this.description = CoolProperties.windowstatusFieldName;
            }
        }
    }
    public enum Type {TEMPERATURE,WINDOWPOSITION,HUMIDITY,SUNPOSITION,AWNINGINPUT}

    public HashMap<String, Object> getValues() {
        return values;
    }

    private HashMap<String,Object> values;

    public String getCode() {
        return code;
    }

    public void addValue(String key, Object value){
        if (null == values){
            this.values = new HashMap<>();
        }
        this.values.put(key,value);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
