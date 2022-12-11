package org.maxmcold.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.Controller;
import org.maxmcold.items.Item;
import org.maxmcold.utils.CoolProperties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileOutputWriter implements OutputWriter{

    final static Logger logger = LogManager.getLogger(Controller.class.getName());


    public FileOutputWriter(){

    }

    @Override
    public boolean write(Item item, String stream) throws IOException {
        if (item.getType().equals(CoolProperties.boilerFieldName)){
            return setBoilerStatus(stream);
        }
        return false;
    }
    private boolean setBoilerStatus(String stream) throws IOException{

        String fileName = CoolProperties.boilerReaderURI;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(stream);
        writer.flush();
        logger.info("Changed boiler status to: " +stream);
        return true;
    }
}
