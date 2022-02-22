package org.maxmcold.models;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.utils.CoolProperties;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;



public class FileInputReader implements InputReader{
    HashMap<String,String> attributes = new HashMap<>();
    HashMap<String,Object> values = new HashMap<>();

    final static Logger logger = LogManager.getLogger(FileInputReader.class);


    @Override
    public HashMap<String, String> getAttributes(){
        return attributes;
    }
    @Override
    public void putAttribute(String key, String value){
        attributes.put(key,value);
    }
    @Override
    public String getReaderType() {
        return readerType;
    }

    String readerType;
    String fileName;

    public Object getValue(){
        String out = null;
        try {

            File f = new File(this.fileName);
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

    public HashMap<String, Object> getValues(){


        return  this.values;
    }

    public FileInputReader(String valueType){

        this.readerType = "file";

        this.fileName = CoolProperties.getFileName(valueType);

        try {

            File f = new File(this.fileName);
            BufferedReader br = new BufferedReader(new FileReader(f));

            String in = br.readLine();

            while (in != null){
                String[] tokens = this.getData(in);
                this.values.put(tokens[0],tokens[1]);
                in = br.readLine();
            }
            br.close();

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());

        } catch (IOException e) {
            logger.error(e.getMessage());
        }




    }

    private String[] getData(String line){
        String[] tokens = new String[1];
        if (line == null) return tokens;
        tokens = line.split("=");
        if (tokens[0] == null)  tokens[0] = "no key";
        if (tokens[1] == null)  tokens[1] = "no value";
        return tokens;
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

    private Long getValue(String fieldName){
        Long longValue = null;
        try {
            File f = new File(this.fileName);
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
}
