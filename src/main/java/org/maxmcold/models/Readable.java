package org.maxmcold.models;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.utils.CoolProperties;

import java.io.IOException;
import java.util.HashMap;

public class Readable {

    final static Logger logger = LogManager.getLogger(Controller.class.getName());

    public InputReader getInputReader() {
        return inputReader;
    }

    private InputReader inputReader;
    private String code;
    private String description;
    private HashMap<String,Object> values;

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
                inputReader = InputReaderFactory.getInputReader(Type.TEMPERATURE);


            }

            case HUMIDITY -> {
                this.code = CoolProperties.humidityCodeName;
                this.description = CoolProperties.humidityFieldName;
                inputReader = InputReaderFactory.getInputReader(Type.HUMIDITY);

            }

            case SUNPOSITION -> {
                this.code = CoolProperties.sunCodeName;
                this.description = CoolProperties.sunFieldName;
                inputReader = InputReaderFactory.getInputReader(Type.SUNPOSITION);

            }

            case WINDOWPOSITION -> {
                this.code = CoolProperties.windowstatusCodeName;
                this.description = CoolProperties.windowstatusFieldName;
                inputReader = InputReaderFactory.getInputReader(Type.WINDOWPOSITION);

            }

            case AWNINGINPUT -> {
                this.code = CoolProperties.awningsCodeName;
                this.description = CoolProperties.awningsFieldName;
                inputReader = InputReaderFactory.getInputReader(Type.AWNINGINPUT);


            }


        }
        this.values = inputReader.getValues();


    }
    public enum Type {TEMPERATURE,WINDOWPOSITION,HUMIDITY,SUNPOSITION,AWNINGINPUT}
    public enum StreamType {FILE,SOCKET,KAFKA}
    public HashMap<String, Object> getValues() {
        return values;
    }



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
