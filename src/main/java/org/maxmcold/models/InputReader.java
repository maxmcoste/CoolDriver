package org.maxmcold.models;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;

/**
 * Read inputs from any source
 * As of now reads only from a file
 */
public class InputReader {
    final static Logger logger = LogManager.getLogger(InputReader.class);


    

    String filename = "/Users/massimodelvecchio/in.txt";
    File f = new File(filename);
    Readable readable;
    public InputReader(Readable.Type type){
        logger.debug("Input reader called");
        switch (type) {
            case TEMPERATURE:
                this.readable = new Readable();
                this.readFromFile();
                break;
            case SUN:
                //do nothing
                break;
            default:
                this.doNothing();
                break;
        }



    }
    public Readable getValues(){
        return this.readable;
    }
    private void readFromFile(){
        try {
            logger.debug("Start reading from file..." + f);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String in = br.readLine();
            logger.debug("in value:" + in);
            readable.setCode("tempcheck");
            readable.setDescription("Check input temperature");
            Long longValue = Long.parseLong(in);
            readable.addValue("temp",longValue);

            br.close();

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    private void doNothing(){}
}
