package org.maxmcold.items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.io.*;
import org.maxmcold.readable.Readable;
import org.maxmcold.readable.ReadableFactory;
import org.maxmcold.utils.CoolProperties;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Boiler implements Item{
    Long position;
    String status;
    final static Logger logger = LogManager.getLogger(FileInputReader.class);
    private String id;

    public Boiler(){
        //TODO: manage errors

        InputReader inputReader = InputReaderFactory.getInputReader(ReadableFactory.Type.BOILERSTATUS);
        this.status = inputReader.getValue().toString().trim().split("=")[1].trim();
        this.position = Long.parseLong("-1");


    }

    public boolean switchOn(){
        logger.debug("Switching on boiler");
        return true;
    }


    @Override
    public boolean openStatus() {
        return false;
    }

    @Override
    public boolean closeStatus() {
        return false;
    }

    @Override
    public boolean onStatus() {
        return false;
    }

    @Override
    public boolean offStatus() {
        return false;
    }

    @Override
    public Status getStatus(){
        if (this.status.equals("ON")) return Status.ON;
        else return Status.OFF;

    }
    public Status getCurrentStatus() {

        Readable read = ReadableFactory.getReadable(ReadableFactory.Type.BOILERSTATUS);
        this.status = read.getStringValue();
        switch (this.status) {
            case "ON" -> {
                return Status.ON;
            }
            case "OFF" -> {
                return Status.OFF;
            }
            default -> {
                return Status.ON;
            }
        }

    }

    @Override
    public boolean setPosition(Long posit) {
        this.position = posit;
        return true;
    }

    @Override
    public boolean setStatus(Status stat) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        if (stat == this.getCurrentStatus()) return true;

        switch (stat){
            case ON -> {this.status = "ON";}
            case OFF -> {this.status = "OFF";}
            default -> {this.status = "UNDEFINED";}
        }
        OutputWriter ow = OutputWriterFactory.getOutputWriter(this);
        ow.write(this, this.getID()+".status="+this.getStatus()+"\n");
        logger.debug("Changing status to... " + status);
        return true;
    }



    @Override
    public String getType() {

        return CoolProperties.boilerFieldName;
    }

    @Override
    public String getWriterType() {

        return CoolProperties.boilerWriterType;
    }

    @Override
    public String getWriterURI() {
        return CoolProperties.boilerWriterFile;
    }

    @Override
    public Long getPosition() {
        if (this.position == null) return Long.parseLong("-1");
        return this.position;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public void setID(String name) {
        this.id = name;
    }


}
