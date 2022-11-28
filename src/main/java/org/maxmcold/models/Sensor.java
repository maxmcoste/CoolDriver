package org.maxmcold.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.io.InputReader;
import org.maxmcold.io.InputReaderFactory;
import org.maxmcold.readable.Readable;
import org.maxmcold.readable.ReadableFactory;
import org.maxmcold.statuses.Status;
import org.maxmcold.utils.CoolProperties;

import java.io.IOException;

public class Sensor {

    private Area area;
    private InputReader inputReader;
    private Status status;
    private ReadableFactory.Type type;
    private String code;
    private String name;
    private String fullDesc;
    private String desc;
    private Readable readable;

    final static Logger logger = LogManager.getLogger(Controller.class.getName());


    public Sensor(ReadableFactory.Type type){
        readable = ReadableFactory.getReadable(type);

    }

    public Sensor(String typeDesc){
        if (typeDesc.equals(CoolProperties.temperatureCodeName)) {
            type = ReadableFactory.Type.TEMPERATURE;
            desc = CoolProperties.temperatureFieldName;
        }
        else if (typeDesc.equals(CoolProperties.humidityCodeName)){
            type = ReadableFactory.Type.HUMIDITY;
            desc = CoolProperties.humidityFieldName;
        }
        else if (typeDesc.equals(CoolProperties.awningsCodeName)) {
            type = ReadableFactory.Type.AWNINGINPUT;
            desc = CoolProperties.awningsFieldName;
        }
        else if (typeDesc.equals(CoolProperties.sunCodeName)) {
            type = ReadableFactory.Type.SUNPOSITION;
            desc = CoolProperties.sunFieldName;
        }
        else if (typeDesc.equals(CoolProperties.windowstatusCodeName)) {
            type = ReadableFactory.Type.WINDOWPOSITION;
            desc = CoolProperties.windowstatusFieldName;
        }

        readable = ReadableFactory.getReadable(type);

    }

    public Readable getReadable() {
        return readable;
    }





    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ReadableFactory.Type getType() {
        return type;
    }

    public void setType(ReadableFactory.Type type) {
        this.type = type;
    }



    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private InputReader getInputReader() {
        if (this.status == Status.OFF) return null;
        try {
            CoolProperties.getProperties();
        } catch (IOException e) {
            logger.error("IOError to get Properties", e);
        }
        inputReader = InputReaderFactory.getInputReader(ReadableFactory.Type.TEMPERATURE);
        switch (this.type) {

            case TEMPERATURE -> {

                this.code = CoolProperties.temperatureCodeName;
                this.desc = CoolProperties.temperatureFieldName;
                inputReader = InputReaderFactory.getInputReader(ReadableFactory.Type.TEMPERATURE);
                break;

            }

            case HUMIDITY -> {
                this.code = CoolProperties.humidityCodeName;
                this.desc = CoolProperties.humidityFieldName;
                inputReader = InputReaderFactory.getInputReader(ReadableFactory.Type.HUMIDITY);
                break;
            }

            case SUNPOSITION -> {
                this.code = CoolProperties.sunCodeName;
                this.desc = CoolProperties.sunFieldName;
                inputReader = InputReaderFactory.getInputReader(ReadableFactory.Type.SUNPOSITION);
                break;
            }

            case WINDOWPOSITION -> {
                this.code = CoolProperties.windowstatusCodeName;
                this.desc = CoolProperties.windowstatusFieldName;
                inputReader = InputReaderFactory.getInputReader(ReadableFactory.Type.WINDOWPOSITION);
                break;
            }

            case AWNINGINPUT -> {
                this.code = CoolProperties.awningsCodeName;
                this.desc = CoolProperties.awningsFieldName;
                inputReader = InputReaderFactory.getInputReader(ReadableFactory.Type.AWNINGINPUT);
                break;

            }



        }
        return inputReader;
    }














    public String getFullDescription(){

        String out = "";

        return out;
    }

}
