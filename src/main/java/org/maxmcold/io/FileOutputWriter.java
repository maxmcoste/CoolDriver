package org.maxmcold.io;

import org.maxmcold.items.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileOutputWriter implements OutputWriter{

    public FileOutputWriter(){

    }

    @Override
    public boolean write(Item item) throws IOException {

        String fileName = item.getWriterURL();
        if (null == fileName) throw new IOException("Missine filename for writer, check your configuration file");
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String status = "ON";
        switch (item.getStatus()){
            case ON -> status = "ON";
            case OFF -> status = "OFF";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        writer.write(dtf.format(now) + " status ---> " + status + " position ---> "+item.getPosition() +"\n");
        writer.flush();
        return false;
    }
}
