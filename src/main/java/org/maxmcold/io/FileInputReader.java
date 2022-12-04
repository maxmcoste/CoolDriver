package org.maxmcold.io;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.maxmcold.utils.CoolProperties;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FileInputReader implements InputReader {
    HashMap<String,String> attributes = new HashMap<>();
    HashMap<String,Object> values = new HashMap<>();

    final static Logger logger = LogManager.getLogger(FileInputReader.class);

    @Override
    public List<Long> getLongValues(){

       return new ArrayList<>();
    }
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
            if (null == this.fileName) throw new IOException("Missing filename "+this.fileName+" - check your conf file");
            File f = new File(this.fileName);
            if (null == f) throw new IOException("Missing filename "+this.fileName+" - check your conf file");
            BufferedReader br = new BufferedReader(new FileReader(f));
            out = br.readLine();
            br.close();

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return out;
    }

    public HashMap<String, Object> getValues(){

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

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return this.values;
    }

    public FileInputReader(String valueType){

        this.readerType = "file";
        this.fileName = CoolProperties.getFileName(valueType);
    }

    private String[] getData(String line){
        String[] tokens = new String[1];
        if (line == null) return tokens;
        tokens = line.split("=");
        if (tokens[0] == null)  tokens[0] = "no key";
        if (tokens[1] == null)  tokens[1] = "no value";
        return tokens;
    }







}
