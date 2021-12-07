package org.maxmcold.models;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.utils.CoolProperties;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * Read inputs from any source
 * As of now reads only from a file
 */
public class FileInputReader implements InputReader{

    final static Logger logger = LogManager.getLogger(FileInputReader.class);
    private Readable.Type type;
    Readable readable;

    @Override
    public Readable getReadable() {
        return readable;
    }

    @Override
    public Readable.Type getType() {
        return this.type;
    }

    public void setReadable(Readable readable) {
        this.readable = readable;
    }


    public FileInputReader(Readable.Type t){

        this.type = t;
        switch (t) {
            case TEMPERATURE:

                this.readable = new Readable();
                this.readable.addValue(CoolProperties.temperatureFieldName,this.getTemperature());
                break;
            case SUN:
                //do nothing
                break;
            case AWNINGS:
                //do nothing;
                break;
            case HUMIDITY:
                //do nothing
                break;
            case WINDOWSTATUS:
                //do nothing
                break;
            default:
                this.doNothing();
                break;
        }



    }
    private Long getTemperature(){
        Long longValue = null;
        try {

            String filename = CoolProperties.sensorTemperatureStreamTypeFileName;
            File f = new File(filename);

            logger.debug("Start reading from file..." + f);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String in = br.readLine();
            logger.debug("in value:" + in);
            readable.setCode(CoolProperties.temperatureCodeName);
            //readable.setDescription("Check input temperature");
            longValue = Long.parseLong(in);
            readable.addValue(CoolProperties.temperatureCodeName,longValue);

            br.close();

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return longValue;
    }
    public Readable getValues(){
        return this.readable;
    }

    private void doNothing(){}
}
