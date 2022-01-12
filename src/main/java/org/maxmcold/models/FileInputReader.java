package org.maxmcold.models;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.utils.CoolProperties;

import java.io.*;


/**
 * Read inputs from any source
 * As of now reads only from a file
 */
public class FileInputReader implements InputReader{

    final static Logger logger = LogManager.getLogger(FileInputReader.class);

    Readable readable;

    public void setReadable(Readable readable) {
        this.readable = readable;
    }

    public FileInputReader(Readable.Type type){

        this.readable = new Readable(type);

        this.readable.addValue(CoolProperties.temperatureFieldName,
                this.getTemperature());
        switch  (type){
            case TEMPERATURE -> {
                this.readable.addValue(
                        CoolProperties.temperatureFieldName,
                        this.getValue(CoolProperties.sensorTemperatureStreamTypeFileName,
                                CoolProperties.temperatureFieldName)
                );
            }
            case SUNPOSITION -> {
                this.readable.addValue(
                        CoolProperties.sunFieldName,
                        this.getValue(CoolProperties.sensorSunStreamTypeFileName,
                                CoolProperties.sunFieldName)
                );
            }
            case HUMIDITY -> {
                this.readable.addValue(
                        CoolProperties.humidityFieldName,
                        this.getValue(CoolProperties.sensorHumidityStreamTypeFileName,
                                CoolProperties.humidityFieldName)
                );
            }
            case WINDOWPOSITION -> {
                this.readable.addValue(
                        CoolProperties.windowstatusFieldName,
                        this.getValue(CoolProperties.sensorWindowstatusStreamTypeFileName,
                                CoolProperties.windowstatusFieldName)
                );
            }
            case AWNINGINPUT -> {
                this.readable.addValue(
                        CoolProperties.awningsFieldName,
                        this.getAwningsValue()
                );
            }
        }

    }

    private String getAwningsValue(){
        String out = null;
        try {

            File f = new File(CoolProperties.sensorAwningsStreamTypeFileName);
            BufferedReader br = new BufferedReader(new FileReader(f));
            out = br.readLine();
            br.close();

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return out;
    }

    private Long getValue(String filename,String fieldName){
        Long longValue = null;
        try {
            File f = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String in = br.readLine();
            longValue = Long.parseLong(in);
            br.close();

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return longValue;

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
             //readable.setDescription("Check input temperature");
            longValue = Long.parseLong(in);
            br.close();

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return longValue;
    }

    @Override
    public Readable getReadable(){
        return this.readable;
    }


}
