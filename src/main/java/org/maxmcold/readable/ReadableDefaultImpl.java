package org.maxmcold.readable;

import org.maxmcold.io.InputReader;
import org.maxmcold.io.InputReaderFactory;


import java.util.HashMap;

public class ReadableDefaultImpl implements Readable{
    /**
     * Returns the last long value of input stream temperature written in the form:
     * key = <i>value</i>
     * where key is a String and value is a long
     * ...
     * @return long
     */
    ReadableFactory.Type readType;
    public ReadableDefaultImpl(ReadableFactory.Type type){
        this.readType = type;
    }

    @Override
    public long getLongValue() {
        return 0;
    }

    @Override
    public long getValue() {

        //TODO manage errors
        InputReader ir = InputReaderFactory.getInputReader(readType);
        HashMap<String, Object> values = ir.getValues();

        String[] tokens = ir.getValue().toString().split("=");
        Long out = Long.parseLong(tokens[1]);
        return out;
    }


    @Override
    public HashMap<String, Object> getValues() {
        InputReader ir = InputReaderFactory.getInputReader(readType);
        HashMap<String, Object> values = ir.getValues();
        return values;
    }

    @Override
    public String getStringValue() {
        return null;
    }

}
