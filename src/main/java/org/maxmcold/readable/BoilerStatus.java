package org.maxmcold.readable;

import org.maxmcold.io.InputReader;
import org.maxmcold.io.InputReaderFactory;
import org.maxmcold.utils.CoolProperties;

import java.util.HashMap;

public class BoilerStatus implements Readable {

    @Override
    public long getLongValue() {
        return 0;
    }

    @Override
    public long getValue() {

        //TODO manage errors
        ReadableDefaultImpl ri = new ReadableDefaultImpl(ReadableFactory.Type.BOILERSTATUS);

        return ri.getValue();
    }

    @Override
    public HashMap<String, Object> getValues() {

        ReadableDefaultImpl ri = new ReadableDefaultImpl(ReadableFactory.Type.BOILERSTATUS);
        return ri.getValues();

    }

    @Override
    public String getStringValue() {
        InputReader ir = InputReaderFactory.getInputReader(ReadableFactory.Type.BOILERSTATUS);
        String out = ir.getValue().toString();
        out = out.split("=")[1];
        return out;
    }
}
