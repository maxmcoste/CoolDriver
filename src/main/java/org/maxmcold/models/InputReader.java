package org.maxmcold.models;

public interface InputReader {
    public Readable getReadable();
    public Readable getValues();
    public Readable.Type getType();

}
