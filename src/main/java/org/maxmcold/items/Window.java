package org.maxmcold.items;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.io.FileInputReader;
import org.maxmcold.io.OutputWriter;
import org.maxmcold.io.OutputWriterFactory;
import org.maxmcold.utils.CoolProperties;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Window implements Item {

    final static Logger logger = LogManager.getLogger(FileInputReader.class);
    String id;

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
    public Status getStatus() {
        return null;
    }

    @Override
    public boolean setPosition(Long position) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (position == this.getPosition()) return true;

        OutputWriter ow = OutputWriterFactory.getOutputWriter(this);
        ow.write(this,this.getType() + ".position="+this.getPosition());
        logger.debug("Changing position to... " + position);
        return true;

    }

    @Override
    public boolean setStatus(Status status) {
        return false;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getWriterType() {
        return null;
    }

    @Override
    public String getWriterURI() {
        return CoolProperties.windowWriterFile;
    }

    @Override
    public Long getPosition() {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public void setID(String name) {

        this.id = name;
    }
}
