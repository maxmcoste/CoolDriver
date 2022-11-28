package org.maxmcold.readable;

import org.maxmcold.models.*;
import org.maxmcold.models.Readable;

import java.util.HashMap;
import java.util.Iterator;

public class Temperature implements org.maxmcold.models.Readable {

    /**
     * Returns the last long value of input stream temperature written in the form:
     * key = <i>value</i>
     * where key is a String and value is a long
     * ...
     * @return long
     */
    @Override
    public long getValue() {


        InputReader ir = InputReaderFactory.getInputReader(ReadableImpl.Type.TEMPERATURE);
        HashMap<String, Object> values = ir.getValues();
        Iterator<Object> iterator = values.values().iterator();

        long out = -1;

        //TODO: has does not guarantee ordering fifo. Buffering is required
        while (iterator.hasNext()){
            String tmp =iterator.next().toString();
            out = Long.parseLong(tmp);
        }

        return out;
    }

    @Override
    public Status getStatus() {

        //when there is no status related to this readable return always ON
        return Status.ON;
    }
}
