package org.maxmcold.models;


import java.util.HashMap;

public class Readable {

    private String code;
    private String description;

    public enum Type {TEMPERATURE,HUMIDITY,SUN,WINDOWSTATUS,AWNINGS}

    public Readable(){
        //create a new default readable object reading from file


    }
    //TODO: transform into singleton
    public HashMap<String, Long> getValues() {
        return values;
    }

    public void setValues(HashMap<String, Long> values) {
        this.values = values;
    }

    private HashMap<String,Long> values;

    public String getCode() {
        return code;
    }
    public void addValue(String key, Long value){
        if (null == values){
            this.values = new HashMap<>();
        }
        this.values.put(key,value);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
