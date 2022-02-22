package org.maxmcold.models;

import java.util.HashMap;

public interface InputReader {
    String getReaderType();
    HashMap<String, Object> getValues();
    Object getValue();
    HashMap<String,String> getAttributes();
    void putAttribute(String key, String value);


}
