package org.maxmcold.io;

import java.util.HashMap;
import java.util.List;

public interface InputReader {

    String getReaderType();
    HashMap<String, Object> getValues();
    Object getValue();
    HashMap<String,String> getAttributes();
    void putAttribute(String key, String value);
    List<Long> getLongValues();


}
