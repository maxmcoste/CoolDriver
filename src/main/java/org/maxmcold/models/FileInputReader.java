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


    public FileInputReader(){

        this.readable = new Readable();

        this.readable.addValue(CoolProperties.temperatureFieldName,
                this.getTemperature());

        this.readable.addValue(
                CoolProperties.humidityFieldName,
                this.getValue(CoolProperties.sensorHumidityStreamTypeFileName,
                        CoolProperties.humidityFieldName)
        );

        this.readable.addValue(
                CoolProperties.awningsFieldName,
                this.getAwningsValue()
        );

        this.readable.addValue(
                CoolProperties.sunFieldName,
                this.getValue(CoolProperties.sensorSunStreamTypeFileName,
                        CoolProperties.sunFieldName)
        );

        this.readable.addValue(
                CoolProperties.windowstatusFieldName,
                this.getValue(CoolProperties.sensorWindowstatusStreamTypeFileName,
                        CoolProperties.windowstatusFieldName)
        );



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

    public Readable getValues(){
        return this.readable;
    }

    private void doNothing(){}
}
