package org.maxmcold.models;

import org.maxmcold.utils.CoolProperties;

import java.io.IOException;
import java.util.Properties;

public class InputReaderFactory {

    public static InputReader getInputReader(Readable.Type type) throws IOException {
        Properties properties = CoolProperties.getProperties();

        return new FileInputReader(type);
    }
}
